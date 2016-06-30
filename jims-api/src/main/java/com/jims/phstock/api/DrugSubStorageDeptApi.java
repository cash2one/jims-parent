package com.jims.phstock.api;

import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * 药品子库存单位Api接口
 * Created by fyg on 2016/6/28.
 */
public interface DrugSubStorageDeptApi {

    /**
     * 根据库存单位代码获取该库存单位下的子库存单位列表
     * @param orgId  所属组织机构ID
     * @param storageCode 库存单位代码
     * @return 子库存单位列表
     * @author fengyuguang
     */
    public List<DrugSubStorageDept> getListByStorageCode(String orgId,String storageCode);

    /**
     * 保存增删改数据
     * @param beanChangeVo
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugSubStorageDept> beanChangeVo);

}
