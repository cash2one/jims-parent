package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.JedisUtils;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.bo.PersionServiceListBo;
import com.jims.sys.dao.SysCompanyDao;
import com.jims.sys.dao.SysUserDao;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.io.Serializable;


/**
 * Created by Administrator on 2016/4/14 0014.
 */
@Service(version = "1.0.0")
public class SysUserImpl extends CrudImplService<SysUserDao, SysUser> implements SysUserApi, Serializable {

    @Autowired
    private SysCompanyDao sysCompanyDao;
    @Autowired
    private PersionServiceListBo persionServiceListBo;

    /**
     * 用户登录
     *
     * @param sysUser
     * @return
     */
    public SysUser login(SysUser sysUser) {
        SysUser user = dao.login(sysUser);
        if(user!=null && !user.getLoginName().equals("admin"))
        {
            persionServiceListBo.save(user.getPersionId());
        }
        return user;
    }



    public SysCompany findNameByOwner(String loginName) {
        SysCompany sysCompany = sysCompanyDao.findNameByOwner(loginName);
        return sysCompany;
    }


    /**
     * 与数据库中的用户名比对，是否正确
     *
     * @param loginName
     * @return
     */
    @Override
    public SysUser selectLoginName(String loginName) {
        if (StringUtils.isNotBlank(loginName)){
            SysUser user = dao.selectLoginName(loginName);
            return user;
        }
        return null;
    }

    /**
     * 与数据库中的密码比对，是否正确
     *
     * @param loginName
     * @return
     */
    @Override
    public SysUser selectPassword(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            SysUser user = dao.selectPasswrod(loginName);
            return user;
        }
        return null;
    }
}
