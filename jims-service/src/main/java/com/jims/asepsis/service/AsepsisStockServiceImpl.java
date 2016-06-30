package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.api.AsepsisAntiRecApi;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.asepsis.api.AsepsisStockApi;
import com.jims.asepsis.bo.AsepsisStockBo;
import com.jims.asepsis.vo.AsepsisStockVo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 包库存初始化service
* @author yangruidong
* @version 2016-06-27
*/
@Service(version = "1.0.0")
public class AsepsisStockServiceImpl implements AsepsisStockApi{

    @Autowired
    private AsepsisStockBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisStock get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisStock> findList(AsepsisStock entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisStock> findPage(Page<AsepsisStock> page, AsepsisStock entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisStock entity) {
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
    public String save(List<AsepsisStock> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }


    /**
     * 保存  增删改
     *
     * @param asepsisStockVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<AsepsisStock> saveAll(AsepsisStockVo<AsepsisStock> asepsisStockVo) {
        return bo.saveAll(asepsisStockVo);
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
}