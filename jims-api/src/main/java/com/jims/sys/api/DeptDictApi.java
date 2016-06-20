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
    public List<DeptDict> findAllList(String orgId);



    /**
     * 查询所有的科室属性类型
     * @return
     */
    public List<DeptDict> findProperty();



    /**
     * 查询上级科室
     * @return
     */
    public List<DeptDict> findParent();

    /**
     * 查询某个机构的上级科室
     * @return
     */
    public List<DeptDict> findListParent(String orgId);

    /**
     * 保存或修改
     * @param deptDict
     * @return
     */
    public  String save(DeptDict deptDict);

    /**
     * 删除
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(String code);

    /**
     * 检索科室
     * @param dept
     * @return
     */
    public List<DeptDict> findList(DeptDict dept);

    /**
     * 查询所有科室
     * @return
     */
    public List<DeptDict> getList();

    /**
     * 查询所有科室
     * @return
     */
    public List<DeptDict> getList();
}
