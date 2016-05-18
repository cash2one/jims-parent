package com.jims.sys.api;

import com.jims.common.web.impl.BaseDto;

import java.util.List;

/**
 * 收入法设置相关接口
 * Created by heren on 2016/5/18.
 */
public interface InputSettingServiceApi {

    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     * @param dictType 字典类型
     * @param orgId 组织机构
     * @return
     * @Author ztq
     */
    public List<BaseDto> listInputDataBy(String dictType,String orgId) ;


    /**
     * 根据表名称，查询表中有什么样的字段
     * @param tableName
     * @return
     */
    public List<String> listTableColByTableName(String tableName) ;

}
