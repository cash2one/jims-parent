package com.jims.register.service;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.ClinicReturnedAcctDao;
import com.jims.register.entity.ClinicReturnedAcct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 退号Service
 * @author zhangyao
 * @version 2016-05-19
 */
@Service
@Transactional(readOnly = true)
public class ClinicReturnedAcctServiceImpl extends CrudImplService<ClinicReturnedAcctDao, ClinicReturnedAcct> {


	
}