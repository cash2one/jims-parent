package com.jims.sys.api;

import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.PerformDefaultSchedule;
import com.jims.sys.entity.PerformFreqDict;

import java.util.List;

/**
 * Created by lgx on 2016/6/21
 */
public interface PerformDefaultScheduleApi {

    /**
     * 保存
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(PerformDefaultSchedule entity);

    /**
     * 删除
     * @param ids 多个id以逗号（,）隔开
     * @return 0 失败，1成功
     */
    public String delete(String ids);

    /**
     * 检索
     * @param entity
     * @return
     */
    public List<PerformDefaultSchedule> findList(PerformDefaultSchedule entity);

    /**
     * 取以后的频次或给药途径
     * @param type freqDesc
     * @return
     */
    public List<PerformDefaultSchedule> findTypeList(String type);

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
