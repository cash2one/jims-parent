/*
drop table TRANSFER cascade constraints;
*/

/*==============================================================*/
/* Table: TRANSFER                                              */
/*==============================================================*/
create table TRANSFER 
(
   ID                   VARCHAR2(64)         not null,
   PATIENT_ID           VARCHAR2(64)       ,
   ORG_ID               VARCHAR2(64)         ,
   VISIT_ID             VARCHAR2(64)         ,
   DEPT_STAYED          VARCHAR2(8),
   ADMISSION_DATE_TIME  TIMESTAMP                 not null,
   DISCHARGE_DATE_TIME  TIMESTAMP,
   DEPT_TRANSFERED_TO   VARCHAR2(8),
   DOCTOR_IN_CHARGE     VARCHAR2(20),
   DEPT_CODE_LEND       VARCHAR2(8),
   TRANS_FLAG           VARCHAR2(1),
   YWLSH                VARCHAR2(20),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200),
   DEL_FLAG             CHAR(1) DEFAULT '0',
   constraint PK_TRANSFER primary key (ID)
);

comment on table TRANSFER is
'病人在科记录';

comment on column TRANSFER.PATIENT_ID is
'病人标识';

comment on column TRANSFER.ORG_ID is
'组织机构标识';

comment on column TRANSFER.VISIT_ID is
'病人本次住院标识';

comment on column TRANSFER.DEPT_STAYED is
'所在科室';

comment on column TRANSFER.ADMISSION_DATE_TIME is
'入科日期及时间';

comment on column TRANSFER.DISCHARGE_DATE_TIME is
'出科日期及时间';

comment on column TRANSFER.DEPT_TRANSFERED_TO is
'转向科室';

comment on column TRANSFER.DOCTOR_IN_CHARGE is
'经治医师';

comment on column TRANSFER.TRANS_FLAG is
'医保上传标记';

comment on column TRANSFER.YWLSH is
'医保业务流水号';

comment on column TRANSFER.CREATE_DATE is
'创建日期';

comment on column TRANSFER.CREATE_BY is
'创建人';

comment on column TRANSFER.UPDATE_DATE is
'更新时间';

comment on column TRANSFER.UPDATE_BY is
'更新人';

comment on column TRANSFER.REMARKS is
'备注';

comment on column TRANSFER.DEL_FLAG is
'是否删除';
