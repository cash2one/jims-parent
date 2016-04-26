package com.jims.clinic.operationApply;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.OperatioinSerivceApi;
import com.jims.clinic.entity.*;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinlongxin on 2016/4/25.
 */
@Component
@Produces("application/json")
@Path("operationApply")
public class operationApplyRest {
    @Reference(version = "1.0.0")
    private OperatioinSerivceApi operatioinServiceApi ;
    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<Operatioin> page = operatioinServiceApi.findPage(new Page<Operatioin>(request,response), new Operatioin());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=operatioinServiceApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 保存每日病程
     */
    @Path("save")
    @POST
    public StringData save(Operatioin operatioin) {
        StringData data = new StringData();
        String num = data.getCode();
        OperationGrade operationGrade=new OperationGrade();
        operationGrade.setOperatioin(operatioin);
        List<OperationGrade> list=new ArrayList<OperationGrade>();
        list.add(operationGrade);
        operatioin.setList(list);
        num=operatioinServiceApi.saveOperatioin(operatioin);
        data.setCode("1");
        data.setData("success");
        return data;
    }
}
