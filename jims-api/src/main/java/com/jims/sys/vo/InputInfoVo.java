package com.jims.sys.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/5/23.
 */
public class InputInfoVo implements Serializable {
    private String orgId ;//组织机构
    private String dictType ;//表或者视图名称
    private String itemClass;//如果是药品，用于区分中西药

    private List<InputParamVo> inputParamVos = new ArrayList<InputParamVo>() ;

    public InputInfoVo() {
    }

    public InputInfoVo(String orgId, String dictType, String itemClass,List<InputParamVo> inputParamVos) {
        this.orgId = orgId;
        this.dictType = dictType;
        this.itemClass = itemClass;
        this.inputParamVos = inputParamVos;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public List<InputParamVo> getInputParamVos() {
        return inputParamVos;
    }

    public void setInputParamVos(List<InputParamVo> inputParamVos) {
        this.inputParamVos = inputParamVos;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }
}
