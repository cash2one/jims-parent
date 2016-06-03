package com.jims.nurse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.persistence.Page;
import com.jims.nurse.api.BedRecServiceApi;
import com.jims.nurse.entity.BedRec;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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




}
