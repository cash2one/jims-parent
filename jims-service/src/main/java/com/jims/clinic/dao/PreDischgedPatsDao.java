package com.jims.clinic.dao;

import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 出院通知单DAO接口
 * @author qinlongxin
 * @version 2016-06-02
 */
@MyBatisDao
public interface PreDischgedPatsDao extends CrudDao<PreDischgedPats> {

    /**
     * 查询病人是否有出院通知单
     * @param patientId
     * @return
     */
    public Integer findByPatientId(@Param("patientId")String patientId);
    public List<PreDischgedPatsVo> findPreDischList(String wardCode);
    public int delAll(String hospitalId);
}