package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugDispPropertyDictApi;
import com.jims.phstock.entity.DrugDispPropertyDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
@Component
@Produces("application/json")
@Path("drug-disp")
public class DrugDispPropertyDictRest {

    @Reference(version = "1.0.0")
    private DrugDispPropertyDictApi drugDispPropertyDictApi;

    @Path("findAll")
    @GET
    public List<DrugDispPropertyDict> findAllList() {
        return drugDispPropertyDictApi.findAllList();
    }

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    @Path("merge")
    @POST
    public StringData merge(BeanChangeVo<DrugDispPropertyDict> beanChangeVo) {
        String num = drugDispPropertyDictApi.merge(beanChangeVo);
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
    public StringData save(List<DrugDispPropertyDict> list) {
        int saveResult = 0;
        for (int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
            DrugDispPropertyDict drugDispPropertyDict = list.get(i);
            String num = drugDispPropertyDictApi.save(drugDispPropertyDict);
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
        String num = drugDispPropertyDictApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
