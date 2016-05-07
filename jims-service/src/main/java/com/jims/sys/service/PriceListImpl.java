package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.PriceList;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 价格表Service
 * @author 罗海昆
 * @version 2016-04-26
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class PriceListImpl extends CrudImplService<PriceListDao, PriceList> implements PriceListApi {
    /**
     * 通过项目代码对照项目价格
     * @param clinicItemCode 项目代码
     * @param orgId 机构代码
     * @return
     */
    @Override
    public List<PriceList> findListByItem(String clinicItemCode, String orgId) {
        return dao.findListByItem(clinicItemCode,orgId);
    }
}