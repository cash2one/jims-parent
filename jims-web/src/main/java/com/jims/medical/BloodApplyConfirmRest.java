package com.jims.medical;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.blood.api.BloodApplyServiceApi;
import com.jims.blood.entity.BloodApply;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 门诊用血申请
 *
 * @author PangQian
 * @date2016/7/7 0007
 */
@Component
@Produces("application/json")
@Path("bloodConfirm")
public class BloodApplyConfirmRest {

    @Reference(version = "1.0.0")
    private BloodApplyServiceApi bloodApplyServiceApi;

    /**
     * 查询用血
     * @author pq
     * @return
     */
    @GET
    @Path("findBlood")
    public List<BloodApply> findBlood(){
        BloodApply bloodApply = new BloodApply();
        Page<BloodApply>  bloodApplyPage =bloodApplyServiceApi.findPage(new Page<BloodApply>(), bloodApply);
        return  bloodApplyPage.getList();
    }

    /**
     * 用血确认
     * @author pq
     * @param bloodApplies
     * @return
     */
    @POST
    @Path("confirmBlood")
    public StringData confirmBlood(List<BloodApply> bloodApplies){
        StringData stringData = new StringData();
        String code =   bloodApplyServiceApi.confirmBlood(bloodApplies);
        stringData.setCode(code);
        if(!"".equals(code)&&!"0".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }

        return stringData;
    }


}
