/*==============================================================*/
/* Table: ORDERS  修改医嘱表的字段长度                        */
/* CREATE_BY: pq                                       */
/*==============================================================*/
alter table ORDERS modify ADMINISTRATION VARCHAR2(64);
alter table ORDERS modify FREQUENCY VARCHAR2(64);