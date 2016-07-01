-- Create table
/*==============================================================*/
/* Table: mr_on_line	联机病历描述                                      */
/*==============================================================*/

create table MR_ON_LINE
(
  ID                VARCHAR2(64 CHAR) not null,
  PATIENT_ID        VARCHAR2(64 CHAR) ,
  STATUS            VARCHAR2(1 CHAR),
  REQUEST_DOCTOR_ID VARCHAR2(64 CHAR) not null,
  REQUEST_DATE_TIME TIMESTAMP(6),
  SUPER_DOCTOR_ID   VARCHAR2(64 CHAR),
  PARENT_DOCTOR_ID  VARCHAR2(64 CHAR),
  OTHER_DOCTOR_ID   VARCHAR2(64 CHAR),
  REMARKS         VARCHAR2(2000 CHAR),
  UPDATE_BY       VARCHAR2(64 CHAR),
  CREATE_BY       VARCHAR2(64  CHAR),
  UPDATE_DATE     TIMESTAMP(6),
  DEL_FLAG        VARCHAR2(2 CHAR),
  CREATE_DATE     TIMESTAMP(6)
);
-- Add comments to the table 
comment on table MR_ON_LINE
  is '联机病历描述';
-- Add comments to the columns
comment on column MR_ON_LINE.ID
  is '联机病历描述ID';
comment on column MR_ON_LINE.PATIENT_ID
  is '病人ID';
comment on column MR_ON_LINE.STATUS
  is '病历状态';
comment on column MR_ON_LINE.REQUEST_DOCTOR_ID
  is '请求医生';
comment on column MR_ON_LINE.REQUEST_DATE_TIME
  is '请求时间';
comment on column MR_ON_LINE.SUPER_DOCTOR_ID
  is '主任医师';
comment on column MR_ON_LINE.PARENT_DOCTOR_ID
  is '上级医师';
