/*==============================================================*/
/* Table: TIME_INTERVAL_DICT                 时间段字典表        */
/*==============================================================*/
create table TIME_INTERVAL_DICT
(
  serial_no          NUMBER(1),
  time_interval_code VARCHAR2(1),
  time_interval_name VARCHAR2(8) not null,
  input_code         VARCHAR2(8)
);
comment on table TIME_INTERVAL_DICT
  is '时间段字典';
-- Add comments to the columns
comment on column TIME_INTERVAL_DICT.serial_no
  is '序号';
comment on column TIME_INTERVAL_DICT.time_interval_code
  is '时间段代码';
comment on column TIME_INTERVAL_DICT.time_interval_name
  is '时间段名称';
comment on column TIME_INTERVAL_DICT.input_code
  is '输入码';
insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (1, '1', '上午', 'SW');

insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (2, '2', '下午', 'XW');

insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (3, '3', '白天', 'BT');

insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (4, '4', '前夜', 'QY');

insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (5, '5', '后夜', 'HY');

insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (6, '6', '夜间', 'YJ');

insert into TIME_INTERVAL_DICT (SERIAL_NO, TIME_INTERVAL_CODE, TIME_INTERVAL_NAME, INPUT_CODE)
values (7, '7', '昼夜', 'ZY');

