package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 诊疗项目与价表对照DAO接口
 * @author lgx
 * @version 2016-04-28
 */
@MyBatisDao
public interface ClinicVsChargeDao extends CrudDao<ClinicVsCharge> {
    /**
     * 不根据主键进行删除
     * @param entity
     * @return
     */
    public int deleteNoId(ClinicVsCharge entity);

    /**
     * 根据组织机构ID、项目代码查询诊疗项目与价表对照
     * @param orgId  组织机构ID
     * @param itemCode  项目代码
     * @return
     * @author fengyuguang
     */
    public ClinicVsCharge findByOrgIdItemCode(String orgId, String itemCode);
}