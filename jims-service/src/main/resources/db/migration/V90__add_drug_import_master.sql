/*==============================================================*/
/* Table: DRUG_IMPORT_MASTER        药品入库主记录 ztq            */
/*==============================================================*/
-- Create table
create table DRUG_IMPORT_MASTER
(
  DOCUMENT_NO        VARCHAR2(10) not null,
  STORAGE            VARCHAR2(8),
  IMPORT_DATE        DATE,
  SUPPLIER           VARCHAR2(40),
  ACCOUNT_RECEIVABLE NUMBER(10,2),
  ACCOUNT_PAYED      NUMBER(10,2),
  ADDITIONAL_FEE     NUMBER(8,2),
  IMPORT_CLASS       VARCHAR2(8),
  SUB_STORAGE        VARCHAR2(8),
  ACCOUNT_INDICATOR  NUMBER(1),
  MEMOS              VARCHAR2(20),
  OPERATOR           VARCHAR2(20),
  FLAG               NUMBER(1),
  ACCT_DATE          DATE,
  ACCT_OPERATOR      VARCHAR2(20),
  VOUCHER_NO         VARCHAR2(9),
  TALLY_DATE         DATE,
  TALLY_OPERATOR     VARCHAR2(20),
  SUB_SUPPLIER       VARCHAR2(8),
  RECOVERY_DOCU_NO   VARCHAR2(10),
  ID                 VARCHAR2(64) not null,
  ORG_ID             VARCHAR2(64),
  REMARKS            VARCHAR2(2000),
  UPDATE_BY          VARCHAR2(64),
  CREATE_BY          VARCHAR2(64),
  UPDATE_DATE        DATE,
  DEL_FLAG           VARCHAR2(100),
  CREATE_DATE        DATE
);
-- Add comments to the table
comment on table DRUG_IMPORT_MASTER
  is '入库记录单';
-- Add comments to the columns
comment on column DRUG_IMPORT_MASTER.DOCUMENT_NO
  is '入库单号';
comment on column DRUG_IMPORT_MASTER.STORAGE
  is '库存管理单位';
comment on column DRUG_IMPORT_MASTER.IMPORT_DATE
  is '入库日期';
comment on column DRUG_IMPORT_MASTER.SUPPLIER
  is '供货方';
comment on column DRUG_IMPORT_MASTER.ACCOUNT_RECEIVABLE
  is '应付款';
comment on column DRUG_IMPORT_MASTER.ACCOUNT_PAYED
  is '已付款';
comment on column DRUG_IMPORT_MASTER.ADDITIONAL_FEE
  is '附加费';
comment on column DRUG_IMPORT_MASTER.IMPORT_CLASS
  is '入库类别';
comment on column DRUG_IMPORT_MASTER.SUB_STORAGE
  is '存放库房';
comment on column DRUG_IMPORT_MASTER.ACCOUNT_INDICATOR
  is '记帐标志';
comment on column DRUG_IMPORT_MASTER.MEMOS
  is '备注';
comment on column DRUG_IMPORT_MASTER.OPERATOR
  is '录入者';
comment on column DRUG_IMPORT_MASTER.FLAG
  is '上账标志';
comment on column DRUG_IMPORT_MASTER.ACCT_DATE
  is '上账日期 ';
comment on column DRUG_IMPORT_MASTER.ACCT_OPERATOR
  is '上帐人';
comment on column DRUG_IMPORT_MASTER.VOUCHER_NO
  is '凭证号';
comment on column DRUG_IMPORT_MASTER.TALLY_DATE
  is '上帐日期';
comment on column DRUG_IMPORT_MASTER.RECOVERY_DOCU_NO
  is '纠错入库的单据号';
comment on column DRUG_IMPORT_MASTER.ID
  is '主键';
comment on column DRUG_IMPORT_MASTER.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_IMPORT_MASTER
  add constraint DRUG_IMPORT_MASTER_PK primary key (ID);
alter table DRUG_IMPORT_MASTER
  add constraint DRUG_IMPORT_MASTER_UK unique (DOCUMENT_NO,ORG_ID);
