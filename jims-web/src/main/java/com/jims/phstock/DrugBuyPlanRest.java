package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugBuyPlanApi;
import com.jims.phstock.entity.DrugBuyPlan;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 药品采购计划服务
 * @author lgx
 * @version 2016-05-11
 */
@Component
@Produces("application/json")
@Path("drug-buy-plan")
public class DrugBuyPlanRest {

    @Reference(version ="1.0.0")
    private DrugBuyPlanApi drugBuyPlanApi ;

    /**
     * 根据购买单号、所属机构以及执行标志检索
     * @param buyId
     * @param orgId
     * @param flag 1仓管员暂存，2仓管员保存，3采购员保存，4审核员保存，5 已执行，6 已入库
     * @return
     */
    @GET
    @Path("findList")
    public List<DrugBuyPlan> findList(@QueryParam("buyId")String buyId,
                                                        @QueryParam("orgId")String orgId,
                                                        @QueryParam("flag")int flag){
        DrugBuyPlan planObj = new DrugBuyPlan();
        planObj.setBuyId(buyId);
        planObj.setOrgId(orgId);
        planObj.setFlag(flag);
        return drugBuyPlanApi.findList(planObj);
    }

    /**
     * 批量保存
     * @param entityBatch,list.get(0)添加的数据
     *                    list.get(1)删除的数据
     * @return 0 失败，1 成功
     */
    @POST
    @Path("saveBatch")
    public String saveBatch(List<List<DrugBuyPlan>> entityBatch){
        List<DrugBuyPlan> delObj = entityBatch.get(1);
        String ids = null;
        if(delObj != null && delObj.size() > 0)
            ids = delObj.get(0).getId();
        return drugBuyPlanApi.saveAndDelete(entityBatch.get(0),ids);
    }

    /**
     * 获取当前日期的下一个单据号
     * @return
     */
    @GET
    @Path("getNextBuyId")
    public String getNextBuyId(@QueryParam("orgId")String orgId){
        return drugBuyPlanApi.getNextBuyId(new Date(),orgId);
    }

    /**
     * 根据执行标志获取单据号
     * @param flag
     * @return String[0] 采购单据号，String[1] 执行状态
     */
    @GET
    @Path("getBuyId")
    public List<String[]> getBuyId(@QueryParam("flag")String flag
            ,@QueryParam("buyer")String buyer
            ,@QueryParam("orgId")String orgId
            ,@QueryParam("storage")String storage){
        return drugBuyPlanApi.getBuyId(flag,orgId,buyer,storage);
    }

    /**
     * 药品入库
     * @param plan
     * @return 0失败，1成功
     */
    @Path("drugInStock")
    @POST
    public String drugInStock(List<DrugBuyPlan> plan){
        return drugBuyPlanApi.drugInStock(plan);
    }

    /**
     * 获取当前机构和子机构的采购单据号
     * @param orgId,flag,
     * @return DrugBuyPlanList
     * zhuqi
     */
    @GET
    @Path("getBuyListByOrg")
    public List<DrugBuyPlan> getBuyListByOrg(@QueryParam("flag")String flag
            ,@QueryParam("orgId")String orgId){
        return drugBuyPlanApi.getBuyListByOrg(flag, orgId);
    }
}
