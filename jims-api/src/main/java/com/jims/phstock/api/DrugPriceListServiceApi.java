package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;

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

    /**
     * 根据当前组织结构获取去本组织结构内所有的药品名称字典。
     * 关联durg_price_list,drug_name_dict drug_price_list drug_code 去重复。
     * @param orgId
     * @return
     * @author ztq
     *
     */
    public List<DrugNameDict> listDrugNameDict(String orgId) ;


    /**
     * 根据药品代码查询当前组织结构的药品价格
     * 不同规格、不同厂商，不同单位，不同价格，不同零售价
     * @param drugCode
     * @param orgId
     * @return
     * @author ztq
     */
    public List<DrugPriceList> listDrugPriceList(String drugCode,String orgId) ;

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @return
     */
    public List<DrugDict> findDrugDict(String orgId);

}
