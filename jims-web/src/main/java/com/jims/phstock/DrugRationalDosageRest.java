package com.jims.phstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.phstock.api.DrugRationalDosageApi;
import com.jims.phstock.entity.DrugRationalDosage;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 药品用量信息Rest
 * Created by fyg on 2016/7/12.
 */
@Component
@Produces("application/json")
@Path("drug-rational-dosage")
public class DrugRationalDosageRest {

    @Reference(version = "1.0.0")
    private DrugRationalDosageApi drugRationalDosageApi;

    /**
     * 查询所有药品用量信息情况
     * @return
     * @author fengyuguang
     */
    @Path("list")
    @GET
    public List<DrugRationalDosage> findAll(){
        return drugRationalDosageApi.findAll();
    }

    /**
     * 根据药品代码查询药品用量信息
     * @param drugCode 药品代码
     * @return
     * @author fengyuguang
     */
    @Path("list-by-drugCode")
    @GET
    public List<DrugRationalDosage> getListByDrugCode(@QueryParam("drugCode")String drugCode){
        return drugRationalDosageApi.getListByDrugCode(drugCode);
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<DrugRationalDosage> beanChangeVo) {
        String num = drugRationalDosageApi.merge(beanChangeVo);
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
