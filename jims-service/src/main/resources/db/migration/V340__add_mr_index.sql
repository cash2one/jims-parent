-- Create table
/*==============================================================*/
/* Table: MR_INDEX 病案索引                                      */
/*==============================================================*/

create table MR_INDEX
(
  ID                    VARCHAR2(64 CHAR) not null,
  PATIENT_ID            VARCHAR2(64 CHAR),
  MR_STATUS             VARCHAR2(1 CHAR),
  STORAGE_VOLUME_LABEL  VARCHAR2(32 CHAR),
  ACCESS_PATH           VARCHAR2(40 CHAR),
  LAST_ACCESS_DATE_TIME TIMESTAMP(6),
  LAST_ACCESS_USER      VARCHAR2(20 CHAR),
  REMARKS         VARCHAR2(2000 CHAR),
  UPDATE_BY       VARCHAR2(64),
  CREATE_BY       VARCHAR2(64),
  UPDATE_DATE     TIMESTAMP(6),
  DEL_FLAG        VARCHAR2(2 CHAR),
  CREATE_DATE     TIMESTAMP(6)
);
-- Add comments to the table
comment on table MR_INDEX
  is '病案索引';
-- Add comments to the columns
comment on column MR_INDEX.ID
  is '病案索引主键';
comment on column MR_INDEX.PATIENT_ID
  is '病人ID';
comment on column MR_INDEX.MR_STATUS
  is '病案状态';
comment on column MR_INDEX.STORAGE_VOLUME_LABEL
  is '卷标';
comment on column MR_INDEX.ACCESS_PATH
  is '访问路径';
comment on column MR_INDEX.LAST_ACCESS_DATE_TIME
  is '最近访问时间';
