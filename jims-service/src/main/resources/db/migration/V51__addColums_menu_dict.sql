-- alter table
/*==============================================================*/
/* Table: MENU_DICT      菜单                                */
/*==============================================================*/
-- Add/modify columns
alter table MENU_DICT add pid varchar2(64);
alter table MENU_DICT add menu_level varchar2(2);
-- Add comments to the columns
comment on column MENU_DICT.pid
  is '父id';
comment on column MENU_DICT."MENU_LEVEL"
is '级别（0，1，2）一级二级三级';


insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('c41e3b43214a404ba50115c7d545a62d', '医师工作', '/modules/sys/menuDict.html', '1', 1, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('35E111DB41F9420B9B19B200A41488CB', '菜单管理', '/modules/sys/menuDict.html', '1', 1, '1', null, null, null, to_date('28-04-2016 09:37:43', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '55CB0105F69B490DA48F7D1F1029370A', '1');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('A1F9DA40723D498CBD2FA81F59D99A30', '消耗品管理', '/modules/sys/menuDict.html', '1', 6, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('9396B1CBF46D4902B850331A5BF9ABED', '财务管理', '/modules/sys/menuDict.html', '1', 5, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('352F4FCC7FAD4895BC14AC833DDC564D', '固定资产管理', '/modules/sys/menuDict.html', '1', 7, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('9ED7DB110B4F41F7AED1340F9B26CF1C', '运维管理', '/modules/sys/menuDict.html', '1', 8, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('4DF3F8BACB5345BC93B62FE8D2BE2695', '医技管理', '/modules/sys/menuDict.html', '1', 4, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('55CB0105F69B490DA48F7D1F1029370A', '系统管理', null, '1', 9, '2', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('3f9c90d23d52440eb465c9924e38ac77', '护理工作', null, '1', 2, '2', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('b194262cc2a049828e28b2685b8be350', '住院管理', '/modules/sys/menuDict.html', '1', 1, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '3f9c90d23d52440eb465c9924e38ac77', '1');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('79cb0ea6ab764b81a481add71053df02', '门诊管理', '/modules/sys/menuDict.html', '1', 2, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '3f9c90d23d52440eb465c9924e38ac77', '1');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('065969698a724445b2c03085aad8dfcd', '药品管理', '/modules/sys/menuDict.html', '1', 3, '1', null, null, null, to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('28-04-2016 09:30:34', 'dd-mm-yyyy hh24:mi:ss'), null, '0');