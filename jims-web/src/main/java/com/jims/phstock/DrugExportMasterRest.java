package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.exam.vo.ExamRptPatternVo;
import com.jims.phstock.api.DrugExportMasterApi;
import com.jims.phstock.api.DrugExportServiceApi;
import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
@Component
@Produces("application/json")
@Path("drug-export-master")
public class DrugExportMasterRest {

    @Reference(version = "1.0.0")
    private DrugExportMasterApi drugExportMasterApi;
    @Reference(version = "1.0.0")
    private DrugExportServiceApi drugExportServiceApi;

    /**
     * 查询入库记录
     * @param orgId
     * @return
     */
    @Path("find-list")
    @GET
    public List<DrugExportMaster> findList(
            @QueryParam("orgId") String orgId,@QueryParam("subStorage")String subStorage,
            @QueryParam("startImportDate")String startImportDate,
            @QueryParam("stopImportDate")String stopImportDate,@QueryParam("storage")String storageCode ) {
        return drugExportMasterApi.findMasterList(orgId,subStorage,startImportDate,stopImportDate,storageCode);
    }

    /**
     *
     * @param documentNo
     * @return
     */

   @Path("find-detail-list")
   @GET
   public List<DrugExportDetail> findDetailList(@QueryParam("documentNo") String documentNo) {
       return drugExportServiceApi.findDetailList(documentNo);
   }

    /**
     * 保存上账
     * @param
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ExamRptPatternVo<DrugExportMaster> examRptPatternVo) {
        int num = 0;
        int count = 0;

        for (int i = 0; i < examRptPatternVo.getUpdated().size(); i++) {
            DrugExportMaster drugExportMaster = new DrugExportMaster();
            drugExportMaster = (DrugExportMaster) examRptPatternVo.getUpdated().get(i);
            drugExportMaster.setFlag(drugExportMaster.getFlag());
            drugExportMaster.setVoucherNo(drugExportMaster.getVoucherNo());
            drugExportMaster.setTallyDate(drugExportMaster.getTallyDate());
            drugExportMaster.setTallyOperator(drugExportMaster.getTallyOperator());
            count = count + Integer.parseInt(drugExportMasterApi.update(drugExportMaster));
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
