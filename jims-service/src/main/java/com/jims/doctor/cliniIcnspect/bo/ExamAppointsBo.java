/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.cliniIcnspect.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.vo.LoginInfo;
import com.jims.doctor.cliniIcnspect.dao.ExamAppointsDao;
import com.jims.doctor.cliniIcnspect.dao.ExamItemsDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 门诊检查预约记录BO层
 *
 * @author zhangyao
 * @version 2016-04-25
 */
@Service
@Transactional(readOnly = false)
public class ExamAppointsBo extends CrudImplService<ExamAppointsDao, ExamAppoints>{

    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    /**
     * 查询预约主记录
     *
     * @param patientId
     * @return
     */

    public List<ExamAppoints> getExamAppionts(String patientId) {
        return examAppointsDao.getExamAppionts(patientId);
    }

    /**
     * 删除预约记录
     *
     * @param ids
     * @return
     */
    public String deleteExamAppionts(String ids) {
        int num =0;
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                List<OutpTreatRec> outpTreatRecList = outpTreatRecDao.getSerialNo(id[j]);
                String serialNo =  outpTreatRecList.get(0).getSerialNo();
                outpTreatRecDao.deleteTreat(serialNo);
                outpOrdersDao.deleteOutpOrders(serialNo);
                outpOrdersCostsDao.deleteOutpOrdersCosts(serialNo);
                examItemsDao.deleteItems(id[j]);
                num = examAppointsDao.deleteExamAppionts(id[j]);
            }

        return num+"";

    }

    /**
     * 获得最大的检查申请号
     *
     * @return
     */
    public Integer getMaxExamNo() {
        return examAppointsDao.getMaxExamNo();
    }

    /**
     * 保存门诊检查申请记录
     * @param examAppoints
     * @return
     */
    public int batchSave(ExamAppoints examAppoints,LoginInfo loginInfo) {
        int  num=0;
        ClinicMaster clinicMaster=clinicMasterDao.get(examAppoints.getClinicId());
        //添加EXAM_APPOINTS 相应字段
        examAppoints.setCnsltState(0);
        examAppoints.preInsert();
        examAppoints.setRegPrnFlag(0);
        examAppoints.setVisitNo(clinicMaster.getVisitNo());
        examAppoints.setReqDateTime(new Date());
        examAppoints.setInOrOut("0");
        //申请序号
        String examNo="JC"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        examAppoints.setExamNo(examNo);
        examAppoints.setReqPhysician(loginInfo.getPersionId());
        examAppoints.setReqDept(loginInfo.getDeptId());
        examAppoints.setDoctorUser(loginInfo.getUserName());
        examAppoints.setOrgId(loginInfo.getOrgId());
        examAppoints.setChargeType(clinicMaster.getChargeType());
        num = examAppointsDao.insert(examAppoints);
//        OutpTreatRec outpTreatRec = new OutpTreatRec();
//        outpTreatRec.setPerformedBy(examAppoints.getPerformedBy());
        List<ClinicItemDict> clinicItemDictList=new ArrayList<ClinicItemDict>();
        List<ExamItems> examItemsList=examAppoints.getExamItemsList();
        for(int i=0;i<examItemsList.size();i++){
            ClinicItemDict clinicItemDict=new ClinicItemDict();
            ExamItems examItems=examItemsList.get(i);
            clinicItemDict.setItemCode(examItems.getExamItemCode());
            examItems.setAppointsId(examAppoints.getId());
            examItems.setOrgId(examAppoints.getOrgId());
            examItems.setClinicId(examAppoints.getClinicId());
            examItems.setVisitId(examAppoints.getVisitId());
            examItems.setExamNo(examAppoints.getExamNo());
            examItems.preInsert();
            examItemsDao.saveExamItems(examItems);
            clinicItemDictList.add(clinicItemDict);
        }

        costOrdersUtilsService.save(examAppoints.getClinicId(),clinicItemDictList,loginInfo,
                examAppoints.getId(),examAppoints.getPerformedBy(),1.00,examAppoints.getExamNo());
        return  num;
    }
}