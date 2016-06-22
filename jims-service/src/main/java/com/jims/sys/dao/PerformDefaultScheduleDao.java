package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.PerformDefaultSchedule;
import com.jims.sys.entity.PerformFreqDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lgx on 2016/6/21.
 */
@MyBatisDao
public interface PerformDefaultScheduleDao extends CrudDao<PerformDefaultSchedule>{

    /**
     * 取以后的频次或给药途径
     * @param type freqDesc
     * @return
     */
    public List<PerformDefaultSchedule> findTypeList(@Param("type") String type);
    /**
     * 检索某给药途径未添加的频次
     * @param administration
     * @return
     */
    public List<PerformFreqDict> findNoExistFreq(String administration);

    /**
     * 检索某频次未添加的给药途径
     * @param freqDesc
     * @return
     */
    public List<AdministrationDict> findNoExistAdministration(String freqDesc);
}
