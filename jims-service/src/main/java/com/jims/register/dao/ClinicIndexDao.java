/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.entity.ClinicIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 号别DAO接口
 * @author zhaoning
 * @version 2016-05-17
 */
@MyBatisDao
public interface ClinicIndexDao extends CrudDao<ClinicIndex> {
    //获取号别价格
    public BaseDto getCost(String id);

    /**
     * 根据 号类ID 查询号别集合
     * @param clinicTypeId
     * @return
     * @author zhaoning
     */
    public List<ClinicIndex> getClinicIndexs(@Param("clinicTypeId")String clinicTypeId);
}