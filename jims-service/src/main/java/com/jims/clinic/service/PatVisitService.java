package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.PatVisitApi;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.clinic.entity.PatVisit;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by che on 2016/4/20.
 */
@Service
@Transactional(readOnly = true)
public class PatVisitService extends CrudImplService<PatVisitDao,PatVisit> implements PatVisitApi {
   @Autowired
    private   PatVisitDao  patVisitDao;

    /**
     *新增修改病人住院记录信息
     * @param patVisit
     * @author che
     * @version 2016-04-19
     */
    @Override
    public void savePatVisit(PatVisit patVisit) {
          this.save(patVisit);
    }
}
