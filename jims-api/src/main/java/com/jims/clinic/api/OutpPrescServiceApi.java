package com.jims.clinic.api;

import com.jims.clinic.entity.OutpPresc;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 * 处方明细记录api接口
 * @author zhaoning
 * @version 2016-04-21
 */
public interface OutpPrescServiceApi {
    public List<OutpPresc> findList(OutpPresc outpPresc);
    /**
     * 根据病人就诊记录ID查询处方主记录
     * @param clinicMasterId
     * @return
     */
    public List<OutpPresc> getOutpPresc(String clinicMasterId);

    /**
     * 保存处方信息
     * @param outpPresc
     * @return
     */
    public String save(OutpPresc outpPresc);

    /**
     * 删除方法
     * @param ids
     */
    public String deletePresc(String ids);

    /**
     * 根据参数查询处方相关信息
     * @param outpPresc
     * @author CTQ
     * @return
     */
    public List<OutpPresc> findListByParams(OutpPresc outpPresc);
}
