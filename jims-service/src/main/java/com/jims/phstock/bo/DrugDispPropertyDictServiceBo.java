package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugDispPropertyDictDao;
import com.jims.phstock.entity.DrugDispPropertyDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugDispPropertyDictServiceBo extends CrudImplService<DrugDispPropertyDictDao, DrugDispPropertyDict> {
    @Autowired
    private DrugDispPropertyDictDao drugDispPropertyDictDao;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugDispPropertyDict> beanChangeVo){
        List<DrugDispPropertyDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (DrugDispPropertyDict drugDispPropertyDict : insertedList) {
            drugDispPropertyDict.preInsert();
            inNum = drugDispPropertyDictDao.insert(drugDispPropertyDict);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<DrugDispPropertyDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (DrugDispPropertyDict drugDispPropertyDict : updatedList) {
            updNum = drugDispPropertyDictDao.update(drugDispPropertyDict);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<DrugDispPropertyDict> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (DrugDispPropertyDict drugDispPropertyDict : deletedList) {
            dltNum = drugDispPropertyDictDao.delete(drugDispPropertyDict);
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
     * 获取摆药类别列表
     *
     * @return
     */
    public List<DrugDispPropertyDict> findAllList() {
        return dao.findAll();
    }
}
