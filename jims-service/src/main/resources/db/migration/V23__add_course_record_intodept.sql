drop index RICHANGBINGLI_ID;

drop table COURSE_RECORD_INTODEPT cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_INTODEPT   转科转入                             */
/*==============================================================*/
create table COURSE_RECORD_INTODEPT  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   ZHUSHU               CLOB,
   RUYUANQINGKUANG      CLOB,
   RUYUANZHENDUAN       CLOB,
   ZHENLIAOJINGGUO      CLOB,
   MUQIANQINGKUANG      CLOB,
   MUQIANZHENDUAN       CLOB,
   ZHENLIAOJIHUA        CLOB,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1),
   constraint "PK_course_record_intodept" primary key (ID, BINGCHENG_ID)
);

comment on table COURSE_RECORD_INTODEPT is
'转科转入';

comment on column COURSE_RECORD_INTODEPT.ID is
'主键';

comment on column COURSE_RECORD_INTODEPT.BINGCHENG_ID is
'日常病程ID';

comment on column COURSE_RECORD_INTODEPT.ZHUSHU is
'主诉';

comment on column COURSE_RECORD_INTODEPT.RUYUANQINGKUANG is
'入院情况';

comment on column COURSE_RECORD_INTODEPT.RUYUANZHENDUAN is
'入院诊断';

comment on column COURSE_RECORD_INTODEPT.ZHENLIAOJINGGUO is
'诊疗经过';

comment on column COURSE_RECORD_INTODEPT.MUQIANQINGKUANG is
'目前情况';

comment on column COURSE_RECORD_INTODEPT.MUQIANZHENDUAN is
'目前诊断';

comment on column COURSE_RECORD_INTODEPT.ZHENLIAOJIHUA is
'诊疗计划';

/*==============================================================*/
/* Index: RICHANGBINGLI_ID                                      */
/*==============================================================*/
create index RICHANGBINGLI_ID on COURSE_RECORD_INTODEPT (
   BINGCHENG_ID ASC
);
