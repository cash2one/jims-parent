package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.entity.AsepsisDetailDict;
import com.jims.asepsis.api.AsepsisDetailDictApi;
import com.jims.asepsis.bo.AsepsisDetailDictBo;
import com.jims.asepsis.vo.AsepsisDetailDictVo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 包内物品管理service
* @author yangruidong
* @version 2016-06-27
*/
@Service(version = "1.0.0")
public class AsepsisDetailDictServiceImpl implements AsepsisDetailDictApi{

    @Autowired
    private AsepsisDetailDictBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisDetailDict get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisDetailDict> findList(AsepsisDetailDict entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisDetailDict> findPage(Page<AsepsisDetailDict> page, AsepsisDetailDict entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisDetailDict entity) {
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
    public String save(List<AsepsisDetailDict> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }


    /**
     * 保存  增删改
     *
     * @param asepsisDetailDictVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<AsepsisDetailDict> saveAll(AsepsisDetailDictVo<AsepsisDetailDict> asepsisDetailDictVo) {
        return bo.saveAll(asepsisDetailDictVo);
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