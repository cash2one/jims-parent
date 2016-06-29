package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisDetailDict;
import com.jims.asepsis.dao.AsepsisDetailDictDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 包内物品管理bo
* @author yangruidong
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisDetailDictBo extends CrudImplService<AsepsisDetailDictDao, AsepsisDetailDict>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisDetailDict> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisDetailDict entity : list) {
                save(entity);
            }
        }
    }
}