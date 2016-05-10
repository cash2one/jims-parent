package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugStorageDept;

import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 * 药品库存单位Api接口
 * @author zhaoning
 * @version 2016-04-23
 */
public interface DrugStorageDeptServiceApi {
    /**
     * 保存数据
     * @param drugStorageDept
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String save(DrugStorageDept drugStorageDept);

    /**
     * 分页查询数据
     * @param page
     * @param drugStorageDept
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public Page<DrugStorageDept> findPage(Page<DrugStorageDept> page,DrugStorageDept drugStorageDept);

    /**
     * 查询列表数据
     * @param drugStorageDept
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public List<DrugStorageDept> findList(DrugStorageDept drugStorageDept);

    /**
     * 获得单条数据
     * @param drugStorageDept
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public DrugStorageDept get(DrugStorageDept drugStorageDept);

    /**
     * 删除数据
     * @param ids
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String delete(String ids);
}

