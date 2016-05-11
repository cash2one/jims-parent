package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugSupplierCatalog;

import java.util.List;

/**
 * Created by wei on 2016/5/10.
 */
@MyBatisDao
public interface DrugSupplierCatalogDao extends CrudDao<DrugSupplierCatalog> {



    /**
     * 查询所有供货商类别
     * @param orgId
     * @return
     * @author wei
     */
    public List<DrugSupplierCatalog> list(String orgId);

    /**
     * 查询全部数据
     * @param orgId
     * @return
     * @author wei
     */
    public List<DrugSupplierCatalog> findList(String orgId);

    /**
     * 根据拼音码查询所属组织机构的供应商和生产商
     * @param orgId 组织机构ID
     * @param inputCode 拼音字头
     * @return
     * @author wei
     *
     */
    public List<DrugSupplierCatalog> listDrugSupplierCatalogByInputCode(String orgId,String inputCode) ;

    /**
     * 根据厂商类别查询所属组织机构的生产商或供应商
     * @param orgId 组织机构ID
     * @param supplierType 厂商类别：生厂商、供应商
     * @return
     * @author wei
     */
    public List<DrugSupplierCatalog> listDrugSupplierCatalogBySupplierType(String orgId,String supplierType) ;
}
