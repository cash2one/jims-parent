/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ElectronLeaveHospital;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;


/**
 * 出院记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface ElectronLeaveHospitalDao extends CrudDao<ElectronLeaveHospital> {
    /**
     * 根据住院ID查询出院记录
     * @param patVisitId
     * @return
     * @Author zhaoning
     * @version 2016-04-21
     */
    public ElectronLeaveHospital getLeaveHosByVisit(@Param("patVisitId")String patVisitId);
}