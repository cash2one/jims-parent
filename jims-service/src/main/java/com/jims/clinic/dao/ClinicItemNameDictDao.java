package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 诊疗项目名称DAO接口
 * @author lgx
 * @version 2016-04-28
 */
@MyBatisDao
public interface ClinicItemNameDictDao extends CrudDao<ClinicItemNameDict> {

    /**
     * 查询检验项目
     * @param标本expand1
     * @param检验类别expand2
     * @param科室expand3
     * @author xueyx
     * @version 2016/5/06
     */
    public List<ClinicItemNameDict> selectLabItem(ClinicItemNameDict clinicItemNameDict);

    /**
     * 不根据主键进行删除
     * @param entity
     * @return
     */
    public int deleteNoId(ClinicItemNameDict entity);
}