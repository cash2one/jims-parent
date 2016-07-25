-- Create table
create table MENU_UPDATE_EXPLAIN
(
  ID                   VARCHAR2(64) not null,
  SERVICE_ID           VARCHAR2(64),
  TITLE                VARCHAR2(500),
  EXPLAIN              BLOB,
  CREATE_DATE          TIMESTAMP,
  CREATE_BY            VARCHAR2(64),
  UPDATE_BY            VARCHAR2(64),
  UPDATE_DATE          TIMESTAMP,
  REMARKS              VARCHAR2(200 char)
);
-- Add comments to the table
comment on table MENU_UPDATE_EXPLAIN
  is '服务菜单更新说明';
-- Add comments to the columns
comment on column MENU_UPDATE_EXPLAIN.ID
  is '主键';
comment on column MENU_UPDATE_EXPLAIN.TITLE
  is '标题';
comment on column MENU_UPDATE_EXPLAIN.SERVICE_ID
  is '服务ID';
comment on column MENU_UPDATE_EXPLAIN.EXPLAIN
  is '说明';
-- Create/Recreate primary, unique and foreign key constraints
alter table MENU_UPDATE_EXPLAIN
  add constraint PK_MENU_UPDATE_EXPLAIN primary key (ID);
