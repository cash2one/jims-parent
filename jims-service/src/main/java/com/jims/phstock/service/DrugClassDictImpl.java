package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugClassDictApi;
import com.jims.phstock.dao.DrugClassDictDao;
import com.jims.phstock.entity.DrugClassDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by zhu on 2016/5/10.
 */
@Service(version = "1.0.0")

public class DrugClassDictImpl extends CrudImplService<DrugClassDictDao, DrugClassDict> implements DrugClassDictApi{

    @Autowired
    private DrugClassDictDao drugClassDictDao;


    /***
     *  根据诊疗机构ID获取药品类别
     * @return 药品类别集合
     * @author zq
     */
    public List<DrugClassDict> listDrugClassDict(){
        return drugClassDictDao.listDrugClassDict();
    }

    /***
     * 获取组织机构的某一大类的所有亚类
     * @param parentId 大类ID
     * @return 返回某一大类的所有亚类
     * @author zq
     */
    public List<DrugClassDict> listSubClassDict(String parentId) {
        return drugClassDictDao.listSubClassDict(parentId );
    }

    @Override
    public String saveDrugClassDict(DrugClassDict drugClassDict) {
        String intStr=super.save(drugClassDict);

        return intStr;
    }

    @Override
    public String saveDrugClassDicts(List<DrugClassDict> drugClassDicts) {
        return null;
    }
}
