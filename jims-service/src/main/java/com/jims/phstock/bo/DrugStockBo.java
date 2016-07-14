package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugStockVo;
import com.jims.phstock.vo.DrugWorkCount;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 保存  增删改
     *
     * @param drugStockVo
     * @return
     * @author yangruidong
     */
    public List<DrugStock> saveAll(DrugStockVo<DrugStock> drugStockVo) {
        List<DrugStock> newUpdateDict = new ArrayList<DrugStock>();
        List<DrugStock> inserted = drugStockVo.getInserted();
        List<DrugStock> updated = drugStockVo.getUpdated();
        List<DrugStock> deleted = drugStockVo.getDeleted();
        //插入
        for (DrugStock drugStock : inserted) {
            drugStock.preInsert();
            drugStock.setOrgId(drugStockVo.getOrgId());
            int num = dao.insert(drugStock);
        }
        //更新
        for (DrugStock drugStock : updated) {
            drugStock.preUpdate();
            int num = dao.update(drugStock);
        }
        //删除
        Map<String,String> ids = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : ids.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            dao.delete(key);
//            asepsisDetailDictDao.deleteByCode(value);
        }
        return newUpdateDict;
    }
}
