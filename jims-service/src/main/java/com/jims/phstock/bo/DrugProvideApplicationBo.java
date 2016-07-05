package com.jims.phstock.bo;

import com.jims.phstock.entity.DrugProvideApplication;
import com.jims.phstock.dao.DrugProvideApplicationDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.vo.DrugProvideApplicationVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 录入申请bo
 *
 * @author yangruidong
 * @version 2016-07-04
 */
@Service
@Transactional(readOnly = false)
public class DrugProvideApplicationBo extends CrudImplService<DrugProvideApplicationDao, DrugProvideApplication> {

    /**
     * 批量保存（插入或更新）
     *
     * @param list
     */
    public void save(List<DrugProvideApplication> list) {
        if (list != null && list.size() > 0) {
            for (DrugProvideApplication entity : list) {
                save(entity);
            }
        }
    }

    /**
     * 保存  增删改
     *
     * @param drugProvideApplicationVo
     * @return
     * @author yangruidong
     */
    public List<DrugProvideApplication> saveAll(DrugProvideApplicationVo<DrugProvideApplication> drugProvideApplicationVo) {
        List<DrugProvideApplication> newUpdateDict = new ArrayList<DrugProvideApplication>();
        List<DrugProvideApplication> inserted = drugProvideApplicationVo.getInserted();
        List<DrugProvideApplication> updated = drugProvideApplicationVo.getUpdated();
        List<DrugProvideApplication> deleted = drugProvideApplicationVo.getDeleted();
        //插入
        for (DrugProvideApplication drugProvideApplication : inserted) {
            drugProvideApplication.preInsert();
            int num = dao.insert(drugProvideApplication);
        }
        //更新
        for (DrugProvideApplication drugProvideApplication : updated) {
            drugProvideApplication.preUpdate();
            int num = dao.update(drugProvideApplication);
        }

        //删除
        List<String> ids = new ArrayList<String>();

        for (DrugProvideApplication drugProvideApplication : deleted) {
            ids.add(drugProvideApplication.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }

        return newUpdateDict;
    }

    /**
     *查询去除重复的申请号
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findDocumentByDistinct(DrugProvideApplication entity){
        return  dao.findDocumentByDistinct(entity);
    }
}