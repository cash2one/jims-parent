package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.asepsis.api.AsepsisLendRecApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
* 借物还物rest
* @author lgx
* @version 2016-06-27
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("asepsisLendRec")
public class AsepsisLendRecRest {

    @Reference(version = "1.0.0")
    private AsepsisLendRecApi api;

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<AsepsisLendRec> findList(@QueryParam("orgId")String orgId,
                                         @QueryParam("lendDate")Date lendDate,
                                         @QueryParam("toDept")String toDept,
                                         @QueryParam("lender")String lender) {
        AsepsisLendRec entity = new AsepsisLendRec();
        entity.setOrgId(orgId);
        entity.setLendDate(lendDate);
        entity.setToDept(toDept);
        entity.setLender(lender);
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(AsepsisLendRec entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<AsepsisLendRec> list) {
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