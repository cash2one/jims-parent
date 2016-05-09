package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.IdGen;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.dao.PriceItemNameDictDao;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.PriceItemNameDict;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 价格表Service
 *
 * @author 罗海昆
 * @version 2016-04-26
 */
@Service(version = "1.0.0")
@Transactional
public class PriceListImpl extends CrudImplService<PriceListDao, PriceList> implements PriceListApi {

    @Autowired
    private PriceItemNameDictDao priceItemNameDictDao;
    @Autowired
    private PriceListDao priceListDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String save(PriceDictListVo dictListVo) {
        PriceItemNameDict priceItemNameDict = new PriceItemNameDict();
        priceItemNameDict.setId(IdGen.uuid());
        priceItemNameDict.setItemClass(dictListVo.getItemClass());
        priceItemNameDict.setItemName(dictListVo.getItemName());
        priceItemNameDict.setItemCode(dictListVo.getItemCode());
        priceItemNameDict.setInputCode(dictListVo.getInputCode());
        priceItemNameDict.setMemo(dictListVo.getMemo());
        priceItemNameDict.setStdIndicator(1);

        PriceList priceList = new PriceList();
        priceList.setId(IdGen.uuid());
        priceList.setItemClass(dictListVo.getItemClass());
        priceList.setItemName(dictListVo.getItemName());
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
        priceList.setStartDate(DateUtils.parseDate(dictListVo.getStartDate()));
        priceList.setMaterialCode(dictListVo.getInputCode());
        priceList.setInputCode(dictListVo.getInputCode());
        priceList.setMaterialCode(dictListVo.getMaterialCode());

        int i = priceItemNameDictDao.insert(priceItemNameDict);
        int j = priceListDao.insert(priceList);
        if (i * j == 1) {
            return "1";
        }
        return "0";
    }

    public String findSeqences() {
        return priceListDao.findSeqences();
    }

}