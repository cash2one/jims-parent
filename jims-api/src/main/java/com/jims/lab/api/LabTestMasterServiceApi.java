package com.jims.lab.api;

import com.jims.lab.entity.LabTestMaster;

/**
 * Created by Administrator on 2016/4/28.
 * 检验主记录Api接口
 * @author xueyx
 * @version 2016-04-28
 */
public interface LabTestMasterServiceApi {

    /**
     * 保存或编辑
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */
    public void saveAll(LabTestMaster labTestMaster);
}
