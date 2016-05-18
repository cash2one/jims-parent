/*==============================================================*/
/* Table: CLINIC_TYPE_FEE    门诊号类的收费信息                 */
/* CREATE_DATE: 2016-05-16                                      */
/* CREATE_BY :  cfj                                             */
/*==============================================================*/
-- drop table CLINIC_TYPE_FEE cascade constraints;
create table CLINIC_TYPE_FEE
(
   ID                   VARCHAR2(64),
   TYPE_ID              VARCHAR2(64),
   CHARGE_ITEM          VARCHAR2(64),
   PRICE_ITEM           VARCHAR2(64),
   PRICE                NUMBER(12,2),
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   REMARKS              VARCHAR2(200 char),
   DEL_FLAG             char                 default '0',
constraint CLINIC_TYPE_FEE primary key (ID)
);

comment on table CLINIC_TYPE_FEE is
'号类字典：此表定义了门诊号类的收费信息。用户定义。';

comment on column CLINIC_TYPE_FEE.ID is
'主键';

comment on column CLINIC_TYPE_FEE.TYPE_ID is
'号类外键';

comment on column CLINIC_TYPE_FEE.CHARGE_ITEM is
'费用类型：挂号费、诊疗费、其它费；来自字典表
';

comment on column CLINIC_TYPE_FEE.PRICE_ITEM is
'价表项目';

comment on column CLINIC_TYPE_FEE.PRICE is
'价格（空，可以用于查询）';

comment on column CLINIC_TYPE_FEE.CREATE_DATE is
'创建日期';

comment on column CLINIC_TYPE_FEE.CREATE_BY is
'创建人';

comment on column CLINIC_TYPE_FEE.UPDATE_DATE is
'更新日期';

comment on column CLINIC_TYPE_FEE.UPDATE_BY is
'更新人';

comment on column CLINIC_TYPE_FEE.REMARKS is
'备注信息';

comment on column CLINIC_TYPE_FEE.DEL_FLAG is
'删除标志';