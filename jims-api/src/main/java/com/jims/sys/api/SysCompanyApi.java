package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.SysCompany;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public interface SysCompanyApi {

    /**
     * 根据申请状态查询组织机构列表
     * @param applyStatus 申请状态
     * @return 组织机构list集合
     * @author fengyuguang
     */
    public List<SysCompany> findListByApplyStatus(String applyStatus);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public SysCompany get(String id);

    public int update(SysCompany sysCompany);

    /**
     * 查询组织机构列表
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
    public String save(SysCompany sysCompany);

    public String insertReturnId(SysCompany sysCompany);

    /**
     * 查询父机构
     * @return
     */
    public List<SysCompany> findListByName();

    /**
     * 查询组织机构名称是否存在
     * @param orgName
     * @return
     */
    public SysCompany findByName(String orgName);

    /**
     * 根据创建人查询组织机构的名称
     * @return
     */
   // public SysCompany findNameByCreateBy();

    /**
     * 保存注册信息以及选择的服务
     * @param company
     * @return 1 成功 ,0 失败
     */
    public String saveCompanyAndService(SysCompany company);

}
