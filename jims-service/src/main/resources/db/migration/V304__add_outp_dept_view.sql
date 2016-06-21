--创建门诊科室视图
create or replace view v_outp_dept_dict as
select * from dept_dict a where substr(a.dept_propertity,3,1)='0'