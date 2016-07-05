package com.jims.clinic.PreDischgedPats;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.PreDischgedPatsServiceApi;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.entity.DeptDict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by qinlongxin on 2016/6/2.
 */
@Component
@Produces("application/json")
@Path("preDischgedPats")
public class PreDischgedPatsRest {
    @Reference(version = "1.0.0")
    private PreDischgedPatsServiceApi preDischgedPatsServiceApi;
    @Reference(version = "1.0.0")
    private DeptDictApi deptDictApi;
    /**
     * 异步加载表格根据科室查询该科室下的在院患者
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response,@QueryParam("wardCode") String wardCode){
        Page<PreDischgedPatsVo> page = preDischgedPatsServiceApi.findPreList(new Page<PreDischgedPatsVo>(request, response),"15006135",wardCode);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 加载科室信息
     * @param
     * @param
     * @return
     */
    @Path("getDeptName")
    @POST
    public List<DeptDict> getDeptName() {
        DeptDict deptDict = new DeptDict();
        deptDict.setDeptName("病区");
        List<DeptDict> list = deptDictApi.findParent();
        return list;
    }
    /**
     * 加载待出院的患者(显示动态行数据)
     * @param
     * @param
     * @return
     */
    @Path("findPreDischList")
    @POST
    public List<PreDischgedPatsVo> findPreDischList(@QueryParam("wardCode") String wardCode) {
        List<PreDischgedPatsVo> list = preDischgedPatsServiceApi.findPreDischList(wardCode);
        return list;
    }

    /**
     * 保存医嘱记录和出院通知单
     */
    @Path("save")
    @POST
    public StringData save(List<PreDischgedPatsVo> list) {
        StringData data = new StringData();
        String strData ="";
        String num = data.getCode();
        if (list != null) {
            num = preDischgedPatsServiceApi.savePreDischPat(list);
            if(num!=""&& !"0".equals(num)){
                strData = "success";
            }else{
                strData = "error";
            }
        }
        data.setCode(num);
        data.setData(strData);
        return data;
    }
    /**
     * 点击出院按钮是需要保存出院通知单
     */
    @Path("savePatsVo")
    @POST
    public StringData savePatsVo(@QueryParam("paitentId") String paitentId) {
        StringData data = new StringData();
        String num = data.getCode();
        PreDischgedPats preDischgedPats=new PreDischgedPats();
        preDischgedPats.setPatientId(paitentId);
            num = preDischgedPatsServiceApi.savePats(preDischgedPats);
        data.setCode(num);
        data.setData("success");
        return data;
    }
    /**
     * 删除医嘱记录和出院通知单
     */
    @Path("delete")
    @POST
    public StringData delete(@QueryParam("hospitalId")String hospitalId) {
        StringData data = new StringData();
        String num = data.getCode();
        if (StringUtils.isNotBlank(hospitalId)) {
            num = preDischgedPatsServiceApi.delPats(hospitalId);
        }
        data.setCode(num);
        data.setData("success");
        return data;
    }
}
