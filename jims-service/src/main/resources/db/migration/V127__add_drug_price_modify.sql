
/*==============================================================*/
/* Table:DRUG_PRICE_MODIFY   调价记录表                          */
/* Table:DRUG_PRICE_MODIFY   调价记录表                          */
/* CREATE_DATE: 2016-05-14                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/
-- Create table
create table DRUG_PRICE_MODIFY
(
  DRUG_CODE             VARCHAR2(20) not null,
  DRUG_SPEC             VARCHAR2(20) not null,
  UNITS                 VARCHAR2(8),
  FIRM_ID               VARCHAR2(10) not null,
  MIN_SPEC              VARCHAR2(20),
  MIN_UNITS             VARCHAR2(8),
  ORIGINAL_TRADE_PRICE  NUMBER(10,4),
  CURRENT_TRADE_PRICE   NUMBER(10,4),
  ORIGINAL_RETAIL_PRICE NUMBER(10,4),
  CURRENT_RETAIL_PRICE  NUMBER(10,4),
  NOTICE_EFFICIENT_DATE DATE not null,
  ACTUAL_EFFICIENT_DATE DATE,
  MODIFY_CREDENTIAL     VARCHAR2(50),
  OPERATOR              VARCHAR2(64),
  CONFIRM_OPERATOR      VARCHAR2(64),
  ORG_ID                VARCHAR2(64),
  REMARKS               VARCHAR2(2000),
  UPDATE_BY             VARCHAR2(64),
  CREATE_BY             VARCHAR2(64),
  UPDATE_DATE           DATE,
  DEL_FLAG              VARCHAR2(100),
  CREATE_DATE           DATE,
  ID                    VARCHAR2(64) not null
);
-- Add comments to the table
comment on table DRUG_PRICE_MODIFY
  is '调价记录表';
-- Add comments to the columns
comment on column DRUG_PRICE_MODIFY.DRUG_CODE
  is '药品代码';
comment on column DRUG_PRICE_MODIFY.DRUG_SPEC
  is '包装规格';
comment on column DRUG_PRICE_MODIFY.UNITS
  is '包装单位';
comment on column DRUG_PRICE_MODIFY.FIRM_ID
  is '厂家标识';
comment on column DRUG_PRICE_MODIFY.MIN_SPEC
  is '最小规格';
comment on column DRUG_PRICE_MODIFY.MIN_UNITS
  is '最小单位';
comment on column DRUG_PRICE_MODIFY.ORIGINAL_TRADE_PRICE
  is '原市场批发价';
comment on column DRUG_PRICE_MODIFY.CURRENT_TRADE_PRICE
  is '现市场批发价';
comment on column DRUG_PRICE_MODIFY.ORIGINAL_RETAIL_PRICE
  is '原市场零售价';
comment on column DRUG_PRICE_MODIFY.CURRENT_RETAIL_PRICE
  is '现市场零售价';
comment on column DRUG_PRICE_MODIFY.NOTICE_EFFICIENT_DATE
  is '调价通知生效日期';
comment on column DRUG_PRICE_MODIFY.ACTUAL_EFFICIENT_DATE
  is '调价实际生效日期';
comment on column DRUG_PRICE_MODIFY.MODIFY_CREDENTIAL
  is '调价依据';
comment on column DRUG_PRICE_MODIFY.CONFIRM_OPERATOR
  is '确认人';
comment on column DRUG_PRICE_MODIFY.ORG_ID
  is '组织机构';
comment on column DRUG_PRICE_MODIFY.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_PRICE_MODIFY
  add constraint PK_DRUG_PRICE_MODIFY primary key (ID);
alter table DRUG_PRICE_MODIFY
  add constraint UK_DRUG_PRICE_MODIFY unique (DRUG_CODE, DRUG_SPEC, UNITS, FIRM_ID, ORG_ID);
