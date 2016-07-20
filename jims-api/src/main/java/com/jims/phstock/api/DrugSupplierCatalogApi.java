package com.jims.phstock.api;

import com.jims.phstock.entity.DrugSupplierCatalog;

import java.util.List;

/**
 * 供应商、生产商接口
 * Created by heren on 2016/5/10.
 */
public interface DrugSupplierCatalogApi {


    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询所有供货商类别
     *
     * @param orgId
     * @return
     * @author wei
     */
    public List<DrugSupplierCatalog> list(String orgId);

    /**
     * 增加数据
     *
     * @param drugSupplierCatalog
     * @return
     */
    public String save(DrugSupplierCatalog drugSupplierCatalog);

    /**
     * 修改数据
     *
     * @param drugSupplierCatalog
     * @return
     */
    public String update(DrugSupplierCatalog drugSupplierCatalog);

    /**
     * 查询全部
     * @return
     */
    public List<DrugSupplierCatalog> findList(String orgId);

    /**
     * 查询全部
     * @param q 拼音码、厂商名
     * @return
     */
    public List<DrugSupplierCatalog> findList(String orgId,String q);

    /**
     * 根据拼音码查询所属组织机构的供应商和生产商
     *
     * @param orgId     组织机构ID
     * @param inputCode 拼音字头
     * @return
     * @author ztq
     */
    public List<DrugSupplierCatalog> listDrugSupplierCatalogByInputCode(String orgId, String inputCode);

    /**
     * 根据厂商类别查询所属组织机构的生产商或供应商
     *
     * @param orgId        组织机构ID
     * @param supplierType 厂商类别：生厂商、供应商
     * @return
     * @author ztq
     */
    public List<DrugSupplierCatalog> listDrugSupplierCatalogBySupplierType(String orgId, String supplierType);

    /**
     * 查询全部单位
     * @param orgId
     * @return
     */
    public List<DrugSupplierCatalog> findBySupplier(String orgId);

}
