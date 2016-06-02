package com.jims.clinic.bo;


import com.jims.clinic.dao.PatHospitalNoticeDao;
import com.jims.clinic.entity.PatHospitalNotice;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qinlongxin on 2016/6/1.
 */
@org.springframework.stereotype.Service
@Component
@Transactional(readOnly = false)
public class PatHospitalNoticeBo extends CrudImplService<PatHospitalNoticeDao, PatHospitalNotice> {

}
