--drop table ORDER_OPEN cascade constraints;

/*==============================================================*/
/* Table: ORDER_OPEN    类医嘱锁表                       				*/
/* CREATE_DATE: 2016-05-16 11:52:40                             */
/* CREATE_BY: CTQ						                                    */
/*==============================================================*/
create table ORDER_OPEN 
(
   ID                   VARCHAR2(64)         not null,
   ORG_ID               VARCHAR2(64),
   PATIENT_ID           VARCHAR2(64),
   VISIT_ID             VARCHAR2(64),
   ORDERS_ID            VARCHAR2(64),
   USER_NAME            VARCHAR2(50)         not null,
   USER_ID              VARCHAR2(18)         not null,
   COMPUTER             VARCHAR2(50)         not null,
   OPEN_TIME            TIMESTAMP            not null,
   DEPT_NAME            VARCHAR2(50),
   CREATE_BY            VARCHAR2(64),
   CREATE_DATA          TIMESTAMP,
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATA          TIMESTAMP,
   REMARKS              VARCHAR2(200),
   DEL_FLAG             char(1) default '0',
   constraint PK_ORDER_OPEN primary key (ID)
       
);
comment on table ORDER_OPEN is
'记录病人医嘱的锁定情况';

comment on column ORDER_OPEN.DEL_FLAG is
'是否删除';

comment on column ORDER_OPEN.ID is
'主键';

comment on column ORDER_OPEN.ORG_ID is
'机构ID';

comment on column ORDER_OPEN.PATIENT_ID is
'病人ID';

comment on column ORDER_OPEN.VISIT_ID is
'住院ID';

comment on column ORDER_OPEN.USER_NAME is
'医生名称';

comment on column ORDER_OPEN.USER_ID is
'医生ID';

comment on column ORDER_OPEN.COMPUTER is
'计算机名称';

comment on column ORDER_OPEN.OPEN_TIME is
'锁定起始时间';

comment on column ORDER_OPEN.DEPT_NAME is
'科室名称';

comment on column ORDER_OPEN.CREATE_BY is
'创建人';

comment on column ORDER_OPEN.CREATE_DATA is
'创建时间';

comment on column ORDER_OPEN.UPDATE_BY is
'更新人';

comment on column ORDER_OPEN.UPDATE_DATA is
'更新时间';

comment on column ORDER_OPEN.ORDERS_ID is
'处方ID';

comment on column ORDER_OPEN.REMARKS is
'备注';
