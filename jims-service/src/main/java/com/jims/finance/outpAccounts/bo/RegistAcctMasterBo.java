package com.jims.finance.outpAccounts.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.outpAccounts.dao.RegistAcctDetailDao;
import com.jims.finance.outpAccounts.dao.RegistAcctMasterDao;
import com.jims.finance.outpAccounts.dao.RegistAcctMoneyDao;
import com.jims.finance.outpAccounts.entity.RegistAcctDetail;
import com.jims.finance.outpAccounts.entity.RegistAcctMaster;
import com.jims.finance.outpAccounts.entity.RegistAcctMoney;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    RegistAcctMasterDao registAcctMasterDao;
    @Autowired
    RegistAcctDetailDao registAcctDetailDao;
    @Autowired
    RegistAcctMoneyDao registAcctMoneyDao;


    public String saveMaster(RegistAcctMaster registAcctMaster) {
        registAcctMaster.preInsert();
        if(registAcctMaster.getAcctDetails()!=null&&registAcctMaster.getAcctDetails().size()>0){
            for(int i=0;i<registAcctMaster.getAcctDetails().size();i++){
                RegistAcctDetail detail = new RegistAcctDetail();
                detail = registAcctMaster.getAcctDetails().get(i);
                detail.preInsert();
                detail.setAcctId(registAcctMaster.getId());
                detail.setOrgId(registAcctMaster.getOrgId());
                registAcctDetailDao.insert(detail);
            }
            /*for(RegistAcctDetail detail : registAcctMaster.getAcctDetails()){
                detail.preInsert();
                detail.setAcctId(registAcctMaster.getId());
                detail.setOrgId(registAcctMaster.getOrgId());

                registAcctDetailDao.insert(detail);
            }*/
        }
        if(registAcctMaster.getAcctMoneys()!=null&&registAcctMaster.getAcctDetails().size()>0){
            for(RegistAcctMoney money : registAcctMaster.getAcctMoneys()){
                money.setAcctId(registAcctMaster.getId());
                money.setAcctId(registAcctMaster.getId());
                money.preInsert();
                registAcctMoneyDao.insert(money);
            }
        }
        return String.valueOf(registAcctMasterDao.insert(registAcctMaster));
    }
}
