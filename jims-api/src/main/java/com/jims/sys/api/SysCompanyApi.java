package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.SysCompany;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public interface SysCompanyApi {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public SysCompany get(String id);

    /**
     * 查询字典类型列表
     *
     * @return
     */
    public Page<SysCompany> findPage(Page<SysCompany> page, SysCompany sysCompany);

    /**
     * 创建组织机构
     *
     * @param sysCompany
     * @return
     */
    public int createCompany(SysCompany sysCompany);

    public List<SysCompany> findAllList();

    public List<SysCompany> findListByName();
}
