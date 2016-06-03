package com.jims.sys.api;


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
     * @param flag
     * @return
     */
    public List<SysService> findListByFlag(String persionId, String flag);
}
