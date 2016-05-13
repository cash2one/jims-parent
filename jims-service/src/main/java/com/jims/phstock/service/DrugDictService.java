/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.dao.DrugDictDao;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 药品字典Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugDictService extends CrudImplService<DrugDictDao, DrugDict> implements DrugDictServiceApi {

    @Autowired
    private DrugNameDictDao drugNameDictDao;

    @Override
    public String getDrugCodeByRule(String secondType, String drugForm) {
        return null;
    }

    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    @Override
    public List<DrugDict> listDrugDictByDrugCode(String drugCode) {
        return dao.listDrugDictByDrugCode(drugCode);
    }

    @Override
    @Transactional(readOnly = true)
    public String saveDrugCatalog(DrugCatalogBeanVo drugCatalogBeanVo) {
        DrugCatalogChangeVo<DrugNameDict> drugNameDictList = drugCatalogBeanVo.getDrugNameDictDrugCatalogBeanVo();
        DrugCatalogChangeVo<DrugDict> drugDictList = drugCatalogBeanVo.getDrugDictDrugCatalogBeanVo();

        //药品名称字典实体改变
        if (drugNameDictList != null){
            List<DrugNameDict> insertNameDicts = drugNameDictList.getInserted();
            List<DrugNameDict> updateNameDicts = drugNameDictList.getUpdated();
            List<DrugNameDict> deleteNameDicts = drugNameDictList.getDeleted();

            if (insertNameDicts != null && insertNameDicts.size() > 0) {
                for (DrugNameDict nameDict : insertNameDicts) {
                    nameDict.setInputCode(PinYin2Abbreviation.cn2py(nameDict.getDrugName()));
                    nameDict.preInsert();
                    drugNameDictDao.insert(nameDict);

                }
            }
            if (updateNameDicts != null && updateNameDicts.size() > 0) {
                for (DrugNameDict nameDict : updateNameDicts) {
                    nameDict.setInputCode(PinYin2Abbreviation.cn2py(nameDict.getDrugName()));
                    nameDict.preUpdate();
                    drugNameDictDao.update(nameDict);
                }
            }
            if (deleteNameDicts != null && deleteNameDicts.size() > 0) {
                for (DrugNameDict nameDict : deleteNameDicts) {
                    drugNameDictDao.delete(nameDict);
                }
            }
        }
        //药品字典实体改变
        if (drugDictList != null){
            List<DrugDict> insertDicts = drugDictList.getInserted();
            List<DrugDict> updateDicts = drugDictList.getUpdated();
            List<DrugDict> deleteDicts = drugDictList.getDeleted();

            if (insertDicts != null && insertDicts.size() > 0) {
                for (DrugDict dict : insertDicts) {
                    dict.setInputCode(PinYin2Abbreviation.cn2py(dict.getDrugName()));
                    dict.preInsert();
                    dao.insert(dict);
                }
            }
            if (updateDicts != null && updateDicts.size() > 0) {
                for (DrugDict dict : updateDicts) {
                    dict.setInputCode(PinYin2Abbreviation.cn2py(dict.getDrugName()));
                    dict.preUpdate();
                    dao.update(dict);
                }
            }
            if (deleteDicts != null && deleteDicts.size() > 0) {
                for (DrugDict dict : deleteDicts) {
                    dao.delete(dict);
                }
            }
        }


        return "1";
    }
}