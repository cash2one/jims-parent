package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.AbbreviationUtils;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

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
        if(priceDictListVo.getClinicDict() == 1){
            clinicItemApi.saveDictList(priceDictListVo);
        }
        String num = priceListApi.save(priceDictListVo);
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    @Path("code/{transcode}")
    @GET
    public StringData Transcoding(@PathParam("transcode") String transcode) {
        System.out.print(transcode);
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
}
