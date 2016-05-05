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
    @Autowired
    private ExamMasterDao examMasterDao;

    /**
     * 修改检查申请记录
     * @param examAppoints
     * @return
     */
    @Override
    public Integer updateExamAppoints(ExamAppoints examAppoints) {
        //修改EXAM_APPOINTS 相应字段
        examAppoints.setCnsltState(1);
        examAppoints.preUpdate();
        examAppoints.setPatientId("1111");
        examAppoints.setVisitId(1);
        examAppoints.setVisitNo(22);
        examAppoints.setPatientLocalId("1");
        examAppoints.setChargeType("1");
        //设置就诊序号
        examAppoints.setVisitNo((int) Math.random() * 1000);
        List<OutpOrdersCosts> outpOrdersCostses = examAppoints.getOutpOrdersCostses();
        for (int i = 0; i < outpOrdersCostses.size(); i++) {
            OutpOrdersCosts outpOrdersCosts = outpOrdersCostses.get(i);
            outpOrdersCosts.preInsert();
            outpOrdersCosts.setPatientId("1111");
            outpOrdersCosts.setOrderNo(22);
            outpOrdersCosts.setItemNo(1);
            outpOrdersCosts.setItemClass("1");
            outpOrdersCosts.setItemCode("123");
            outpOrdersCosts.setPatientId(examAppoints.getPatientId());
            outpOrdersCosts.setVisitNo(examAppoints.getVisitNo());
            outpOrdersCosts.setVisitDate(examAppoints.getReqDateTime());
            outpOrdersCosts.setMasterId(examAppoints.getId());
            //设置就诊序号
            outpOrdersCosts.setVisitNo(examAppoints.getVisitNo());
            //流水号,医嘱号
            outpOrdersCosts.setOrderClass("A");
            outpOrdersCosts.setSerialNo("111");
            examAppointsDao.update(examAppoints);

            OutpTreatRec outpTreatRec = new OutpTreatRec();
            outpTreatRec.preUpdate();
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
            //设置就诊序号
            outpTreatRec.setVisitNo(examAppoints.getVisitNo());
            outpTreatRecDao.update(outpTreatRec);

            OutpOrders outpOrders = new OutpOrders();
            outpOrders.preUpdate();
            //设置就诊序号
            outpOrders.setVisitNo(examAppoints.getVisitNo());
            outpOrders.setPatientId(outpOrdersCosts.getPatientId());
            outpOrders.setVisitDate(outpOrdersCosts.getVisitDate());
            outpOrders.setVisitNo(outpOrdersCosts.getVisitNo());
            outpOrders.setClinicNo(outpOrdersCosts.getClinicNo());
            outpOrders.setOrderedBy(outpOrdersCosts.getOrderedByDept());
            outpOrders.setSerialNo(outpOrdersCosts.getSerialNo());
            outpOrders.setDoctor("张三");
            outpOrdersDao.update(outpOrders);

            ExamItems examItems = new ExamItems();
            examItems.setAppointsId(examAppoints.getId());
            examItems.setExamItem(outpOrdersCosts.getItemName());
            examItems.preUpdate();
            examItemsDao.update(examAppoints.getExamItems());
        }
        int nun = examAppointsDao.update(examAppoints);
        return nun;
    }

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
     * 删除预约记录
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deleteExamAppionts(String ids) {
        int i = 0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++) {
                ExamAppoints examAppoints=examAppointsDao.get(id[j]);
                int visitNo=examAppoints.getVisitNo();
                examAppointsDao.deleteExamAppionts(id[j]);
                examItemsDao.deleteItems(id[j]);
                outpOrdersCostsDao.deleteOutpOrders(visitNo);
                outpTreatRecDao.deleteTreatRec(visitNo);
                i=outpOrdersDao.deleteOutpOrders(visitNo);
            }
        } catch (Exception e) {
            return i;
        }
        return i;

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

    //保存检查预约记录
    @Override
    public int batchSave(ExamAppoints examAppoints) {
        int num = 0;
//
//        if(examAppointsDao.getMaxExamNo()!=null){
//            examAppoints.setExamNo(examAppointsDao.getMaxExamNo()+1+"");
//        }else{
//            examAppoints.setExamNo("1");
//        }
        //添加EXAM_APPOINTS 相应字段
        examAppoints.setCnsltState(1);
        examAppoints.preInsert();
        examAppoints.setPatientId("1111");
        examAppoints.setVisitId(1);
        examAppoints.setPatientLocalId("1");
        examAppoints.setChargeType("1");
        //设置就诊序号
        examAppoints.setVisitNo((int) Math.random() * 1000);
        List<OutpOrdersCosts> outpOrdersCostses = examAppoints.getOutpOrdersCostses();
        for (int i = 0; i < outpOrdersCostses.size(); i++) {
            OutpOrdersCosts outpOrdersCosts = outpOrdersCostses.get(i);
            outpOrdersCosts.preInsert();
            outpOrdersCosts.setPatientId("1111");
            outpOrdersCosts.setOrderNo(22);
            outpOrdersCosts.setItemNo(1);
            outpOrdersCosts.setItemClass("1");
            outpOrdersCosts.setItemCode("123");
            outpOrdersCosts.setPatientId(examAppoints.getPatientId());
            outpOrdersCosts.setVisitNo(examAppoints.getVisitNo());
            outpOrdersCosts.setVisitDate(examAppoints.getReqDateTime());
            outpOrdersCosts.setMasterId(examAppoints.getId());
            //设置就诊序号
            outpOrdersCosts.setVisitNo(examAppoints.getVisitNo());
            //流水号,医嘱号
            outpOrdersCosts.setOrderClass("A");
            outpOrdersCosts.setSerialNo("111");
            outpOrdersCostsDao.insert(outpOrdersCosts);

            OutpTreatRec outpTreatRec = new OutpTreatRec();
            outpTreatRec.preInsert();
            outpTreatRec.setVisitDate(outpOrdersCosts.getVisitDate());
            outpTreatRec.setVisitNo(examAppoints.getVisitNo());
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
            //设置就诊序号
            outpTreatRec.setVisitNo(examAppoints.getVisitNo());
            outpTreatRecDao.insert(outpTreatRec);

            OutpOrders outpOrders = new OutpOrders();
            outpOrders.preInsert();
            //设置就诊序号
            outpOrders.setVisitNo(examAppoints.getVisitNo());
            outpOrders.setPatientId(outpOrdersCosts.getPatientId());
            outpOrders.setVisitDate(outpOrdersCosts.getVisitDate());
            outpOrders.setVisitNo(outpOrdersCosts.getVisitNo());
            outpOrders.setClinicNo(outpOrdersCosts.getClinicNo());
            outpOrders.setOrderedBy(outpOrdersCosts.getOrderedByDept());
            outpOrders.setSerialNo(outpOrdersCosts.getSerialNo());
            outpOrders.setDoctor("张三");
            outpOrdersDao.insert(outpOrders);

            ExamItems examItems = new ExamItems();
            examItems.setAppointsId(examAppoints.getId());
            examItems.setExamItem(outpOrdersCosts.getItemName());
            examItems.preInsert();
            examItemsDao.insert(examItems);
        }
        num = examAppointsDao.insert(examAppoints);
        return num;
    }
}