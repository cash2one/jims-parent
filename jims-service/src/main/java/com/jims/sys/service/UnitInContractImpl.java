package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.UnitInContractApi;
import com.jims.sys.dao.UnitInContractDao;
import com.jims.sys.entity.UnitInContract;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */

@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class UnitInContractImpl extends CrudImplService<UnitInContractDao, UnitInContract> implements UnitInContractApi{

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new UnitInContract());
    }
}
