/*==============================================================*/
/* Table: DRUG_INFO    药品药理信息表                             */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/

-- Create table
create table DRUG_INFO
(
  DRUG_CODE        VARCHAR2(20) not null,
  DRUG_NAME        VARCHAR2(100),
  DRUG_E_NAME      VARCHAR2(40),
  ACTION           VARCHAR2(2000),
  INDICATION       VARCHAR2(2000),
  DOSAGE           VARCHAR2(2000),
  FORM             VARCHAR2(2000),
  PHARMACOKINETICS VARCHAR2(2000),
  ADVERSE_REACTION VARCHAR2(2000),
  ATTENTION        VARCHAR2(2000),
  CONTRAINDICATION VARCHAR2(2000),
  SKINTEST         CHAR(1) default 0,
  SKIN_TIME        NUMBER(3),
  ID               VARCHAR2(64) not null,
  REMARKS          VARCHAR2(2000),
  UPDATE_BY        VARCHAR2(64),
  CREATE_BY        VARCHAR2(64),
  UPDATE_DATE      DATE,
  DEL_FLAG         VARCHAR2(100),
  CREATE_DATE      DATE
);
-- Add comments to the table
comment on table DRUG_INFO
  is '药理信息表';
-- Add comments to the columns
comment on column DRUG_INFO.DRUG_CODE
  is '药品代码';
comment on column DRUG_INFO.DRUG_NAME
  is '药品名称';
comment on column DRUG_INFO.DRUG_E_NAME
  is '药品英文名称';
comment on column DRUG_INFO.ACTION
  is '药理作用';
comment on column DRUG_INFO.INDICATION
  is '适用症';
comment on column DRUG_INFO.DOSAGE
  is '用法用量';
comment on column DRUG_INFO.FORM
  is '制剂';
comment on column DRUG_INFO.PHARMACOKINETICS
  is '药代动力学';
comment on column DRUG_INFO.ADVERSE_REACTION
  is '不良反应';
comment on column DRUG_INFO.ATTENTION
  is '注意事项';
comment on column DRUG_INFO.CONTRAINDICATION
  is '禁忌';
comment on column DRUG_INFO.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_INFO
  add constraint DRUG_INFO_PK primary key (ID);
alter table DRUG_INFO
  add constraint DRUG_INFO_UK unique (DRUG_CODE);
