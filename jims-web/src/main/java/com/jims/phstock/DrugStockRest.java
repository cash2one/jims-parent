package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.phstock.api.DrugStockServiceApi;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugStockVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/5/16.
 */
@Produces("application/json")
@Path("drug-stock")
@Component
public class DrugStockRest {

    @Reference(version ="1.0.0")
    private DrugStockServiceApi drugStockServiceApi ;

    @Path("findListHasStock")
    @GET
    public List<DrugStock> findListHasStock(@QueryParam("orgId")String orgId,
                                   @QueryParam("supplyIndicator")Integer supplyIndicator,
                                   @QueryParam("storage")String storage,
                                   @QueryParam("subStorage")String subStorage,
                                   @QueryParam("start")Integer start,
                                   @QueryParam("limit")Integer limit,
                                   @QueryParam("q")String q) {
        DrugStock drugStock = new DrugStock();
        drugStock.setOrgId(orgId);
        drugStock.setSupplyIndicator(supplyIndicator);
        drugStock.setStorage(storage);
        drugStock.setSubStorage(subStorage);
        drugStock.setQ(q);
        List<DrugStock> list = drugStockServiceApi.findListHasStock(drugStock);
        start = start == null || start < 0 ? 0 : start;
        limit = limit == null || limit < 0 ? list.size() : (start + limit < list.size() ? limit : list.size()-start);
        return list.subList(start,limit);
    }

    /**
     * 药品供应维护，分页查询可维护的药品信息
     * @param orgId  组织机构ID
     * @param supplyIndicator 药品供应标志(0不可供应，1可供应)
     * @param storage 所属科室
     * @param subStorage 所属子科室
     * @param start
     * @param limit
     * @param documentNo 入库单号
     * @param firmId 来源厂家
     * @param drugCode 药品名称对应的编号
     * @param request
     * @param response
     * @return
     * @author lhl
     */
    @Path("findListStock")
    @GET
    public PageData findListStock(@QueryParam("orgId")String orgId,
                                   @QueryParam("supplyIndicator")Integer supplyIndicator,
                                   @QueryParam("storage")String storage,
                                   @QueryParam("subStorage")String subStorage,
                                   @QueryParam("start")Integer start,
                                   @QueryParam("limit")Integer limit,
                                   @QueryParam("documentNo")String documentNo,
                                   @QueryParam("firmId")String firmId,
                                   @QueryParam("drugCode")String drugCode,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        DrugStock drugStock = new DrugStock();
        drugStock.setOrgId(orgId);
        drugStock.setSupplyIndicator(supplyIndicator);
        drugStock.setStorage(storage);
        drugStock.setSubStorage(subStorage);
        drugStock.setDrugCode(drugCode);
        drugStock.setDocumentNo(documentNo);
        drugStock.setFirmId(firmId);
        Page<DrugStock> page = drugStockServiceApi.findPage(new Page<DrugStock>(request, response), drugStock);
        PageData<DrugStock> pageData = new PageData<DrugStock>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 保存  增删改
     *
     * @param drugStockVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(DrugStockVo<DrugStock> drugStockVo) {
        List<DrugStock> newUpdateDict = new ArrayList<DrugStock>();
        newUpdateDict = drugStockServiceApi.saveAll(drugStockVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }
}
