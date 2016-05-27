/*drop table PATS_IN_TRANSFERRING cascade constraints;*/

/*==============================================================*/
/* Table: PATS_IN_TRANSFERRING     转科病人记录                 */
/* CREATE_BY CTQ                                                */
/* CREATE_DATE 2016-05-25 10:11:47                              */
/*==============================================================*/
create table PATS_IN_TRANSFERRING 
(
   ID                   VARCHAR2(64)         not null,
   PATIENT_ID           VARCHAR2(64),
   DEPT_TRANSFERED_FROM VARCHAR2(8),
   DEPT_TRANSFERED_TO   VARCHAR2(8),
   TRANSFER_DATE_TIME   DATE,
   CREATE_BY            VARCHAR2(64),
   CREATE_DATE          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   REMARKS              VARCHAR2(200),
   DEL_FLAG             CHAR(1) default '0',
   constraint PK_PATS_IN_TRANSFERRING primary key (ID)
);

comment on table PATS_IN_TRANSFERRING is
'转科病人记录';

comment on column PATS_IN_TRANSFERRING.ID is
'主键';

comment on column PATS_IN_TRANSFERRING.PATIENT_ID is
'病人标识号';

comment on column PATS_IN_TRANSFERRING.DEPT_TRANSFERED_FROM is
'转出科室';

comment on column PATS_IN_TRANSFERRING.DEPT_TRANSFERED_TO is
'转向科室';

comment on column PATS_IN_TRANSFERRING.TRANSFER_DATE_TIME is
'转出日期及时间';

comment on column PATS_IN_TRANSFERRING.CREATE_BY is
'创建人';

comment on column PATS_IN_TRANSFERRING.CREATE_DATE is
'创建日期';

comment on column PATS_IN_TRANSFERRING.UPDATE_BY is
'更新人';

comment on column PATS_IN_TRANSFERRING.UPDATE_DATE is
'更新日期';

comment on column PATS_IN_TRANSFERRING.REMARKS is
'备注';

comment on column PATS_IN_TRANSFERRING.DEL_FLAG is
'删除标识';
