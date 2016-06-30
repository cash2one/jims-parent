package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.AsepsisDetailDict;
import com.jims.asepsis.api.AsepsisDetailDictApi;
import com.jims.asepsis.vo.AsepsisDetailDictVo;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
* 包内物品管理rest
* @author yangruidong
* @version 2016-06-27
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisDetailDict")
public class AsepsisDetailDictRest {

    @Reference(version = "1.0.0")
    private AsepsisDetailDictApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<AsepsisDetailDict> findList(@QueryParam("orgId")String orgId,@QueryParam("asepsisCode") String asepsisCode) {
        AsepsisDetailDict entity = new AsepsisDetailDict();
        entity.setOrgId(orgId);
        entity.setAsepsisCode(asepsisCode);
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(AsepsisDetailDict entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<AsepsisDetailDict> list) {
        return api.save(list);
    }

   /*
     * 保存  增删改
     *
     * @param asepsisDictVo
     * @return
     * @author yangruidong
      */
    @Path("saveAll")
    @POST
    public StringData saveAll(AsepsisDetailDictVo<AsepsisDetailDict> asepsisDictVo) {
        List<AsepsisDetailDict> newUpdateDict = new ArrayList<AsepsisDetailDict>();
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
}