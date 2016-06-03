/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.OrgRoleDao;
import com.jims.sys.dao.PersionServiceListDao;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 个人服务BAO层
 *
 * @author yangruidong
 * @version 2016-06-02
 */
@Service
@Component
@Transactional(readOnly = false)
public class PersionServiceListBo extends CrudImplService<PersionServiceListDao, PersionServiceList> {

    @Autowired
    private PersionServiceListDao persionServiceListDao;

    /**
     * 根据persionId查询免费的服务
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag( String persionId)
    {
        return persionServiceListDao.findListByFlag(persionId);
    }


    /**
     * 保存个人购买的服务
     * @param persionServiceList
     * @return 1 成功 ,0 失败
     */
    public void saveService(PersionServiceList persionServiceList){
        List<PersionServiceList> services = persionServiceList.getServiceList();
        if(services != null && services.size() > 0){
            for(PersionServiceList service : services){
                service.preInsert();
                persionServiceListDao.insert(service);
            }
        }
    }
}