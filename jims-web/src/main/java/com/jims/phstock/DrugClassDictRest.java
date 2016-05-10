package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.phstock.api.DrugClassDictApi;
import com.jims.phstock.entity.DrugClassDict;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * 提供药品类别维护的服务
 * Created by ztq on 2016/5/9.
 */
@Produces("application/json")
@Path("drug-class-dict")
public class DrugClassDictRest {

    @Reference(version ="1.0.0")
    private DrugClassDictApi drugClassDictApi ;

    /**
     * 根据组织结构ID 获取本组织结构的药品类别字典
     * @param orgId 组织机构ID
     * @return
     * @Author ztq
     */
    @GET
    @Path("list")
    public List<DrugClassDict> listDrugClassDictByOrgId(@QueryParam("orgId")String orgId){
        return null ;
    }


    //保存单个

    //保存多个

    //根据大类获取亚类

    //修改类别

    //删除类别

}
