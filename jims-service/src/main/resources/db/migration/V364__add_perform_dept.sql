/*=============================================================*/
/* Table: PERFORM_DEPT    价表项目执行科室                     */
/* CREATE_DATE: 2016-07-18                                     */
/* CREATE_BY :  fengyuguang                                    */
/*  创建表                                                     */
/*=============================================================*/
-- Create table
create table PERFORM_DEPT
(
  id           VARCHAR2(64) not null,
  item_class   VARCHAR2(1),
  item_code    VARCHAR2(20),
  performed_by VARCHAR2(8),
  org_id       VARCHAR2(64)
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
comment on table PERFORM_DEPT
  is '价表项目执行科室';
-- Add comments to the columns
comment on column PERFORM_DEPT.id
  is '主键';
comment on column PERFORM_DEPT.item_class
  is '价表项目分类';
comment on column PERFORM_DEPT.item_code
  is '价表项目代码';
comment on column PERFORM_DEPT.performed_by
  is '执行科室';
comment on column PERFORM_DEPT.org_id
  is '所属组织机构';
-- Create/Recreate primary, unique and foreign key constraints
alter table PERFORM_DEPT
  add constraint PK_PERFORM_DEPT primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
