/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugStockAllVo;
import com.jims.phstock.vo.DrugWorkCount;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 药品库存DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugStockDao extends CrudDao<DrugStock> {

    /**
     * 查询某库存一段时间内的工作量
     * @param storage 库存代码
     * @param startTime 开始时间
     * @param endDate 结束时间
     * @param orgId 所属组织结构
     * @return
     * @Author ztq
     *
     */
    public List<DrugWorkCount> getWorkCountBy(@Param("storage")String storage,
                                              @Param("startTime")String startTime,
                                              @Param("endDate")String endDate,
                                              @Param("orgId")String orgId);


    /**
     * 根据进价范围，或者库存单位，查询某一组织机构的库存量
     * @param storageCode
     * @param priceMin
     * @param priceMax
     * @param orgId
     * @return
     * @author ztq
     */
    public List<DrugStockAllVo> listDrugStockAllVo(@Param("storageCode")String storageCode,
                                                   @Param("priceMin")double priceMin,
                                                   @Param("priceMax")double priceMax,
                                                   @Param("orgId")String orgId);

    /**
     * 通过唯一键获取药品库存
     * @param drugCode 药品编码
     * @param drugSpec 药品最小规格
     * @param firmId 生产商
     * @param packageSpec 包装规格
     * @param batchNo 批号
     * @param storage 库房代码
     * @param subStorage 子库房代码
     * @param orgId 组织机构
     * @return
     * @author txb
     */
    public List<DrugStock> findByUnique(String drugCode,String drugSpec,String firmId,String packageSpec,String batchNo,
                                        String storage,String subStorage,String orgId);

}