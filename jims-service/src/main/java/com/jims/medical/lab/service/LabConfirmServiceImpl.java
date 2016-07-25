package com.jims.medical.lab.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.vo.LoginInfo;
import com.jims.lab.api.LabConfirmServiceApi;
import com.jims.lab.entity.LabTestMaster;
import com.jims.medical.lab.bo.LabConfirmBo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 * 检验确认 service
 */
@Service(version = "1.0.0")
public class LabConfirmServiceImpl implements LabConfirmServiceApi {
    @Autowired
    private LabConfirmBo labConfirmBo;

    /**
     * 检验确认 根据当前登录人所在执行科室 查询检验记录
     * @param performedBy
     * @return
     * @author zhaoning
     */
    @Override
    public List<LabTestMaster> getLabMaster(String performedBy,String inOrOut,String startTime,String endTime,String reqDept,String labNo,String patName,LoginInfo loginInfo) {
        return labConfirmBo.getLabMaster(performedBy,inOrOut,startTime,endTime,reqDept,labNo,patName,loginInfo);
    }

    /**
     * 检验确认
     * @param labTestMaster
     * @return
     * @author zhaoning
     */
    @Override
    public String confrimLab(LabTestMaster labTestMaster)throws Exception {
        return labConfirmBo.confirmLab(labTestMaster);
    }
}
