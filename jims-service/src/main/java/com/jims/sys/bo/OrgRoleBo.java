/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.data.StringData;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.OrgRoleDao;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查预约记录BAO层
 *
 * @author zhangyao
 * @version 2016-04-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrgRoleBo extends CrudImplService<OrgRoleDao, OrgRole> {

    @Autowired
    private OrgRoleDao orgRoleDao;

    /**
     * 根据orgId获取所有的角色
     * @return
     */
    public List<OrgRole> findAllList(String orgId) {
        return orgRoleDao.findAllList(orgId);
    }

    /**
     * 根据角色名称模糊查询角色信息
     * @param roleName 角色名称
     * @param orgId  组织机构ID
     * @return 角色信息列表
     * @author fengyuguang
     */
    public List<OrgRole> findByRoleName(String roleName,String orgId){
        return orgRoleDao.findByRoleName(roleName,orgId);
    }

    /**
     * 保存角色增删改
     * @param beanChangeVo 角色增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgRole> beanChangeVo) {
        List<OrgRole> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (OrgRole orgRole : insertedList) {
            orgRole.preInsert();
            inNum = orgRoleDao.insert(orgRole);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<OrgRole> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (OrgRole orgRole : updatedList) {
            updNum = orgRoleDao.update(orgRole);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<OrgRole> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (OrgRole orgRole : deletedList) {
            dltNum = orgRoleDao.delete(orgRole);
            dltNum++;
        }
        String deletedNum = dltNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deletedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }
}