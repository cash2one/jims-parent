package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugDispPropertyDict;

import java.util.List;

/**
 * 摆药类别接口
 * Created by heren on 2016/5/10.
 */
public interface DrugDispPropertyDictApi {

    /**
     * 获取摆药类别
     * @param id 摆药类别主键ID
     * @return
     */
    public DrugDispPropertyDict get(String id);

    /**
     * 根据摆药类别获取摆药集合
     * @param drugDispPropertyDict
     * @return
     */
    public List<DrugDispPropertyDict> findList(DrugDispPropertyDict drugDispPropertyDict);

    /**
     * 获取摆药类别列表
     * @return
     */
    public List<DrugDispPropertyDict> findAllList();

    /**
     * 根据摆药类别获取摆药集合
     * @param page
     * @param drugDispPropertyDict
     * @return
     */
    public Page<DrugDispPropertyDict> findPage(Page<DrugDispPropertyDict> page, DrugDispPropertyDict
            drugDispPropertyDict);

    /**
     * 保存功能
     * @param drugDispPropertyDict
     */
    public String save(DrugDispPropertyDict drugDispPropertyDict);

    /**
     * 删除功能
     * @param drugDispPropertyDict
     */
    public String delete(DrugDispPropertyDict drugDispPropertyDict);

    /**
     * 删除功能
     * @param id
     * @return
     */
    public String delete(String id);
}
