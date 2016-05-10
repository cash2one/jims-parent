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
     * @param beanChangeVo 多条数据的Vo类
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<Dict> beanChangeVo) {
        List<Dict> insertedList = beanChangeVo.getInserted();
        String insertedNum = "";
        if (insertedList.isEmpty()) {
            insertedNum = "1";
        }
        for (Dict dict : insertedList) {
            insertedNum = save(dict);
            System.out.println("增加insertedNum:" + insertedNum);
        }

        List<Dict> updatedList = beanChangeVo.getUpdated();
        String updatedNum = "";
        if (updatedList.isEmpty()) {
            updatedNum = "1";
        }
        for (Dict dict : updatedList) {
            updatedNum = save(dict);
            System.out.println("修改updatedNum:" + updatedNum);
        }

        List<Dict> deletedList = beanChangeVo.getDeleted();
        String deletedNum = "";
        if (deletedList.isEmpty()) {
            deletedNum = "1";
        }
        for (Dict dict : deletedList) {
            String dictId = dict.getId();
            deletedNum = delete(dictId);
            System.out.println("删除deletedNum:" + deletedNum);
        }

        if(insertedNum == "1" && updatedNum == "1" && deletedNum == "1"){
            return "1";
        }else{
            return "0";
        }
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
