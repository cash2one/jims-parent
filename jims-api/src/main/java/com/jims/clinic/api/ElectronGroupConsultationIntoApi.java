package com.jims.clinic.api;

import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.persistence.Page;

/**
 * Created by xueyx on 2016/4/22.
 */
public interface ElectronGroupConsultationIntoApi {
    /**
     * 根据会诊主表id查询参与会诊表信息
     * @author xueyx
     * @version 2016/4/22
     *@return 需要返回子表ElectronGroupConsultationIn
     *@return 相关主表的全部信息ElectronGroupConsultationIn.ElectronGroupConsultation
     * 包括主表及其相关的所有的子表（ElectronGroupConsultationIn）信息List
     */
    public ElectronGroupConsultationIn getByMain(ElectronGroupConsultation clectronGroupConsultation);

    /**
     * 保存或编辑
     * @author xueyx
     * @version 2016/4/22
     */
    public String save(ElectronGroupConsultationIn electronGroupConsultationIn);
}
