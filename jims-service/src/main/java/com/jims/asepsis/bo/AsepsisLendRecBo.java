package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.asepsis.dao.AsepsisLendRecDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 借物还物bo
* @author lgx
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisLendRecBo extends CrudImplService<AsepsisLendRecDao, AsepsisLendRec>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisLendRec> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisLendRec entity : list) {
                save(entity);
            }
        }
    }

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId){
        return dao.getMaxDocumentNo(orgId);
    }
}