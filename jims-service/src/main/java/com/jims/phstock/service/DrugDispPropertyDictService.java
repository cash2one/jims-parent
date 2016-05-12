/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugDispPropertyDictApi;
import com.jims.phstock.dao.DrugDispPropertyDictDao;
import com.jims.phstock.entity.DrugDispPropertyDict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 摆药类别字典生成Service
 * @author luohk
 * @version 2016-05-10
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugDispPropertyDictService extends CrudImplService<DrugDispPropertyDictDao, DrugDispPropertyDict> implements DrugDispPropertyDictApi{

    /**
     * 获取摆药类别列表
     * @return
     */
    public List<DrugDispPropertyDict> findAllList(){
        return dao.findAll();
    }
}