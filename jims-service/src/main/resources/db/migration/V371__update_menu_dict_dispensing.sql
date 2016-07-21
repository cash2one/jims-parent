/*=============================================================*/
/* menu_dict 表插入菜单                                                    */
/*=============================================================*/
-- Create table

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values (sys_guid(), '门诊发药', '/modules/clinic/outDispensing/outDispensing.html', null, 4, '1', null, null, null, '21-7月 -16 03.02.44.579000 下午', '0', '21-7月 -16 02.56.12.454000 下午', 'c41e3b43214a404ba50115c7d545a62d', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values (sys_guid(), '住院发药', '/modules/clinic/outDispensing/inDispense.html', null, 6, '1', null, null, null, '21-7月 -16 03.02.49.371000 下午', '0', '21-7月 -16 02.56.34.492000 下午', 'c41e3b43214a404ba50115c7d545a62d', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values (sys_guid(), '已发药查询', '/modules/clinic/outDispensing/queryConfirmDrug.html', null, 5, '1', null, null, null, '21-7月 -16 03.02.47.057000 下午', '0', '21-7月 -16 02.57.03.075000 下午', 'c41e3b43214a404ba50115c7d545a62d', '1');



