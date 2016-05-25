-- Create table
create table DRUG_INVENTORY_CHECK
(
  CHECK_YEAR_MONTH DATE not null,
  STORAGE          VARCHAR2(8) not null,
  DRUG_CODE        VARCHAR2(20) not null,
  DRUG_SPEC        VARCHAR2(20) not null,
  UNITS            VARCHAR2(8),
  FIRM_ID          VARCHAR2(10) not null,
  BATCH_NO         VARCHAR2(16) not null,
  MIN_SPEC         VARCHAR2(20) not null,
  MIN_UNITS        VARCHAR2(8),
  SUB_STORAGE      VARCHAR2(10),
  ACCOUNT_QUANTITY NUMBER(12,2),
  ACTUAL_QUANTITY  NUMBER(12,2),
  TRADE_PRICE      NUMBER(10,4),
  RETAIL_PRICE     NUMBER(10,4),
  REC_STATUS       NUMBER(1),
  CHANGE_FLAG      NUMBER(1),
  REMARKS          VARCHAR2(2000),
  UPDATE_BY        VARCHAR2(64),
  CREATE_BY        VARCHAR2(64),
  UPDATE_DATE      DATE,
  DEL_FLAG         VARCHAR2(100),
  CREATE_DATE      DATE,
  ID               VARCHAR2(64) not null,
  ORG_ID           varchar2(64)
);
-- Add comments to the table
comment on table DRUG_INVENTORY_CHECK
  is '盘点记录表';
-- Add comments to the columns
comment on column DRUG_INVENTORY_CHECK.CHECK_YEAR_MONTH
  is '盘点年月';
comment on column DRUG_INVENTORY_CHECK.STORAGE
  is '库存管理单位';
comment on column DRUG_INVENTORY_CHECK.DRUG_CODE
  is '药品代码';
comment on column DRUG_INVENTORY_CHECK.DRUG_SPEC
  is '包装规格';
comment on column DRUG_INVENTORY_CHECK.UNITS
  is '单位';
comment on column DRUG_INVENTORY_CHECK.FIRM_ID
  is '厂家标识';
comment on column DRUG_INVENTORY_CHECK.BATCH_NO
  is '批号';
comment on column DRUG_INVENTORY_CHECK.MIN_SPEC
  is '最小单位规格';
comment on column DRUG_INVENTORY_CHECK.MIN_UNITS
  is '最小单位';
comment on column DRUG_INVENTORY_CHECK.SUB_STORAGE
  is '存放库房';
comment on column DRUG_INVENTORY_CHECK.ACCOUNT_QUANTITY
  is '帐面数量';
comment on column DRUG_INVENTORY_CHECK.ACTUAL_QUANTITY
  is '实际数量';
comment on column DRUG_INVENTORY_CHECK.TRADE_PRICE
  is '市场批发价';
comment on column DRUG_INVENTORY_CHECK.RETAIL_PRICE
  is '市场零售价';
comment on column DRUG_INVENTORY_CHECK.REC_STATUS
  is '状态0，为暂存；1，终存';
comment on column DRUG_INVENTORY_CHECK.CHANGE_FLAG
  is '更改库存标志';
comment on column DRUG_INVENTORY_CHECK.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_INVENTORY_CHECK
  add constraint DRUG_INVENTORY_CHECK_PK primary key (ID);
alter table DRUG_INVENTORY_CHECK
  add constraint DRUG_INVENTORY_CHECK_UK unique (CHECK_YEAR_MONTH, STORAGE, MIN_SPEC, DRUG_CODE, DRUG_SPEC, FIRM_ID, BATCH_NO);
