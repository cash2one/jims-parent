package com.jims.finance.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.dao.OutpBillItemsDao;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * OutpBillItemsBo
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Service
@Component
@Transactional(readOnly = false)
public class OutpBillItemsBo extends CrudImplService<OutpBillItemsDao, OutpBillItems> {

    /**
     * 方法 findItems的功能描述
     * 门诊-收费结账
     * @param outpRcptMaster
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public List<OutpBillItems> findItems(OutpRcptMaster outpRcptMaster){
        return  dao.findItems(outpRcptMaster);
    }
}
