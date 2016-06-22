package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugWorkCount;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lgx on 2016/6/21.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugStockBo extends CrudImplService<DrugStockDao, DrugStock> {

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
    public List<DrugStockAllVo> listDrugStockAllVo(String storageCode, double priceMin, double priceMax, String orgId) {
        return dao.listDrugStockAllVo(storageCode,priceMin,priceMax,orgId);
    }

    /**
     * 检索有库存的药品库存
     * @param drugStock
     * @return
     */
    public List<DrugStock> findListHasStock(DrugStock drugStock){
        return dao.findListHasStock(drugStock);
    };
}
