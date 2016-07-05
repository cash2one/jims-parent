package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 *
 * 系统服务参数
 * Created by heren on 2016/7/4.
 */
public class SysServiceParam extends DataEntity<SysServiceParam>{

    private String paramName ;//参数名称
    private String paramValue ;//参数值
    private String serviceId;//所属服务
    private String valueRange ;//值域
    private String paramDesp ;//参数描述
    private String paramType ;//参数类型

    public SysServiceParam(String paramName, String paramValue, String serviceId, String valueRange, String paramDesp, String paramType) {
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.serviceId = serviceId;
        this.valueRange = valueRange;
        this.paramDesp = paramDesp;
        this.paramType = paramType;
    }





    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    public String getParamDesp() {
        return paramDesp;
    }

    public void setParamDesp(String paramDesp) {
        this.paramDesp = paramDesp;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}
