/*==============================================================*/
/* Table: CLINIC_FOR_REGIST              门诊号表       */
/* CREATE_BY :  zhangyao                                            */
/*==============================================================*/
create table CLINIC_FOR_REGIST
(
   ID                   VARCHAR2(64),
   ORG_ID               VARCHAR2(64),
   CLINIC_DATE          DATE                 not null,
   CLINIC_LABEL         VARCHAR2(64)         not null,
   TIME_DESC            VARCHAR2(64)         not null,
   REGISTRATION_LIMITS  NUMBER(4),
   APPOINTMENT_LIMITS   NUMBER(4),
   CURRENT_NO           NUMBER(4),
   REGISTRATION_NUM     NUMBER(4),
   APPOINTMENT_NUM      NUMBER(4),
   REGIST_PRICE         NUMBER(12,2),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char                 default '0'
);

comment on column CLINIC_FOR_REGIST.ID is
'主键';

comment on column CLINIC_FOR_REGIST.ORG_ID is
'所属组织';

comment on column CLINIC_FOR_REGIST.CLINIC_DATE is
'门诊日期';

comment on column CLINIC_FOR_REGIST.CLINIC_LABEL is
'号别';

comment on column CLINIC_FOR_REGIST.TIME_DESC is
'门诊时间描述';

comment on column CLINIC_FOR_REGIST.REGISTRATION_LIMITS is
'限号数';

comment on column CLINIC_FOR_REGIST.APPOINTMENT_LIMITS is
'限预约号数';

comment on column CLINIC_FOR_REGIST.CURRENT_NO is
'当前号';

comment on column CLINIC_FOR_REGIST.REGISTRATION_NUM is
'当日已挂号数';

comment on column CLINIC_FOR_REGIST.APPOINTMENT_NUM is
'已预约号数';

comment on column CLINIC_FOR_REGIST.REGIST_PRICE is
'挂号费标准：空';

comment on column CLINIC_FOR_REGIST.CREATE_DATE is
'创建日期';

comment on column CLINIC_FOR_REGIST.CREATE_BY is
'创建人';

comment on column CLINIC_FOR_REGIST.UPDATE_DATE is
'更新日期';

comment on column CLINIC_FOR_REGIST.UPDATE_BY is
'更新人';

comment on column CLINIC_FOR_REGIST.REMARKS is
'备注信息';

comment on column CLINIC_FOR_REGIST.DEL_FLAG is
'删除标志';
