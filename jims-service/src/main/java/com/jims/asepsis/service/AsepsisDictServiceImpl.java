package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.entity.AsepsisDict;
import com.jims.asepsis.api.AsepsisDictApi;
import com.jims.asepsis.bo.AsepsisDictBo;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 包名称维护service
* @author yangruidong
* @version 2016-06-27
*/
@Service(version = "1.0.0")
public class AsepsisDictServiceImpl implements AsepsisDictApi{

    @Autowired
    private AsepsisDictBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public AsepsisDict get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<AsepsisDict> findList(AsepsisDict entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<AsepsisDict> findPage(Page<AsepsisDict> page, AsepsisDict entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(AsepsisDict entity) {
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
    public String save(List<AsepsisDict> list) {
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
}