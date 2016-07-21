package com.jims.clinic.notice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.hospitalNotice.api.PatHospitalNoticeServiceApi;
import com.jims.hospitalNotice.entity.PatHospitalNotice;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.Date;

/**住院通知单
 * Created by qinlongxin on 2016/6/1.
 */
@Component
@Produces("application/json")
@Path("patHospitalNotice")
public class PatHospitalNoticeRest {
    @Reference(version = "1.0.0")
    private PatHospitalNoticeServiceApi patHospitalNoticeServiceApi ;
    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<PatHospitalNotice> page = patHospitalNoticeServiceApi.findPage(new Page<PatHospitalNotice>(request,response), new PatHospitalNotice());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 保存申请记录
     */
    @Path("save")
    @POST
    public StringData save(PatHospitalNotice patHospitalNotice,@Context HttpServletRequest request) {
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        StringData data = new StringData();
        String num = data.getCode();

        if (patHospitalNotice != null) {
            patHospitalNotice.setOrgId(loginInfo.getOrgId());
            patHospitalNotice.setOperator(loginInfo.getPersionId());
            patHospitalNotice.setEnterDate(new Date());
            num = patHospitalNoticeServiceApi.save(patHospitalNotice);
        }
        data.setCode(num);
        if(!"".equals(num)&& !"0".equals(num)&&num!=null){
            data.setData("success");
        }else{
            data.setData("error");
        }

        return data;
    }
    /**
     *删除手术申请
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        StringData stringData=new StringData();
        String num=patHospitalNoticeServiceApi.delete(ids);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 根据id查询手术申请表信息
     */
    @Path("get")
    @POST
    public PatHospitalNotice get(String clinicId) {
        PatHospitalNotice patHospitalNotice= patHospitalNoticeServiceApi.getNotice(clinicId);
        return patHospitalNotice;
    }
}
