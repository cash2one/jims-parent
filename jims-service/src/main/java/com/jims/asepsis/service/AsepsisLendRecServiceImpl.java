package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.asepsis.api.AsepsisLendRecApi;
import com.jims.asepsis.bo.AsepsisLendRecBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 借物还物service
* @author lgx
* @version 2016-06-27
*/
@Service(version = "1.0.0")
public class AsepsisLendRecServiceImpl implements AsepsisLendRecApi{

    @Autowired
    private AsepsisLendRecBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisLendRec get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisLendRec> findList(AsepsisLendRec entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisLendRec> findPage(Page<AsepsisLendRec> page, AsepsisLendRec entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisLendRec entity) {
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
    public String save(List<AsepsisLendRec> list) {
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
     * @param prefix
     * @return
     */
    public String getMaxDocumentNo(String orgId,String prefix){
        return bo.getMaxDocumentNo(orgId,prefix);
    }

    /**
     * 检索有库存的
     * @param entity
     * @return
     */
    public List<AsepsisLendRec> findListWithStock(AsepsisLendRec entity){
        return bo.findListWithStock(entity);
    }
}