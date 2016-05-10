package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysUser;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
@MyBatisDao
public interface SysUserDao extends CrudDao<SysUser> {

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    public SysUser login(SysUser sysUser);

    /**
     * 查询登录名是否正确
     * @param sysUser
     * @return
     */

    public SysUser selectLoginName(SysUser sysUser);

    /**
     * 查询登录密码是否正确
     * @param sysUser
     * @return
     */
    public SysUser selectPasswrod(SysUser sysUser);

    /**
     * 根据名称修改用户信息
     * @param sysUser
     * @return
     */
    public int updateById(SysUser sysUser);

    /**
     * 查询用户的信息
     * @param loginName
     * @param persionId
     * @return
     */
    public SysUser findByLoginName(String loginName,String persionId);

    /**
     * 通过persionId 删除
     *
     * @param id
     * @return
     */
    public Integer deleteByPid(String id);

    /**
     * 通过persionId查询密码，用于回显页面
     * @param persionId
     * @return
     */
    public SysUser findPasswordByPersionId(String persionId);
}
