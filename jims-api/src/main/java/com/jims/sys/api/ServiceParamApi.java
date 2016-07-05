package com.jims.sys.api;

import com.jims.sys.entity.SysServiceParam;

import java.util.List;

/**
 * Created by heren on 2016/7/4.
 */
public interface ServiceParamApi {
    /**
     * 查询某一个服务的所有ID
     * @param serviceId
     * @return
     */
    public List<SysServiceParam> listSysServiceParam(String serviceId);

    /**
     * 修改保存Service服务
     * @param sysServiceParams
     * @return
     */
    public int mergeSysServiceParam(List<SysServiceParam> sysServiceParams);

    /**
     * 删除对应的服务参数
     * @param sysServiceParams
     * @return
     */
    public int deleteSysServiceParam(List<SysServiceParam> sysServiceParams);


}
