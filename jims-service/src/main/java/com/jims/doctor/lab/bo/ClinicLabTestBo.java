package com.jims.doctor.lab.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.NumberUtils;
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
    @Autowired
    private OutpOrdersDao outpOrdersDao;
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
        labTestMaster.setOrgId(clinicMaster.getOrgId());
        //申请医生(暂无)
        labTestMaster.setOrderingProvider("");
        //申请科室(暂无)
        labTestMaster.setOrderingDept("");
         //申请状态
         labTestMaster.setStatus(labTestMaster.LAB_STATUS_APPLY);
        //结果状态
        labTestMaster.setResultStatus(labTestMaster.LAB_RESULTSTATUS_APPLY);

        //申请序号
        labTestMaster.setTestNo(NumberUtils.getClinicLab(clinicMaster.getId()));
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
                labTestItems.setBillingIndicator(labTestItems.LAB_BILLINGINDICATOR_APPLY);
                labTestItems.setLabMaster(labTestMaster.getId());
                labTestItems.setTestNo(labTestMaster.getTestNo());
                labTestItems.preInsert();
                labTestItemsDao.insert(labTestItems);
                clinicItemDictList.add(clinicItemDict);
            }
            costOrdersUtilsService.save(labTestMaster.getClinicId(), clinicItemDictList, labTestMaster.getId(),null);
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
                OutpTreatRec outpTreatRec = outpTreatRecDao.getSerialNo(id[j]);
                outpTreatRecDao.deleteTreat(outpTreatRec.getSerialNo());
                outpOrdersDao.deleteOutpOrders(outpTreatRec.getSerialNo());
                outpOrdersCostsDao.deleteOutpOrdersCosts(outpTreatRec.getSerialNo());
                num = labTestMasterDao.deleteLabTestMaster(id[j]);

            }
        }catch(Exception e){
            return num+"";
        }
        return num+"";

    }
}
