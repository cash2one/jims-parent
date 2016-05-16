package com.jims.phstock.vo;

import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 药品目录封装Vo
 *
 * @author tangxb
 * @version 2016-05-12
 */
@XmlRootElement
public class DrugCatalogBeanVo<T> implements Serializable {
    private DrugCatalogChangeVo<DrugNameDict> drugNameDictDrugCatalogBeanVo;//药品名称字典vo
    private DrugCatalogChangeVo<DrugDict> drugDictDrugCatalogBeanVo ;//药品字典vo

    public DrugCatalogBeanVo() {
    }

    public DrugCatalogBeanVo(DrugCatalogChangeVo<DrugNameDict> drugNameDictDrugCatalogBeanVo, DrugCatalogChangeVo<DrugDict> drugDictDrugCatalogBeanVo) {
        this.drugNameDictDrugCatalogBeanVo = drugNameDictDrugCatalogBeanVo;
        this.drugDictDrugCatalogBeanVo = drugDictDrugCatalogBeanVo;
    }

    public DrugCatalogChangeVo<DrugNameDict> getDrugNameDictDrugCatalogBeanVo() {
        return drugNameDictDrugCatalogBeanVo;
    }

    public void setDrugNameDictDrugCatalogBeanVo(DrugCatalogChangeVo<DrugNameDict> drugNameDictDrugCatalogBeanVo) {
        this.drugNameDictDrugCatalogBeanVo = drugNameDictDrugCatalogBeanVo;
    }

    public DrugCatalogChangeVo<DrugDict> getDrugDictDrugCatalogBeanVo() {
        return drugDictDrugCatalogBeanVo;
    }

    public void setDrugDictDrugCatalogBeanVo(DrugCatalogChangeVo<DrugDict> drugDictDrugCatalogBeanVo) {
        this.drugDictDrugCatalogBeanVo = drugDictDrugCatalogBeanVo;
    }
};

