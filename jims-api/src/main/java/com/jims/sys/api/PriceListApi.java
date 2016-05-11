package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceDictListVo;
import com.jims.sys.vo.PriceListVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public interface PriceListApi {
    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public PriceList get(String id);

    /**
     * 获取多条数据
     *
     * @param priceList
     * @return
     */
    public List<PriceList> findList(PriceList priceList);

    /**
     * 查询字段列表
     *
     * @param page
     * @param priceList
     * @return
     */
    public Page<PriceList> findPage(Page<PriceList> page, PriceList priceList);

    /**
     * 保存修改数据
     *
     * @param priceList
     */
    public String save(PriceList priceList);

    /**
     * 保存修改数据
     *
     * @param priceDictListVo
     */
    public String save(PriceDictListVo priceDictListVo);

    /**
     * 删除数据
     *
     * @param priceList
     */
    public String delete(PriceList priceList);

    /**
     * 删除数据
     * @param id
     * @return
     */
    public String delete(String id);

    /**
     * 查询序列
     *
     * @return
     */
    public String findSeqences();

    /**
     * 通过拼音码查询数据
     *
     * @param inputCode
     * @return
     */
    public List<PriceList> findCode(String inputCode);


    /**
     * 根据拼音码查询现行价表数据
     * @param inputCode
     * @param label
     * @return
     * @author wei
     */
    public List<PriceListVo> getInputCodeNow(String orgId,String inputCode,String label);

    /**
     * 根据拼音码查询历史价表数据
     * @param orgId
     * @param inputCode
     * @param label
     * @return
     */
    public List<PriceListVo> getInputCodeOld(String orgId,String inputCode,String label);


    /**
     * 下拉框查询药品类别
     * @return
     * @author wei
     */
    public List<PriceListVo> list();

    /**
     * 现行药价
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findPage(String orgId,Page<PriceListVo> page, PriceListVo priceListVo);
    /**
     * 历史药价
     * @param page
     * @param priceListVo
     * @return
     * @author wei
     */
    public Page<PriceListVo> findOLdPage(String orgId,Page<PriceListVo> page, PriceListVo priceListVo);
}
