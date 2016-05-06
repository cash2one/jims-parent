-- drop table CHARGE_TYPE_VS_IDENTITY cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: CHARGE_TYPE_VS_IDENTITY      �����ѱ����
/* CREATE_DATE: 2016-05-05 13:33                                */
/* CREATE_BY :  zp      
/*==============================================================*/
create table CHARGE_TYPE_VS_IDENTITY 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar2(64 char)              null,
   CHARGE_TYPE          char(8)                        null,
   IDENTITY_SERIAL_NO   number(11,2)                   null,
   IDENTITY             char(10)                       null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                      null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_CHARGE_TYPE_VS_IDENTITY  primary key (id) 
  
   );

comment on table CHARGE_TYPE_VS_IDENTITY is 
'�����ѱ����';

comment on column CHARGE_TYPE_VS_IDENTITY.id is 
'����';

comment on column CHARGE_TYPE_VS_IDENTITY.HOSPITAL_ID is 
'ҽԺid';

comment on column CHARGE_TYPE_VS_IDENTITY.CHARGE_TYPE is 
'�ѱ�';

comment on column CHARGE_TYPE_VS_IDENTITY.IDENTITY_SERIAL_NO is 
'������';

comment on column CHARGE_TYPE_VS_IDENTITY.IDENTITY is 
'���';

comment on column CHARGE_TYPE_VS_IDENTITY.CREATE_BY is 
'������';

comment on column CHARGE_TYPE_VS_IDENTITY.CREATE_DATE is 
'����ʱ��';

comment on column CHARGE_TYPE_VS_IDENTITY.UPDATE_BY is 
'�޸���';

comment on column CHARGE_TYPE_VS_IDENTITY.UPDATE_DATE is 
'�޸�ʱ��';

comment on column CHARGE_TYPE_VS_IDENTITY.DEL_FLAG is 
'ɾ�����';

comment on column CHARGE_TYPE_VS_IDENTITY.REMARKS is 
'��ע��Ϣ';
