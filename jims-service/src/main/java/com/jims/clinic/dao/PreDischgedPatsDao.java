package com.jims.clinic.dao;

import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 出院通知单DAO接口
 * @author qinlongxin
 * @version 2016-06-02
 */
@MyBatisDao
public interface PreDischgedPatsDao extends CrudDao<PreDischgedPats> {
	public List<PreDischgedPatsVo> findPreDischList(String wardCode);
	public int delAll(String hospitalId);
}