package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.CookieUtils;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.LoginVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
@Component
@Produces("application/json")
@Path("login")
public class LoginRest {

    @Reference(version = "1.0.0")
    private SysUserApi sysUserApi;

    SysUser user = null;

    @POST
    @Path("list")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData login(LoginVo loginVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {

        SysUser sysUser = new SysUser();
        sysUser.setLoginName(loginVo.getLoginName());
        sysUser.setPassword(loginVo.getPassword());
        sysUser.setLastLoginTime(new Date());
            //判断用户名和密码是否不为空
            if (StringUtils.isNotBlank(sysUser.getLoginName()) && StringUtils.isNotBlank(sysUser.getPassword())) {

                //与数据库中的用户名进行比对，查看是否正确，并响应给用户
                SysUser loginName = sysUserApi.selectLoginName(sysUser);
                if (loginName == null) {

                    StringData stringData = new StringData();
                    stringData.setData("nameNull");
                    return stringData;
                }
                //与数据库中的密码进行比对，查看是否正确，并响应给用户
                SysUser password = sysUserApi.selectPassword(sysUser);
                if (StringUtils.equalsIgnoreCase(password.getPassword(), sysUser.getPassword())) {
                    SysUser user= sysUserApi.login(sysUser);
                    if(user!=null)
                    {
                        StringData stringData = new StringData();
                        stringData.setData("success");
                        return stringData;
                    }
                } else {
                    //判断密码是否正确   向页面返回信息
                    StringData stringData = new StringData();
                    stringData.setData("passNull");
                    return stringData;
                }
            }
        return null;
    }

    @POST
    @Path("findNameByOwner")
    public StringData findNameByOwner(String loginName) {
        SysCompany sysCompany=sysUserApi.findNameByOwner(loginName);
        StringData stringData=new StringData();
        stringData.setData(sysCompany.getOrgName());
        return stringData;
    }

}

