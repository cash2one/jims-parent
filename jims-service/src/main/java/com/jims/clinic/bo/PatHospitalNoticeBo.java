package com.jims.clinic.bo;


import com.jims.clinic.dao.PatHospitalNoticeDao;
import com.jims.clinic.entity.PatHospitalNotice;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
/**
 * Created by qinlongxin on 2016/6/1.
 */
@Service
@Transactional(readOnly = false)
public class PatHospitalNoticeBo extends CrudImplService<PatHospitalNoticeDao, PatHospitalNotice> {

}
