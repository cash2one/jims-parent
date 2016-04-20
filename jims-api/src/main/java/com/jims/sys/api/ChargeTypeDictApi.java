package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargeTypeDict;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargeTypeDictApi {
    public Page<ChargeTypeDict> findPage(Page<ChargeTypeDict> page, ChargeTypeDict chargeTypeDict);
    public void save(ChargeTypeDict chargeTypeDict);
    public void delete(ChargeTypeDict chargeTypeDict);
}
