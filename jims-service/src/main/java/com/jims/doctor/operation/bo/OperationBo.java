package com.jims.doctor.operation.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.doctor.operation.dao.OperationScheduleDao;
import com.jims.doctor.operation.dao.ScheduledOperationNameDao;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 * 门诊手术申请BO
 */
@Service
@Transactional(readOnly = false)
public class OperationBo extends CrudImplService<OperationScheduleDao,OperationSchedule>{

    @Autowired
    private PatsInHospitalDao patsInHospitalDao;
    @Autowired
    private ScheduledOperationNameDao scheduledOperationNameDao;
    @Autowired
    private OperationScheduleDao operationScheduleDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;

    /**
     * 保存门诊
     *
     * @param operationSchedule
     * @return
     */
    public String saveOperationOut(OperationSchedule operationSchedule) {
        int num;
        List<ClinicItemDict> clinicItemDictList = new ArrayList<ClinicItemDict>();
        if (operationSchedule.getIsNewRecord()) {
            int scheduleId = getScheduleId("","",operationSchedule.getClinicId());
            int sId = scheduleId + 1;
            operationSchedule.setScheduleId(sId);
            operationSchedule.setAckIndicator(0);
            operationSchedule.preInsert();
            num = operationScheduleDao.insert(operationSchedule);
            List<ScheduledOperationName> scheduledOperationNameList = operationSchedule.getScheduledOperationNameList();

            for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                ClinicItemDict clinicItemDict = new ClinicItemDict();
                ScheduledOperationName scheduledOperationName = scheduledOperationNameList.get(i);
                scheduledOperationName.setScheduleId(operationSchedule.getId());
                clinicItemDict.setItemCode(scheduledOperationName.getOperationCode());
//                            clinicItemDict.setOrgId(scheduledOperationName.);
                if (scheduledOperationName.getIsNewRecord()) {
                    scheduledOperationName.setOperationNo(i + 1);
                    scheduledOperationName.preInsert();
                    scheduledOperationNameDao.insert(scheduledOperationName);
                } else {
                    scheduledOperationName.preUpdate();
                    scheduledOperationNameDao.update(scheduledOperationName);
                }
                clinicItemDictList.add(clinicItemDict);
            }
            //costOrdersUtilsService.save(operationSchedule.getClinicId(), clinicItemDictList, operationSchedule.getId());
            return num + "";
        }else {
            operationSchedule.preUpdate();
                int scheduleId = getScheduleId("","",operationSchedule.getClinicId());
                int sId= scheduleId+1;
                operationSchedule.setScheduleId(sId);
//                operationScheduleDao.update(operationSchedule);
            operationSchedule.setAckIndicator(0);
            num = operationScheduleDao.update(operationSchedule);
            if (operationSchedule.getScheduledOperationNameList() != null) {
                List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();
                for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                    ClinicItemDict clinicItemDict = new ClinicItemDict();
                    ScheduledOperationName scheduledOperationName = scheduledOperationNameList.get(i);
                    clinicItemDict.setItemCode(scheduledOperationName.getOperationCode());
                    if (scheduledOperationName.getIsNewRecord()) {
                        scheduledOperationName.setOperationNo(i+1);
                        scheduledOperationName.preInsert();
                        scheduledOperationName.setScheduleId(operationSchedule.getId());
                        scheduledOperationNameDao.insert(scheduledOperationName);
                    } else {
                        scheduledOperationName.preUpdate();
                        scheduledOperationNameDao.update(scheduledOperationName);
                    }
                    clinicItemDictList.add(clinicItemDict);
                }
            }
        }
//            return "1";
        //costOrdersUtilsService.save(operationSchedule.getClinicId(),clinicItemDictList,operationSchedule.getId());
        return  num+"";

    }


    /**
     * 找到病人本次住院最大的ScheduleId
     * @return
     */
    public Integer getScheduleId(String patientId, String visitId,String clinicId) {
        Integer scheduleId = operationScheduleDao.getScheduleId("", "", clinicId);
        if (scheduleId == null) {
            scheduleId = 0;
        }
        return scheduleId;
    }

    /**
     * 通过patientId、visitId拿到手术安排
     *
     * @param patientId
     * @param visitId
     * @return
     */
    public List<OperationSchedule> getSchedule(String patientId, String visitId, String clinicId) {
        List<OperationSchedule> operationScheduleList = operationScheduleDao.getScheduleList("", "", clinicId);
        return operationScheduleList;
    }

//    /**
//     * 查询手术名称
//     *
//     * @param patientId
//     * @param visitId
//     * @return
//     */
//    public List<ScheduledOperationName> getOperationName(String patientId, String visitId, String clinicId, String scheduleId) {
//        OperationSchedule operationSchedule = operationScheduleDao.getSchedule(patientId, visitId, clinicId);
//        List<ScheduledOperationName> operationNameList = scheduledOperationNameDao.getOperationName(patientId, visitId, clinicId, scheduleId);
//        return operationNameList;
//    }

    /**
     * 删除手术名称
     *
     * @param id
     * @return
     */
    public int deleteOperationName(String id) {
        int num = operationScheduleDao.deleteOperation(id);
        scheduledOperationNameDao.deleteSchedule(id);
//        OutpTreatRec outpTreatRec = outpTreatRecDao.getSerialNo(id);
//        outpTreatRecDao.deleteTreat(outpTreatRec.getSerialNo());
//        outpOrdersDao.deleteOutpOrders(outpTreatRec.getSerialNo());
//        outpOrdersCostsDao.deleteOutpOrdersCosts(outpTreatRec.getSerialNo());

        return num;
    }


    /**
     * 查询门诊手术确认的列表
     *
     * @return
     * @author pq
     */
    public List<BaseDto> findOperation(OperationSchedule operationSchedule) {
        return operationScheduleDao.findOperation(operationSchedule);
    }

    /**
     * 确认门诊手术
     *
     * @return
     * @author pq
     */
    public String confrimOperation(OperationSchedule operationSchedule) {
        int num = operationScheduleDao.confrimOperation(operationSchedule);
        return num + "";
    }
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public OperationSchedule getOneOperation(String id){
        OperationSchedule operationSchedule = operationScheduleDao.getOneOperation(id);
        return operationSchedule;
    }
}
