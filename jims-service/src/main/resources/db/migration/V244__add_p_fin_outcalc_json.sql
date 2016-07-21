create or replace procedure p_fin_outcalc_json(I_jsondata1 IN VARCHAR, /*待划价项目id字符串 ,分割*/
                                            O_SCCE_FLAG OUT VARCHAR /*返回值 0为正确 -1为失败*/) as
  v_data_obj_list json_list; ---传入对象列表
  v_data_obj      json; ---循环对象
  v_data_orders      json_list; ---循环对象
  v_data_order      json; ---循环对象
  v_rcptNo VARCHAR2(80);
  v_orderNo VARCHAR2(80);
  
  --procedure p(v varchar2) as begin dbms_output.put_line(null);dbms_output.put_line(v); end;

begin

  v_data_obj_list := json_list(I_jsondata1);
  --v_data_obj_list.print;

  for i in 1 .. v_data_obj_list.count loop
    v_data_obj := json(v_data_obj_list.get(i));
    v_rcptNo := json_ext.get_string(v_data_obj,'rcptNo');
    --p(v_rcptNo);
    
    v_data_orders := json_ext.get_json_list(v_data_obj,'orderIds');
    -- v_data_orders.print;
     
    p_fin_outcalc(v_rcptNo,v_data_orders.to_char(),O_SCCE_FLAG);
    /**for i in 1..v_data_orders.count loop
    
    v_data_order := json(v_data_orders.get(i));
    v_orderNo := json_ext.get_string(v_data_order,'orderId');
   
    
    end loop; */
    
  
    
  end loop;

end;
