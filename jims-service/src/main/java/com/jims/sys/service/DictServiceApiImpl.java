/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.dao.DictDao;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.BeanChangeVo;
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
     * @return 类型描述的列表list集合
     * @author fengyuguang
     */
    public List<Dict> leftList() {
        return dao.leftList();
    }

    /**
     * 根据字典表的类型查询属于该类型的数据列表
     * @param type  字典表类型
     * @return  字典表某类的列表集合
     * @author fengyuguang
     */
    public List<Dict> rightList(String type) {
        return dao.rightList(type);
    }

    /**
     * 保存增删改多条数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo) {
        List<Dict> insertedList = beanChangeVo.getInserted();
        for (Dict dict : insertedList) {
            String num = save(dict);
            System.out.println("增加num:" + num);
        }
        List<Dict> updatedList = beanChangeVo.getUpdated();
        for (Dict dict : updatedList) {
            String num = save(dict);
            System.out.println("修改num:" + num);
        }
        List<Dict> deletedList = beanChangeVo.getDeleted();
        for (Dict dict : deletedList) {
            String num = delete(dict);
            System.out.println("删除num:" + num);
        }
        return null;
    }

    /**
     * 查询字段类型列表
     * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

    public List<String> findListType(String dict){
        return dao.findListType(dict);
    }
}
