package com.jims.sys.api;

import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public interface SysUserApi {

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    public String login(SysUser sysUser);

    /**
     * 根据ticket查询用户信息
     * @param ticket
     * @return
     */
    public SysUser queryUserByTicket(String ticket);

    /**
     * 查询登录名是否正确
     *
     * @param sysUser
     * @return
     */
    public SysUser selectLoginName(SysUser sysUser);

    /**
     * 查询登录密码是否正确
     *
     * @param sysUser
     * @return
     */
    public SysUser selectPassword(SysUser sysUser);

    public SysCompany findNameByOwner(String loginName);


}
