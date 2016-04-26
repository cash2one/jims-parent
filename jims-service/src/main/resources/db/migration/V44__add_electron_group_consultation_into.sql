-- drop table ELECTRON_GROUP_CONSULTATION_IN cascade constraints;

/*==============================================================*/
/* Table: ELECTRON_GROUP_CONSULTATION_IN      参与会诊表                  */
/*==============================================================*/



create table ELECTRON_GROUP_CONSULTATION_IN
(
   ID                   VARCHAR2(64)         not null,
   DOCTOR_ID            VARCHAR2(64),
   HUIZHEN_ID           VARCHAR2(64),
   HUIZHENYIJIAN        CLOB,
   CREATE_DATE          TIMESTAMP,
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   QIANMINGSTYPE        VARCHAR2(1),
   QIANMINGSHIJIAN      TIMESTAMP,
   QIANMING_ID          VARCHAR2(64),
   constraint "PK_electron_group_into" primary key (ID)
);

comment on table ELECTRON_GROUP_CONSULTATION_IN is
'参与会诊记录';

comment on column ELECTRON_GROUP_CONSULTATION_IN.DOCTOR_ID is
'医生ID';

comment on column ELECTRON_GROUP_CONSULTATION_IN.HUIZHEN_ID is
'参与会诊ID';

comment on column ELECTRON_GROUP_CONSULTATION_IN.HUIZHENYIJIAN is
'会诊意见';

comment on column ELECTRON_GROUP_CONSULTATION_IN.QIANMINGSTYPE is
'签名类型';

comment on column ELECTRON_GROUP_CONSULTATION_IN.QIANMINGSHIJIAN is
'签名时间';

comment on column ELECTRON_GROUP_CONSULTATION_IN.QIANMING_ID is
'医生签名';