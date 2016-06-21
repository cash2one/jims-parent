package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.api.DrugNameDictServiceApi;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 提供药品目录维护的服务
 * Created by dt on 2016/5/11.
 */
@Component
@Produces("application/json")
@Path("drug-catalog")
public class DrugCatalogRest {

    @Reference(version ="1.0.0")
    private DrugNameDictServiceApi drugNameDictServiceApi ;
    @Reference(version ="1.0.0")
    private DrugDictServiceApi drugDictServiceApi ;
    @Reference(version ="1.0.0")
    private DrugPriceListServiceApi drugPriceListServiceApi;

    /**
     * 查询所有药品名称列表通过拼音码
     * @param inputCode 拼音码
     * @return
     * @author txb
     * @version 2016-05-11
     */
    @Path("drugNameDictList")
    @GET
    public List<DrugNameDict> drugNameDictList(@QueryParam("q") String inputCode){
        return drugNameDictServiceApi.findDrugNameDictList(inputCode);
    }



    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    @Path("listDrugDictByDrugCode")
    @GET
    public List<DrugDict> listDrugDictByDrugCode(@QueryParam("drugCode")String drugCode){
        return drugDictServiceApi.listDrugDictByDrugCode(drugCode);
    }
    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    @Path("listDrugNameDictByDrugCode")
    @GET
    public List<DrugNameDict> listDrugNameDictByDrugCode(@QueryParam("drugCode")String drugCode){
        return drugNameDictServiceApi.listDrugNameDictByDrugCode(drugCode);
    }
    /**
     * 删除药品名称
     * @param id 药品名称id
     * @return
     * @author txb
     */
    @Path("delDrugNameDict")
    @POST
    public StringData delDrugNameDict(@QueryParam("id")String id){
        String num = drugNameDictServiceApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 根据药品代码查询当前组织结构的药品价格
     * @param drugCode 药品代码
     * @param orgId 组织机构id
     * @return
     * @author txb
     */
    @Path("listDrugPriceList")
    @GET
    public List<DrugPriceList> listDrugPriceList(@QueryParam("drugCode")String drugCode,@QueryParam("orgId")String orgId){
        return drugPriceListServiceApi.listDrugPriceList(drugCode, orgId);
    }
    /**
     * 保存药品目录
     * @param drugCatalogBeanVo 药品实体vo类
     * @return
     * @author txb
     */
    @Path("save")
    @POST
    public String save(DrugCatalogBeanVo drugCatalogBeanVo){
        return drugDictServiceApi.saveDrugCatalog(drugCatalogBeanVo);
    }

    /**
     * 根据商品亚类 药品剂型,序号长度生成药品代码drug_code
     * @param secondType
     * @param drugForm
     * @return
     * @author txb
     *
     */
    @Path("getDrugCodeByRule")
    @GET
    public StringData getDrugCodeByRule(@QueryParam("secondType")String secondType,@QueryParam("drugForm")String drugForm){
        String newDrugCode = drugDictServiceApi.getDrugCodeByRule(secondType,drugForm,null);
        StringData stringData = new StringData();
        stringData.setCode(newDrugCode);
        stringData.setData("success");
        return stringData;
    }

}
