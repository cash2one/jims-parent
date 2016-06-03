create table ROLE_SERVICE_MENU
(
  id              VARCHAR2(64) not null,
  role_service_id VARCHAR2(64),
  menu_id         VARCHAR2(64),
  menu_operate    VARCHAR2(2),
  careate_by      VARCHAR2(64),
  remark          VARCHAR2(2000),
  update_by       VARCHAR2(64),
  update_date     DATE,
  del_flag        VARCHAR2(2),
  create_time     DATE
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
comment on table ROLE_SERVICE_MENU
  is '角色服务菜单表';
-- Add comments to the columns
comment on column ROLE_SERVICE_MENU.role_service_id
  is '权限范围id';
comment on column ROLE_SERVICE_MENU.menu_id
  is '菜单id';
comment on column ROLE_SERVICE_MENU.menu_operate
  is '菜单操作（0，view，1，edit）';
-- Create/Recreate primary, unique and foreign key constraints
alter table ROLE_SERVICE_MENU
  add constraint PK_ROLE_SERVICE_MENU primary key (ID)
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