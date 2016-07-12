package com.jims.phstock.api;

import com.jims.phstock.entity.DrugProvideApplication;
import com.jims.common.persistence.Page;
import com.jims.phstock.vo.DrugProvideApplicationVo;

import java.util.List;

/**
* 录入申请Api
* @author yangruidong
* @version 2016-07-04
*/
public interface DrugProvideApplicationApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public DrugProvideApplication get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<DrugProvideApplication> findList(DrugProvideApplication entity);

    /**
     * 检索(含有价格)
     * @param entity
     * @param storage 库存管理单位
     * @param subStorage 存放库房
     * @return
     */
    public List<DrugProvideApplication> findListWithPrice(DrugProvideApplication entity,String storage,String subStorage);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<DrugProvideApplication> findPage(Page<DrugProvideApplication> page, DrugProvideApplication entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(DrugProvideApplication entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<DrugProvideApplication> list);


    /**
     * 保存  增删改
     * @param drugProvideApplicationVo
     * @return
     *  @author  yangruidong
     */
    public List<DrugProvideApplication> saveAll(DrugProvideApplicationVo<DrugProvideApplication> drugProvideApplicationVo);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;

    /**
     *查询去除重复的申请号
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findDocumentByDistinct(DrugProvideApplication entity);

    /**
     *查询去除重复的申请时间和单位
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findListByDistinct(DrugProvideApplication entity);
}