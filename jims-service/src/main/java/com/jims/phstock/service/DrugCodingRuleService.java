package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugCodingRuleApi;
import com.jims.phstock.bo.DrugCodingRuleServiceBo;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.entity.DrugCodingRule;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 药品编码字典生成Service
 * @author luohk
 * @version 2016-05-10
 */

@Service(version = "1.0.0")
public class DrugCodingRuleService implements DrugCodingRuleApi{

    @Autowired
    private DrugCodingRuleServiceBo drugCodingRuleServiceBo;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugCodingRule> beanChangeVo){
        return drugCodingRuleServiceBo.merge(beanChangeVo);
    }

    /**
     * 获取药品编码规则
     *
     * @param id
     * @return
     */
    public DrugCodingRule get(String id){
        return drugCodingRuleServiceBo.get(id);
    }

    /**
     * 根据药品编码规则获取编码列表
     *
     * @param drugCodingRule
     * @return
     */
    public List<DrugCodingRule> findList(DrugCodingRule drugCodingRule){
        return drugCodingRuleServiceBo.findList(drugCodingRule);
    }

    /**
     * 根据编码规则获取带分页功能的编码列表
     *
     * @param page
     * @param drugCodingRule
     * @return
     */
    public Page<DrugCodingRule> findPage(Page<DrugCodingRule> page, DrugCodingRule drugCodingRule){
        return drugCodingRuleServiceBo.findPage(page,drugCodingRule);
    }

    /**
     * 保存功能
     *
     * @param drugCodingRule
     */
    public String save(DrugCodingRule drugCodingRule){
        return drugCodingRuleServiceBo.save(drugCodingRule);
    }

    /**
     * 删除功能
     *
     * @param drugCodingRule
     */
    public String delete(DrugCodingRule drugCodingRule){
        return drugCodingRuleServiceBo.delete(drugCodingRule);
    }

    /**
     * 删除功能
     *
     * @param id
     * @return
     */
    public String delete(String id){
        return drugCodingRuleServiceBo.delete(id);
    }

    /**
     * 获取药品编码规则列表
     * @return
     */
    public List<DrugCodingRule> findAllList(){
        return drugCodingRuleServiceBo.findAllList();
    }

    /**
     * 通过编码名称获取编码长度
     *
     * @param className
     * @return
     */
    public DrugCodingRule findLevelWidth(String className){
          return drugCodingRuleServiceBo.findLevelWidth(className);
    }
}