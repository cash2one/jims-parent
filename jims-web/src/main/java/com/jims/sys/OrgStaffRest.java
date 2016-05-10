package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.OrgStaffVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;


/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("orgStaff")
public class OrgStaffRest {
    @Reference(version = "1.0.0")
    private OrgStaffApi orgStaffApi;

    @Reference(version = "1.0.0")
    private PersionInfoApi persionInfoApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String orgId = request.getParameter("orgId");
        String deptId = request.getParameter("deptId");
        OrgStaffVo orgStaffVo = new OrgStaffVo();

        orgStaffVo.setOrgId(orgId);
        orgStaffVo.setDeptId(deptId);

        Page<OrgStaffVo> page = orgStaffApi.findPageByVo(new Page<OrgStaffVo>(request, response), orgStaffVo);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }


    /**
     * 根据身份证号查询相关的信息
     *
     * @param cardNo
     * @return
     */
    @Path("findInfoByCardNo")
    @GET
    public PersionInfo findInfoByCardNo(@QueryParam("cardNo") String cardNo) {
        PersionInfo persionInfo = persionInfoApi.findInfoByCardNo(cardNo);
        return persionInfo;
    }

    /**
     * 通过persionId查询密码  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Path("findPasswordByPersionId")
    @GET
    public SysUser findPasswordByPersionId(@QueryParam("persionId") String persionId)
    {
        return  orgStaffApi.findPasswordByPersionId(persionId);
    }

    /**
     * 通过persionId查询密码  ，用于回显数据
     *
     * @param persionId
     * @return
     * @author yangruidong
     */
    @Path("findTitleByPersionId")
    @GET
    public OrgStaff findTitleByPersionId(@QueryParam("persionId") String persionId) {
        return orgStaffApi.findTitleByPersionId(persionId);
    }


    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public OrgStaff get(String id) {
        OrgStaff orgStaff = orgStaffApi.get(id);
        return orgStaff;
    }

    /**
     * 保存修改方法
     *
     * @param orgStaffVo
     * @return
     */
    @Path("save")
    @POST
    public StringData save(OrgStaffVo orgStaffVo) {

        PersionInfo persionInfo = new PersionInfo();
        SysUser sysUser = new SysUser();
        sysUser.setPassword(orgStaffVo.getPassword());
        OrgStaff orgStaff = new OrgStaff();

        persionInfo.setPhoneNum(orgStaffVo.getPhoneNum());
        persionInfo.setNickName(orgStaffVo.getNickName());
        persionInfo.setEmail(orgStaffVo.getEmail());
        persionInfo.setCardNo(orgStaffVo.getCardNo());
        persionInfo.setName(orgStaffVo.getName());
        persionInfo.setNation(orgStaffVo.getNation());
        persionInfo.setSex(orgStaffVo.getSex());
        persionInfo.setId(orgStaffVo.getId());



        orgStaff.setDeptId(orgStaffVo.getDeptId());
        orgStaff.setOrgId(orgStaffVo.getOrgId());
        orgStaff.setTitle(orgStaffVo.getTitle());
        orgStaff.setPersionId(orgStaffVo.getId());

       /* if(StringUtils.isNotEmpty(orgStaffVo.getPassword()))
        {
            sysUser.setPassword(orgStaffVo.getPassword());
        }
        else {

        }*/

        String num = orgStaffApi.insertOrgStaffAndPersion(persionInfo, sysUser, orgStaff);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }


        return null;

    }


    /**
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = orgStaffApi.deleteByAll(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null) {
            stringData.setData("success");
        }
        return stringData;
    }

}
