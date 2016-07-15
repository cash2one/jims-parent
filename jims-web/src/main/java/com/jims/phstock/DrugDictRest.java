package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.entity.DrugDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 药品字典Rest
 * Created by fyg on 2016/7/12.
 */
@Component
@Produces("application/json")
@Path("drug-dict")
public class DrugDictRest {

    @Reference(version ="1.0.0")
    private DrugDictServiceApi drugDictApi ;

    /**
     * 获取药品类别字典
     * @return
     * @Author zq
     */
    @GET
    @Path("list")
    public List<DrugDict> listDrugDict(){
        DrugDict drugDict = new DrugDict();
//        drugDict.setDelFlag("0");
        return drugDictApi.findList(drugDict) ;
    }



    /**
     * 根据药品名称或药品代码查询数据
     * @return
     * @author fengyuguang
     */
    @Path("get-by-name")
    @GET
    public List<DrugDict> getByName(@QueryParam("drugCode")String drugCode,@QueryParam("drugName")String drugName){
        return drugDictApi.getByName(drugCode,drugName);
    }
}
