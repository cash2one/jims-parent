package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugInventoryCheckApi;
import com.jims.phstock.vo.DrugInventoryCheckVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 盘点记录
 * @author txb
 * @version 2016-05-23
 */
@Component
@Produces("application/json")
@Path("drug-inventory-check")
public class DrugInventoryCheckRest {

    @Reference(version = "1.0.0")
    private DrugInventoryCheckApi drugInventoryCheckApi;


    /**
     * 生成盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    @Path("generateInventory")
    @GET
    public List<DrugInventoryCheckVo> generateInventory(@QueryParam("storage") String storage,@QueryParam("orgId")String orgId,
                                                        @QueryParam("checkYearMonth") String checkYearMonth,@QueryParam("subStorage")String subStorage){
        return drugInventoryCheckApi.generateInventory(storage,orgId,checkYearMonth,subStorage);
    }
 /**
     * 提取盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    @Path("extractInventory")
    @GET
    public List<DrugInventoryCheckVo> extractInventory(@QueryParam("storage") String storage, @QueryParam("orgId")String orgId,
                                                       @QueryParam("checkYearMonth") String checkYearMonth,@QueryParam("subStorage")String subStorage){
        return drugInventoryCheckApi.extractInventory(storage, orgId, checkYearMonth,subStorage);
    }
    /**
     * 暂存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    @Path("temporaryStorage")
    @POST
    public StringData  temporaryStorage(List<DrugInventoryCheckVo> drugInventoryCheckVos){
        String num = drugInventoryCheckApi.temporaryStorage(drugInventoryCheckVos);
        StringData stringData = new StringData();
        stringData.setData(num);
        stringData.setCode("success");
        return stringData;
    }
    /**
     * 保存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    @Path("saveInventory")
    @POST
    public StringData  saveInventory(List<DrugInventoryCheckVo> drugInventoryCheckVos){
        String num = drugInventoryCheckApi.saveInventory(drugInventoryCheckVos);
        StringData stringData = new StringData();
        stringData.setData(num);
        stringData.setCode("success");
        return stringData;
    }

}
