package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 药品入库Rest
 * @author lgx
 * @version 2016/5/18
 */
@Component
@Produces("application/json")
@Path("drug-in")
public class DrugImportRest {

    @Reference(version = "1.0.0")
    private DrugImportServiceApi api;

    /**
     * 保存药品入库单主单和明细
     * @param master 主表内含有明细表List序列
     * @return 0 失败，1成功
     */
    @POST
    @Path("save")
    public String save(DrugImportMaster master){
        return api.saveMasterAndDetail(master);
    }
}
