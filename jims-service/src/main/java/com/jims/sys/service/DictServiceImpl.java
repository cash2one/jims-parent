/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import java.util.List;

import com.jims.common.service.impl.CrudImplService;
import com.jims.demo.dao.SayHelloDao;
import com.jims.demo.entity.DemoUser;
import com.jims.sys.api.DictService;
import com.jims.sys.dao.DictDao;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 字典Service
 * @author zhangyao
 * @version 2014-05-18
 */
@Service
@Transactional(readOnly = true)
public class DictServiceImpl extends CrudImplService<DictDao, Dict> implements DictService {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
	}

}
