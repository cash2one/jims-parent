package com.jims.clinic.bo;

import com.jims.clinic.dao.ElectronGroupConsultationDao;
import com.jims.clinic.dao.ElectronGroupConsultationInDao;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.clinic.entity.ElectronGroupConsultationIn;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
@Service
@Transactional(readOnly = false)
public class ElectronGroupConsultationInBo extends CrudImplService<ElectronGroupConsultationInDao,ElectronGroupConsultationIn> {
    @Autowired
    private ElectronGroupConsultationDao electronGroupConsultationDao;
    @Autowired
    private ElectronGroupConsultationInDao electronGroupConsultationInDao;

    /**
     * 根据会诊主表id查询参与会诊表信息
     * @author zp
     * @version 2016/7/7
     *@return 需要返回子表ElectronGroupConsultationIn
     *@return 相关主表的全部信息ElectronGroupConsultationIn.ElectronGroupConsultation
     * 包括主表及其相关的所有的子表（ElectronGroupConsultationIn）信息List
     */
    //@Override
    public ElectronGroupConsultationIn getByMain(ElectronGroupConsultation electronGroupConsultation) {
        ElectronGroupConsultationIn electronGroupConsultationIn = dao.getByMain(electronGroupConsultation.getId());
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
        List<ElectronGroupConsultationIn> electronGroupConsultationInList = dao.getListByMain(electronGroupConsultation);
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
        List<ElectronGroupConsultationIn> electronGroupConsultationInList = dao.getBrotherList(electronGroupConsultationIn);
        return electronGroupConsultationInList;
    }

    /**
     * 查询信息
     * @param-ElectronGroupConsultationIn.doctorId
     * @param-ElectronGroupConsultationIn.huizhenId
     * @return 需要返回主表
     */
    public ElectronGroupConsultationIn getByMainIdAndDoctorId(ElectronGroupConsultationIn electronGroupConsultationIn){
        ElectronGroupConsultationIn electronGroupConsultationIn1 = dao.getByMainIdAndDoctorId(electronGroupConsultationIn);
        return electronGroupConsultationIn1;
    }

    /**
     * 保存参与会诊人的意见
     * @param "ElectronGroupConsultationIn.id"
     * @param "ElectronGroupConsultationIn.inHuizhenyijian "
     * @author xueyx
     * @version 2016-04-26
     */
    public void saveOtherIdea(ElectronGroupConsultationIn electronGroupConsultationIn){
        dao.saveOtherIdea(electronGroupConsultationIn);
        electronGroupConsultationIn = get(electronGroupConsultationIn.getId());
        if(electronGroupConsultationIn!=null) {
            ElectronGroupConsultation electronGroupConsultation =electronGroupConsultationDao.
                    get(electronGroupConsultationIn.getHuizhenId());
            electronGroupConsultation.setIdeaFlag("1");
            electronGroupConsultationDao.update(electronGroupConsultation);
        }
    }

    /**
     * 删除主表相关的子表
     * @param "electronGroupConsultation.id"
     * @author xueyx
     * @version 2016-04-26
     */
    public void delByMain(ElectronGroupConsultation electronGroupConsultation){
        dao.delByMain(electronGroupConsultation);
    }
}
