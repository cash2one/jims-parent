package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by xueyx on 2016/4/22.
 */
public interface ElectronGroupConsultationIntoApi {
    /**
     * 根据会诊主表id查询参与会诊表信息
     * @author xueyx
     * @version 2016/4/22
     * @return 需要返回子表ElectronGroupConsultationIn
     * @return 相关主表的全部信息ElectronGroupConsultationIn.ElectronGroupConsultation
     * 包括主表及其相关的所有的子表（ElectronGroupConsultationIn）信息List
     */
    public ElectronGroupConsultationIn getByMain(ElectronGroupConsultation electronGroupConsultation);
    /**异步参与医生信息列表
     * 查询主表的所有的子表（ElectronGroupConsultationIn）信息List
     * @param “ElectronGroupConsultation.id”
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
     * 保存或编辑
     * @author xueyx
     * @version 2016/4/22
     */
    public String save(ElectronGroupConsultationIn electronGroupConsultationIn);

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
     * 删除会诊子表方法
     * @param id 会诊主表id
     */
    public String delete(String id);

    /**
     * 删除主表相关的子表
     * @param "electronGroupConsultation.id"
     * @author xueyx
     * @version 2016-04-26
     */
    public void delByMain(ElectronGroupConsultation electronGroupConsultation);
}
