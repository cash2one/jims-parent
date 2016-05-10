/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PriceList;

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
}