package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugSubStorageDeptApi;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 药品库存子单位 Rest
 * Created by fyg on 2016/6/28.
 */
@Component
@Produces("application/json")
@Path("drug-sub-storage-dept")
public class DruSubStorageDeptRest {

    @Reference(version = "1.0.0")
    private DrugSubStorageDeptApi drugSubStorageDeptApi;

    /**
     * 根据库存单位代码获取该库存单位下的子库存单位列表
     * @param orgId  所属组织机构ID
     * @param storageCode  库存单位代码
     * @return  子库存单位列表
     * @author fengyuguang
     */
    @Path("list-by-storageCode")
    @GET
    public List<DrugSubStorageDept> getListByStorageCode(@QueryParam("orgId")String orgId,@QueryParam("storageCode")String storageCode){
        return drugSubStorageDeptApi.getListByStorageCode(orgId,storageCode);
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<DrugSubStorageDept> beanChangeVo) {
        String num = drugSubStorageDeptApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

}
