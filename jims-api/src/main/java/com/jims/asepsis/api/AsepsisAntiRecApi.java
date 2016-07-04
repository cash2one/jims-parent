package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.vo.AsepsisAntiRecVo;
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
     * 无菌物品消毒包管理(清洗，打包，灭菌)(修改)
     * @param asepsisAntiRec
     * @return int
     * @author louhuili
     */
    public int saveClean(AsepsisAntiRec asepsisAntiRec);

    /**
     * 无菌物品消毒包管理(清洗，打包，灭菌)(新增，修改，删除)
     * @author louhuili
     * @version 2016/6/28
     */
    public int saveAll(AsepsisAntiRecVo<AsepsisAntiRec> asepsisAntiRecVo);
    /**
     * 保存（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(AsepsisAntiRec entity) ;
}
