create or replace procedure p_back_charge(
 in_rcpt_no IN varchar2,
 o_outp_flag OUT varchar2
)
as 
v_bill_date date;
v_rcpt_no varchar2(20);
v_master_info outp_rcpt_master%rowtype;
v_outp_order outp_order_desc%rowtype;
v_outp_bill_items outp_bill_items%rowtype;
v_payments_money outp_payments_money%rowtype;
v_item_class varchar2(10);
v_appoint_no varchar2(64);


begin 
   select sysdate into v_bill_date from dual;/*获取当前系统时间*/
   select to_char(sysdate,'yyyyMMddHHmmss')||trunc(dbms_random.value(0,1000))into v_rcpt_no from dual;/*根据当前日期+随机数 生成 收据号*/
   /*根据 rcptNo 查询 outp_rcpt_master*/
   SELECT * into v_master_info FROM OUTP_RCPT_MASTER where rcpt_no = in_rcpt_no and charge_indicator =0;
   /*插入 outp_rcpt_master 退费信息*/
    INSERT INTO OUTP_RCPT_MASTER(
                        id, 
                        RCPT_NO,
                        PATIENTID,
                        NAME,
                        NAME_PHONETIC,
                        IDENTITY,
                        CHARGE_TYPE,
                        UNIT_IN_CONTRACT,
                        VISIT_DATE,
                        TOTAL_COSTS,
                        TOTAL_CHARGES,
                        OPERATOR_NO,
                        CHARGE_INDICATOR) values(
                        sys_guid() ,
                        v_rcpt_no,
                        v_master_info.patientid,
                        v_master_info.name,
                        v_master_info.name_phonetic,
                        v_master_info.identity,
                        v_master_info.charge_type,
                        v_master_info.unit_in_contract,
                        v_master_info.visit_date,
                        -v_master_info.total_costs,
                        -v_master_info.total_charges,
                        '登录人',/*操作人 取出当前登录人*/
                        2
                        );
     /*更新 退费信息*/
     update OUTP_RCPT_MASTER SET CHARGE_INDICATOR = 2,REFUNDED_RCPT_NO = v_rcpt_no where rcpt_no = in_rcpt_no;         
     /*查询 开单记录*/
   --  select * into v_outp_order from outp_order_desc where rcpt_no=in_rcpt_no ORDER BY visit_date ASC,visit_no ASC;
     if v_outp_order.presc_indicator=0  then/*检查 检验*/
     update OUTP_TREAT_REC SET CHARGE_INDICATOR =2 where  bill_visit_no = v_outp_order.visit_no and bill_visit_date =v_outp_order.visit_date;  
    
     SELECT item_class , appoint_no into v_item_class , v_appoint_no from OUTP_TREAT_REC where bill_visit_no = v_outp_order.visit_no and bill_visit_date =v_outp_order.visit_date and rownum = 1; 
     if v_item_class='C' then /*检验*/
       update LAB_TEST_ITEMS SET billing_indicator =2 where test_no =v_appoint_no;
       update LAB_TEST_MASTER SET billing_indicator =2 where  id =v_appoint_no;  
   
     else if v_item_class='D' then /*检查*/
        update exam_items SET billing_indicator =2 where APPOINTS_ID=v_appoint_no ;
        update exam_appoints SET billing_indicator =2 where id =v_appoint_no; 
      end if; 
      end if;
      if  v_outp_order.presc_indicator=1  then /*处方 1：西药，2：中药*/
     update outp_presc SET CHARGE_INDICATOR = 2 WHERE (DRUG_PRESC_DATE ='2016-06-02 10:28:11'  And drug_presc_no = 1406) or (newvisit_date ='2016-06-02 10:28:11'  and newvisit_no = 1406)  ;/*更新处方*/
    
     delete from drug_presc_detail_temp  where (PRESC_DATE, presc_no) in
       (SELECT DRUG_PRESC_DATE, drug_presc_no
          FROM outp_presc
         Where (DRUG_PRESC_DATE = '2016-06-02 10:28:11' And
               drug_presc_no = 1406)
            or (newvisit_date = '2016-06-02 10:28:11' and newvisit_no = 1406));/*删除 处方发药主表*/
       end if;
       /*插入开单记录*/
        INSERT INTO outp_order_desc
        ( id,
         visit_date,
         visit_no,
         ordered_by_dept,
         ordered_by_doctor,
         rcpt_no,
         presc_indicator,
         PRESC_ATTR,
         CLINIC_NO)
      VALUES
        (sys_guid() ,
         v_bill_date,
         v_rcpt_no,
         '110201',
         '0000XX',
          v_rcpt_no,
         1,
         NULL,
         v_outp_order.clinic_no
         );
       /*查询 所有诊疗项目费用*/  
       DECLARE CURSOR v_outp_bill_items IS
       select * from outp_bill_items where rcpt_no=in_rcpt_no ;
       begin
          /*循环 outp_bill_items*/
      for v_items in  v_outp_bill_items loop
        INSERT INTO OUTP_BILL_ITEMS
        (id,
         VISIT_DATE,
         RCPT_NO,
         ITEM_NO,
         ITEM_CLASS,
         CLASS_ON_RCPT,
         ITEM_CODE,
         ITEM_NAME,
         ITEM_SPEC,
         AMOUNT,
         UNITS,
         PERFORMED_BY,
         COSTS,
         CHARGES,
         CONFIRMED_OPERATOR,
         CONFIRMED_DATETIME,
         INVOICE_NO,
         FLAG,
         REPETITION,
         CLASS_ON_RECKONING,
         SUBJ_CODE,
         PRICE_QUOTIETY,
         ITEM_PRICE,
         ORDER_NO,
         SUB_ORDER_NO,
         PRINTED_RCPT_NO,
         PERFORMED_BY_SUB)
      VALUES
      (
       sys_guid() ,
       v_bill_date,
       v_rcpt_no,
       v_items.item_no,
       v_items.item_class,
       v_items.class_on_rcpt,
       v_items.item_code,
       v_items.item_name,
       v_items.item_spec,
       -v_items.amount,
       v_items.units,
       v_items.performed_by,
       -v_items.costs,
       -v_items.charges,
       v_items.confirmed_operator,
       v_items.confirmed_datetime,
       v_items.invoice_no,
       v_items.flag,
       v_items.repetition,
       v_items.class_on_reckoning,
       v_items.subj_code,
       v_items.price_quotiety,
       v_items.item_price,
       v_items.order_no,
       v_items.sub_order_no,
       v_items.printed_rcpt_no,
       v_items.performed_by_sub
        );
      end loop;
      end;
      /*查询支付方式*/
      select * into v_payments_money from outp_payments_money where rcpt_no=in_rcpt_no;
      /*插入支付方式*/
      INSERT INTO 
      OUTP_PAYMENTS_MONEY
     (id ,RCPT_NO, PAYMENT_NO, MONEY_TYPE, PAYMENT_AMOUNT, REFUNDED_AMOUNT)
     values
     (sys_guid() ,v_rcpt_no,v_payments_money.payment_no,v_payments_money.money_type,-v_payments_money.payment_amount,v_payments_money.refunded_amount);       
      end if;
     o_outp_flag :='1';
     
      EXCEPTION
  WHEN OTHERS THEN
            o_outp_flag :='-1';
     
    
end;
