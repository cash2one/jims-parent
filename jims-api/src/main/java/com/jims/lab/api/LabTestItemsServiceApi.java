package com.jims.lab.api;

import com.jims.lab.entity.LabTestItems;

import java.util.List;


/**
 * Created by Administrator on 2016/4/28.
 * 检验项目Api接口
 * @author xueyx
 * @version 2016-04-28
 */
public interface LabTestItemsServiceApi {
    public List<LabTestItems> getItemName(String labMaster);
}
