package com.jims.sys.bo;

import com.jims.sys.dao.OrgServiceParamDao;
import com.jims.sys.dao.SysServiceDao;
import com.jims.sys.dao.SysServiceParamDao;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServiceParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务参数Bo
 * Created by heren on 2016/7/4.
 */
@Service
@Component
@Transactional(readOnly = false)
public class SysServiceParamBo {

    @Autowired
    private SysServiceParamDao sysServiceParamDao ;//系统服务参数的DAO

    @Autowired
    private OrgServiceParamDao orgServiceParamDao ;//机构服务的参数的DAO


    /**
     * 根据服务的ID获取所有的服务
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> findSysServiceParamByServiceId(String serviceId) {
        return sysServiceParamDao.findSysServiceParamDao(serviceId) ;
    }


    /**
     * 更新服务的ID
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams) {
        int i=0 ;
        for(SysServiceParam param :sysServiceParams){
            if("".equals(param.getId())||null==param.getId()){
               i+=sysServiceParamDao.insert(param) ;
            }else{
                i+=sysServiceParamDao.update(param) ;
            }
        }
        return i ;
    }


    /**
     * 删除服务参数
     * @param sysServiceParams
     * @return
     */
    public int delSysServiceParam(List<SysServiceParam> sysServiceParams) {
        int i = 0 ;
        for(SysServiceParam param:sysServiceParams){
            i+=sysServiceParamDao.delete(param) ;
        }
        return i;
    }



}
