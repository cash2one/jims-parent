package com.jims.phstock.api;

import com.jims.common.persistence.Page;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.vo.DrugCatalogBeanVo;

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
     * @return 0 失败，1 成功
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
     * 根据药品名称或药品代码查询数据
     * @return
     * @author fengyuguang
     */
    public List<DrugDict> getByName(String drugCode,String drugName);

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
     * @return 0 失败，1 成功
     * @author zhaoning
     * @version 2016-04-23
     */
    public String delete(String ids);


    /**
     * 根据商品亚类 药品剂型,序号长度生成药品代码drug_code
     * @param secondType
     * @param drugForm
     * @param numLength
     * @return
     * @author ztq
     *
     */
    public String getDrugCodeByRule(String secondType,String drugForm,String numLength);

    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    public List<DrugDict> listDrugDictByDrugCode(String drugCode);

    /**
     * 药品目录保存
     * @param drugCatalogBeanVo 药品目录实体vo
     * @return 0 失败，1 成功
     * @author txb
     */
    public String saveDrugCatalog(DrugCatalogBeanVo drugCatalogBeanVo);


}
