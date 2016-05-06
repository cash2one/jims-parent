-- drop table UNIT_IN_CONTRACT cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: UNIT_IN_CONTRACT      ��ͬ��λ��¼
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table UNIT_IN_CONTRACT 
(
   id                   varchar2(64 char)              not null,
   UNIT_CODE            char(11)                       null,
   UNIT_NAME            char(40)                       null,
   INPUT_CODE           char(8)                        null,
   ADDRESS              char(40)                       null,
   UNIT_TYPE            char(1)                        null,
   SUBORDINATE_TO       char(11)                       null,
   DISTANCE_TO_HOSPITAL number(4,2)                    null,
   REGULAR_NUM          number(4,2)                    null,
   TEMP_NUM             number(4,2)                    null,
   RETIRED_NUM          number(4,2)                    null,
   INPUT_CODE_WB        number(8,2)                    null,
   CREATE_BY            varchar2(64 char)              null,
   CREATE_DATE          timestamp                      null,
   UPDATE_BY            varchar2(64 char)              null,
   UPDATE_DATE          timestamp                      null,
   DEL_FLAG             char(1)                        null,
   REMARKS              varchar2(2000 char)            null,
   constraint PK_UNIT_IN_CONTRACT primary key (id)
);

comment on table UNIT_IN_CONTRACT is 
'��ͬ��λ��¼';

comment on column UNIT_IN_CONTRACT.id is 
'����';

comment on column UNIT_IN_CONTRACT.UNIT_CODE is 
'��ͬ��λ����';

comment on column UNIT_IN_CONTRACT.UNIT_NAME is 
'��ͬ��λ����';

comment on column UNIT_IN_CONTRACT.INPUT_CODE is 
'������';

comment on column UNIT_IN_CONTRACT.ADDRESS is 
'��λ��ַ';

comment on column UNIT_IN_CONTRACT.UNIT_TYPE is 
'��λ����';

comment on column UNIT_IN_CONTRACT.SUBORDINATE_TO is 
'������λ';

comment on column UNIT_IN_CONTRACT.DISTANCE_TO_HOSPITAL is 
'��ҽ����';

comment on column UNIT_IN_CONTRACT.REGULAR_NUM is 
'�ڱ�����';

comment on column UNIT_IN_CONTRACT.TEMP_NUM is 
'�Ǳ�����';

comment on column UNIT_IN_CONTRACT.RETIRED_NUM is 
'����������';

comment on column UNIT_IN_CONTRACT.INPUT_CODE_WB is 
'�����';

comment on column UNIT_IN_CONTRACT.CREATE_BY is 
'������';

comment on column UNIT_IN_CONTRACT.CREATE_DATE is 
'����ʱ��';

comment on column UNIT_IN_CONTRACT.UPDATE_BY is 
'�޸���';

comment on column UNIT_IN_CONTRACT.UPDATE_DATE is 
'�޸�ʱ��';

comment on column UNIT_IN_CONTRACT.DEL_FLAG is 
'ɾ�����';

comment on column UNIT_IN_CONTRACT.REMARKS is 
'��ע��Ϣ';
