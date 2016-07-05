package com.jims.lab.api;

import com.jims.common.persistence.Page;
import com.jims.lab.entity.LabTestMaster;


/**
 * Created by Administrator on 2016/4/28.
 * 检验主记录Api接口
 * @author xueyx
 * @version 2016-04-28
 */
public interface LabTestMasterServiceApi {

    /**
     * 门诊保存或编辑
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     * @author xueyx
     * @version 2016/5/06
     */
    public String saveAll(LabTestMaster labTestMaster);

    /**
     * 住院保存
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     */

    public String saveAllIn(LabTestMaster labTestMaster);

    /**
     * 生成申请序号
     * @param主表 当前日期
     * @author xueyx
     * @version 2016/5/09
     */
    public String creatTestNo();

    /**
     * 删除申请
     * @param主表id
     * @author xueyx
     * @version 2016/5/09
     */
    public void delAll(String ids);

    /**
     *
     * @param
     * @param
     * @return
     * @author xueyx
     * @version 2016/5/13
     */
    public Page<LabTestMaster> findPage(Page<LabTestMaster> page, LabTestMaster labTestMaster);

    /**
     * 住院删除
     * @param ids
     * @return
     */
    public String deleteLabTestMaster(String ids);
}
