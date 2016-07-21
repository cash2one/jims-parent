/*==============================================================*/
/* Table: menu_dict    增加菜单按钮                             */
/* CREATE_DATE: 2016-07-21                                     */
/* CREATE_BY :  weishen                                   */
/*==============================================================*/
-- insert into


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('C13F1D8F3FD24C3D93AAA06B6E4E255B', '药品出库上账', '/modules/phstock/drug-export-bill.html', null, null, null, null, null, null, '18-7月 -16 08.57.27.926000 上午', '0', '18-7月 -16 08.57.27.926000 上午', 'F091DC51565646FDB438810D32685FB2', '2');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('39DA7B8D683C4CCBA266B9F010DA683E', '药品入库上账', '/modules/phstock/drug-import-bill.html', null, null, null, null, null, null, '21-7月 -16 09.29.59.584000 上午', '0', '15-7月 -16 08.58.05.399000 上午', 'F091DC51565646FDB438810D32685FB2', '2');
