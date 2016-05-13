package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugCodingRule;

import java.util.List;

/**
 * 药品编码生成规则定义
 * Created by ztq on 2016/5/10.
 */
public interface DrugCodingRuleApi {

    /**
     * 获取药品编码规则
     * @param id
     * @return
     */
    public DrugCodingRule get(String id);

    /**
     * 根据药品编码规则获取编码列表
     * @param drugCodingRule
     * @return
     */
    public List<DrugCodingRule> findList(DrugCodingRule drugCodingRule);

    /**
     * 根据编码规则获取带分页功能的编码列表
     * @param page
     * @param drugCodingRule
     * @return
     */
    public Page<DrugCodingRule> findPage(Page<DrugCodingRule> page, DrugCodingRule drugCodingRule);

    /**
     *  保存功能
     * @param drugCodingRule
     */
    public String save(DrugCodingRule drugCodingRule);

    /**
     * 删除功能
     * @param drugCodingRule
     */
    public String delete(DrugCodingRule drugCodingRule);

    /**
     * 删除功能
     * @param id
     * @return
     */
    public String delete(String id);

    /**
     * 获取药品编码规则列表
     * @return
     */
    public List<DrugCodingRule> findAllList();

    /**
     * 通过编码名称获取编码长度
     * @param className
     * @return
     */
    public DrugCodingRule findLevelWidth(String className);

}
