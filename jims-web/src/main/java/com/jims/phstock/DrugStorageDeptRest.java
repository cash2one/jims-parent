package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugStorageDeptServiceApi;
import com.jims.phstock.entity.DrugStorageDept;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by fyg on 2016/5/12.
 * 药品库存单位Rest
 */
@Component
@Produces("application/json")
@Path("drug-storage-dept")
public class DrugStorageDeptRest {

    @Reference(version = "1.0.0")
    private DrugStorageDeptServiceApi drugStorageDeptService;

    /**
     * 获取药品库存单位集合
     * @param orgId 组织机构ID
     * @param storageType 单位性质
     * @param storageCode
     * @param q 检索查询字段
     * @return 库存单位list集合
     * @author fyg
     */
    @GET
    @Path("list")
    public List<DrugStorageDept> list(@QueryParam("orgId")String orgId,
                                      @QueryParam("storageType") String storageType,
                                      @QueryParam("storageCode") String storageCode,
                                      @QueryParam("q") String q){
        DrugStorageDept drugStorageDept = new DrugStorageDept();
        drugStorageDept.setOrgId(orgId);
        drugStorageDept.setStorageType(storageType);
        drugStorageDept.setStorageCode(storageCode);
        drugStorageDept.setQ(q);
        return drugStorageDeptService.findList(drugStorageDept);
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo 增加修改删除的数据集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("save")
    public StringData save(BeanChangeVo<DrugStorageDept> beanChangeVo){
        String num = drugStorageDeptService.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 获取药品库存单位子单位
     * @param orgId 所属机构
     * @param storageCode  库存单位编码
     * @return
     */
    @GET
    @Path("findSubList")
    public List<DrugSubStorageDept> findSubList(@QueryParam("orgId")String orgId
            ,@QueryParam("storageCode")String storageCode){
        return drugStorageDeptService.findSubList(orgId,storageCode);
    }

    @POST
    @Path("saveSub")
    public String saveSub(DrugSubStorageDept sub){
        return drugStorageDeptService.saveSub(sub);
    }
}
