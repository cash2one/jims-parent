package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.PerformFreqDict;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface PerformFreqDictApi {
    public Page<PerformFreqDict> findPage(Page<PerformFreqDict> page, PerformFreqDict performFreqDict);
    public void save(PerformFreqDict performFreqDict);
    public void delete(PerformFreqDict performFreqDict);
}
