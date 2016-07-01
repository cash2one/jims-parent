package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.asepsis.api.AsepsisSendRecApi;
import com.jims.asepsis.bo.AsepsisSendRecBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 送物领物service
* @author lgx
* @version 2016-06-27
*/
@Service(version = "1.0.0")
public class AsepsisSendRecServiceImpl implements AsepsisSendRecApi{

    @Autowired
    private AsepsisSendRecBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisSendRec get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisSendRec> findList(AsepsisSendRec entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisSendRec> findPage(Page<AsepsisSendRec> page, AsepsisSendRec entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisSendRec entity) {
        try {
            bo.save(entity);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<AsepsisSendRec> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId){
        return bo.getMaxDocumentNo(orgId);
    }

    /**
     * 检索有库存、在保质期内的数据
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListWithStock(AsepsisSendRec entity){
        return bo.findListWithStock(entity);
    }
    /**
     * 科室消毒费统计
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListFee(AsepsisSendRec entity){
        return bo.findListFee(entity);
    }
}