package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.MrFeeClassDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@MyBatisDao
public interface MrFeeClassDictDao extends CrudDao<MrFeeClassDict>{
    public List<String> findTypeList(MrFeeClassDict mrFeeClassDict);
}
