drop table DRUG_PRICE_LIST cascade constraints;
-- Create table
/*==============================================================*/
/* Table: DRUG_PRICE_LIST      药品价格                                */
/*==============================================================*/
create table DRUG_PRICE_LIST
(
  id                 varchar2(64 CHAR),
  DRUG_CODE          VARCHAR2(20 CHAR) not null,
  DRUG_SPEC          VARCHAR2(20 CHAR) not null,
  FIRM_ID            VARCHAR2(10 CHAR) not null,
  UNITS              VARCHAR2(8 CHAR),
  TRADE_PRICE        NUMBER(10,4),
  RETAIL_PRICE       NUMBER(10,4),
  AMOUNT_PER_PACKAGE NUMBER(5),
  MIN_SPEC           VARCHAR2(20 CHAR),
  MIN_UNITS          VARCHAR2(8 CHAR),
  START_DATE         DATE not null,
  STOP_DATE          DATE,
  MEMOS              VARCHAR2(20 CHAR),
  CLASS_ON_INP_RCPT  VARCHAR2(1 CHAR),
  CLASS_ON_OUTP_RCPT VARCHAR2(1 CHAR),
  CLASS_ON_RECKONING VARCHAR2(4 CHAR),
  SUBJ_CODE          VARCHAR2(4 CHAR),
  CLASS_ON_MR        VARCHAR2(4 CHAR),
  HLIMIT_PRICE       NUMBER(10,4),
  PRICE_CLASS        VARCHAR2(4 CHAR),
  PASS_NO            VARCHAR2(30 CHAR),
  GMP                NUMBER(1),
  ORG_ID             VARCHAR2(64 CHAR),
  CREATE_DATE      TIMESTAMP (6),
  CREATE_BY        VARCHAR2(64 CHAR),
  UPDATE_DATE      TIMESTAMP (6),
  UPDATE_BY        VARCHAR2(64 CHAR),
  REMARKS          VARCHAR2(200 CHAR),
  DEL_FLAG         NUMBER(1)
);
-- Add comments to the table 
comment on table DRUG_PRICE_LIST
  is '药品价格';
-- Add comments to the columns 
comment on column DRUG_PRICE_LIST.DRUG_CODE
  is '药品代码';
comment on column DRUG_PRICE_LIST.DRUG_SPEC
  is '规格';
comment on column DRUG_PRICE_LIST.FIRM_ID
  is '厂家标识';
comment on column DRUG_PRICE_LIST.UNITS
  is '单位';
comment on column DRUG_PRICE_LIST.TRADE_PRICE
  is '市场批发价';
comment on column DRUG_PRICE_LIST.RETAIL_PRICE
  is '市场零售价';
comment on column DRUG_PRICE_LIST.AMOUNT_PER_PACKAGE
  is '包装数量';
comment on column DRUG_PRICE_LIST.MIN_SPEC
  is '最小单位规格';
comment on column DRUG_PRICE_LIST.MIN_UNITS
  is '最小单位';
comment on column DRUG_PRICE_LIST.START_DATE
  is '起用日期';
comment on column DRUG_PRICE_LIST.STOP_DATE
  is '停止日期';
comment on column DRUG_PRICE_LIST.MEMOS
  is '备注';
comment on column DRUG_PRICE_LIST.ORG_ID
  is '所属组织结构';
 comment on column DRUG_PRICE_LIST.CREATE_DATE
  is '创建日期';
comment on column DRUG_PRICE_LIST.CREATE_BY
  is '创建人';
comment on column DRUG_PRICE_LIST.UPDATE_DATE
  is '更新日期';
comment on column DRUG_PRICE_LIST.UPDATE_BY
  is '更新人';
comment on column DRUG_PRICE_LIST.REMARKS
  is '备注信息';
comment on column DRUG_PRICE_LIST.DEL_FLAG
  is '删除标志';  
-- Create/Recreate primary, unique and foreign key constraints 
alter table DRUG_PRICE_LIST
  add constraint PK_DRUG_PRICE_LIST primary key (id);
-- Create/Recreate indexes 
create index INX_DRUG_CODE on DRUG_PRICE_LIST (DRUG_CODE);
-- Grant/Revoke object privileges 
/*grant select, insert, update, delete on DRUG_PRICE_LIST to DBA;
grant select on DRUG_PRICE_LIST to PUBLIC;*/
