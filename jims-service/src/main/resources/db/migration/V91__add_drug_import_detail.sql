-- Create table
create table DRUG_IMPORT_DETAIL
(
  DOCUMENT_NO         VARCHAR2(10) not null,
  ITEM_NO             NUMBER(4) not null,
  DRUG_CODE           VARCHAR2(20),
  DRUG_SPEC           VARCHAR2(20),
  UNITS               VARCHAR2(8),
  BATCH_NO            VARCHAR2(16),
  EXPIRE_DATE         DATE,
  FIRM_ID             VARCHAR2(10),
  PURCHASE_PRICE      NUMBER(10,4),
  DISCOUNT            NUMBER(5,2),
  RETAIL_PRICE        NUMBER(10,4),
  PACKAGE_SPEC        VARCHAR2(20),
  QUANTITY            NUMBER(12,2),
  PACKAGE_UNITS       VARCHAR2(8),
  SUB_PACKAGE_1       NUMBER(12,2),
  SUB_PACKAGE_UNITS_1 VARCHAR2(8),
  SUB_PACKAGE_SPEC_1  VARCHAR2(20),
  SUB_PACKAGE_2       NUMBER(12,2),
  SUB_PACKAGE_UNITS_2 VARCHAR2(8),
  SUB_PACKAGE_SPEC_2  VARCHAR2(20),
  INVOICE_NO          VARCHAR2(10),
  INVOICE_DATE        DATE,
  TRADE_PRICE         NUMBER(10,4),
  INVENTORY           NUMBER(12,2),
  MEMO                VARCHAR2(20),
  ORDER_BATCH         VARCHAR2(4),
  VOUCHER_NO          VARCHAR2(9),
  TENDER_NO           NUMBER(4),
  INVOICE_SIGN        NUMBER(1) default 0,
  ORIGIN              VARCHAR2(50),
  PRODUCTION_DATE     DATE,
  PRODUCE_DATE        DATE,
  ID                  VARCHAR2(64) not null,
  ORG_ID              VARCHAR2(64),
  REMARKS             VARCHAR2(2000),
  UPDATE_BY           VARCHAR2(64),
  CREATE_BY           VARCHAR2(64),
  UPDATE_DATE         DATE,
  DEL_FLAG            VARCHAR2(100),
  CREATE_DATE         DATE
);
-- Add comments to the table
comment on table DRUG_IMPORT_DETAIL
  is '药品入库明细单据';
-- Add comments to the columns
comment on column DRUG_IMPORT_DETAIL.DOCUMENT_NO
  is '入库单号';
comment on column DRUG_IMPORT_DETAIL.ITEM_NO
  is '项目序号';
comment on column DRUG_IMPORT_DETAIL.DRUG_CODE
  is '药品代码';
comment on column DRUG_IMPORT_DETAIL.DRUG_SPEC
  is '规格';
comment on column DRUG_IMPORT_DETAIL.UNITS
  is '单位';
comment on column DRUG_IMPORT_DETAIL.BATCH_NO
  is '批号';
comment on column DRUG_IMPORT_DETAIL.EXPIRE_DATE
  is '有效期';
comment on column DRUG_IMPORT_DETAIL.FIRM_ID
  is '厂家标识';
comment on column DRUG_IMPORT_DETAIL.PURCHASE_PRICE
  is '进货价';
comment on column DRUG_IMPORT_DETAIL.DISCOUNT
  is '折扣';
comment on column DRUG_IMPORT_DETAIL.RETAIL_PRICE
  is '零售价';
comment on column DRUG_IMPORT_DETAIL.PACKAGE_SPEC
  is '包装规格';
comment on column DRUG_IMPORT_DETAIL.QUANTITY
  is '数量';
comment on column DRUG_IMPORT_DETAIL.PACKAGE_UNITS
  is '包装单位';
comment on column DRUG_IMPORT_DETAIL.SUB_PACKAGE_1
  is '内含包装1';
comment on column DRUG_IMPORT_DETAIL.SUB_PACKAGE_UNITS_1
  is '内含包装1单位';
comment on column DRUG_IMPORT_DETAIL.SUB_PACKAGE_SPEC_1
  is '内含包装1规格';
comment on column DRUG_IMPORT_DETAIL.SUB_PACKAGE_2
  is '内含包装2';
comment on column DRUG_IMPORT_DETAIL.SUB_PACKAGE_UNITS_2
  is '内含包装2单位';
comment on column DRUG_IMPORT_DETAIL.SUB_PACKAGE_SPEC_2
  is '内含包装2规格';
comment on column DRUG_IMPORT_DETAIL.INVOICE_NO
  is '发票号';
comment on column DRUG_IMPORT_DETAIL.INVOICE_DATE
  is '发票日期';
comment on column DRUG_IMPORT_DETAIL.TRADE_PRICE
  is '批发价';
comment on column DRUG_IMPORT_DETAIL.INVENTORY
  is '入库后库存数';
comment on column DRUG_IMPORT_DETAIL.MEMO
  is '备注';
comment on column DRUG_IMPORT_DETAIL.ORDER_BATCH
  is '招标批号';
comment on column DRUG_IMPORT_DETAIL.TENDER_NO
  is '中标序号';
comment on column DRUG_IMPORT_DETAIL.ORIGIN
  is '产地';
comment on column DRUG_IMPORT_DETAIL.PRODUCTION_DATE
  is '生产日期';
comment on column DRUG_IMPORT_DETAIL.ID
  is '主键';
comment on column DRUG_IMPORT_DETAIL.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_IMPORT_DETAIL
  add constraint DRUG_IMPORT_DETAIL_PK primary key (ID);
alter table DRUG_IMPORT_DETAIL
  add constraint DRUG_IMPORT_DETAIL_UK unique (DOCUMENT_NO, ITEM_NO,ORG_ID);
