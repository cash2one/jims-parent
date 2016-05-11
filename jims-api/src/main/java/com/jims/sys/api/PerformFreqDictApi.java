package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.PerformFreqDict;

import java.util.List;

/**
 * 检验标本
 * Created by Administrator on 2016/4/18.
 */
public interface PerformFreqDictApi {
    /**
     * 查询字段列表
     * @param page
     * @param performFreqDict
     * @return
     */
    public Page<PerformFreqDict> findPage(Page<PerformFreqDict> page, PerformFreqDict performFreqDict);

    /**
     * 保存修改数据
     * @param performFreqDict
     * @return
     */
    public String save(PerformFreqDict performFreqDict);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public PerformFreqDict get(String id);

    /**
     * 检索频次数据
     * @param entity
     * @return
     */
    public List<PerformFreqDict> findList(PerformFreqDict entity);
}
