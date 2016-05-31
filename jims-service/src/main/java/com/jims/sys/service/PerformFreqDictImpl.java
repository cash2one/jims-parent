package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.PerformFreqDictApi;
import com.jims.sys.dao.PerformFreqDictDao;
import com.jims.sys.entity.PerformFreqDict;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service(version = "1.0.0")

public class PerformFreqDictImpl extends CrudImplService<PerformFreqDictDao, PerformFreqDict> implements PerformFreqDictApi{

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new PerformFreqDict());
    }
}
