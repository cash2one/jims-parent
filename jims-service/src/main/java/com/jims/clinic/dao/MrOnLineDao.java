/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.MrOnLine;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 联机病历描述DAO接口
 * @author zhaoning
 * @version 2016-06-29
 */
@MyBatisDao
public interface MrOnLineDao extends CrudDao<MrOnLine> {
    /**
     * 根据病人ID 查询 病历描述
     * @param patientId
     * @return
     * @author zhaoning
     */
    public MrOnLine getMrOnLByPatId(@Param("patientId")String patientId);
    /**
     * 修改 病历描述 移入(移除)病历时
     * @param mrOnLine
     * @author zhaoning
     */
    public void updateMrOnLByMoveIn(MrOnLine mrOnLine);

	
}