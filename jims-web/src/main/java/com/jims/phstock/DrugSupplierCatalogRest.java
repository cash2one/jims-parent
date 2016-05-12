package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.phstock.api.DrugSupplierCatalogApi;
import com.jims.phstock.entity.DrugSupplierCatalog;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @Path("list")
    @GET
    public List<DrugSupplierCatalog> list(@QueryParam("orgId")String orgId){
        return drugSupplierCatalogApi.findList(orgId);
    }
    @Path("list-type")
    @GET
    public List<DrugSupplierCatalog> listSupplier(@QueryParam("orgId")String orgId){
        return drugSupplierCatalogApi.list(orgId);
    }

    @Path("list-supplier-type")
    @GET
    public List<DrugSupplierCatalog> listSupplierType(@QueryParam("orgId")String orgId,@QueryParam("supplierClass")String supplierType){
        return drugSupplierCatalogApi.listDrugSupplierCatalogBySupplierType(orgId,supplierType);
    }

    @Path("list-supplier-inputCode")
    @GET
    public List<DrugSupplierCatalog> listSupplierInputCode(@QueryParam("orgId")String orgId,@QueryParam("inputCode")String inputCode){
        return drugSupplierCatalogApi.listDrugSupplierCatalogByInputCode(orgId,inputCode);
    }

}
