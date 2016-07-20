 --检验项目视图
 create or replace view v_lab_class as
 select a.*, b.dept_name
   from lab_item_class_dict a, dept_dict b
  where a.dept_code = b.dept_code
    and a.org_id = b.org_id