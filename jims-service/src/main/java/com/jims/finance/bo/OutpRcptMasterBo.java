package com.jims.finance.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.dao.OutpRcptMasterDao;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊医疗收据记录
 *
 * @author PangQian
 * @date2016/5/31 0031
 */
@Service
@Component
@Transactional(readOnly = false)
public class OutpRcptMasterBo extends CrudImplService<OutpRcptMasterDao, OutpRcptMaster> {
    /**
     * 方法 findCharge 的能描述
     * 查询收费结账的收据
     * @param
     * @reurn
     * @thows
     * @author pq
     * @date 2016/5/31 0031
     */
    public OutpRcptMaster findCharge(OutpRcptMaster outpRcptMaster){
        return dao.findCharge(outpRcptMaster);
    }

}
