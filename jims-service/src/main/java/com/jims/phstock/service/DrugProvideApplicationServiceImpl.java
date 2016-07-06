package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.entity.DrugProvideApplication;
import com.jims.phstock.api.DrugProvideApplicationApi;
import com.jims.phstock.bo.DrugProvideApplicationBo;
import com.jims.common.persistence.Page;
import com.jims.phstock.vo.DrugProvideApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 录入申请service
* @author yangruidong
* @version 2016-07-04
*/
@Service(version = "1.0.0")
public class DrugProvideApplicationServiceImpl implements DrugProvideApplicationApi{

    @Autowired
    private DrugProvideApplicationBo bo;

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public DrugProvideApplication get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<DrugProvideApplication> findList(DrugProvideApplication entity) {
        return bo.findList(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<DrugProvideApplication> findPage(Page<DrugProvideApplication> page, DrugProvideApplication entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(DrugProvideApplication entity) {
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
    public String save(List<DrugProvideApplication> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
     * 保存  增删改
     *
     * @param drugProvideApplicationVo
     * @return
     * @author yangruidong
     */
    @Override
    public List<DrugProvideApplication> saveAll(DrugProvideApplicationVo<DrugProvideApplication> drugProvideApplicationVo) {
        return bo.saveAll(drugProvideApplicationVo);
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
     *查询去除重复的申请号
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findDocumentByDistinct(DrugProvideApplication entity){
        return bo.findDocumentByDistinct(entity);
    }
}