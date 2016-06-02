package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.bo.OrgRoleBo;
import com.jims.sys.entity.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class OrgRoleImpl implements OrgRoleApi {

    @Autowired
    private OrgRoleBo orgRoleBo;

    /**
     * 根据orgId获取所有的角色
     * @return
     */
    @Override
    public List<OrgRole> findAllList(String orgId) {
        return orgRoleBo.findAllList(orgId);
    }


    /**
     * 保存或修改
     * @param orgRole
     * @return
     */
    @Override
    public String save(OrgRole orgRole) {
        return null;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public String delete(String ids) {
        return null;
    }
}
