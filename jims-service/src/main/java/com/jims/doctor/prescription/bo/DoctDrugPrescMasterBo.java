package com.jims.doctor.prescription.bo;


import com.jims.prescription.entity.DoctDrugPrescDetail;
import com.jims.prescription.entity.DoctDrugPrescMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.doctor.prescription.dao.DoctDrugPrescDetailDao;
import com.jims.doctor.prescription.dao.DoctDrugPrescMasterDao;
import com.jims.orders.dao.OrdersCostsDao;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import com.jims.orders.entity.OrdersCosts;
import com.jims.sys.dao.AdministrationDictDao;
import com.jims.sys.dao.PerformDefaultScheduleDao;
import com.jims.sys.dao.PerformFreqDictDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.PerformDefaultSchedule;
import com.jims.sys.entity.PerformFreqDict;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 待发药住院处方主记录Bo
 * @author CTQ
 * @version 2016-07-5
 */

@Service
@Transactional(readOnly = false)
public class DoctDrugPrescMasterBo extends CrudImplService<DoctDrugPrescMasterDao, DoctDrugPrescMaster> {
    @Autowired
    private DoctDrugPrescDetailDao doctDrugPrescDetailDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private OrdersCostsDao ordersCostsDao;
    @Autowired
    private PerformFreqDictDao performFreqDictDao;
    @Autowired
    private PerformDefaultScheduleDao performDefaultScheduleDao;
    @Autowired
    private AdministrationDictDao administrationDictDao;

    /**
     * 根据参数查询列表
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
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
                        ddpd.preInsert();
                        doctDrugPrescDetailDao.insert(ddpd);
                        //判断是否勾选生成长期或者临时医嘱，如果已勾选，则生成医嘱信息----默认生成长期医嘱
                       /* if(!"".equals(doctDrugPrescMaster.getLongTerm())){
*/
                            Orders orders = new Orders();
                            orders.setStartDateTime(new Date());
                            orders.setOrderText(ddpd.getDrugName());
                            orders.setDosage(ddpd.getDosage());
                            orders.setDosageUnits(ddpd.getDosageUnits());
                            orders.setAdministration(ddpd.getAdministration());
                            orders.setFrequency(ddpd.getFrequency());
                            orders.setOrderClass("A");//药疗
                            orders.setRepeatIndicator(String.valueOf(doctDrugPrescMaster.getLongTerm()));//长期/临时医嘱
                            orders.setOrderCode(ddpd.getDrugCode());//药品代码
                            orders.setOrderStatus("1");//医嘱状态-传输状态
                            orders.setEnterDateTime(new Date());// 开医嘱录入日期及时间
                            orders.setFreqDetail(ddpd.getFreqDetail());
                            orders.setPatientId(doctDrugPrescMaster.getPatientId());
                            orders.setVisitId(doctDrugPrescMaster.getVisitId());
                            orders.setOrgId(doctDrugPrescMaster.getOrgId());
                            orders.setClinicId(doctDrugPrescMaster.getId());
                            orders.setOrderNo(ddpd.getOrderNo());
                            orders.setOrderSubNo(ddpd.getOrderSubNo());
                            if(doctDrugPrescMaster.getLongTerm()!=0){
                                //根据频次id查询频次信息
                                PerformFreqDict performFreqDict = performFreqDictDao.get(ddpd.getFrequency());
                                 orders.setFreqCounter(performFreqDict.getFreqCounter());
                                orders.setFreqInterval(performFreqDict.getFreqInterval());
                                orders.setFreqIntervalUnit(performFreqDict.getFreqIntervalUnits());
                                orders.setFreqDetail(performFreqDict.getFreqDesc());

                                //根据频次和途径id 获取执行时间
                                PerformDefaultSchedule pds = new PerformDefaultSchedule();
                                pds.setAdministration(ddpd.getAdministration());
                                pds.setFreqDesc(ddpd.getFrequency());
                                pds = performDefaultScheduleDao.findByParams(pds);
                                orders.setPerformSchedule(pds.getDefaultSchedule());//护士执行时间
                            }else{
                                orders.setPerformSchedule(DateFormatUtils.format(new Date(),"HH:ss"));//护士执行时间
                            }
                            orders.setDoctor("");
                            orders.setBillingAttr(3);//0-正常计价 1-自带药 2-需手工计价 3-不计价4-不摆药
                            orders.setOrderingDept("");//科室-当前科室
                            orders.setDrugBillingAttr(0);//药品计价属性：0正常1自带药
                            orders.setCurrentPrescNo(doctDrugPrescMaster.getPrescNo());
                            //orders.setDoctorUser();;//医生代码
                            /*orders.setOrderPrintIndicator();
                            orders.setDegree();*/
                            orders.preInsert();
                            ordersDao.insert(orders);
                            /*INSERT INTO "ORDERS_COSTS"
                            ("ITEM_NAME","AMOUNT", "UNITS", "ITEM_CODE","ITEM_NO","ORDER_SUB_NO", "ORDER_NO",  "VISIT_ID",   "PATIENT_ID","COSTS","ITEM_SPEC", "BACKBILL_RULE")
                            VALUES ('静脉输液',  '1.0000',  '组',   '12040000600',   'E',  '1',  '1', '53',  '1',  '15002959', '6.00', '/',    NULL)*/
                            /**根据参数查询途径收费项**/
                        if(ddpd.getAdministration()!=null&&!"".equals(ddpd.getAdministration())){
                            AdministrationDict administrationDict = administrationDictDao.get(ddpd.getAdministration());
                            if(administrationDict!=null){
                                BaseDto baseDto = administrationDictDao.findByParams(administrationDict.getAdministrationName(),orders.getOrgId());
                                if(baseDto!=null){
                                    OrdersCosts ordersCosts = new OrdersCosts();
                                    ordersCosts.setItemName(baseDto.get("item_name").toString());
                                    ordersCosts.setAmount(Double.valueOf(baseDto.get("amount").toString()));
                                    ordersCosts.setUnits(baseDto.get("units").toString());
                                    ordersCosts.setItemCode(baseDto.get("item_code").toString());
                                    ordersCosts.setItemNo(Integer.valueOf(baseDto.get("item_no").toString()));
                                    ordersCosts.setOrderNo(orders.getOrderNo());
                                    ordersCosts.setOrderSubNo(orders.getOrderSubNo());
                                    ordersCosts.setPatientId(orders.getPatientId());
                                    ordersCosts.setVisitId(orders.getVisitId());
                                    ordersCosts.setCosts(Double.valueOf(baseDto.get("cost").toString()));
                                    ordersCosts.setItemSpec(baseDto.get("item_spec").toString());
                                    ordersCosts.setBackbillRule(baseDto.get("backbill_rule").toString());
                                    ordersCosts.preInsert();
                                    ordersCostsDao.insert(ordersCosts);
                                }
                            }
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
    public String deletePresc(String id) {
        int num = 0;
        try {
            Orders orders = new Orders();
            doctDrugPrescDetailDao.removeByMasterId(id);
            orders.setStopDoctor("");
            orders.setOrderStatus("8");
            orders.setClinicId(id);
            ordersDao.updateOrders(orders);
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