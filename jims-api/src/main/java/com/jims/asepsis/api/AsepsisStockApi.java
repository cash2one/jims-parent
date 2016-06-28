package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisStock;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 包库存初始化Api
* @author yangruidong
* @version 2016-06-27
*/
public interface AsepsisStockApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisStock get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisStock> findList(AsepsisStock entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisStock> findPage(Page<AsepsisStock> page, AsepsisStock entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisStock entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<AsepsisStock> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}