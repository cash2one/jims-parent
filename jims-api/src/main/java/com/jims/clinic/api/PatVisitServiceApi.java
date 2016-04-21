package com.jims.clinic.api;

import com.jims.clinic.entity.PatVisit;

/**
 * 病人住院记录信息api
 * @author Chefj
 * @version 2016-04-20
 */
public interface PatVisitServiceApi {
    /**
     * 新增\修改 病人住院记录信息
     * @param patVisit
     */
   public String  save(PatVisit patVisit );

}
