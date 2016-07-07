/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.lab.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.lab.entity.LabTestMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;


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
    public String saveAll(LabTestMaster labTestMaster);

    /**
     * 住院保存
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */

    public String saveAllIn(LabTestMaster labTestMaster);
    /**
     * 生成申请序号
     * @param主表 当前日期
     * @author xueyx
     * @version 2016/5/09
     */
    public String creatTestNo();

    /**
     * 删除记录
     * @param id
     * @return
     */
    public int deleteLabTestMaster(@Param("id")String id);

    /**
     * 根据，当前登录人所在执行科室，查询 检验记录
     * @param performedBy
     * @return
     * @author zhaoning
     */
    public List<LabTestMaster> getLabMaster(@Param("performedBy")String performedBy );

    /**
     * 检验确认 更新 检验主记录 状态，更改为：1 （已确认）
     * @param id
     * @return
     */
    public int updateLabMaster(@Param("id")String id);
}