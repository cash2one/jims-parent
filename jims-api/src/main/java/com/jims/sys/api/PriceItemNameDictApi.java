package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.PriceItemNameDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface PriceItemNameDictApi {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public PriceItemNameDict get(String id);

    /**
     * 获取多条数据
     *
     * @param priceItemNameDict
     * @return
     */
    public List<PriceItemNameDict> findList(PriceItemNameDict priceItemNameDict);

    /**
     * 查询字段列表
     *
     * @param page
     * @param priceItemNameDict
     * @return
     */
    public Page<PriceItemNameDict> findPage(Page<PriceItemNameDict> page, PriceItemNameDict priceItemNameDict);

    /**
     * 保存修改数据
     *
     * @param priceItemNameDict
     */
    public String save(PriceItemNameDict priceItemNameDict);

    /**
     * 删除数据
     *
     * @param priceItemNameDict
     */
    public String delete(PriceItemNameDict priceItemNameDict);
}
