/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.bo;

import com.jims.clinic.dao.*;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.clinic.utils.CostOrdersUtils;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.api.ExamAppointsServiceApi;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * 检查预约记录BAO层
 *
 * @author zhangyao
 * @version 2016-04-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class ExamAppointsBo extends CrudImplService<ExamAppointsDao, ExamAppoints> implements ExamAppointsServiceApi {

    @Autowired
    private ExamAppointsDao examAppointsDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private ExamItemsDao examItemsDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;

    /**
     * 查询预约主记录
     *
     * @param patientId
     * @return
     */
    @Override
    public List<ExamAppoints> getExamAppionts(String patientId) {
        return examAppointsDao.getExamAppionts(patientId);
    }

    /**
     * 删除预约记录
     *
     * @param ids
     * @return
     */
    @Override
    public String deleteExamAppionts(String ids) {
        int num =0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                examItemsDao.deleteItems(id[j]);
                ExamAppoints examAppoints=examAppointsDao.get(id[j]);
                String clinicId=examAppoints.getClinicId();
//                ExamItems examItems=examItemsDao.getItemList(id[j]);
                outpTreatRecDao.deleteTreat(clinicId);
                outpOrdersCostsDao.deleteOutpOrders(clinicId);
                num = examAppointsDao.deleteExamAppionts(id[j]);

            }
        }catch(Exception e){
            return num+"";
        }
        return num+"";

    }

    /**
     * 获得最大的检查申请号
     *
     * @return
     */
    @Override
    public Integer getMaxExamNo() {
        return examAppointsDao.getMaxExamNo();
    }

    /**
     * 保存检查申请记录
     * @param examAppoints
     * @return
     */
    @Override
    public int batchSave(ExamAppoints examAppoints) {
        int  num=0;
        ClinicMaster clinicMaster=clinicMasterDao.get(examAppoints.getClinicId());
        //添加EXAM_APPOINTS 相应字段
        examAppoints.setCnsltState(0);
        examAppoints.preInsert();
        examAppoints.setVisitNo(clinicMaster.getVisitNo());
        examAppoints.setChargeType(clinicMaster.getChargeType());
        List<ClinicItemDict> clinicItemDictList=new ArrayList<ClinicItemDict>();
        List<ExamItems> examItemsList=examAppoints.getExamItemsList();
        for(int i=0;i<examItemsList.size();i++){
            ClinicItemDict clinicItemDict=new ClinicItemDict();
            ExamItems examItems=examItemsList.get(i);
            clinicItemDict.setInputCode(examItems.getExamItemCode());
            examItems.setAppointsId(examAppoints.getId());
            examItems.setClinicId(examAppoints.getClinicId());
            examItems.setVisitId(examAppoints.getVisitId());
            examItems.preInsert();
            examItemsDao.saveExamItems(examItems);
            OutpTreatRec outpTreatRec=new OutpTreatRec();
            //outpTreatRec.preInsert();
            outpTreatRec.setItemClass("D");
            outpTreatRec.setClinicId(examItems.getClinicId());
            outpTreatRec.setItemName(examItems.getExamItem());
            outpTreatRec.setItemCode(examItems.getExamItemCode());
            outpTreatRec.setPerformedBy(examAppoints.getPerformedBy());
            outpTreatRec.setCosts(examItems.getCosts());
            outpTreatRec.setCharges(examItems.getCosts());
            outpTreatRec.setAppointNo(examItems.getExamNo());
            outpTreatRec.setRcptNo(examItems.getRcptNo());
            outpTreatRecDao.saveTreatRec(outpTreatRec);
            clinicItemDictList.add(clinicItemDict);
        }

        CostOrdersUtils costOrdersUtils=new CostOrdersUtils();
        costOrdersUtils.save(examAppoints.getClinicId(),clinicItemDictList);
        num = examAppointsDao.saveExamAppionts(examAppoints);
        return  num;
    }

}