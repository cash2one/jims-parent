/*==============================================================*/
/* Table: EMR_DIAGNOSIS    修改手术名称表中的字段类型           */
/* CREATE_BY :  pq                                              */
/*==============================================================*/
alter table SCHEDULED_OPERATION_NAME modify SCHEDULE_ID VARCHAR2(64);