package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.PerformDefaultScheduleApi;
import com.jims.sys.bo.PerformDefaultScheduleBo;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.PerformDefaultSchedule;
import com.jims.sys.entity.PerformFreqDict;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lgx on 2016/6/21.
 */
@Service(version = "1.0.0")
public class PerformDefaultScheduleImpl implements PerformDefaultScheduleApi{

    @Autowired
    private PerformDefaultScheduleBo bo;

    @Override
    public String save(PerformDefaultSchedule entity) {
        try {
            bo.save(entity);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }

    @Override
    public String delete(String ids) {
        try {
            bo.delete(ids);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }

    @Override
    public List<PerformDefaultSchedule> findList(PerformDefaultSchedule entity) {
        return bo.findList(entity);
    }

    /**
     * 取以后的频次或给药途径
     * @param type freqDesc
     * @return
     */
    public List<PerformDefaultSchedule> findTypeList(String type){
        return bo.findTypeList(type);
    }

    /**
     * 检索某给药途径未添加的频次
     * @param administration
     * @return
     */
    public List<PerformFreqDict> findNoExistFreq(String administration){
        return bo.findNoExistFreq(administration);
    }

    /**
     * 检索某频次未添加的给药途径
     * @param freqDesc
     * @return
     */
    public List<AdministrationDict> findNoExistAdministration(String freqDesc){
        return bo.findNoExistAdministration(freqDesc);
    }
}
