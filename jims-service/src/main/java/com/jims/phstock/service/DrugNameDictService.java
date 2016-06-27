/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.phstock.api.DrugNameDictServiceApi;
import com.jims.phstock.bo.DrugNameDictBo;
import com.jims.phstock.entity.DrugNameDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 药品名称Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
public class DrugNameDictService implements DrugNameDictServiceApi {

    @Autowired
    private DrugNameDictBo bo;

    @Override
    public String save(DrugNameDict drugNameDict) {
        String result = "0";
        try{
            bo.save(drugNameDict);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    @Override
    public Page<DrugNameDict> findPage(Page<DrugNameDict> page, DrugNameDict drugNameDict) {
        return bo.findPage(page,drugNameDict);
    }

    @Override
    public List<DrugNameDict> findList(DrugNameDict drugNameDict) {
        return bo.findList(drugNameDict);
    }

    @Override
    public DrugNameDict get(String id) {
        return bo.get(id);
    }

    @Override
    public String delete(String ids) {
        String result = "0";
        try{
            bo.delete(ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 查询所有药品名称列表
     * @return
     * @author txb
     * @version 2016-05-11
     */
    @Override
    public List<DrugNameDict> findDrugNameDictList(String inputCode) {
        return bo.findDrugNameDictList(inputCode);
    }
    /**
     * 查询所有药品名称列表通过拼音码
     * @param drugCode 拼音码
     * @return
     * @author txb
     * @version 2016-05-11
     */
    @Override
    public List<DrugNameDict> listDrugNameDictByDrugCode(String drugCode) {
        return bo.listDrugNameDictByDrugCode(drugCode);
    }
}