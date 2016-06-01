$(function(){
    $("#visitDate").datebox("setValue", formatter(new Date()));
    $('#payments').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outpPaymentsMoney/findMaoneyPayment?'+$("#search").serialize(),
        columns:[[      //每个列具体内容
            {field:'moneyType',title:'支付方式',width:'15%',align:'center'},
            {field:'paymentAmount',title:'收款',width:'15%',align:'center'},
            {field:'refundedAmount',title:'找零',width:'25%',align:'center'},
            {field:'paymentAmount',title:'小计',width:'10%',align:'center'}
        ]], onClickRow: function (index, row) {
          //  $("#masterForm").form('load',row);
        }, onLoadSuccess: function(){

        }
    });
});