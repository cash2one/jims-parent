package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.Page;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.ChargeTypeDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@MyBatisDao
public interface ChargeTypeDictDao extends CrudDao<ChargeTypeDict>{

    /**
     * 查询某个组织机构的费别列表
     * @param orgId  组织机构ID
     * @return
     */
    public List<ChargeTypeDict> findListByOrgId(String q,String orgId);

    /**
     * 查询某个组织机构的费别列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> listAll(String orgId);

    public List<String> findTypeList(ChargeTypeDict chargeTypeDict);
}
