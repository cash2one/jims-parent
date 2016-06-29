package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.api.DrugNameDictServiceApi;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.Date;
import java.util.List;

/**
 * 药品价格维护服务
 * @author lgx
 * @version 2016/5/12
 */
@Component
@Produces("application/json")
@Path("drug-price")
public class DrugPriceListRest {

    @Reference(version ="1.0.0")
    private DrugPriceListServiceApi drugPriceListServiceApi ;
    @Reference(version ="1.0.0")
    private DrugDictServiceApi drugDictServiceApi ;
    @Reference(version ="1.0.0")
    private DrugNameDictServiceApi drugNameDictServiceApi ;

    /**
     * 根据当前组织结构获取去本组织结构内所有的药品名称字典。
     * 关联durg_price_list,drug_name_dict drug_price_list drug_code 去重复。
     * @param orgId
     * @return
     * @author txb
     *
     */
    @GET
    @Path("listDrugNameDict")
    public List<DrugNameDict> listDrugNameDict(@QueryParam("orgId")String orgId){
        return drugPriceListServiceApi.listDrugNameDict(orgId);
    }
    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @return
     */
    @GET
    @Path("findDrugDict")
    public List<DrugDict> findDrugDict(@QueryParam("orgId")String orgId){
        return drugPriceListServiceApi.findDrugDict(orgId);
    }

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @return
     */
    @GET
    @Path("findDrugDictWithFilter")
    public List<DrugDict> findDrugDictWithFilter(@QueryParam("orgId")String orgId,
                                       @QueryParam("q")String q,
                                       @QueryParam("start")Integer start,
                                       @QueryParam("limit")Integer limit){
        List<DrugDict> list = drugPriceListServiceApi.findDrugDict(orgId,q);
        start = start == null || start < 0 ? 0 : start;
        limit = limit == null || limit < 0 ? list.size() : (start + limit < list.size() ? limit : list.size()-start);
        return list.subList(start,limit);
    }

    /**
     * 检索未删除的药品价表
     * @param orgId 所属机构Id
     * @param drugCode 药品编码
     * @return
     */
    @GET
    @Path("findList")
    public List<DrugPriceList> findList(@QueryParam("orgId")String orgId
            ,@QueryParam("drugCode")String drugCode){
        DrugPriceList priceObj = new DrugPriceList();
        priceObj.setOrgId(orgId);
        priceObj.setDrugCode(drugCode);
        //检索当前时间可用的数据，参数通过stopDate属性传递
        priceObj.setStopDate(new Date());
        return drugPriceListServiceApi.findList(priceObj);
    }
    /**
     * 根据药品代码查询当前组织结构的药品价格
     * 不同规格、不同厂商，不同单位，不同价格，不同零售价
     * @param drugCode
     * @param orgId
     * @return
     * @author txb
     */
    @GET
    @Path("listDrugPriceList")
    public List<DrugPriceList> listDrugPriceList(@QueryParam("drugCode")String drugCode,@QueryParam("orgId")String orgId){
        return drugPriceListServiceApi.listDrugPriceList(drugCode,orgId);
    } ;
    /**
     * 根据药品代码查询当前组织结构的药品价格
     * 不同规格、不同厂商，不同单位，不同价格，不同零售价
     * @param classCode

     * @return
     * @author txb
     */
    @GET
    @Path("listDrugNameDictByClassCode")
    public List<DrugNameDict> listDrugNameDictByClassCode(@QueryParam("classCode")String classCode){
        return drugPriceListServiceApi.listDrugNameDictByClassCode(classCode);
    } ;
    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    @GET
    @Path("listDrugDictByDrugCode")
    public List<DrugDict> listDrugDictByDrugCode(@QueryParam("drugCode")String drugCode){
        return drugDictServiceApi.listDrugDictByDrugCode(drugCode);
    } ;

    /**
     * 保存 删除 修改
     * @param drugPriceList
     * @return
     * @author txb
     */
    @POST
    @Path("save")
    public StringData save(DrugCatalogChangeVo<DrugPriceList> drugPriceList){
        String num = drugPriceListServiceApi.saveDrugPrice(drugPriceList);
        StringData stringData  = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    };

    /**
     *查找药品名称全部列表
     * @return
     * @author txb
     */
    @GET
    @Path("findDrugNameDictList")
    public List<DrugNameDict> findDrugNameDictList(){
        return drugNameDictServiceApi.findList(new DrugNameDict());
    }
}
