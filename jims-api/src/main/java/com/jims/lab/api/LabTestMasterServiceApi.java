package com.jims.lab.api;

import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
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
    public String saveAll(LabTestMaster labTestMaster,LoginInfo loginInfo);

    /**
     * 住院保存
     * 整个主表、字表list
     * @param主表LabTestMaster
     * @param子表List
     */

    public String saveAllIn(LabTestMaster labTestMaster,LoginInfo loginInfo);


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
     * 门诊删除
     * @param ids
     * @return
     */
    public String deleteLabTestMaster(String ids);

    /**
     * 住院删除
     * @param ids
     * @return
     */
    public String deleteLabTestMasterHos(String ids);
}
