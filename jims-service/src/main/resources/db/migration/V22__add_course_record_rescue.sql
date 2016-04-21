drop index BINGCHANG_ID4;

drop table COURSE_RECORD_RESCUE cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_RESCUE       抢救记录                            */
/*==============================================================*/
create table COURSE_RECORD_RESCUE  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   YIBANQINGKUANG       CLOB,
   RUYUANZHENDUAN       CLOB,
   BIINGQINGBIANHUAQINGKUANG CLOB,
   CHUBUZHENDUAN        CLOB,
   QIANGJIUJINGGUO      CLOB,
   CANJIARENYUAN        VARCHAR2(200),
   CREATE_BY            VARCHAR2(64)                    not null,
   CREATE_DATE          TIMESTAMP                            not null,
   UPDATE_BY            VARCHAR2(64)                    not null,
   UPDATE_DATE          TIMESTAMP                            not null,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1)                        default '0' not null,
   QIANGJIUSHIJIAN      TIMESTAMP
);

comment on table COURSE_RECORD_RESCUE is
'抢救记录';

comment on column COURSE_RECORD_RESCUE.ID is
'编号';

comment on column COURSE_RECORD_RESCUE.BINGCHENG_ID is
'日常病程id';

comment on column COURSE_RECORD_RESCUE.YIBANQINGKUANG is
'一般情况';

comment on column COURSE_RECORD_RESCUE.RUYUANZHENDUAN is
'入院诊断';

comment on column COURSE_RECORD_RESCUE.BIINGQINGBIANHUAQINGKUANG is
'病情变化情况';

comment on column COURSE_RECORD_RESCUE.CHUBUZHENDUAN is
'初步诊断';

comment on column COURSE_RECORD_RESCUE.QIANGJIUJINGGUO is
'抢救经过';

comment on column COURSE_RECORD_RESCUE.CANJIARENYUAN is
'参加人员';

comment on column COURSE_RECORD_RESCUE.CREATE_BY is
'创建者';

comment on column COURSE_RECORD_RESCUE.CREATE_DATE is
'创建时间';

comment on column COURSE_RECORD_RESCUE.UPDATE_BY is
'更新者';

comment on column COURSE_RECORD_RESCUE.UPDATE_DATE is
'更新时间';

comment on column COURSE_RECORD_RESCUE.REMARKS is
'备注信息';

comment on column COURSE_RECORD_RESCUE.DEL_FLAG is
'删除标记';

comment on column COURSE_RECORD_RESCUE.QIANGJIUSHIJIAN is
'抢救时间';

/*==============================================================*/
/* Index: BINGCHANG_ID4                                         */
/*==============================================================*/
create index BINGCHANG_ID4 on COURSE_RECORD_RESCUE (
   BINGCHENG_ID ASC
);
