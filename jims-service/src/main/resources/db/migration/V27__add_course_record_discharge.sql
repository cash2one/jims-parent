drop index BINGCHANG_ID3;

drop table COURSE_RECORD_DISCHARGE cascade constraints;

/*==============================================================*/
/* Table: COURSE_RECORD_DISCHARGE      出院记录                         */
/*==============================================================*/
create table COURSE_RECORD_DISCHARGE  (
   ID                   VARCHAR2(64)                    not null,
   BINGCHENG_ID         VARCHAR2(64)                    not null,
   CHUYUANRIQI          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(225 CHAR),
   DEL_FLAG             CHAR(1)                        default '0'
);

comment on table COURSE_RECORD_DISCHARGE is
'出院记录';

comment on column COURSE_RECORD_DISCHARGE.ID is
'编号';

comment on column COURSE_RECORD_DISCHARGE.BINGCHENG_ID is
'日常病程id';

comment on column COURSE_RECORD_DISCHARGE.CHUYUANRIQI is
'出院日期';

comment on column COURSE_RECORD_DISCHARGE.CREATE_BY is
'创建者';

comment on column COURSE_RECORD_DISCHARGE.CREATE_DATE is
'创建时间';

comment on column COURSE_RECORD_DISCHARGE.UPDATE_BY is
'更新者';

comment on column COURSE_RECORD_DISCHARGE.UPDATE_DATE is
'更新时间';

comment on column COURSE_RECORD_DISCHARGE.REMARKS is
'备注信息';

comment on column COURSE_RECORD_DISCHARGE.DEL_FLAG is
'删除标记';

/*==============================================================*/
/* Index: BINGCHANG_ID3                                         */
/*==============================================================*/
create index BINGCHANG_ID3 on COURSE_RECORD_DISCHARGE (
   BINGCHENG_ID ASC
);
