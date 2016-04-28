/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.jims.clinic.api.BloodApplyServiceApi;
import com.jims.clinic.dao.BloodApplyDao;
import com.jims.clinic.entity.BloodApply;
import com.jims.common.service.CrudService;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用血申请Service
 * @author qlx
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class BloodApplyService extends CrudImplService<BloodApplyDao,BloodApply> implements BloodApplyServiceApi{

}