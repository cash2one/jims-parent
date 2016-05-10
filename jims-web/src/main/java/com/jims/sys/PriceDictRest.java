package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.AbbreviationUtils;
import com.jims.sys.api.PriceItemNameDictApi;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.entity.PriceItemNameDict;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
@Component
@Produces("application/json")
@Path("price")
public class PriceDictRest {

    @Reference(version = "1.0.0")
    private PriceItemNameDictApi priceItemNameDictApi;
    @Reference(version = "1.0.0")
    private PriceListApi priceListApi;
    @Path("save")
    @POST
    public StringData save(PriceDictListVo dictListVo){
        PriceItemNameDict priceItemNameDict = new PriceItemNameDict();
        priceItemNameDict.setItemClass(dictListVo.getItemClass());
        priceItemNameDict.setItemName(dictListVo.getItemName());
        priceItemNameDict.setItemCode(dictListVo.getItemCode());
        priceItemNameDict.setInputCode(dictListVo.getInputCode());
        priceItemNameDict.setMemo(dictListVo.getMemo());
        String num = priceItemNameDictApi.save(priceItemNameDict);
        PriceList priceList = new PriceList();
        priceList.setItemClass(dictListVo.getItemClass());
        priceList.setItemCode(dictListVo.getItemCode());
        priceList.setItemCode(dictListVo.getItemCode());
        priceList.setItemSpec(dictListVo.getItemSpec());
        priceList.setUnits(dictListVo.getUnits());
        priceList.setPrice(dictListVo.getPrice());
        priceList.setPreferPrice(dictListVo.getPreferPrice());
        priceList.setForeignerPrice(dictListVo.getForeignerPrice());
        priceList.setPerformedBy(dictListVo.getPerformedBy());
        priceList.setFeeTypeMask(dictListVo.getFeeTypeMask());
        priceList.setClassOnInpRcpt(dictListVo.getClassOnInpRcpt());
        priceList.setClassOnOutpRcpt(dictListVo.getClassOnOutpRcpt());
        priceList.setClassOnReckoning(dictListVo.getClassOnReckoning());
        priceList.setSubjCode(dictListVo.getSubjCode());
        priceList.setClassOnMr(dictListVo.getClassOnMr());
        priceList.setMemo(dictListVo.getMemo());
        priceList.setStartDate(dictListVo.getStartDate());
        priceList.setMaterialCode(dictListVo.getInputCode());
        priceList.setInputCode(dictListVo.getInputCode());
        String num1 = priceListApi.save(priceList);
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



    @POST
    @Path("findList")
    public List<PriceList> findList(PriceList entity){
        return priceListApi.findList(entity);
    }
}
