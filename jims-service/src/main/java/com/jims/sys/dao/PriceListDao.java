/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PriceList;
import com.jims.sys.vo.PriceListVo;

import java.util.List;

/**
 * 价格表DAO接口
 *
 * @author 罗海昆
 * @version 2016-04-26
 */
@MyBatisDao
public interface PriceListDao extends CrudDao<PriceList> {

    /**
     * 查询价表序列
     * @return
     */
    public String findSeqences();

    /**
     * 通过拼音码查询数据
     * @param inputCode
     * @return
     */
    public List<PriceList> findCode(String inputCode);

    /**
     * 现行价格表
     * @param priceListVo
     * @return
     * @author wei
     */
    public List<PriceListVo> findPriceList(String orgId,PriceListVo priceListVo);

    /**
     * 根据拼音码查询现行价表数据
     * @param inputCode 拼音码
     * @param label 药品类型
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
     * 历史价格表
     * @param priceListVo
     * @return
     * @author wei
     */
    public List<PriceListVo> findOLdPriceList(String orgId,PriceListVo priceListVo);

    /**
     * 下拉框查询药品类别
     * @return
     * @author wei
     */
    public List<PriceListVo> list();
}