/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.cliniIcnspect.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.cliniIcnspect.dao.ExamAppointsDao;
import com.jims.doctor.cliniIcnspect.dao.ExamItemsDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
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
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                examItemsDao.deleteItems(id[j]);
                ExamAppoints examAppoints=examAppointsDao.get(id[j]);
                String clinicId=examAppoints.getClinicId();
//                ExamItems examItems=examItemsDao.getItemList(id[j]);
                outpTreatRecDao.deleteTreat(clinicId);
                outpOrdersCostsDao.deleteOutpOrders(clinicId);
                num = examAppointsDao.deleteExamAppionts(id[j]);

            }
        }catch(Exception e){
            return num+"";
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
    public int batchSave(ExamAppoints examAppoints) {
        int  num=0;
        ClinicMaster clinicMaster=clinicMasterDao.get(examAppoints.getClinicId());
        //添加EXAM_APPOINTS 相应字段
        examAppoints.setCnsltState(0);
        examAppoints.preInsert();
        examAppoints.setRegPrnFlag(0);
        examAppoints.setVisitNo(clinicMaster.getVisitNo());
        //申请序号
        String examNo="JC"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        examAppoints.setExamNo(examNo);
        examAppoints.setChargeType(clinicMaster.getChargeType());
        num = examAppointsDao.insert(examAppoints);
        List<ClinicItemDict> clinicItemDictList=new ArrayList<ClinicItemDict>();
        List<ExamItems> examItemsList=examAppoints.getExamItemsList();
        for(int i=0;i<examItemsList.size();i++){
            ClinicItemDict clinicItemDict=new ClinicItemDict();
            ExamItems examItems=examItemsList.get(i);
            clinicItemDict.setItemCode(examItems.getExamItemCode());
            examItems.setAppointsId(examAppoints.getId());
            examItems.setClinicId(examAppoints.getClinicId());
            examItems.setVisitId(examAppoints.getVisitId());
            examItems.setExamNo(examAppoints.getExamNo());
            examItems.preInsert();
            examItemsDao.saveExamItems(examItems);
            clinicItemDictList.add(clinicItemDict);
        }

        costOrdersUtilsService.save(examAppoints.getClinicId(),clinicItemDictList,examAppoints.getId());
        return  num;
    }
}