-- Create table
create table SYS_SERVICE
(
  ID                  VARCHAR2(64) not null,
  SERVICE_NAME        VARCHAR2(100),
  SERVICE_DESCRIPTION VARCHAR2(2048),
  SERVICE_TYPE        VARCHAR2(2),
  REMARKS             VARCHAR2(2000),
  UPDATE_BY           VARCHAR2(64),
  CREATE_BY           VARCHAR2(64),
  UPDATE_DATE         DATE,
  DEL_FLAG            VARCHAR2(100),
  CREATE_DATE         DATE
);
-- Add comments to the table
comment on table SYS_SERVICE
  is '系统服务';
-- Add comments to the columns
comment on column SYS_SERVICE.ID
  is '系统服务';
comment on column SYS_SERVICE.SERVICE_NAME
  is '系统服务名称';
comment on column SYS_SERVICE.SERVICE_DESCRIPTION
  is '服务描述';
comment on column SYS_SERVICE.SERVICE_TYPE
  is '1,有偿服务,0无偿服务';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_SERVICE
  add constraint SYS_SERVICE_PK primary key (ID);
alter table SYS_SERVICE
  add constraint SYS_SERVICE_UK unique (SERVICE_NAME);
