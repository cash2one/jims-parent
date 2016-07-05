package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.ServiceParamApi;
import com.jims.sys.bo.SysServiceParamBo;
import com.jims.sys.entity.SysServiceParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by heren on 2016/7/4.
 */
@Service(version = "1.0.0")
public class ServiceParamImpl implements ServiceParamApi {

    @Autowired
    private SysServiceParamBo sysServiceParamBo ;

    /**
     * 根据服务查询所有的服务参数
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> listSysServiceParam(String serviceId) {

        return sysServiceParamBo.findSysServiceParamByServiceId(serviceId);
    }

    /**
     * 更新服务参数
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams) {
        return sysServiceParamBo.mergeSysServiceParam(sysServiceParams);
    }

    /**
     * 删除服务参数
     * @param sysServiceParams
     * @return
     */
    public int deleteSysServiceParam(List<SysServiceParam> sysServiceParams) {
        return sysServiceParamBo.delSysServiceParam(sysServiceParams);
    }
}
