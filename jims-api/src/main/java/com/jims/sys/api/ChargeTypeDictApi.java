package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.ChargeTypeDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface ChargeTypeDictApi {
    /**
     * 查询字段列表
     * @param page
     * @param chargeTypeDict
     * @return
     */

    public Page<ChargeTypeDict> findPage(Page<ChargeTypeDict> page, ChargeTypeDict chargeTypeDict);

    /**
     * 保存修改数据
     * @param chargeTypeDict
     * @return
     */
    public String save(ChargeTypeDict chargeTypeDict);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public ChargeTypeDict get(String id);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();


}
