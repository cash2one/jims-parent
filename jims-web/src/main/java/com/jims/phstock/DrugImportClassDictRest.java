package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugImportClassDictApi;
import com.jims.phstock.entity.DrugImportClassDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 入库分类字典类别的维护
 * Created by luohk on 2016/5/10.
 */
@Component
@Produces("application/json")
@Path("drug-import")
public class DrugImportClassDictRest {

    @Reference(version = "1.0.0")
    private DrugImportClassDictApi drugImportClassDictApi;

    @Path("findAll")
    @GET
    public List<DrugImportClassDict> findAllList(){
        return drugImportClassDictApi.findAllList();
    }

    @Path("findList")
    @GET
    public List<DrugImportClassDict> findList(@QueryParam("storageType")String storageType){
        DrugImportClassDict param = new DrugImportClassDict();
        param.setStorageType(storageType);
        return drugImportClassDictApi.findList(param);
    }

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    @Path("merge")
    @POST
    public StringData merge(BeanChangeVo<DrugImportClassDict> beanChangeVo) {
        String num = drugImportClassDictApi.merge(beanChangeVo);
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
    public StringData save(List<DrugImportClassDict> list){
        int saveResult = 0;
        for(int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
            DrugImportClassDict drugImportClassDict = list.get(i);
            String num  = drugImportClassDictApi.save(drugImportClassDict);
            saveResult += Integer.valueOf(num);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(saveResult));
        stringData.setData("success");
        return stringData;
    }

    @Path("delete")
    @POST
    public StringData delete(String id){
        String num = drugImportClassDictApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
