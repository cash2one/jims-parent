package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugClassDictDao;
import com.jims.phstock.entity.DrugClassDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhuq on 2016/6/27.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugClassDictBo extends CrudImplService<DrugClassDictDao,DrugClassDict> {
    @Autowired
    private DrugClassDictDao drugClassDictDao;

    /***
     * 获取药品类别
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
    public List<DrugClassDict> listSubClassDict(String parentId){
        return drugClassDictDao.listSubClassDict(parentId);
    }

    /**
     * 保存单条数据
     * zq
     * */

    public String saveDrugClassDict (DrugClassDict drugClassDict){
        String intStr=super.save(drugClassDict);
        return intStr;
    }
}
