/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 给药途径字典DAO接口
 * @author yangruidong
 *
 */
@MyBatisDao
public interface AdministrationDictDao extends CrudDao<AdministrationDict> {

    /**
     * 根据试用返回获取用药途径 ，传入是门诊则获取门诊+全部
     * 传入的是住院 = 住院+ 全部
     * 传入的全部 = 门诊 + 住院+全部
     * @param inpOutpFlag 全部（综合）、门诊、住院
     * @return
     */

    public List<AdministrationDict> listAdministrationByInpOrOutpFlag(@Param("inpOutpFlag") String inpOutpFlag) ;

    public List<AdministrationDict> findAllList();

	
}
