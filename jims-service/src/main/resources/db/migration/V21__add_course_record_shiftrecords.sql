drop table COURSE_RECORD_SHIFTRECORDS cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_SHIFTRECORDS    交班记录                        */
/*==============================================================*/
create table COURSE_RECORD_SHIFTRECORDS  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   JILURIQI             TIMESTAMP,
   ZHUSU                CLOB,
   RUYUANQINGKUANG      CLOB,
   RUYUANZHENDUAN       CLOB,
   ZHENLIAOJINGGUO      CLOB,
   MUQIANQINGKUANG      CLOB,
   MUQIANZHENDUAN       CLOB,
   ZHUYISHIXIANG        CLOB,
   CREATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR2(1),
   REMARKS              VARCHAR2(225 CHAR),
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   constraint "PK_course_record_shiftrecords" primary key (ID)
);

comment on table COURSE_RECORD_SHIFTRECORDS is
'交班记录';

comment on column COURSE_RECORD_SHIFTRECORDS.ZHUSU is
'主诉';

comment on column COURSE_RECORD_SHIFTRECORDS.RUYUANQINGKUANG is
'入院情况';

comment on column COURSE_RECORD_SHIFTRECORDS.RUYUANZHENDUAN is
'入院诊断';

comment on column COURSE_RECORD_SHIFTRECORDS.ZHENLIAOJINGGUO is
'诊疗经过';

comment on column COURSE_RECORD_SHIFTRECORDS.MUQIANQINGKUANG is
'目前情况';

comment on column COURSE_RECORD_SHIFTRECORDS.ZHUYISHIXIANG is
'住院事项';
