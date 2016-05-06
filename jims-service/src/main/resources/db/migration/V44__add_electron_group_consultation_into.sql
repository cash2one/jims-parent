-- drop table ELECTRON_GROUP_CONSULTATION_IN cascade constraints;

/*==============================================================*/
/* Table: ELECTRON_GROUP_CONSULTATION_IN      参与会诊表                  */
/*==============================================================*/



create table ELECTRON_GROUP_CONSULTATION_IN
(
     ID            VARCHAR2(64 CHAR) not null,
     DOCTOR_ID        VARCHAR2(64 CHAR),
  HUIZHEN_ID       VARCHAR2(64 CHAR),
  IN_HUIZHENYIJIAN CLOB,
  CREATE_DATE      TIMESTAMP(6),
  UPDATE_DATE      TIMESTAMP(6),
  CREATE_BY        VARCHAR2(255 CHAR),
  UPDATE_BY        VARCHAR2(255 CHAR),
  QIANMINGSTYPE    VARCHAR2(1 CHAR),
  QIANMINGSHIJIAN  TIMESTAMP(6),
  QIANMING_ID      VARCHAR2(64 CHAR),
  OFFICE_ID        VARCHAR2(64 CHAR),
  DEL_FLAG         NUMBER (1),
   constraint "PK_electron_group_into" primary key (ID)
);

-- Add comments to the table
comment on table ELECTRON_GROUP_CONSULTATION_IN
  is '参与会诊记录';
-- Add comments to the columns
comment on column ELECTRON_GROUP_CONSULTATION_IN.DOCTOR_ID
  is '医生ID';
comment on column ELECTRON_GROUP_CONSULTATION_IN.HUIZHEN_ID
  is '参与会诊ID';
comment on column ELECTRON_GROUP_CONSULTATION_IN.IN_HUIZHENYIJIAN
  is '会诊意见';
comment on column ELECTRON_GROUP_CONSULTATION_IN.QIANMINGSTYPE
  is '签名类型';
comment on column ELECTRON_GROUP_CONSULTATION_IN.QIANMINGSHIJIAN
  is '签名时间';
comment on column ELECTRON_GROUP_CONSULTATION_IN.QIANMING_ID
  is '医生签名';
comment on column ELECTRON_GROUP_CONSULTATION_IN.OFFICE_ID
  is '科室ID';
comment on column ELECTRON_GROUP_CONSULTATION_IN.DEL_FLAG
  is '删除标志';