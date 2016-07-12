--created by chenxy
--修改系统服务表的服务描述字段类型为Blob
-- Add/modify columns
alter table sys_service add  (service_description1 blob);--新加一列blob类型
update sys_service set service_description1=service_description;--将原列的值插入新建的列
alter table sys_service drop column service_description;
alter table sys_service rename column service_description1 to service_description