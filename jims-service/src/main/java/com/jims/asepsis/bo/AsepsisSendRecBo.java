package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.dao.AsepsisSendRecDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 送物领物bo
* @author lgx
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisSendRecBo extends CrudImplService<AsepsisSendRecDao, AsepsisSendRec>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisSendRec> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisSendRec entity : list) {
                save(entity);
            }
        }
    }
}