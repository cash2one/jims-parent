/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.persistence.BaseDao;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 病人就诊记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface ClinicMasterDao extends CrudDao<ClinicMaster> {
    /**
     * 病人信息更新
     * @param clinicMaster
     */
    public void updateMasterInfo(ClinicMaster clinicMaster);
    /**
     * 根据当前登录人 医生ID查询 门诊病人(待诊病人)
     * 查询的病人列表是  -----我的病人 列表   非  全科病人列表
     * @param doctorId
     * @return
     * @author zhaoning
     */
    public List<ClinicMaster>  getClinicBydoctor(@Param("doctorId")String doctorId,@Param("visitDept")String visitDept);

    /**
     * 根据当前登录人 医生ID 查询 门诊病人（已诊病人）
     * @param doctorId
     * @return
     * @author zhaoning
     */
    public List<ClinicMaster> getClinicMasterDiagnosed(@Param("doctorId")String doctorId,@Param("visitDept")String visitDept);

    /**
     * 根据 就诊日期，就诊序号 ，查询 有关退号的基本信息
     * @param visitDate
     * @param visitNo
     * @author zhaoning
     * @return
     */
    public ClinicMaster getClinicMaster(@Param("visitDate")String visitDate,@Param("visitNo")Integer visitNo);

    /**
     * 查询出最大的 visitNo
     * @return
     */
    public Integer  getMaxVisitNO();

    /**
     * 退号时 更新 就诊信息
     * @param visitDate
     * @param visitNo
     * @param returnedDate
     * @param returnedOperator
     */
    public void updateClinicMasterByReturn(@Param("visitDate")String  visitDate,@Param("visitNo")Integer visitNo,@Param("returnedDate")Date returnedDate,@Param("returnedOperator")String returnedOperator);

    /**
     * 通过就诊号与机构ID获取就诊记录
     * @param orgId
     * @param clinicNo
     * @return
     */
    public BaseDto getClinicMasterCost(@Param("orgId")String orgId,@Param("clinicNo")String clinicNo);

    public List<BaseDto> getClinicMasterCostAll(@Param("id")String id);

    /**
     * 根据参数获取费用信息
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public ClinicMaster getTotalAccount(@Param("operator")String operator,@Param("registeringDate")String registeringDate);

    /**
     * 根据操作员和挂号时间查询挂号数量
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public Double getRegiNum(@Param("operator")String operator,@Param("registeringDate")String registeringDate);

    /**
     * 根据操作员和挂号时间查询并按照支付方式分组数据
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public List<ClinicMaster> getGroupData(@Param("operator")String operator,@Param("registeringDate")String registeringDate);
    /**
     * 根据参数获取检查项目费用信息
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public ClinicMaster getCheckItem(@Param("operator")String operator,@Param("registeringDate")String registeringDate);

    /**
     * 根据参数更新信息
     * @param acctNo
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public int updateMaster(@Param("acctNo")String acctNo,@Param("operator")String operator,@Param("registeringDate")String registeringDate);
}