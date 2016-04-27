package com.jims.sys.api;

import com.jims.sys.entity.DeptDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
public interface DeptDictApi {

    /**
     * 查询所有科室信息
     *
     * @return
     */
    public List<DeptDict> findAllList();

    public int add(DeptDict deptDict);

    public List<DeptDict> findProperty();

    public int update(DeptDict deptDict);

    public List<DeptDict> findParent();

    public  String save(DeptDict deptDict);

    public String delete(String ids);
}
