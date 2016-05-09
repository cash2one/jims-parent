package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.TreatmentServiceApi;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.entity.OutpOrders;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TreatmentServiceImpl
 *
 * @author PangQian
 * @date2016/5/7 0007
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class TreatmentServiceImpl extends CrudImplService<OutpOrdersCostsDao,OutpOrdersCosts> implements TreatmentServiceApi {
   @Autowired
   private  OutpOrdersCostsDao outpOrdersCostsDao;
   @Autowired
   private OutpOrdersDao outpOrdersDao;


    public List<OutpOrdersCosts> findTreatment(OutpOrdersCosts outpOrdersCosts){
      return   outpOrdersCostsDao.findTreatment(outpOrdersCosts);
    }

    /**
     * 保存门诊收费明细  检查治疗医嘱明细  门诊医嘱主记录
     * @param outpOrdersCostsList OutpTreatRec OutpOrders
     * @return
     */
    public  String saveClinicItem(List<OutpOrdersCosts> outpOrdersCostsList){
        if(outpOrdersCostsList.size()!=0){
           for(int i=0;i<outpOrdersCostsList.size();i++){
               OutpOrdersCosts outpOrdersCosts=new OutpOrdersCosts();//门诊收费明细
               OutpTreatRec outpTreatRec = new OutpTreatRec();//检查治疗医嘱明细
               OutpOrders outpOrders =new OutpOrders();//门诊医嘱主记录
               outpOrdersCosts=outpOrdersCostsList.get(i);
               //通过clinicId(patientId)拿到visitDate、visitNo  现在是死数据
               /*门诊医嘱主记录*/
               outpOrders.setPatientId("15001159");//clinicId

               outpOrders.setVisitDate(new Date());
               outpOrders.setVisitNo(637);
               outpOrders.setSerialNo("000001");//流水号
               outpOrders.setOrderedBy("急诊科");//开单科室(当前科室)
               outpOrders.setDoctor("李俊山");//开单医生(当前医生)
               outpOrders.setOrderDate(new Date());//开单日期（当前日期）
               outpOrders.setDoctorNo("李俊山");//?
               outpOrders.setNurse("护士");//当前护士
               outpOrdersDao.saveOutpOrders(outpOrders);
               /*检查治疗医嘱明细*/
               outpTreatRec.setVisitDate(new Date());
               outpTreatRec.setVisitNo(637);
               outpTreatRec.setSerialNo("000001");
               outpTreatRec.setItemNo(1);//?


              return save(outpOrdersCosts);
           }
        }else{
            return "0";
        }
     return "0";
    }

}
