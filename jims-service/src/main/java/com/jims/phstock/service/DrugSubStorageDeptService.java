package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugSubStorageDeptApi;
import com.jims.phstock.bo.DrugSubStorageDeptBo;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 药品库存子单位service
 * Created by fyg on 2016/6/28.
 */
@Service(version = "1.0.0")

public class DrugSubStorageDeptService implements DrugSubStorageDeptApi {
    @Autowired
    private DrugSubStorageDeptBo drugSubStorageDeptBo;

    /**
     * 根据库存单位代码获取该库存单位下的子库存单位列表
     * @param orgId 所属组织机构ID
     * @param storageCode 库存单位代码
     * @return 子库存单位列表
     * @author fengyuguang
     */
    public List<DrugSubStorageDept> getListByStorageCode(String orgId,String storageCode){
        return drugSubStorageDeptBo.getListByStorageCode(orgId,storageCode);
    }

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugSubStorageDept> beanChangeVo){
        return drugSubStorageDeptBo.merge(beanChangeVo);
    }
}
