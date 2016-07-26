package com.jims.doctor.clinicItem.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.*;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * TreatmentServiceImpl
 *
 * @author PangQian
 * @date2016/5/11 0011
 */
@Service
@Transactional(readOnly = false)
public class TreatmentBo extends CrudImplService<OutpTreatRecDao, OutpTreatRec> {
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private ClinicItemDictDao clinicItemDictDao;
    @Autowired
    private PriceListDao priceListDao;

    /**
     * 处置治疗的查询
     *
     * @param clinicId
     * @return
     */
    public List<OutpTreatRec> findTreatment(String clinicId) {
        return outpTreatRecDao.findTreatment(clinicId);
    }

    /**
     * 保存门诊收费明细  检查治疗医嘱明细  门诊医嘱主记录
     *
     * @param outpTreatRecs OutpTreatRec OutpOrders
     * @return
     */
    public String saveClinicItem(List<OutpTreatRec> outpTreatRecs,LoginInfo loginInfo) {
        String num = "";
        List<ClinicItemDict> clinicItemDictList = new ArrayList<ClinicItemDict>();
        String clinicId = outpTreatRecs.get(0).getClinicId();
        for (int i = 0; i < outpTreatRecs.size(); i++) {
            OutpTreatRec outpTreatRec = outpTreatRecs.get(i);
            ClinicItemDict clinicItemDict = new ClinicItemDict();
            clinicItemDict.setItemCode(outpTreatRec.getItemCode());
//            clinicItemDict.setExpand3(outpTreatRec.getPerformedBy());
            clinicItemDict.setExpand4(outpTreatRec.getWardCode());
            clinicItemDict.setOrgId(outpTreatRec.getOrgId());
            clinicItemDictList.add(clinicItemDict);
            if (outpTreatRec.getId() == null || outpTreatRec.getId() == "") {
                ClinicMaster clinicMaster = clinicMasterDao.get(clinicId);
                OrgStaff orgStaff = new OrgStaff();
                // SysUser user=new SysUser();
                String serialNo = IdGen.uuid();
                String orgId = clinicMaster.getOrgId();
                String patientId = clinicMaster.getPatientId();
                OutpOrders outpOrders = new OutpOrders();
                outpOrders.setClinicId(clinicId);
                outpOrders.setPatientId(patientId);
                outpOrders.setOrgId(orgId);
                outpOrders.setDoctor(loginInfo.getPersionId());
                outpOrders.setVisitDate(clinicMaster.getVisitDate());
                outpOrders.setSerialNo(serialNo);
                outpOrders.preInsert();
                outpOrdersDao.saveOutpOrders(outpOrders);
                Integer itemNo = outpTreatRecDao.getMaxItemNo(clinicId, orgId);
                if (itemNo == null) {
                    itemNo = 0;
                }
                outpTreatRec.setChargeIndicator(0);
                Double price = 0.00;
                List<PriceListVo> listPriceListVo = priceListDao.listByClinicItemCodeAndOrgId(orgId, clinicItemDict.getItemCode());
                for (int j = 0; j < listPriceListVo.size(); j++) {
                    PriceListVo priceListVo = listPriceListVo.get(j);
                    OutpOrdersCosts outpOrdersCosts = new OutpOrdersCosts();
                    outpOrdersCosts.setOrgId(orgId);
                    outpOrdersCosts.setClinicId(clinicId);
                    outpOrdersCosts.setVisitNo(clinicMaster.getVisitNo());
                    outpOrdersCosts.setSubjCode(priceListVo.getSubjCode());//会计科目
                    outpOrdersCosts.setClassOnReckoning(priceListVo.getClassOnReckoning());//核算项目分类
                    outpOrdersCosts.setOrderClass(priceListVo.getItemClass());
                    outpOrdersCosts.setPatientId(patientId);
                    outpOrdersCosts.setVisitDate(clinicMaster.getVisitDate());
                    outpOrdersCosts.setSerialNo(serialNo);
                    outpOrdersCosts.setItemNo(j + 1);
                    outpOrdersCosts.setItemCode(priceListVo.getItemCode());
                    outpOrdersCosts.setItemName(priceListVo.getItemName());
                    outpOrdersCosts.setItemClass(priceListVo.getItemClass());
                    outpOrdersCosts.setOrderNo(itemNo);
                    outpOrdersCosts.setOrderSubNo(1);
                    outpOrdersCosts.setItemSpec(priceListVo.getItemSpec());
                    outpOrdersCosts.setUnits(priceListVo.getUnits());
                    outpOrdersCosts.setAmount(outpTreatRec.getAmount());//数量
                    outpOrdersCosts.setRepetition(1);//付数
                    outpOrdersCosts.setPerformedBy(outpTreatRec.getPerformedBy());//执行科室
                    outpOrdersCosts.setOrderedByDept(loginInfo.getDeptId());//开单科室
                    outpOrdersCosts.setOrderedByDoctor(loginInfo.getUserName());//开单医生id
                    outpOrdersCosts.setOrderedByDept(orgStaff.getDeptId());
                    outpOrdersCosts.setOrderedByDoctor(orgStaff.getPersionId());//开单医生
                    price += priceListVo.getPrice()*outpTreatRec.getAmount();
                    outpOrdersCosts.setCosts(priceListVo.getPrice()*outpTreatRec.getAmount());
                    outpOrdersCosts.setCharges(priceListVo.getPrice()*outpTreatRec.getAmount());
                    outpOrdersCosts.setChargeIndicator(0);
                    outpOrdersCosts.preInsert();
                    outpOrdersCostsDao.saveOrdersCosts(outpOrdersCosts);
                }
                outpTreatRec.setCharges(price);
                outpTreatRec.setCosts(price);
                outpTreatRec.setSerialNo(serialNo);
                outpTreatRec.setVisitDate(clinicMaster.getVisitDate());
                outpTreatRec.setOrgId(orgId);
                outpTreatRec.setVisitNo(clinicMaster.getVisitNo());
//                outpTreatRec.setItemClass(clinicItemDict.getItemClass());
                outpTreatRec.setItemCode(clinicItemDict.getItemCode());
//                outpTreatRec.setItemName(clinicItemDict.getItemName());
                outpTreatRec.preInsert();
                outpTreatRecDao.saveTreatRec(outpTreatRec);
            }
        }
        return "success";
    }


    /**
     * 处置治疗的删除
     *
     * @param id
     * @return pq
     */
    public int deleteTreat(String id) {
       String serialNo = outpTreatRecDao.get(id).getSerialNo();
        int num = outpTreatRecDao.deleteTreat(serialNo);
        outpOrdersDao.deleteOutpOrders(serialNo);
        outpOrdersCostsDao.deleteOutpOrdersCosts(serialNo);
        return num;
    }
}
