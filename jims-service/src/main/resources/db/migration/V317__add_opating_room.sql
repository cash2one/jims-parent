-- Create table
create table OPERATING_ROOM
(
  id           varchar2(64) ,
  ROOM_NO      VARCHAR2(3) not null,
  DEPT_ID    VARCHAR2(8) not null,
  LOCATION     VARCHAR2(20),
  STATUS       VARCHAR2(1),
  BED_ID       NUMBER(3),
  BED_LABEL    VARCHAR2(12),
  MONITOR_CODE VARCHAR2(5),
  BRANCH_NO    NUMBER(2),
  SAM_SPACE    NUMBER(4),
  PATIENT_ID   VARCHAR2(10),
  VISIT_ID     NUMBER(5),
  OPER_ID      NUMBER(2)
);
-- Add comments to the table
comment on table OPERATING_ROOM
  is '手术间';
-- Add comments to the columns
comment on column OPERATING_ROOM.ROOM_NO
  is '手术间号';
comment on column OPERATING_ROOM.DEPT_CODE
  is '所属手术室';
comment on column OPERATING_ROOM.LOCATION
  is '位置';
comment on column OPERATING_ROOM.STATUS
  is '状态';
comment on column OPERATING_ROOM.BED_ID
  is '床号';
comment on column OPERATING_ROOM.BED_LABEL
  is '床标签';
comment on column OPERATING_ROOM.PATIENT_ID
  is '在床病人ID';
comment on column OPERATING_ROOM.VISIT_ID
  is '住院次数';
comment on column OPERATING_ROOM.OPER_ID
  is '手术次数';
-- Create/Recreate primary, unique and foreign key constraints
alter table OPERATING_ROOM
  add constraint PK_OPERATING_ROOM primary key (id);
