package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;

import java.beans.Transient;
import java.util.Date;

/**
 * 入库明细记录
 * Created by heren on 2016/5/16.
 */
public class DrugImportDetail extends DataEntity<DrugImportDetail> {

    private static final long serialVersionUID = 1L;
    private String documentNo;		// 入库单号
    private Integer itemNo;		// 项目序号
    private String drugCode;		// 药品代码
    private String drugSpec;		// 规格
    private String units;		// 单位
    private String batchNo;		// 批号
    private Date expireDate;		// 有效期
    private String firmId;		// 厂家标识
    private Double purchasePrice;		// 进货价
    private Double discount;		// 折扣
    private Double retailPrice;		// 零售价
    private String packageSpec;		// 包装规格
    private Double quantity;		// 数量
    private String packageUnits;		// 包装单位
    private Double subPackage1;		// 内含包装1
    private String subPackageUnits1;		// 内含包装1单位
    private String subPackageSpec1;		// 内含包装1规格
    private Double subPackage2;		// 内含包装2
    private String subPackageUnits2;		// 内含包装2单位
    private String subPackageSpec2;		// 内含包装2规格
    private String invoiceNo;		// 发票号
    private Date invoiceDate;		// 发票日期
    private Double tradePrice;		// 批发价
    private Double inventory;		// 入库后库存数
    private String memo;		// 备注
    private String orderBatch;		// 招标批号
    private String voucherNo;		// voucher_no
    private Integer tenderNo;		// 中标序号
    private Integer invoiceSign;		// invoice_sign
    private String origin;		// 产地
    private Date productionDate;		// 生产日期
    private Date produceDate;		// produce_date
    private String orgId;		// 所属结构
    private String priceListId;  //对应价格主键

    @Transient
    public String getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(String priceListId) {
        this.priceListId = priceListId;
    }

    public DrugImportDetail() {
        super();
    }

    public DrugImportDetail(String id){
        super(id);
    }


    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }


    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }


    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }


    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }


    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }


    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }


    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }


    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }


    public String getPackageSpec() {
        return packageSpec;
    }

    public void setPackageSpec(String packageSpec) {
        this.packageSpec = packageSpec;
    }


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


    public String getPackageUnits() {
        return packageUnits;
    }

    public void setPackageUnits(String packageUnits) {
        this.packageUnits = packageUnits;
    }


    public Double getSubPackage1() {
        return subPackage1;
    }

    public void setSubPackage1(Double subPackage1) {
        this.subPackage1 = subPackage1;
    }


    public String getSubPackageUnits1() {
        return subPackageUnits1;
    }

    public void setSubPackageUnits1(String subPackageUnits1) {
        this.subPackageUnits1 = subPackageUnits1;
    }


    public String getSubPackageSpec1() {
        return subPackageSpec1;
    }

    public void setSubPackageSpec1(String subPackageSpec1) {
        this.subPackageSpec1 = subPackageSpec1;
    }


    public Double getSubPackage2() {
        return subPackage2;
    }

    public void setSubPackage2(Double subPackage2) {
        this.subPackage2 = subPackage2;
    }


    public String getSubPackageUnits2() {
        return subPackageUnits2;
    }

    public void setSubPackageUnits2(String subPackageUnits2) {
        this.subPackageUnits2 = subPackageUnits2;
    }


    public String getSubPackageSpec2() {
        return subPackageSpec2;
    }

    public void setSubPackageSpec2(String subPackageSpec2) {
        this.subPackageSpec2 = subPackageSpec2;
    }


    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }


    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }


    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }


    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public String getOrderBatch() {
        return orderBatch;
    }

    public void setOrderBatch(String orderBatch) {
        this.orderBatch = orderBatch;
    }


    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }


    public Integer getTenderNo() {
        return tenderNo;
    }

    public void setTenderNo(Integer tenderNo) {
        this.tenderNo = tenderNo;
    }


    public Integer getInvoiceSign() {
        return invoiceSign;
    }

    public void setInvoiceSign(Integer invoiceSign) {
        this.invoiceSign = invoiceSign;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }


    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
