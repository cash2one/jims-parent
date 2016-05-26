package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.PatsInTransferringServiceApi;
import com.jims.finance.dao.PatsInTransferringDao;
import com.jims.finance.entity.PatsInTransferring;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户反馈转科病人记录ServiceImpl
 * @author CTQ
 * @version 2016-05-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class PatsInTransferringServiceImpl extends CrudImplService<PatsInTransferringDao, PatsInTransferring> implements PatsInTransferringServiceApi {

}