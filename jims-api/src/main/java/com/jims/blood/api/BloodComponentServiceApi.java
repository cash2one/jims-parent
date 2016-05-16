package com.jims.blood.api;

import com.jims.blood.entity.BloodComponent;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public interface BloodComponentServiceApi {
    /**
     * 获得血液成分列表
     * @return
     */
    public List<BloodComponent> getBloodComponent();
}
