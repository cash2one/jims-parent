/*==============================================================*/
/* Table: ORDERS  住院医嘱                                      */
/* CREATE_BY: pq                                                */
/*==============================================================*/
alter table ORDERS modify EXEC_OPERATOR VARCHAR2(64);
alter table ORDERS modify EXEC_DATE_TIME TIMESTAMP(6);
-- Add comments to the columns 
comment on column ORDERS.EXEC_OPERATOR
  is '执行操作护士';
comment on column ORDERS.EXEC_DATE_TIME
  is '护士执行时间';