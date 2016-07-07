package com.jims.medical.lab.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.lab.dao.LabTestMasterDao;
import com.jims.lab.entity.LabTestMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 * 检验确认 bo
 * @author zhaoning
 */
@Service
@Transactional(readOnly = false)
public class LabConfirmBo extends CrudImplService<LabTestMasterDao,LabTestMaster> {
    @Autowired
    private LabTestMasterDao labTestMasterDao;

    /**
     * 根据当前登录人所在科室，查询检验主记录
     * @param performedBy
     * @return
     * @author zhaoning
     */
    public List<LabTestMaster> getLabMaster(String performedBy){
     return labTestMasterDao.getLabMaster(performedBy);
    }

    /**
     * 检验确认
     * @param labTestMaster
     * @return
     * @author zhaoning
     */
    public String confirmLab(LabTestMaster labTestMaster){
        String code="";
        if(labTestMaster!=null && labTestMaster.getId()!=null){
            if(labTestMaster.getStatus()!=null && labTestMaster.getStatus().equals("1")){
                code="2";
            }else {
                String id=labTestMaster.getId();
                labTestMasterDao.updateLabMaster(id);
                code="1";
            }

        }
        return code;
    }
}
