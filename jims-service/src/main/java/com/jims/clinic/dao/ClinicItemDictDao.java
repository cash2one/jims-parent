package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.vo.ClinicItemPriceVo;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 诊疗项目DAO接口
 * @author lgx
 * @version 2016-04-28
 */
@MyBatisDao
public interface ClinicItemDictDao extends CrudDao<ClinicItemDict> {
    /**
     * 获取检查项目价格列表
     * @param orgId
     * @return
     */
    public List<ClinicItemPriceVo> itemListByOrgId(String orgId);
	
}