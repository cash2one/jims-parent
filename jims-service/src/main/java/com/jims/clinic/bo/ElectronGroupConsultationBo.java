package com.jims.clinic.bo;

import com.jims.clinic.dao.ElectronGroupConsultationDao;
import com.jims.clinic.dao.ElectronGroupConsultationInDao;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.service.impl.CrudImplService;
import com.jims.orders.dao.OrdersDao;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 * 会诊主记录Bo
 */
@Service
@Transactional(readOnly = false)
public class ElectronGroupConsultationBo extends CrudImplService<ElectronGroupConsultationDao,ElectronGroupConsultation> {
    @Autowired
    private ElectronGroupConsultationInDao electronGroupConsultationInDao;

    @Autowired
    private
    ElectronGroupConsultationDao electronGroupConsultationDao;

    @Autowired
    private OrdersDao ordersDao;
    /**
     * 保存会诊信息
     * @param electronGroupConsultation
     * @author zhaoning
     * @version 2016-04-23
     */
    //@Override

    public String saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation) {
        int num;
        //electronGroupConsultationDao.saveGroupConsulation(electronGroupConsultation);//保存会诊主记录
		/*if(StringUtils.isBlank(electronGroupConsultation.getId())){
			electronGroupConsultation.setFabuflag("0");
			electronGroupConsultation.setIdeaFlag("0");
			electronGroupConsultation.preInsert();
			electronGroupConsultationDao.insert(electronGroupConsultation);
		}else{
			electronGroupConsultationDao.update(electronGroupConsultation);
		}*/
        electronGroupConsultation.setFabuflag("0");
        electronGroupConsultation.setIdeaFlag("0");
        electronGroupConsultation.preInsert();
        num = electronGroupConsultationDao.insert(electronGroupConsultation);
        if(electronGroupConsultation!=null && electronGroupConsultation.getId()!=null){
            electronGroupConsultationInDao.delByMain(electronGroupConsultation);
            List<ElectronGroupConsultationIn> list = electronGroupConsultation.getElectronGroupConsultationInList();
            if(list.size()>0){
                for (int i=0;i<list.size();i++){
                    ElectronGroupConsultationIn electronGroupConsultationIn=list.get(i);
                    electronGroupConsultationIn.setDelFlag("0");
                    electronGroupConsultationIn.preInsert();
                    electronGroupConsultationIn.setHuizhenId(electronGroupConsultation.getId());
                    electronGroupConsultationInDao.insert(electronGroupConsultationIn);
                    Orders orders = new Orders();
                    orders.preInsert();
                    orders.setPatientId(electronGroupConsultation.getPatientId());
                    orders.setVisitId(electronGroupConsultation.getZhuyuanId());
                    orders.setAppNo(electronGroupConsultation.getId());
                    orders.setDoctor(electronGroupConsultation.getDoctorId());
                    Integer orderNo = ordersDao.getOrderNo(electronGroupConsultation.getPatientId(),electronGroupConsultation.getZhuyuanId(),"");
                    if(orderNo !=null){
                        orders.setOrderNo(orderNo+1);
                        orders.setOrderSubNo(orderNo+1);
                    }else {
                        orders.setOrderNo(1);
                        orders.setOrderSubNo(1);
                    }
                    orders.setOrderClass("D");
                    orders.setStartDateTime(electronGroupConsultation.getShenqingshijian());
                    orders.setRepeatIndicator("0"); // 临时医嘱标志
                    orders.setOrderStatus("6");
                    orders.setFreqDetail("1");
                    orders.setPerformSchedule(newDate());
                    orders.setOrderText(electronGroupConsultation.getHuizhenyijian());
//                    orders.setOrderCode(examItems.getExamItemCode());
                    ordersDao.insert(orders);
                }
            }
        }
        return num+"";
    }
    public String newDate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date=new Date();
        String newDate = dateFormater.format(date);
        return newDate;
    }
    /**异步参与医生信息列表
     * 查询所属同一主表的所有的子表（ElectronGroupConsultationIn）信息List
     * @param “ElectronGroupConsultationIn.id”
     * @author zp
     * @version 2016/7/7
     */
    public void fabu(ElectronGroupConsultation electronGroupConsultation){
        dao.fabu(electronGroupConsultation);
    }

    /**
     * 保存发布会诊人的意见
     * @param "ElectronGroupConsultation.id 会诊主表id"
     * @param "ElectronGroupConsultation.huizhenyijian "
     * @author zp
     * @version 2016-7-7
     */
    public String saveMainOnly(ElectronGroupConsultation electronGroupConsultation){
       int num= electronGroupConsultationDao.saveMainOnly(electronGroupConsultation);
        return num+"";
    }
}
