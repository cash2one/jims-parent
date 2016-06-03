package com.jims.sys.api;


import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangruidong on 2016/6/02
 */
public interface PersionServiceListApi {

    /**
     * 根据persionId查询免费的服务
     * @param persionId
     * @return
     */
    public List<SysService> findListByFlag(String persionId);

    /**
     * 保存个人购买的服务
     * @param persionServiceList
     *
     */
    public String saveService(PersionServiceList persionServiceList);
}
