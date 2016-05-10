package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugNameDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 * 药品名称字典Api接口
 * @author zhaoning
 * @version 2016-04-23
 */
public interface DrugNameDictServiceApi {
    /**
     * 保存药品名称
     * @param drugNameDict
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String save(DrugNameDict drugNameDict);

    /**
     * 分页查询数据
     * @param page
     * @param drugNameDict
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public Page<DrugNameDict> findPage(Page<DrugNameDict> page,DrugNameDict drugNameDict);

    /**
     * 查询列表数据
     * @param drugNameDict
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public List<DrugNameDict> findList(DrugNameDict drugNameDict);

    /**
     * 获取单个对象
     * @param id
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public DrugNameDict get(String id);

    /**
     * 删除数据（单条数据删除，批量删除）
     * @param ids
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String delete(String ids);


}
