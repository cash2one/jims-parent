

-- insert data  Menu_dict
/*==============================================================*/
/* Table: Menu_dict          给组织机构人员加菜单     createBy  yangruidong    */
/*==============================================================*/
insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('AA68F77546214C038A1F1833365F9123', '组织机构人员管理', '/modules/sys/orgStaff.html', null, 1, '1', null, null, null, to_timestamp('12-05-2016 09:41:48.960000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('12-05-2016 09:41:48.960000', 'dd-mm-yyyy hh24:mi:ss.ff'), '55CB0105F69B490DA48F7D1F1029370A', '1');


insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('3CC4823AB9994B6F953BF86CBF8ECEC7', '出库记录查询', '/modules/phstock/drug-stock-export-search.html', null, 1, '1', null, null, null, to_timestamp('12-05-2016 16:17:57.623000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('12-05-2016 16:17:57.623000', 'dd-mm-yyyy hh24:mi:ss.ff'), '065969698a724445b2c03085aad8dfcd', '1');

insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('1E797895EC524374B81F8BAD3F1A3DA5', '库存量查询', '/modules/phstock/drug-stock-search.html', null, 1, '1', null, null, null, to_timestamp('12-05-2016 16:18:41.070000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('12-05-2016 16:18:41.070000', 'dd-mm-yyyy hh24:mi:ss.ff'), '065969698a724445b2c03085aad8dfcd', '1');

insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)
values ('ADF7BF849BC049F9B5C5B46DCB118D48', '入库记录查询', '/modules/phstock/drug-stock-import-search.html', null, 1, '1', null, null, null, to_timestamp('12-05-2016 16:17:21.271000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('12-05-2016 16:17:21.271000', 'dd-mm-yyyy hh24:mi:ss.ff'), '065969698a724445b2c03085aad8dfcd', '1');


update menu_dict set href='',target='2' where id='065969698a724445b2c03085aad8dfcd'