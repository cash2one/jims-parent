/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.patient.api;

import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;

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
    public String saveMasterIndex(PatMasterIndex patMasterIndex);
    /**
     * 取消入院患者登记信息
     * @param ids
     * @author CTQ
     * @return
     */
    public String deleteMasterIndex(String ids);

}

