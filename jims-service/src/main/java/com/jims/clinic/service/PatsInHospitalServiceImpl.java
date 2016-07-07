package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.PatsInHospitalServiceApi;
import com.jims.clinic.bo.PatsInHospitalBo;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.web.impl.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 病人在院记录
 * @author CTQ
 * @date 2016-06-06 09:36:49
 */
@Service(version = "1.0.0")
public class PatsInHospitalServiceImpl implements PatsInHospitalServiceApi {
    @Autowired
    PatsInHospitalBo patsInHospitalBo;
    @Override
    public String saveHospInfo(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.saveHospInfo(comeDeptVo);
    }

    @Override
    public String turnOutDept(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.turnOutDept(comeDeptVo);
    }

    @Override
    public PatsInHospital findByPatientId(String patientId) {
        return patsInHospitalBo.findByPatientId(patientId);
    }


    /**
     * 出院-根据床位和病区查询病人信息
     * @param bedNo
     * @param wardCode
     * @author CTQ
     * @return
     */
    @Override
    public BaseDto searchInfoByParams(Integer bedNo, String wardCode) {
        return patsInHospitalBo.searchInfoByParams(bedNo,wardCode);
    }

    /**
     * 转出-根据床位和病区查询病人信息
     * @param bedNo
     * @param wardCode
     * @author CTQ
     * @return
     */
    @Override
    public BaseDto searchTurnOutInfoByParams(Integer bedNo, String wardCode) {
        return patsInHospitalBo.searchTurnOutInfoByParams(bedNo,wardCode);
    }

    /**
     * 取消转出-待专科病人列表
     * @author CTQ
     * @return
     */
    @Override
    public List<BaseDto> waitTurnOutList() {
        return patsInHospitalBo.waitTurnOutList();
    }

    /**
     * 取消入科病人列表
     * @author CTQ
     * @return
     */
    @Override
    public List<BaseDto> cacelPatientlist(String wardCode) {
        return patsInHospitalBo.cacelPatientlist(wardCode);
    }
    /**
     * 确认取消入科
     * @param comeDeptVo
     * @return
     */
    @Override
    public String cancelComeDept(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.cancelComeDept(comeDeptVo);
    }

    /**
     * 确认取消离院
     * @param comeDeptVo
     * @return
     */
    @Override
    public String cancelLeaveHosp(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.cancelLeaveHosp(comeDeptVo);
    }

    /**
     * 可被取消离院的病人列表
     * @param comeDeptVo
     * @return
     */
    @Override
    public List<BaseDto> cancelLeavePatientlist(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.cancelLeavePatientlist(comeDeptVo);
    }
}
