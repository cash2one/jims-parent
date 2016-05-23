-- Create table
create table DRUG_SUB_STORAGE_DEPT
(
  STORAGE_CODE     VARCHAR2(8) not null,
  SUB_STORAGE      VARCHAR2(30) not null,
  IMPORT_NO_PREFIX VARCHAR2(6),
  IMPORT_NO_AVA    NUMBER(7),
  EXPORT_NO_PREFIX VARCHAR2(6),
  EXPORT_NO_AVA    NUMBER(7),
  SUB_STORAGE_CODE VARCHAR2(8) not null,
  INPUT_CODE       VARCHAR2(8),
  ID               VARCHAR2(64) not null,
  ORG_ID           VARCHAR2(64),
  REMARKS          VARCHAR2(2000),
  UPDATE_BY        VARCHAR2(64),
  CREATE_BY        VARCHAR2(64),
  UPDATE_DATE      DATE,
  DEL_FLAG         VARCHAR2(100),
  CREATE_DATE      DATE
);
-- Add comments to the table
comment on table DRUG_SUB_STORAGE_DEPT
  is '库存子单位';
-- Add comments to the columns
comment on column DRUG_SUB_STORAGE_DEPT.STORAGE_CODE
  is '库存单位代码';
comment on column DRUG_SUB_STORAGE_DEPT.SUB_STORAGE
  is '子库房';
comment on column DRUG_SUB_STORAGE_DEPT.IMPORT_NO_PREFIX
  is '入库单号前缀';
comment on column DRUG_SUB_STORAGE_DEPT.IMPORT_NO_AVA
  is '入库单可用流水号';
comment on column DRUG_SUB_STORAGE_DEPT.EXPORT_NO_PREFIX
  is '出库单号前缀';
comment on column DRUG_SUB_STORAGE_DEPT.EXPORT_NO_AVA
  is '出库单可用流水号';
comment on column DRUG_SUB_STORAGE_DEPT.SUB_STORAGE_CODE
  is '子库房代码';
comment on column DRUG_SUB_STORAGE_DEPT.INPUT_CODE
  is '拼音码';
comment on column DRUG_SUB_STORAGE_DEPT.ID
  is '主键';
comment on column DRUG_SUB_STORAGE_DEPT.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_SUB_STORAGE_DEPT
  add constraint DRUG_SUB_STORAGE_DEPT_PK primary key (ID);
alter table DRUG_SUB_STORAGE_DEPT
  add constraint DRUG_SUB_STORAGE_DEPT_UK unique (SUB_STORAGE, ORG_ID, SUB_STORAGE_CODE);
