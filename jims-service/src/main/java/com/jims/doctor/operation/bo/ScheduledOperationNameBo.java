package com.jims.doctor.operation.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.doctor.operation.dao.ScheduledOperationNameDao;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 * 手术子表Bo
 */
@Service
@Transactional(readOnly = false)
public class ScheduledOperationNameBo extends CrudImplService<ScheduledOperationNameDao,ScheduledOperationName>{
    @Autowired
    private ScheduledOperationNameDao scheduledOperationNameDao;

    /**
     * 通过scheduleId获取手术安排
     * @param scheduleId
     * @return
     */
    public List<ScheduledOperationName> getOperationNameList(String scheduleId){
        List<ScheduledOperationName> scheduledOperationNameList = scheduledOperationNameDao.getOperationNameList(scheduleId);
        return scheduledOperationNameList;
    }
}
