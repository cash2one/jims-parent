package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugRationalDosageApi;
import com.jims.phstock.bo.DrugRationalDosageBo;
import com.jims.phstock.entity.DrugRationalDosage;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 药品用量信息service
 * Created by fyg on 2016/7/12.
 */
@Service(version = "1.0.0")
public class DrugRationalDosageService implements DrugRationalDosageApi {

    @Autowired
    private DrugRationalDosageBo drugRationalDosageBo;

    /**
     * 查询所有药品用量信息情况
     * @return
     * @author fengyuguang
     */
    public List<DrugRationalDosage> findAll(){
        return drugRationalDosageBo.findAll();
    }

    /**
     * 根据药品代码查询药品用量信息
     * @param drugCode 药品代码
     * @return
     * @author fengyuguang
     */
    public List<DrugRationalDosage> getListByDrugCode(String drugCode){
        return drugRationalDosageBo.getListByDrugCode(drugCode);
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugRationalDosage> beanChangeVo){
        return drugRationalDosageBo.merge(beanChangeVo);
    }
}
