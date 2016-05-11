/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.lab.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.lab.entity.LabTestMaster;

/**
 * 检验主记录DAO接口
 * @author xueyx
 * @version 2016-05-04
 */
@MyBatisDao
public interface LabTestMasterDao extends CrudDao<LabTestMaster> {

    /**
     * 保存或编辑
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */
    public void saveAll(LabTestMaster labTestMaster);
    /**
     * 生成申请序号
     * @param主表 当前日期
     * @author xueyx
     * @version 2016/5/09
     */
    public String creatTestNo();
}