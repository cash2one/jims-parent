-- drop index BINGCHANG_ID2;

-- drop table COURSE_RECORD_CASEDISCUS cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_CASEDISCUS  死亡病例讨论记录                             */
/*==============================================================*/
create table COURSE_RECORD_CASEDISCUS  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   TAOLUNRIQI           TIMESTAMP,
   TAOLUNDIDIAN         CLOB,
   TAOLUNMUDI           CLOB,
   CANJIARENYUAN        CLOB,
   SIWANGYUANYIN        CLOB,
   SIWANGZHENDUAN       CLOB,
   JINGZHIYISHI         CLOB,
   TAOLUNJILU           CLOB,
   ZHUCHIRENZONG        CLOB,
   CREATE_DATE          CLOB,
   UPDATE_BY            CLOB,
   UPDATE_DATE          TIMESTAMP,
   REMARKS              CLOB,
   DEL_FLAG             CHAR(1)                        default '0',
   ZHUCHIRENZHICHENG    VARCHAR2(64),
   ZHUCHIREN            VARCHAR2(64),
   constraint "PK_course_record_casediscus" primary key (ID)
);

comment on table COURSE_RECORD_CASEDISCUS is
'死亡病例讨论记录';

comment on column COURSE_RECORD_CASEDISCUS.ID is
'编号';

comment on column COURSE_RECORD_CASEDISCUS.BINGCHENG_ID is
'日常病程id';

comment on column COURSE_RECORD_CASEDISCUS.TAOLUNRIQI is
'讨论日期';

comment on column COURSE_RECORD_CASEDISCUS.TAOLUNDIDIAN is
'讨论地点';

comment on column COURSE_RECORD_CASEDISCUS.TAOLUNMUDI is
'讨论目的';

comment on column COURSE_RECORD_CASEDISCUS.CANJIARENYUAN is
'参加人员名单';

comment on column COURSE_RECORD_CASEDISCUS.SIWANGYUANYIN is
'死亡原因';

comment on column COURSE_RECORD_CASEDISCUS.SIWANGZHENDUAN is
'死亡诊断';

comment on column COURSE_RECORD_CASEDISCUS.JINGZHIYISHI is
'经治医师汇报病情';

comment on column COURSE_RECORD_CASEDISCUS.TAOLUNJILU is
'讨论记录';

comment on column COURSE_RECORD_CASEDISCUS.ZHUCHIRENZONG is
'主持人总结';

comment on column COURSE_RECORD_CASEDISCUS.CREATE_DATE is
'创建时间';

comment on column COURSE_RECORD_CASEDISCUS.UPDATE_BY is
'更新者';

comment on column COURSE_RECORD_CASEDISCUS.UPDATE_DATE is
'更新时间';

comment on column COURSE_RECORD_CASEDISCUS.REMARKS is
'备注信息';

comment on column COURSE_RECORD_CASEDISCUS.DEL_FLAG is
'删除标记';

comment on column COURSE_RECORD_CASEDISCUS.ZHUCHIRENZHICHENG is
'主持人职称';

comment on column COURSE_RECORD_CASEDISCUS.ZHUCHIREN is
'主持人';

/*==============================================================*/
/* Index: BINGCHANG_ID2                                         */
/*==============================================================*/
create index BINGCHANG_ID2 on COURSE_RECORD_CASEDISCUS (
   BINGCHENG_ID ASC
);
