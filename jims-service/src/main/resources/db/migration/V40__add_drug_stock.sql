-- drop table DRUG_STOCK cascade constraints;
-- Create table
/*==============================================================*/
/* Table: DRUG_STOCK      药品库存                                */
/*==============================================================*/
create table DRUG_STOCK
(
  id                  varchar2(64 CHAR),
  STORAGE             VARCHAR2(8 CHAR) not null,
  DRUG_CODE           VARCHAR2(20 CHAR) not null,
  DRUG_SPEC           VARCHAR2(20 CHAR) not null,
  UNITS               VARCHAR2(8 CHAR),
  BATCH_NO            VARCHAR2(16 CHAR) not null,
  EXPIRE_DATE         DATE,
  FIRM_ID             VARCHAR2(10 CHAR) not null,
  PURCHASE_PRICE      NUMBER(10,4),
  DISCOUNT            NUMBER(5,2),
  PACKAGE_SPEC        VARCHAR2(20 CHAR) not null,
  QUANTITY            NUMBER(12,2),
  PACKAGE_UNITS       VARCHAR2(8 CHAR),
  SUB_PACKAGE_1       NUMBER(12,2),
  SUB_PACKAGE_UNITS_1 VARCHAR2(8 CHAR),
  SUB_PACKAGE_SPEC_1  VARCHAR2(20 CHAR),
  SUB_PACKAGE_2       NUMBER(12,2),
  SUB_PACKAGE_UNITS_2 VARCHAR2(8 CHAR),
  SUB_PACKAGE_SPEC_2  VARCHAR2(20 CHAR),
  SUB_STORAGE         VARCHAR2(8 CHAR) not null,
  LOCATION            VARCHAR2(20 CHAR),
  DOCUMENT_NO         VARCHAR2(10 CHAR),
  SUPPLY_INDICATOR    NUMBER(1),
  SUPPLY_MZ           NUMBER(1),
  ORG_ID              VARCHAR2(64 CHAR),
  CREATE_DATE      TIMESTAMP (6),
  CREATE_BY        VARCHAR2(64 CHAR),
  UPDATE_DATE      TIMESTAMP (6),
  UPDATE_BY        VARCHAR2(64 CHAR),
  REMARKS          VARCHAR2(200 CHAR),
  DEL_FLAG         NUMBER(1)
);
-- Add comments to the table 
comment on table DRUG_STOCK
  is '药品库存';
-- Add comments to the columns 
comment on column DRUG_STOCK.STORAGE
  is '库存管理单位';
comment on column DRUG_STOCK.DRUG_CODE
  is '药品代码';
comment on column DRUG_STOCK.DRUG_SPEC
  is '规格';
comment on column DRUG_STOCK.UNITS
  is '单位';
comment on column DRUG_STOCK.BATCH_NO
  is '批号';
comment on column DRUG_STOCK.EXPIRE_DATE
  is '有效期';
comment on column DRUG_STOCK.FIRM_ID
  is '厂家标识';
comment on column DRUG_STOCK.PURCHASE_PRICE
  is '进货价';
comment on column DRUG_STOCK.DISCOUNT
  is '折扣';
comment on column DRUG_STOCK.PACKAGE_SPEC
  is '包装规格';
comment on column DRUG_STOCK.QUANTITY
  is '数量';
comment on column DRUG_STOCK.PACKAGE_UNITS
  is '包装单位';
comment on column DRUG_STOCK.SUB_PACKAGE_1
  is '内含包装1';
comment on column DRUG_STOCK.SUB_PACKAGE_UNITS_1
  is '内含包装1单位';
comment on column DRUG_STOCK.SUB_PACKAGE_SPEC_1
  is '内含包装1规格';
comment on column DRUG_STOCK.SUB_PACKAGE_2
  is '内含包装2';
comment on column DRUG_STOCK.SUB_PACKAGE_UNITS_2
  is '内含包装2单位';
comment on column DRUG_STOCK.SUB_PACKAGE_SPEC_2
  is '内含包装2规格';
comment on column DRUG_STOCK.SUB_STORAGE
  is '存放库房';
comment on column DRUG_STOCK.LOCATION
  is '货位';
comment on column DRUG_STOCK.DOCUMENT_NO
  is '入库单号';
comment on column DRUG_STOCK.SUPPLY_INDICATOR
  is '供应标志';
comment on column DRUG_STOCK.SUPPLY_MZ
  is '适用门诊住院';
comment on column DRUG_STOCK.ORG_ID
  is '所属组织结构';
comment on column DRUG_STOCK.CREATE_DATE
  is '创建日期';
comment on column DRUG_STOCK.CREATE_BY
  is '创建人';
comment on column DRUG_STOCK.UPDATE_DATE
  is '更新日期';
comment on column DRUG_STOCK.UPDATE_BY
  is '更新人';
comment on column DRUG_STOCK.REMARKS
  is '备注信息';
comment on column DRUG_STOCK.DEL_FLAG
  is '删除标志';  
-- Create/Recreate primary, unique and foreign key constraints 
alter table DRUG_STOCK
  add constraint PK_DRUG_STOCK primary key (id);
-- Grant/Revoke object privileges 
/*grant select on DRUG_STOCK to PUBLIC;*/
