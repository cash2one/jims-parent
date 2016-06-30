package com.jims.nurse;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.PatsInHospital;
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
     * @param bedRec
     * @author pq
     * @return
     */
    @Path("getAllBed")
    @POST
    public List<BaseDto> getAllBed(@QueryParam("wardCode")String wardCode){
        BedRec bedRec = new BedRec();
        bedRec.setWardCode(wardCode);
        return  bedRecServiceApi.getAllBed(bedRec);
    }

    /**
     * 删除病床
     * @param ids
     * @author pq
     * @return
     */
    @Path("delete")
    @POST
    public StringData delete(String ids){
        StringData stringData = new StringData();
        String num=bedRecServiceApi.delete(ids);
        stringData.setCode(num);
        if(num !=null && !"0".equals(num)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 已经分配了床位的在院病人列表
     * @param wardCode
     * @author pq
     * @return
     */
    @Path("getInPat")
    @GET
    public  List<BaseDto> getInPat(@QueryParam("wardCode")String wardCode){
        BedRec bedRec = new BedRec();
        bedRec.setWardCode(wardCode);
        return bedRecServiceApi.getInPat(bedRec);
    }

    /**
     * 根据床号和病区查找单条信息
     * @param bedRec
     * @author pq
     * @return
     */
    @Path("getOneBed")
    @POST
    public  BaseDto getOneBed(BedRec bedRec){
        return  bedRecServiceApi.getInPatOne(bedRec);
    }

    /**
     * 包床
     * @param bedRec
     * @author pq
     * @return
     */
    @Path("packBed")
    @POST
    public StringData packBed(List<BedRec> bedRec){
        StringData stringData = new StringData();
        String num=bedRecServiceApi.packBed(bedRec);
        stringData.setCode(num);
        if(num !=null && !"0".equals(num)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    @Path("findList")
    @GET
    public List<BedRec> findList(@QueryParam("wardCode")String wardCode,@QueryParam("bedStatus")String bedStatus,@QueryParam("patientId")String patientId){
        BedRec bedRec = new BedRec();
        bedRec.setWardCode(wardCode);
        bedRec.setBedStatus(bedStatus);
        bedRec.setPatientId(patientId);
      return bedRecServiceApi.findList(bedRec);
    }

    /**
     * 换床
     * @param bedRec
     * @author pq
     * @return
     */
    @Path("changeBed")
    @POST
    public StringData changeBed( BedRec bedRec){
        PatsInHospital  patsInHospital = new PatsInHospital();
        patsInHospital.setPatientId(bedRec.getPatientId());
        patsInHospital.setBedNo(bedRec.getOldBedNo());
        StringData stringData = new StringData();
        String num = bedRecServiceApi.changeBed(bedRec);
        stringData.setCode(num);
        if(num !=null && !"0".equals(num)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 判断 病区 下的床位号的唯一性
     * @param bedRec
     * @author pq
     * @return
     */
    @Path("judgeBedNo")
    @POST
    public Boolean judgeBedNo(BedRec bedRec){
      return  bedRecServiceApi.judgeBedNo(bedRec.getBedNo(),bedRec.getWardCode());
    }


    /**
     * 通过visitId 拿到在医院病人的在院信息
     * @param bedRec
     * @return
     */
    @Path("getInPats")
    @POST
    public List<BaseDto> getInPats(BedRec bedRec){
        return  bedRecServiceApi.getAllBed(bedRec);
    }


    /**
     * 查询床位相关费用
     * @author pq
     * @param itemClass
     * @return
     */
    @Path("findBedPrice")
    @GET
    public List<BaseDto> findBedPrice(@QueryParam("itemClass")String itemClass){
        return bedRecServiceApi.findBedPrice(itemClass);
    }
}
