package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugSupplierCatalogApi;
import com.jims.phstock.entity.DrugSupplierCatalog;
import com.jims.phstock.dao.DrugSupplierCatalogDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wei on 2016/5/10.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugSupplierCatalogServiceImpl extends CrudImplService<DrugSupplierCatalogDao, DrugSupplierCatalog> implements DrugSupplierCatalogApi {

    /**
     * 查询所有供货商类别
     * @param orgId
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> list(String orgId) {
        List<DrugSupplierCatalog> list=dao.list(orgId);
        return list;
    }



    /**
     * 查询全部
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> findList(String orgId) {
        return dao.findList(orgId);
    }

    /**
     *根据拼音码查询所属组织机构的供应商和生产商
     * @param orgId 组织机构ID
     * @param inputCode 拼音字头
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> listDrugSupplierCatalogByInputCode(String orgId, String inputCode) {
        return dao.listDrugSupplierCatalogByInputCode(orgId,inputCode);
    }

    /**
     *根据厂商类别查询所属组织机构的生产商或供应商
     * @param orgId 组织机构ID
     * @param supplierType 厂商类别：生厂商、供应商
     * @return
     * @author wei
     */
    @Override
    public List<DrugSupplierCatalog> listDrugSupplierCatalogBySupplierType(String orgId, String supplierType) {
        return dao.listDrugSupplierCatalogBySupplierType(orgId,supplierType);
    }
}
