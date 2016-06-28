package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugImportClassDictDao;
import com.jims.phstock.entity.DrugImportClassDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugImportDictServiceBo extends CrudImplService<DrugImportClassDictDao, DrugImportClassDict> {
    @Autowired
    private DrugImportClassDictDao drugImportClassDictDao;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugImportClassDict> beanChangeVo){
        List<DrugImportClassDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugImportClassDict drugImportClassDict : insertedList) {
            drugImportClassDict.preInsert();
            inNum = drugImportClassDictDao.insert(drugImportClassDict);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<DrugImportClassDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugImportClassDict drugImportClassDict : updatedList) {
            updNum = drugImportClassDictDao.update(drugImportClassDict);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<DrugImportClassDict> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (DrugImportClassDict drugImportClassDict : deletedList) {
            dltNum = drugImportClassDictDao.delete(drugImportClassDict);
            dltNum++;
        }
        String deletedNum = dltNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deletedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 根据适用单位查询所有的入库类型
     * @param storageType 适用单位
     * @return
     * @author luohk
     */
    public List<DrugImportClassDict> listDrugImportByStorageType(String storageType) {
        return dao.listDrugImportByStorageType(storageType);
    }

    /**
     * 获取入库类别列表
     * @return
     */
    public List<DrugImportClassDict> findAllList() {
        return dao.findAll();
    }
}
