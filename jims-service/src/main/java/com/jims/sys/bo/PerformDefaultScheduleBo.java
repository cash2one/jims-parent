package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.PerformDefaultScheduleDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.PerformDefaultSchedule;
import com.jims.sys.entity.PerformFreqDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author lgx
 * @version 2016-06-21
 */
@Service
@Component
@Transactional(readOnly = false)
public class PerformDefaultScheduleBo extends CrudImplService<PerformDefaultScheduleDao, PerformDefaultSchedule> {

    /**
     * 取以后的频次或给药途径
     * @param type freqDesc
     * @return
     */
    public List<PerformDefaultSchedule> findTypeList(String type){
        return dao.findTypeList(type);
    }

    /**
     * 检索某给药途径未添加的频次
     * @param administration
     * @return
     */
    public List<PerformFreqDict> findNoExistFreq(String administration){
        return dao.findNoExistFreq(administration);
    }

    /**
     * 检索某频次未添加的给药途径
     * @param freqDesc
     * @return
     */
    public List<AdministrationDict> findNoExistAdministration(String freqDesc){
        return dao.findNoExistAdministration(freqDesc);
    }
}