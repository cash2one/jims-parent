package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugNameDict;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lgx on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugNameDictBo extends CrudImplService<DrugNameDictDao, DrugNameDict> {

    /**
     * 查询所有药品名称列表
     * @return
     * @author txb
     * @version 2016-05-11
     */
    public List<DrugNameDict> findDrugNameDictList(String inputCode) {
        return dao.findDrugNameDictList(inputCode);
    }
    /**
     * 查询所有药品名称列表通过拼音码
     * @param drugCode 拼音码
     * @return
     * @author txb
     * @version 2016-05-11
     */
    public List<DrugNameDict> listDrugNameDictByDrugCode(String drugCode) {
        return dao.listDrugNameDictByDrugCode(drugCode);
    }
}
