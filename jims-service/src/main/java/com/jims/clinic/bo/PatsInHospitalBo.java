package com.jims.clinic.bo;

import com.jims.clinic.dao.DoctDrugPrescMasterDao;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.dao.PreDischgedPatsDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.exam.dao.OrdersDao;
import com.jims.finance.dao.PatsInTransferringDao;
import com.jims.finance.entity.PatsInTransferring;
import com.jims.nurse.dao.*;
import com.jims.nurse.entity.*;
import com.jims.patient.entity.PatVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 病人在院记录
 * @author CTQ
 * @date 2016-06-06 09:36:49
 */
@Service
@Transactional(readOnly = false)
public class PatsInHospitalBo  extends CrudImplService<PatsInHospitalDao, PatsInHospital> {
    @Autowired
    PatsInHospitalDao patsInHospitalDao;
    @Autowired
    BedRecDao bedRecDao;
    @Autowired
    TransferDao transferDao;
    @Autowired
    LendBedLogDao lendBedLogDao;
    @Autowired
    AdtLogDao adtLogDao;
    @Autowired
    OrdersGroupRecDao ordersGroupRecDao;
    @Autowired
    PatsInTransferringDao patsInTransferringDao;
    @Autowired
    PatVisitDao patVisitDao;
    @Autowired
    PreDischgedPatsDao preDischgedPatsDao;
    @Autowired
    DoctDrugPrescMasterDao doctDrugPrescMasterDao;
    @Autowired
    OrdersDao ordersDao;

    /**
     * @param     vo        传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: saveHospInfo
     * @Description: (入科操作)
     * @author CTQ
     * @date 2016/6/12
     */
    public String saveHospInfo(ComeDeptVo vo) {

        String patientId = vo.getPatientId();
        int num = 0;
        try {
            PatsInHospital patsInHospital = new PatsInHospital();
            /**1.根据床位ID更新床位状态**/
            BedRec bedRec = bedRecDao.get(vo.getBedRecId());
            //update bed_rec set bed_status ='1' where bed_no =65553 And ward_code ='160101' ;
            bedRec.setBedStatus("1");
            bedRecDao.update(bedRec);
            /**2.插入病人在科记录**/
            Transfer transfer = new Transfer();
            //INSERT INTO transfer ( patient_id , visit_id , dept_stayed , admission_date_time , doctor_in_charge ) Values ( '02000031' , 13303809 , '130502' , '2016-06-04 10:28:48' , '000CWJ' )
            transfer.setPatientId(patientId);
            transfer.setVisitId(vo.getVisitId());
            transfer.setDeptStayed(vo.getDeptStayed());
            transfer.setAdmissionDateTime(vo.getAdmissionDateTime());
            transfer.setDoctorInCharge(vo.getDoctorUser());
            transfer.preInsert();
            transferDao.insert(transfer);
            /**3.判断选中病人科室是否与当前护士科室一致，若不一致，则更新借床和**/

            /**4.插入借床日志记录**/
            LendBedLog lendBedLog = new LendBedLog();
            //INSERT INTO lend_bed_log ( ward_code , Lend_start_date , patient_id , visit_id , dept_code , lend_ward_code , lend_dept_code ) Values ( '160101' , '2016-06-04 10:28:48' , '02000031' , 892338177 , '130502' , '160301' , '130502' )
            lendBedLog.setWardCode(bedRec.getWardCode());
            lendBedLog.setLendStartDate(vo.getAdmissionDateTime());
            lendBedLog.setPatientId(patientId);
            lendBedLog.setVisitId(vo.getVisitId());
            lendBedLog.setDeptCode(vo.getDeptStayed());
        /*lendBedLog.setLendWardCode();//借床护理单元
        lendBedLog.setLendDeptCode();//借床科室*/
            lendBedLog.preInsert();
            lendBedLogDao.insert(lendBedLog);
            /**5.更新病人在科记录**/
            //update transfer SET dept_code_lend ='130502' WHERE patient_id ='02000031' AND visit_id =858783745 And to_char ( admission_date_time , 'yyyy-mm-dd hh:mi:ss' ) =to_char ( '2016-06-04 10:28:48' , 'yyyy-mm-dd hh:mi:ss' )
        /*transfer.setDeptCodeLend();//借床科室*/
            transferDao.update(transfer);
            /**6.插入病人入出转及状态变化日志**/
            AdtLog adtLog = new AdtLog();
            //INSERT INTO adt_log ( ward_code , dept_code , log_date_time , patient_id , visit_id , action , operator_no ) Values ( '160101' , '130502' , '2016-06-04 10:28:48' , '02000031' , 892338177 , 'C' , Upper ( '000TXJ' ) )
//        adtLog.setWardCode();
//        adtLog.setDeptCode();
            adtLog.setLogDateTime(new Date());
            adtLog.setPatientId(vo.getPatientId());
            adtLog.setVisitId(vo.getVisitId());
            adtLog.setAction(vo.getAction());
//        adtLog.setOperatorNo();
            adtLog.preInsert();
            adtLogDao.insert(adtLog);
            /**7.插入核算组记录**/
            OrdersGroupRec ordersGroupRec = new OrdersGroupRec();
            //INSERT INTO ORDERS_group_REC ( PATIENT_ID , VISIT_ID , dept_code , ORDER_GROUP , ORDER_DOCTOR , doctor_user , SUPER_DOCTOR_ID , PARENT_DOCTOR_ID ) Values ( '02000031' , 39714817 , '130502' , '130502' , '000CWJ' , '000CWJ' , '000ZZD' , '000WHW' )
            ordersGroupRec.setPatientId(patientId);
            ordersGroupRec.setVisitId(vo.getVisitId());
//        ordersGroupRec.setDeptCode();//当前登录用户科室
//        ordersGroupRec.setOrderGroup();
            ordersGroupRec.setOrderDoctor(vo.getDoctorUser());
            ordersGroupRec.setDoctorUser(vo.getDoctorUser());
            ordersGroupRec.setSuperDoctorId(vo.getSuperDoctorId());
            ordersGroupRec.setParentDoctorId(vo.getParentDoctorId());
            ordersGroupRec.preInsert();
            ordersGroupRecDao.insert(ordersGroupRec);
            /**8.删除病人转科记录**/
            //DELETE pats_in_transferring where patient_id ='02000031'
            patsInTransferringDao.deleteByPatientId(patientId);
            /**9.更新病人住院记录**/
            PatVisit patVisit =  new PatVisit();
            //update pat_visit SET dept_admission_to ='130502' , adt_room_no ='105' , WEIGHT_BIRTH =NULL , PARITY_NO =NULL , ONSET_DATE ='2016-05-30 00:00:00' where patient_id ='02000031' And visit_id =1
            patVisit.setDeptAdmissionTo(bedRec.getDeptCode());
//        patVisit.setAdtRoomNo();
            patVisit.setId(vo.getVisitId());
            patVisitDao.update(patVisit);
            /**10.更新病人住院记录**/
            //update pat_visit SET body_weight ='10.00' , body_height ='55.0' where patient_id ='02000031' And visit_id =13303809
            patVisit.setBodyHeight(vo.getBodyHeight());
            patVisit.setBodyWeight(vo.getBodyWeight());
            patVisitDao.update(patVisit);
            /**11.更新在院病人记录**/
            //UPDATE "PATS_IN_HOSPITAL" SET "BED_NO" = '17', "ADM_WARD_DATE_TIME" = '2016-06-04 10:28:48', "NURSING_CLASS" = '1', "DOCTOR_IN_CHARGE" = '000CWJ', "PATIENT_CONDITION" = '3', "WARD_CODE" = '160101', "DEPT_CODE" = '120202', "DEPT_CODE_LEND" = '130502', "LEND_INDICATOR" = '1' WHERE "PATIENT_ID" = '02000031'
            patsInHospital.setBedNo(bedRec.getBedNo());
            patsInHospital.setAdmWardDateTime(vo.getAdmissionDateTime());
            patsInHospital.setNursingClass(vo.getNursingClass());
            patsInHospital.setDoctorInCharge(vo.getDoctorUser());
            patsInHospital.setPatientCondition(vo.getPatientCondition());
            patsInHospital.setWardCode(bedRec.getWardCode());
            patsInHospital.setDeptCode(bedRec.getDeptCode());
            patsInHospital.setDeptCodeLend(bedRec.getDeptCode());
            patsInHospital.setLendIndicator(1);

            num = patsInHospitalDao.update(patsInHospital);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(num);
    }
    /**
     * @param       vo      传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: turnOutDept
     * @Description: (病人转科操作)
     * @author CTQ
     * @date 2016/6/12
     */
    public String turnOutDept(ComeDeptVo vo){

        int num = 0;
        try {
            Date date = new Date();
            BedRec bedRec = bedRecDao.get(vo.getBedRecId());
        /*转出*/
            /**1.更新床位状态**/
            //update bed_rec SET bed_status ='0' WHERE ward_code ='160101' AND bed_no =17
            bedRec.setBedStatus("0");
            bedRec.setPatientId(null);
            bedRecDao.update(bedRec);
            /**2.更新在科病人记录**/
            Transfer transfer = new Transfer();
            //UPDATE "TRANSFER" SET "DISCHARGE_DATE_TIME" = '2016-06-08 10:59:15', "DEPT_TRANSFERED_TO" = '121202'
            // WHERE "PATIENT_ID" = '02000032' AND "VISIT_ID" = '1' AND "DEPT_STAYED" = '131002' AND "ADMISSION_DATE_TIME" = '2016-06-06 15:53:54' AND "DISCHARGE_DATE_TIME" IS NULL AND "DEPT_TRANSFERED_TO" IS NULL AND "DOCTOR_IN_CHARGE" = '000KSM'
            transfer.setDischargeDateTime(date);
            transfer.setDeptTransferedTo(vo.getDeptTransferedTo());
            transfer.setPatientId(vo.getPatientId());
            transfer.setVisitId(vo.getVisitId());
            transfer.setDeptStayed(vo.getDeptStayed());
            transferDao.updateInfo(transfer);
            /**3.更新在院病人记录**/
            // update pats_in_hospital SET ward_code =NULL , bed_no =NULL , patient_condition ='3' , adm_ward_date_time =NULL , dept_code =NULL , doctor_in_charge =NULL WHERE ward_code ='160101' AND bed_no =5505041
            PatsInHospital patsInHospital = new PatsInHospital();
            patsInHospital.setWardCode(null);
            patsInHospital.setBedNo(null);
            patsInHospital.setId(vo.getVisitId());
            patsInHospital.setPatientCondition("3");
            patsInHospital.setAdmWardDateTime(null);
            patsInHospital.setDeptCode(null);
            patsInHospital.setDoctorInCharge(null);
            patsInHospitalDao.update(patsInHospital);

            /**4.插入转科病人记录**/
            //INSERT INTO pats_in_transferring ( patient_id , dept_transfered_from , dept_transfered_to , transfer_date_time ) Values ( '02000032' , '131002' , '121202' , '2016-06-08 10:59:15' )
            PatsInTransferring patsInTransferring = new PatsInTransferring();
            patsInTransferring.setPatientId(vo.getPatientId());
            patsInTransferring.setDeptTransferedFrom(bedRec.getDeptCode());
            patsInTransferring.setDeptTransferedTo(vo.getDeptTransferedTo());
            patsInTransferring.setTransferDateTime(date);
            patsInTransferringDao.insert(patsInTransferring);
            /**5.插入病人入出转及状态变化日志**/
            //INSERT INTO adt_log ( ward_code , dept_code , log_date_time , patient_id , visit_id , action , operator_no ) Values ( '160101' , '131002' , '2016-06-08 10:59:15' , '02000032' , 1 , 'E' , Upper ( '000SLX' ) )
            AdtLog adtLog = new AdtLog();
//        adtLog.setWardCode();
//        adtLog.setDeptCode();
            adtLog.setLogDateTime(new Date());
            adtLog.setPatientId(vo.getPatientId());
            adtLog.setVisitId(vo.getVisitId());
            adtLog.setAction(vo.getAction());
//        adtLog.setOperatorNo();
            adtLog.preInsert();
            adtLogDao.insert(adtLog);

            /**6.删除核算组记录**/
            // DELETE From ORDERS_group_REC Where patient_id ='02000032'
            ordersGroupRecDao.deleteByParentId(vo.getPatientId());
            /**6.更新病人入出转及状态变化日志**/
            // update lend_bed_log SET Lend_end_date ='2016-06-08 10:59:15' Where patient_id ='02000032' And Visit_id =5505025 And ward_code ='160101'
            LendBedLog lendBedLog = new LendBedLog();
            lendBedLog.setLendEndDate(date);
            lendBedLog.setPatientId(vo.getPatientId());
            lendBedLog.setVisitId(vo.getVisitId());
            lendBedLog.setWardCode(bedRec.getWardCode());
            lendBedLogDao.updateByParam(lendBedLog);
            /**7..更新在院病人记录**/
            //update pats_in_hospital Set lend_indicator =Null , dept_code_lend =Null Where patient_id ='02000032' And Visit_id =5505025

            patsInHospital = new PatsInHospital();
            patsInHospital.setLendIndicator(null);
            patsInHospital.setPatientId(vo.getPatientId());
            patsInHospital.setDeptCode(null);
            num = patsInHospitalDao.update(patsInHospital);
            /**8.更新在院病人记录 与1操作合为一项**/
            //update BED_REC SET PATIENT_ID =NULL , BED_STATUS ='0' WHERE WARD_CODE ='160101' AND PATIENT_ID ='02000032'
            //bedRecDao.updateByParam(vo.getPatientId(),bedRec.getWardCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(num);
    }
    /**
     * @param        vo     传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: leaveHosp
     * @Description: (病人出院)
     * @author CTQ
     * @date 2016/6/13
     */
    public String leaveHosp(ComeDeptVo vo){

        int num = 0;
        /**1.判断该病人是否有出院通知，如果医生还没有对病人下达出院通知的话，系统会有提示“医生没有开出院通知，不允许出院”**/
        //SELECT count ( *) From pre_dischged_pats where patient_id ='16011346'
        Integer flag = preDischgedPatsDao.findByPatientId(vo.getPatientId());
        if(flag!=null && flag>0){
            /**2.出院前判结帐：如果系统设置中设置了出院前判结帐，系统将进行出院划价，检查该病人是否已结帐，如果没有，则提示“该病人还有未结算项目，出院吗？”，选择【否】的话系统自动中止本次出院处理，选择【是】，继续执行；系统检查是否还有未停止的长期医嘱，如果有，则提示是否中止，选择【否】，系统中止出院处理，选择【是】，则输入停止时间后，完成出院处理**/

            /**3.出院前不判结帐：如果系统设置中未设置出院前判结帐，系统检查该病人是否还有未停的长期医嘱，如果有，则弹出对话框提示“该病人还有没停的长期医嘱，停止吗？”。选择【否】，则中止出院处理；选择【是】，则继续出院处理，需要自动停医嘱，弹出如下图所示的对话框。输入停医嘱时间后选择【是】，则停止所有医嘱后继续出院处理；选择【否】则中止出院处理**/

            /**4.如果还有未发药的处方，则提示“该病人还有未发药处方或退药请求，出院吗？”，选择【是】，则直接出院；选择【否】，则中止出院**/
            Integer dcount = doctDrugPrescMasterDao.findWaitMedicine(vo.getPatientId());
                if(dcount!=null&&dcount>0){
                    /**满足以上四个条件则可以出院**/
                }else{
                    num = -4;
                }
        }else{
            num=-1;
        }
        return String.valueOf(num);
    }
    /**
     * @param     vo       传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: cancelComeDept
     * @Description: (取消入科)
     * @author CTQ
     * @date 2016/6/13
     */
    public String cancelComeDept(ComeDeptVo vo){
        int num = 0;
        PatVisit patVisit = patVisitDao.getPatientInformation(vo.getPatientId());
        /**1.判断是否有下达的医嘱**/
        if(patVisit!=null){
            //SELECT count ( *) FROM orders Where patient_id ='16010726' And Visit_id =1 And start_date_time >= '2016-01-07 15:47:14'
            Integer flag = ordersDao.findOrderCount(vo.getPatientId(),patVisit.getVisitId().toString(),patVisit.getAdmissionDateTime());
            if(flag!=null&&flag>0){
                /**2.判断是否有费用信息**/
                /**3.若无医嘱和费用信息，则可取消入科**/
            }else {
                num = -1;
            }
        }
        return String.valueOf(num);
    }
}
