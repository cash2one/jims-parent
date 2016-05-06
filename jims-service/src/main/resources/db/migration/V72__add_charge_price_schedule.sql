-- drop table CHARGE_PRICE_SCHEDULE cascade constraints;

/*==============================================================*/
/* Table: CHARGE_PRICE_SCHEDULE    收费系数                    */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table CHARGE_PRICE_SCHEDULE 
(
   ID                   VARCHAR2(64 char)    not null,
   HOSPITAL_ID          CHAR(10),
   CHARGE_TYPE          CHAR(8),
   PRICE_COEFF_NUMERATOR NUMBER(11,2)         not null,
   PRICE_COEFF_DENOMINATOR NUMBER(11,2)         not null,
   CHARGE_SPECIAL_INDICATOR NUMBER(11,1),
   NUMERATOR_OUTP       NUMBER(11,2),
   DENOMINATOR_OUTP     NUMBER(11,1),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 char),
   UUPDATE_DATE         TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_CHARGE_PRICE_SCHEDULE primary key (ID)
  );
comment on table CHARGE_PRICE_SCHEDULE is
'收费系数';

comment on column CHARGE_PRICE_SCHEDULE.ID is
'主键';

comment on column CHARGE_PRICE_SCHEDULE.CHARGE_TYPE is
'费别';

comment on column CHARGE_PRICE_SCHEDULE.PRICE_COEFF_NUMERATOR is
'收费系数分子(住院)';

comment on column CHARGE_PRICE_SCHEDULE.PRICE_COEFF_DENOMINATOR is
'收费系数分母(住院)';

comment on column CHARGE_PRICE_SCHEDULE.CHARGE_SPECIAL_INDICATOR is
'适用特殊收适用特殊收费项目标志';

comment on column CHARGE_PRICE_SCHEDULE.NUMERATOR_OUTP is
'收费系数分子(门诊)';

comment on column CHARGE_PRICE_SCHEDULE.DENOMINATOR_OUTP is
'收费系数分母(分母)';

comment on column CHARGE_PRICE_SCHEDULE.CREATE_BY is
'创建人';

comment on column CHARGE_PRICE_SCHEDULE.CREATE_DATE is
'创建时间';

comment on column CHARGE_PRICE_SCHEDULE.UPDATE_BY is
'修改人';

comment on column CHARGE_PRICE_SCHEDULE.UUPDATE_DATE is
'修改时间';

comment on column CHARGE_PRICE_SCHEDULE.DEL_FLAG is
'删除标记';

comment on column CHARGE_PRICE_SCHEDULE.REMARKS is
'备注信息';
-- drop table CHARGE_PRICE_SCHEDULE cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: CHARGE_PRICE_SCHEDULE      �շ�ϵ��
/* CREATE_DATE: 2016-05-05                                */
/* CREATE_BY :  zp      
/*==============================================================*/
create table CHARGE_PRICE_SCHEDULE 
(
   ID                   varchar2(64 char)              not null,
   HOSPITAL_ID          char(10)                       null,
   CHARGE_TYPE          char(8)                        null,
   PRICE_COEFF_NUMERATOR number(11,2)                   not null,
   PRICE_COEFF_DENOMINATOR number(11,2)                   not null,
   CHARGE_SPECIAL_INDICATOR number(11,1)                   null,
   NUMERATOR_OUTP       number(11,2)                   null,
   DENOMINATOR_OUTP     number(11,1)                   null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                           null,
   UPDATE_BY            varchar2(64 char)              null,
   UUPDATE_DATE         timestamp                           null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_CHARGE_PRICE_SCHEDULE primary key  (ID)
);

comment on table CHARGE_PRICE_SCHEDULE is 
'�շ�ϵ��';

comment on column CHARGE_PRICE_SCHEDULE.ID is 
'����';

comment on column CHARGE_PRICE_SCHEDULE.CHARGE_TYPE is 
'�ѱ�';

comment on column CHARGE_PRICE_SCHEDULE.PRICE_COEFF_NUMERATOR is 
'�շ�ϵ������(סԺ)';

comment on column CHARGE_PRICE_SCHEDULE.PRICE_COEFF_DENOMINATOR is 
'�շ�ϵ����ĸ(סԺ)';

comment on column CHARGE_PRICE_SCHEDULE.CHARGE_SPECIAL_INDICATOR is 
'�������������������շ���Ŀ��־';

comment on column CHARGE_PRICE_SCHEDULE.NUMERATOR_OUTP is 
'�շ�ϵ������(����)';

comment on column CHARGE_PRICE_SCHEDULE.DENOMINATOR_OUTP is 
'�շ�ϵ����ĸ(��ĸ)';

comment on column CHARGE_PRICE_SCHEDULE.CREATE_BY is 
'������';

comment on column CHARGE_PRICE_SCHEDULE.CREATE_DATE is 
'����ʱ��';

comment on column CHARGE_PRICE_SCHEDULE.UPDATE_BY is 
'�޸���';

comment on column CHARGE_PRICE_SCHEDULE.UUPDATE_DATE is 
'�޸�ʱ��';

comment on column CHARGE_PRICE_SCHEDULE.DEL_FLAG is 
'ɾ�����';

comment on column CHARGE_PRICE_SCHEDULE.REMARKS is 
'��ע��Ϣ';