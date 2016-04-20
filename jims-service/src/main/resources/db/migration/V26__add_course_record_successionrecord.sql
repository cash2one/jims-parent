drop table COURSE_RECORD_SUCCESSIONRECORD cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_SUCCESSIONRECORD     接班记录                   */
/*==============================================================*/
create table COURSE_RECORD_SUCCESSIONRECORD  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   RIQI                 DATE,
   JIEBANQINGKUANG      CLOB,
   JIEBANZHENLIAO       CLOB,
   CREATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(1),
   REMARKS              VARCHAR2(255 CHAR),
   UPDATE_DATE          DATE,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   constraint "PK_course_record_successionrecords" primary key (ID)
);

comment on table COURSE_RECORD_SUCCESSIONRECORD is
'接班记录';

comment on column COURSE_RECORD_SUCCESSIONRECORD.RIQI is
'日期';

comment on column COURSE_RECORD_SUCCESSIONRECORD.JIEBANQINGKUANG is
'接班情况';

comment on column COURSE_RECORD_SUCCESSIONRECORD.JIEBANZHENLIAO is
'接班诊疗计划';

comment on column COURSE_RECORD_SUCCESSIONRECORD.CREATE_DATE is
'记录时间';
