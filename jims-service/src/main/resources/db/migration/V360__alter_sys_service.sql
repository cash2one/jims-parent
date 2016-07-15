--created by chenxy
--修改系统服务表的服务描述字段类型为Blob
-- Add/modify columns

--原表改名
rename sys_service to sys_service_test;

-- Create table--创建新表（注意：新表名与原表名同，新表的service_description字段是blob类型，原表中是varchar2）
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

--从原表中查询数据，插入到新表中（注意：原表的varchar2数据经过rawtohex()加工才放到新表的吧blob类型同一字段内）
insert into sys_service(id,service_name,service_type,service_class,service_image,remarks,update_by,create_by,update_date,del_flag,create_date,service_description)
  select id,service_name,service_type,service_class,service_image,remarks,update_by,create_by,update_date,del_flag,create_date,rawtohex(service_description) from sys_service_test;

--删除原表
drop table sys_service_test;

--下面是创建主键和附键
alter table SYS_SERVICE
  add constraint SYS_SERVICE_PK primary key (ID)
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
  add constraint SYS_SERVICE_UK unique (SERVICE_NAME)
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