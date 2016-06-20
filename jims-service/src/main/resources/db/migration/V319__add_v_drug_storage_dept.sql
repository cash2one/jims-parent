
--获取药局数据
create or replace view v_drug_storage_dept as
select * from drug_storage_dept where storage_type in ('1','2','4') ;