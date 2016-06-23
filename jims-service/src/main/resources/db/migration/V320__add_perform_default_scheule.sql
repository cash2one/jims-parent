-- Create table
create table PERFORM_DEFAULT_SCHEDULE
(
  ID        varchar2(64),
  FREQ_DESC        VARCHAR2(64),--外键与频次字典表
  ADMINISTRATION   VARCHAR2(64),--途径
  DEFAULT_SCHEDULE VARCHAR2(16)--默认执行时间
);
-- Add comments to the table
comment on table PERFORM_DEFAULT_SCHEDULE
  is '医嘱执行缺省时间表';
-- Add comments to the columns
comment on column PERFORM_DEFAULT_SCHEDULE.ID
  is '序号';
comment on column PERFORM_DEFAULT_SCHEDULE.FREQ_DESC
  is '执行频率描述';
comment on column PERFORM_DEFAULT_SCHEDULE.ADMINISTRATION
  is '给药途径和方法';
comment on column PERFORM_DEFAULT_SCHEDULE.DEFAULT_SCHEDULE
  is '缺省的执行时间表';
-- Create/Recreate indexes
create unique index IND_1_PERFORM_DEFAULT_SCHEDULE on PERFORM_DEFAULT_SCHEDULE (id);
