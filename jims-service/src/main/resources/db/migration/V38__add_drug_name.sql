-- drop table DRUG_NAME_DICT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: DRUG_NAME_DICT      药品名称                                */
/*==============================================================*/
create table DRUG_NAME_DICT
(
  id            varchar2(64 CHAR),
  DRUG_CODE     VARCHAR2(20 CHAR) not null,
  DRUG_NAME     VARCHAR2(100 CHAR) not null,
  STD_INDICATOR NUMBER(1),
  INPUT_CODE    VARCHAR2(8 CHAR),
  INPUT_CODE_WB VARCHAR2(8 CHAR),
  CHEMICAL_NAME VARCHAR2(100 CHAR),
  CREATE_DATE      TIMESTAMP (6),
  CREATE_BY        VARCHAR2(64 CHAR),
  UPDATE_DATE      TIMESTAMP (6),
  UPDATE_BY        VARCHAR2(64 CHAR),
  REMARKS          VARCHAR2(200 CHAR),
  DEL_FLAG         NUMBER(1)
);
-- Add comments to the table 
comment on table DRUG_NAME_DICT
  is '药品名称字典';
-- Add comments to the columns 
comment on column DRUG_NAME_DICT.DRUG_CODE
  is '药品代码';
comment on column DRUG_NAME_DICT.DRUG_NAME
  is '药品名称';
comment on column DRUG_NAME_DICT.STD_INDICATOR
  is '正名标志';
comment on column DRUG_NAME_DICT.INPUT_CODE
  is '输入码';
comment on column DRUG_NAME_DICT.INPUT_CODE_WB
  is '五笔码';
  comment on column DRUG_NAME_DICT.CREATE_DATE
  is '创建日期';
comment on column DRUG_NAME_DICT.CREATE_BY
  is '创建人';
comment on column DRUG_NAME_DICT.UPDATE_DATE
  is '更新日期';
comment on column DRUG_NAME_DICT.UPDATE_BY
  is '更新人';
comment on column DRUG_NAME_DICT.REMARKS
  is '备注信息';
comment on column DRUG_NAME_DICT.DEL_FLAG
  is '删除标志';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DRUG_NAME_DICT
  add constraint PK_DRUG_NAME_DICT primary key (id);
-- Grant/Revoke object privileges 
/*grant select, insert, update, delete on DRUG_NAME_DICT to DBA;
grant select on DRUG_NAME_DICT to PUBLIC;*/
