package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ClinicItemApi;
import com.jims.clinic.dao.ClinicItemDictDao;
import com.jims.clinic.dao.ClinicItemNameDictDao;
import com.jims.clinic.dao.ClinicVsChargeDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 诊疗项目Service
 * @author lgx
 * @version 2016-04-28
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ClinicItemDictServiceImpl extends CrudImplService<ClinicItemDictDao, ClinicItemDict> implements ClinicItemApi{

    @Autowired
    private ClinicItemNameDictDao nameDao;
    @Autowired
    private ClinicVsChargeDao vsDao;

    @Override
    public boolean codeOrNameHas(ClinicItemDict entity){
        List<ClinicItemDict> list = dao.findExisted(entity);
        return list != null && list.size() > 0 ? true : false;
    }

    @Transactional(readOnly = false)
    public String save(List<ClinicItemDict> entityList){
        int i = 0;
        if(entityList != null){
            for(ClinicItemDict entity : entityList){
                if("1".equals(super.save(entity))){
                    i ++ ;
                }
            }
        }
        return i + "";
    }

    @Override
    @Transactional(readOnly = false)
    public String deleteCascade(ClinicItemDict entity) {
        try{
            deleteName(entity);
            deleteVs(entity);
            super.delete(entity);
        }catch(Exception e){
            return "0";
        }
        return "1";
    }

    @Override
    @Transactional(readOnly = false)
    public String deleteCascade(String ids) {
        int i=0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                ClinicItemDict entity = super.get(id[j]);
                if("1".equals(deleteCascade(entity))){
                    i ++ ;
                }
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    public List<ClinicItemNameDict> findNameList(ClinicItemDict entity) {
        ClinicItemNameDict itemName = new ClinicItemNameDict();
        itemName.setOrgId(entity.getOrgId());
        itemName.setItemClass(entity.getItemClass());
        itemName.setItemCode(entity.getItemCode());
        return nameDao.findList(itemName);
    }

    @Override
    @Transactional(readOnly = false)
    public String save(ClinicItemNameDict entity) {
        int i=0;
        try{
            if (entity.getIsNewRecord()){
                entity.preInsert();
                i=nameDao.insert(entity);
            }else{
                entity.preUpdate();
                i=nameDao.update(entity);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    @Transactional(readOnly = false)
    public String saveNameList(List<ClinicItemNameDict> entityList){
        int i = 0;
        if(entityList != null){
            for(ClinicItemNameDict entity : entityList){
                if("1".equals(save(entity))){
                    i ++ ;
                }
            }
        }
        return i + "";
    }

    @Override
    @Transactional(readOnly = false)
    public String deleteName(String ids) {
        int i=0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                i += nameDao.delete(id[j]);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    @Transactional(readOnly = false)
    public String delete(ClinicItemNameDict entity) {
        int i=0;
        try{
            if(entity.getId() == null){
                i = nameDao.deleteNoId(entity);
            } else {
                i = nameDao.delete(entity);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    @Transactional(readOnly = false)
    public String deleteName(ClinicItemDict entity) {
        ClinicItemNameDict itemName = new ClinicItemNameDict();
        itemName.setOrgId(entity.getOrgId());
        itemName.setItemClass(entity.getItemClass());
        itemName.setItemCode(entity.getItemCode());
        return delete(itemName);
    }

    @Override
    public List<ClinicVsCharge> findVsList(ClinicItemDict entity) {
        ClinicVsCharge vs = new ClinicVsCharge();
        vs.setOrgId(entity.getOrgId());
        vs.setClinicItemClass(entity.getItemClass());
        vs.setClinicItemCode(entity.getItemCode());
        return vsDao.findList(vs);
    }

    @Override
    @Transactional(readOnly = false)
    public String save(ClinicVsCharge entity) {
        int i=0;
        try{
            if (entity.getIsNewRecord()){
                entity.preInsert();
                i=vsDao.insert(entity);
            }else{
                entity.preUpdate();
                i=vsDao.update(entity);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    @Transactional(readOnly = false)
    public String saveVsList(List<ClinicVsCharge> entityList){
        int i = 0;
        if(entityList != null){
            for(ClinicVsCharge entity : entityList){
                if("1".equals(save(entity))){
                    i ++ ;
                }
            }
        }
        return i + "";
    }

    @Override
    @Transactional(readOnly = false)
    public String delete(ClinicVsCharge entity) {
        int i=0;
        try{
            if(entity.getId() == null){
                i = vsDao.deleteNoId(entity);
            } else {
                i = vsDao.delete(entity);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    @Transactional(readOnly = false)
    public String deleteVs(String ids) {
        int i=0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                i += vsDao.delete(id[j]);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    @Override
    @Transactional(readOnly = false)
    public String deleteVs(ClinicItemDict entity) {
        ClinicVsCharge vs = new ClinicVsCharge();
        vs.setOrgId(entity.getOrgId());
        vs.setClinicItemClass(entity.getItemClass());
        vs.setClinicItemCode(entity.getItemCode());
        return delete(vs);
    }

}