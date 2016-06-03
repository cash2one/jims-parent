/*==============================================================*/
/* 创建 检验科室、检验项目获取视图    */
/*CREATE_BY: ztq	                                      */
/*==============================================================*/
create or replace view lab_info_view as
select distinct b.class_code,
                b.class_name,
                a.dept_name,
                a. org_id,
                a.id as dept_id,
                a.dept_code
  from dept_dict a, lab_item_class_dict b
 where b.org_id = a.org_id(+)
   and b.dept_code = a.dept_code(+)