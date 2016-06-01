package com.jims.finance.outpAccounts.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.outpAccounts.dao.RegistAcctMasterDao;
import com.jims.finance.outpAccounts.entity.RegistAcctMaster;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 挂号结账主记录
 * @author CTQ
 * @date 2016-06-01 10:06:45
 */
@Service
@Component
@Transactional(readOnly = false)
public class RegistAcctMasterBo extends CrudImplService<RegistAcctMasterDao, RegistAcctMaster> {
}
