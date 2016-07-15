/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.bo.DictBo;
import com.jims.sys.dao.DictDao;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 字典Service
 *
 * @author zhangyao
 * @version 2014-05-18
 */
@Service(version = "1.0.0")

public class DictServiceApiImpl implements DictServiceApi {
    @Autowired
    private DictBo dictBo;

    @Override
    public Dict get(String id) {
        return dictBo.get(id);
    }

    @Override
    public Page<Dict> findPage(Page<Dict> page, Dict dict) {
        return dictBo.findPage(page, dict);
    }

    /**
     * 查询字典表的类型和描述这两个字段
     *
     * @return 类型描述的列表list集合
     * @author fengyuguang
     */
    public List<Dict> leftList() {
        return dictBo.leftList();
    }

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     *
     * @param type 字典表类型
     * @return 字典表某类的列表集合
     * @author fengyuguang
     */
    public List<Dict> rightList(String type) {
        return dictBo.rightList(type);
    }

    /**
     * 根据类型或描述模糊查询
     *
     * @param type
     * @param description
     * @return 查询到的字典表List集合
     * @author fengyuguang
     */
    public List<Dict> select(String type, String description) {
        return dictBo.select(type, description);
    }

    /**
     * 保存增删改多条数据
     *
     * @param beanChangeVo 多条数据的Vo类
     * @return 操作数据条数
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo) {
        return dictBo.merge(beanChangeVo);
    }

    /**
     * 查询字段类型列表
     *
     * @return
     */
    public List<String> findTypeList() {
        return dictBo.findTypeList();
    }

    @Override
    public String save(Dict dict) {
        return dictBo.save(dict);
    }

    @Override
    public String delete(String ids) {
        return dictBo.delete(ids);
    }

    public List<String> findListType(String type) {
        return dictBo.findListType(type);
    }

    public String getLabel(String type, String value) {
        return dictBo.getLabel(type, value);
    }

    /**
     * 根据类型检索字典
     *
     * @param type
     * @return
     */
    @Override
    public List<Dict> findList(String type) {
        Dict d = new Dict();
        d.setType(type);
        return dictBo.findList(d);
    }

    public List<Dict> findList(Dict d) {
        return dictBo.findList(d);
    }
}
