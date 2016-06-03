/*
drop table ADT_LOG cascade constraints;
*/
/*==============================================================*/
/* Table: ADT_LOG                                               */
/*==============================================================*/
create table ADT_LOG 
(
   ID                   VARCHAR2(64)         not null,
   WARD_CODE            VARCHAR2(8),
   DEPT_CODE            VARCHAR2(8),
   LOG_DATE_TIME        TIMESTAMP            not null,
   PATIENT_ID           VARCHAR2(64),
   ORG_ID               VARCHAR2(64),
   VISIT_ID             VARCHAR2(64),
   ACTION               VARCHAR2(1),
   OPERATOR_NO          VARCHAR2(64),
   constraint PK_ADT_LOG primary key (ID)
   
);

comment on table ADT_LOG is
'病人入出转及状态变化日志';

comment on column ADT_LOG.ID is
'主键';

comment on column ADT_LOG.WARD_CODE is
'病房代码';

comment on column ADT_LOG.DEPT_CODE is
'科室代码';

comment on column ADT_LOG.LOG_DATE_TIME is
'记录日期及时间';

comment on column ADT_LOG.PATIENT_ID is
'病人标识号';

comment on column ADT_LOG.ORG_ID is
'机构标识';

comment on column ADT_LOG.VISIT_ID is
'病人本次住院标识';

comment on column ADT_LOG.ACTION is
'变化';
