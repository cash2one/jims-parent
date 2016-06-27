package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.api.PerformDefaultScheduleApi;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.PerformDefaultSchedule;
import com.jims.sys.entity.PerformFreqDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 医嘱执行缺省时间 rest
 * Created by lgx on 2016/6/21.
 */
@Component
@Produces("application/json")
@Path("performDefaultSchedule")
public class PerformDefaultScheduleRest {

    @Reference(version = "1.0.0")
    private PerformDefaultScheduleApi api;

    @GET
    @Path("findList")
    public List<PerformDefaultSchedule> findList(@QueryParam("freqDesc")String freqDesc,
                                                 @QueryParam("administration")String administration){

        PerformDefaultSchedule param = new PerformDefaultSchedule();
        param.setFreqDesc(freqDesc);
        param.setAdministration(administration);
        return api.findList(param);
    }

    @POST
    @Path("save")
    public String save(PerformDefaultSchedule performDefaultSchedule){
        return api.save(performDefaultSchedule);
    }

    /**
     * 取以后的频次或给药途径
     * @param type freqDesc
     * @return
     */
    @GET
    @Path("findTypeList")
    public List<PerformDefaultSchedule> findTypeList(@QueryParam("type")String type){
        return api.findTypeList(type);
    }

    /**
     * 删除
     * @param ids 删除的ID,多个以逗号（,）隔开
     * @return 0失败，1成功
     */
    @GET
    @Path("delete")
    public String delete(@QueryParam("ids")String ids){
        return api.delete(ids);
    }

    /**
     * 检索某给药途径未添加的频次
     * @param administration
     * @return
     */
    @GET
    @Path("findNoExistFreq")
    public List<PerformFreqDict> findNoExistFreq(@QueryParam("administration") String administration) {
        return api.findNoExistFreq(administration);
    }

    /**
     * 检索某频次未添加的给药途径
     * @param freqDesc
     * @return
     */
    @GET
    @Path("findNoExistAdministration")
    public List<AdministrationDict> findNoExistAdministration(@QueryParam("freqDesc")String freqDesc){
        return api.findNoExistAdministration(freqDesc);
    }
}
