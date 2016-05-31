/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugNameDictServiceApi;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugNameDict;


import java.util.List;


/**
 * 药品名称Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")

public class DrugNameDictService extends CrudImplService<DrugNameDictDao, DrugNameDict> implements DrugNameDictServiceApi {

    /**
     * 查询所有药品名称列表
     * @return
     * @author txb
     * @version 2016-05-11
     */
    @Override
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
    @Override
    public List<DrugNameDict> listDrugNameDictByDrugCode(String drugCode) {
        return dao.listDrugNameDictByDrugCode(drugCode);
    }
}