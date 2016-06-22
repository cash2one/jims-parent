package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.IdentityDict;

import java.util.List;

/**
 * 身份字典表dao层
 * Created by fyg on 2016/6/21.
 */
@MyBatisDao
public interface IdentityDictDao extends CrudDao<IdentityDict>{

    /**
     * 根据身份名称模糊查询记录
     * @param identityName 身份名称
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> search(String identityName);
}
