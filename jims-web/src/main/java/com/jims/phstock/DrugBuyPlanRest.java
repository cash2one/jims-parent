package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugBuyPlanApi;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 药品采购计划服务
 * @author lgx
 * @version 2016-05-11
 */
@Produces("application/json")
@Path("drugBuyPlan")
public class DrugBuyPlanRest {

    @Reference(version ="1.0.0")
    private DrugBuyPlanApi drugBuyPlanApi ;

}
