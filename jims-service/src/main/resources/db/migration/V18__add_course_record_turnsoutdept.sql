drop table COURSE_RECORD_TURNSOUTDEPT cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_TURNSOUTDEPT    转科（转出）记录                         */
/*==============================================================*/
create table COURSE_RECORD_TURNSOUTDEPT  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   RIQI                 TIMESTAMP,
   ZHUSU                CLOB,
   RUYUANQINGKUANG      CLOB,
   RUYUANZHENDUAN       CLOB,
   ZHENLIAOJINGGUO      CLOB,
   MUQIANQINGKUANG      CLOB,
   MUQIANZHENDUAN       CLOB,
   ZHUANKEMUDI          CLOB,
   ZHUYISHIXIANG        CLOB,
   SHIJIAN              TIMESTAMP,
   CREATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR2(1),
   REMARKS              VARCHAR2(255 CHAR),
   UPDATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(255),
   UPDATE_BY            VARCHAR2(255),
   constraint "PK_course_record_turnsoutdept" primary key (ID)
);

comment on table COURSE_RECORD_TURNSOUTDEPT is
'转科（转出）记录';

comment on column COURSE_RECORD_TURNSOUTDEPT.RIQI is
'日期';

comment on column COURSE_RECORD_TURNSOUTDEPT.ZHUSU is
'主诉';

comment on column COURSE_RECORD_TURNSOUTDEPT.RUYUANQINGKUANG is
'入院情况';

comment on column COURSE_RECORD_TURNSOUTDEPT.RUYUANZHENDUAN is
'入院诊断';

comment on column COURSE_RECORD_TURNSOUTDEPT.ZHENLIAOJINGGUO is
'诊疗经过';

comment on column COURSE_RECORD_TURNSOUTDEPT.MUQIANQINGKUANG is
'目前情况';

comment on column COURSE_RECORD_TURNSOUTDEPT.MUQIANZHENDUAN is
'目前诊断';

comment on column COURSE_RECORD_TURNSOUTDEPT.ZHUYISHIXIANG is
'注意事项';

comment on column COURSE_RECORD_TURNSOUTDEPT.SHIJIAN is
'时间';

comment on column COURSE_RECORD_TURNSOUTDEPT.CREATE_DATE is
'记录时间';
