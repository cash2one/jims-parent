package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.AsepsisDict;
import com.jims.asepsis.api.AsepsisDictApi;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.common.data.StringData;
import com.jims.sys.entity.InputSettingMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
* 包名称维护rest
* @author yangruidong
* @version 2016-06-27
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisDict")
public class AsepsisDictRest {

    @Reference(version = "1.0.0")
    private AsepsisDictApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<AsepsisDict> findList(@QueryParam("orgId")String orgId) {
        AsepsisDict entity = new AsepsisDict();
        entity.setOrgId(orgId);
        return api.findList(entity);
    }

    /**
     * 检索科室下的包
     * @param orgId
     * @return
     */
    @GET
    @Path("findPage")
    public List<AsepsisDict> findPage(@QueryParam("deptCode") String deptCode,@QueryParam("orgId")String orgId,@QueryParam("pageName") String pageName) {
        AsepsisDict entity = new AsepsisDict();
        entity.setOrgId(orgId);
        entity.setBelongDept(deptCode);
        entity.setAsepsisName(pageName);
        return api.findPageByDept(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(AsepsisDict entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<AsepsisDict> list) {
        return api.save(list);
    }

    /**
     * 保存  增删改
     *
     * @param asepsisDictVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(AsepsisDictVo<AsepsisDict> asepsisDictVo) {
        List<AsepsisDict> newUpdateDict = new ArrayList<AsepsisDict>();
        newUpdateDict = api.saveAll(asepsisDictVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

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

    /**
     * 检索有库存的数据
     * @param orgId
     * @param belongDept
     * @param q
     * @return
     */
    @GET
    @Path("findListHasStock")
    public List<AsepsisDict> findListHasStock(@QueryParam("orgId")String orgId,
                                              @QueryParam("belongDept")String belongDept,
                                              @QueryParam("q")String q) {
        AsepsisDict entity = new AsepsisDict();
        entity.setOrgId(orgId);
        entity.setBelongDept(belongDept);
        entity.setQ(q);
        return api.findListHasStock(entity);
    }
}