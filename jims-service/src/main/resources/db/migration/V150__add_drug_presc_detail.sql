--drop table DRUG_PRESC_DETAIL cascade constraints;

/*==============================================================*/
/* Table: DRUG_PRESC_DETAIL   药品处方明细记录           */
/*CREATE_BY: pq	                                      */
/*==============================================================*/
create table DRUG_PRESC_DETAIL
(
  ID             VARCHAR2(64) not null,
  MASTER_ID      VARCHAR2(64),
  PRESC_DATE     TIMESTAMP not null,
  PRESC_NO       NUMBER(5) not null,
  ITEM_NO        NUMBER(2) not null,
  DRUG_CODE      VARCHAR2(64),
  DRUG_SPEC      VARCHAR2(64),
  DRUG_NAME      VARCHAR2(100),
  FIRM_ID        VARCHAR2(64),
  PACKAGE_SPEC   VARCHAR2(64),
  PACKAGE_UNITS  VARCHAR2(64),
  QUANTITY       NUMBER(6,2),
  COSTS          NUMBER(10,4),
  PAYMENTS       NUMBER(10,4),
  ORDER_NO       NUMBER(4),
  ORDER_SUB_NO   NUMBER(2),
  ADMINISTRATION VARCHAR2(100),
  FLAG           NUMBER(1),
  DOSAGE_EACH    NUMBER(10,4),
  DOSAGE_UNITS   VARCHAR2(64),
  FREQUENCY      VARCHAR2(64),
  FREQ_DETAIL    VARCHAR2(80),
  BATCH_NO       VARCHAR2(16),
  INVENTORY      NUMBER(12,2),
  HSPH           VARCHAR2(64),
  FFPH           VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64 char),
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             NUMBER(1)            default 0,
   constraint PK_DRUG_PRESC_DETAIL primary key (ID)    
  
);

-- Add comments to the table 
comment on table DRUG_PRESC_DETAIL
  is '药品处方明细记录';
  
-- Add comments to the columns 
comment on column DRUG_PRESC_DETAIL.ID is
'主键';
comment on column DRUG_PRESC_DETAIL.PRESC_DATE
  is '处方日期';
comment on column DRUG_PRESC_DETAIL.PRESC_NO
  is '处方号';
comment on column DRUG_PRESC_DETAIL.ITEM_NO
  is '项目序号';
comment on column DRUG_PRESC_DETAIL.DRUG_CODE
  is '药品代码';
comment on column DRUG_PRESC_DETAIL.DRUG_SPEC
  is '药品规格';
comment on column DRUG_PRESC_DETAIL.DRUG_NAME
  is '药品名称';
comment on column DRUG_PRESC_DETAIL.FIRM_ID
  is '厂商标识';
comment on column DRUG_PRESC_DETAIL.PACKAGE_SPEC
  is '包装规格';
comment on column DRUG_PRESC_DETAIL.PACKAGE_UNITS
  is '单位';
comment on column DRUG_PRESC_DETAIL.QUANTITY
  is '数量';
comment on column DRUG_PRESC_DETAIL.COSTS
  is '费用';
comment on column DRUG_PRESC_DETAIL.PAYMENTS
  is '实付费用';
comment on column DRUG_PRESC_DETAIL.ORDER_NO
  is '医嘱序号';
comment on column DRUG_PRESC_DETAIL.ORDER_SUB_NO
  is '医嘱子序号';
comment on column DRUG_PRESC_DETAIL.ADMINISTRATION
  is '用法';
comment on column DRUG_PRESC_DETAIL.FLAG
  is '退药标志';
comment on column DRUG_PRESC_DETAIL.HSPH
  is '毒麻回收批号';
comment on column DRUG_PRESC_DETAIL.FFPH
  is '毒麻发放批号';
comment on column DRUG_PRESC_DETAIL.CREATE_DATE is
'创建日期';

comment on column DRUG_PRESC_DETAIL.CREATE_BY is
'创建人';

comment on column DRUG_PRESC_DETAIL.UPDATE_BY is
'更新人';

comment on column DRUG_PRESC_DETAIL.UPDATE_DATE is
'更新日期';

comment on column DRUG_PRESC_DETAIL.REMARKS is
'备注信息';

comment on column DRUG_PRESC_DETAIL.DEL_FLAG is
'删除标志';

