/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.patient.api;

import com.jims.patient.entity.PatMasterIndex;

import java.util.List;

/**
 * 病人主索引api
 * @author zhaoning
 * @version 2016-04-19
 */
public interface PatMasterIndexServiceApi {

    public List<PatMasterIndex> findList(PatMasterIndex patMasterIndex);

}

