package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.DeptVsWard;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface DeptVsWardApi {
    public Page<DeptVsWard> findPage(Page<DeptVsWard> page, DeptVsWard deptVsWard);
    public void save(DeptVsWard deptVsWard);
    public void delete(DeptVsWard deptVsWard);
}
