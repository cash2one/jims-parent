package com.jims.phstock.dao;

import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.vo.OrdersDispInfo;
import com.jims.phstock.vo.PatientBaseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by heren on 2016/6/29.
 */
@MyBatisDao
public interface DrugDispDao {
    /**
     * 获取选择护理单当前在院病人
     * @param wardDeptCode 护理单元
     * @param deptCode     入院科室
     * @param orgId        组织机构
     * @param bedNos       床位
     * @return
     */
    public List<PatientBaseVo> findPatsInHospital(@Param("wardDeptCode")String wardDeptCode,
                                                   @Param("deptCode")String deptCode,
                                                   @Param("orgId")String orgId,
                                                   @Param("bedNos")List<String> bedNos);

    /**
     * 查询某一个病人的摆药医嘱
     * @param patientId
     * @param visitId
     * @param orgIdTemp
     * @return
     */
    public List<OrdersDispInfo> findOrdersDispInfos(String patientId, int visitId, String orgIdTemp);

    /**
     * 根据医嘱内容查询概要的价格记录
     * @param drugCode
     * @param drugSpec
     * @param orgId
     * @return
     */
    public DrugPriceList findDrugPriceList(String drugCode, String drugSpec, String orgId);
}
