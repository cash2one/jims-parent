/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.blood.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.blood.api.BloodComponentServiceApi;
import com.jims.blood.dao.BloodComponentDao;
import com.jims.blood.entity.BloodComponent;
import com.jims.common.service.impl.CrudImplService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 血液成分字典表Service
 * @author zhangpeng
 * @version 2016-05-13
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class BloodComponentServiceImpl extends CrudImplService<BloodComponentDao, BloodComponent> implements BloodComponentServiceApi{

	/**
	 * 获得血液成分列表
	 * @return
	 */
	@Autowired
	private BloodComponentDao bloodComponentDao;
	@Override
	public List<BloodComponent> getBloodComponent() {
		return bloodComponentDao.getBloodComponent();
	}
}