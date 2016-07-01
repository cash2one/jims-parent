/**
 * Created by Administrator on 2016/6/25.
 */
package com.jims.asepsis.service;

import com.jims.asepsis.api.AsepsisAntiRecApi;
import com.jims.asepsis.bo.AsepsisAntiRecBo;
import com.jims.asepsis.bo.AsepsisStockBo;
import com.jims.asepsis.dao.AsepsisAntiRecDao;
import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
* 无菌物品消毒表
* @author louhuili
* @version 2016-06-27
*/
@Service(version = "1.0.0")

public class AsepsisAntiRecImpl extends CrudImplService<AsepsisAntiRecDao, AsepsisAntiRec> implements AsepsisAntiRecApi {

    @Autowired
    private AsepsisAntiRecBo asepsisAntiRecBo;

    /**
     * 获取某状态下的消毒包(无菌物品包)
     *
     * @author louhuili
     * @version 2016/6/27
     */
    public List<AsepsisAntiRec> getAsepsisAntiRecByState(AsepsisAntiRec aar) {
        return asepsisAntiRecBo.getAsepsisAntiRecByState(aar);
    }

    public int saveClean(AsepsisAntiRec asepsisAntiRec){
        return asepsisAntiRecBo.saveClean(asepsisAntiRec);
    }
    public int saveAll(AsepsisDictVo<AsepsisAntiRec> asepsisAntiRecVo){
        return asepsisAntiRecBo.saveAll(asepsisAntiRecVo);
    }

    /**
     * 保存（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(AsepsisAntiRec entity) {
        try {
            asepsisAntiRecBo.save(entity);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}