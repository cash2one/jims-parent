drop table PRICE_LIST cascade constraints;
-- Create table
/*==============================================================*/
/* Table: PRICE_LIST                                      */
/*==============================================================*/
create table PRICE_LIST
(
  ITEM_CLASS         VARCHAR2(1) not null,
  ITEM_CODE          VARCHAR2(20) not null,
  ITEM_NAME          VARCHAR2(100),
  ITEM_SPEC          VARCHAR2(50) not null,
  UNITS              VARCHAR2(8) not null,
  PRICE              NUMBER(9,3),
  PREFER_PRICE       NUMBER(9,3),
  FOREIGNER_PRICE    NUMBER(9,3),
  PERFORMED_BY       VARCHAR2(8),
  FEE_TYPE_MASK      NUMBER(1),
  CLASS_ON_INP_RCPT  VARCHAR2(1),
  CLASS_ON_OUTP_RCPT VARCHAR2(1),
  CLASS_ON_RECKONING VARCHAR2(10),
  SUBJ_CODE          VARCHAR2(3),
  CLASS_ON_MR        VARCHAR2(4),
  MEMO               VARCHAR2(100),
  START_DATE         DATE not null,
  STOP_DATE          DATE,
  OPERATOR           VARCHAR2(8),
  ENTER_DATE         DATE,
  HIGH_PRICE         NUMBER(10,4),
  MATERIAL_CODE      VARCHAR2(20),
  SCORE_1            NUMBER(10,2),
  SCORE_2            NUMBER(10,2),
  PRICE_NAME_CODE    VARCHAR2(20),
  CONTROL_FLAG       VARCHAR2(1),
  INPUT_CODE         VARCHAR2(8),
  INPUT_CODE_WB      VARCHAR2(8),
  STD_CODE_1         VARCHAR2(20),
  CHANGED_MEMO       VARCHAR2(40),
  CLASS_ON_INSUR_MR  VARCHAR2(24),
  CWTJ_CODE          VARCHAR2(24),
  XM_WY              VARCHAR2(24),
  LB_WY              VARCHAR2(24),
  MZSJ_WY            VARCHAR2(24),
  ZYSJ_WY            VARCHAR2(24),
  GROUP_FLAG         CHAR(1),
  STOP_OPERATOR      VARCHAR2(20)
)
tablespace TSP_COMM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column PRICE_LIST.CHANGED_MEMO
  is '价格变更原因包括调价和停用等都可以录入保存原因';
comment on column PRICE_LIST.XM_WY
  is '未央项目对照';
comment on column PRICE_LIST.LB_WY
  is '未央类别对照';
comment on column PRICE_LIST.MZSJ_WY
  is '门诊收据对照';
comment on column PRICE_LIST.ZYSJ_WY
  is '住院收据对照';
-- Create/Recreate primary, unique and foreign key constraints
alter table PRICE_LIST
  add constraint PK_PRICE_LIST primary key (ITEM_CLASS, ITEM_CODE, ITEM_SPEC, UNITS, START_DATE)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );