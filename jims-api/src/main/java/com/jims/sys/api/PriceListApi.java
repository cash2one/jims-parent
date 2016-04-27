package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.PriceList;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface PriceListApi {
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public PriceList get(String id);

    /**
     * 获取多条数据
     * @param priceList
     * @return
     */
    public List<PriceList> findList(PriceList priceList);

    /**
     *  查询字段列表
     * @param page
     * @param priceList
     * @return
     */
    public Page<PriceList> findPage(Page<PriceList> page, PriceList priceList);

    /**
     * 保存修改数据
     * @param priceList
     */
    public String save(PriceList priceList);

    /**
     * 删除数据
     * @param priceList
     */
    public String delete(PriceList priceList);
}
