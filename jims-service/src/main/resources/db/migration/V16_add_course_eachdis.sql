drop table COURSE_RECORD_EACHDIS cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_EACHDIS       日常病程记录                           */
/*==============================================================*/
create table COURSE_RECORD_EACHDIS  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64),
   BINGCHENGJILUTIME    DATE,
   CONTENT              CLOB,
   DOC_QIANMING         VARCHAR2(64),
   CREATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(1),
   REMARKS              VARCHAR2(100 CHAR),
   UPDATE_DATE          DATE,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   constraint "PK_course_record_eachdis" primary key (ID)
);

comment on table COURSE_RECORD_EACHDIS is
'日常病程记录';

comment on column COURSE_RECORD_EACHDIS.CONTENT is
'内容';

comment on column COURSE_RECORD_EACHDIS.DOC_QIANMING is
'医师签名';
