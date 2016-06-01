package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.OrgStaffVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31
 */
public interface OrgRoleApi {


    /**
     * 根据orgId获取所有的角色
     * @return
     */
    public List<OrgRole> findAllList(String orgId);

    /**
     * 保存或修改
     * @param orgRole
     * @return
     */
    public  String save(OrgRole orgRole);



    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public String delete(String ids);

}
