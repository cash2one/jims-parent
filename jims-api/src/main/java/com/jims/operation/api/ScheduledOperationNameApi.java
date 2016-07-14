package com.jims.operation.api;

import com.jims.operation.entity.ScheduledOperationName;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 * 手术名称安排
 */
public interface ScheduledOperationNameApi {
    /**
     * 通过scheduleId获取手术安排
     * @param scheduleId
     * @return
     */
    public List<ScheduledOperationName> getOperationNameList(String scheduleId);
}
