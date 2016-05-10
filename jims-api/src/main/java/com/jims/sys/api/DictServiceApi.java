/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;


/**
 * 字典Service
 * @author fengyuguang
 * @version 2016-04-18
 */

public interface DictServiceApi {

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
     * 查询字典表的类型和描述这两个字段
     * @return 字典表type和description字段的集合
     * @author fengyuguang
     */
    public List<Dict> leftList();

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     * @param type 字典表类型
     * @return 字典表list集合
     * @author fengyuguang
     */
    public List<Dict> rightList(String type);

    /**
     * 保存增删改多条数据
     * @param beanChangeVo 多条增删改数据的集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo);

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

    /**
     * 获取指定类型列表
     * @param type
     * @return
     */
    public List<String> findListType(String type);
}
