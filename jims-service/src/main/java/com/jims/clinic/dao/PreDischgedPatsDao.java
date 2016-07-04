package com.jims.clinic.dao;

import com.jims.clinic.entity.PreDischgedPats;
import com.jims.clinic.vo.PreDischgedPatsVo;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
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

    /**
     * 查询待出院的病人
     * @param wardCode
     * @author pq
     * @return
     */
    public List<PreDischgedPatsVo> findPreDischList(String wardCode);

    /**
     * 查询科室下所有的
     * @param patientId
     * @param wardCode
     * @return
     */
    public List<PreDischgedPatsVo> findPreList(@Param("patientId")String patientId,@Param("wardCode")String wardCode);
    public int delAll(String hospitalId);
}