package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.api.AsepsisSendRecApi;
import com.jims.asepsis.vo.AsepsisVo;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 送物领物rest
* @author lgx
* @version 2016-06-27
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisSendRec")
public class AsepsisSendRecRest {

    @Reference(version = "1.0.0")
    private AsepsisSendRecApi api;

    /**
    * 检索
    * @param orgId
     * @param sendDate,
     * @param sendDateStart,
     * @param sendDateEnd,
     * @param fromDept,
     * @param getFlag,
     * @param sender
    * @return List<AsepsisSendRec>
    */
    @GET
    @Path("findList")
    public List<AsepsisSendRec> findList(@QueryParam("orgId")String orgId,
                                         @QueryParam("sendDate")Date sendDate,
                                         @QueryParam("sendDateStart")Date sendDateStart,
                                         @QueryParam("sendDateEnd")Date sendDateEnd,
                                         @QueryParam("fromDept")String fromDept,
                                         @QueryParam("getFlag")String getFlag,
                                         @QueryParam("sender")String sender) {
        AsepsisSendRec entity = new AsepsisSendRec();
        entity.setOrgId(orgId);
        entity.setFromDept(fromDept);
        entity.setSendDate(sendDate);
        entity.setSendDateStart(sendDateStart);
        entity.setSendDateEnd(sendDateEnd);
        entity.setGetFlag(getFlag);
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(AsepsisSendRec entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<AsepsisSendRec> list) {
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
    public StringData saveAll(AsepsisVo<AsepsisSendRec> asepsisVo) {
        List<AsepsisSendRec> newUpdateDict = new ArrayList<AsepsisSendRec>();
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
     * 获取当天最大的编码后缀
     * @param orgId
     * @param prefix
     * @return
     */
    @GET
    @Path("getMaxSuffix")
    public String getMaxSuffix(@QueryParam("orgId")String orgId,@QueryParam("prefix")String prefix){
        String documentNo = api.getMaxDocumentNo(orgId);
        if(documentNo != null && documentNo.trim().length() > prefix.length()){
            return Double.valueOf(documentNo.substring(prefix.length())).toString();
        }
        return null;
    }

    /**
     * 检索有库存、在保质期内的数据
     * @param
     * @return
     */
    @GET
    @Path("findListWithStock")
    public List<AsepsisSendRec> findListWithStock(@QueryParam("orgId")String orgId,
                                                  @QueryParam("sendDateStart")Date sendDateStart,
                                                  @QueryParam("sendDateEnd")Date sendDateEnd,
                                                  @QueryParam("fromDept")String fromDept,
                                                  @QueryParam("sender")String sender){
        AsepsisSendRec entity = new AsepsisSendRec();
        entity.setOrgId(orgId);
        entity.setSendDateStart(sendDateStart);
        entity.setSendDateEnd(sendDateEnd);
        entity.setFromDept(fromDept);
        entity.setSender(sender);
        return api.findListWithStock(entity);
    }

    /**
     * 检索有库存、在保质期内的数据
     * @param
     * @return
     */
    @GET
    @Path("findListNoStock")
    public List<AsepsisSendRec> findListNoStock(@QueryParam("orgId")String orgId,
                                                  @QueryParam("sendDateStart")Date sendDateStart,
                                                  @QueryParam("sendDateEnd")Date sendDateEnd,
                                                  @QueryParam("itemName")String itemName,
                                                  @QueryParam("documentNo")String documentNo){
        AsepsisSendRec entity = new AsepsisSendRec();
        entity.setOrgId(orgId);
        entity.setSendDateStart(sendDateStart);
        entity.setSendDateEnd(sendDateEnd);
        entity.setItemName(itemName);
        entity.setDocumentNo(documentNo);
        return api.findListNoStock(entity);
    }
    /**
     * 统计科室消毒费
     * @param
     * @return
     */
    @GET
    @Path("findListFee")
    public List<AsepsisSendRec> findListFee(@QueryParam("orgId")String orgId,
                                                  @QueryParam("sendDateStart")Date sendDateStart,
                                                  @QueryParam("sendDateEnd")Date sendDateEnd,
                                                  @QueryParam("fromDept")String fromDept,
                                                  @QueryParam("itemCode")String itemCode,
                                                  @QueryParam("itemName")String itemName,
                                                  @QueryParam("documentNo")String documentNo,
                                                  @QueryParam("flag")String flag){
        AsepsisSendRec entity = new AsepsisSendRec();
        entity.setOrgId(orgId);
        entity.setSendDateStart(sendDateStart);
        entity.setSendDateEnd(sendDateEnd);
        entity.setFromDept(fromDept);
        entity.setItemCode(itemCode);
        entity.setItemName(itemName);
        entity.setDocumentNo(documentNo);
        entity.setFlag(flag);
        return api.findListFee(entity);
    }
}