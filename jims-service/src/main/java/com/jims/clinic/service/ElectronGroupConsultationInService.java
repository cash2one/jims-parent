/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ElectronGroupConsultationIntoApi;
import com.jims.clinic.bo.ElectronGroupConsultationInBo;
import com.jims.clinic.dao.ElectronGroupConsultationDao;
import com.jims.clinic.dao.ElectronGroupConsultationInDao;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 参与会诊信息Service
 * @author zhaoning
 * @version 2016-04-23
 */
@Service(version = "1.0.0")

public class ElectronGroupConsultationInService extends CrudImplService<ElectronGroupConsultationInDao, ElectronGroupConsultationIn> implements ElectronGroupConsultationIntoApi {
    @Autowired
    private ElectronGroupConsultationDao electronGroupConsultationDao;
    @Autowired
    private ElectronGroupConsultationInBo electronGroupConsultationInBo;
    /**
     * 根据会诊主表id查询参与会诊表信息
     * @author xueyx
     * @version 2016/4/22
     *@return 需要返回子表ElectronGroupConsultationIn
     *@return 相关主表的全部信息ElectronGroupConsultationIn.ElectronGroupConsultation
     * 包括主表及其相关的所有的子表（ElectronGroupConsultationIn）信息List
     */
    //@Override
    public ElectronGroupConsultationIn getByMain(ElectronGroupConsultation electronGroupConsultation) {
        ElectronGroupConsultationIn electronGroupConsultationIn = electronGroupConsultationInBo.getByMain(electronGroupConsultation);
        return electronGroupConsultationIn;
    };

    /**异步参与医生信息列表
     * 查询主表的所有的子表（ElectronGroupConsultationIn）信息List
     * @param “a.huizhen_id=#{id}”
     * @author xueyx
     * @version 2016/4/22
     */
    //@Override
    public List<ElectronGroupConsultationIn> getListByMain(ElectronGroupConsultation electronGroupConsultation){
        List<ElectronGroupConsultationIn> electronGroupConsultationInList = electronGroupConsultationInBo.getListByMain(electronGroupConsultation);
        return electronGroupConsultationInList;
    };

    /**异步参与医生信息列表
     * 查询所属同一主表的所有的子表（ElectronGroupConsultationIn）信息List
     * @param “ElectronGroupConsultationIn.id”
     * @author xueyx
     * @version 2016/4/22
     */
    //@Override
    public List<ElectronGroupConsultationIn> getBrotherList(ElectronGroupConsultationIn electronGroupConsultationIn){
        return electronGroupConsultationInBo.getBrotherList(electronGroupConsultationIn);
    }

    /**
     * 查询信息
     * @param-ElectronGroupConsultationIn.doctorId
     * @param-ElectronGroupConsultationIn.huizhenId
     * @return 需要返回主表
     */
    public ElectronGroupConsultationIn getByMainIdAndDoctorId(ElectronGroupConsultationIn electronGroupConsultationIn){
        return electronGroupConsultationInBo.getByMainIdAndDoctorId(electronGroupConsultationIn);
    }

    /**
     * 保存参与会诊人的意见
     * @param "ElectronGroupConsultationIn.id"
     * @param "ElectronGroupConsultationIn.inHuizhenyijian "
     * @author xueyx
     * @version 2016-04-26
     */
    public void saveOtherIdea(ElectronGroupConsultationIn electronGroupConsultationIn){
       electronGroupConsultationInBo.saveOtherIdea(electronGroupConsultationIn);
    }

    /**
     * 删除主表相关的子表
     * @param "electronGroupConsultation.id"
     * @author xueyx
     * @version 2016-04-26
     */
    public void delByMain(ElectronGroupConsultation electronGroupConsultation){
        electronGroupConsultationInBo.delByMain(electronGroupConsultation);
    }
}