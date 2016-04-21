drop index BINGCHENG_ID2;

drop table COURSE_RECORD_INTRACTABLECASE cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_INTRACTABLECASE     疑难病例讨论                      */
/*==============================================================*/
create table COURSE_RECORD_INTRACTABLECASE  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   CONTENT              CLOB,
   JILUSHIJIAN          TIMESTAMP,
   CREATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR2(1),
   CANJIARENYUAN        CLOB,
   REMARKS              VARCHAR2(255 CHAR),
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   TAOLUNDIDIAN         VARCHAR2(100),
   JILUREN              VARCHAR2(64),
   ZHUCHIREN            VARCHAR2(64),
   BINGANHAO            VARCHAR2(64),
   BINGQU               VARCHAR2(64),
   KESHI                VARCHAR2(64),
   JINGZHIYISHI         VARCHAR2(64),
   TAOLUNYIJIAN         CLOB,
   ZHICHIRENYIJIAN      CLOB,
   TAOLUNSHIJIAN        TIMESTAMP,
   ZHUCHIRENZHICHENG    VARCHAR2(64),
   BINGQINGJIESHAO      CLOB,
   TAOLUNMUDI           CLOB,
   constraint "PK_course_record_intractablecase" primary key (ID)
);

comment on table COURSE_RECORD_INTRACTABLECASE is
'疑难病例讨论';

comment on column COURSE_RECORD_INTRACTABLECASE.CANJIARENYUAN is
'参加人员名单';

comment on column COURSE_RECORD_INTRACTABLECASE.TAOLUNDIDIAN is
'讨论地点';

comment on column COURSE_RECORD_INTRACTABLECASE.JILUREN is
'记录人';

comment on column COURSE_RECORD_INTRACTABLECASE.ZHUCHIREN is
'主持人';

comment on column COURSE_RECORD_INTRACTABLECASE.ZHUCHIRENZHICHENG is
'主持人职称';

comment on column COURSE_RECORD_INTRACTABLECASE.BINGQINGJIESHAO is
'病情介绍';

comment on column COURSE_RECORD_INTRACTABLECASE.TAOLUNMUDI is
'讨论目的';

/*==============================================================*/
/* Index: BINGCHENG_ID2                                         */
/*==============================================================*/
create index BINGCHENG_ID2 on COURSE_RECORD_INTRACTABLECASE (
   BINGCHENG_ID ASC
);
