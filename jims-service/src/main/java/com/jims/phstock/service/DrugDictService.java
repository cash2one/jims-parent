/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.dao.DrugDictDao;
import com.jims.phstock.entity.DrugDict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 药品字典Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugDictService extends CrudImplService<DrugDictDao, DrugDict> implements DrugDictServiceApi {


    @Override
    public String getDrugCodeByRule(String secondType, String drugForm) {
        return null;
    }

    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    @Override
    public List<DrugDict> listDrugDictByDrugCode(String drugCode) {
        return dao.listDrugDictByDrugCode(drugCode);
    }
}