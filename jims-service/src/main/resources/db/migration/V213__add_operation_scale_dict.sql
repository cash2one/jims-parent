/*==============================================================*/
/* Table: OPERATION_SCALE_DICT                                  */
/*==============================================================*/
create table OPERATION_SCALE_DICT 
(
   ID                   varchar2(64)                   not null,
   SERIAL_NO            char(1)                        null,
   OPERATION_SCALE_CODE char(1)                        null,
   OPERATION_SCALE_NAME varchar2(20)                   null,
   INPUT_CODE           varchar2(8)                    null,
   MEMO                 varchar2(200)                  null,
   CREATE_BY            varchar2(20)                   null,
   CREATE_DATE          TIMESTAMP                      null,
   UPDATE_BY            varchar2(20)                   null,
   UPDATE_DATE          TIMESTAMP                      null,
   DEL_FLAG             char(1)                        null,
   constraint PK_OPERATION_SCALE_DICT primary key (ID)
);

comment on table OPERATION_SCALE_DICT is 
'手术等级字典';

comment on column OPERATION_SCALE_DICT.ID is 
'主键';

comment on column OPERATION_SCALE_DICT.SERIAL_NO is 
'序号';

comment on column OPERATION_SCALE_DICT.OPERATION_SCALE_CODE is 
'手术等级代码';

comment on column OPERATION_SCALE_DICT.OPERATION_SCALE_NAME is 
'手术等级名称';

comment on column OPERATION_SCALE_DICT.INPUT_CODE is 
'输入码';

comment on column OPERATION_SCALE_DICT.MEMO is 
'备注';

comment on column OPERATION_SCALE_DICT.CREATE_BY is 
'创建人';

comment on column OPERATION_SCALE_DICT.CREATE_DATE is 
'创建时间';

comment on column OPERATION_SCALE_DICT.UPDATE_BY is 
'修改人';

comment on column OPERATION_SCALE_DICT.UPDATE_DATE is 
'修改时间';

comment on column OPERATION_SCALE_DICT.DEL_FLAG is 
'删除标记';


insert into  OPERATION_SCALE_DICT (ID, SERIAL_NO, OPERATION_SCALE_CODE, OPERATION_SCALE_NAME, INPUT_CODE ,MEMO) values ( '1', '1', '1', '1', 'Y', '一级手术，风险较低、过程简单、技术难度低的普通手术 ');
insert into  OPERATION_SCALE_DICT (ID, SERIAL_NO, OPERATION_SCALE_CODE, OPERATION_SCALE_NAME, INPUT_CODE, MEMO) values ( '2', '2', '2', '2', 'R', '二级手术，有一定风险、过程复杂程度一般、有一定技术难度的手术 ');
insert into  OPERATION_SCALE_DICT (ID, SERIAL_NO, OPERATION_SCALE_CODE, OPERATION_SCALE_NAME, INPUT_CODE, MEMO) values ( '3', '3', '3', '3', 'S', '三级手术，风险较高、过程较复杂、难度较大的手术 ');
insert into  OPERATION_SCALE_DICT (ID, SERIAL_NO, OPERATION_SCALE_CODE, OPERATION_SCALE_NAME, INPUT_CODE, MEMO) values ( '4', '4', '4', '4', 'S', '四级手术，风险高、过程复杂、难度大的重大手术 ');