-- drop table DRUG_DISPENS_PROPERTY cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: DRUG_DISPENS_PROPERTY      ҩƷ��ҩ���ඨ�� 
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table DRUG_DISPENS_PROPERTY 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar(64 char)               null,
   DISPENSARY           char(8)                        null,
   DRUG_CODE            char(20)                       null,
   DISPENSING_PROPERTY  char(10)                       null,
   DRUG_SPEC            varchar2(64 char)              null,
   DISPENSING_CUMULATE  number(11,2)                   null,
   SEPARABLE            number(11,2)                   null,
   VIRTUAL_CABINET      number(11,2)                   null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                           null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                           null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_DRUG_DISPENS_PROPERTY primary key  (id)
);

comment on table DRUG_DISPENS_PROPERTY is 
'ҩƷ��ҩ���ඨ��';

comment on column DRUG_DISPENS_PROPERTY.id is 
'����';

comment on column DRUG_DISPENS_PROPERTY.HOSPITAL_ID is 
'ҽԺid';

comment on column DRUG_DISPENS_PROPERTY.DISPENSARY is 
'����ҩ��';

comment on column DRUG_DISPENS_PROPERTY.DRUG_CODE is 
'ҩƷ����';

comment on column DRUG_DISPENS_PROPERTY.DISPENSING_PROPERTY is 
'��ҩ���';

comment on column DRUG_DISPENS_PROPERTY.DRUG_SPEC is 
'ҩƷ���';

comment on column DRUG_DISPENS_PROPERTY.DISPENSING_CUMULATE is 
'��ҩ�ۻ�';

comment on column DRUG_DISPENS_PROPERTY.SEPARABLE is 
'�ɷָ��';

comment on column DRUG_DISPENS_PROPERTY.VIRTUAL_CABINET is 
'����ҩ��';

comment on column DRUG_DISPENS_PROPERTY.CREATE_BY is 
'������';

comment on column DRUG_DISPENS_PROPERTY.CREATE_DATE is 
'����ʱ��';

comment on column DRUG_DISPENS_PROPERTY.UPDATE_BY is 
'�޸���';

comment on column DRUG_DISPENS_PROPERTY.UPDATE_DATE is 
'�޸�ʱ��';

comment on column DRUG_DISPENS_PROPERTY.DEL_FLAG is 
'ɾ�����';

comment on column DRUG_DISPENS_PROPERTY.REMARKS is 
'��ע��Ϣ';
