package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.common.data.StringData;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.exam.vo.ExamRptPatternVo;
import com.jims.phstock.api.DrugSupplierCatalogApi;
import com.jims.phstock.entity.DrugSupplierCatalog;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2016/5/10.
 */
@Component
@Produces("application/json")
@Path("drug-supplier-catalog")
public class DrugSupplierCatalogRest {

    @Reference(version = "1.0.0")
    private DrugSupplierCatalogApi drugSupplierCatalogApi;

    /**
     * 查询全部
     *
     * @param orgId
     * @return
     */
    @Path("list")
    @GET
    public List<DrugSupplierCatalog> list(@QueryParam("orgId") String orgId) {
        return drugSupplierCatalogApi.findList(orgId);
    }

    /**
     * 查询厂商类别
     *
     * @param orgId
     * @return
     */
    @Path("list-type")
    @GET
    public List<DrugSupplierCatalog> listSupplier(@QueryParam("orgId") String orgId) {
        return drugSupplierCatalogApi.list(orgId);
    }

    /**
     * 根据supplierType查询
     *
     * @param orgId
     * @param supplierType
     * @return
     */
    @Path("list-supplier-type")
    @GET
    public List<DrugSupplierCatalog> listSupplierType(@QueryParam("orgId") String orgId, @QueryParam("supplierClass") String supplierType) {
        return drugSupplierCatalogApi.listDrugSupplierCatalogBySupplierType(orgId, supplierType);
    }

    /**
     * 根据inputCode查询
     *
     * @param orgId
     * @param inputCode
     * @return
     */
    @Path("list-supplier-input-code")
    @GET
    public List<DrugSupplierCatalog> listSupplierInputCode(@QueryParam("orgId") String orgId, @QueryParam("inputCode") String inputCode) {
        return drugSupplierCatalogApi.listDrugSupplierCatalogByInputCode(orgId, inputCode);
    }

    /**
     * 保存增加和修改
     *
     * @param examRptPatternVo
     * @return
     */
    @POST
    @Path("merge")
    public StringData save(ExamRptPatternVo<DrugSupplierCatalog> examRptPatternVo) {
        int num = 0;
        int count = 0;
        for (int i = 0; i < examRptPatternVo.getInserted().size(); i++) {
            DrugSupplierCatalog drugSupplierCatalog = new DrugSupplierCatalog();
            drugSupplierCatalog = (DrugSupplierCatalog) examRptPatternVo.getInserted().get(i);
            drugSupplierCatalog.preInsert();
            drugSupplierCatalog.setSupplierId(drugSupplierCatalog.getSupplierId());
            drugSupplierCatalog.setSupplier(drugSupplierCatalog.getSupplier());
            drugSupplierCatalog.setSupplierClass(drugSupplierCatalog.getSupplierClass());
            drugSupplierCatalog.setInputCode(PinYin2Abbreviation.cn2py(drugSupplierCatalog.getSupplier()));
            drugSupplierCatalog.setMemo(drugSupplierCatalog.getMemo());
            drugSupplierCatalog.setTrademark(drugSupplierCatalog.getTrademark());
            drugSupplierCatalog.setInputCodeWb(drugSupplierCatalog.getInputCodeWb());
            drugSupplierCatalog.setForeignx(drugSupplierCatalog.getForeignx());
            drugSupplierCatalog.setSupplierCode(drugSupplierCatalog.getSupplierCode());
            drugSupplierCatalog.setUsedFlag(drugSupplierCatalog.getUsedFlag());
            drugSupplierCatalog.setOrgId(examRptPatternVo.getOrgId());
            count = count + Integer.parseInt(drugSupplierCatalogApi.save(drugSupplierCatalog));
        }
        for (int i = 0; i < examRptPatternVo.getUpdated().size(); i++) {
            DrugSupplierCatalog drugSupplierCatalog = new DrugSupplierCatalog();
            drugSupplierCatalog = (DrugSupplierCatalog) examRptPatternVo.getUpdated().get(i);
            drugSupplierCatalog.setSupplierId(drugSupplierCatalog.getSupplierId());
            drugSupplierCatalog.setSupplier(drugSupplierCatalog.getSupplier());
            drugSupplierCatalog.setSupplierClass(drugSupplierCatalog.getSupplierClass());
            drugSupplierCatalog.setInputCode(PinYin2Abbreviation.cn2py(drugSupplierCatalog.getSupplier()));
            drugSupplierCatalog.setMemo(drugSupplierCatalog.getMemo());
            drugSupplierCatalog.setTrademark(drugSupplierCatalog.getTrademark());
            drugSupplierCatalog.setInputCodeWb(drugSupplierCatalog.getInputCodeWb());
            drugSupplierCatalog.setForeignx(drugSupplierCatalog.getForeignx());
            drugSupplierCatalog.setSupplierCode(drugSupplierCatalog.getSupplierCode());
            drugSupplierCatalog.setUsedFlag(drugSupplierCatalog.getUsedFlag());
            drugSupplierCatalog.setOrgId(examRptPatternVo.getOrgId());
            count = count + Integer.parseInt(drugSupplierCatalogApi.update(drugSupplierCatalog));
        }
        StringData stringData = new StringData();
        if (count == (examRptPatternVo.getInserted().size() + examRptPatternVo.getUpdated().size())) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = drugSupplierCatalogApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}