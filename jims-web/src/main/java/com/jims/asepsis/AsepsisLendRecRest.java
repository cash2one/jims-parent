package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.asepsis.api.AsepsisLendRecApi;
import com.jims.asepsis.vo.AsepsisVo;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 借物还物rest
* @author lgx
* @version 2016-06-27
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisLendRec")
public class AsepsisLendRecRest {

    @Reference(version = "1.0.0")
    private AsepsisLendRecApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<AsepsisLendRec> findList(@QueryParam("orgId")String orgId,
                                         @QueryParam("lendDate")Date lendDate,
                                         @QueryParam("lendDateStart")Date lendDateStart,
                                         @QueryParam("lendDateEnd")Date lendDateEnd,
                                         @QueryParam("toDept")String toDept,
                                         @QueryParam("returnFlag")String returnFlag,
                                         @QueryParam("lender")String lender,
                                         @QueryParam("itemName")String itemName,
                                         @QueryParam("documentNo")String documentNo) {
        AsepsisLendRec entity = new AsepsisLendRec();
        entity.setOrgId(orgId);
        entity.setLendDate(lendDate);
        entity.setLendDateStart(lendDateStart);
        entity.setLendDateEnd(lendDateEnd);
        entity.setToDept(toDept);
        entity.setLender(lender);
        entity.setReturnFlag(returnFlag);
        entity.setDocumentNo(documentNo);
        entity.setItemName(itemName);
        return api.findList(entity);
    }

    /**
     * 检索有库存的
     * @param
     * @return
     */
    @GET
    @Path("findListWithStock")
    public List<AsepsisLendRec> findListWithStock(@QueryParam("orgId")String orgId,
                                                  @QueryParam("lendDateStart")Date lendDateStart,
                                                  @QueryParam("lendDateEnd")Date lendDateEnd,
                                                  @QueryParam("toDept")String toDept){
        AsepsisLendRec entity = new AsepsisLendRec();
        entity.setOrgId(orgId);
        entity.setLendDateStart(lendDateStart);
        entity.setLendDateEnd(lendDateEnd);
        entity.setToDept(toDept);
        return api.findListWithStock(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(AsepsisLendRec entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list   当AsepsisLendRec的id为空时，stock有值则为
     *              当id不为空时，
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<AsepsisLendRec> list) {
        return api.save(list);
    }



    /*
    * 保存  增删改
    *
    * @param asepsisDictVo
    * @return
    * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(AsepsisVo<AsepsisLendRec> asepsisVo) {
        List<AsepsisLendRec> newUpdateDict = new ArrayList<AsepsisLendRec>();
        newUpdateDict = api.saveAll(asepsisVo);
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
    public String delete(@QueryParam("ids")String ids) {
        return api.delete(ids);
    }

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    @GET
    @Path("getMaxDocumentNo")
    public String getMaxDocumentNo(@QueryParam("orgId")String orgId){
        return api.getMaxDocumentNo(orgId);
    }
}