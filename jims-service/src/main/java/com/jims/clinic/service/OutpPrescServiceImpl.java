/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpPrescDao;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.service.impl.CrudImplService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    public String save(OutpPresc outpPresc){
        int num = 0;
        try {
            if(outpPresc!=null){
                OutpOrders oo = new OutpOrders();
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
                outpOrdersDao.saveOutpOrders(oo);
                //保存门诊处方药品价目表信息
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
}