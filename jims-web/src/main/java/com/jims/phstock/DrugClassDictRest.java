package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugClassDictApi;
import com.jims.phstock.entity.DrugClassDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 提供药品类别维护的服务
 * Created by ztq on 2016/5/9.
 */
@Component
@Produces("application/json")
@Path("drug-class-dict")
public class DrugClassDictRest {

    @Reference(version ="1.0.0")
    private DrugClassDictApi drugClassDictApi ;

    /**
     * 根据组织结构ID 获取本组织结构的药品类别字典
     * @param orgId 组织机构ID
     * @return
     * @Author zq
     */
    @GET
    @Path("list")
    public List<DrugClassDict> listDrugClassDictByOrgId(@QueryParam("orgId")String orgId){
        return drugClassDictApi.listDrugClassDictByOrgId(orgId) ;
    }

    /***
     * 获取组织机构的某一大类的所有亚类
     * @param orgId 组织机构ID
     * @param parentId 大类ID
     * @return 返回某一大类的所有亚类
     * @author zq
     */
    @GET
    @Path("list-parent")
    public List<DrugClassDict> listSubClassDict(@QueryParam("orgId")String orgId ,@QueryParam("parentId")String parentId){
        parentId=parentId;
        return drugClassDictApi.listSubClassDict(orgId,parentId) ;
    }

    /***
     * 保存修改与增加父类
     * @param drugClassDict
     * zq
     */
    @Path("save")
    @POST
    public StringData save(DrugClassDict drugClassDict){;
        drugClassDict=drugClassDict;
        String num=drugClassDictApi.saveDrugClassDict(drugClassDict);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    //保存多个

    //根据大类获取亚类

    //修改类别

    //删除类别

}
