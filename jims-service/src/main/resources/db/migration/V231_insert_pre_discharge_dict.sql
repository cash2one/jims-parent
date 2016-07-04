/*==============================================================*/
/* Table: PRE_DISCHARGE_DICT    出院通知单 - 出院方式字典表 */
/* CREATE_DATE: 2016-07-04 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/

insert into PRE_DISCHARGE_DICT (ID, ORG_ID, DISCHARGE_NAME, DISCHARGE_CODE, ORDERS_TEXT, ORDERS_CODE, INPUT_CODE, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG)
values ('1', '', '自动', 'ZD', '自动出院', 'ZDCY', 'ZD', '', '', '', '', '', '0');

insert into PRE_DISCHARGE_DICT (ID, ORG_ID, DISCHARGE_NAME, DISCHARGE_CODE, ORDERS_TEXT, ORDERS_CODE, INPUT_CODE, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG)
values ('2', '', '正常', 'ZC', '今日出院', 'JRCY', 'ZC', '', '', '', '', '', '0');

insert into PRE_DISCHARGE_DICT (ID, ORG_ID, DISCHARGE_NAME, DISCHARGE_CODE, ORDERS_TEXT, ORDERS_CODE, INPUT_CODE, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG)
values ('3', '', '死亡', 'SW', '临床死亡', 'LCSW', 'SW', '', '', '', '', '', '0');


