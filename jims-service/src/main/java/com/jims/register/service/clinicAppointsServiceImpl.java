package com.jims.register.service;



import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicAppointsServiceApi;
import com.jims.register.dao.ClinicAppointsDao;
import com.jims.register.entity.ClinicAppoints;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 预约挂号Service
 * @author zhangyao
 * @version 2016-05-20
 */
@Service(version="1.0.0")
@Transactional(readOnly = true)
public class ClinicAppointsServiceImpl extends CrudImplService<ClinicAppointsDao, ClinicAppoints> implements ClinicAppointsServiceApi {

}