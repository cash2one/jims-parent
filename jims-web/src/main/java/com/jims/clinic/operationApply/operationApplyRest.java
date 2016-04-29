package com.jims.clinic.operationApply;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.OperatioinSerivceApi;
import com.jims.clinic.entity.Operatioin;
import com.jims.clinic.entity.OperationGrade;
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
        return null;
    }

    /**
     * 根据id查询手术申请表信息
     */
    @Path("getOperation")
    @POST
    public Operatioin getOperation(String id) {
        Operatioin operatioin= operatioinServiceApi.get(id);
        return operatioin;
    }/**
     * 保存申请记录
     */
    @Path("save")
    @POST
    public StringData save(Operatioin operatioin) {
        OperationGrade operationGrade=new OperationGrade();
        operationGrade.setOperatioin(operatioin);
        List<OperationGrade> list=new ArrayList<OperationGrade>();
        list.add(operationGrade);
        operatioin.setList(list);
        StringData data = new StringData();
        String num = data.getCode();
        if (operatioin != null) {
            num = operatioinServiceApi.saveOperatioin(operatioin);
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
        String num=operatioinServiceApi.delete(ids);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
