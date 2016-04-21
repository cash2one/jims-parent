/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典Service
 * @author zhangyao
 * @version 2016-04-18
 */

public interface DictService {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public Dict get(String id);

    /**
     * 查询字典类型列表
     * @return
     */
    public Page<Dict> findPage(Page<Dict> page, Dict dict);



    /**
     * 查询html类型列表
     * @return
     */
	public List<String> findTypeList();

    /**
     * 保存修改方法
     * @param dict
     */
	public String save(Dict dict);

    /**
     * 删除方法
     * @param ids
     */
	public String delete(String ids);


}
