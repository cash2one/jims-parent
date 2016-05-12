-- drop table SCHEDULED_OPERATION_NAME cascade constraints;     
-- Create table BY PQ
/*==============================================================*/
/* Table: SCHEDULED_OPERATION_NAME      安排手术名称            */
/*==============================================================*/
create table SCHEDULED_OPERATION_NAME
(
  ID              VARCHAR2(64 CHAR),
  SCHEDULE_ID     NUMBER(2) not null,
  OPERATION_NO    NUMBER(2) not null,
  OPERATION       VARCHAR2(200 CHAR),
  OPERATION_SCALE VARCHAR2(2 CHAR),
  OPERATION_CODE  VARCHAR2(20 CHAR),
  REMARKS         VARCHAR2(2000 CHAR),
  UPDATE_BY       VARCHAR2(64 CHAR),
  CREATE_BY       VARCHAR2(64 CHAR),
  UPDATE_DATE     TIMESTAMP(6),
  DEL_FLAG        VARCHAR2(2 CHAR),
  CREATE_DATE     TIMESTAMP(6),
  constraint PK_SCHEDULED_OPERATION_NAME primary key (ID)
);

-- Add comments to the table 
comment on table SCHEDULED_OPERATION_NAME
  is '安排手术名称';
-- Add comments to the columns 

comment on column SCHEDULED_OPERATION_NAME.SCHEDULE_ID
  is '手术安排标识';
comment on column SCHEDULED_OPERATION_NAME.OPERATION_NO
  is '手术序号';
comment on column SCHEDULED_OPERATION_NAME.OPERATION
  is '手术';
comment on column SCHEDULED_OPERATION_NAME.OPERATION_SCALE
  is '手术等级';
comment on column SCHEDULED_OPERATION_NAME.OPERATION_CODE
  is '手术编码';

