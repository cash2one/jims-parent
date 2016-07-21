package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugSupplierCatalogDao;
import com.jims.phstock.entity.DrugSupplierCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wei on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugSupplierCatalogBo extends CrudImplService<DrugSupplierCatalogDao, DrugSupplierCatalog> {
    @Autowired
    private DrugSupplierCatalogDao drugSupplierCatalogDao;

    /**
     * 查询所有供货商类别
     *
     * @param orgId
     * @return
     * @author wei
     */

    public List<DrugSupplierCatalog> list(String orgId) {
        List<DrugSupplierCatalog> list = dao.list(orgId);
        return list;
    }


    /**
     * 查询全部
     * @param q 拼音码、厂商名
     * @return
     * @author wei
     */
    public List<DrugSupplierCatalog> findList(String orgId, String q) {
        return dao.findList(orgId,q);
    }

    /**
     * 根据拼音码查询所属组织机构的供应商和生产商
     *
     * @param orgId     组织机构ID
     * @param inputCode 拼音字头
     * @return
     * @author wei
     */

    public List<DrugSupplierCatalog> listDrugSupplierCatalogByInputCode(String orgId, String inputCode) {
        return dao.listDrugSupplierCatalogByInputCode(orgId, inputCode);
    }

    /**
     * 根据厂商类别查询所属组织机构的生产商或供应商
     *
     * @param orgId        组织机构ID
     * @param supplierType 厂商类别：生厂商、供应商
     * @return
     * @author wei
     */

    public List<DrugSupplierCatalog> listDrugSupplierCatalogBySupplierType(String orgId, String supplierType) {
        return dao.listDrugSupplierCatalogBySupplierType(orgId, supplierType);
    }

    /**
     *增加方法
     * @param drugSupplierCatalog
     * @return
     * @author wei
     */

    public String save(DrugSupplierCatalog drugSupplierCatalog) {
        return dao.insert(drugSupplierCatalog)+"";
    }

    /**
     *修改
     * @param drugSupplierCatalog
     * @return
     * @author wei
     */
    public String update(DrugSupplierCatalog drugSupplierCatalog) {
        return dao.update(drugSupplierCatalog)+"";
    }



    /**
     *查询全部单位
     * @param orgId        组织机构ID
     * @return
     * @author wei
     */
    public List<DrugSupplierCatalog> findBySupplier(String orgId) {
        return dao.findBySupplier(orgId);
    }

}
