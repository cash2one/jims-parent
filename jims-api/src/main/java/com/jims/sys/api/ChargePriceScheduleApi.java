package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargePriceSchedule;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargePriceScheduleApi {
    public Page<ChargePriceSchedule> findPage(Page<ChargePriceSchedule> page, ChargePriceSchedule chargePriceSchedule);
    public void save(ChargePriceSchedule chargePriceSchedule);
    public void delete(ChargePriceSchedule chargePriceSchedule);

}
