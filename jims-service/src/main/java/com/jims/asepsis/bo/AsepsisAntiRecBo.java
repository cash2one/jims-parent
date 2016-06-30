package com.jims.asepsis.bo;

import com.jims.asepsis.dao.AsepsisAntiRecDao;
import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Auser on
 * 消毒包处理Service
 *
 * @author louhuili
 * @version 2016/6/27.
 */
@Service
@Transactional(readOnly = false)
public class AsepsisAntiRecBo extends CrudImplService<AsepsisAntiRecDao, AsepsisAntiRec> {

    @Autowired
    private AsepsisAntiRecDao asepsisAntiRecDao;

    /**
     * 查询某状态下的消毒包（无菌物品的信息）集合
     * @return
     * @author louhuili
     */
    public List<AsepsisAntiRec> getAsepsisAntiRecByState(AsepsisAntiRec aar) {
        return asepsisAntiRecDao.getAsepsisAntiRecByState(aar);
    }
    /**
     * 修改无菌物品的当前状态(清洗，打包，灭菌)
     *
     * @param asepsisAntiRec
     * @return int
     * @author louhuili
     */
    public int saveClean(AsepsisAntiRec asepsisAntiRec){
        return asepsisAntiRecDao.saveClean(asepsisAntiRec);
    };
    /**
     * 修改无菌物品的当前状态(清洗，打包，灭菌)
     *
     * @param asepsisAntiRecVo
     * @return int
     * @author louhuili
     */
    public int saveAll(AsepsisDictVo<AsepsisAntiRec> asepsisAntiRecVo){
        List<AsepsisAntiRec> newUpdateDict = new ArrayList<AsepsisAntiRec>();
        List<AsepsisAntiRec> inserted = asepsisAntiRecVo.getInserted();
        List<AsepsisAntiRec> updated = asepsisAntiRecVo.getUpdated();
        List<AsepsisAntiRec> deleted = asepsisAntiRecVo.getDeleted();
        int num = 0;
        //插入
        for (AsepsisAntiRec asepsisAntiRec : inserted) {
            asepsisAntiRec.preInsert();
            asepsisAntiRec.setOrgId(asepsisAntiRecVo.getOrgId());
            num += dao.insert(asepsisAntiRec);
        }
        //更新
        for (AsepsisAntiRec asepsisAntiRec : updated) {
            asepsisAntiRec.preUpdate();
            num +=  dao.update(asepsisAntiRec);
        }
        //删除
        Map<String,String> ids = new HashMap<String, String>();

        for (AsepsisAntiRec asepsisAntiRec : deleted) {
            ids.put(asepsisAntiRec.getId(), asepsisAntiRec.getAsepsisCode());
        }

        for (Map.Entry<String, String> entry : ids.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            num += dao.delete(key);
//            asepsisAntiRecBo.deleteByCode(value);
        }
        return num;
    }

}

