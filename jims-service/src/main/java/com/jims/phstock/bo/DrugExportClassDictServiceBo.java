package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugExportClassDictDao;
import com.jims.phstock.entity.DrugExportClassDict;
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
public class DrugExportClassDictServiceBo extends CrudImplService<DrugExportClassDictDao, DrugExportClassDict> {
    @Autowired
    DrugExportClassDictDao drugExportClassDictDao;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugExportClassDict> beanChangeVo){
        List<DrugExportClassDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugExportClassDict drugExportClassDict : insertedList) {
            drugExportClassDict.preInsert();
            inNum = drugExportClassDictDao.insert(drugExportClassDict);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<DrugExportClassDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugExportClassDict drugExportClassDict : updatedList) {
            updNum = drugExportClassDictDao.update(drugExportClassDict);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<DrugExportClassDict> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (DrugExportClassDict drugExportClassDict : deletedList) {
            dltNum = drugExportClassDictDao.delete(drugExportClassDict);
            dltNum++;
        }
        String deletedNum = dltNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deletedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }

    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType) {
        return dao.listDrugExportClassDictByStorageType(storageType);
    }

    /**
     * 获取出库数据列表
     *
     * @return
     */
    public List<DrugExportClassDict> findAllList() {
        return dao.findAll();
    }
}
