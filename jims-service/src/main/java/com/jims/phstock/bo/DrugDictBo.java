package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.dao.DrugDictDao;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lgx on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugDictBo extends CrudImplService<DrugDictDao, DrugDict> {

    @Autowired
    private DrugNameDictDao drugNameDictDao;

    @Autowired
    private DrugCodingRuleDao drugCodingRuleDao;


    /**
     * 根据商品亚类 药品剂型,序号长度生成药品代码drug_code
     * @param secondType
     * @param drugForm
     * @param numLength
     * @return
     * @author txb
     */
    public String getDrugCodeByRule(String secondType, String drugForm,String numLength) {
        numLength = drugCodingRuleDao.findLevelWidth("药品序号").getLevelWidth().toString();
        String goodsNameLength = drugCodingRuleDao.findLevelWidth("商品名称").getLevelWidth().toString();

        String drugCode = dao.getDrugCodeByRule(secondType,drugForm,numLength);
        for (int i = 0 ; i < Integer.parseInt(goodsNameLength) ; i++){
            drugCode = drugCode + '0';
        }

        return drugCode;
    }

    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    public List<DrugDict> listDrugDictByDrugCode(String drugCode) {
        return dao.listDrugDictByDrugCode(drugCode);
    }

    public void saveDrugCatalog(DrugCatalogBeanVo drugCatalogBeanVo) {
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
    }
}
