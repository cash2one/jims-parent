drop table DRUG_STORAGE_DEPT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: DRUG_STORAGE_DEPT      药品库存单位字典                                */
/*==============================================================*/
create table DRUG_STORAGE_DEPT
(
  id                 varchar2(64 CHAR),
  STORAGE_CODE       VARCHAR2(8 CHAR) not null,
  STORAGE_NAME       VARCHAR2(20 CHAR),
  STORAGE_TYPE       VARCHAR2(8 CHAR),
  DISBURSE_NO_PREFIX VARCHAR2(6 CHAR),
  DISBURSE_NO_AVA    NUMBER(4),
  EXPORT_NO_AVA      NUMBER(4),
  EXPORT_NO_PREFIX   VARCHAR2(6 CHAR),
  IMPORT_NO_AVA      NUMBER(4),
  IMPORT_NO_PREFIX   VARCHAR2(6 CHAR),
  STORAGE_FLAG       VARCHAR2(2 CHAR),
  YG_FLAG            VARCHAR2(2 CHAR),
  WARD_CODE          VARCHAR2(10 CHAR),
  ORG_ID             VARCHAR2(64 CHAR),
  CREATE_DATE      TIMESTAMP (6),
  CREATE_BY        VARCHAR2(64 CHAR),
  UPDATE_DATE      TIMESTAMP (6),
  UPDATE_BY        VARCHAR2(64 CHAR),
  REMARKS          VARCHAR2(200 CHAR),
  DEL_FLAG         NUMBER(1)
);
-- Add comments to the table 
comment on table DRUG_STORAGE_DEPT
  is '药品库存单位字典';
-- Add comments to the columns 
comment on column DRUG_STORAGE_DEPT.STORAGE_CODE
  is '单位代码';
comment on column DRUG_STORAGE_DEPT.STORAGE_NAME
  is '单位名称';
comment on column DRUG_STORAGE_DEPT.STORAGE_TYPE
  is '单位性质';
comment on column DRUG_STORAGE_DEPT.DISBURSE_NO_PREFIX
  is '付款单前缀';
comment on column DRUG_STORAGE_DEPT.DISBURSE_NO_AVA
  is '当前付款单号';
comment on column DRUG_STORAGE_DEPT.EXPORT_NO_AVA
  is '出库单可用流水号';
comment on column DRUG_STORAGE_DEPT.EXPORT_NO_PREFIX
  is '出库单号前缀';
comment on column DRUG_STORAGE_DEPT.IMPORT_NO_AVA
  is '入库单可用流水号';
comment on column DRUG_STORAGE_DEPT.IMPORT_NO_PREFIX
  is '入库单号前缀';
comment on column DRUG_STORAGE_DEPT.STORAGE_FLAG
  is '标志';
comment on column DRUG_STORAGE_DEPT.YG_FLAG
  is '是否纳入药柜范畴';
comment on column DRUG_STORAGE_DEPT.WARD_CODE
  is '对应药柜护理单元';
comment on column DRUG_STORAGE_DEPT.ORG_ID
  is '结构Id';
comment on column DRUG_STORAGE_DEPT.CREATE_DATE
  is '创建日期';
comment on column DRUG_STORAGE_DEPT.CREATE_BY
  is '创建人';
comment on column DRUG_STORAGE_DEPT.UPDATE_DATE
  is '更新日期';
comment on column DRUG_STORAGE_DEPT.UPDATE_BY
  is '更新人';
comment on column DRUG_STORAGE_DEPT.REMARKS
  is '备注信息';
comment on column DRUG_STORAGE_DEPT.DEL_FLAG
  is '删除标志';  
-- Create/Recreate primary, unique and foreign key constraints 
alter table DRUG_STORAGE_DEPT
  add constraint PK_DRUG_STORAGE_DEPT primary key (id);
