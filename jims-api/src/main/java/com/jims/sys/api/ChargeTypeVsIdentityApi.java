package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargeTypeVsIdentity;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargeTypeVsIdentityApi {
    public Page<ChargeTypeVsIdentity> findPage(Page<ChargeTypeVsIdentity> page, ChargeTypeVsIdentity chargeTypeVsIdentity);
    public void save(ChargeTypeVsIdentity chargeTypeVsIdentity);
    public void delete(ChargeTypeVsIdentity chargeTypeVsIdentity);
}
