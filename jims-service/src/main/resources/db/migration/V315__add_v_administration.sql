--门诊途径视图
create or replace view v_administration_mz as
select * from administration_dict where inp_outp_flag in ('0','9') ;

--住院途径视图
create or replace view v_administration_zy as
select * from administration_dict where inp_outp_flag in ('1','9') ;