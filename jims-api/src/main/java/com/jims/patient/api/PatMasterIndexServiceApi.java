/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.patient.api;

import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.vo.LoginInfo;
import com.jims.patient.entity.PatMasterIndex;

import java.util.List;

/**
 * 病人主索引api
 * @author zhaoning
 * @version 2016-04-19
 */
public interface PatMasterIndexServiceApi {
    /**
     * 查询入院患者信息
     * @param patMasterIndex
     * @author CTQ
     * @return
     */
    public List<PatMasterIndex> findList(PatMasterIndex patMasterIndex);

    /**
     * 保存入院患者信息
     * @param patMasterIndex
     * @author CTQ
     * @return
     */
    public String saveMasterIndex(PatMasterIndex patMasterIndex,LoginInfo loginInfo);
    /**
     * 取消入院患者登记信息
     * @param ids
     * @author CTQ
     * @return
     */
    public String deleteMasterIndex(String ids);
    /**
     * 护理-查询待入科室床位病人列表
     * @author CTQ
     * @date 2016-06-06 09:23:31
     * @return
     */
    public List<ComeDeptVo> findWaitFrom();

}

