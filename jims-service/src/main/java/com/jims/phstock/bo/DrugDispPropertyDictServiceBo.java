package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugDispPropertyDictDao;
import com.jims.phstock.entity.DrugDispPropertyDict;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class DrugDispPropertyDictServiceBo extends CrudImplService<DrugDispPropertyDictDao, DrugDispPropertyDict> {

    /**
     * 获取摆药类别列表
     *
     * @return
     */
    public List<DrugDispPropertyDict> findAllList() {
        return dao.findAll();
    }
}
