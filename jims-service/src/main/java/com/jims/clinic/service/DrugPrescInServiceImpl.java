package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DrugPrescInServiceApi;
import com.jims.clinic.dao.DoctDrugPrescDetailDao;
import com.jims.clinic.dao.DoctDrugPrescMasterDao;
import com.jims.clinic.dao.DrugPrescDetailDao;
import com.jims.clinic.dao.DrugPrescMasterDao;
import com.jims.clinic.entity.*;
import com.jims.common.service.impl.CrudImplService;
import com.jims.drugPresc.dao.InpBillDetailDao;
import com.jims.drugPresc.entity.InpBillDetail;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.List;

/**
 * 住院处方发药
 *
 * @author PangQian
 * @date2016/5/30 0030
 */
@Service(version = "1.0.0")

public class DrugPrescInServiceImpl extends CrudImplService<DoctDrugPrescMasterDao, DoctDrugPrescMaster> implements DrugPrescInServiceApi {
  @Autowired
  private DoctDrugPrescDetailDao doctdoctDrugPrescDetailDao;
    @Autowired
    private InpBillDetailDao inpBillDetailDao;
    @Autowired
    private DrugPrescMasterDao drugPrescMasterDao;
    @Autowired
    private DrugPrescDetailDao drugPrescDetailDao;

    public String confirmInDrugPresc(String masterId){
        int num=0;
        //更新住院代发药主表（更新发药人名目前没更新）
        dao.confirmDoctDrugPresc(masterId);
        DoctDrugPrescMaster doctDrugPrescMaster =  dao.get(masterId);//住院代发药主表
        List<DoctDrugPrescDetail> doctDrugPrescDetailList= doctdoctDrugPrescDetailDao.findListByPrescMasterId(masterId);//住院代发药表明细
        //插入药品处方主表(插入成功后更新药品的结存数)
        DrugPrescMaster drugPrescMaster=new DrugPrescMaster();
        if (drugPrescMaster.getIsNewRecord()) {
            drugPrescMaster.preInsert();
            drugPrescMaster.setClinicId("");
            drugPrescMaster.setPrescNo(doctDrugPrescMaster.getPrescNo());
            drugPrescMaster.setVisitId(doctDrugPrescMaster.getVisitId());
            drugPrescMaster.setPrescDate(doctDrugPrescMaster.getPrescDate());
            drugPrescMaster.setPatientId(doctDrugPrescMaster.getPatientId());
            //当前登录者
            drugPrescMaster.setBatchProvideNo("发药人");
            //当处方属性是毒麻属性的时候增加
            if(doctDrugPrescMaster.getPrescAttr()=="duma"){
                // drugPrescMaster.setBzMz();
            }
            drugPrescMaster.setChargeType(doctDrugPrescMaster.getChargeType());
            drugPrescMaster.setCountPerRepetition(doctDrugPrescMaster.getCountPerRepetition());
            drugPrescMaster.setDispensary(doctDrugPrescMaster.getDispensary());
            drugPrescMaster.setName(doctDrugPrescMaster.getName());
            drugPrescMaster.setNamePhonetic(doctDrugPrescMaster.getNamePhonetic());
            drugPrescMaster.setIdentity(doctDrugPrescMaster.getIdentity());
            drugPrescMaster.setUnitInContract(doctDrugPrescMaster.getUnitInContract());
            drugPrescMaster.setPrescType(doctDrugPrescMaster.getPrescType()!=null?doctDrugPrescMaster.getPrescType():0);
            drugPrescMaster.setPrescAttr(doctDrugPrescMaster.getPrescAttr());
            drugPrescMaster.setPrescSource(doctDrugPrescMaster.getPrescSource());
            drugPrescMaster.setRepetition(doctDrugPrescMaster.getRepetition());
            drugPrescMaster.setCosts(doctDrugPrescMaster.getCosts());
            drugPrescMaster.setOrderedBy(doctDrugPrescMaster.getOrderedBy());
            drugPrescMaster.setPrescribedBy(doctDrugPrescMaster.getPrescribedBy());
            drugPrescMaster.setEnteredBy(doctDrugPrescMaster.getEnteredBy());
            drugPrescMaster.setDispensingProvider(doctDrugPrescMaster.getDispensingProvider());
           /* drugPrescMaster.setEnteredDatetime(doctDrugPrescMaster.getEnteredDatetime());*///录入日期
            drugPrescMaster.setDispensingDatetime(new Date());
            //处方明细记录中有字段“ADMINISTRATION”，是记录使用方法的；
            //处方主记录中有字段“MEMO”，可记录整副药的用法（如中药）；
            drugPrescMaster.setMemo(doctDrugPrescMaster.getUsage());
            drugPrescMaster.setDispensarySub(doctDrugPrescMaster.getDispensarySub());
            //缺少退药
            drugPrescMasterDao.insert(drugPrescMaster);
        }


        if(doctDrugPrescDetailList!=null && doctDrugPrescDetailList.size()>0){
            for(int i=0;i<doctDrugPrescDetailList.size();i++){
                //插入药品处方明细
                DrugPrescDetail drugPrescDetail=new DrugPrescDetail();
                DoctDrugPrescDetail  doctDrugPrescDetail= doctDrugPrescDetailList.get(i);
                if (doctDrugPrescDetail.getIsNewRecord()) {
                    drugPrescDetail .preInsert();
                    drugPrescDetail.setMasterId(drugPrescMaster.getId());
                    drugPrescDetail.setPrescDate(doctDrugPrescDetail.getPrescDate());
                    drugPrescDetail.setPrescNo(doctDrugPrescDetail.getPrescNo());
                    drugPrescDetail.setItemNo(doctDrugPrescDetail.getItemNo());
                    drugPrescDetail.setDrugCode(doctDrugPrescDetail.getDrugCode());
                    drugPrescDetail.setDrugSpec(doctDrugPrescDetail.getDrugSpec());
                    drugPrescDetail.setDrugName(doctDrugPrescDetail.getDrugName());
                    drugPrescDetail.setFirmId(doctDrugPrescDetail.getFirmId());
                    drugPrescDetail.setPackageSpec(doctDrugPrescDetail.getPackageSpec());
                    drugPrescDetail.setPackageUnits(doctDrugPrescDetail.getPackageUnits());
                    drugPrescDetail.setQuantity(doctDrugPrescDetail.getQuantity());
                    drugPrescDetail.setCosts(doctDrugPrescDetail.getCosts());
                    drugPrescDetail.setPayments(doctDrugPrescDetail.getPayments());
                    drugPrescDetail.setOrderNo(doctDrugPrescDetail.getOrderNo());
                    drugPrescDetail.setOrderSubNo(doctDrugPrescDetail.getOrderSubNo());
                    drugPrescDetail.setAdministration(doctDrugPrescDetail.getAdministration());
                    //缺少退药
                    drugPrescDetail.setDosageEach(doctDrugPrescDetail.getDosageEach());
                    drugPrescDetail.setDosageUnits(doctDrugPrescDetail.getDosageUnits());
                    drugPrescDetail.setFrequency(doctDrugPrescDetail.getFrequency());
                    drugPrescDetail.setFreqDetail(doctDrugPrescDetail.getFreqDetail());
                    drugPrescDetail.setBatchNo(doctDrugPrescDetail.getBatchNo());
                    drugPrescDetailDao.insert(drugPrescDetail);
                }
                /******************插入住院收费明细*************************/
                InpBillDetail inpBillDetail = new InpBillDetail();
                if (inpBillDetail.getIsNewRecord()) {
                    inpBillDetail .preInsert();
                    inpBillDetail.setPatientId(doctDrugPrescMaster.getPatientId());
                    inpBillDetail.setVisitId(doctDrugPrescMaster.getVisitId());
                    Integer itemNo = inpBillDetailDao.getMaxItemNo(doctDrugPrescDetail.getPatientId(), doctDrugPrescDetail.getVisitId());
                    inpBillDetail.setItemNo(itemNo!=null?itemNo:1);
                    //通过ITEM_CODE、ITEM_SPEC、UNITS拿到itemClass
                    inpBillDetail.setItemName(doctDrugPrescDetail.getDrugName());
                    inpBillDetail.setItemCode(doctDrugPrescDetail.getDrugCode());
                    inpBillDetail.setItemSpec(doctDrugPrescDetail.getDrugSpec());
                    inpBillDetail.setAmount(doctDrugPrescDetail.getQuantity());
                    inpBillDetail.setUnits(doctDrugPrescDetail.getDosageUnits());
                    inpBillDetail.setOrderedBy(doctDrugPrescMaster.getOrderedBy());
                    inpBillDetail.setPerformedBy("");//执行科室未找到
                    inpBillDetail.setCosts(doctDrugPrescDetail.getCosts());
                    inpBillDetail.setCharges(doctDrugPrescDetail.getPayments());
                    inpBillDetail.setBillingDateTime(new Date());//计价日期及时间
                    inpBillDetail.setOperatorNo("");//计价员号
                    inpBillDetail.setClassOnInpRcpt("");//住院收据分类
                }
               //执行医生当前登录者 order_group 开单医生核算组,order_doctor 开单医生姓名,perform_group 执行医生核算组,
                //perform_doctor 执行医生,doctor_user 医生代码,item_price,
               num = inpBillDetailDao.insert(inpBillDetail);
            }


        }


     return num+"";
    }

}
