/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicForRegist;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 生成号表DAO接口
 * @author zhaoning
 * @version 2016-05-18
 */
@MyBatisDao
public interface ClinicForRegistDao extends CrudDao<ClinicForRegist> {
    /**
     * 根据 出诊日期，门诊号名称，出诊时间 查询出最大的当前号
     * @param clinicDate
     * @param clinicLable
     * @param timeDesc
     * @return
     * @author zhaoning
     */
	public Integer currentNoMax(@Param("clinicDate")String clinicDate,@Param("clinicLable")String clinicLable,@Param("timeDesc")String timeDesc);

    /**
     * 根据 出诊日期，门诊号名称，出诊时间 查询号表对象
     * @param clinicDate
     * @param clinicLable
     * @param timeDesc
     * @return
     * @author zhaoning
     */
    public List<ClinicForRegist> getClinicForReg(@Param("clinicDate")String clinicDate,@Param("clinicLable")String clinicLable,@Param("timeDesc")String timeDesc);

    /**
     * 查询当天所有的号
     * @param date
     * @return
     */
    public List<ClinicForRegist> findListReg(ClinicForRegist clinicForRegist);

    /**
     * 根据，就诊日期，号别名称，就诊时间,更新号表数据---当日挂号
     * @param clinicDate
     * @param clinicLabel
     * @param timeDesc
     * @author zhaoning
     */
    public void updateRegister(@Param("clinicDate")String clinicDate,@Param("clinicLabel")String clinicLabel,@Param("timeDesc")String timeDesc);

    /**
     * 退号时更新号表的信息
     * @param clinicLabel
     * @param timeDesc
     * @param clinicDate
     * @author zhaoning
     */
    public void updateRegisterByReturn(@Param("clinicLabel")String clinicLabel,@Param("timeDesc")String timeDesc,@Param("clinicDate")String clinicDate);

    /**
     * 根据，就诊日期，号别名称，就诊时间,更新号表数据---预约挂号
     * @param clinicDate
     * @param clinicLabel
     * @param timeDesc
     * @author zhaoning
     */
    public  void updateRegisterByAppoint(@Param("clinicDate")String clinicDate,@Param("clinicLabel")String clinicLabel,@Param("timeDesc")String timeDesc);

    /**
     * 根据，就诊日期，号别名称，就诊时间,更新号表数据---预约挂号(退号)
     * @param clinicDate
     * @param clinicLabel
     * @param timeDesc
     */
    public void updateRegByAppointReturn(@Param("clinicDate")String clinicDate,@Param("clinicLabel")String clinicLabel,@Param("timeDesc")String timeDesc);
}
