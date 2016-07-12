package com.jims.doctor.lab.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.lab.dao.LabTestItemsDao;
import com.jims.doctor.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 * 门诊检验申请BO
 */
@Service
@Transactional(readOnly = false)
public class ClinicLabTestBo extends CrudImplService<LabTestMasterDao, LabTestMaster> {

    @Autowired
    private LabTestItemsDao labTestItemsDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;
    @Autowired
    private LabTestMasterDao labTestMasterDao;
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    /**
     * 门诊检验保存
     *
     * @param labTestMaster
     * @return
     */
    public String saveAll(LabTestMaster labTestMaster) {
        int num=0;
        ClinicMaster clinicMaster=clinicMasterDao.get(labTestMaster.getClinicId());
        labTestMaster.preInsert();
        labTestMaster.setOrgId(clinicMaster.getId());
        //申请医生(暂无)
        labTestMaster.setOrderingProvider("");
        //申请科室(暂无)
        labTestMaster.setOrderingDept("");
         //申请状态
         labTestMaster.setStatus(labTestMaster.LAB_STATUS_APPLY);
        //结果状态
        labTestMaster.setResultStatus(labTestMaster.LAB_RESULTSTATUS_APPLY);

        //申请序号
        String testNo="JC"+clinicMaster.getClinicNo()+(int)(Math.random()*9000);
        labTestMaster.setTestNo(testNo);
        //打印标记
        labTestMaster.setPrintIndicator(labTestMaster.PRINTINDICATOR_NOT);
        //申请时间
        labTestMaster.setRequestedDateTime(new Date());

        List<ClinicItemDict> clinicItemDictList = new ArrayList<ClinicItemDict>();
        List<LabTestItems> labTestItemsList = labTestMaster.getList();
        if (labTestItemsList.size() > 0) {
            for (int i = 0; i < labTestItemsList.size() ; i++) {
                ClinicItemDict clinicItemDict = new ClinicItemDict();
                LabTestItems labTestItems = labTestItemsList.get(i);
                clinicItemDict.setItemCode(labTestItems.getItemCode());
                clinicItemDict.setOrgId(labTestMaster.getOrgId());
                labTestItems.setItemNo(i + 1);
                labTestItems.setLabMaster(labTestMaster.getId());
                labTestItems.setTestNo(labTestMaster.getTestNo());
                labTestItems.preInsert();
                labTestItemsDao.insert(labTestItems);
                clinicItemDictList.add(clinicItemDict);
            }
            costOrdersUtilsService.save(labTestMaster.getClinicId(), clinicItemDictList, labTestMaster.getId());
            num = labTestMasterDao.insert(labTestMaster);
            return num + "";
        }
    return "0";
    }


    /**
     * 删除门诊记录
     *
     * @return
     */
    public String deleteLabTestMaster(String ids) {
        int num =0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                labTestItemsDao.deleteItmes(id[j]);
                LabTestMaster labTestMaster=labTestMasterDao.get(id[j]);
                String clinicId=labTestMaster.getClinicId();
//                ExamItems examItems=examItemsDao.getItemList(id[j]);
                outpTreatRecDao.deleteTreat(clinicId);
                outpOrdersCostsDao.deleteOutpOrders(clinicId);
                num = labTestMasterDao.deleteLabTestMaster(id[j]);

            }
        }catch(Exception e){
            return num+"";
        }
        return num+"";

    }
}
