package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.SysCompany;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public interface DeptPropertyDictApi {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public OrgDeptPropertyDict get(String id);

    /**
     * 查询类型列表
     *
     * @return
     */
    public Page<OrgDeptPropertyDict> findPage(Page<OrgDeptPropertyDict> page, OrgDeptPropertyDict orgDeptPropertyDict);


    /**
     * 保存修改方法
     *
     * @param
     */
    public int add(OrgDeptPropertyDict orgDeptPropertyDict);

    /**
     * 删除方法
     *
     * @param ids
     */
    public String delete(String ids);


    public List<OrgDeptPropertyDict> findNameByType();

    public List<OrgDeptPropertyDict> findProperty();


}
