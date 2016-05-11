/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.lab.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.lab.api.LabTestMasterServiceApi;
import com.jims.lab.dao.LabTestItemsDao;
import com.jims.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检验主记录Service
 * @author xueyx
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class LabTestMasterServiceImpl  extends CrudImplService<LabTestMasterDao, LabTestMaster> implements LabTestMasterServiceApi {

    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private OutpOrdersDao outpOrdersDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    /**
     * 保存或编辑
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */
    @Transactional(readOnly = false)
    public void saveAll(LabTestMaster labTestMaster){
        if(labTestMaster!=null && labTestMaster.getId()!=null){
            //本次住院标识对门诊病人为空
            //patientId病人标识号页面有公共值
            //labTestMaster.setPatientId("");
            //todo(userid)申请医生
            labTestMaster.setOrderingProvider("");
            //todo(clinicId)申请科室
            labTestMaster.setOrderingDept("");
            //结果状态
            labTestMaster.setResultStatus("1");
            labTestMaster.setDelFlag("0");
            //申请序号
            labTestMaster.setTestNo(dao.creatTestNo());
            labTestMaster.setBillingIndicator(0);
            labTestMaster.setPrintIndicator(0);
            save(labTestMaster);
             /*门诊医嘱主记录*/
            OutpOrders outpOrders =new OutpOrders();//门诊医嘱主记录
            outpOrders.setPatientId(labTestMaster.getPatientId());
            //todo(clinicId) 就诊序号
            //outpOrders.setVisitDate(1);
            //todo(clinicId) 就诊序号
            outpOrders.setVisitNo(1);
            //todo 公共流水号
            outpOrders.setSerialNo("");//流水号
            //todo (clinicId) 开单科室
            outpOrders.setOrderedBy("");
            //todo (userid)
            outpOrders.setDoctor("");
            //todo(clinicId) 就诊序号
            outpOrders.setClinicNo("");
            //todo (userid)
            outpOrders.setDoctorNo("李俊山");//医生代码
            outpOrdersDao.saveOutpOrders(outpOrders);
            List<LabTestItems> list = labTestMaster.getList();
            if(list.size()>0){
                for (int i= 0; i < list.size();i++){
                    LabTestItems labTestItems=list.get(i);
                    OutpTreatRec outpTreatRec = new OutpTreatRec();/*门诊医嘱明细记录*/
                    //取价格
                    outpTreatRec.setCosts(labTestItems.getPrice());
                    outpTreatRec.setCharges(labTestItems.getPrice());
                    /*检验项目*/
                    labTestItems.setDelFlag("0");
                    labTestItems.preInsert();
                    labTestItems.setTestNo(labTestMaster.getTestNo());
                    labTestItemsDao.insert(labTestItems);
                    /*门诊医嘱明细记录*/
                    //todo(clinicId) 就诊序号
                    //outpTreatRec.setVisitDate();
                    //todo(clinicId) 就诊序号
                    outpTreatRec.setVisitNo(1);
                    outpTreatRec.setSerialNo(outpOrders.getSerialNo());
                    //todo (?)
                    //outpTreatRec.setItemNo(1);
                    outpTreatRec.setItemClass("C");
                    outpTreatRec.setItemName(labTestItems.getItemName());
                    outpTreatRec.setItemCode(labTestItems.getItemCode());
                    outpTreatRec.setAmount(Double.valueOf(1));
                    outpTreatRec.setPerformedBy(labTestMaster.getPerformedBy());
                    outpTreatRec.setChargeIndicator(0);
                    outpTreatRec.setAppointNo(labTestMaster.getTestNo());
                    outpTreatRec.setAppointItemNo(labTestItems.getItemNo());
                    outpTreatRecDao.saveTreatRec(outpTreatRec);
                    /*门诊收费明细*/
                    //todo 收费子项
                    OutpOrdersCosts outpOrdersCosts=new OutpOrdersCosts();//门诊收费明细
                    //todo  收费子项价格
                    outpOrdersCosts.setCosts(labTestItems.getPrice());
                    //todo  收费子项价格
                    outpOrdersCosts.setCharges(labTestItems.getPrice());
                    outpOrdersCosts.setPatientId(labTestMaster.getPatientId());
                    //todo(clinicId) 就诊序号
                    //outpOrdersCosts.setVisitDate();
                    //todo(clinicId) 就诊序号
                    outpOrdersCosts.setVisitNo(1);
                    outpOrdersCosts.setSerialNo(outpOrders.getSerialNo());
                    //ORDER_NO
                    //ORDER_SUB_NO
                    //ITEM_NO
                    outpOrdersCosts.setOrderClass("C");
                    outpOrdersCosts.setItemCode(labTestItems.getItemCode());
                    outpOrdersCosts.setItemName(labTestItems.getItemName());
                    //todo ITEM_SPEC UNITS
                    outpOrdersCosts.setOrderedByDept(labTestMaster.getOrderingDept());
                    outpOrdersCosts.setOrderedByDoctor(labTestMaster.getOrderingProvider());
                    outpOrdersCosts.setAmount(Double.valueOf(1));
                    outpOrdersCosts.setPerformedBy(labTestMaster.getPerformedBy());
                    outpOrdersCostsDao.saveOrdersCosts(outpOrdersCosts);
                }
            }
        }
    }
}