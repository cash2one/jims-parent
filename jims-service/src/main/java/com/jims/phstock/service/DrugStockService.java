/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugStockServiceApi;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugWorkCount;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品库存Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugStockService extends CrudImplService<DrugStockDao, DrugStock> implements DrugStockServiceApi{


    /**
     * 查询某库存一段时间内的工作量
     * @param storage 库存代码
     * @param startTime 开始时间
     * @param endDate 结束时间
     * @param orgId 所属组织结构
     * @return
     * @Author ztq
     *
     */
    @Override
    public List<DrugWorkCount> getWorkCountBy(String storage, String startTime, String endDate, String orgId) {
        return dao.getWorkCountBy(storage,startTime,endDate,orgId);
    }


    /**
     * 根据进价范围，或者库存单位，查询某一组织机构的库存量
     * @param storageCode
     * @param priceMin
     * @param priceMax
     * @param orgId
     * @return
     * @author ztq
     */
    @Override
    public List<DrugStockAllVo> listDrugStockAllVo(String storageCode, double priceMin, double priceMax, String orgId) {
        return dao.listDrugStockAllVo(storageCode,priceMin,priceMax,orgId);
    }


}