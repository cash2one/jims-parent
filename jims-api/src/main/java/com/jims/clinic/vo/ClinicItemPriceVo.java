package com.jims.clinic.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 检查项目与对应价表Vo
 *
 * @author tangxb
 * @version 2016-05-04
 */
@XmlRootElement
public class ClinicItemPriceVo<T> implements Serializable {
    private String itemClass;//项目分类
    private String itemName;//项目名称
    private String itemCode;//项目代码
    private String inputCode;//拼音码
    private Double sumPrice;//价格合计

    public ClinicItemPriceVo() {
    }

    public ClinicItemPriceVo(String itemClass, String itemName, String itemCode, String inputCode, Double sumPrice) {
        this.itemClass = itemClass;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.inputCode = inputCode;
        this.sumPrice = sumPrice;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }
};

