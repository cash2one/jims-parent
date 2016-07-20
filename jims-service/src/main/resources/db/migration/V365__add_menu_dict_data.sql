/*=============================================================*/
/* menu_dict 表插入菜单                                                    */
/*=============================================================*/
-- Create table
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks,update_by, create_by, update_date, del_flag, create_date, pid, menu_level)values ('3E6C686311DD43FDB07D3F84A45F7BFD', '重置密码', '/modules/sys/reset-password.html', null, null, '1', null, null, null, to_timestamp('18-07-201615:12:54.346000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('18-07-201615:12:54.346000', 'dd-mm-yyyy hh24:mi:ss.ff'),'55CB0105F69B490DA48F7D1F1029370A', '1');
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('9771728251C54F9C8824D5C57E5EA96F', '录入申请', '/modules/phstock/drug-provide-application.html', null, 1, '1', null, null, null, to_timestamp('20-07-2016 08:57:52.880000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('20-07-2016 08:57:52.880000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'A917A81F718E485EA716BB9AFB82D7C0', '2');
