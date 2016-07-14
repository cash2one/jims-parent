package com.jims.doctor.operation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.doctor.operation.bo.ScheduledOperationNameBo;
import com.jims.operation.api.ScheduledOperationNameApi;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
@Service(version = "1.0.0")
public class ScheduledOperationNameImpl implements ScheduledOperationNameApi {
    @Autowired
    private ScheduledOperationNameBo scheduledOperationNameBo;

    /**
     * 通过scheduleId获取手术安排
     * @param scheduleId
     * @return
     */
    public List<ScheduledOperationName> getOperationNameList(String scheduleId){
        List<ScheduledOperationName> scheduledOperationNameList = scheduledOperationNameBo.getOperationNameList(scheduleId);
        return scheduledOperationNameList;
    }
}
