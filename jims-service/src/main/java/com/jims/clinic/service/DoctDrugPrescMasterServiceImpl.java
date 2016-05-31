package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.DoctDrugPrescMasterServiceApi;
import com.jims.clinic.dao.DoctDrugPrescDetailDao;
import com.jims.clinic.dao.DoctDrugPrescMasterDao;
import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.dao.OrdersDao;
import com.jims.exam.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 待发药住院处方主记录Service
 * @author CTQ
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DoctDrugPrescMasterServiceImpl extends CrudImplService<DoctDrugPrescMasterDao, DoctDrugPrescMaster> implements DoctDrugPrescMasterServiceApi {
    @Autowired
    DoctDrugPrescDetailDao doctDrugPrescDetailDao;
    @Autowired
    OrdersDao ordersDao;
    /**
     * 根据参数查询列表
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
    @Override
    public List<DoctDrugPrescMaster> findListByParams(DoctDrugPrescMaster doctDrugPrescMaster) {
        return dao.findListByParams(doctDrugPrescMaster);
    }

    /**
     * 保存处方信息及处方明细
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日16:19:11
     * @return
     */
    @Override
    public String savePresc(DoctDrugPrescMaster doctDrugPrescMaster) {
        int num = 0;
        try {
            //保存处方主记录
            doctDrugPrescMaster.setPrescDate(new Date());
            doctDrugPrescMaster.setPrescStatus(0);
            doctDrugPrescMaster.preInsert();
            num = dao.insert(doctDrugPrescMaster);
            //保存处方记录明细
            if(doctDrugPrescMaster.getList()!=null&&doctDrugPrescMaster.getList().size()>0){
                for (DoctDrugPrescDetail ddpd : doctDrugPrescMaster.getList()){
                    if(ddpd.getId()!=null&&!"".equals(ddpd.getId())){
                        doctDrugPrescDetailDao.update(ddpd);
                    }else{
                        ddpd.setPrescMasterId(doctDrugPrescMaster.getId());
                        ddpd.setPrescDate(doctDrugPrescMaster.getPrescDate());
                        ddpd.setItemNo(1);
                        ddpd.setOrderNo(1);
                        ddpd.setOrderSubNo(1);
                        ddpd.preInsert();
                        doctDrugPrescDetailDao.insert(ddpd);
                        //判断是否勾选生成长期或者临时医嘱，如果已勾选，则生成医嘱信息
                        if(!"".equals(doctDrugPrescMaster.getLongTerm())){
                            Orders orders = new Orders();
                            orders.setStartDateTime(doctDrugPrescMaster.getPrescDate());
                            orders.setOrderText(ddpd.getDrugName());
                            orders.setDosage(ddpd.getDosage());
                            orders.setDosageUnits(ddpd.getDosageUnits());
                            orders.setAdministration(ddpd.getAdministration());
                            orders.setFrequency(ddpd.getFrequency());
                           /* orders.setFreqCounter(1);
                            orders.setFreqInterval();
                            orders.setFreqIntervalUnit();
                            orders.setDoctor();*/
//                            orders.setNurse();
                            orders.setOrderClass("A");//药疗
                            orders.setRepeatIndicator(String.valueOf(doctDrugPrescMaster.getLongTerm()));//长期/临时医嘱
//                            orders.setStopDateTime();
                            orders.setOrderCode(ddpd.getDrugCode());//药品代码
                            orders.setOrderStatus("1");//医嘱状态-新开
                            orders.setEnterDateTime(new Date());// 开医嘱录入日期及时间
                            orders.setFreqDetail(ddpd.getFreqDetail());
                            orders.setPatientId(doctDrugPrescMaster.getPatientId());
                            orders.setVisitId(doctDrugPrescMaster.getVisitId());
                            orders.setOrgId(doctDrugPrescMaster.getOrgId());
                            orders.setClinicId(doctDrugPrescMaster.getId());
                            orders.setOrderNo(1);
                            orders.setOrderSubNo(1);
                            //orders.setPerformSchedule();//护士执行时间
                            orders.setBillingAttr(0);//0-正常计价 1-自带药 2-需手工计价 3-不计价
                            /*orders.setLastAcctingDateTime();
                            orders.setPerformResult();
                            orders.setLastPerformDateTime();*/
                            orders.setOrderingDept(doctDrugPrescMaster.getOrderedBy());//科室
                            orders.setDrugBillingAttr(0);//药品计价属性：0正常1自带药
                            //orders.setDoctorUser();;//医生代码
                            /*orders.setStopNurse();
                            orders.setVerifyDataTime();
                            orders.setOrderPrintIndicator();
                            orders.setProcessionDateTime();
                            orders.setProcessionNurse();
                            orders.setStopProcessionDateTime();
                            orders.setStopProcessionNurse();*/
                            orders.setCurrentPrescNo(doctDrugPrescMaster.getPrescNo());
                            /*orders.setCancelDateTime();
                            orders.setCancelDoctor();
                            orders.setDegree();*/
                            orders.preInsert();
                            ordersDao.insert(orders);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(num);
    }
    /**
     * 根据处方主记录ID删除处方明细
     * @param id
     * @author CTQ
     * @date 2016年5月16日16:19:11
     * @return
     */
    @Override
    public String deletePresc(String id) {
        int num = 0;
        try {
            doctDrugPrescDetailDao.removeByMasterId(id);
            num = dao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(num);
    }
    /**
     * 根据参数查询最大处方号+1
     * @param visitId
     * @author CTQ
     * @date 2016年5月17日14:25:04
     * @return
     */
    @Override
    public Integer searchPrescNo(String visitId) {
        return dao.searchPrescNo(visitId);
    }


    /**
     * 查询住院发药的列表
     *
     * @param doctDrugPrescMaster
     * @reurn
     * @thrws
     * @author pq
     * @date 2016/5/30 0030
     */
    public List<DoctDrugPrescMaster> getDrugMasterList(DoctDrugPrescMaster doctDrugPrescMaster){
      return  dao.getDrugMasterList(doctDrugPrescMaster);
    }

    /**
     * 方法 confirmDoctDrugPresc的功能描述
     * 住院处方发药
     * @param id
     * @return
     * @author pq
     * @date 2016/5/30 0030
     */
    public String confirmDoctDrugPresc(String id){
        int num=dao.confirmDoctDrugPresc(id);
       return num+"";
    }
}