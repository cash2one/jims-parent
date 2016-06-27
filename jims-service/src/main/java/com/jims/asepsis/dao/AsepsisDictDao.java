package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
* 包名称维护Dao
* @author yangruidong
* @version 2016-06-27
*/
@MyBatisDao
public interface AsepsisDictDao extends CrudDao<AsepsisDict> {


}