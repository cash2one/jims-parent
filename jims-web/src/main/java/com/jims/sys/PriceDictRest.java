package com.jims.sys;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ClinicItemApi;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.AbbreviationUtils;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
@Component
@Produces("application/json")
@Path("price")
public class PriceDictRest {

    @Reference(version = "1.0.0")
    private PriceListApi priceListApi;
    @Reference(version = "1.0.0")
    private ClinicItemApi clinicItemApi;

    /**
     * 保存价表
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    @Path("save")
    @POST
    public StringData save(PriceDictListVo priceDictListVo) {
        StringData stringData = new StringData();
        if (priceDictListVo.getItemClass() == null) {
            stringData.setCode("项目类别不能为空");
            return stringData;
        } else if (priceDictListVo.getItemCode() == null) {
            stringData.setCode("项目代码不能为空");
            return stringData;
        } else if (priceDictListVo.getItemSpec() == null) {
            stringData.setCode("项目规格不能为空");
            return stringData;
        } else if (priceDictListVo.getUnits() == null) {
            stringData.setCode("计价单位不能为空");
            return stringData;
        } else if (priceDictListVo.getItemName() == null) {
            stringData.setCode("项目名称不能为空");
            return stringData;
        } else if (priceDictListVo.getStartDate() == null) {
            stringData.setCode("起用日期不能为空");
            return stringData;
        }
        String code = priceListApi.saveData(priceDictListVo);
        stringData.setCode(code);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 修改价表
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    @Path("update-price")
    @POST
    public StringData updatePrice(PriceDictListVo priceDictListVo){
        StringData stringData = new StringData();
        String code = priceListApi.updatePrice(priceDictListVo);
        stringData.setCode(code);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 保存调价
     * @param priceDictListVo
     * @return
     * @author fengyuguang
     */
    @Path("save-adjust-price")
    @POST
    public StringData saveAdjustPrice(PriceDictListVo priceDictListVo){
        StringData stringData = new StringData();
        String code = priceListApi.saveAdjustPrice(priceDictListVo);
        stringData.setCode(code);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 根据类别查询价表
     * @param itemClass 类别
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    @Path("find-by-item-class")
    @GET
    public List<PriceList> findByItemClass(@QueryParam("itemClass")String itemClass,@QueryParam("orgId")String orgId){
        return priceListApi.findByItemClass(itemClass,orgId);
    }

    /**
     * 根据输入码查询价表数据
     * @param inputCode  输入码
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    @Path("get-by-inputCode")
    @GET
    public List<PriceList> getByInputCode(@QueryParam("inputCode")String inputCode,@QueryParam("orgId")String orgId){
        if(inputCode != null || inputCode != ""){
            return priceListApi.getByInputCode(inputCode, orgId);
        }
        return null;
    }

    @Path("code/{transcode}")
    @GET
    public StringData Transcoding(@PathParam("transcode") String transcode) {
        String res = priceListApi.findSeqences();
        if (res.length() <= 1) {
            res = "00" + res + transcode;
        } else if (res.length() == 2) {
            res = "0" + res + transcode;
        } else if (res.length() == 3) {
            res = res + transcode;
        }
        StringData stringData = new StringData();
        stringData.setData(res);
        return stringData;
    }

    @Path("abb/{code}")
    @GET
    public StringData Abbreviation(@PathParam("code") String code) {
        String res = AbbreviationUtils.cn2py(code);
        if(res.length() > 8){
            res = res.substring(0, 8);
        }
        StringData stringData = new StringData();
        stringData.setCode(res);
        stringData.setData("success");
        return stringData;
    }

    @Path("find")
    @GET
    public PageData findPrice(@Context HttpServletRequest request, @Context HttpServletResponse response){
        Page<PriceList> page = priceListApi.findPage(new Page<PriceList>(request, response), new PriceList());
        PageData<PriceList> pageData = new PageData<PriceList>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    @Path("get")
    @POST
    public PriceList get(String id){
        PriceList priceList=  priceListApi.get(id);
        return priceList;
    }

    @Path("del")
    @POST
    public StringData delete(String id) {
        StringData stringData = new StringData();
        String num= priceListApi.delete(id);
        stringData.setCode(num + "");
        stringData.setData("success");
        return stringData;
    }

    @Path("find/{inputCode}")
    @GET
    public List<PriceList> findList(@PathParam("inputCode")String inputCode){
        List<PriceList> priceLists = priceListApi.findCode(inputCode+"%");
        return priceLists;
    }



    @POST
    @Path("findList")
    public List<PriceList> findList(PriceList entity){
        return priceListApi.findList(entity);
    }

    @GET
    @Path("findListWithLimit")
    public List<PriceList> findListWithLimit(@QueryParam("itemClass") String itemClass,
                                             @QueryParam("orgId") String orgId,
                                             @QueryParam("priceType") String priceType,
                                             @QueryParam("q") String q,
                                             @QueryParam("start") Integer start,
                                             @QueryParam("limit") Integer limit){

        PriceList entity = new PriceList();
        entity.setItemClass(itemClass);
        entity.setOrgId(orgId);
        entity.setPriceType(priceType);
        entity.setQ(q);
        List<PriceList> list = priceListApi.findList(entity);
        start = start == null || start < 0 ? 0 : start;
        limit = limit == null || limit < 0 ? list.size() : (start + limit < list.size() ? limit : list.size()-start);
        return list.subList(start,limit);
    }


}
