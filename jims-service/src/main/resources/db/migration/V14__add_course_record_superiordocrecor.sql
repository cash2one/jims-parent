drop index BINGCHENG_ID;

drop table COURSE_RECORD_SUPERIORDOCRECOR cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_SUPERIORDOCRECOR   上级医师查房记录                       */
/*==============================================================*/
create table COURSE_RECORD_SUPERIORDOCRECOR  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   CONTENT              CLOB,
   JILUSHIJIAN          TIMESTAMP,
   CREATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR2(1),
   REMARKS              VARCHAR2(255 CHAR),
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   constraint "PK_course_record_superiordocrecord" primary key (ID)
);

comment on table COURSE_RECORD_SUPERIORDOCRECOR is
'上级医师查房记录';

/*==============================================================*/
/* Index: BINGCHENG_ID                                          */
/*==============================================================*/
create index BINGCHENG_ID on COURSE_RECORD_SUPERIORDOCRECOR (
   BINGCHENG_ID ASC
);
