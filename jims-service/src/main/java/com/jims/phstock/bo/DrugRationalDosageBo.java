package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugRationalDosageDao;
import com.jims.phstock.entity.DrugRationalDosage;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.jsf.DelegatingNavigationHandlerProxy;

import java.util.List;

/**
 * 药品用量信息bo事务处理层
 * Created by fyg on 2016/7/12.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugRationalDosageBo extends CrudImplService<DrugRationalDosageDao,DrugRationalDosage> {
    @Autowired
    private DrugRationalDosageDao drugRationalDosageDao;

    /**
     * 查询所有药品用量信息情况
     * @return
     * @author fengyuguang
     */
    public List<DrugRationalDosage> findAll(){
        return drugRationalDosageDao.findAll();
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugRationalDosage> beanChangeVo) {
        List<DrugRationalDosage> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugRationalDosage drugRationalDosage : insertedList) {
            drugRationalDosage.preInsert();
            inNum = drugRationalDosageDao.insert(drugRationalDosage);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<DrugRationalDosage> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugRationalDosage drugRationalDosage : updatedList) {
            updNum = drugRationalDosageDao.update(drugRationalDosage);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<DrugRationalDosage> delList = beanChangeVo.getDeleted();
        int delNum = 0;
        for (DrugRationalDosage drugRationalDosage : delList) {
            delNum = drugRationalDosageDao.delete(drugRationalDosage);
            delNum++;
        }
        String deleteNum = delNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deleteNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }
}
