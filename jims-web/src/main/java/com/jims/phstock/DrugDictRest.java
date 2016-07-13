package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.entity.DrugDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 提供药品字典维护的服务
 * Created by ztq on 2016/7/12.
 * @author : lhl
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


    /***
     * 保存修改与增加父类
     * @param drugDict
     * zq
     */
    @Path("save")
    @POST
    public StringData save(DrugDict drugDict){;
        drugDict=drugDict;
//        String num=drugDictApi.saveDrugDict(drugDict);
//        StringData stringData=new StringData();
//        stringData.setCode(num);
//        stringData.setData("success");
//        return stringData;
        return null;
    }

    //保存多个

    //根据大类获取亚类

    //修改类别

    //删除类别

}
