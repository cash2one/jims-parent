package com.jims.sys.api;

import com.jims.sys.entity.AdministrationDict;

import java.util.List;

/**
 * 给药途径字典表
 * Created by ztq on 2016/5/10.
 */
public interface AdministrationDictApi {

    //增删改查

    /**
     * 根据试用返回获取用药途径 ，传入是门诊则获取门诊+全部
     * 传入的是住院 = 住院+ 全部
     * 传入的全部 = 门诊 + 住院+全部
     * @param inpOrOutpFlag 全部（综合）、门诊、住院
     * @return
     */
    public List<AdministrationDict> listAdministrationByInpOrOutpFlag(String inpOrOutpFlag) ;

}
