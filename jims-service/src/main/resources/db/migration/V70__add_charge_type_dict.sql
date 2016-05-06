-- drop table CHARGE_TYPE_DICT cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: CHARGE_TYPE_DICT      �ѱ��ֵ�
/* CREATE_DATE: 2016-05-05 13:33                                */
/* CREATE_BY :  zp      
/*==============================================================*/
create table CHARGE_TYPE_DICT 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar2(64 char)              null,
   CHARGE_TYPE_CODE     char(1)                        null,
   CHARGE_TYPE_NAME     char(8)                        null,
   CHARGE_PRICE_INDICATOR number(11,2)                 null,
   INPUT_CODE_WB        char(8)                        null,
   IS_INSUR             char(1)                        null,
   GROUP_NO             number(11,2)                   null,
   GROUP_NAME           varchar2(64 char)              null,
   INSURANCE_TYPE_INQ   number(11,2)                   null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATA          timestamp                      null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_CHARGE_TYPE_DICT primary key (id)
);

comment on table CHARGE_TYPE_DICT is 
'�ѱ��ֵ�';

comment on column CHARGE_TYPE_DICT.HOSPITAL_ID is 
'ҽԺid';

comment on column CHARGE_TYPE_DICT.CHARGE_TYPE_CODE is 
'�ѱ����';

comment on column CHARGE_TYPE_DICT.CHARGE_TYPE_NAME is 
'�ѱ�����';

comment on column CHARGE_TYPE_DICT.CHARGE_PRICE_INDICATOR is 
'�����Żݼ۸��־';

comment on column CHARGE_TYPE_DICT.INPUT_CODE_WB is 
'�����';

comment on column CHARGE_TYPE_DICT.IS_INSUR is 
'�Ƿ���ҽ�����';

comment on column CHARGE_TYPE_DICT.GROUP_NO is 
'�ѱ�����';

comment on column CHARGE_TYPE_DICT.GROUP_NAME is 
'�ѱ��������';

comment on column CHARGE_TYPE_DICT.INSURANCE_TYPE_INQ is 
'Ժ����ѯ�õ�ҽ�����';

comment on column CHARGE_TYPE_DICT.CREATE_BY is 
'������';

comment on column CHARGE_TYPE_DICT.CREATE_DATA is 
'����ʱ��';

comment on column CHARGE_TYPE_DICT.UPDATE_BY is 
'�޸���';

comment on column CHARGE_TYPE_DICT.UPDATE_DATE is 
'�޸�ʱ��';

comment on column CHARGE_TYPE_DICT.DEL_FLAG is 
'ɾ����־';

comment on column CHARGE_TYPE_DICT.REMARKS is 
'ɾ����־';