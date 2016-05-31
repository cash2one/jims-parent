package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.StaffGroupClassDictApi;
import com.jims.sys.dao.StaffGroupClassDictDao;
import com.jims.sys.entity.StaffGroupClassDict;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */

@Service(version = "1.0.0")

public class StaffGroupClassDictImpl extends CrudImplService<StaffGroupClassDictDao, StaffGroupClassDict> implements StaffGroupClassDictApi{

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new StaffGroupClassDict());
    }
}
