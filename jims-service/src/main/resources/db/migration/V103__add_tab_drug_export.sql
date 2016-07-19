
/*==============================================================*/
/* Table: DRUG_EXPORT_MASTER    增加 药品出库主记录               */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/
-- Create table
create table DRUG_EXPORT_MASTER
(
  DOCUMENT_NO        VARCHAR2(10) not null,
  STORAGE            VARCHAR2(8),
  EXPORT_DATE        DATE,
  RECEIVER           VARCHAR2(40),
  ACCOUNT_RECEIVABLE NUMBER(10,2),
  ACCOUNT_PAYED      NUMBER(10,2),
  ADDITIONAL_FEE     NUMBER(8,2),
  EXPORT_CLASS       VARCHAR2(8),
  SUB_STORAGE        VARCHAR2(10),
  ACCOUNT_INDICATOR  NUMBER(1),
  MEMOS              VARCHAR2(20),
  OPERATOR           VARCHAR2(20),
  FLAG               NUMBER(1),
  ACCT_DATE          DATE,
  ACCT_OPERATOR      VARCHAR2(20),
  VOUCHER_NO         VARCHAR2(9),
  TALLY_DATE         DATE,
  TALLY_OPERATOR     VARCHAR2(20),
  SUB_RECEIVER       VARCHAR2(8),
  RECOVERY_DOCU_NO   VARCHAR2(10),
  ID                 VARCHAR2(64) not null,
  ORG_ID             VARCHAR2(64),
  IMPORT_MAN         VARCHAR2(20),
  IMPORT_DATE        DATE,
  IMPORT_DOCUMENT_NO VARCHAR2(10),
  REMARKS            VARCHAR2(2000),
  UPDATE_BY          VARCHAR2(64),
  CREATE_BY          VARCHAR2(64),
  UPDATE_DATE        DATE,
  DEL_FLAG           VARCHAR2(100),
  CREATE_DATE        DATE
);
comment on column DRUG_EXPORT_MASTER.IMPORT_MAN
  is '入库人';
comment on column DRUG_EXPORT_MASTER.IMPORT_DATE
  is '入库日期';
comment on column DRUG_EXPORT_MASTER.IMPORT_DOCUMENT_NO
  is '入库单据号';
-- Add comments to the table
comment on table DRUG_EXPORT_MASTER
  is '出库主记录';
-- Add comments to the columns
comment on column DRUG_EXPORT_MASTER.DOCUMENT_NO
  is '出库单号';
comment on column DRUG_EXPORT_MASTER.STORAGE
  is '库存管理单位';
comment on column DRUG_EXPORT_MASTER.EXPORT_DATE
  is '出库日期';
comment on column DRUG_EXPORT_MASTER.RECEIVER
  is '收货方';
comment on column DRUG_EXPORT_MASTER.ACCOUNT_RECEIVABLE
  is '应付款';
comment on column DRUG_EXPORT_MASTER.ACCOUNT_PAYED
  is '已付款';
comment on column DRUG_EXPORT_MASTER.ADDITIONAL_FEE
  is '附加费';
comment on column DRUG_EXPORT_MASTER.EXPORT_CLASS
  is '出库类别';
comment on column DRUG_EXPORT_MASTER.SUB_STORAGE
  is '存放库房';
comment on column DRUG_EXPORT_MASTER.ACCOUNT_INDICATOR
  is '记帐标志';
comment on column DRUG_EXPORT_MASTER.MEMOS
  is '备注';
comment on column DRUG_EXPORT_MASTER.OPERATOR
  is '录入者';
comment on column DRUG_EXPORT_MASTER.FLAG
  is '上账标志';
comment on column DRUG_EXPORT_MASTER.ACCT_DATE
  is '上账日期';
comment on column DRUG_EXPORT_MASTER.ACCT_OPERATOR
  is '上帐人';
comment on column DRUG_EXPORT_MASTER.VOUCHER_NO
  is '凭证号';
comment on column DRUG_EXPORT_MASTER.SUB_RECEIVER
  is '收货方子单位';
comment on column DRUG_EXPORT_MASTER.RECOVERY_DOCU_NO
  is '纠错出库的单据号';
comment on column DRUG_EXPORT_MASTER.IMPORT_MAN
  is '入库人';
comment on column DRUG_EXPORT_MASTER.IMPORT_DATE
  is '入库日期';
comment on column DRUG_EXPORT_MASTER.IMPORT_DOCUMENT_NO
  is '入库单据号';
comment on column DRUG_EXPORT_MASTER.ID
  is '主键';
comment on column DRUG_EXPORT_MASTER.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_EXPORT_MASTER
  add constraint DRUG_EXPORT_MASTER_PK primary key (ID);
alter table DRUG_EXPORT_MASTER
  add constraint DRUG_EXPORT_MASTER_UK unique (DOCUMENT_NO,ORG_ID);

/*==============================================================*/
/* Table: DRUG_EXPORT_DETAIL    增加 药品出库明细记录              */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/
-- Create table
create table DRUG_EXPORT_DETAIL
(
  DOCUMENT_NO         VARCHAR2(10) not null,
  ITEM_NO             NUMBER(4) not null,
  DRUG_CODE           VARCHAR2(20),
  DRUG_SPEC           VARCHAR2(20),
  UNITS               VARCHAR2(8),
  BATCH_NO            VARCHAR2(16),
  EXPIRE_DATE         DATE,
  FIRM_ID             VARCHAR2(10),
  IMPORT_DOCUMENT_NO  VARCHAR2(10),
  PURCHASE_PRICE      NUMBER(10,4),
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
  TRADE_PRICE         NUMBER(10,4),
  INVENTORY           NUMBER(12,2),
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
comment on table DRUG_EXPORT_DETAIL
  is '出库明细记录表';
-- Add comments to the columns
comment on column DRUG_EXPORT_DETAIL.DOCUMENT_NO
  is '出库单号';
comment on column DRUG_EXPORT_DETAIL.ITEM_NO
  is '项目序号';
comment on column DRUG_EXPORT_DETAIL.DRUG_CODE
  is '药品代码';
comment on column DRUG_EXPORT_DETAIL.DRUG_SPEC
  is '规格';
comment on column DRUG_EXPORT_DETAIL.UNITS
  is '单位';
comment on column DRUG_EXPORT_DETAIL.BATCH_NO
  is '批号';
comment on column DRUG_EXPORT_DETAIL.EXPIRE_DATE
  is '有效期';
comment on column DRUG_EXPORT_DETAIL.FIRM_ID
  is '厂家标识';
comment on column DRUG_EXPORT_DETAIL.IMPORT_DOCUMENT_NO
  is '相关入库单号';
comment on column DRUG_EXPORT_DETAIL.PURCHASE_PRICE
  is '出库价';
comment on column DRUG_EXPORT_DETAIL.RETAIL_PRICE
  is '零售价';
comment on column DRUG_EXPORT_DETAIL.PACKAGE_SPEC
  is '包装规格';
comment on column DRUG_EXPORT_DETAIL.QUANTITY
  is '数量';
comment on column DRUG_EXPORT_DETAIL.PACKAGE_UNITS
  is '包装单位';
comment on column DRUG_EXPORT_DETAIL.SUB_PACKAGE_1
  is '内含包装1';
comment on column DRUG_EXPORT_DETAIL.SUB_PACKAGE_UNITS_1
  is '内含包装1单位';
comment on column DRUG_EXPORT_DETAIL.SUB_PACKAGE_SPEC_1
  is '内含包装1规格';
comment on column DRUG_EXPORT_DETAIL.SUB_PACKAGE_2
  is '内含包装2';
comment on column DRUG_EXPORT_DETAIL.SUB_PACKAGE_UNITS_2
  is '内含包装2单位';
comment on column DRUG_EXPORT_DETAIL.SUB_PACKAGE_SPEC_2
  is '内含包装2规格';
comment on column DRUG_EXPORT_DETAIL.TRADE_PRICE
  is '批发价';
comment on column DRUG_EXPORT_DETAIL.INVENTORY
  is '出库后库存数';
comment on column DRUG_EXPORT_DETAIL.ID
  is '主键';
comment on column DRUG_EXPORT_DETAIL.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_EXPORT_DETAIL
  add constraint DRUG_EXPORT_DETAIL_PK primary key (ID);
alter table DRUG_EXPORT_DETAIL
  add constraint DRUG_EXPORT_DETAIL_UK unique (DOCUMENT_NO, ITEM_NO,ORG_ID);
