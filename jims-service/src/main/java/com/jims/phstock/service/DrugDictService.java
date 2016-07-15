/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.common.utils.StringUtils;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.bo.DrugDictBo;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.dao.DrugDictDao;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugCodingRule;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;


/**
 * 药品字典Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
public class DrugDictService implements DrugDictServiceApi {

    @Autowired
    private DrugDictBo bo;

    @Override
    public String save(DrugDict drugDict) {
        String result = "0";
        try {
            bo.save(drugDict);
            result = "1";
        } catch (RuntimeException e){}
        return result;
    }

    @Override
    public Page<DrugDict> findPage(Page<DrugDict> page, DrugDict drugDict) {
        return bo.findPage(page,drugDict);
    }

    @Override
    public List<DrugDict> findList(DrugDict drugDict) {
        return bo.findList(drugDict);
    }

    /**
     * 根据药品名称或药品代码查询数据
     * @return
     * @author fengyuguang
     */
    public List<DrugDict> getByName(String drugCode,String drugName){
        return bo.getByName(drugCode,drugName);
    }

    @Override
    public DrugDict get(String id) {
        return bo.get(id);
    }

    @Override
    public String delete(String ids) {
        String result = "0";
        try {
            bo.delete(ids);
            result = "1";
        } catch (RuntimeException e){}
        return result;
    }

    /**
     * 根据商品亚类 药品剂型,序号长度生成药品代码drug_code
     * @param secondType
     * @param drugForm
     * @param numLength
     * @return
     * @author txb
     */
    @Override
    public String getDrugCodeByRule(String secondType, String drugForm,String numLength) {
        return bo.getDrugCodeByRule(secondType,drugForm,numLength);
    }

    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    @Override
    public List<DrugDict> listDrugDictByDrugCode(String drugCode) {
        return bo.listDrugDictByDrugCode(drugCode);
    }

    @Override
    public String saveDrugCatalog(DrugCatalogBeanVo drugCatalogBeanVo) {
        String result = "0";
        try {
            bo.saveDrugCatalog(drugCatalogBeanVo);
            result = "1";
        } catch (RuntimeException e){}
        return result;
    }
}