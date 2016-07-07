package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.common.persistence.Page;

/**
 * Created by xueyx on 2016/4/22.
 */
public interface ElectronGroupConsultationApi {

    /**
     * 对当前患者-当前医生用户的发起的会诊+其他医生用户发起的并且发布的会诊（当前医生用户参与）
     * @param对当前患者 patientId
     * @param当前医生用户 userId
     * @return 需要多返回标志位：groupatate（1、发起会诊，2、参与会诊）
     * @author xueyx
     * @version 2016/4/22
     */
    public Page<ElectronGroupConsultation> findPage(Page<ElectronGroupConsultation> page, ElectronGroupConsultation electronGroupConsultation);
    /**
     * 保存或编辑
     * 整个会诊信息主表、字表list
     * @param会诊主表ElectronGroupConsultation
     * @param参与子表List
     * @author xueyx
     * @version 2016/4/22
     */
    public String saveGroupConsulation(ElectronGroupConsultation electronGroupConsultation);
    /**
     * 根据会诊主表ID 查询会诊信息
     * @param-ElectronGroupConsultation.id
     * @return 需要返回主表
     */
    public ElectronGroupConsultation get(String id);

    /**
     * 根据保存会诊主表
     * @param-ElectronGroupConsultation
     * @return
     */
    //public String save(ElectronGroupConsultation electronGroupConsultation);

    /**
     * 删除会诊主表方法
     * @param id 会诊主表id
     */
    public String delete(String id);

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
    public String saveMainOnly(ElectronGroupConsultation electronGroupConsultation);

}
