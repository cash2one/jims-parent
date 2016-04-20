drop table COURSE_RECORD_DEATH cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_DEATH     死亡记录                              */
/*==============================================================*/
create table COURSE_RECORD_DEATH  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   RUYUANSHIJIAN        DATE,
   SIWANGSHIJIAN        DATE,
   RUANYUANZHENDUAN     CLOB,
   RUYUANQINGKUANG      CLOB,
   ZHENLIAOJINGGUO      CLOB,
   SIWANGYUANYIN        CLOB,
   SIWANGZHENDUAN       CLOB,
   JIASHUSHIFOU         VARCHAR2(64),
   CREATE_DATE          DATE,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          DATE,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1)                        default '0'
);

comment on table COURSE_RECORD_DEATH is
'死亡记录';

comment on column COURSE_RECORD_DEATH.ID is
'编号';

comment on column COURSE_RECORD_DEATH.BINGCHENG_ID is
'日常病程id';

comment on column COURSE_RECORD_DEATH.RUYUANSHIJIAN is
'入院时间';

comment on column COURSE_RECORD_DEATH.SIWANGSHIJIAN is
'死亡时间';

comment on column COURSE_RECORD_DEATH.RUANYUANZHENDUAN is
'入院诊断';

comment on column COURSE_RECORD_DEATH.RUYUANQINGKUANG is
'入院情况';

comment on column COURSE_RECORD_DEATH.ZHENLIAOJINGGUO is
'诊疗经过';

comment on column COURSE_RECORD_DEATH.SIWANGYUANYIN is
'死亡原因';

comment on column COURSE_RECORD_DEATH.SIWANGZHENDUAN is
'死亡诊断';

comment on column COURSE_RECORD_DEATH.JIASHUSHIFOU is
'家属是否同意尸体解剖';

comment on column COURSE_RECORD_DEATH.CREATE_DATE is
'创建时间';

comment on column COURSE_RECORD_DEATH.UPDATE_BY is
'更新者';

comment on column COURSE_RECORD_DEATH.UPDATE_DATE is
'更新时间';

comment on column COURSE_RECORD_DEATH.REMARKS is
'备注信息';

comment on column COURSE_RECORD_DEATH.DEL_FLAG is
'删除标记';
