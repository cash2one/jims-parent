package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.phstock.api.DrugStockServiceApi;
import com.jims.phstock.bo.DrugStockBo;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugWorkCount;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 药品库存Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
public class DrugStockService implements DrugStockServiceApi{

    @Autowired
    private DrugStockBo bo;

    @Override
    public String save(DrugStock drugStock) {
        try {
            bo.save(drugStock);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }

    @Override
    public Page<DrugStock> findPage(Page<DrugStock> page, DrugStock drugStock) {
        return bo.findPage(page,drugStock);
    }

    @Override
    public List<DrugStock> findList(DrugStock drugStock) {
        return bo.findList(drugStock);
    }

    @Override
    public DrugStock get(String id) {
        return bo.get(id);
    }

    @Override
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }

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
        return bo.getWorkCountBy(storage,startTime,endDate,orgId);
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
        return bo.listDrugStockAllVo(storageCode,priceMin,priceMax,orgId);
    }

    /**
     * 检索有库存的药品库存
     * @param drugStock
     * @return
     */
    public List<DrugStock> findListHasStock(DrugStock drugStock){
        return bo.findListHasStock(drugStock);
    };
}