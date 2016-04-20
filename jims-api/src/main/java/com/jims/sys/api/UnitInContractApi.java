package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.UnitInContract;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface UnitInContractApi {
    public Page<UnitInContract> findPage(Page<UnitInContract> page, UnitInContract unitInContract);
    public void save(UnitInContract unitInContract);
    public void delete(UnitInContract unitInContract);
}
