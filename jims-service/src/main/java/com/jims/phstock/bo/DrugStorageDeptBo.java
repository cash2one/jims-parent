package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugStorageDeptDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugStorageDept;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品库存单位事务管理层
 * Created by fyg on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugStorageDeptBo extends CrudImplService<DrugStorageDeptDao, DrugStorageDept> {
    @Autowired
    private DeptDictDao deptDictDao;
    @Autowired
    private DrugSubStorageDeptDao subDao;

    /**
     * 保存增删改数据
     *
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

    /**
     * 获取药品库存单位子单位
     *
     * @param orgId       所属机构
     * @param storageCode 库存单位编码
     * @return
     */
    public List<DrugSubStorageDept> findSubList(String orgId, String storageCode) {
        DrugSubStorageDept sub = new DrugSubStorageDept();
        sub.setOrgId(orgId);
        sub.setStorageCode(storageCode);
        return subDao.findList(sub);
    }

    /**
     * 保存子单位
     *
     * @param sub
     * @return
     */
    public String saveSub(DrugSubStorageDept sub) {
        int i = 0;
        try {
            if (sub.getIsNewRecord()) {
                sub.preInsert();
                i = subDao.insert(sub);
            } else {
                sub.preUpdate();
                i = subDao.update(sub);
            }
        } catch (Exception e) {
            return i + "";
        }
        return i + "";
    }

}
