package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.MrIndex;
import com.jims.clinic.entity.MrOnLine;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.utils.DateUtils;
import com.jims.nurse.dao.OrdersGroupRecDao;
import com.jims.nurse.entity.OrdersGroupRec;
import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by che on 2016/4/20.
 */
@Service(version = "1.0.0")
public class PatVisitServiceImpl extends CrudImplService<PatVisitDao,PatVisit> implements PatVisitServiceApi {
     @Autowired
    private   PatVisitDao  patVisitDao;
    @Autowired
    private OrdersGroupRecDao ordersGroupRecDao;
    @Autowired
    private PatsInHospitalDao patsInHospitalDao;
    @Autowired
    private MrIndexDao mrIndexDao;
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;
    @Autowired
    private MrOnLineDao mrOnLineDao;

    /**
     * 查询病人列表
     * @param deptCode
     * @return
     * @author zhaoning
     */
    @Override
    public List<PatientListDto> getPatientList(String deptCode,String status,String patName,String startTime,String endTime) {
        List<PatientListDto> list= new ArrayList<PatientListDto>();
        if(status!=null && status.equals("0")){
            list=patVisitDao.getPatientListInHos(deptCode,patName,startTime,endTime);
        }else if(status!=null && status.equals("1")){
            list=patVisitDao.getPatientListOutHos(deptCode,patName,startTime,endTime);
        }
        return list;
    }

    /**
     * 点击用血申请获取病人信息通过patient_id获得
     *
     * @param patientId
     * @return
     * @author zhaoning
     */
    @Override
    public PatVisit getPatientInformation(String patientId) {
        PatVisit patVisit = patVisitDao.getPatientInformation(patientId);
        return patVisit;
    }

    /**
     * 查询 所有需要新建病历的病人信息
     * @return
     * @author zhaoning
     */
    @Override
    public List<PatMasterIndex> getPatMaster(String deptCode) {
        return patVisitDao.getPatMaster(deptCode);
    }

    /**
     * 确认新建病历
     * @param patId
     * @return
     */
    @Override
    public String confirmNewMr(String patId) {
        String code="";
        PatMasterIndex patMasterIndex=patMasterIndexDao.get(patId);
       MrIndex mrIndexInfo= mrIndexDao.getMrIndexBypat(patId);
        if(mrIndexInfo==null){//病历未创建
            //插入 mr_index
            MrIndex mrIndex=new MrIndex();
            mrIndex.setPatientId(patId);
            mrIndex.setMrStatus("O");//工作
            mrIndex.setLastAccessUser("当前登录人");
            mrIndex.preInsert();
            mrIndexDao.insert(mrIndex);
            //插入 mr_on_line
            MrOnLine mrOnLine = new MrOnLine();
            mrOnLine.setPatientId(patId);
            mrOnLine.setStatus("0");
            mrOnLine.setRequestDoctorId("当前登录人");//请求医生=主管医生
            mrOnLine.setRequestDateTime(new Date());//请求时间
            mrOnLine.preInsert();
            mrOnLineDao.insert(mrOnLine);
            //更新 pats_in_hospital
            PatsInHospital patsInHospital =patsInHospitalDao.getPatsInfoByMaster(patId);
            if(patsInHospital!=null){
                patsInHospitalDao.updateByMrNew(patsInHospital.getId(),"当前登录人");
            }
            //更新 orders_group_rec
            OrdersGroupRec ordersGroupRec=ordersGroupRecDao.getOrdersByPat(patId);
            if(ordersGroupRec!=null){
                ordersGroupRec.setOrderDoctor("当前登录人");
                ordersGroupRec.setDeptCode(patMasterIndex.getDeptCode());
                ordersGroupRec.setOrderGroup("核算组");
                ordersGroupRec.setDoctorUser("医生代码");
                ordersGroupRecDao.updateByMrNew(ordersGroupRec);
            }
            code="1";
        }else{//病历已创建
             code="2";
        }
        return code;
    }
}
