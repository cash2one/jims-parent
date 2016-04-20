drop table COURSE_RECORD cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD      病程记录                                     */
/*==============================================================*/
create table COURSE_RECORD  (
   ID                   VARCHAR2(64)                    not null,
   ZHUYUAN_ID           VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   LURU_SHIJIAN         DATE,
   CANYU_DOCTOR         VARCHAR2(200),
   TYPE                 VARCHAR2(100 CHAR)              not null,
   CREATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(1),
   REMARKS              VARCHAR2(225 CHAR),
   UPDATE_DATE          DATE,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   ZHUYUAN_DEPT         VARCHAR2(100),
   ZHUYUAN_BINGQ        VARCHAR2(100 CHAR),
   BING_CH              VARCHAR2(50 CHAR),
   ZHUYUAN_CHANGETIME   DATE,
   LURU_DATETIME        DATE,
   constraint "PK_course_record" primary key (ID)
);

comment on table COURSE_RECORD is
'病程记录';

comment on column COURSE_RECORD.ID is
'主键';

comment on column COURSE_RECORD.ZHUYUAN_ID is
'住院ID';

comment on column COURSE_RECORD.PATIENT_ID is
'病人ID';

comment on column COURSE_RECORD.LURU_SHIJIAN is
'录入时间';

comment on column COURSE_RECORD.CANYU_DOCTOR is
'参与医生';

comment on column COURSE_RECORD.TYPE is
'病程类型';

comment on column COURSE_RECORD.CREATE_DATE is
'记录时间';

comment on column COURSE_RECORD.ZHUYUAN_DEPT is
'住院科别';

comment on column COURSE_RECORD.ZHUYUAN_BINGQ is
'住院病区';

comment on column COURSE_RECORD.BING_CH is
'病床号';

comment on column COURSE_RECORD.LURU_DATETIME is
'记录病程录入时间';
