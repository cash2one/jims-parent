package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.clinic.entity.ClinicItemNameDict;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 * 消毒包处理表Api接口
 * @author louhuili
 * @version 2016-06-27
 */
public interface AsepsisAntiRecApi {

    /**
     * 获取某状态下的的消毒包(无菌物品包)
     * @author louhuili
     * @version 2016/6/27
     */
    public List<AsepsisAntiRec> getAsepsisAntiRecByState(AsepsisAntiRec aar);

    /**
     * 修改无菌物品
     * @author louhuili
     * @version 2016/6/27
     */
    public int saveClean(AsepsisAntiRec asepsisAntiRecVo);

    /**
     * 修改or增加or删除无菌物品
     * @author louhuili
     * @version 2016/6/28
     */
    public int saveAll(AsepsisDictVo<AsepsisAntiRec> asepsisAntiRecVo);
}
