package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.api.DrugInfoApi;
import com.jims.phstock.api.DrugNameDictServiceApi;
import com.jims.phstock.api.DrugPriceListServiceApi;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugInfo;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 提供药品毒理信息维护的服务
 * Created by yangruidong on 2016/5/16.
 */
@Component
@Produces("application/json")
@Path("drug-info")
public class DrugInfoRest {

    @Reference(version = "1.0.0")
    private DrugInfoApi drugInfoApi;

    @Reference(version = "1.0.0")
    private DictServiceApi dictServiceApi;


    /**
     * 根据药品代码查询药品毒理的相关信息
     *
     * @param drugCode
     * @return
     * @author yangruidong
     */
    @Path("findDrugInfoByDrugCode")
    @GET
    public DrugInfo getDrugInfoByDrugCode(@QueryParam("drugCode") String drugCode) {
        DrugInfo drugInfo = drugInfoApi.getDrugInfoByDrugCode(drugCode);
        return drugInfo;
    }


    /**
     * 保存药品毒理信息
     *
     * @param drugInfo 药品毒理信息实体类
     * @return
     * @author txb
     */
    @Path("save")
    @POST
    public StringData save(DrugInfo drugInfo) {

        String num = drugInfoApi.save(drugInfo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
