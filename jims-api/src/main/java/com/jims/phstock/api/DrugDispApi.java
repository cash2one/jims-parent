package com.jims.phstock.api;

import com.jims.phstock.vo.PatDrugDisp;

import java.util.Date;
import java.util.List;

/**
 * 医嘱摆药相关
 * Created by heren on 2016/6/29.
 */
public interface DrugDispApi {

    /**
     * 计算摆药
     *
     * @param orgId           组织机构ID
     * @param deptCode        入院科室
     * @param wardDeptCode    入院护理单元
     * @param administations  摆药类型
     * @param bedNos          摆药床位
     * @param repeatIndicator 长临
     * @param dispDays        摆药天数
     * @param dispStartTime   摆药开始时间
     * @param dispStopTime    摆药结束时间
     * @return
     */
    public List<PatDrugDisp> calcPatDrugDisp(String orgId, String deptCode, String wardDeptCode, List<String> administations,
                                             List<String> bedNos, String repeatIndicator, int dispDays,
                                             Date dispStartTime, Date dispStopTime);


}
