package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.api.DrugPrescTempServiceApi;
import com.jims.clinic.dao.DrugPrescDetailDao;
import com.jims.clinic.dao.DrugPrescDetailTempDao;
import com.jims.clinic.dao.DrugPrescMasterDao;
import com.jims.clinic.dao.DrugPrescMasterTempDao;
import com.jims.clinic.entity.*;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 待发药主记录
 *
 * @author PangQian
 * @date2016/5/24 0024
 */
@Service(version = "1.0.0")

public class DrugPrescTempServiceImpl extends CrudImplService<DrugPrescMasterTempDao, DrugPrescMasterTemp> implements DrugPrescTempServiceApi {
   @Autowired
   private DrugPrescDetailTempDao drugPrescDetailTempDao;
    @Autowired
    private DrugPrescMasterDao drugPrescMasterDao;
    @Autowired
    private DrugPrescDetailDao drugPrescDetailDao;
    /**
     * 拿到最近一个月的待发药主记录
     * @return
     */
    public List<DrugPrescMasterTemp> getPrescMasterTemp(DrugPrescMasterTemp drugPrescMasterTemp){
        return dao.getPrescMasterTemp(drugPrescMasterTemp);
    }

    /**
     * 通过主表Id找到药品信息
     * @param masterId
     * @return
     */
   public  List<DrugPrescDetailTemp> getDetail(String masterId){
     return   drugPrescDetailTempDao.getDetail(masterId);
   }


    /**
     * 确认发药
     * @param id
     * @return
     */
    public String deleteMaster(String id){
     int num = dao.deleteMaster(id);
        return num+"";
    }

    /**
     * 删除代发药的明细
     * @param masterId
     * @return
     */
    public String deleteDetail(String masterId){
     int num = drugPrescDetailTempDao.deleteDetail(masterId);
        return  num+"";
    }

    /**
     * 确认发药
     * @param id
     * @return
     */
  public String confirmDrug(String id){
      DrugPrescMasterTemp drugPrescMasterTemp= dao.get(id);
      int num=0;
      try {
      if(drugPrescMasterTemp!=null){
          //保存药品主表
          DrugPrescMaster drugPrescMaster=new DrugPrescMaster();
          if (drugPrescMaster.getIsNewRecord()) {
              drugPrescMaster.preInsert();
              drugPrescMaster.setClinicId(drugPrescMasterTemp.getClinicNo());
              drugPrescMaster.setPrescNo(drugPrescMasterTemp.getPrescNo());
              drugPrescMaster.setVisitId("");
              drugPrescMaster.setPrescDate(drugPrescMasterTemp.getPrescDate());
              drugPrescMaster.setPatientId(drugPrescMasterTemp.getPatientId());
              //当前登录者
              drugPrescMaster.setBatchProvideNo("发药人");
              //当处方属性是毒麻属性的时候增加
              if(drugPrescMasterTemp.getPrescAttr()=="duma"){
                 // drugPrescMaster.setBzMz();
              }
              drugPrescMaster.setChargeType(drugPrescMasterTemp.getChargeType());
              drugPrescMaster.setCountPerRepetition(drugPrescMasterTemp.getCountPerRepetition());
              drugPrescMaster.setDispensary(drugPrescMasterTemp.getDispensary());
              drugPrescMaster.setName(drugPrescMasterTemp.getName());
              drugPrescMaster.setNamePhonetic(drugPrescMasterTemp.getNamePhonetic());
              drugPrescMaster.setIdentity(drugPrescMasterTemp.getIdentity());
              drugPrescMaster.setUnitInContract(drugPrescMasterTemp.getUnitInContract());
              drugPrescMaster.setPrescType(drugPrescMasterTemp.getPrescType()!=null?Integer.parseInt(drugPrescMasterTemp.getPrescType()):0);
              drugPrescMaster.setPrescAttr(drugPrescMasterTemp.getPrescAttr());
              drugPrescMaster.setPrescSource(drugPrescMasterTemp.getPrescSource());
              drugPrescMaster.setRepetition(drugPrescMasterTemp.getRepetition());
              drugPrescMaster.setCosts(drugPrescMasterTemp.getCosts());
              drugPrescMaster.setOrderedBy(drugPrescMasterTemp.getOrderedBy());
              drugPrescMaster.setPrescribedBy(drugPrescMasterTemp.getPrescribedBy());
              drugPrescMaster.setEnteredBy(drugPrescMasterTemp.getEnteredBy());
              drugPrescMaster.setDispensingProvider(drugPrescMasterTemp.getDispensingProvider());
              drugPrescMaster.setEnteredDatetime(drugPrescMasterTemp.getEnteredDatetime());
              drugPrescMaster.setDispensingDatetime(drugPrescMasterTemp.getDispensingDatetime());
              drugPrescMaster.setMemo(drugPrescMasterTemp.getMemo());
              drugPrescMaster.setDispensarySub(drugPrescMasterTemp.getDispensarySub());
              //缺少退药
              drugPrescMasterDao.insert(drugPrescMaster);
               }
       //保存药品明细表

          List<DrugPrescDetailTemp> drugPrescDetailTemps=drugPrescDetailTempDao.getDetail(id);
          if(drugPrescDetailTemps!=null && drugPrescDetailTemps.size()>0){
              for(int i=0;i<drugPrescDetailTemps.size();i++){
                  DrugPrescDetail drugPrescDetail=new DrugPrescDetail();
                  DrugPrescDetailTemp drugPrescDetailTemp=drugPrescDetailTemps.get(i);
                  if (drugPrescDetail.getIsNewRecord()) {
                      drugPrescDetail .preInsert();
                      drugPrescDetail.setMasterId(drugPrescMaster.getId());
                      drugPrescDetail.setPrescDate(drugPrescDetailTemp.getPrescDate());
                      drugPrescDetail.setPrescNo(drugPrescDetailTemp.getPrescNo());
                      drugPrescDetail.setItemNo(drugPrescDetailTemp.getItemNo());
                      drugPrescDetail.setDrugCode(drugPrescDetailTemp.getDrugCode());
                      drugPrescDetail.setDrugSpec(drugPrescDetailTemp.getDrugSpec());
                      drugPrescDetail.setDrugName(drugPrescDetailTemp.getDrugName());
                      drugPrescDetail.setFirmId(drugPrescDetailTemp.getFirmId());
                      drugPrescDetail.setPackageSpec(drugPrescDetailTemp.getPackageSpec());
                      drugPrescDetail.setPackageUnits(drugPrescDetailTemp.getPackageUnits());
                      drugPrescDetail.setQuantity(drugPrescDetailTemp.getQuantity());
                      drugPrescDetail.setCosts(drugPrescDetailTemp.getCosts());
                      drugPrescDetail.setPayments(drugPrescDetailTemp.getPayments());
                      drugPrescDetail.setOrderNo(drugPrescDetailTemp.getOrderNo());
                      drugPrescDetail.setOrderSubNo(drugPrescDetailTemp.getOrderSubNo());
                      drugPrescDetail.setAdministration(drugPrescDetailTemp.getAdministration());
                     //缺少退药
                      drugPrescDetail.setDosageEach(drugPrescDetailTemp.getDosageEach());
                      drugPrescDetail.setDosageUnits(drugPrescDetailTemp.getDosageUnits());
                      drugPrescDetail.setFrequency(drugPrescDetailTemp.getFrequency());
                      drugPrescDetail.setFreqDetail(drugPrescDetailTemp.getFreqDetail());
                      drugPrescDetail.setBatchNo(drugPrescDetailTemp.getBatchNo());
                      drugPrescDetailDao.insert(drugPrescDetail);
                  }
              }


          }
      }
      dao.deleteMaster(id);
       num=  drugPrescDetailTempDao.deleteDetail(id);

      } catch (Exception e) {
          e.printStackTrace();
      }

      return String.valueOf(num);
  }
}
