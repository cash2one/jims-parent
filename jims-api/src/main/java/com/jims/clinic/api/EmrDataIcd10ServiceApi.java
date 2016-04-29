package com.jims.clinic.api;

import com.jims.clinic.entity.EmrDataIcd10;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * EmrDataIcd10ServiceApi
 * icd10编码
 * @author PangQian
 * @date2016/4/26 0026
 */
public interface EmrDataIcd10ServiceApi {
    /**
     * 通过模糊查询拿到icd集合
     * @param emrDataIcd10
     * @return
     */
    public Page<EmrDataIcd10> findPage(Page<EmrDataIcd10> page,EmrDataIcd10 emrDataIcd10);

    public List<EmrDataIcd10> findList(EmrDataIcd10 emrDataIcd10);
}
