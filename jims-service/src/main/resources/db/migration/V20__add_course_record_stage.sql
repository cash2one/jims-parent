-- drop index BINGCHANG_ID;

-- drop table COURSE_RECORD_STAGE cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_STAGE   阶段小结                                */
/*==============================================================*/
create table COURSE_RECORD_STAGE  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   YIBANQINGKUANG       CLOB,
   RUYUANZHENDUAN       CLOB,
   ZHENLIAOJINGGUO      CLOB,
   MUQIANQINGKUANG      CLOB,
   MUQIANZHENDUAN       CLOB,
   ZHENLIAOJIHUA        CLOB,
   CREATE_BY            VARCHAR2(64)                    not null,
   CREATE_DATE          TIMESTAMP                            not null,
   UPDATE_BY            VARCHAR2(64)                    not null,
   UPDATE_DATE          TIMESTAMP                            not null,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1)                        default '0' not null,
   ZHUSU                CLOB,
   LASTTIME             TIMESTAMP,
   NOWTIME              TIMESTAMP
);

comment on table COURSE_RECORD_STAGE is
'阶段小结';

comment on column COURSE_RECORD_STAGE.ID is
'编号';

comment on column COURSE_RECORD_STAGE.BINGCHENG_ID is
'日常病程id';

comment on column COURSE_RECORD_STAGE.YIBANQINGKUANG is
'一般情况';

comment on column COURSE_RECORD_STAGE.RUYUANZHENDUAN is
'入院诊断';

comment on column COURSE_RECORD_STAGE.ZHENLIAOJINGGUO is
'诊疗经过';

comment on column COURSE_RECORD_STAGE.MUQIANQINGKUANG is
'目前情况';

comment on column COURSE_RECORD_STAGE.MUQIANZHENDUAN is
'目前诊断';

comment on column COURSE_RECORD_STAGE.ZHENLIAOJIHUA is
'诊疗计划';

comment on column COURSE_RECORD_STAGE.CREATE_BY is
'创建者';

comment on column COURSE_RECORD_STAGE.CREATE_DATE is
'创建时间';

comment on column COURSE_RECORD_STAGE.UPDATE_BY is
'更新者';

comment on column COURSE_RECORD_STAGE.UPDATE_DATE is
'更新时间';

comment on column COURSE_RECORD_STAGE.REMARKS is
'备注信息';

comment on column COURSE_RECORD_STAGE.DEL_FLAG is
'删除标记';

comment on column COURSE_RECORD_STAGE.ZHUSU is
'主诉';

comment on column COURSE_RECORD_STAGE.LASTTIME is
'上传时间';

comment on column COURSE_RECORD_STAGE.NOWTIME is
'本次时间';

/*==============================================================*/
/* Index: BINGCHANG_ID                                          */
/*==============================================================*/
create index BINGCHANG_ID on COURSE_RECORD_STAGE (
   BINGCHENG_ID ASC
);
