package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.MrFeeClassDict;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface MrFeeClassDictApi {
    public Page<MrFeeClassDict> findPage(Page<MrFeeClassDict> page, MrFeeClassDict mrFeeClassDict);
    public void save(MrFeeClassDict mrFeeClassDict);
    public void delete(MrFeeClassDict mrFeeClassDict);
}
