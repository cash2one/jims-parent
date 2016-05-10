-- drop table DRUG_SUPPLIER_CATALOG cascade constraints;
-- Create table
/*==============================================================*/
/* Table: DRUG_SUPPLIER_CATALOG          药品供应商生产商         */
/*==============================================================*/

create table DRUG_SUPPLIER_CATALOG
(
  SUPPLIER_ID    VARCHAR2(10) not null,
  SUPPLIER       VARCHAR2(40),
  SUPPLIER_CLASS VARCHAR2(8),
  INPUT_CODE     VARCHAR2(8),
  MEMO           VARCHAR2(100),
  TRADEMARK      VARCHAR2(20),
  INPUT_CODE_WB  VARCHAR2(8),
  FOREIGNX       NUMBER(1) default 0,
  SUPPLIER_CODE  VARCHAR2(10),
  USED_FLAG      NUMBER(1) default 0,
  ID             VARCHAR2(64) not null,
  ORG_ID         VARCHAR2(64),
  REMARKS        VARCHAR2(2000),
  UPDATE_BY      VARCHAR2(64),
  CREATE_BY      VARCHAR2(64),
  UPDATE_DATE    DATE,
  DEL_FLAG       VARCHAR2(100),
  CREATE_DATE    DATE
);
-- Add comments to the table
comment on table DRUG_SUPPLIER_CATALOG
  is '药品供应商生产商维护';
-- Add comments to the columns
comment on column DRUG_SUPPLIER_CATALOG.SUPPLIER_ID
  is '供应商别名';
comment on column DRUG_SUPPLIER_CATALOG.SUPPLIER
  is '厂商';
comment on column DRUG_SUPPLIER_CATALOG.SUPPLIER_CLASS
  is '厂商类别';
comment on column DRUG_SUPPLIER_CATALOG.INPUT_CODE
  is '输入码';
comment on column DRUG_SUPPLIER_CATALOG.MEMO
  is '备注';
comment on column DRUG_SUPPLIER_CATALOG.TRADEMARK
  is '注册商标';
comment on column DRUG_SUPPLIER_CATALOG.FOREIGNX
  is '是否国外';
comment on column DRUG_SUPPLIER_CATALOG.SUPPLIER_CODE
  is '厂商代码';
comment on column DRUG_SUPPLIER_CATALOG.USED_FLAG
  is '是否使用1停用0使用';
comment on column DRUG_SUPPLIER_CATALOG.ID
  is '主键';
comment on column DRUG_SUPPLIER_CATALOG.ORG_ID
  is '所属组织结构';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_SUPPLIER_CATALOG
  add constraint DRUG_SUPPLIER_CATALOG_PK primary key (ID);
alter table DRUG_SUPPLIER_CATALOG
  add constraint DRUG_SUPPLIER_CATALOG_UK unique (SUPPLIER_ID);
