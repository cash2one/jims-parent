package com.jims.blood.bo;

import com.jims.blood.dao.BloodApplylDao;
import com.jims.blood.dao.BloodCapacityDao;
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

    /**
     * 保存用血申请和用血量申请
     *
     * @param bloodApply
     * @return
     */
    public String saveBloodApply(BloodApply bloodApply) {
        int strState=0;
        if (bloodApply.getIsNewRecord()) {
            bloodApply.preInsert();
            bloodApply.setApplyNum(IdGen.uuid());
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
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.insert(bloodCapacity);
                }else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.update(bloodCapacity);
                }

                clinicItemDictList.add(clinicItemDict);
            }
            costOrdersUtilsService.save(bloodApply.getClinicId(), clinicItemDictList, bloodApply.getId());

        }else {
            bloodApply.preUpdate();
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
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.insert(bloodCapacity);
                }else {
                    bloodCapacity.preUpdate();
                    bloodCapacity.setMatchSubNum(i + "");
                    bloodCapacity.setPatientId(bloodApply.getPatientId());
                    bloodCapacity.setClinicId(bloodApply.getClinicId());
                    bloodCapacity.setVisitId(bloodApply.getVisitId());
                    bloodCapacityDao.update(bloodCapacity);
                }
                clinicItemDictList.add(clinicItemDict);
            }
            costOrdersUtilsService.save(bloodApply.getClinicId(), clinicItemDictList, bloodApply.getId());
        }
        return strState+"";
    }
//    public String getMatchSubNum(String applyNum){
//
//        String applyNu=bloodCapacityDao.getMatchSubNum(applyNum);
//        return applyNu;
//    }
}
