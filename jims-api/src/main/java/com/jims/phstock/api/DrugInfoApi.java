package com.jims.phstock.api;

import com.jims.phstock.entity.DrugInfo;

/**
 * 药理信息维护
 * Created by 赵铁强 on 2016/5/16.
 */
public interface DrugInfoApi {

    /**
     * 根据药品代码，获取药品毒理信息
     * @param drugCode
     * @return
     * @author  ztq
     */
    public DrugInfo getDrugInfoByDrugCode(String drugCode) ;

    //增删改查

    /**
     * 保存药品毒理信息
     * @param drugInfo
     * @return
     * @author yangruidong
     */
    public String save(DrugInfo drugInfo);


}
