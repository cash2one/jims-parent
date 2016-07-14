package com.jims.clinic.bo;

;

import com.jims.clinic.dao.*;
import com.jims.clinic.entity.*;
import com.jims.common.utils.IdGen;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 门诊收费医嘱工具类
 *
 * @author zhangyao
 * @version 2016-5-11
 */
@Service
@Transactional(readOnly = false)
public class CostOrdersUtilsService {
    //就诊记录
    @Autowired
    private ClinicMasterDao clinicMasterDao;

    //收费医嘱明细
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;

    //门诊医嘱记录
    @Autowired
    private OutpOrdersDao outpOrdersDao;

    //检查治疗医嘱明细
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;

    //项目价格
    @Autowired
    private PriceListDao priceListDao ;

    //诊疗项目
    @Autowired
    private ClinicItemDictDao clinicItemDictDao;



    /**
     * 保存收费医嘱
     * @param clinicId 就诊ID
     * @param itemDictList 诊疗项目list
     * @return
     */
    public String save(String clinicId ,List<ClinicItemDict> itemDictList,String appointsId) {
        ClinicMaster clinicMaster=clinicMasterDao.get(clinicId);
        OrgStaff orgStaff=new OrgStaff();
       // SysUser user=new SysUser();
        String serialNo= IdGen.uuid();
        String orgId=clinicMaster.getOrgId();
        String patientId=clinicMaster.getPatientId();
        OutpOrders outpOrders=new OutpOrders();
        outpOrders.setClinicId(clinicId);
        outpOrders.setPatientId(patientId);
        outpOrders.setOrgId(orgId);
        outpOrders.setDoctor(orgStaff.getPersionId());
        outpOrders.setVisitDate(clinicMaster.getVisitDate());
        outpOrders.setSerialNo(serialNo);
        outpOrders.preInsert();
        outpOrdersDao.saveOutpOrders(outpOrders);
        Integer  itemNo=outpTreatRecDao.getMaxItemNo(clinicId, orgId);
        if(itemNo==null){
            itemNo=0;
        }
        for (int i = 0; i <itemDictList.size() ; i++) {
            itemNo++;
            ClinicItemDict clinicItemDict=itemDictList.get(i);
            clinicItemDict.setOrgId(orgId);
             List<ClinicItemDict> clinicItemDictList= clinicItemDictDao.findExisted(clinicItemDict);
            clinicItemDict=clinicItemDictList.get(0);
            OutpTreatRec outpTreatRec=new OutpTreatRec();

            outpTreatRec.setItemNo(itemNo);
            outpTreatRec.setVisitDate(clinicMaster.getVisitDate());
            outpTreatRec.setOrgId(orgId);
            outpTreatRec.setVisitNo(clinicMaster.getVisitNo());
            outpTreatRec.setClinicId(clinicId);
            outpTreatRec.setSerialNo(serialNo);
            outpTreatRec.setAppointNo(appointsId);
            outpTreatRec.setAppointItemNo(i+1);
            outpTreatRec.setItemClass(clinicItemDict.getItemClass());
            outpTreatRec.setItemCode(clinicItemDict.getItemCode());
            outpTreatRec.setItemName(clinicItemDict.getItemName());
            outpTreatRec.setAmount(clinicItemDict.getNum());
            //执行科室
            outpTreatRec.setPerformedBy(clinicItemDict.getExpand3());
            outpTreatRec.setChargeIndicator(0);
            Double price=0.00;
            List<PriceListVo>  listPriceListVo=priceListDao.listByClinicItemCodeAndOrgId(orgId,clinicItemDict.getItemCode());
//            if(listPriceListVo.size()>0){
//                return "项目没有价格，请相关部门设置价格";
//            }
            for (int j = 0; j < listPriceListVo.size(); j++) {
                PriceListVo priceListVo=listPriceListVo.get(j);
                OutpOrdersCosts outpOrdersCosts=new OutpOrdersCosts();
                outpOrdersCosts.setOrgId(orgId);
                outpOrdersCosts.setClinicId(clinicId);
                outpOrdersCosts.setOrderClass(priceListVo.getItemClass());
                outpOrdersCosts.setPatientId(patientId);
                outpOrdersCosts.setVisitDate(clinicMaster.getVisitDate());
                outpOrdersCosts.setSerialNo(serialNo);
                outpOrdersCosts.setItemNo(j+1);
                outpOrdersCosts.setItemCode(priceListVo.getItemCode());
                outpOrdersCosts.setItemName(priceListVo.getItemName());
                outpOrdersCosts.setItemClass(priceListVo.getItemClass());
                outpOrdersCosts.setOrderNo(itemNo);
                outpOrdersCosts.setOrderSubNo(1);
                outpOrdersCosts.setItemSpec(priceListVo.getItemSpec());
                outpOrdersCosts.setUnits(priceListVo.getUnits());
                outpOrdersCosts.setAmount(clinicItemDict.getNum());
                outpOrdersCosts.setOrderedByDept(orgStaff.getDeptId());
                outpOrdersCosts.setOrderedByDoctor(orgStaff.getPersionId());
                outpOrdersCosts.setPerformedBy(clinicItemDict.getExpand3());
                outpOrdersCosts.setCosts(priceListVo.getPrice());
                price+=priceListVo.getPrice();
                outpOrdersCosts.setCharges(priceListVo.getPrice());
                outpOrdersCosts.setChargeIndicator(0);
                outpOrdersCosts.preInsert();
                outpOrdersCostsDao.saveOrdersCosts(outpOrdersCosts);
            }
            outpTreatRec.setCharges(price);
            outpTreatRec.setCosts(price);
            outpTreatRec.preInsert();
            outpTreatRecDao.saveTreatRec(outpTreatRec);
        }
        return "success";
    }
}
