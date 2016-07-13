package com.jims.phstock.api;

import com.jims.phstock.entity.DrugRationalDosage;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * 药品用量信息api
 * Created by fyg on 2016/7/12.
 */
public interface DrugRationalDosageApi {

    /**
     * 查询所有药品用量信息情况
     * @return
     * @author fengyuguang
     */
    public List<DrugRationalDosage> findAll();

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugRationalDosage> beanChangeVo);
}
