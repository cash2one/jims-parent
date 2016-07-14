/*==============================================================*/
/* Table: DRUG_EXPORT_CLASS_DICT          出库类型字典表        */
/*==============================================================*/
-- Create table
create table DRUG_EXPORT_CLASS_DICT
(
  ID              VARCHAR2(64) not null,
  EXPORT_CLASS    VARCHAR2(8) not null,
  STATISTIC_CLASS VARCHAR2(16),
  STORAGE_TYPE    VARCHAR2(8),
  TO_LEVEL    VARCHAR2(4),
  ACCOUNT_FLAG    NUMBER,
  REMARKS         VARCHAR2(2000),
  UPDATE_BY       VARCHAR2(64),
  CREATE_BY       VARCHAR2(64),
  UPDATE_DATE     DATE,
  DEL_FLAG        VARCHAR2(100),
  CREATE_DATE     DATE
);
-- Add comments to the table
comment on table DRUG_EXPORT_CLASS_DICT
  is '出库分类字典表';
-- Add comments to the columns
comment on column DRUG_EXPORT_CLASS_DICT.ID
  is '主键';
comment on column DRUG_EXPORT_CLASS_DICT.EXPORT_CLASS
  is '出库分类';
comment on column DRUG_EXPORT_CLASS_DICT.TO_LEVEL
  is '去向等级';
comment on column DRUG_EXPORT_CLASS_DICT.ACCOUNT_FLAG
  is '保存时是否记帐';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_EXPORT_CLASS_DICT
  add constraint DRUG_EXPORT_CLASS_DICT_PKI primary key (ID);
