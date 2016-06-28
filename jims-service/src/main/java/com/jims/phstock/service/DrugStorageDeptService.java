/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugStorageDeptServiceApi;
import com.jims.phstock.bo.DrugStorageDeptBo;
import com.jims.phstock.dao.DrugStorageDeptDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugStorageDept;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 药品库存单位Service
 *
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")

public class DrugStorageDeptService extends CrudImplService<DrugStorageDeptDao, DrugStorageDept> implements DrugStorageDeptServiceApi {
    @Autowired
    private DrugStorageDeptBo drugStorageDeptBo;

    /**
     * 保存增删改数据
     * @param beanChangeVo 增删改数据集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugStorageDept> beanChangeVo) {
        return drugStorageDeptBo.merge(beanChangeVo);
    }

    /**
     * 获取药品库存单位子单位
     *
     * @param orgId       所属机构
     * @param storageCode 库存单位编码
     * @return
     */
    public List<DrugSubStorageDept> findSubList(String orgId, String storageCode) {
        return drugStorageDeptBo.findSubList(orgId, storageCode);
    }

    /**
     * 保存子单位
     *
     * @param sub
     * @return
     */
    public String saveSub(DrugSubStorageDept sub) {
        return drugStorageDeptBo.saveSub(sub);
    }
}