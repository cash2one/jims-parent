package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugDispApi;
import com.jims.phstock.bo.DrugDispBo;
import com.jims.phstock.vo.PatDrugDisp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by heren on 2016/6/29.
 */
@Service(version = "1.0.0")
public class DrugDispImpl implements DrugDispApi {

    @Autowired
    private DrugDispBo drugDispBo;

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
     * @return
     */
    public List<PatDrugDisp> calcPatDrugDisp(String orgId, String deptCode, String wardDeptCode, List<String> administations, List<String> bedNos, String repeatIndicator, int dispDays) {
        return drugDispBo.calcPatDrugDisp(orgId, deptCode, wardDeptCode, administations, bedNos, repeatIndicator, dispDays);
    }
}
