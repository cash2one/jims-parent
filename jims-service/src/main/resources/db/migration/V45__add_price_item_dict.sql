--drop table PRICE_ITEM_NAME_DICT cascade constraints;
-- Create table
/*==============================================================*/
/* Table: PRICE_ITEM_NAME_DICT                                      */
/*==============================================================*/
create table PRICE_ITEM_NAME_DICT
(
  ITEM_CLASS      VARCHAR2(20) not null,
  ITEM_NAME       VARCHAR2(100) not null,
  ITEM_CODE       VARCHAR2(20) not null,
  STD_INDICATOR   NUMBER(1),
  INPUT_CODE      VARCHAR2(8),
  STOP_FLAG       NUMBER(1),
  INPUT_CODE_WB   VARCHAR2(8),
  PERFORMED_BY_MZ VARCHAR2(20),
  WARD_FLAG       NUMBER(1),
  TESHU_MZ_FLAG   NUMBER(1),
  MEMO            VARCHAR2(80),
  JCKFLAG         VARCHAR2(2),
  ID              VARCHAR2(64) not null,
  CREATE_BY       VARCHAR2(64),
  CREATE_DATE     TIMESTAMP(6),
  UPDATE_BY       VARCHAR2(64),
  UPDATE_DATE     TIMESTAMP(6),
  REMARKS         VARCHAR2(255),
  DEL_FLAG        CHAR(1) default '0' not null,
  ORG_ID          VARCHAR2(64)
)
tablespace TSP_COMM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 960
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table PRICE_ITEM_NAME_DICT
  is '价表项目名称字典';
-- Add comments to the columns
comment on column PRICE_ITEM_NAME_DICT.ITEM_CLASS
  is '项目分类';
comment on column PRICE_ITEM_NAME_DICT.ITEM_NAME
  is '项目名称';
comment on column PRICE_ITEM_NAME_DICT.ITEM_CODE
  is '项目代码';
comment on column PRICE_ITEM_NAME_DICT.STD_INDICATOR
  is '正名标志';
comment on column PRICE_ITEM_NAME_DICT.INPUT_CODE
  is '输入码';
comment on column PRICE_ITEM_NAME_DICT.PERFORMED_BY_MZ
  is '门诊医护分开,默认护理单元';
comment on column PRICE_ITEM_NAME_DICT.WARD_FLAG
  is '是否护理(住院)';
comment on column PRICE_ITEM_NAME_DICT.TESHU_MZ_FLAG
  is '门诊特殊项目';
comment on column PRICE_ITEM_NAME_DICT.ID
  is '主键';
comment on column PRICE_ITEM_NAME_DICT.CREATE_BY
  is '创建者';
comment on column PRICE_ITEM_NAME_DICT.CREATE_DATE
  is '创建时间';
comment on column PRICE_ITEM_NAME_DICT.UPDATE_BY
  is '更新者';
comment on column PRICE_ITEM_NAME_DICT.UPDATE_DATE
  is '更新时间';
comment on column PRICE_ITEM_NAME_DICT.REMARKS
  is '备注信息';
comment on column PRICE_ITEM_NAME_DICT.DEL_FLAG
  is '删除标记';
-- Create/Recreate primary, unique and foreign key constraints
alter table PRICE_ITEM_NAME_DICT
  add constraint PK_PRICE_ITEM_NAME_DICT primary key (ID)
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
create unique index UK_PRICE_ITEM_NAME_DICT on PRICE_ITEM_NAME_DICT (ITEM_CLASS, ITEM_NAME, ITEM_CODE)
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
