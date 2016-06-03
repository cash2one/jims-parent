package com.jims.nurse.api;

import com.jims.common.persistence.Page;
import com.jims.nurse.entity.BedRec;

import java.util.List;

/**
 * 床位记录
 *
 * @author PangQian
 * @date2016/6/2 0002
 */
public interface BedRecServiceApi {
    /**
     * 查询护士所负责的病区的床位信息
     * @return
     */
    public Page<BedRec> findPage(Page<BedRec> page, BedRec bedRec);
}
