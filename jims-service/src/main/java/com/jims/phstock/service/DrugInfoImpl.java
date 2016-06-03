/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.phstock.api.DrugDictServiceApi;
import com.jims.phstock.api.DrugInfoApi;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.dao.DrugDictDao;
import com.jims.phstock.dao.DrugInfoDao;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugInfo;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.vo.DrugCatalogBeanVo;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 药品毒理信息维护service
 * @author yangruidong
 * @version 2016-05-16
 */
@Service(version = "1.0.0")

public class DrugInfoImpl extends CrudImplService<DrugInfoDao, DrugInfo> implements DrugInfoApi {


    @Override
    public DrugInfo getDrugInfoByDrugCode(String drugCode) {
        return dao.getDrugInfoByDrugCode(drugCode);
    }
}