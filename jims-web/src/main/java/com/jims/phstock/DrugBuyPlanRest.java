package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugBuyPlanApi;
import com.jims.phstock.entity.DrugBuyPlan;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * 药品采购计划服务
 * @author lgx
 * @version 2016-05-11
 */
@Produces("application/json")
@Path("drug-buy-plan")
public class DrugBuyPlanRest {

    @Reference(version ="1.0.0")
    private DrugBuyPlanApi drugBuyPlanApi ;

    /**
     * 根据购买单号、所属机构以及执行标志检索
     * @param buyId
     * @param orgId
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

    public StringData save(List<DrugBuyPlan> entityBatch){
        StringData result = new StringData();

        return result;
    }
}
