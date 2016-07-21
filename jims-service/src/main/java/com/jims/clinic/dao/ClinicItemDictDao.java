package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 诊疗项目DAO接口
 * @author lgx
 * @version 2016-04-28
 */
@MyBatisDao
public interface ClinicItemDictDao extends CrudDao<ClinicItemDict> {
    /**
     * 查询有相同编码或名称的数据
     * @param entity
     * @return
     */
    public List<ClinicItemDict> findExisted(ClinicItemDict entity);
    /**
     *  查询诊疗项目列表通过组织机构id
     * @param orgId 组织机构id
     * @return
     * @author txb
     */
    public List<ClinicItemDict> itemListByOrgId(@Param("orgId")String orgId,@Param("q")String q);
}