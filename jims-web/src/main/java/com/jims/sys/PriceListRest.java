package com.jims.sys;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.vo.PriceListVo;
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
 * Created by wei on 2016/5/5.
 */
@Component
@Produces("application/json")
@Path("price-list")
public class PriceListRest {
    @Reference(version = "1.0.0")
    private PriceListApi priceListApi;

    @Path("list-now")
    @GET
    public PageData list(@QueryParam("orgId")String orgId,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        Page<PriceListVo> page= priceListApi.findPage(orgId,new Page<PriceListVo>(request, response), new PriceListVo());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }


    @Path("list-all")
    @GET
    public PageData OLdList(@QueryParam("orgId")String orgId,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        Page<PriceListVo> page= priceListApi.findOLdPage(orgId,new Page<PriceListVo>(request, response), new PriceListVo());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    @GET
    @Path("find-by-input-code-now")
    public List<PriceListVo> getNow(@QueryParam("orgId")String orgId,@QueryParam("inputCode")String inputCode,@QueryParam("label")String label){
        List<PriceListVo> priceListVo = priceListApi.getInputCodeNow(orgId,inputCode,label);
        return priceListVo;
    }
    @GET
    @Path("find-by-input-code-old")
    public List<PriceListVo> getOld(@QueryParam("orgId")String orgId,@QueryParam("inputCode")String inputCode,@QueryParam("label")String label){
        List<PriceListVo> priceListVo = priceListApi.getInputCodeOld(orgId,inputCode,label);
        return priceListVo;
    }
    @Path("list")
    @GET
    public List<PriceListVo> list(){
        return priceListApi.list();
    }

    /**
     * 根据诊疗项目获取诊疗项目所对应的价表项目
     * @param orgId
     * @param clinicItemCode
     * @return
     */
    @GET
    @Path("list-by-clinic-code")
    public List<PriceListVo> getListByClinicItemCodeAndOrgId(@QueryParam("orgId")String orgId,@QueryParam("clinicItemCode")String clinicItemCode){
        return priceListApi.getListByClinicItemCodeAndOrgId(orgId, clinicItemCode);
    }



}
