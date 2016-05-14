/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugStorageDeptServiceApi;
import com.jims.phstock.dao.DrugStorageDeptDao;
import com.jims.phstock.entity.DrugStorageDept;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 药品库存单位Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugStorageDeptService extends CrudImplService<DrugStorageDeptDao, DrugStorageDept> implements DrugStorageDeptServiceApi {
    @Autowired
    private DeptDictDao deptDictDao;

    /**
     * 保存增删改数据
     * @param beanChangeVo 增删改数据集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugStorageDept> beanChangeVo) {
        List<DrugStorageDept> inserted = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugStorageDept drugStorageDept : inserted) {
            DeptDict deptDict = deptDictDao.getByName(drugStorageDept.getStorageName()).get(0);
            drugStorageDept.setStorageCode(deptDict.getDeptCode());
            inNum = Integer.valueOf(this.save(drugStorageDept));
            inNum++;
        }
        String insertedNum = inNum + "";
        List<DrugStorageDept> updated = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugStorageDept drugStorageDept : updated) {
            DeptDict deptDict = deptDictDao.getByName(drugStorageDept.getStorageName()).get(0);
            drugStorageDept.setStorageCode(deptDict.getDeptCode());
            updNum = Integer.valueOf(this.save(drugStorageDept));
            updNum++;
        }
        String updatedNum = updNum + "";
        List<DrugStorageDept> deleted = beanChangeVo.getDeleted();
        int delNum = 0;
        for (DrugStorageDept drugStorageDept : deleted) {
            delNum = dao.delete(drugStorageDept);
            delNum++;
        }
        String deletedNum = delNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deletedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }
}