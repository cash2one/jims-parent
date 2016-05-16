package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugStockServiceApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by heren on 2016/5/16.
 */
@Produces("application/json")
@Path("drug-stock")
@Component
public class DrugStockRest {

    @Reference(version ="1.0.0")
    private DrugStockServiceApi drugStockServiceApi ;


}
