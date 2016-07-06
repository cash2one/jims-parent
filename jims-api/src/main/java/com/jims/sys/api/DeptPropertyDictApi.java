package com.jims.sys.api;

import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.vo.OrgDeptPropertyDictVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public interface DeptPropertyDictApi {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public OrgDeptPropertyDict get(String id);

    /**
     * 查询类型列表
     *
     * @return
     */
    public Page<OrgDeptPropertyDict> findPage(Page<OrgDeptPropertyDict> page, OrgDeptPropertyDict orgDeptPropertyDict);


    /**
     * 保存修改方法
     *
     * @param
     */
    public StringData add(OrgDeptPropertyDict orgDeptPropertyDict);


    /**
     * 保存  增删改
     * @param orgDeptPropertyDictVo
     * @return
     *  @author  yangruidong
     */
    public List<OrgDeptPropertyDict> saveAll(OrgDeptPropertyDictVo<OrgDeptPropertyDict> orgDeptPropertyDictVo);

    /**
     * 删除方法
     *
     * @param ids
     */
    public String delete(String ids);

    /**
     * 根据属性类型查询属性名称
     * @param propertyType
     * @return
     */
    public List<OrgDeptPropertyDict> findNameByType(String propertyType,String orgId);

    /**
     * 根据属性类型和属性值来查询属性名称
     * @param propertyType
     * @param propertyValue
     * @return
     */
    public OrgDeptPropertyDict findNameByTypeAndValue(String propertyType, String propertyValue,String orgId);

    /**
     * 查询所有的属性类型
     * @return
     */
    public List<OrgDeptPropertyDict> findProperty(String orgId);

    /**
     * 查询所有的科室信息
     * @return
     */
   // public List<OrgDeptPropertyDict> findList();

    /**
     * 根据条件查询所有的属性信息
     * @param orgDeptPropertyDict
     * @return
     */
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict);

    /**
     * 查询属性名称
     * @param
     * @return
     */
    public List<OrgDeptPropertyDict> findName(String propertyType,String orgId);

    /**
     * 查询最大的排序值
     *
     * @param
     * @return
     */
    public OrgDeptPropertyDict findSort(String orgId);
}
