/*

drop table ORDERS_GROUP_REC cascade constraints;
*/

/*==============================================================*/
/* Table: ORDERS_GROUP_REC                                      */
/*==============================================================*/
create table ORDERS_GROUP_REC 
(
   ID                   VARCHAR2(64)         not null,
   PATIENT_ID           VARCHAR2(64),
   VISIT_ID             VARCHAR2(64),
   DEPT_CODE            VARCHAR2(8),
   ORDER_GROUP          VARCHAR2(8),
   ORDER_DOCTOR         VARCHAR2(20),
   DOCTOR_USER          VARCHAR2(64),
   SUPER_DOCTOR_ID      VARCHAR2(64),
   PARENT_DOCTOR_ID     VARCHAR2(64),
   ORG_ID               VARCHAR2(64),
   constraint PK_ORDERS_GROUP_REC_1 primary key (ID)
);

comment on table ORDERS_GROUP_REC is
'核算组记录';

comment on column ORDERS_GROUP_REC.ID is
'ID';

comment on column ORDERS_GROUP_REC.PATIENT_ID is
'病人ID';

comment on column ORDERS_GROUP_REC.VISIT_ID is
'住院次数';

comment on column ORDERS_GROUP_REC.DEPT_CODE is
'科室代码';

comment on column ORDERS_GROUP_REC.ORDER_GROUP is
'核算组代码';

comment on column ORDERS_GROUP_REC.ORDER_DOCTOR is
'经治医生';

comment on column ORDERS_GROUP_REC.DOCTOR_USER is
'医生代码';

comment on column ORDERS_GROUP_REC.ORG_ID is
'机构标识';
