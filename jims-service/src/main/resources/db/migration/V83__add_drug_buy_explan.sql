
/*==============================================================*/
/* Table: DRUG_BUY_PLAN    药品采购计划表                 */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
-- Create table
create table DRUG_BUY_PLAN
(
  BUY_ID             VARCHAR2(12) not null,
  BUY_NO             NUMBER(4) not null,
  DRUG_CODE          VARCHAR2(20),
  DRUG_NAME          VARCHAR2(100),
  DRUG_SPEC          VARCHAR2(20),
  UNITS              VARCHAR2(8),
  DRUG_FORM          VARCHAR2(10),
  TOXI_PROPERTY      VARCHAR2(10),
  DOSE_PER_UNIT      NUMBER(8,3),
  DOSE_UNITS         VARCHAR2(8),
  DRUG_INDICATOR     NUMBER(1),
  INPUT_CODE         VARCHAR2(8),
  WANT_NUMBER        NUMBER(12,2),
  STORER             VARCHAR2(20),
  STOCK_NUMBER       NUMBER(12,2),
  STOCK_SUPPLIER     VARCHAR2(10),
  BUYER              VARCHAR2(20),
  CHECK_NUMBER       NUMBER(12,2),
  CHECK_SUPPLIER     VARCHAR2(10),
  CHECKER            VARCHAR2(20),
  FLAG               NUMBER(2),
  PACK_SPEC          VARCHAR2(20),
  PACK_UNIT          VARCHAR2(8),
  FIRM_ID            VARCHAR2(64),
  PURCHASE_PRICE     NUMBER(10,4),
  STORAGE            VARCHAR2(8) not null,
  STOCKQUANTITY_REF  NUMBER(12,2),
  EXPORTQUANTITY_REF NUMBER(12,2),
  EXECUTED_NUMBER    NUMBER(12,2),
  IMPORT_DOCUMENT    VARCHAR2(50),
  EXECUTED_DATE      DATE,
  ID                 VARCHAR2(64) not null,
  ORG_ID             VARCHAR2(64),
  REMARKS            VARCHAR2(2000),
  UPDATE_BY          VARCHAR2(64),
  CREATE_BY          VARCHAR2(64),
  UPDATE_DATE        DATE,
  DEL_FLAG           VARCHAR2(100),
  CREATE_DATE        DATE
)
tablespace JIMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DRUG_BUY_PLAN
  is '药品采购计划表';
-- Add comments to the columns
comment on column DRUG_BUY_PLAN.BUY_ID
  is '采购单据号,当前日期201605110001';
comment on column DRUG_BUY_PLAN.BUY_NO
  is '采购单序号';
comment on column DRUG_BUY_PLAN.DRUG_CODE
  is '药品代码';
comment on column DRUG_BUY_PLAN.DRUG_NAME
  is '药品名称';
comment on column DRUG_BUY_PLAN.DRUG_SPEC
  is '规格';
comment on column DRUG_BUY_PLAN.UNITS
  is '单位';
comment on column DRUG_BUY_PLAN.DRUG_FORM
  is '剂型';
comment on column DRUG_BUY_PLAN.TOXI_PROPERTY
  is '毒理分类';
comment on column DRUG_BUY_PLAN.DOSE_PER_UNIT
  is '最小单位剂量';
comment on column DRUG_BUY_PLAN.DOSE_UNITS
  is '剂量单位';
comment on column DRUG_BUY_PLAN.DRUG_INDICATOR
  is '药品类别标志';
comment on column DRUG_BUY_PLAN.INPUT_CODE
  is '输入码';
comment on column DRUG_BUY_PLAN.WANT_NUMBER
  is '计划数量';
comment on column DRUG_BUY_PLAN.STORER
  is '仓管员';
comment on column DRUG_BUY_PLAN.STOCK_NUMBER
  is '采购数量';
comment on column DRUG_BUY_PLAN.STOCK_SUPPLIER
  is '采购供应商';
comment on column DRUG_BUY_PLAN.BUYER
  is '采购员';
comment on column DRUG_BUY_PLAN.CHECK_NUMBER
  is '审核数量';
comment on column DRUG_BUY_PLAN.CHECK_SUPPLIER
  is '审核供应商';
comment on column DRUG_BUY_PLAN.CHECKER
  is '审核员';
comment on column DRUG_BUY_PLAN.FLAG
  is '执行标志';
comment on column DRUG_BUY_PLAN.PACK_SPEC
  is '包装规格';
comment on column DRUG_BUY_PLAN.PACK_UNIT
  is '包装单位';
comment on column DRUG_BUY_PLAN.FIRM_ID
  is '厂商';
comment on column DRUG_BUY_PLAN.PURCHASE_PRICE
  is '进货价';
comment on column DRUG_BUY_PLAN.STORAGE
  is '库存管理单位';
comment on column DRUG_BUY_PLAN.EXECUTED_NUMBER
  is '执行数量';
comment on column DRUG_BUY_PLAN.IMPORT_DOCUMENT
  is '入库单号';
comment on column DRUG_BUY_PLAN.EXECUTED_DATE
  is '执行日期';
comment on column DRUG_BUY_PLAN.ID
  is '主键';
comment on column DRUG_BUY_PLAN.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_BUY_PLAN
  add constraint DRUG_BUY_PLAN_PK primary key (ID)
  using index
  tablespace JIMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255;
alter table DRUG_BUY_PLAN
  add constraint DRUG_BUY_PLAN_UK unique (BUY_ID, BUY_NO)
  using index
  tablespace JIMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255;
