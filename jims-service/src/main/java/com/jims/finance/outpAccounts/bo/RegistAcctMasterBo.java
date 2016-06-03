package com.jims.finance.outpAccounts.bo;

import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.finance.outpAccounts.dao.RegistAcctDetailDao;
import com.jims.finance.outpAccounts.dao.RegistAcctMasterDao;
import com.jims.finance.outpAccounts.dao.RegistAcctMoneyDao;
import com.jims.finance.outpAccounts.entity.RegistAcctDetail;
import com.jims.finance.outpAccounts.entity.RegistAcctMaster;
import com.jims.finance.outpAccounts.entity.RegistAcctMoney;
import com.jims.register.dao.ClinicReturnedAcctDao;
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
    @Autowired
    ClinicMasterDao clinicMasterDao;
    @Autowired
    ClinicReturnedAcctDao clinicReturnedAcctDao;

    /**
     * @param      registAcctMaster       传递参数
     * @return java.lang.String    返回类型
     * @throws
     * @Title: saveMaster
     * @Description: (处理挂号结账信息)
     * @author CTQ
     * @date 2016/6/2
     */
    public String saveMaster(RegistAcctMaster registAcctMaster) {
        int num = 0;
        registAcctMaster.setAcctNo("1");
        registAcctMaster.setOperatorNo("1");
        registAcctMaster.preInsert();
        if(registAcctMaster.getAcctDetails()!=null&&registAcctMaster.getAcctDetails().size()>0){
            for(RegistAcctDetail detail : registAcctMaster.getAcctDetails()){
                detail.setAcctId(registAcctMaster.getId());
                detail.setOrgId(registAcctMaster.getOrgId());
                detail.setAcctNo(registAcctMaster.getAcctNo());
                detail.preInsert();
                registAcctDetailDao.insert(detail);
            }
        }
        if(registAcctMaster.getAcctMoneys()!=null&&registAcctMaster.getAcctDetails().size()>0){
            for(RegistAcctMoney money : registAcctMaster.getAcctMoneys()){
                money.setAcctId(registAcctMaster.getId());
                money.setOrgId(registAcctMaster.getOrgId());
                money.setAcctNo(registAcctMaster.getAcctNo());
                money.preInsert();
                registAcctMoneyDao.insert(money);
            }
        }
        num = registAcctMasterDao.insert(registAcctMaster);
        if(num>0){
            clinicMasterDao.updateMaster(registAcctMaster.getAcctNo(),"1", DateUtils.formatDate(registAcctMaster.getAcctDate(),"yyyy-MM-dd HH:mm:ss"));
            clinicReturnedAcctDao.updateReturned(registAcctMaster.getAcctNo(),"1", DateUtils.formatDate(registAcctMaster.getAcctDate(), "yyyy-MM-dd HH:mm:ss"));
        }
        return String.valueOf(num);
    }
}
