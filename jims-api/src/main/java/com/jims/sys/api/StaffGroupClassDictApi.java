package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.StaffGroupClassDict;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface StaffGroupClassDictApi {
    public Page<StaffGroupClassDict> findPage(Page<StaffGroupClassDict> page, StaffGroupClassDict staffGroupClassDict);
    public void save(StaffGroupClassDict staffGroupClassDict);
    public void delete(StaffGroupClassDict staffGroupClassDict);
}
