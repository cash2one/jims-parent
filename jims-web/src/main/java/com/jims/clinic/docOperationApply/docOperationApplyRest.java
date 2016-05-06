package com.jims.clinic.docOperationApply;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.DocOperationApplyServiceApi;
import com.jims.clinic.entity.DocBloodApply;
import com.jims.clinic.entity.DocOperationApply;
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

/**
 * Created by qinlongxin on 2016/5/6.
 */
@Component
@Produces("application/json")
@Path("docOperationApply")
public class docOperationApplyRest {
    @Reference(version = "1.0.0")
    private DocOperationApplyServiceApi docOperationApplyServiceApi ;
    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<DocOperationApply> page = docOperationApplyServiceApi.findPage(new Page<DocOperationApply>(request,response), new DocOperationApply());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 根据id查询手术申请表信息
     */
    @Path("getOperation")
    @POST
    public DocOperationApply getOperation(String id) {
        DocOperationApply docOperationApply= docOperationApplyServiceApi.get(id);
        return docOperationApply;
    }
    /**
     * 保存申请记录
     */
    @Path("save")
    @POST
    public StringData save(DocOperationApply docOperationApply) {
        StringData data = new StringData();
        String num = data.getCode();
        if (docOperationApply != null) {
            num = docOperationApplyServiceApi.saveDocOperationApply(docOperationApply);
        }
        data.setCode(num);
        data.setData("success");
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
        String num=docOperationApplyServiceApi.delete(ids);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
