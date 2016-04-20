package com.jims.sys.api;

import com.jims.sys.entity.SysUser;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public interface SysUserApi  {

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

    public SysUser selectLoginName(SysUser sysUser);

    public SysUser selectPassword(SysUser sysUser);
}
