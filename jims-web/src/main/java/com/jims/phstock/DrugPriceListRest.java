package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugPriceList;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
}
