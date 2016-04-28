package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.PriceItemNameDictApi;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.entity.PriceItemNameDict;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
        if(num.equals("1") && num1.equals("1")){
            stringData.setCode("1");
            stringData.setData("success");
            return stringData;
        } else {
            stringData.setCode("0");
            stringData.setData("false");
        }
        return stringData;
    }
}
