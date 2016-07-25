-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:drug_provide_application                           */
/*==============================================================*/

-- Create table
create table DRUG_PROVIDE_APPLICATION
(
  applicant_storage     VARCHAR2(8) not null,
  provide_storage       VARCHAR2(8),
  item_no               NUMBER(4) not null,
  drug_code             VARCHAR2(20),
  drug_spec             VARCHAR2(20),
  package_spec          VARCHAR2(20),
  quantity              NUMBER(12,2),
  package_units         VARCHAR2(8),
  enter_date_time       DATE,
  firm_id               VARCHAR2(64),
  batch_no              VARCHAR2(16),
  document_no           VARCHAR2(10),
  no_provide_quantity   NUMBER,
  flag                  NUMBER(1),
  sub_storage           VARCHAR2(8),
  applicant_storage_sub VARCHAR2(8) not null,
  id                    VARCHAR2(64) not null,
  org_id                VARCHAR2(64) ,
  units                 VARCHAR2(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table DRUG_PROVIDE_APPLICATION
  is '药品发放申请';
-- Add comments to the columns
comment on column DRUG_PROVIDE_APPLICATION.applicant_storage
  is '请领库房';
comment on column DRUG_PROVIDE_APPLICATION.provide_storage
  is '发放库房';
comment on column DRUG_PROVIDE_APPLICATION.item_no
  is '项目序号';
comment on column DRUG_PROVIDE_APPLICATION.drug_code
  is '药品代码';
comment on column DRUG_PROVIDE_APPLICATION.drug_spec
  is '规格';
comment on column DRUG_PROVIDE_APPLICATION.package_spec
  is '包装规格';
comment on column DRUG_PROVIDE_APPLICATION.quantity
  is '数量';
comment on column DRUG_PROVIDE_APPLICATION.package_units
  is '包装单位';
comment on column DRUG_PROVIDE_APPLICATION.enter_date_time
  is '申请日期';
comment on column DRUG_PROVIDE_APPLICATION.firm_id
  is '厂家标识';
comment on column DRUG_PROVIDE_APPLICATION.batch_no
  is '批号';
comment on column DRUG_PROVIDE_APPLICATION.document_no
  is '申请单据号';
comment on column DRUG_PROVIDE_APPLICATION.no_provide_quantity
  is '未发放数量';
comment on column DRUG_PROVIDE_APPLICATION.flag
  is '是否发放  0  未发放   1  未全部发放  2  全部发放';
comment on column DRUG_PROVIDE_APPLICATION.sub_storage
  is '发放库房子单位';
comment on column DRUG_PROVIDE_APPLICATION.applicant_storage_sub
  is '请领库房子单位';
comment on column DRUG_PROVIDE_APPLICATION.id
  is '主键';
comment on column DRUG_PROVIDE_APPLICATION.org_id
  is '组织机构ID';
-- Create/Recreate primary, unique and foreign key constraints
alter table DRUG_PROVIDE_APPLICATION
  add constraint PK_DRUG_PROVIDE_APPLIATION primary key (ID)
  using index;

