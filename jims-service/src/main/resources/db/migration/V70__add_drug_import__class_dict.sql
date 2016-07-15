
/*==============================================================*/
/* Table: DRUG_IMPORT_CLASS_DICT          入库类型字典表        */
/*==============================================================*/
-- Create table
create table DRUG_IMPORT_CLASS_DICT
(
  ID              VARCHAR2(64) not null,
  IMPORT_CLASS    VARCHAR2(8) not null,
  STATISTIC_CLASS VARCHAR2(16),
  STORAGE_TYPE    VARCHAR2(8),
  FROM_LEVEL            VARCHAR2(4),
  REMARKS         VARCHAR2(2000),
  UPDATE_BY       VARCHAR2(64),
  CREATE_BY       VARCHAR2(64),
  UPDATE_DATE     DATE,
  DEL_FLAG        VARCHAR2(100),
  CREATE_DATE     DATE,
  ACCOUNT_FLAG    VARCHAR2(2)
);
-- Add comments to the table
comment on table DRUG_IMPORT_CLASS_DICT
  is '入库分类字典表';
-- Add comments to the columns
comment on column DRUG_IMPORT_CLASS_DICT.ID
  is '主键';
comment on column DRUG_IMPORT_CLASS_DICT.IMPORT_CLASS
  is '入库分类';
comment on column DRUG_IMPORT_CLASS_DICT.STATISTIC_CLASS
  is '所属类别';
comment on column DRUG_IMPORT_CLASS_DICT.STORAGE_TYPE
  is '库存类型';
comment on column DRUG_IMPORT_CLASS_DICT.FROM_LEVEL
  is '来源(上级、平级、下级、供货商)';
comment on column DRUG_IMPORT_CLASS_DICT.ACCOUNT_FLAG
  is '0，不记账，1记账';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_IMPORT_CLASS_DICT
  add constraint DURG_IMPORT_CLASS_DICT_PK primary key (ID);
