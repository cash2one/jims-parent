/*==============================================================*/
/* Table: menu_dict    增加菜单按钮                             */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  朱齐                                              */
/*==============================================================*/
-- insert into
insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('3154CD0A37344E4BBF3B49A00B2C77DF', '药品类别管理', '/modules/phstock/drug-class-dict.html', '1', 2, '1', null, null, null, '11-5月 -16 03.11.18.646000 下午', '0', '11-5月 -16 03.11.18.646000 下午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('F2A22CAD7CA646EB8353A3A8ABFBB327', '检验项目分类', '/modules/sys/labItemClassDict.html', '1', 1, '1', null, null, null, '09-5月 -16 03.16.30.252000 下午', '0', '09-5月 -16 03.12.43.114000 下午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');