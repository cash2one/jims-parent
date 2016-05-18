/*==============================================================*/
/* Table:ORDERS_COSTS   医嘱计价项目                          */
/* Table:ORDERS_COSTS   医嘱计价项目                          */
/* CREATE_DATE: 2016-05-18                                      */
/* CREATE_BY :  pq                                             */
/*==============================================================*/
create table ORDERS_COSTS
(
  ID            VARCHAR2(64)         not null,
  PATIENT_ID    VARCHAR2(64) not null,
  VISIT_ID      VARCHAR2(64) not null,
  ORDER_ID      VARCHAR2(64) not null, 
  ORDER_NO      NUMBER(4) not null,
  ORDER_SUB_NO  NUMBER(2),
  ITEM_NO       NUMBER(2) not null,
  ITEM_CLASS    VARCHAR2(1),
  ITEM_NAME     VARCHAR2(100 CHAR),
  ITEM_CODE     VARCHAR2(20),
  ITEM_SPEC     VARCHAR2(64),
  UNITS         VARCHAR2(8),
  AMOUNT        NUMBER(8,4),
  TOTAL_AMOUNT  NUMBER(10,4),
  COSTS         NUMBER(8,2),
  BACKBILL_RULE VARCHAR2(64),
  REMARKS         VARCHAR2(2000 CHAR),
  UPDATE_BY       VARCHAR2(64),
  CREATE_BY       VARCHAR2(64),
  UPDATE_DATE     TIMESTAMP(6),
  DEL_FLAG        VARCHAR2(2 CHAR),
  CREATE_DATE     TIMESTAMP(6),


  constraint PK_ORDERS_COSTS primary key (ID)
);

-- Add comments to the table 
-- Add comments to the table
comment on table ORDERS_COSTS
  is '医嘱计价项目';
-- Add comments to the columns
comment on column ORDERS_COSTS.PATIENT_ID
  is '病人标识号';
comment on column ORDERS_COSTS.VISIT_ID
  is '病人本次住院标识';
comment on column ORDERS_COSTS.ORDER_ID
  is '医嘱主键Id';
comment on column ORDERS_COSTS.ORDER_NO
  is '医嘱序号';
comment on column ORDERS_COSTS.ORDER_SUB_NO
  is '医嘱子序号';
comment on column ORDERS_COSTS.ITEM_NO
  is '计价项目序号';
comment on column ORDERS_COSTS.ITEM_CLASS
  is '计价项目类别';
comment on column ORDERS_COSTS.ITEM_NAME
  is '计价项目名称';
comment on column ORDERS_COSTS.ITEM_CODE
  is '计价项目代码';
comment on column ORDERS_COSTS.ITEM_SPEC
  is '计价项目规格';
comment on column ORDERS_COSTS.UNITS
  is '计价单位';
comment on column ORDERS_COSTS.AMOUNT
  is '数量';
comment on column ORDERS_COSTS.TOTAL_AMOUNT
  is '累计数量';
comment on column ORDERS_COSTS.COSTS
  is '本项目累计费用';
comment on column ORDERS_COSTS.BACKBILL_RULE
  is '后台记费规则';
