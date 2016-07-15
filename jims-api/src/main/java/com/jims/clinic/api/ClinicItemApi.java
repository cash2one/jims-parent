package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.persistence.Page;
import com.jims.sys.vo.PriceDictListVo;

import java.util.List;

/**
 * Created by lgx on 2016/4/28.
 */
public interface ClinicItemApi {

    /**
     * 获取单条临床诊疗项目数据
     * @param id
     * @return
     */
    public ClinicItemDict get(String id);

    /**
     * 获取临床诊疗项目列表数据
     * @param entity
     * @return
     */
    public List<ClinicItemDict> findList(ClinicItemDict entity);

    /**
     *  查询诊疗项目列表通过组织机构id
     * @param orgId 组织机构id
     * @return
     * @author txb
     */
    public List<ClinicItemDict> itemListByOrgId(String orgId);

    /**
     * 编码或名称已存在个数
     * @return
     */
    public boolean codeOrNameHas(ClinicItemDict entity);

    /**
     * 查询临床诊疗项目分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<ClinicItemDict> findPage(Page<ClinicItemDict> page, ClinicItemDict entity);

    /**
     * 保存临床诊疗项目数据（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(ClinicItemDict entity) ;

    /**
     * 批量保存临床诊疗项目数据（插入或更新）
     * @param entityList
     * @return 0 失败，1成功
     */
    public String save(List<ClinicItemDict> entityList);

    /**
     * 删除数据
     * @param ids,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    public String delete(String ids) ;

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param ids,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    public String deleteCascade(String ids);

    /**
     * 删除诊疗项目数据以及所属名称和价表对照
     * @param entity
     * @return 0 失败，1成功
     */
    public String deleteCascade(ClinicItemDict entity);

    /**
     * 获取临床诊疗项目名称（正/别名）信息
     * @param entity
     * @return
     */
    public List<ClinicItemNameDict> findNameList(ClinicItemNameDict entity);

    /**
     * 批量保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entityList
     * @return 0 失败，1成功
     */
    public String saveNameList(List<ClinicItemNameDict> entityList);

    /**
     * 保存临床诊疗项目名称(正/别名)数据（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(ClinicItemNameDict entity);

    /**
     * 删除临床诊疗项目名称(正/别名)数据
     * @param ids ,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    public String deleteName(String ids);

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     * @return 0 失败，1成功
     */
    public String delete(ClinicItemNameDict entity);

    /**
     * 删除临床诊疗项目所有名称(正/别名)数据
     * @param entity
     * @return 0 失败，1成功
     */
    public String deleteName(ClinicItemDict entity);

    /**
     * 获取临床诊疗与价表对照信息
     * @param entity
     * @return
     */
    public List<ClinicVsCharge> findVsList(ClinicVsCharge entity);

    /**
     * 保存临床诊疗与价表对照数据（插入或更新）
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(ClinicVsCharge entity);

    /**
     * 批量保存临床诊疗与价表对照数据（插入或更新）
     * @param entityList
     * @return 0 失败，1成功
     */
    public String saveVsList(List<ClinicVsCharge> entityList);

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     * @return 0 失败，1成功
     */
    public String delete(ClinicVsCharge entity);

    /**
     * 删除临床诊疗与价表对照数据
     * @param ids,多个id以逗号隔开
     * @return 0 失败，1成功
     */
    public String deleteVs(String ids);

    /**
     * 删除临床诊疗与价表对照数据
     * @param entity
     * @return 0 失败，1成功
     */
    public String deleteVs(ClinicItemDict entity);

    /**
     * 根据组织机构ID和诊疗项目类别获取诊疗项目名称
     * @param orgId 组织机构Id
     * @param clinicClass 诊疗项目名称
     * @author ztq
     * @return
     */
    public List<ClinicItemDict> findListByOrgIdAndClinicClass(String orgId, String clinicClass);

    /**
     * 批量处理（添加、修改、删除）诊疗项目、正别名以及对照
     * @param list ClinicItemDict对象序列
     *          如果ClinicItemDict对象delFlag为1，该对象为删除数据参数。
     *                      该对象的Id为需要删除的数据的Id(也有可能是多个Id以‘ , ’拼接的ID字符串)
     *          其余为诊疗项目保存的数据(ClinicItemDict数据没有itemCode则只处理正别名、对照)，
     *                      saveNameList为正别名数据
     *                      saveVsList  为对照数据
     *
     * @return 0 失败，1成功
     */
    public String saveBatch(List<ClinicItemDict> list);
}
