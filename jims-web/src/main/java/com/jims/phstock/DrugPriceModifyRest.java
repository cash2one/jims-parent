package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.entity.DrugPriceModify;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 调价记录表rest
 * @author txb
 * @version 2016-05-18
 */
@Component
@Produces("application/json")
@Path("drug-price-modify")
public class DrugPriceModifyRest {

    @Reference(version = "1.0.0")
    private DrugPriceListServiceApi drugPriceListServiceApi;

    /**
     * 查询列表药品调价数据
     * @return
     * @author txb
     * @version 2016-05-18
     */
    @Path("findAll")
    @GET
    public List<DrugPriceModify> findAllList() {
        return drugPriceListServiceApi.findListDrugPriceModify(new DrugPriceModify());
    }
    /**
     * 保存药品调价数据
     * @param drugPriceModifyVo
     * @return
     * @author txb
     * @version 2016-05-18
     */
    @Path("save")
    @POST
    public StringData save(DrugCatalogChangeVo<DrugPriceModify> drugPriceModifyVo) {
        String num = drugPriceListServiceApi.saveDrugPriceModify(drugPriceModifyVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
