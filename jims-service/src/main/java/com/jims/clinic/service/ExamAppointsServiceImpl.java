/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.*;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.api.ExamAppointsServiceApi;
import com.jims.clinic.entity.*;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private ClinicVsChargeDao clinicVsChargeDao;

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
    @Override
    public Integer getMaxExamNo() {
        return examAppointsDao.getMaxExamNo();
    }

    /**
     * 保存检查申请记录
     * @param examAppoints
     * @return
     */
    @Override
    public int batchSave(ExamAppoints examAppoints) {
        int  num=0;
//       if(examAppointsDao.getMaxExamNo()!=null){
//            examAppoints.setExamNo(examAppointsDao.getMaxExamNo()+1+"");
//        }else{
//            examAppoints.setExamNo("1");
//        }
        //添加EXAM_APPOINTS 相应字段
        examAppoints.setCnsltState(1);
        examAppoints.preInsert();
        examAppoints.setPatientId("1111");
        examAppoints.setVisitNo(22);
        examAppoints.setPatientLocalId("1");
        examAppoints.setChargeType("1");
        //设置就诊序号
        examAppoints.setVisitNo((int) Math.random() * 1000);



        List<ExamItems> examItemsList=examAppoints.getExamItemsList();
        for(int i=0;i<examItemsList.size();i++){
            ExamItems examItems=examItemsList.get(i);
            examItems.setAppointsId(examAppoints.getId());
            examItems.setClinicId(examAppoints.getClinicId());
            examItems.setVisitId(examAppoints.getVisitId());
            examItems.preInsert();
            examItemsDao.saveExamItems(examItems);

//            List<ClinicVsCharge> clinicVsChargeList=clinicVsChargeDao.getClinicCsCharge(examItems.getExamItemCode(), "D");
//            for (int j=0;j<clinicVsChargeList.size();j++){
//
//            }

            OutpTreatRec outpTreatRec=new OutpTreatRec();
            outpTreatRec.preInsert();
            outpTreatRec.setItemClass("D");
            outpTreatRec.setClinicId(examItems.getClinicId());
            outpTreatRec.setItemName(examItems.getExamItem());
            outpTreatRec.setItemCode(examItems.getExamItemCode());
            outpTreatRec.setPerformedBy(examAppoints.getPerformedBy());
            outpTreatRec.setCosts(examItems.getCosts());
            outpTreatRec.setCharges(examItems.getCosts());
            outpTreatRec.setAppointNo(examItems.getExamNo());
            outpTreatRec.setRcptNo(examItems.getRcptNo());
            outpTreatRecDao.saveTreatRec(outpTreatRec);

            OutpOrdersCosts outpOrdersCosts=new OutpOrdersCosts();
            outpOrdersCosts.preInsert();
            outpOrdersCosts.setClinicId(outpTreatRec.getClinicId());
            outpOrdersCosts.setPatientId("1111");
            outpOrdersCosts.setOrderNo(22);
            outpOrdersCosts.setItemNo(1);
            outpOrdersCosts.setItemClass("D");
            outpOrdersCosts.setItemCode("123");
            outpOrdersCosts.setItemName(outpTreatRec.getItemName());
            outpOrdersCosts.setPatientId(examAppoints.getPatientId());
            outpOrdersCosts.setVisitDate(outpTreatRec.getVisitDate());
            outpOrdersCosts.setRcptNo(outpTreatRec.getRcptNo());
            outpOrdersCosts.setPerformedBy(outpTreatRec.getPerformedBy());
            //流水号,医嘱号
            outpOrdersCosts.setOrderClass("D");
            outpOrdersCosts.setSerialNo("111");
            outpOrdersCostsDao.saveOrdersCosts(outpOrdersCosts);

        }

        OutpOrders outpOrders = new OutpOrders();
        outpOrders.preInsert();
        outpOrders.setClinicId(examAppoints.getClinicId());
        outpOrders.setPatientId(examAppoints.getPatientId());
        //设置就诊序号
        outpOrders.setClinicId(examAppoints.getClinicId());
        outpOrders.setOrderedBy(examAppoints.getReqDept());
        outpOrders.setOrderDate(examAppoints.getReqDateTime());
        //开单科室
        outpOrders.setOrderedBy(examAppoints.getReqDept());
        outpOrders.setSerialNo("1111");
        outpOrders.setDoctor("张三");
        outpOrdersDao.saveOutpOrders(outpOrders);
        num = examAppointsDao.saveExamAppionts(examAppoints);
        return  num;
    }

}