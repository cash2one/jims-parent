package com.jims.asepsis.api;

import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.vo.AsepsisVo;
import com.jims.common.persistence.Page;

import java.util.List;

/**
* 送物领物Api
* @author lgx
* @version 2016-06-27
*/
public interface AsepsisSendRecApi {

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisSendRec get(String id);

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisSendRec> findList(AsepsisSendRec entity);

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisSendRec> findPage(Page<AsepsisSendRec> page, AsepsisSendRec entity);

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisSendRec entity) ;

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<AsepsisSendRec> list);

    /**
     * 保存  增删改
     * @param asepsisVo
     * @return
     *  @author  yangruidong
     */
    public List<AsepsisSendRec> saveAll(AsepsisVo<AsepsisSendRec> asepsisVo);

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) ;

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId);

    /**
     * 检索有库存、在保质期内的数据
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListWithStock(AsepsisSendRec entity);
}