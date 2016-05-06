-- drop table PERFORM_FREQ_DICT cascade constraints;     
-- Create table
/*==============================================================*/
/* Table: PERFORM_FREQ_DICT      ҽ��ִ��Ƶ���ֵ� 
/* CREATE_DATE: 2016-05-05                                 */
/* CREATE_BY :  zp      
/*==============================================================*/
create table PERFORM_FREQ_DICT 
(
   id                   varchar2(64 char)              not null,
   HOSPITAL_ID          varchar2(64 char),
   FREQ_DESC            char(16),
   FREQ_COUNTER         number(11,2) ,
   FREQ_INTERVAL        number(11,2),
   FREQ_INTERVAL_UNITS  char(4),
   CREATE_BY            varchar2(64 char),
   CREATE_DATE          TIMESTAMP(6),
   UPDATE_BY            varchar2(64 char),
   UPDATE_DATE          TIMESTAMP(6),
   DEL_FLAG             char(1) ,
   REMARKS              varchar2(2000 char),
  constraint "PK_PERFORM_FREQ_DICT" primary key (ID)
  );

comment on table PERFORM_FREQ_DICT is 
'ҽ��ִ��Ƶ���ֵ�';

comment on column PERFORM_FREQ_DICT.id is 
'����';

comment on column PERFORM_FREQ_DICT.HOSPITAL_ID is 
'ҽԺid';

comment on column PERFORM_FREQ_DICT.FREQ_DESC is 
'ִ��Ƶ������';

comment on column PERFORM_FREQ_DICT.FREQ_COUNTER is 
'Ƶ�ʴ���';

comment on column PERFORM_FREQ_DICT.FREQ_INTERVAL is 
'Ƶ�ʼ��';

comment on column PERFORM_FREQ_DICT.FREQ_INTERVAL_UNITS is 
'Ƶ�ʼ����λ';

comment on column PERFORM_FREQ_DICT.CREATE_BY is 
'������';

comment on column PERFORM_FREQ_DICT.CREATE_DATE is 
'����ʱ��';

comment on column PERFORM_FREQ_DICT.UPDATE_BY is 
'�޸���';

comment on column PERFORM_FREQ_DICT.UPDATE_DATE is 
'�޸�ʱ��';

comment on column PERFORM_FREQ_DICT.DEL_FLAG is 
'ɾ�����';

comment on column PERFORM_FREQ_DICT.REMARKS is 
'��ע��Ϣ';