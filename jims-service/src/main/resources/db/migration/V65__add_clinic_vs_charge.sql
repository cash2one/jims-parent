-- Create table
create table CLINIC_VS_CHARGE
(
  ID                VARCHAR2(64) not null,
  CLINIC_ITEM_CLASS VARCHAR2(1) not null,
  CLINIC_ITEM_CODE  VARCHAR2(20) not null,
  CHARGE_ITEM_NO    NUMBER(2) not null,
  CHARGE_ITEM_CLASS VARCHAR2(1),
  CHARGE_ITEM_CODE  VARCHAR2(20),
  CHARGE_ITEM_SPEC  VARCHAR2(50),
  AMOUNT            NUMBER(4),
  UNITS             VARCHAR2(8),
  BACKBILL_RULE     VARCHAR2(10),
  ORG_ID            VARCHAR2(64),
  REMARKS           VARCHAR2(2000),
  UPDATE_BY         VARCHAR2(64),
  CREATE_BY         VARCHAR2(64),
  UPDATE_DATE       TIMESTAMP(6),
  DEL_FLAG          VARCHAR2(2),
  CREATE_DATE       TIMESTAMP(6)
);
comment on table CLINIC_VS_CHARGE
  is '诊疗项目与价表对照';
-- Add comments to the columns 
comment on column CLINIC_VS_CHARGE.ID
  is '主键';
comment on column CLINIC_VS_CHARGE.CLINIC_ITEM_CLASS
  is '临床诊疗项目类别';
comment on column CLINIC_VS_CHARGE.CLINIC_ITEM_CODE
  is '临床诊疗项目代码';
comment on column CLINIC_VS_CHARGE.CHARGE_ITEM_NO
  is '对应价表项目序号';
comment on column CLINIC_VS_CHARGE.CHARGE_ITEM_CLASS
  is '对应价表项目分类';
comment on column CLINIC_VS_CHARGE.CHARGE_ITEM_CODE
  is '对应价表项目代码';
comment on column CLINIC_VS_CHARGE.CHARGE_ITEM_SPEC
  is '对应价表项目规格';
comment on column CLINIC_VS_CHARGE.AMOUNT
  is '对应价表项目数量';
comment on column CLINIC_VS_CHARGE.UNITS
  is '对应价表项目单位';
comment on column CLINIC_VS_CHARGE.BACKBILL_RULE
  is '划价规则';
comment on column CLINIC_VS_CHARGE.ORG_ID
  is '所属组织结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CLINIC_VS_CHARGE
  add constraint CLINIC_VS_CHARGE_PK primary key (ID);
