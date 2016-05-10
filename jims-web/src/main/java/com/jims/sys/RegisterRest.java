package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.RegisterVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
@Component
@Produces("application/json")
@Path("register")
public class RegisterRest {

    @Reference(version = "1.0.0")
    private PersionInfoApi persionInfoApi;

    /**
     * 用户注册
     *
     * @param registerVo
     * @return
     */
    @POST
    @Path("add-info")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response register(RegisterVo registerVo) {

        try {
            PersionInfo persionInfo = new PersionInfo();
            persionInfo.setName(registerVo.getName());
            persionInfo.setCardNo(registerVo.getCardNo());
            persionInfo.setPhoneNum(registerVo.getPhoneNum());
            persionInfo.setNickName(registerVo.getNickName());
            persionInfo.setEmail(registerVo.getEmail());
            persionInfo.setCreateDate(new Date());

            SysUser sysUser = new SysUser();
            sysUser.setPassword(registerVo.getPassword());
            String register = "1";
            PersionInfo persionInfo2 = new PersionInfo();
            if (StringUtils.isNotBlank(persionInfo.getName()) && StringUtils.isNotBlank(persionInfo.getCardNo()) && StringUtils.isNotBlank(persionInfo.getPhoneNum())
                    && StringUtils.isNotBlank(persionInfo.getNickName()) && StringUtils.isNotBlank(persionInfo.getEmail()) && StringUtils.isNotBlank(sysUser.getPassword())) {
                register = persionInfoApi.register(persionInfo, sysUser);
            }else
            {
                persionInfo2.setDelFlag("6");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            }

             /*else if (persionInfo.getName() == "") {
                persionInfo2.setDelFlag("1");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            } else if (persionInfo.getCardNo() == "") {
                persionInfo2.setDelFlag("2");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            } else if (persionInfo.getPhoneNum() == "") {
                persionInfo2.setDelFlag("3");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            } else if (persionInfo.getNickName() == "") {
                persionInfo2.setDelFlag("4");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            } else if (persionInfo.getEmail() == "") {
                persionInfo2.setDelFlag("5");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            } else if (sysUser.getPassword() == "") {
                persionInfo2.setDelFlag("6");
                return Response.status(Response.Status.OK).entity(persionInfo2).build();
            }*/


            //判断是否注册成功
            if (register == null) {
                PersionInfo persionInfo1 = new PersionInfo();
                //注册成功
                return Response.status(Response.Status.OK).entity(persionInfo1).build();
            }
        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
        }

        return null;
    }


    /**
     * 校验身份证号是否存在
     *
     * @param registerVo
     * @return
     */
    @POST
    @Path("getCard")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData selectCard(RegisterVo registerVo) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setCardNo(registerVo.getCardNo());
        PersionInfo persionInfo1 = persionInfoApi.getCard(persionInfo);
        if (persionInfo1 != null) {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;

    }

    /**
     * 检验用户名是否存在
     * @param registerVo
     * @return
     */

    @POST
    @Path("getNick")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData selectNick(RegisterVo registerVo) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setNickName(registerVo.getNickName());
        PersionInfo persionInfo1 = persionInfoApi.getNick(persionInfo);
        if (persionInfo1 != null) {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;

    }

    /**
     * 检验邮箱是否存在
     * @param registerVo
     * @return
     */
    @POST
    @Path("getEmail")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData selectEmail(RegisterVo registerVo) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setEmail(registerVo.getEmail());
        PersionInfo persionInfo1 = persionInfoApi.getEmail(persionInfo);
        if (persionInfo1 != null) {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 检验手机号是否存在
     * @param registerVo
     * @return
     */
    @POST
    @Path("getPhone")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData selectPhone(RegisterVo registerVo) {

        PersionInfo persionInfo = new PersionInfo();
        persionInfo.setPhoneNum(registerVo.getPhoneNum());
        PersionInfo persionInfo1 = persionInfoApi.getPhone(persionInfo);
        if(persionInfo1!=null)
        {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
           return null;
    }

}
