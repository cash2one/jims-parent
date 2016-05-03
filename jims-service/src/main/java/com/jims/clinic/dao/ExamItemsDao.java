/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ExamItems;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 检查项目记录DAO接口
 * @author zhaoning
 * @version 2016-04-25
 */
@MyBatisDao
public interface ExamItemsDao extends CrudDao<ExamItems> {
    /**
     *  检查项目保存
     */
    public  void saveExamItems(ExamItems examItems);

    /**
     * 根据申请序号查询检查项目
     * @param examNo
     * @return
     */

    public List<ExamItems> loadExamItems(String examNo );

    /**
     * 删除检查项目
     * @param examNo
     * @return
     */
    public Integer deleteItems(String examNo);
}