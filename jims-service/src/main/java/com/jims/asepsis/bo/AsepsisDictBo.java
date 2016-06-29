package com.jims.asepsis.bo;

import com.jims.asepsis.dao.AsepsisDetailDictDao;
import com.jims.asepsis.entity.AsepsisDict;
import com.jims.asepsis.dao.AsepsisDictDao;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 包名称维护bo
* @author yangruidong
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisDictBo extends CrudImplService<AsepsisDictDao, AsepsisDict>{

    @Autowired
    private AsepsisDetailDictDao asepsisDetailDictDao;
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

    /**
     * 根据科室和orgId 查询科室下的包
     * @param entity
     * @return
     */
    public List<AsepsisDict> findPageByDept(AsepsisDict entity){
        return dao.findPageByDept(entity);
    }

    /**
     * 保存  增删改
     *
     * @param asepsisDictVo
     * @return
     * @author yangruidong
     */
    public List<AsepsisDict> saveAll(AsepsisDictVo<AsepsisDict> asepsisDictVo) {
        List<AsepsisDict> newUpdateDict = new ArrayList<AsepsisDict>();
        List<AsepsisDict> inserted = asepsisDictVo.getInserted();
        List<AsepsisDict> updated = asepsisDictVo.getUpdated();
        List<AsepsisDict> deleted = asepsisDictVo.getDeleted();
        //插入
        for (AsepsisDict asepsisDict : inserted) {
            asepsisDict.preInsert();
            asepsisDict.setOrgId(asepsisDictVo.getOrgId());
            int num = dao.insert(asepsisDict);
        }
        //更新
        for (AsepsisDict asepsisDict : updated) {
            asepsisDict.preUpdate();
            int num = dao.update(asepsisDict);
        }
        //删除
        Map<String,String> ids = new HashMap<String, String>();

        for (AsepsisDict asepsisDict : deleted) {
            ids.put(asepsisDict.getId(), asepsisDict.getAsepsisCode());
        }

        for (Map.Entry<String, String> entry : ids.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            dao.delete(key);
            asepsisDetailDictDao.deleteByCode(value);
        }
        return newUpdateDict;
    }

    /**
     * 检索有库存的数据
     * @param entity
     * @return
     */
    public List<AsepsisDict> findListHasStock(AsepsisDict entity){
        return dao.findListHasStock(entity);
    }

}