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
     * @return
     */
    @POST
    @Path("saveBatch")
    public StringData saveBatch(List<List<DrugBuyPlan>> entityBatch){
        StringData result = new StringData();
        List<String> resultList = new ArrayList<String>();
        List<DrugBuyPlan> delObj = entityBatch.get(1);
        String deleteResult = "0";
        if(delObj != null && delObj.size() > 0){
            String ids = delObj.get(0).getId();
            if(ids != null && ids.trim().length() > 0){
                deleteResult = drugBuyPlanApi.deleteInfo(ids);
            }
        }
        // 由于采购单据号、采购单序号唯一，所以必须先删除，才能保证保存的成功
        resultList.add(drugBuyPlanApi.save(entityBatch.get(0)));
        resultList.add(deleteResult);
        result.setDatas(resultList);
        result.setCode("0");
        return result;
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
     * @return
     */
    @GET
    @Path("getBuyId")
    public List<String> getBuyId(@QueryParam("flag")String flag
            ,@QueryParam("buyer")String buyer
            ,@QueryParam("orgId")String orgId){
        return drugBuyPlanApi.getBuyId(flag,orgId,buyer);
    }
}
