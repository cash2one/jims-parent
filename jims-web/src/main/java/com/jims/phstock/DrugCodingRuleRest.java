package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugCodingRuleApi;
import com.jims.phstock.entity.DrugCodingRule;
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
@Path("drug-code")
public class DrugCodingRuleRest {

    @Reference(version = "1.0.0")
    private DrugCodingRuleApi drugCodingRuleApi;

    @Path("findAll")
    @GET
    public List<DrugCodingRule> findAllList() {
        return drugCodingRuleApi.findAllList();
    }

    @Path("save")
    @POST
    public StringData save(List<DrugCodingRule> list) {
        int saveResult = 0;
        for (int i = 0, j = (list != null ? list.size() : 0); i < j; i++) {
            DrugCodingRule drugCodingRule = list.get(i);
            String num = drugCodingRuleApi.save(drugCodingRule);
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
        String num = drugCodingRuleApi.delete(id);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    //通过编码名称获取编码长度
    @Path("findLevel")
    @GET
    public StringData findLevelWidth(String className){
        DrugCodingRule num =drugCodingRuleApi.findLevelWidth(className);
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num.getLevelWidth()));
        stringData.setData("success");
        return stringData;
    }
}
