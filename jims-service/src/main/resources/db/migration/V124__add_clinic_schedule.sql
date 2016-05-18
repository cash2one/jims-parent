/*==============================================================*/
/* Table: CLINIC_SCHEDULE              门诊安排       */
/* CREATE_BY :  zhangyao                                            */
/*==============================================================*/
create table CLINIC_SCHEDULE
(
   ID                   VARCHAR2(64),
   ORG_ID               VARCHAR2(64),
   CLINIC_LABEL         VARCHAR2(64)         not null,
   DAY_OF_WEEK          VARCHAR2(64)         not null,
   TIME_DESC            VARCHAR2(64)         not null,
   REGISTRATION_LIMITS  NUMBER(4),
   APPOINTMENT_LIMITS   NUMBER(4),
   PHONE_LIMITS         NUMBER(4),
   WEB_LIMITS           NUMBER(4),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char                 default '0'
);

comment on table CLINIC_SCHEDULE is
'此表以周为描述单位，反映每日门诊出诊安排情况。由该表可以生成每日的实际号表记录。唯一约束：号别、星期、门诊时间描述。星期字典DAY_OF_WEEK_DICT、时间段字典TIME_INTERVAL_DICT 放入字典表';
comment on column CLINIC_SCHEDULE.ID is
'主键';

comment on column CLINIC_SCHEDULE.ORG_ID is
'所属组织';

comment on column CLINIC_SCHEDULE.CLINIC_LABEL is
'号别';

comment on column CLINIC_SCHEDULE.DAY_OF_WEEK is
'星期';

comment on column CLINIC_SCHEDULE.TIME_DESC is
'门诊时间描述';

comment on column CLINIC_SCHEDULE.REGISTRATION_LIMITS is
'限号数';

comment on column CLINIC_SCHEDULE.APPOINTMENT_LIMITS is
'限预约号数';

comment on column CLINIC_SCHEDULE.PHONE_LIMITS is
'电话预约号数';

comment on column CLINIC_SCHEDULE.WEB_LIMITS is
'网上预约号数';

comment on column CLINIC_SCHEDULE.CREATE_DATE is
'创建日期';

comment on column CLINIC_SCHEDULE.CREATE_BY is
'创建人';

comment on column CLINIC_SCHEDULE.UPDATE_DATE is
'更新日期';

comment on column CLINIC_SCHEDULE.UPDATE_BY is
'更新人';

comment on column CLINIC_SCHEDULE.REMARKS is
'备注信息';

comment on column CLINIC_SCHEDULE.DEL_FLAG is
'删除标志';
