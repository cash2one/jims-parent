package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugInfoDao;
import com.jims.phstock.entity.DrugInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by txb on 2016-06-17.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugInfoBo extends CrudImplService<DrugInfoDao,DrugInfo> {
    public DrugInfo getDrugInfoByDrugCode(String drugCode) {
        return dao.getDrugInfoByDrugCode(drugCode);
    }
}
