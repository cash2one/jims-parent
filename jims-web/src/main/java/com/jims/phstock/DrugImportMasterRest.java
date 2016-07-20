package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.exam.vo.ExamRptPatternVo;
import com.jims.phstock.api.DrugImportMasterApi;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
@Component
@Produces("application/json")
@Path("drug-import-master")
public class DrugImportMasterRest {

    @Reference(version = "1.0.0")
    private DrugImportMasterApi drugImportMasterApi;
    @Reference(version = "1.0.0")
    private DrugImportServiceApi drugImportServiceApi;

    /**
     * 查询入库记录
     * @param orgId
     * @return
     */
    @Path("find-list")
    @GET
    public List<DrugImportMaster> findList(
            @QueryParam("orgId") String orgId,@QueryParam("subStorage")String subStorage,
            @QueryParam("supplier")String supplier,@QueryParam("startImportDate")String startImportDate,
            @QueryParam("stopImportDate")String stopImportDate,@QueryParam("storage")String storageCode ) {
        return drugImportMasterApi.findMasterList(orgId,subStorage,supplier,startImportDate,stopImportDate,storageCode);
    }

    /**
     * 查询入库详情
     * @param documentNo
     * @return
     */
    @Path("find-detail-list")
    @GET
    public List<DrugImportDetail> findDetailList(@QueryParam("documentNo") String documentNo) {
        return drugImportServiceApi.findDetailList(documentNo);
    }

    /**
     * 保存上账
     * @param
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ExamRptPatternVo<DrugImportMaster> examRptPatternVo) {
        int num = 0;
        int count = 0;

        for (int i = 0; i < examRptPatternVo.getUpdated().size(); i++) {
            DrugImportMaster drugImportMaster = new DrugImportMaster();
            drugImportMaster = (DrugImportMaster) examRptPatternVo.getUpdated().get(i);
            drugImportMaster.setFlag(drugImportMaster.getFlag());
            drugImportMaster.setVoucherNo(drugImportMaster.getVoucherNo());
            drugImportMaster.setTallyDate(drugImportMaster.getTallyDate());
            drugImportMaster.setTallyOperator(drugImportMaster.getTallyOperator());
            count = count + Integer.parseInt(drugImportMasterApi.update(drugImportMaster));
        }
        StringData stringData = new StringData();
        if (count ==  examRptPatternVo.getUpdated().size()) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

}
