-- drop table OPERATION_SCHEDULE cascade constraints;     
-- Create table BY PQ
/*==============================================================*/
/* Table: OPERATION_SCHEDULE      手术安排                      */
/*==============================================================*/
create table OPERATION_SCHEDULE
(
  ID                     VARCHAR2(64) not null,
  PATIENT_ID             VARCHAR2(64),
  VISIT_ID               VARCHAR2(64),
  CLINIC_ID              VARCHAR2(64),
  ORG_ID                 VARCHAR2(64),
  SCHEDULE_ID            NUMBER(2) not null,
  DEPT_STAYED            VARCHAR2(64 CHAR),
  BED_NO                 NUMBER(3),
  SCHEDULED_DATE_TIME    TIMESTAMP(6),
  OPERATING_ROOM         VARCHAR2(8 CHAR),
  OPERATING_ROOM_NO      VARCHAR2(3 CHAR),
  SEQUENCE               NUMBER(1),
  DIAG_BEFORE_OPERATION  VARCHAR2(80 CHAR),
  PATIENT_CONDITION      VARCHAR2(40 CHAR),
  OPERATION_SCALE        VARCHAR2(64 CHAR),
  ISOLATION_INDICATOR    VARCHAR2(64 CHAR),
  OPERATING_DEPT         VARCHAR2(64),
  SURGEON                VARCHAR2(64),
  FIRST_ASSISTANT        VARCHAR2(64),
  SECOND_ASSISTANT       VARCHAR2(64),
  THIRD_ASSISTANT        VARCHAR2(64),
  FOURTH_ASSISTANT       VARCHAR2(64),
  ANESTHESIA_METHOD      VARCHAR2(64 CHAR),
  ANESTHESIA_DOCTOR      VARCHAR2(64),
  ANESTHESIA_ASSISTANT   VARCHAR2(64),
  BLOOD_TRAN_DOCTOR      VARCHAR2(64),
  FIRST_OPERATION_NURSE  VARCHAR2(64),
  SECOND_OPERATION_NURSE VARCHAR2(64),
  FIRST_SUPPLY_NURSE     VARCHAR2(64),
  SECOND_SUPPLY_NURSE    VARCHAR2(64),
  NOTES_ON_OPERATION     VARCHAR2(500 CHAR),
  ENTERED_BY             VARCHAR2(64 CHAR),
  REQ_DATE_TIME          TIMESTAMP(6),
  ACK_INDICATOR          NUMBER(1),
  DOCTOR_USER            VARCHAR2(64),
  EMERGENCY_INDICATOR    NUMBER(1),
  STATE                  VARCHAR2(1 CHAR),
  OPS_BODY_PART          VARCHAR2(128 CHAR),
  PROVIDE_WAY            VARCHAR2(20 CHAR),
  OPER_STATUS            NUMBER(1),
  REMARKS                VARCHAR2(2000 CHAR),
  UPDATE_BY              VARCHAR2(64),
  CREATE_BY              VARCHAR2(64),
  UPDATE_DATE            TIMESTAMP(6),
  DEL_FLAG               VARCHAR2(2 CHAR),
  CREATE_DATE            TIMESTAMP(6),
  IN_OR_OUT              VARCHAR2(2 CHAR),
  constraint PK_OPERATION_SCHEDULE primary key (ID)
);

-- Add comments to the table 
comment on table OPERATION_SCHEDULE
  is '手术安排';
-- Add comments to the columns 
comment on column OPERATION_SCHEDULE.PATIENT_ID
  is '病人标识号';
comment on column OPERATION_SCHEDULE.VISIT_ID
  is '病人本次住院标识';
comment on column OPERATION_SCHEDULE.CLINIC_ID
  is '就诊ID';
comment on column OPERATION_SCHEDULE.ORG_ID
  is '组织机构';
comment on column OPERATION_SCHEDULE.SCHEDULE_ID
  is '手术安排标识';
comment on column OPERATION_SCHEDULE.DEPT_STAYED
  is '病人所在科室';
comment on column OPERATION_SCHEDULE.BED_NO
  is '病人所在床号';
comment on column OPERATION_SCHEDULE.SCHEDULED_DATE_TIME
  is '手术日期及时间';
comment on column OPERATION_SCHEDULE.OPERATING_ROOM
  is '手术室';
comment on column OPERATION_SCHEDULE.OPERATING_ROOM_NO
  is '手术间';
comment on column OPERATION_SCHEDULE.SEQUENCE
  is '台次';
comment on column OPERATION_SCHEDULE.DIAG_BEFORE_OPERATION
  is '术前主要诊断';
comment on column OPERATION_SCHEDULE.PATIENT_CONDITION
  is '病情说明';
comment on column OPERATION_SCHEDULE.OPERATION_SCALE
  is '手术等级';
comment on column OPERATION_SCHEDULE.ISOLATION_INDICATOR
  is '隔离标志';
comment on column OPERATION_SCHEDULE.OPERATING_DEPT
  is '手术科室';
comment on column OPERATION_SCHEDULE.SURGEON
  is '手术者';
comment on column OPERATION_SCHEDULE.FIRST_ASSISTANT
  is '第一手术助手';
comment on column OPERATION_SCHEDULE.SECOND_ASSISTANT
  is '第二手术助手';
comment on column OPERATION_SCHEDULE.THIRD_ASSISTANT
  is '第三手术助手';
comment on column OPERATION_SCHEDULE.FOURTH_ASSISTANT
  is '第四手术助手';
comment on column OPERATION_SCHEDULE.ANESTHESIA_METHOD
  is '麻醉方法';
comment on column OPERATION_SCHEDULE.ANESTHESIA_DOCTOR
  is '麻醉医师';
comment on column OPERATION_SCHEDULE.ANESTHESIA_ASSISTANT
  is '麻醉助手';
comment on column OPERATION_SCHEDULE.BLOOD_TRAN_DOCTOR
  is '输血者';
comment on column OPERATION_SCHEDULE.FIRST_OPERATION_NURSE
  is '第一台上护士';
comment on column OPERATION_SCHEDULE.SECOND_OPERATION_NURSE
  is '第二台上护士';
comment on column OPERATION_SCHEDULE.FIRST_SUPPLY_NURSE
  is '第一供应护士';
comment on column OPERATION_SCHEDULE.SECOND_SUPPLY_NURSE
  is '第二供应护士';
comment on column OPERATION_SCHEDULE.NOTES_ON_OPERATION
  is '备注';
comment on column OPERATION_SCHEDULE.ENTERED_BY
  is '申请日期及时间';
comment on column OPERATION_SCHEDULE.REQ_DATE_TIME
  is '手术室确认标志';
comment on column OPERATION_SCHEDULE.ACK_INDICATOR
  is '录入者';
comment on column OPERATION_SCHEDULE.OPS_BODY_PART
  is '手术部位';
comment on column OPERATION_SCHEDULE.PROVIDE_WAY
  is '供血方式';
comment on column OPERATION_SCHEDULE.OPER_STATUS
  is '手术状态';
comment on column OPERATION_SCHEDULE.IN_OR_OUT
  is '是否住院';


