package com.jims.clinic.api;

import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.persistence.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmrDiagnosisServiceApi
 * 诊断
 * @author PangQian
 * @date2016/4/26 0026
 */
public interface EmrDiagnosisServiceApi {
    /**
     * 通过父级Id查询诊断
     * @param parentId
     * @return
     */
    public List<EmrDiagnosis> findAllListByParent(String parentId);

    /**根据父类查询对应的诊断类型
     *
     * @param parent
     * @param type
     * @return
     */
    public List<EmrDiagnosis> findAllListByType( String parent,String type);

    /**
     * 分页查询数据
     * @param page
     * @param emrDiagnosis
     * @return
     */
    public Page<EmrDiagnosis> findPage(Page<EmrDiagnosis> page, EmrDiagnosis emrDiagnosis);

    /**
     * 保存
     * @param emrDiagnosis
     * @return
     */
    public String saveDiagnosis( List<EmrDiagnosis> emrDiagnosis);

    /**
     * 删除
     * @param ids
     * @return
     */
    public String delete(String ids);
}
