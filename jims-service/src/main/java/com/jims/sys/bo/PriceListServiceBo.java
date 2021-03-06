package com.jims.sys.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.IdGen;
import com.jims.sys.dao.PriceItemNameDictDao;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.PriceItemNameDict;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import com.jims.sys.vo.PriceListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class PriceListServiceBo extends CrudImplService<PriceListDao, PriceList> {

    @Autowired
    private PriceItemNameDictDao priceItemNameDictDao;
    /**
     * 价表的保存
     *
     * @param dictListVo
     * @return
     */
    @Transactional
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
        int j = dao.insert(priceList);
        if (i * j == 1) {
            return "1";
        }
        return "0";
    }

    /**
     * 查询序列
     *
     * @return
     */
    public String findSeqences() {
        return dao.findSeqences();
    }

    /**
     * 通过拼音码查询数据
     *
     * @param inputCode
     * @return
     */
    public List<PriceList> findCode(String inputCode) {
        return dao.findCode(inputCode);
    }

    /**
     * 现行价格表
     *
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findPage(String orgId, Page<PriceListVo> page, PriceListVo priceListVo) {
        priceListVo.setPage(page);
        page.setList(dao.findPriceList(orgId, priceListVo));
        return page;
    }

    /**
     * 历史价格表
     *
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findOLdPage(String orgId, Page<PriceListVo> page, PriceListVo priceListVo) {
        priceListVo.setPage(page);
        page.setList(dao.findOLdPriceList(orgId, priceListVo));
        return page;
    }

    /**
     * 拼音码查询现行价表
     *
     * @param inputCode
     * @param label
     * @return
     * @author wei
     */
    public List<PriceListVo> getInputCodeNow(String orgId, String inputCode, String label) {
        List<PriceListVo> list = dao.getInputCodeNow(orgId, inputCode, label);
        return list;
    }

    /**
     * 拼音码查询历史价表
     *
     * @param orgId
     * @param inputCode
     * @param label
     * @return
     */
    public List<PriceListVo> getInputCodeOld(String orgId, String inputCode, String label) {
        List<PriceListVo> list = dao.getInputCodeOld(orgId, inputCode, label);
        return list;
    }

    /**
     * 下拉框查询药品类别
     *
     * @return
     * @author wei
     */
    public List<PriceListVo> list() {
        return dao.list();
    }


    /**
     * 根据诊疗项目获取诊疗项目所对应的价表项目
     *
     * @param orgId
     * @param clinicItemCode
     * @return
     */
    public List<PriceListVo> getListByClinicItemCodeAndOrgId(String orgId, String clinicItemCode) {
        return dao.listByClinicItemCodeAndOrgId(orgId, clinicItemCode);
    }
}
