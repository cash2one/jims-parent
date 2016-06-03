package com.jims.nurse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.web.impl.BaseDto;
import com.jims.nurse.api.BedRecServiceApi;
import com.jims.nurse.entity.BedRec;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 床位记录
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
@Component
@Produces("application/json")
@Path("bedRec")
public class BedRecRest {
    @Reference(version = "1.0.0")
    private BedRecServiceApi bedRecServiceApi;

    /**
     * 方法 findBedInfo 的功能描述
     * 查询床位记录
     * @param request
     * @param response
     * @param wardCode
     * @return
     * @author pq
     * @date 2016/6/3 0003
     */
    @Path("findBedInfo")
    @GET
    public List<BedRec>  findBedInfo(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("wardCode")String wardCode){
        BedRec bedRec=new BedRec();
        bedRec.setWardCode(wardCode);
       Page<BedRec>  bedRecPage =  bedRecServiceApi.findPage(new Page<BedRec>(request, response), bedRec);
        return  bedRecPage.getList();
    }

    /**
     * 保存床位信息
     * @param bedRecList
     * @author pq
     * @return
     */
    @Path("save")
    @POST
    public StringData saveBed(List<BedRec> bedRecList){
        StringData stringData = new StringData();
        String code =   bedRecServiceApi.saveBed(bedRecList);
        stringData.setCode(code);
        if(code !=null && !"0".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 查询病区下所有的床位信息
     * @param wardCode
     * @author pq
     * @return
     */
    @Path("getAllBed")
    @GET
    public List<BaseDto> getAllBed(String wardCode){
        return  bedRecServiceApi.getAllBed(wardCode);
    }

}
