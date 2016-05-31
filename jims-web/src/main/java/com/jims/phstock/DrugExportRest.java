package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugExportServiceApi;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 药品出库Rest
 * @author lgx
 * @version 2016/5/23
 */
@Component
@Produces("application/json")
@Path("drug-out")
public class DrugExportRest {

    @Reference(version = "1.0.0")
    private DrugExportServiceApi api;

    /**
     * 保存药品出库主表、关联的明细表，
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     * @return 0 失败，1成功
     */
    @POST
    @Path("save")
    public String save(DrugExportMaster master){
        return api.saveMasterAndDetail(master);
    }
}
