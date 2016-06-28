package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.api.OrgSysDictApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
* 包单位维护rest
* @author yangruidong
* @version 2016-06-28
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("orgSysDict")
public class OrgSysDictRest {

    @Reference(version = "1.0.0")
    private OrgSysDictApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<OrgSysDict> findList(@QueryParam("orgId")String orgId) {
        OrgSysDict entity = new OrgSysDict();
        entity.setOrgId(orgId);
        return api.findList(entity);
    }

    /**
     * 根据类型查询包单位
     * @param type
     * @return
     */
    @GET
    @Path("findUnits")
    public List<OrgSysDict> findUnits(@QueryParam("type")String type,@QueryParam("orgId")String orgId) {
        OrgSysDict entity = new OrgSysDict();
        entity.setType(type);
        entity.setOrgId(orgId);
        return api.findUnits(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(OrgSysDict entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<OrgSysDict> list) {
        return api.save(list);
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }
}