package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.dao.AsepsisStockDao;
import com.jims.asepsis.vo.AsepsisStockVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* 包库存初始化bo
* @author yangruidong
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisStockBo extends CrudImplService<AsepsisStockDao, AsepsisStock>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisStock> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisStock entity : list) {
                save(entity);
            }
        }
    }


    /**
     * 保存  增删改
     *
     * @param asepsisStockVo
     * @return
     * @author yangruidong
     */
    public List<AsepsisStock> saveAll(AsepsisStockVo<AsepsisStock> asepsisStockVo) {
        List<AsepsisStock> newUpdateDict = new ArrayList<AsepsisStock>();
        List<AsepsisStock> inserted = asepsisStockVo.getInserted();
        List<AsepsisStock> updated = asepsisStockVo.getUpdated();
        List<AsepsisStock> deleted = asepsisStockVo.getDeleted();
        //插入
        for (AsepsisStock asepsisStock : inserted) {
            asepsisStock.preInsert();
            asepsisStock.setOrgId(asepsisStockVo.getOrgId());
            int num = dao.insert(asepsisStock);
        }
        //更新
        for (AsepsisStock asepsisStock : updated) {
            asepsisStock.preUpdate();
            int num = dao.update(asepsisStock);
        }
        //删除
        List<String> ids=new ArrayList<String>();
        for (AsepsisStock asepsisStock : deleted) {
           ids.add(asepsisStock.getId());
        }
        for(String id:ids)
        {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 检索有库存的
     * @param entity
     * @return
     */
    public List<AsepsisStock> findListHasStock(AsepsisStock entity){
        return dao.findListHasStock(entity);
    }
    /**
     * 检索过期的
     * @param entity
     * @return
     */
    public List<AsepsisStock> findListOver(AsepsisStock entity){
        return dao.findListOver(entity);
    }

}