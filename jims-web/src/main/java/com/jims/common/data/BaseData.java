package com.jims.common.data;

import java.util.List;

/**
 *
 */
public class BaseData<T> {


    private T data;  //实体

    private List<T> datas;  //返回对象list

    private String code;   //返回状态

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

}
