-- Create table
/*==============================================================*/
/* Table: CLINIC_ITEM_CLASS_DICT      药品类别字典表                                */
/*==============================================================*/
create table DRUG_CLASS_DICT
(
  ID          VARCHAR2(64) not null,
  CLASS_CODE  VARCHAR2(10) not null,
  CLASS_NAME  VARCHAR2(40),
  PARENT_ID   VARCHAR2(10),
  ORG_ID      VARCHAR2(64),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE
)
tablespace JIMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DRUG_CLASS_DICT
  is '类别字典表';
-- Add comments to the columns
comment on column DRUG_CLASS_DICT.ID
  is '主键';
comment on column DRUG_CLASS_DICT.CLASS_CODE
  is '类别代码';
comment on column DRUG_CLASS_DICT.CLASS_NAME
  is '类别名称';
comment on column DRUG_CLASS_DICT.PARENT_ID
  is '父ID';
comment on column DRUG_CLASS_DICT.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_CLASS_DICT
  add constraint DRUG_CLASS_DICT_PK primary key (ID)
  using index
  tablespace JIMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255;
