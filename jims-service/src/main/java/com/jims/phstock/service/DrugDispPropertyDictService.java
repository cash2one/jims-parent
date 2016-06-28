/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugDispPropertyDictApi;
import com.jims.phstock.bo.DrugDispPropertyDictServiceBo;
import com.jims.phstock.dao.DrugDispPropertyDictDao;
import com.jims.phstock.entity.DrugDispPropertyDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 摆药类别字典生成Service
 * @author luohk
 * @version 2016-05-10
 */
@Service(version = "1.0.0")
public class DrugDispPropertyDictService  implements DrugDispPropertyDictApi{

    @Autowired
    private DrugDispPropertyDictServiceBo drugDispPropertyDictServiceBo;

    /**
     * 保存增删改
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugDispPropertyDict> beanChangeVo){
        return drugDispPropertyDictServiceBo.merge(beanChangeVo);
    }

    /**
     * 获取摆药类别
     *
     * @param id 摆药类别主键ID
     * @return
     */
    public DrugDispPropertyDict get(String id){
        return drugDispPropertyDictServiceBo.get(id);
    }

    /**
     * 根据摆药类别获取摆药集合
     *
     * @param drugDispPropertyDict
     * @return
     */
    public List<DrugDispPropertyDict> findList(DrugDispPropertyDict drugDispPropertyDict){
        return drugDispPropertyDictServiceBo.findList(drugDispPropertyDict);
    }

    /**
     * 获取摆药类别列表
     * @return
     */
    public List<DrugDispPropertyDict> findAllList(){
        return drugDispPropertyDictServiceBo.findAllList();
    }

    /**
     * 根据摆药类别获取摆药集合
     *
     * @param page
     * @param drugDispPropertyDict
     * @return
     */
    public Page<DrugDispPropertyDict> findPage(Page<DrugDispPropertyDict> page, DrugDispPropertyDict drugDispPropertyDict){
        return drugDispPropertyDictServiceBo.findPage(page,drugDispPropertyDict);
    }

    /**
     * 保存功能
     *
     * @param drugDispPropertyDict
     */
    public String save(DrugDispPropertyDict drugDispPropertyDict){
        return drugDispPropertyDictServiceBo.save(drugDispPropertyDict);
    }

    /**
     * 删除功能
     *
     * @param drugDispPropertyDict
     */
    public String delete(DrugDispPropertyDict drugDispPropertyDict){
        return drugDispPropertyDictServiceBo.delete(drugDispPropertyDict);
    }

    /**
     * 删除功能
     *
     * @param id
     * @return
     */
    public String delete(String id){
        return drugDispPropertyDictServiceBo.delete(id);
    }
}