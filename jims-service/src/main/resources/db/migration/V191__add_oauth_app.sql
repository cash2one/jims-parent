create table APP
(
  app_key      VARCHAR2(64) not null,
  secret_key   VARCHAR2(64),
  callback_url VARCHAR2(60),
  description  VARCHAR2(10),
  name         VARCHAR2(10),
  status       NUMBER(11),
  create_time  DATE,
  user_id      VARCHAR2(64),
  owner        VARCHAR2(10),
  approval     VARCHAR2(4),
  scope        VARCHAR2(100)
);
alter table APP
  add constraint APPPRIMARY primary key (APP_KEY);
create table AUTHORITY
(
  app_key VARCHAR2(10) not null,
  user_id VARCHAR2(11) not null
);
alter table AUTHORITY
  add constraint AUTHORITYPRIMARY primary key (APP_KEY, USER_ID);
create table REFRESH_TOKEN
(
  access_token       VARCHAR2(100),
  refresh_token      VARCHAR2(100),
  create_time        DATE,
  expire             VARCHAR2(30),
  app_key            VARCHAR2(10) not null,
  user_id            VARCHAR2(64) not null,
  scope              VARCHAR2(100),
  authorization_time DATE,
  grant_type         VARCHAR2(30)
);
alter table REFRESH_TOKEN
  add constraint REFRESH_TOKEN_PRIMARY primary key (APP_KEY);
