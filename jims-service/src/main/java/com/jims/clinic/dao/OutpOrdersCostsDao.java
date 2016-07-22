/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 门诊医生收费明细DAO接口
 *
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpOrdersCostsDao extends CrudDao<OutpOrdersCosts> {

    /**
     * 根据就诊ID和医嘱号查询明细信息
     *
     * @param clinicId
     * @param serialNo
     * @return
     */
    public List<OutpOrdersCosts> getOutpCosts(@Param("serialNo")String serialNo,@Param("clinicId")String clinicId);

    /**
     * 保存门诊医师收费明细
     *
     * @param outpOrdersCosts
     * @return
     */
    public void saveOrdersCosts(OutpOrdersCosts outpOrdersCosts);

    public List<OutpOrdersCosts> loadOutpOrders(Date visitDate, Integer visitNo, String itemClass);

    /**
     * 查询最大医嘱号
     *
     * @return
     */
    public Integer getMaxOrderNo(@Param("clinicId") String clinicId,@Param("orgId")String orgId);

    /**
     * 查询出最大的流水号
     *
     * @return
     */
    public Integer getSerialNo();

    /**
     * 删除门诊医师收费
     *
     * @param serialNo
     * @return
     */
    public int deleteOutpOrdersCosts(@Param("serialNo")String serialNo);

    /**
     * 查询出最大的序号
     *
     * @param visitDate
     * @param visitNo
     * @param itemClass
     * @return
     */
    public Integer getMaxItemNo(Date visitDate, Integer visitNo, String itemClass);


    /**
     * 删除门诊收费明细（处置治疗）
     * @param itemCode
     * @return
     * pq
     */
    public int deleteTreatment(@Param(value = "itemCode")String itemCode);

    /**
     * @param outpOrdersCosts 传递参
     * @return Integer    返回类型
     *@throws
     * @Title: removeByParams
     * @Descripion: (根据参数[处方Id]删除收费项目)
     * @author CTQ
     * @date 2016/5/9
     */
    public Integer removeByMasterId(OutpOrdersCosts outpOrdersCosts);

    /**
     * 获取个人门诊所有收费项目
     * @param clinicId
     * @return
     */

    public List<BaseDto> getCostAll(@Param(value = "clinicId")String clinicId);

    /**
     * 确认收费
     * @param map
     */
    public void confirmPay(Map<String ,Object> map);
}