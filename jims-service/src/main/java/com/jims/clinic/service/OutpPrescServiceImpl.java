/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpPrescDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 处方医嘱明细记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OutpPrescServiceImpl extends CrudImplService<OutpPrescDao, OutpPresc> implements OutpPrescServiceApi{

    @Autowired
    OutpOrdersDao outpOrdersDao;
    @Autowired
    OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    ClinicMasterDao clinicMasterDao;
    public String save(OutpPresc outpPresc){
        int num = 0;
        String serialTemp = null;
        try {
            if(outpPresc!=null){
                /*OutpOrders oo = new OutpOrders();
                //查询序列号
                String snum = outpOrdersDao.getSerialNo();
                //根据执行删除门诊处方明细

                //重新保存门诊处方明细
                if(outpPresc.getList()!=null&&outpPresc.getList().size()>0){
                    for(OutpPresc op : outpPresc.getList()){
                        op.setItemClass(outpPresc.getItemClass());
                        op.setPrescAttr(outpPresc.getPrescAttr());
                        op.setClinicId("1");
                        op.setVisitDate(DateUtils.parseDate("2016-01-23 00:00:00", "yyyy-MM-dd HH:mm:ss"));
                        op.setVisitNo(99297);
                        op.setSerialNo(snum);
                        op.setPrescNo(1);
                        op.setItemNo(1);
                        num = dao.insert(op);
                    }
                }
                //保存门诊医嘱信息
                oo.setPatientId("1");
                oo.setVisitDate(DateUtils.parseDate("2016-01-23 00:00:00","yyyy-MM-dd HH:mm:ss"));
                oo.setVisitNo(99297);
                oo.setSerialNo(snum);
                oo.setOrderedBy("140106");
                oo.setDoctor("李俊山");
                oo.setClinicNo(DateFormatUtils.format(DateUtils.parseDate("2016-01-23 00:00:00", "yyyy-MM-dd HH:mm:ss"), "yyyyMMdd")+oo.getVisitNo());
                oo.setDoctorNo("000LJS");
                outpOrdersDao.saveOutpOrders(oo);*/


                ClinicMaster clinicMaster = clinicMasterDao.getMasterInfo(outpPresc.getVisitDate(), outpPresc.getVisitNo());
              /*  String doctorName = chufangDto.getDoctorName();
                String deptCode = chufangDto.getDeptCode();*/

                Integer orderNo ;
                Integer subOrderNo = 1;

                List<OutpPresc> lists = outpPresc.getList();
                List<OutpOrdersCosts> ordersCostsesList = new ArrayList<OutpOrdersCosts>();
                for (int i = 0; i < lists.size(); ) {
                    OutpPresc op = lists.get(i);
                    op.setChargeIndicator(0); // 未收费
                    // op.setItemClass(outpPresc.getItemClass());
                    // op.setPrescAttr(outpPresc.getPrescAttr());
                    if(StringUtils.isEmpty(op.getSerialNo())) {
                        serialTemp = serialTemp != null ? serialTemp : outpOrdersDao.getSerialNo()+"";
                        op.setSerialNo(serialTemp);
                    }else{
                        // 处方修改而非新增
                        serialTemp = op.getSerialNo();
                    }
                    if( op.getOrderNo() == null ){  // 修改处方新增子药品
                        orderNo = dao.getOrderNo(clinicMaster.getVisitDate(), clinicMaster.getVisitNo(), serialTemp);
                        if( ++i < lists.size()-1 && lists.get(i).getSubOrderNo() != null ){
                            // 下一条药品为子药品
                            op.setOrderNo(++orderNo);
                            op.setSubOrderNo(++subOrderNo);
                            lists.get(i).setOrderNo(orderNo);
                            lists.get(i).setSubOrderNo(++subOrderNo);
                        }else{
                            // 下一条医嘱非子医嘱 orderNo++ subOrderNo 赋值为初始值
                            subOrderNo=0;
                            op.setOrderNo(++orderNo);
                            op.setSubOrderNo(++subOrderNo);
                        }
                    } else {
                        ++i;
                    }
                    // 否则orderNo subOrderNo都按照前台传递的参数存储
                    ordersCostsesList.add(makeOutpOrderCosts(op));
                    dao.insert(op);
                }
                //保存门诊处方药品价目表信息
                saveOutpOrdersCosts(ordersCostsesList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return String.valueOf(num);
    }
    /**
     * 根据病人诊断记录查询处方主记录
     * @param clinicMasterId
     * @return
     */
    @Override
    public List<OutpPresc> getOutpPresc(String clinicMasterId) {
        return dao.getOutpPresc(clinicMasterId);
    }


    /**
     * @param   ordersCostsesList          传递参数
     * @return int    返回类型
     * @throws
     * @Title: saveOutpOrdersCosts
     * @Description: (保存门诊处方药品收费明细)
     * @author CTQ
     * @date 2016/5/7
     */
    public int saveOutpOrdersCosts(List<OutpOrdersCosts> ordersCostsesList){
        int num = 0 ;
        try{
            if(ordersCostsesList!=null&&ordersCostsesList.size()>0){
                for(OutpOrdersCosts outpOrdersCosts : ordersCostsesList) {
                    num = outpOrdersCostsDao.insert(outpOrdersCosts);
                }
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return num;
    }

    /**
     * @param      outpPresc       传递参数
     * @return com.jims.clinic.entity.OutpOrdersCosts    返回类型
     * @throws
     * @Title: makeOutpOrderCosts
     * @Description: (设置收费项目数据)
     * @author CTQ
     * @date 2016/5/7
     */
    public OutpOrdersCosts makeOutpOrderCosts(OutpPresc outpPresc){

        OutpOrdersCosts outpOrdersCosts = new OutpOrdersCosts();
        outpOrdersCosts.setPatientId(outpPresc.getClinicId());
        outpOrdersCosts.setSerialNo(outpPresc.getSerialNo());
        outpOrdersCosts.setVisitDate(outpPresc.getVisitDate());
        outpOrdersCosts.setVisitNo(outpPresc.getVisitNo());
        outpOrdersCosts.setClinicNo(outpPresc.getSerialNo());
        outpOrdersCosts.setOrderClass(outpPresc.getItemClass());
        outpOrdersCosts.setOrderNo(outpPresc.getOrderNo());
        outpOrdersCosts.setOrderSubNo(outpPresc.getSubOrderNo());
        outpOrdersCosts.setItemNo(1);
        outpOrdersCosts.setItemClass(outpPresc.getItemClass());
        outpOrdersCosts.setItemName(outpPresc.getDrugName());
        outpOrdersCosts.setItemCode(outpPresc.getDrugCode());
        outpOrdersCosts.setItemSpec(outpPresc.getDrugSpec());
        outpOrdersCosts.setUnits(outpPresc.getUnits());
        outpOrdersCosts.setRepetition(outpPresc.getRepetition());
        outpOrdersCosts.setAmount(outpPresc.getAmount());
        outpOrdersCosts.setOrderedByDept(""); // 当前医师坐诊科室
        outpOrdersCosts.setOrderedByDoctor("");
        outpOrdersCosts.setPerformedBy("");
        outpOrdersCosts.setCosts(outpPresc.getCosts());
        outpOrdersCosts.setOrderClass(outpPresc.getItemClass()); //
        return  outpOrdersCosts;
    }
}