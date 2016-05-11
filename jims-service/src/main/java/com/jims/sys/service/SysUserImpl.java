package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.JedisUtils;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;


/**
 * Created by Administrator on 2016/4/14 0014.
 */
@Service(version = "1.0.0")
@Transactional
public class SysUserImpl extends CrudImplService<SysUserDao, SysUser> implements SysUserApi, Serializable {


    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysCompanyDao sysCompanyDao;
    //private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 用户登录
     *
     * @param sysUser
     * @return
     */
    public String login(SysUser sysUser) {
        SysUser user = sysUserDao.login(sysUser);

        if (user != null) {
            try {
                String ticket = "TICKET_TOKEN_" + DigestUtils.md5Hex(user.getLoginName() + System.currentTimeMillis());
                //将用户信息保存在redis中
               // JedisUtils.set(ticket, MAPPER.writeValueAsString(user), 60 * 30);
                //    CookieUtils.setCookie(request,response,"TT_TICKET",ticket);
                return ticket;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public SysCompany findNameByOwner(String loginName) {
        SysCompany sysCompany = sysCompanyDao.findNameByOwner(loginName);
        return sysCompany;
    }

    /**
     * 根据ticket查询用户信息
     *
     * @param ticket
     * @return
     */
    public SysUser queryUserByTicket(String ticket) {
        SysUser user = null;
       /* try {
            String jsonData = JedisUtils.get(ticket);
            if (StringUtils.isNotBlank(jsonData)) {
                user = MAPPER.readValue(jsonData, SysUser.class);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }       */
        return user;

    }

    /**
     * 与数据库中的用户名比对，是否正确
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser selectLoginName(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getLoginName())) {
            SysUser user = sysUserDao.selectLoginName(sysUser);
            return user;
        }
        return null;
    }

    /**
     * 与数据库中的密码比对，是否正确
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser selectPassword(SysUser sysUser) {
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            SysUser user = sysUserDao.selectPasswrod(sysUser);
            return user;
        }
        return null;
    }
}
