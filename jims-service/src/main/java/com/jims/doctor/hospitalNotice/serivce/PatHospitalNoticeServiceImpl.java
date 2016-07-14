package com.jims.doctor.hospitalNotice.serivce;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.hospitalNotice.api.PatHospitalNoticeServiceApi;
import com.jims.doctor.hospitalNotice.bo.PatHospitalNoticeBo;
import com.jims.hospitalNotice.entity.PatHospitalNotice;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 住院通知单Service
 *
 * @author qinlongxin
 * @version 2016-06-01
 */
@Service(version = "1.0.0")
public class PatHospitalNoticeServiceImpl implements PatHospitalNoticeServiceApi {
    @Autowired
    private PatHospitalNoticeBo patHospitalNoticeBo;

    /**
     * 获取住院通知单信息
     *
     * @param id
     * @return
     * @author qinlongxin
     */
    @Override
    public PatHospitalNotice get(String id) {
        return patHospitalNoticeBo.get(id);
    }

    /**
     * 住院通知单分页
     *
     * @param page,patHospitalNotice
     * @return
     * @author qinlongxin
     */
    @Override
    public Page<PatHospitalNotice> findPage(Page<PatHospitalNotice> page, PatHospitalNotice patHospitalNotice) {
        return patHospitalNoticeBo.findPage(page, patHospitalNotice);
    }

    /**
     * 删除住院通知单
     *
     * @param ids 主键id集合
     * @return
     * @author qinlongxin
     */
    @Override
    public String delete(String ids) {
        return patHospitalNoticeBo.delete(ids);
    }

    /**
     * 保存住院通知单
     *
     * @param patHospitalNotice 实体类型
     * @return
     * @author qinlongxin
     */
    @Override
    public String save(PatHospitalNotice patHospitalNotice) {
        return  patHospitalNoticeBo.savePatHospitalNotice(patHospitalNotice);
    }
}