package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisDict;
import com.jims.asepsis.dao.AsepsisDictDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 包名称维护bo
* @author yangruidong
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisDictBo extends CrudImplService<AsepsisDictDao, AsepsisDict>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisDict> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisDict entity : list) {
                save(entity);
            }
        }
    }
}