/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.BloodCapacityServiceApi;
import com.jims.clinic.dao.BloodCapacityDao;
import com.jims.clinic.entity.BloodCapacity;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 申请用血量Service
 * @author qlx
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class BloodCapacityServiceImpl extends CrudImplService<BloodCapacityDao, BloodCapacity> implements BloodCapacityServiceApi {

}