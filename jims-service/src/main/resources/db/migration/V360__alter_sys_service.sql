--created by chenxy
--修改系统服务表的服务描述字段类型为Blob
-- Add/modify columns

rename sys_service to sys_service_test;

-- Create table
create table SYS_SERVICE
(
  id                  VARCHAR2(64) not null,
  service_name        VARCHAR2(100),
  service_type        VARCHAR2(2),
  service_class       VARCHAR2(2),
  service_image       VARCHAR2(2000),
  remarks             VARCHAR2(2000),
  update_by           VARCHAR2(64),
  create_by           VARCHAR2(64),
  update_date         DATE,
  del_flag            VARCHAR2(100),
  create_date         DATE,
  service_description BLOB
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
comment on table SYS_SERVICE
  is '系统服务';
-- Add comments to the columns
comment on column SYS_SERVICE.id
  is '系统服务';
comment on column SYS_SERVICE.service_name
  is '系统服务名称';
comment on column SYS_SERVICE.service_type
  is '1,有偿服务,0无偿服务';
comment on column SYS_SERVICE.service_class
  is '3,机构管理服务，2,所有服务，1,个人服务，0机构服务';
comment on column SYS_SERVICE.service_image
  is '服务图片';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_SERVICE
  add constraint SYS_SERVICE1_PK primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_SERVICE
  add constraint SYS_SERVICE1_UK unique (SERVICE_NAME)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


insert into sys_service select * from  sys_service_test;