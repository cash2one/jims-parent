package com.jims.blood.api;

import com.jims.blood.entity.BloodCapacity;

import java.util.List;

/**
 * Created by qinlongxin on 2016/4/28.
 */
public interface BloodCapacityServiceApi {
    /**
     * 根据用血申请单号查询用血数量申请
     */
    public List<BloodCapacity> getBloodCapacityList(BloodCapacity bloodCapacity);
}
