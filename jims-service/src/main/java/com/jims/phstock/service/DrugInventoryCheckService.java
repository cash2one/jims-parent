package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.UserUtils;
import com.jims.phstock.api.DrugInventoryCheckApi;
import com.jims.phstock.bo.DrugInventoryCheckBo;
import com.jims.phstock.dao.*;
import com.jims.phstock.entity.*;
import com.jims.phstock.vo.DrugInventoryCheckVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 药品盘点Service
 * @author txb
 * @version 2016-05-23
 */
@Service(version = "1.0.0")
public class DrugInventoryCheckService implements DrugInventoryCheckApi{

    @Autowired
    private DrugInventoryCheckBo bo;

    /**
     * 生成盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    @Override
    public List<DrugInventoryCheckVo> generateInventory(String storage, String orgId, String checkYearMonth,String subStorage) {
        return bo.generateInventory(storage,orgId,checkYearMonth,subStorage);
    }
    /**
     * 提取盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    @Override
    public List<DrugInventoryCheckVo> extractInventory(String storage, String orgId, String checkYearMonth,String subStorage) {
        return bo.extractInventory(storage, orgId, checkYearMonth,subStorage);
    }
    /**
     * 暂存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    @Override
    public String temporaryStorage(List<DrugInventoryCheckVo> drugInventoryCheckVos) {
        try {
            bo.temporaryStorage(drugInventoryCheckVos);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }
    /**
     * 保存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    @Override
    public String saveInventory(List<DrugInventoryCheckVo> drugInventoryCheckVos) {
        try {
            bo.saveInventory(drugInventoryCheckVos);
            return "1";
        } catch (RuntimeException e){}
        return "0";
    }
}