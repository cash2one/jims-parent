package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.UnitInContract;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface UnitInContractApi {
    /**
     * 查询字段列表
     * @param page
     * @param unitInContract
     * @return
     */
    public Page<UnitInContract> findPage(Page<UnitInContract> page, UnitInContract unitInContract);

    /**
     * 保存修改数据
     * @param unitInContract
     * @return
     */
    public String save(UnitInContract unitInContract);

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
    public UnitInContract get(String id);
}
