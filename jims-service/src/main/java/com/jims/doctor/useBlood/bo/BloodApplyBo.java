package com.jims.doctor.useBlood.bo;

import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.vo.LoginInfo;
import com.jims.doctor.useBlood.dao.BloodApplylDao;
import com.jims.doctor.useBlood.dao.BloodCapacityDao;
import com.jims.blood.entity.BloodApply;
import com.jims.blood.entity.BloodCapacity;
import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.utils.IdGen;
import com.jims.orders.dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 * 门诊用血申请BO
 */
@Service
@Transactional(readOnly = false)
public class BloodApplyBo {
    @Autowired
    private BloodApplylDao bloodApplylDao;
    @Autowired
    private BloodCapacityDao bloodCapacityDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;

    /**
     * 保存用血申请和用血量申请
     *
     * @param bloodApply
     * @return
     */
    public String saveBloodApply(BloodApply bloodApply,LoginInfo loginInfo) {
        int strState=0;
        if (bloodApply.getIsNewRecord()) {
            bloodApply.preInsert();
            bloodApply.setApplyNum(IdGen.uuid());
            bloodApply.setOrgId(loginInfo.getOrgId());
            bloodApply.setPhysician(loginInfo.getPersionId());//登陆医生id
            bloodApply.setDeptCode(loginInfo.getDeptId());//病人科室
            strState = bloodApplylDao.insert(bloodApply);
//            bloodCapacityDao.delBloodCapacity(bloodApply.getApplyNum());
            List<ClinicItemDict> clinicItemDictList = new ArrayList<ClinicItemDict>();
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                ClinicItemDict clinicItemDict = new ClinicItemDict();
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                clinicItemDict.setInputCode(bloodCapacity.getApplyNum());
                if(bloodCapacity.getIsNewRecord()){
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setOrgId(bloodApply.getOrgId());
                    bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacityDao.insert(bloodCapacity);
                }else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacityDao.update(bloodCapacity);
                }

                clinicItemDictList.add(clinicItemDict);
            }
            //costOrdersUtilsService.save(bloodApply.getClinicId(), clinicItemDictList, bloodApply.getId());

        }else {
            bloodApply.preUpdate();
//            BloodApply bloodApply1 = bloodApplylDao.get(bloodApply.getId());
//            bloodApply.setApplyNum(bloodApply1.getApplyNum());
            strState = bloodApplylDao.update(bloodApply);
            bloodCapacityDao.delBloodCapacity(bloodApply.getApplyNum());
            List<ClinicItemDict> clinicItemDictList = new ArrayList<ClinicItemDict>();
            List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
            for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
                BloodCapacity bloodCapacity = bloodCapacityList.get(i);
                ClinicItemDict clinicItemDict = new ClinicItemDict();
                bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                clinicItemDict.setInputCode(bloodCapacity.getApplyNum());
                if(bloodCapacity.getIsNewRecord()){
                    bloodCapacity.preInsert();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacityDao.insert(bloodCapacity);
                }else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setApplyId(bloodApply.getId());
                    bloodCapacity.setApplyNum(bloodApply.getApplyNum());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacityDao.update(bloodCapacity);
                }
                clinicItemDictList.add(clinicItemDict);
            }
            //costOrdersUtilsService.save(bloodApply.getClinicId(), clinicItemDictList, bloodApply.getId());
        }
        return strState+"";
    }
//    public String getMatchSubNum(String applyNum){
//
//        String applyNu=bloodCapacityDao.getMatchSubNum(applyNum);
//        return applyNu;
//    }
    public String delete(String ids){
        int num =0;
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
//                List<OutpTreatRec> outpTreatRecList = outpTreatRecDao.getSerialNo(id[j]);
//                String serialNo = outpTreatRecList.get(0).getSerialNo();
                num = bloodApplylDao.deleteBloodApply(id[j]);
                bloodCapacityDao.deleteBloodCapacity(id[j]);
//                outpTreatRecDao.deleteTreat(outpTreatRec.getSerialNo());
//                outpOrdersDao.deleteOutpOrders(outpTreatRec.getSerialNo());
//                outpOrdersCostsDao.deleteOutpOrdersCosts(outpTreatRec.getSerialNo());
            }
        return num+"";
    }
}
