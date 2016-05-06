-- drop table PERFORM_FREQ_DICT cascade constraints;

/*==============================================================*/
/* Table: PERFORM_FREQ_DICT    ҽ��ִ��Ƶ���ֵ�                 */
/* CREATE_DATE: 2016-05-05                                      */
/* CREATE_BY :  zp                                              */
/*==============================================================*/
create table PERFORM_FREQ_DICT
(
   ID                   VARCHAR2(64 char)    not null,
   HOSPITAL_ID          VARCHAR2(64 char),
   FREQ_DESC            CHAR(16),
   FREQ_COUNTER         NUMBER(11,2),
   FREQ_INTERVAL        NUMBER(11,2),
   FREQ_INTERVAL_UNITS  CHAR(4),
   CREATE_BY            VARCHAR2(64 char),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64 char),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             CHAR,
   REMARKS              VARCHAR2(2000 char),
   constraint PK_PERFORM_FREQ_DICT primary key (ID)

);
comment on table PERFORM_FREQ_DICT is
'ҽ��ִ��Ƶ���ֵ�';

comment on column PERFORM_FREQ_DICT.ID is
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
