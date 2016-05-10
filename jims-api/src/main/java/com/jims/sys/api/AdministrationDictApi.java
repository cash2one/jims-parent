package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.Dict;

import java.util.List;

/**
 * 给药途径字典表
 * Created by ztq on 2016/5/10.
 */
public interface AdministrationDictApi {

    /**
     * 获取单条数据
     * @param id
     * @return
     * @author  yangruidong
     */
    public AdministrationDict get(String id);

    /**
     * 分页查询给药途径字典列表
     * @return
     *  @author  yangruidong
     */
    public Page<AdministrationDict> findPage(Page<AdministrationDict> page, AdministrationDict administrationDict);

    /**
     * 保存或修改方法
     * @param administrationDict
     * @return
     *  @author  yangruidong
     */
    public String save(AdministrationDict administrationDict);

    /**
     * 删除方法
     * @param ids
     * @author  yangruidong
     */
    public String delete(String ids);

    /**
     * 根据试用返回获取用药途径 ，传入是门诊则获取门诊+全部
     * 传入的是住院 = 住院+ 全部
     * 传入的全部 = 门诊 + 住院+全部
     * @param inpOrOutpFlag 全部（综合）、门诊、住院
     * @return
     */
    public List<AdministrationDict> listAdministrationByInpOrOutpFlag(String inpOrOutpFlag) ;

}
