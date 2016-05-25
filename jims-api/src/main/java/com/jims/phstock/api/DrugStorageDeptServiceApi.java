package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugStorageDept;
import com.jims.phstock.entity.DrugSubStorageDept;
import com.jims.sys.vo.BeanChangeVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 * 药品库存单位Api接口
 * @author zhaoning
 * @version 2016-04-23
 */
public interface DrugStorageDeptServiceApi {
    /**
     * 保存增删改数据
     * @param beanChangeVo 增删改数据集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<DrugStorageDept> beanChangeVo);

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

    /**
     * 获取药品库存单位子单位
     * @param orgId 所属机构
     * @param storageCode  库存单位编码
     * @return
     */
    public List<DrugSubStorageDept> findSubList(String orgId,String storageCode);

    /**
     * 保存子单位
     * @param sub
     * @return
     */
    public String saveSub(DrugSubStorageDept sub);
}

