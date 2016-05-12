/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugCodingRuleApi;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.entity.DrugCodingRule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 药品编码字典生成Service
 * @author luohk
 * @version 2016-05-10
 */

@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugCodingRuleService extends CrudImplService<DrugCodingRuleDao, DrugCodingRule> implements DrugCodingRuleApi{

    /**
     * 获取药品编码规则列表
     * @return
     */
    public List<DrugCodingRule> findAllList(){
        return dao.findAll();
    }

    /**
     * 通过编码名称获取编码长度
     *
     * @param className
     * @return
     */
    public DrugCodingRule findLevelWidth(String className){
          return dao.findLevelWidth(className);
    }
}