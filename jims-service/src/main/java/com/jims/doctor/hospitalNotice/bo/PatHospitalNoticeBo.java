package com.jims.doctor.hospitalNotice.bo;


import com.jims.doctor.hospitalNotice.dao.PatHospitalNoticeDao;
import com.jims.hospitalNotice.entity.PatHospitalNotice;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
/**
 * Created by qinlongxin on 2016/6/1.
 */
@Service
@Transactional(readOnly = false)
public class PatHospitalNoticeBo extends CrudImplService<PatHospitalNoticeDao, PatHospitalNotice> {

    public String savePatHospitalNotice(PatHospitalNotice patHospitalNotice){
        String num ;
        patHospitalNotice.setNoticeId((long)1);
        num= save(patHospitalNotice);
        return num;

    }

}
