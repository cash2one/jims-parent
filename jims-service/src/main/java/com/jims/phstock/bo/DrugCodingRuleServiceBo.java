package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugCodingRuleDao;
import com.jims.phstock.entity.DrugCodingRule;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugCodingRuleServiceBo extends CrudImplService<DrugCodingRuleDao, DrugCodingRule> {
    @Autowired
    private DrugCodingRuleDao drugCodingRuleDao;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugCodingRule> beanChangeVo){
        List<DrugCodingRule> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugCodingRule drugCodingRule : insertedList) {
            drugCodingRule.preInsert();
            inNum = drugCodingRuleDao.insert(drugCodingRule);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<DrugCodingRule> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugCodingRule drugCodingRule : updatedList) {
            updNum = drugCodingRuleDao.update(drugCodingRule);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<DrugCodingRule> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (DrugCodingRule drugCodingRule : deletedList) {
            dltNum = drugCodingRuleDao.delete(drugCodingRule);
            dltNum++;
        }
        String deletedNum = dltNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deletedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }

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
     * @param className
     * @return
     */
    public DrugCodingRule findLevelWidth(String className) {
        return dao.findLevelWidth(className);
    }
}
