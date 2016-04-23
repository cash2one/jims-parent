package com.jims.materiel.api;

import com.jims.common.persistence.Page;
import com.jims.materiel.entity.DrugPriceList;

import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 * 药品价格Api接口
 * @author赵宁
 * @version 2016-04-23
 */
public interface DrugPriceListServiceApi {
    /**
     * 保存药品价格数据
     * @param drugPriceList
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String save(DrugPriceList drugPriceList);

    /**
     * 分页查询数据
     * @param drugPriceListPage
     * @param drugPriceList
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public Page<DrugPriceList> findPage(Page<DrugPriceList> drugPriceListPage,DrugPriceList drugPriceList);

    /**
     * 查询列表数据
     * @param drugPriceList
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public List<DrugPriceList> findList(DrugPriceList drugPriceList);

    /**
     * 获得单条数据
     * @param drugPriceList
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public DrugPriceList get(DrugPriceList drugPriceList);

    /**
     * 删除数据（单条数据删除，批量删除）
     * @param ids
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String delete(String ids);
}
