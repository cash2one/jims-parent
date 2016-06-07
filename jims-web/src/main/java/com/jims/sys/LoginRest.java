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
import javax.ws.rs.*;
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

    @GET
    @Path("list")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData login(@QueryParam("loginName") String loginName,@QueryParam("password") String password) {

        //判断用户名和密码是否不为空
        if (StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(password)){

            //与数据库中的用户名进行比对，查看是否正确，并响应给用户
            SysUser name = sysUserApi.selectLoginName(loginName);
            if (name == null) {

                StringData stringData = new StringData();
                stringData.setData("nameNull");
                return stringData;
            }
            //与数据库中的密码进行比对，查看是否正确，并响应给用户
            SysUser pass = sysUserApi.selectPassword(loginName);
            if (StringUtils.equalsIgnoreCase(pass.getPassword(),password)) {
                SysUser sysUser=new SysUser();
                sysUser.setLoginName(loginName);
                sysUser.setPassword(password);
                SysUser user= sysUserApi.login(sysUser);
                if(user!=null)
                {
                    StringData stringData = new StringData();
                    stringData.setCode("success");
                    stringData.setData(user.getPersionId());
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

