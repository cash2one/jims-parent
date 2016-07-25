package com.jims.finance.outpAccounts.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.finance.dao.OutpAcctMasterDao;
import com.jims.finance.dao.OutpRcptMasterDao;
import com.jims.finance.entity.OutpAcctMaster;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpPaymentsMoney;
import com.jims.finance.entity.OutpRcptMaster;
import com.jims.finance.outpAccounts.dao.OutpAcctDetailDao;
import com.jims.finance.outpAccounts.dao.OutpAcctMoneyDao;
import com.jims.finance.outpAccounts.entity.OutpAcctDetail;
import com.jims.finance.outpAccounts.entity.OutpAcctMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 门诊收费结帐主记录Bo
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Service
@Component
@Transactional(readOnly = false)
public class OutpAcctMasterBo extends CrudImplService<OutpAcctMasterDao, OutpAcctMaster> {
    @Autowired
    private OutpAcctMoneyDao outpAcctMoneyDao;
    @Autowired
    private OutpAcctDetailDao outpAcctDetailDao;
    @Autowired
    private OutpRcptMasterDao outpRcptMasterDao;
    /**
     * 方法 getMaxAcctNo的功能描述
     * 拿到最大的结账序号
     * @param
     * @reurn
     * @thrws
     * @author pq
     * @date 2016/6/1 0001
     */
    public String getMaxAcctNo(){
         String accptNo =  dao.getMaxAcctNo();
        if("".equals(accptNo) || accptNo == null){
            accptNo= ""+28000;

        }else{
           int no= Integer.parseInt(accptNo)+1;
            accptNo = String.valueOf(no);
        }
        return accptNo;
    }

    /**
     * 方法 saveOutpAcct的功能描述
     * 门诊结账 - 收费结账 - 结账确认
     * @param
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public String saveOutpAcct(OutpRcptMaster outpRcptMaster,LoginInfo loginInfo){
        int num=0;
       if(outpRcptMaster !=null){
           OutpAcctMaster  outpAcctMaster = new OutpAcctMaster();
           if (outpAcctMaster.getIsNewRecord()) {
               outpAcctMaster.preInsert();
               outpAcctMaster.setAcctNo(outpRcptMaster.getAcctNo());
               outpAcctMaster.setOrgId(loginInfo.getOrgId());
               outpAcctMaster.setOperatorNo(loginInfo.getPersionId());
               outpAcctMaster.setAcctDate(new Date());
               outpAcctMaster.setMinRcptNo(outpRcptMaster.getMinRcptNo());
               outpAcctMaster.setMaxRcptNo(outpRcptMaster.getMaxRcptNo());
               outpAcctMaster.setRcptsNum(outpRcptMaster.getCountNo());
               outpAcctMaster.setFreeOfChargeNum(outpRcptMaster.getFreeCount());
               outpAcctMaster.setRefundNum(outpRcptMaster.getRefundCount());
               outpAcctMaster.setRefundAmount(outpRcptMaster.getRefundMoney());
               outpAcctMaster.setTotalCosts(outpRcptMaster.getSumCosts());
               outpAcctMaster.setTotalIncomes(outpRcptMaster.getSumCharges());
               outpAcctMaster.setTallyDate(new Date());
               dao.insert(outpAcctMaster);
           }
           if(outpRcptMaster.getOutpAcctMoneyList()!=null){//门诊收费结帐金额分类OutpAcctMoney
               for(int i=0;i<outpRcptMaster.getOutpAcctMoneyList().size();i++){
                   OutpPaymentsMoney outpPaymentsMoney=outpRcptMaster.getOutpAcctMoneyList().get(i);
                   OutpAcctMoney outpAcctMoney=new OutpAcctMoney();
                   outpAcctMoney.preInsert();
                   outpAcctMoney.setAcctId(outpAcctMaster.getId());
                   outpAcctMoney.setAcctNo(outpAcctMaster.getAcctNo());
                   outpAcctMoney.setMoneyType(outpPaymentsMoney.getMoneyType());
                   outpAcctMoney.setIncomeAmount(outpPaymentsMoney.getPaymentAmount());
                   outpAcctMoney.setRefundedAmount(outpPaymentsMoney.getRefundedAmount());
                   outpAcctMoneyDao.insert(outpAcctMoney);
               }
           }

           if(outpRcptMaster.getOutpAcctDetailList()!=null){//门诊收费结帐明细记录OutpAcctDetail
               for(int j=0;j<outpRcptMaster.getOutpAcctDetailList().size();j++){
                   OutpBillItems outpBillItems=outpRcptMaster.getOutpAcctDetailList().get(j);
                   OutpAcctDetail outpAcctDetail = new OutpAcctDetail();
                   outpAcctDetail.preInsert();
                   outpAcctDetail.setAcctId(outpAcctMaster.getId());
                   outpAcctDetail.setAcctNo(outpAcctMaster.getAcctNo());
                   outpAcctDetail.setCosts(outpBillItems.getCosts());
                   outpAcctDetail.setIncome(outpBillItems.getCosts());
                   outpAcctDetail.setTallyFeeClass(outpBillItems.getSubjCode());
                  outpAcctDetailDao.insert(outpAcctDetail);
              }
           }
           num=outpRcptMasterDao.updateAcctNo(outpRcptMaster);

       }else{
           return "0";
       }
        return num+"";
    }

}
