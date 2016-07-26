-- Create table
create table BLOOD_CAPACITY
(
  APPLY_NUM      VARCHAR2(64),
  MATCH_SUB_NUM  VARCHAR2(10) ,
  FAST_SLOW      VARCHAR2(10),
  TRANS_DATE     TIMESTAMP ,
  TRANS_CAPACITY NUMBER(7,2),
  BLOOD_TYPE     VARCHAR2(10),
  OPERATOR       VARCHAR2(20),
  UNIT           VARCHAR2(10),
  ID             VARCHAR2(64) not null,
  ORG_ID         VARCHAR2(64),
  APPLY_ID      VARCHAR2(64),
  REMARKS        VARCHAR2(2000),
  UPDATE_BY      VARCHAR2(64),
  CREATE_BY      VARCHAR2(64),
  UPDATE_DATE    TIMESTAMP(6),
  DEL_FLAG       VARCHAR2(2),
  CREATE_DATE    TIMESTAMP(6)
);
-- Add comments to the table 
comment on table BLOOD_CAPACITY
  is '申请用血量表';
-- Add comments to the columns 
comment on column BLOOD_CAPACITY.APPLY_NUM
  is '申请单号';
comment on column BLOOD_CAPACITY.MATCH_SUB_NUM
  is '申请单子号';
comment on column BLOOD_CAPACITY.FAST_SLOW
  is '用血安排,1:急诊，2：计划，3：备血';
comment on column BLOOD_CAPACITY.TRANS_DATE
  is '预定输血时间';
comment on column BLOOD_CAPACITY.TRANS_CAPACITY
  is '输血量';
comment on column BLOOD_CAPACITY.BLOOD_TYPE
  is '申请成份血';
comment on column BLOOD_CAPACITY.OPERATOR
  is '操作者';
comment on column BLOOD_CAPACITY.UNIT
  is '血液单位';
comment on column BLOOD_CAPACITY.ID
  is '主键';
comment on column BLOOD_CAPACITY.ORG_ID
  is '所属结构';
  comment on column BLOOD_CAPACITY.APPLY_ID
  is '主表ID';
-- Create/Recreate primary, unique and foreign key constraints
alter table BLOOD_CAPACITY
  add constraint BLOOD_CAPACITY_UK unique (APPLY_NUM);
