/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugNameDict;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugCatalogChangeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 药品价格DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugPriceListDao extends CrudDao<DrugPriceList> {

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @param q 模糊查询字段值
     * @return
     */
    public List<DrugDict> findDrugDict(@Param("orgId") String orgId,@Param("q")String q);
    /**
     * 根据药品代码查询当前组织结构的药品价格
     * 不同规格、不同厂商，不同单位，不同价格，不同零售价
     * @param drugCode
     * @param orgId
     * @return
     * @author txb
     */
    public List<DrugPriceList> listDrugPriceList(String drugCode,String orgId) ;
    /**
     * 根据当前组织结构获取去本组织结构内所有的药品名称字典。
     * 关联durg_price_list,drug_name_dict drug_price_list drug_code 去重复。
     * @param orgId
     * @return
     * @author txb
     *
     */
    public List<DrugNameDict> listDrugNameDict(String orgId) ;

    /**
     * 根据当前组织结构，和当前类别获取所有的药品名称字典。
     * @param classCode 类别代码
     * @return
     * @author txb
     *
     */
    public List<DrugNameDict> listDrugNameDictByClassCode(String classCode) ;

    /**
     *停价处理
     * @param id
     * @return
     * @author weishen
     */
    public int stopDate(String id,Date date) ;

    /**
     * 调价确认 查询价表数据
     * @param drugCode 药品代码
     * @param drugSpec 药品规格
     * @param firmId 厂商
     * @param units 单位
     * @param orgId 机构
     * @return
     * @author txb
     */
    public  List<DrugPriceList> selectPriceList(String drugCode,String drugSpec,String firmId ,String units,String orgId);


    /**
     * 根据价格表的ID检索全院库存量
     * @param
     * @return
     * @author zq
     */
    public List<DrugStock> findListByPriceListId(String orgId,String drugCode,String drugSpec,String firmId,String packageSpec);


    /**
     * 根据价格表的ID,subStorage,storage检索库存量
     * @param
     * @return
     * @author zq
     */
    public List<DrugStock> findBySubQuantity(String orgId,String drugCode,String drugSpec,String firmId,String packageSpec,String storage,String subStorage);

    /**
     * 检索（没有其他关联表）
     * @param entity
     * @return
     */
    public List<DrugPriceList> findListNoJoin(DrugPriceList entity);
}