/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.dao.DictDao;
import com.jims.sys.entity.Dict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典Service
 * @author zhangyao
 * @version 2014-05-18
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DictServiceApiImpl extends CrudImplService<DictDao, Dict> implements DictServiceApi {
	
	/**
     * 查询字段类型列表
     * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

    public List<String> findListType(String dict){
        return dao.findListType(dict);
    }

    @Override
    public List<Dict> findList(String type) {
        Dict d = new Dict();
        d.setType(type);
        return dao.findList(d);
    }
}
