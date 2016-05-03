/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamAppointsServiceApi;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.*;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 检查预约记录Service
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamAppointsServiceImpl extends CrudImplService<ExamAppointsDao, ExamAppoints> implements ExamAppointsServiceApi {

    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private OutpOrdersDao outpOrdersDao;

    /**
     * 查询预约主记录
     *
     * @param patientId
     * @return
     */
    @Override
    public List<ExamAppoints> getExamAppionts(String patientId) {
        return examAppointsDao.getExamAppionts(patientId);
    }

    /**
     * 保存检查预约记录
     *
     * @param examAppoints
     * @return
     */
    @Override
    public void saveExamAppionts(ExamAppoints examAppoints) {
        examAppointsDao.saveExamAppionts(examAppoints);
    }

    /**
     * 删除预约记录
     *
     * @param examNo
     * @return
     */
    @Override
    public Integer deleteExamAppionts(String examNo) {
       int delApp= examAppointsDao.deleteExamAppionts(examNo);
        examItemsDao.deleteItems(examNo);
        return delApp;
    }

    /**
     * 获得最大的检查申请号
     *
     * @return
     */
    @Override
    public Integer getMaxExamNo() {
        return examAppointsDao.getMaxExamNo();
    }

    @Override
    public int batchSave(ExamAppoints examAppoints) {
        //保存检查预约记录
        if(examAppointsDao.getMaxExamNo()!=null){
            examAppoints.setExamNo(examAppointsDao.getMaxExamNo()+1+"");
        }else{
            examAppoints.setExamNo("1");
        }
        examAppoints.setCnsltState(1);

        List<ExamItems> examItemses=examAppoints.getExamItemses();
        List<OutpTreatRec> outpTreatRecs=examAppoints.getOutpTreatRecs();
        List<OutpOrdersCosts> outpOrdersCostses=examAppoints.getOutpOrdersCostses();
        for(int i=0;i<outpOrdersCostses.size();i++){
            OutpOrdersCosts outpOrdersCosts=outpOrdersCostses.get(i);
            outpOrdersCosts.preInsert();
            outpOrdersCosts.setPatientId(examAppoints.getPatientId());
            outpOrdersCosts.setVisitNo(examAppoints.getVisitNo());
            outpOrdersCosts.setVisitDate(examAppoints.getReqDateTime());
            outpOrdersCostsDao.saveOrdersCosts(outpOrdersCosts);
            ExamItems examItems=new ExamItems();
        //    ExamItems examItems=examItemses.get(i);
            examItems.setExamNo(examAppoints.getExamNo());
            examItems.preInsert();
            examItemsDao.saveExamItems(examItems);

         //   OutpTreatRec outpTreatRec=outpTreatRecs.get(i);
            OutpTreatRec outpTreatRec=new OutpTreatRec();
            outpTreatRec.preInsert();
            outpTreatRec.setVisitDate(outpOrdersCosts.getVisitDate());
            outpTreatRec.setVisitNo(outpOrdersCosts.getVisitNo());
            outpTreatRec.setItemNo(outpOrdersCosts.getItemNo());
            outpTreatRec.setItemClass(outpOrdersCosts.getItemClass());
            outpTreatRec.setItemName(outpOrdersCosts.getItemName());
            outpTreatRec.setItemCode(outpOrdersCosts.getItemCode());
            outpTreatRec.setItemSpec(outpOrdersCosts.getItemSpec());
            outpTreatRec.setUnits(outpOrdersCosts.getUnits());
            outpTreatRec.setAmount(outpOrdersCosts.getAmount());
            outpTreatRec.setSerialNo(outpOrdersCosts.getSerialNo());
            outpTreatRec.setPerformedBy(outpOrdersCosts.getPerformedBy());
            outpTreatRec.setCosts(outpOrdersCosts.getCosts());
            outpTreatRec.setCharges(outpOrdersCosts.getCharges());
            outpTreatRec.setChargeIndicator(outpOrdersCosts.getChargeIndicator());
            outpTreatRec.preInsert();
            outpTreatRecDao.saveTreatRec(outpTreatRec);

            OutpOrders outpOrders = new OutpOrders();
            outpOrders.preInsert();
            outpOrders.setPatientId(outpOrdersCosts.getPatientId());
            outpOrders.setVisitDate(outpOrdersCosts.getVisitDate());
            outpOrders.setVisitNo(outpOrdersCosts.getVisitNo());
            outpOrders.setClinicNo(outpOrdersCosts.getClinicNo());
            outpOrders.setOrderedBy(outpOrdersCosts.getOrderedByDept());
            outpOrders.setSerialNo(outpOrdersCosts.getSerialNo());
        //    outpOrders.setDoctor(outpOrdersCostsDto.getDoctorName());
            outpOrdersDao.saveOutpOrders(outpOrders);

        }
//        for(int i=0;i<outpTreatRecs.size();i++){
//            OutpTreatRec outpTreatRec=outpTreatRecs.get(i);
//            outpTreatRec.preInsert();
//            outpTreatRecDao.saveTreatRec(outpTreatRec);
//        };
        examAppoints.preInsert();
        int  num = examAppointsDao.saveExamAppionts(examAppoints);
        return  num;
    }

}