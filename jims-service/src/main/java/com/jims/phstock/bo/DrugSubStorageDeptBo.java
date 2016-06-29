package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品子库存单位bo事务处理层
 * Created by fyg on 2016/6/28.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugSubStorageDeptBo extends CrudImplService<DrugSubStorageDeptDao,DrugSubStorageDept>{
    @Autowired
    private DrugSubStorageDeptDao drugSubStorageDeptDao;

    /**
     * 根据库存单位代码获取该库存单位下的子库存单位列表
     * @param orgId 所属组织机构ID
     * @param storageCode 库存单位代码
     * @return 子库存单位列表
     * @author fengyuguang
     */
    public List<DrugSubStorageDept> getListByStorageCode(String orgId,String storageCode){
        return drugSubStorageDeptDao.getListByStorageCode(orgId,storageCode);
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugSubStorageDept> beanChangeVo){
        List<DrugSubStorageDept> inserted = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugSubStorageDept drugSubStorageDept : inserted) {
            drugSubStorageDept.preInsert();
            drugSubStorageDept.setInputCode(PinYin2Abbreviation.cn2py(drugSubStorageDept.getSubStorage()));    //生成拼音码
            inNum = drugSubStorageDeptDao.insert(drugSubStorageDept);
            inNum++;
        }
        String insertedNum = inNum + "";
        List<DrugSubStorageDept> updated = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugSubStorageDept drugSubStorageDept : updated) {
            drugSubStorageDept.setInputCode(PinYin2Abbreviation.cn2py(drugSubStorageDept.getSubStorage()));    //生成拼音码
            updNum = drugSubStorageDeptDao.update(drugSubStorageDept);
            updNum++;
        }
        String updatedNum = updNum + "";
        List<DrugSubStorageDept> deleted = beanChangeVo.getDeleted();
        int delNum = 0;
        for (DrugSubStorageDept drugSubStorageDept : deleted) {
            delNum = drugSubStorageDeptDao.delete(drugSubStorageDept);
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
