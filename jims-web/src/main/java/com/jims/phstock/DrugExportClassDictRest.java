package com.jims.phstock;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugExportClassDictApi;
import com.jims.phstock.entity.DrugExportClassDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 出库分类字典类别的维护
 * Created by luohk on 2016/5/10.
 */
@Component
@Produces("application/json")
@Path("drug-export")
public class DrugExportClassDictRest {
    @Reference(version = "1.0.0")
    private DrugExportClassDictApi drugExportClassDictApi;

    @Path("findAll")
    @GET
    public List<DrugExportClassDict> findAllList() {
        return drugExportClassDictApi.findAllList();
    }

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    @Path("merge")
    @POST
    public StringData merge(BeanChangeVo<DrugExportClassDict> beanChangeVo) {
        String num = drugExportClassDictApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    @Path("save")
    @POST
    public StringData save(List<DrugExportClassDict> list) {
        int saveResult = 0;
        for (int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
            DrugExportClassDict drugExportClassDict = list.get(i);
            String num = drugExportClassDictApi.save(drugExportClassDict);
            saveResult += Integer.valueOf(num);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(saveResult));
        stringData.setData("success");
        return stringData;
    }

    @Path("delete")
    @POST
    public StringData delete(String id) {
        String num = drugExportClassDictApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    @Path("findList")
    @GET
    public List<DrugExportClassDict> findList(@QueryParam("storageType")String storageType){
        DrugExportClassDict param = new DrugExportClassDict();
        param.setStorageType(storageType);
        return drugExportClassDictApi.findList(param);
    }
}
