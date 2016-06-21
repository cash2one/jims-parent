--检验项目视图
create or replace view lab_item_view as
select * from clinic_item_name_dict where item_class='C'