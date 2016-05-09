package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicItemNameDict;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 * 诊疗项目名称表Api接口
 * @author xueyx
 * @version 2016-05-04
 */
public interface ClinicItemNameDictServiceApi {

    /**
     * 查询检验项目
     * @param标本expand1
     * @param检验类别expand2
     * @param科室expand3
     * @author xueyx
     * @version 2016/5/06
     */
    public List<ClinicItemNameDict> selectLabItem(ClinicItemNameDict clinicItemNameDict);
}
