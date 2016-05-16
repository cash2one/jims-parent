/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.dao.DictDao;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;
import com.thoughtworks.xstream.mapper.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典Service
 * @author zhangyao
 * @version 2014-05-18
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DictServiceApiImpl extends CrudImplService<DictDao, Dict> implements DictServiceApi {

    /**
     * 查询字典表的类型和描述这两个字段
     *
     * @return 类型描述的列表list集合
     * @author fengyuguang
     */
    public List<Dict> leftList() {
        return dao.leftList();
    }

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     *
     * @param type 字典表类型
     * @return 字典表某类的列表集合
     * @author fengyuguang
     */
    public List<Dict> rightList(String type) {
        return dao.rightList(type);
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
        return dao.select(type, description);
    }

    /**
     * 保存增删改多条数据
     *
     * @param beanChangeVo 多条数据的Vo类
     * @return 操作数据条数
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo) {
        List<Dict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (Dict dict : insertedList) {
            String label = dict.getLabel();
            dict.setInputCode(PinYin2Abbreviation.cn2py(label));
            inNum = Integer.valueOf(save(dict));
            inNum++;
        }
        String insertedNum = inNum + "";
        List<Dict> updatedList = beanChangeVo.getUpdated();

        int updNum = 0;
        for (Dict dict : updatedList) {
            updNum = dao.update(dict);
            updNum++;
        }
        String updatedNum = updNum + "";
        if (insertedNum == "0" && updatedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }
	
	/**
     * 查询字段类型列表
     * @return
     */
    public List<String> findTypeList() {
        return dao.findTypeList(new Dict());
    }

    public List<String> findListType(String dict) {
        return dao.findListType(dict);
    }

	public String getLabel(String type,String value){
       return dao.getLabel(type,value);
	}

    /**
     * 根据类型检索字典
     * @param type
     * @return
     */
    @Override
    public List<Dict> findList(String type) {
        Dict d = new Dict();
        d.setType(type);
        return dao.findList(d);
    }
}
