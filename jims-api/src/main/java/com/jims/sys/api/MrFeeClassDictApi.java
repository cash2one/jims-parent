package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.MrFeeClassDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface MrFeeClassDictApi {
    /**
     * 查询字段列表
     * @param page
     * @param mrFeeClassDict
     * @return
     */
    public Page<MrFeeClassDict> findPage(Page<MrFeeClassDict> page, MrFeeClassDict mrFeeClassDict);

    /**
     * 保存修改数据
     * @param mrFeeClassDict
     * @return
     */
    public String save(MrFeeClassDict mrFeeClassDict);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public MrFeeClassDict get(String id);
}
