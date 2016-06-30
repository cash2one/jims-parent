package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisDetailDict;
import com.jims.asepsis.vo.AsepsisDetailDictVo;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 包内物品管理Api
* @author yangruidong
* @version 2016-06-27
*/
public interface AsepsisDetailDictApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisDetailDict get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisDetailDict> findList(AsepsisDetailDict entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisDetailDict> findPage(Page<AsepsisDetailDict> page, AsepsisDetailDict entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisDetailDict entity) ;

    /**
     * 保存  增删改
     * @param asepsisDetailDictVo
     * @return
     *  @author  yangruidong
     */
    public List<AsepsisDetailDict> saveAll(AsepsisDetailDictVo<AsepsisDetailDict> asepsisDetailDictVo);

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<AsepsisDetailDict> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;
}