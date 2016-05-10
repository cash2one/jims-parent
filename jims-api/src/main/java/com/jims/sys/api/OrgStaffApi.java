package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.*;
import com.jims.sys.vo.OrgStaffVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
public interface OrgStaffApi {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public OrgStaff get(String id);
    /**
     * 查询组织机构人员列表
     *
     * @return
     */
    public Page<OrgStaff> findPage(Page<OrgStaff> page, OrgStaff orgStaff);

    /**
     * 查询组织机构vo人员列表
     * @param page
     * @param orgStaffVo
     * @return
     */
    public Page<OrgStaffVo> findPageByVo(Page<OrgStaffVo> page, OrgStaffVo orgStaffVo);


    /**
     * 保存或修改
     * @param orgStaff
     * @return
     */
    public  String save(OrgStaff orgStaff);



    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 通过persion_id删除三张表的信息
     *
     * @param ids
     * @return
     */
    public String deleteByAll(String ids);

    /**
     * 同时将sys_user ,  persion_info , org_staff 三张表保存
     * @param persionInfo
     * @param sysUser
     * @param orgStaff
     * @return
     */
    public String insertOrgStaffAndPersion(PersionInfo persionInfo,SysUser sysUser,OrgStaff orgStaff);

    /**
     * 通过persionId查询密码，并用于回显
     * @param persionId
     * @return
     * @author  yangruidong
     */
    public SysUser findPasswordByPersionId(String persionId);

    /**
     * 通过persionId查询title职称  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    public OrgStaff findTitleByPersionId(String persionId);

}
