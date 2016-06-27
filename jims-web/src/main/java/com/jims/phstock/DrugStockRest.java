package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugStockServiceApi;
import com.jims.phstock.entity.DrugStock;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by heren on 2016/5/16.
 */
@Produces("application/json")
@Path("drug-stock")
@Component
public class DrugStockRest {

    @Reference(version ="1.0.0")
    private DrugStockServiceApi drugStockServiceApi ;

    @Path("findListHasStock")
    @GET
    public List<DrugStock> findListHasStock(@QueryParam("orgId")String orgId,
                                   @QueryParam("supplyIndicator")Integer supplyIndicator,
                                   @QueryParam("start")Integer start,
                                   @QueryParam("limit")Integer limit,
                                   @QueryParam("q")String q) {
        DrugStock drugStock = new DrugStock();
        drugStock.setOrgId(orgId);
        drugStock.setSupplyIndicator(supplyIndicator);
        drugStock.setQ(q);
        List<DrugStock> list = drugStockServiceApi.findListHasStock(drugStock);
        start = start == null || start < 0 ? 0 : start;
        limit = limit == null || limit < 0 ? list.size() : (start + limit < list.size() ? limit : list.size()-start);
        return list.subList(start,limit);
    }
}
