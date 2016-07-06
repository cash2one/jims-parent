package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugInfoApi;
import com.jims.phstock.entity.DrugProvideApplication;
import com.jims.phstock.api.DrugProvideApplicationApi;
import com.jims.phstock.vo.DrugProvideApplicationVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 录入申请rest
 *
 * @author yangruidong
 * @version 2016-07-04
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("drugProvideApplication")
public class DrugProvideApplicationRest {

    @Reference(version = "1.0.0")
    private DrugProvideApplicationApi api;

    /**
     * 检索
     *
     * @param orgId
     * @return
     */
    @GET
    @Path("findList")
    public List<DrugProvideApplication> findList(@QueryParam("orgId") String orgId,
                                                 @QueryParam("applicantStorage") String applicantStorage,
                                                 @QueryParam("applicantStorageSub") String applicantStorageSub,
                                                 @QueryParam("documentNo") String documentNo,
                                                 @QueryParam("flag") String flag) {
        DrugProvideApplication entity = new DrugProvideApplication();
        entity.setOrgId(orgId);
        entity.setApplicantStorage(applicantStorage);
        entity.setApplicantStorageSub(applicantStorageSub);
        entity.setDocumentNo(documentNo);
        entity.setFlag(flag);
        return api.findList(entity);
    }

    /**
     * 检索(含有价格)
     * @param orgId
     * @return
     */
    @GET
    @Path("findListWithPrice")
    public List<DrugProvideApplication> findListWithPrice(@QueryParam("orgId") String orgId,
                                                 @QueryParam("applicantStorage") String applicantStorage,
                                                 @QueryParam("applicantStorageSub") String applicantStorageSub,
                                                 @QueryParam("documentNo") String documentNo,
                                                 @QueryParam("flag") String flag,
                                                 @QueryParam("storage")String storage,
                                                 @QueryParam("subStorage")String subStorage) {
        DrugProvideApplication entity = new DrugProvideApplication();
        entity.setOrgId(orgId);
        entity.setApplicantStorage(applicantStorage);
        entity.setApplicantStorageSub(applicantStorageSub);
        entity.setDocumentNo(documentNo);
        entity.setFlag(flag);
        return api.findListWithPrice(entity,storage,subStorage);
    }

    /**
     * 查询去除重复的申请号
     *
     * @param orgId
     * @return
     */
    @GET
    @Path("findDocument")
    public List<DrugProvideApplication> findDocument(@QueryParam("orgId") String orgId,
                                                     @QueryParam("applicantStorage") String applicantStorage,
                                                     @QueryParam("applicantStorageSub") String applicantStorageSub,
                                                     @QueryParam("flag") String flag) {
        DrugProvideApplication entity = new DrugProvideApplication();
        entity.setOrgId(orgId);
        entity.setApplicantStorage(applicantStorage);
        entity.setApplicantStorageSub(applicantStorageSub);
        entity.setFlag(flag);
        return api.findDocumentByDistinct(entity);
    }

    /**
     * 保存（插入或更新）
     *
     * @param entity
     * @return 0 失败，1成功
     */
    @POST
    @Path("save")
    public String save(DrugProvideApplication entity) {
        return api.save(entity);
    }

    /**
     * 批量保存（插入或更新）
     *
     * @param list
     * @return 0 失败，1成功
     */
    @POST
    @Path("saveList")
    public String saveList(List<DrugProvideApplication> list) {
        return api.save(list);
    }

    /**
     * 保存  增删改
     *
     * @param drugProvideApplicationVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(DrugProvideApplicationVo<DrugProvideApplication> drugProvideApplicationVo) {
        List<DrugProvideApplication> newUpdateDict = new ArrayList<DrugProvideApplication>();
        newUpdateDict = api.saveAll(drugProvideApplicationVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 删除数据
     *
     * @param ids,多个id以逗号（,）隔开
     * @return 0 失败，1成功
     */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }
}