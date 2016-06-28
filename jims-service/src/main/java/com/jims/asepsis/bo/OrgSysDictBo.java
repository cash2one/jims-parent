package com.jims.asepsis.bo;

import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.dao.OrgSysDictDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 包单位维护bo
* @author yangruidong
* @version 2016-06-28
*/
@Service
@Transactional(readOnly = false)
public class OrgSysDictBo extends CrudImplService<OrgSysDictDao, OrgSysDict>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<OrgSysDict> list) {
        if(list != null && list.size() > 0) {
            for(OrgSysDict entity : list) {
                save(entity);
            }
        }
    }


    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict>  findUnits(OrgSysDict entity){
        return dao.findUnits(entity);
    }
}