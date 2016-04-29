/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参与会诊信息DAO接口
 * @author zhaoning
 * @version 2016-04-23
 */
@MyBatisDao
public interface ElectronGroupConsultationInDao extends CrudDao<ElectronGroupConsultationIn> {
    /**
     * 根据会诊ID 查询参与会诊信息
     * @param consulaionId
     * @return
     */
    public  ElectronGroupConsultationIn  getByMain(@Param("consulaionId")String consulaionId);

    /**异步参与医生信息列表
     * 查询主表的所有的子表（ElectronGroupConsultationIn）信息List
     * @param “a.huizhen_id=#{id}”
     * @author xueyx
     * @version 2016/4/22
     */
    public List<ElectronGroupConsultationIn> getListByMain(ElectronGroupConsultation electronGroupConsultation);

    /**异步参与医生信息列表
     * 查询所属同一主表的所有的子表（ElectronGroupConsultationIn）信息List
     * @param “ElectronGroupConsultationIn.id”
     * @author xueyx
     * @version 2016/4/22
     */
    public List<ElectronGroupConsultationIn> getBrotherList(ElectronGroupConsultationIn electronGroupConsultationIn);

    /**
     * 查询信息
     * @param-ElectronGroupConsultationIn.doctorId
     * @param-ElectronGroupConsultationIn.huizhenId
     * @return 需要返回主表
     */
    public ElectronGroupConsultationIn getByMainIdAndDoctorId(ElectronGroupConsultationIn electronGroupConsultationIn);

    /**
     * 保存参与会诊人的意见
     * @param "ElectronGroupConsultationIn.id"
     * @param "ElectronGroupConsultationIn.inHuizhenyijian "
     * @author xueyx
     * @version 2016-04-26
     */
    public void saveOtherIdea(ElectronGroupConsultationIn electronGroupConsultationIn);

    /**
     * 删除主表相关的子表
     * @param "electronGroupConsultation.id"
     * @author xueyx
     * @version 2016-04-26
     */
    public void delByMain(ElectronGroupConsultation electronGroupConsultation);
}