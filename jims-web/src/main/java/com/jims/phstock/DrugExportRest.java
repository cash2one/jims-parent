package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugExportServiceApi;
import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

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

    /**
     * 检索出库数据
     * @param orgId 机构ID
     * @param receiver 收货方
     * @param subReceiver 收货子单位
     * @param subStorage 存放库房
     * @return
     */
    @GET
    @Path("findMasterList")
    public List<DrugExportMaster> findMasterList(@QueryParam("orgId")String orgId,
                                                 @QueryParam("receiver")String receiver,
                                                 @QueryParam("subReceiver")String subReceiver,
                                                 @QueryParam("subStorage")String subStorage){
        DrugExportMaster master = new DrugExportMaster();
        master.setOrgId(orgId);
        master.setReceiver(receiver);
        master.setSubReceiver(subReceiver);
        master.setSubStorage(subStorage);
        return api.findMasterList(master);
    }

    /**
     * 检索出库明细
     * @param orgId
     * @param documentNo
     * @return
     */
    @GET
    @Path("findDetailList")
    public List<DrugExportDetail> findDetailList(@QueryParam("orgId")String orgId,
                                                 @QueryParam("documentNo")String documentNo){
        DrugExportDetail detail = new DrugExportDetail();
        detail.setOrgId(orgId);
        detail.setDocumentNo(documentNo);
        return api.findDetailList(detail);
    }

    /**
     * 检索出库明细(包含库存)
     * @param orgId
     * @param documentNo
     * @return
     */
    @GET
    @Path("findDetailListWithStock")
    public List<DrugExportDetail> findDetailListWithStock(@QueryParam("orgId")String orgId,
                                                 @QueryParam("documentNo")String documentNo,
                                                 @QueryParam("storage")String storage,
                                                 @QueryParam("subStorage")String subStorage){
        DrugExportDetail detail = new DrugExportDetail();
        detail.setOrgId(orgId);
        detail.setDocumentNo(documentNo);
        return api.findDetailListWithStock(detail,storage,subStorage);
    }
}
