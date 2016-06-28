package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisDict;
import com.jims.asepsis.vo.AsepsisDictVo;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 包名称维护Api
* @author yangruidong
* @version 2016-06-27
*/
public interface AsepsisDictApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisDict get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisDict> findList(AsepsisDict entity);

    /**
     * 根据科室和orgId 查询科室下的包
     * @param entity
     * @return
     */
    public List<AsepsisDict> findPageByDept(AsepsisDict entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisDict> findPage(Page<AsepsisDict> page, AsepsisDict entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisDict entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<AsepsisDict> list);

    /**
     * 保存  增删改
     * @param asepsisDictVo
     * @return
     *  @author  yangruidong
     */
    public List<AsepsisDict> saveAll(AsepsisDictVo<AsepsisDict> asepsisDictVo);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;

    /**
     * 检索有库存的数据
     * @param entity
     * @return
     */
    public List<AsepsisDict> findListHasStock(AsepsisDict entity);
}