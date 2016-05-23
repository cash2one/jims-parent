/*==============================================================*/
/* Table: BED_REC    增加 床位信息维护                           */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/
-- insert into

-- Create table
create table BED_REC
(
  WARD_CODE          VARCHAR2(8) not null,
  BED_NO             NUMBER(8) not null,
  BED_LABEL          VARCHAR2(8),
  ROOM_NO            VARCHAR2(4),
  DEPT_CODE          VARCHAR2(8),
  BED_APPROVED_TYPE  VARCHAR2(1),
  BED_SEX_TYPE       VARCHAR2(1),
  BED_CLASS          VARCHAR2(20),
  BED_STATUS         VARCHAR2(1),
  LEND_ATTR          VARCHAR2(1),
  LEND_BED_NO        NUMBER(3),
  LEND_BED_DEPT      VARCHAR2(8),
  LEND_BED_WARD      VARCHAR2(8),
  LOCK_STATUS        VARCHAR2(1),
  LOCK_OPERATOR      VARCHAR2(20),
  AIRCONDITION_CLASS VARCHAR2(20),
  PATIENT_ID         VARCHAR2(10),
  DEPT_ID            VARCHAR2(64),
  ID                 VARCHAR2(64) not null,
  REMARKS            VARCHAR2(2000),
  UPDATE_BY          VARCHAR2(64),
  CREATE_BY          VARCHAR2(64),
  UPDATE_DATE        DATE,
  DEL_FLAG           VARCHAR2(100),
  CREATE_DATE        DATE
);
-- Add comments to the table
comment on table BED_REC
  is '床位记录表';
-- Add comments to the columns
comment on column BED_REC.WARD_CODE
  is '病房（护理单元）代码';
comment on column BED_REC.BED_NO
  is '床号';
comment on column BED_REC.BED_LABEL
  is '床标号';
comment on column BED_REC.ROOM_NO
  is '房间';
comment on column BED_REC.DEPT_CODE
  is '所属科室代码';
comment on column BED_REC.BED_APPROVED_TYPE
  is '床位编制类型';
comment on column BED_REC.BED_SEX_TYPE
  is '床位类型';
comment on column BED_REC.BED_CLASS
  is '床位等级';
comment on column BED_REC.BED_STATUS
  is '床位状态';
comment on column BED_REC.LEND_BED_WARD
  is '借床标志';
comment on column BED_REC.LOCK_STATUS
  is '是否锁住床位';
comment on column BED_REC.LOCK_OPERATOR
  is '锁床位操作员';
comment on column BED_REC.AIRCONDITION_CLASS
  is '空调类型';
comment on column BED_REC.PATIENT_ID
  is '当前病人';
comment on column BED_REC.DEPT_ID
  is '护理单元ID';
comment on column BED_REC.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table BED_REC
  add constraint BED_REC_PK primary key (ID);
alter table BED_REC
  add constraint BED_REC_UK unique (DEPT_ID, BED_NO);
