/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.web.impl.BaseDto;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 病人主索引DAO接口
 * @author zhaoning
 * @version 2016-04-19
 */
@MyBatisDao
public interface PatMasterIndexDao extends CrudDao<PatMasterIndex> {

    /**
     * 根据patientId更新数据
     * @author CTQ
     * @date 2016-05-26 11:01:39
     * @param id
     * @return
     */
    public Integer updateInpno(@Param("id")String id);

    /**
     * 护理-查询待入科室床位病人列表
     * @author CTQ
     * @date 2016-06-06 09:23:31
     * @return
     */
    public List<ComeDeptVo> findWaitFrom();
	
}