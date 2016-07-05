/*==============================================================*/
/* Table: orders   住院医嘱收费 */
/* CREATE_DATE: 2016-07-04 11:52:40                             */
/* CREATE_BY: pq                                                */
/*==============================================================*/
alter table ORDERS_COSTS modify ITEM_CLASS VARCHAR2(6);
-- Add/modify columns 
alter table ORDERS_COSTS modify ITEM_NO null;