package com.jims.phstock.bo;

import com.jims.clinic.entity.PatsInHospital;
import com.jims.phstock.api.DrugDispApi;
import com.jims.phstock.dao.DrugDispDao;
import com.jims.phstock.vo.OrdersDispInfo;
import com.jims.phstock.vo.PatDrugDisp;
import com.jims.phstock.vo.PatientBaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 医嘱摆药
 * Created by heren on 2016/6/29.
 */
@Component
public class DrugDispBo {

    @Autowired
    private DrugDispDao drugDispDao ;

    /**
     * 计算摆药
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
        //第一步获取所有的摆药医嘱
        List<PatDrugDisp> patDrugDisps = new ArrayList<PatDrugDisp>() ;
        List<PatientBaseVo> patsInHospitals = drugDispDao.findPatsInHospital(wardDeptCode,deptCode,orgId,bedNos) ;

        if(patsInHospitals ==null){
            return null ;
        }

        //region 循环遍历每一个病人的
        for(PatientBaseVo baseVo:patsInHospitals){
            String patientId = baseVo.getPatientId() ;
            int visitId = baseVo.getVisitId() ;
            String orgIdTemp =baseVo.getOrgId() ;
            List<OrdersDispInfo> ordersDispInfos = drugDispDao.findOrdersDispInfos(patientId,visitId,orgIdTemp);

            PatDrugDisp patDrugDisp = new PatDrugDisp() ;
            patDrugDisp.setOrdersDispInfos(ordersDispInfos);

            for(OrdersDispInfo ordersDispInfo:ordersDispInfos){

            }

        }
        //endregion

        //第二步获取摆药开始时间、摆药结束时间
        //第三步计算每一条医嘱摆药数量

        return null;
    }
}
