/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.prescription.dao;

import com.jims.prescription.entity.OutpPresc;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 处方医嘱明细记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpPrescDao extends CrudDao<OutpPresc> {

    /**
     * 根据就诊记录ID查询病人处方主记录
     * @param orgId clinicId
     * @updateBy CTQ
     * @return
     */
    public List<OutpPresc> getOutpPresc(@Param("orgId")String orgId,@Param("clinicId")String clinicId);

    /**
     * 根据参数查询医嘱组别
     * @param orgId clinicId
     * @updateBy CTQ
     * @return
     */
    public Integer getOrderNo(@Param("orgId")String orgId,@Param("clinicId")String clinicId);

    /**
     * @param outpPresc 传递参
     * @return List<OutpPresc>    返回类型
     * @Title: findListByParams
     * @Descripion: (根据条件查询处方相关信息)
     * @author CTQ
     * @date 2016/5/10
     */
    public List<OutpPresc> findListByParams(OutpPresc outpPresc);
    /**
     * @param       orgId      传递参数
     * @return java.lang.Integer    返回类型
     * @throws
     * @Title: getMaxPrescNo
     * @Description: (根据机构获取最大处方号并且+1，为下个新方做准备)
     * @author CTQ
     * @date 2016/5/12
     */
    public Integer getMaxPrescNo(@Param("orgId")String orgId);
    /**
     * @param       orgId,prescNo 传递参数
     * @return java.lang.Integer    返回类型
     * @throws
     * @Title: searchPrescNoIfExist
     * @Description: (查询机构下该处方号是否存在，存在则>0)
     * @author CTQ
     * @date 2016/5/12
     */
    public Integer searchPrescNoIfExist(@Param("orgId")String orgId,@Param("clinicId")String clinicId,@Param("prescNo")Integer prescNo);

}