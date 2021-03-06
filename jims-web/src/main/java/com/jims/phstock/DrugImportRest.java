package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.vo.ImpExpVo;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * cxy
     * 查询入库记录
     * @param orgId
     * @param storageCode
     * @param startTime
     * @return
     */
    @POST
    @Path("find-import-data")
    public List<ImpExpVo> findImportData(@QueryParam("orgId") String orgId,@QueryParam("storageCode") String storageCode,@QueryParam("startTime") String startTime){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<ImpExpVo> returnVal=new ArrayList<ImpExpVo>();
        List<DrugImportMaster> l=api.findImportData(orgId,startTime,storageCode);
        if(l!=null&&!l.isEmpty()){
            for(DrugImportMaster d:l){
                ImpExpVo i=new ImpExpVo();
                i.setDate(d.getImportDate()!=null?sdf.format(d.getImportDate()):null);
                i.setId(d.getId());
                i.setImportExportClass(d.getImportClass());
                i.setDocumentNo(d.getDocumentNo());
                i.setFlag(0);
                returnVal.add(i);
            }
        }
        return returnVal;
    }

    /**
     * 记账cxy
     * @param id
     * @return
     */
    @POST
    @Path("acct")
    public DrugImportMaster acct(@QueryParam("id") String id){
        DrugImportMaster drugImportMaster= api.findById(id);
        drugImportMaster.setAccountIndicator(1);
        api.update(drugImportMaster);
        return drugImportMaster;
    }

}
