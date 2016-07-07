/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 会诊记录DAO接口
 * @author zhaoning
 * @version 2016-04-23
 */
@MyBatisDao
public interface ElectronGroupConsultationDao extends CrudDao<ElectronGroupConsultation> {

    /**
     * 保存或编辑会诊主表方法
     * @paramElectronGroupConsultation
     */
    public String saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation);

    /**
     * 发布会诊主表方法
     * @param "ElectronGroupConsultation.id 会诊主表id"
     * @author xueyx
     * @version 2016-04-26
     */
    public void fabu(ElectronGroupConsultation electronGroupConsultation);

    /**
     * 保存发布会诊人的意见
     * @param "ElectronGroupConsultation.id 会诊主表id"
     * @param "ElectronGroupConsultation.huizhenyijian "
     * @author xueyx
     * @version 2016-04-26
     */
    public Integer saveMainOnly(ElectronGroupConsultation electronGroupConsultation);
}