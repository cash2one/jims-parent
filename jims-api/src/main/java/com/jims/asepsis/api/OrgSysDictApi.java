package com.jims.asepsis.api;

import com.jims.asepsis.entity.OrgSysDict;
import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgDeptPropertyDict;

import java.util.List;

/**
* 包单位维护Api
* @author yangruidong
* @version 2016-06-28
*/
public interface OrgSysDictApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public OrgSysDict get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<OrgSysDict> findList(OrgSysDict entity);

    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict>  findUnits(OrgSysDict entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<OrgSysDict> findPage(Page<OrgSysDict> page, OrgSysDict entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(OrgSysDict entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<OrgSysDict> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}