package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicAppoints;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预约挂号DAO接口
 * @author zhangyao
 * @version 2016-05-20
 */
@MyBatisDao
public interface ClinicAppointsDao extends CrudDao<ClinicAppoints> {
    /**
     * 根据条件查询，预约记录
     * @param name
     * @param cardNo
     * @param visitDate
     * @return
     */
	public List<ClinicAppoints> findListAppoints(@Param("name")String name,
                                                 @Param("cardNo")String cardNo,@Param("visitDate")String visitDate);
}