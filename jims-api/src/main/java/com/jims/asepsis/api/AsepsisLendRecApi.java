package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 借物还物Api
* @author lgx
* @version 2016-06-27
*/
public interface AsepsisLendRecApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisLendRec get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisLendRec> findList(AsepsisLendRec entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisLendRec> findPage(Page<AsepsisLendRec> page, AsepsisLendRec entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisLendRec entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<AsepsisLendRec> list);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;

    /**
     * 获取当天最大的编码
     * @param orgId
     * @param prefix
     * @return
     */
    public String getMaxDocumentNo(String orgId,String prefix);

    /**
     * 检索有库存的
     * @param entity
     * @return
     */
    public List<AsepsisLendRec> findListWithStock(AsepsisLendRec entity);
}