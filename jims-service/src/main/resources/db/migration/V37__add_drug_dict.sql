-- drop table DRUG_DICT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: DRUG_DICT
    修改 添加唯一键约束  药品字典表                                */
/*==============================================================*/
create table DRUG_DICT
(
  id		           varchar2(64 CHAR),
  DRUG_CODE        VARCHAR2(20 CHAR) not null,
  DRUG_NAME        VARCHAR2(100 CHAR),
  DRUG_SPEC        VARCHAR2(20 CHAR) not null,
  UNITS            VARCHAR2(8 CHAR),
  DRUG_FORM        VARCHAR2(10 CHAR),
  TOXI_PROPERTY    VARCHAR2(10 CHAR),
  DOSE_PER_UNIT    NUMBER(8,3),
  DOSE_UNITS       VARCHAR2(8),
  DRUG_INDICATOR   NUMBER(1) not null,
  INPUT_CODE       VARCHAR2(8 CHAR),
  OTC              VARCHAR2(10 CHAR),
  LIMIT_CLASS      VARCHAR2(4 CHAR),
  STOP_FLAG        NUMBER(1),
  ENTERED_DATETIME DATE default sysdate,
  PRECIOUS_FLAG    VARCHAR2(4 CHAR),
  CREATE_DATE      TIMESTAMP (6),
  CREATE_BY        VARCHAR2(64 CHAR),
  UPDATE_DATE      TIMESTAMP (6),
  UPDATE_BY        VARCHAR2(64 CHAR),
  REMARKS          VARCHAR2(200 CHAR),
  DEL_FLAG         NUMBER(1)
);
-- Add comments to the table 
comment on table DRUG_DICT
  is '药品字典';
-- Add comments to the columns 
comment on column DRUG_DICT.DRUG_CODE
  is '药品代码';
comment on column DRUG_DICT.DRUG_NAME
  is '药品名称';
comment on column DRUG_DICT.DRUG_SPEC
  is '规格';
comment on column DRUG_DICT.UNITS
  is '单位';
comment on column DRUG_DICT.DRUG_FORM
  is '剂型';
comment on column DRUG_DICT.TOXI_PROPERTY
  is '毒理分类';
comment on column DRUG_DICT.DOSE_PER_UNIT
  is '最小单位剂量';
comment on column DRUG_DICT.DOSE_UNITS
  is '剂量单位';
comment on column DRUG_DICT.DRUG_INDICATOR
  is '药品类别标志[1西药，2中草药，3中成药，5辅料，6试剂，8材料，9其他]';
comment on column DRUG_DICT.INPUT_CODE
  is '输入码';
comment on column DRUG_DICT.ENTERED_DATETIME
  is '录入日期';
comment on column DRUG_DICT.PRECIOUS_FLAG
  is '贵重药品等级标示';
comment on column DRUG_DICT.CREATE_DATE
  is '创建日期';
comment on column DRUG_DICT.CREATE_BY
  is '创建人';
comment on column DRUG_DICT.UPDATE_DATE
  is '更新日期';
comment on column DRUG_DICT.UPDATE_BY
  is '更新人';
comment on column DRUG_DICT.REMARKS
  is '备注信息';
comment on column DRUG_DICT.DEL_FLAG
  is '删除标志';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DRUG_DICT
  add constraint PK_DRUG_DICT primary key (id) ;
-- Grant/Revoke object privileges 
/*grant select, insert, update, delete on DRUG_DICT to DBA;
grant select on DRUG_DICT to PUBLIC;*/
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_DICT
  add constraint PK_DRUG_DICT_UK unique (DRUG_CODE, DRUG_SPEC, DRUG_INDICATOR ,units);

