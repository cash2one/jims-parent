package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugSupplierCatalogApi;
import com.jims.phstock.bo.DrugSupplierCatalogBo;
import com.jims.phstock.entity.DrugSupplierCatalog;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by wei on 2016/5/10.
 */
@Service(version = "1.0.0")
public class DrugSupplierCatalogServiceImpl implements DrugSupplierCatalogApi {
    @Autowired
    private DrugSupplierCatalogBo drugSupplierCatalogBo;

    @Override
    public String delete(String ids) {
        return drugSupplierCatalogBo.delete(ids);
    }

    /**
     * 查询所有供货商类别
     *
     * @param orgId
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> list(String orgId) {
        List<DrugSupplierCatalog> list = drugSupplierCatalogBo.list(orgId);
        return list;
    }

    @Override
    public String save(DrugSupplierCatalog drugSupplierCatalog) {
        return drugSupplierCatalogBo.save(drugSupplierCatalog);
    }


    public String update(DrugSupplierCatalog drugSupplierCatalog) {
        return drugSupplierCatalogBo.update(drugSupplierCatalog);
    }

    /**
     * 查询全部
     *
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> findList(String orgId) {
        return drugSupplierCatalogBo.findList(orgId,null);
    }

    /**
     * 查询全部
     * @param q 拼音码、厂商名
     * @return
     */
    @Override
    public List<DrugSupplierCatalog> findList(String orgId, String q) {
        return drugSupplierCatalogBo.findList(orgId,q);
    }

    /**
     * 根据拼音码查询所属组织机构的供应商和生产商
     *
     * @param orgId     组织机构ID
     * @param inputCode 拼音字头
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> listDrugSupplierCatalogByInputCode(String orgId, String inputCode) {
        return drugSupplierCatalogBo.listDrugSupplierCatalogByInputCode(orgId, inputCode);
    }

    /**
     * 根据厂商类别查询所属组织机构的生产商或供应商
     *
     * @param orgId        组织机构ID
     * @param supplierType 厂商类别：生厂商、供应商
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> listDrugSupplierCatalogBySupplierType(String orgId, String supplierType) {
        return drugSupplierCatalogBo.listDrugSupplierCatalogBySupplierType(orgId, supplierType);

    }

    /**
     *查询全部单位
     * @param orgId
     * @return
     */
    public List<DrugSupplierCatalog> findBySupplier(String orgId) {
        return drugSupplierCatalogBo.findBySupplier(orgId);
    }
}
