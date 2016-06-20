package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.entity.DrugCodingRule;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugCodingRuleServiceBo extends CrudImplService<DrugCodingRuleDao, DrugCodingRule> {
    /**
     * 获取药品编码规则列表
     *
     * @return
     */
    public List<DrugCodingRule> findAllList() {
        return dao.findAll();
    }

    /**
     * 通过编码名称获取编码长度
     *
     * @param className
     * @return
     */
    public DrugCodingRule findLevelWidth(String className) {
        return dao.findLevelWidth(className);
    }
}
