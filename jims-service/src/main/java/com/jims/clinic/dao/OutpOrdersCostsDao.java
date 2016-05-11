/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 门诊医生收费明细DAO接口
 *
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpOrdersCostsDao extends CrudDao<OutpOrdersCosts> {

    /**
     * 根据就诊ID和主记录ID查询明细信息
     *
     * @param clinicId
     * @param masterId
     * @return
     */
    public List<OutpOrdersCosts> getOutpCosts(String clinicId, String masterId);

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
     * @param masterId
     * @return
     */
    public String deleteOutpOrders(String masterId);

    /**
     * 查询出最大的序号
     *
     * @param visitDate
     * @param visitNo
     * @param itemClass
     * @return
     */
    public Integer getMaxItemNo(Date visitDate, Integer visitNo, String itemClass);
}