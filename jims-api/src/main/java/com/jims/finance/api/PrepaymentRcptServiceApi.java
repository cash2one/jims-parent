package com.jims.finance.api;

import com.jims.common.web.impl.BaseDto;
import com.jims.finance.entity.PrepaymentRcpt;

import java.util.List;

/**
 * 预交金记录Service
 * @author CTQ
 * @version 2016-05-25
 */

public interface PrepaymentRcptServiceApi{

    /**
     * 根据参数查询列表数据
     * @param prepaymentRcpt
     * @author CTQ
     * @date 2016-05-30 14:49:19
     * @return
     */
    public List<PrepaymentRcpt> findList(PrepaymentRcpt prepaymentRcpt);
    /**
     * 根据参数查询预交金列表
     * @param patientId
     * @author CTQ
     * @date 2016-05-30 14:49:19
     * @return
     */
    public List<PrepaymentRcpt> findByPatientId(String patientId);
    /**
     * 保存数据
     * @param prepaymentRcpt
     * @author CTQ
     * @date 2016-05-30 14:49:19
     * @return
     */
    public String save(PrepaymentRcpt prepaymentRcpt);
    /**
     * 根据ID查询数据
     * @param id
     * @author CTQ
     * @date 2016-05-30 14:49:19
     * @return
     */
    public PrepaymentRcpt get(String id);

    /**
     * @return BaseDto   返回类型
     * @Descripion: (根据参数查询预交金交费记录列表)
     * @author CTQ
     * @date 2016/6/29
     */
    public List<BaseDto> findRecordList(PrepaymentRcpt prepaymentRcpt);
	
}