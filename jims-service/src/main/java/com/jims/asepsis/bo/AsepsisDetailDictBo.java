package com.jims.asepsis.bo;

import com.jims.asepsis.entity.AsepsisDetailDict;
import com.jims.asepsis.dao.AsepsisDetailDictDao;
import com.jims.asepsis.vo.AsepsisDetailDictVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    /**
     * 保存  增删改
     *
     * @param asepsisDetailDictVo
     * @return
     * @author yangruidong
     */
    public List<AsepsisDetailDict> saveAll(AsepsisDetailDictVo<AsepsisDetailDict> asepsisDetailDictVo) {
        List<AsepsisDetailDict> newUpdateDict = new ArrayList<AsepsisDetailDict>();
        List<AsepsisDetailDict> inserted = asepsisDetailDictVo.getInserted();
        List<AsepsisDetailDict> updated = asepsisDetailDictVo.getUpdated();
        List<AsepsisDetailDict> deleted = asepsisDetailDictVo.getDeleted();
        //插入
        for (AsepsisDetailDict asepsisDetailDict : inserted) {
            asepsisDetailDict.preInsert();
            int num = dao.insert(asepsisDetailDict);
        }
        //更新
        for (AsepsisDetailDict asepsisDetailDict : updated) {
            asepsisDetailDict.preUpdate();
            int num = dao.update(asepsisDetailDict);
        }
        //删除
        List<String> ids=new ArrayList<String>();
        for (AsepsisDetailDict asepsisDetailDict : deleted) {
            ids.add(asepsisDetailDict.getId());
        }
        for(String id:ids)
        {
            dao.delete(id);
        }
        return newUpdateDict;
    }
}