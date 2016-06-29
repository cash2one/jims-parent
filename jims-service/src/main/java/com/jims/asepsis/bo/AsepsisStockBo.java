package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.dao.AsepsisStockDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}