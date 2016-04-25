package com.jims.materiel.api;

import com.jims.common.persistence.Page;
import com.jims.materiel.entity.DrugDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/23.
 * 药品字典Api接口
 * @author zhaoning
 * @version 2016-04-23
 */
public interface DrugDictServiceApi {

    /**
     * 保存药品字典数据
     * @param drugDict
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String save(DrugDict drugDict);

    /**
     * 分页查询数据
     * @param page
     * @param drugDict
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public Page<DrugDict>  findPage(Page<DrugDict> page , DrugDict drugDict);

    /**
     * 查询列表数据
     * @param drugDict
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public List<DrugDict> findList(DrugDict drugDict);

    /**
     * 获取单条数据
     * @param id
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public DrugDict get(String id);

    /**
     * 根据ID删除数据(单条数据删除、批量删除)
     * @param ids
     * @return
     * @author zhaoning
     * @version 2016-04-23
     */
    public String delete(String ids);
}
