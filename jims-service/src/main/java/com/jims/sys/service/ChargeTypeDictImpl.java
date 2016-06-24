package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.ChargeTypeDictApi;
import com.jims.sys.dao.ChargeTypeDictDao;
import com.jims.sys.entity.ChargeTypeDict;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service(version = "1.0.0")

public class ChargeTypeDictImpl extends CrudImplService<ChargeTypeDictDao, ChargeTypeDict> implements ChargeTypeDictApi{

    @Autowired
    private ChargeTypeDictDao chargeTypeDictDao;

    /**
     * 查询某个组织机构的费别列表
     * @param orgId  组织机构ID
     * @param page  分页
     * @param chargeTypeDict
     * @return
     * @author fengyuguang
     */
    public Page<ChargeTypeDict> findPage(String q,String orgId, Page<ChargeTypeDict> page, ChargeTypeDict chargeTypeDict){
        chargeTypeDict.setPage(page);
        page.setList(chargeTypeDictDao.findListByOrgId(q,orgId));
        return page;
    }

    /**
     * 查询某个组织机构的费别列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> listAll(String orgId){
        return chargeTypeDictDao.listAll(orgId);
    }

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new ChargeTypeDict());
    }
}
