-- Create table
/*==============================================================*/
/* Table: PRICE_LIST                                      */
/*==============================================================*/
create table PRICE_LIST
(
  ITEM_CLASS         VARCHAR2(20) not null,
  ITEM_CODE          VARCHAR2(20) not null,
  ITEM_NAME          VARCHAR2(100),
  ITEM_SPEC          VARCHAR2(50) not null,
  UNITS              VARCHAR2(8) not null,
  PRICE              NUMBER(9,3),
  PREFER_PRICE       NUMBER(9,3),
  FOREIGNER_PRICE    NUMBER(9,3),
  PERFORMED_BY       VARCHAR2(8),
  FEE_TYPE_MASK      NUMBER(1) default 0,
  CLASS_ON_INP_RCPT  VARCHAR2(20),
  CLASS_ON_OUTP_RCPT VARCHAR2(20),
  CLASS_ON_RECKONING VARCHAR2(20),
  SUBJ_CODE          VARCHAR2(20),
  CLASS_ON_MR        VARCHAR2(4),
  MEMO               VARCHAR2(100),
  START_DATE         DATE not null,
  STOP_DATE          DATE,
  OPERATOR           VARCHAR2(8),
  ENTER_DATE         DATE,
  HIGH_PRICE         NUMBER(10,4),
  MATERIAL_CODE      VARCHAR2(100),
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
  STOP_OPERATOR      VARCHAR2(20),
  ID                 VARCHAR2(64) not null,
  CREATE_BY          VARCHAR2(64),
  CREATE_DATE        TIMESTAMP(6),
  UPDATE_BY          VARCHAR2(64),
  UPDATE_DATE        TIMESTAMP(6),
  REMARKS            VARCHAR2(255),
  DEL_FLAG           CHAR(1) default '0' not null,
  ORG_ID              VARCHAR2(64)
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
comment on column PRICE_LIST.ITEM_CLASS
  is '项目类别';
comment on column PRICE_LIST.ITEM_CODE
  is '项目代码';
comment on column PRICE_LIST.ITEM_NAME
  is '项目名称';
comment on column PRICE_LIST.ITEM_SPEC
  is '规格';
comment on column PRICE_LIST.UNITS
  is '单位';
comment on column PRICE_LIST.PRICE
  is '基本价格';
comment on column PRICE_LIST.PREFER_PRICE
  is '优惠价格';
comment on column PRICE_LIST.FOREIGNER_PRICE
  is '外宾价格';
comment on column PRICE_LIST.PERFORMED_BY
  is '执行科室';
comment on column PRICE_LIST.FEE_TYPE_MASK
  is '是否自费';
comment on column PRICE_LIST.CLASS_ON_INP_RCPT
  is '住院收据分类';
comment on column PRICE_LIST.CLASS_ON_OUTP_RCPT
  is '门诊收据分类';
comment on column PRICE_LIST.CLASS_ON_RECKONING
  is '核算科目';
comment on column PRICE_LIST.SUBJ_CODE
  is '会计科目';
comment on column PRICE_LIST.CLASS_ON_MR
  is '病案首页分类';
comment on column PRICE_LIST.MEMO
  is '备注信息';
comment on column PRICE_LIST.START_DATE
  is '启用日期';
comment on column PRICE_LIST.STOP_DATE
  is '停止日期';
comment on column PRICE_LIST.OPERATOR
  is '维护者';
comment on column PRICE_LIST.ENTER_DATE
  is '输入日期';
comment on column PRICE_LIST.HIGH_PRICE
  is '最高价格';
comment on column PRICE_LIST.MATERIAL_CODE
  is '物价码';
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
comment on column PRICE_LIST.ID
  is '主键';
comment on column PRICE_LIST.CREATE_BY
  is '创建者';
comment on column PRICE_LIST.CREATE_DATE
  is '创建时间';
comment on column PRICE_LIST.UPDATE_BY
  is '更新者';
comment on column PRICE_LIST.UPDATE_DATE
  is '更新时间';
comment on column PRICE_LIST.REMARKS
  is '备注信息';
comment on column PRICE_LIST.DEL_FLAG
  is '删除标记';
-- Create/Recreate primary, unique and foreign key constraints
alter table PRICE_LIST
  add constraint PK_PRICE_LIST primary key (ID)
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
-- Create/Recreate indexes
create unique index UK_PRICE_LIST on PRICE_LIST (ITEM_CLASS, ITEM_CODE, ITEM_SPEC, UNITS, START_DATE)
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
create sequence PRICE_DICT
minvalue  1
maxvalue  999999
start  with  1
increment  by  1
cache  20;