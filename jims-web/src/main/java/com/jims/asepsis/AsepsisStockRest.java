package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.api.AsepsisStockApi;
import com.jims.asepsis.vo.AsepsisStockVo;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 包库存初始化rest
* @author yangruidong
* @version 2016-06-27
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisStock")
public class AsepsisStockRest {

    @Reference(version = "1.0.0")
    private AsepsisStockApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<AsepsisStock> findList(@QueryParam("orgId")String orgId,
                                       @QueryParam("fromDept")String fromDept,
                                       @QueryParam("antiDateStart")Date antiDateStart,
                                       @QueryParam("antiDateEnd")Date antiDateEnd,
                                       @QueryParam("itemCode")String itemCode,
                                       @QueryParam("itemName")String itemName) {
        AsepsisStock entity = new AsepsisStock();
        entity.setOrgId(orgId);
        entity.setFromDept(fromDept);
        entity.setItemCode(itemCode);
        entity.setItemName(itemName);
        entity.setAntiDateStart(antiDateStart);
        entity.setAntiDateEnd(antiDateEnd);
        return api.findList(entity);
    }

    /**
     * 检索有库存的
     * @param
     * @return
     */
    @GET
    @Path("findListHasStock")
    public List<AsepsisStock> findListHasStock(@QueryParam("orgId")String orgId,
                                               @QueryParam("fromDept")String fromDept){
        AsepsisStock entity = new AsepsisStock();
        entity.setOrgId(orgId);
        entity.setFromDept(fromDept);
        return api.findListHasStock(entity);
    }
    /**
     * 检索
     * @param orgId
     * @return
     */
    @GET
    @Path("findListOver")
    public List<AsepsisStock> findListOver(@QueryParam("orgId")String orgId,
                                       @QueryParam("fromDept")String fromDept,
                                       @QueryParam("antiDateStart")Date antiDateStart,
                                       @QueryParam("antiDateEnd")Date antiDateEnd,
                                       @QueryParam("itemCode")String itemCode,
                                       @QueryParam("itemName")String itemName) {
        AsepsisStock entity = new AsepsisStock();
        entity.setOrgId(orgId);
        entity.setFromDept(fromDept);
        entity.setItemCode(itemCode);
        entity.setItemName(itemName);
        entity.setAntiDateStart(antiDateStart);
        entity.setAntiDateEnd(antiDateEnd);
        return api.findListOver(entity);
    }
    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(AsepsisStock entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<AsepsisStock> list) {
        return api.save(list);
    }

    /**
     * 保存  增删改
     *
     * @param asepsisStockVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(AsepsisStockVo<AsepsisStock> asepsisStockVo) {
        List<AsepsisStock> newUpdateDict = new ArrayList<AsepsisStock>();
        newUpdateDict = api.saveAll(asepsisStockVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }
}