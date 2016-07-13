var accNo;
$(function(){
    $.ajax({
        method:"GET",
        url:basePath+"/outpAcctMaster/getMaxAcctNo",
        dataType: 'json',
        /*  contentType: "application/json", //必须有

         data: ,*/
        success: function(data){
            accNo=data;
            $("#acctNoU").val(accNo);
            $('#acctNo').val(accNo);
        }
    });
    $("#visitDate").datebox("setValue", formatDateBoxFull(new Date()));
    $("#visitDateMaster").val(formatDateBoxFull(new Date()));

    $('#payments').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        /*  url:basePath+'/outpPaymentsMoney/findMaoneyPayment?'+$("#search").serialize(),*/
        columns:[[      //每个列具体内容
            {field:'moneyType',title:'支付方式',width:'15%',align:'center'},
            {field:'paymentAmount',title:'收款',width:'15%',align:'center'},
            {field:'refundedAmount',title:'找零',width:'25%',align:'center'},
            {field: 'xiaoji', title: '小计', width: '10%', align: 'center'}
        ]]/*,onLoadSuccess:function(index,row){

                $('#payments').datagrid('appendRow', {
                    moneyType: '合计',
                    paymentAmount: compute("payments","paymentAmount") ,
                    refundedAmount: compute("payments","refundedAmount") ,
                    xiaoji: compute("payments","xiaoji")
                });
            }*/


    });

    $("#itemsTables").datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
      method: 'GET',
        /*   url: basePath + '/outpBillItems/findItems?' + $("#search").serialize(),*/
        columns: [[      //每个列具体内容
            {field: 'subjCode', title: '项目',formatter:subjcodeFormatter, width: '15%', align: 'center'},
            {field: 'costs', title: '金额', width: '15%', align: 'center'},
            {field: 'charges', title: '应收金额', width: '25%', align: 'center'}
        ]]/*,onLoadSuccess:function(index,row){
                $('#itemsTables').datagrid('appendRow', {
                    subjcode: '合计',
                    costs: compute("itemsTables","costs"),
                    charges:  compute("itemsTables","charges")
                });


        }*/
    });





});


//结账确认
function save(){
    $('#acctNo').val(accNo);
    $("#visitDateMaster").val(formatDatebox(new Date()));
    var  paymentsRows=$('#payments').datagrid('getRows');
    var paymentsJson=JSON.stringify(paymentsRows);
    var itemsRows  = $('#itemsTables').datagrid('getRows');
    var itemsJson = JSON.stringify(itemsRows);
    var masterFrom=fromJson('searchform');
    masterFrom = masterFrom.substring(0, masterFrom.length - 1);
    var submitJson=masterFrom+",\"outpAcctMoneyList\":"+paymentsJson+",\"outpAcctDetailList\":"+itemsJson+"}";
    $.postJSON(basePath+'/outpAcctMaster/save',submitJson,function(data){
        if(data.data=="success"){
            $.messager.alert("提示消息","收费结账成功");
            $('#payments').datagrid('load', { total: 0, rows: [] });
            $('#itemsTables').datagrid('load', { total: 0, rows: [] });
            $('#searchform').form('load',"");
        }else{
            $.messager.alert('提示',"收费结账失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"收费结账失败", "error");
    })

}



//求和
function compute(tableName,colName) {
    var rows = $('#'+tableName).datagrid('getRows');
    var total = 0;
    for (var i = 0; i < rows.length; i++) {

        if(isNaN(parseFloat(rows[i][colName]))){
            total += 0;
        }else{
            total += parseFloat(rows[i][colName]);
        }
    }
    return total;
}

//结账累计
function searchAcct(){
    var tableJson=fromJson('northForm');
    $.postJSON(basePath+'/oupRcptMaster/findCharge',tableJson,function(data){
        if(data !=null){
            $('#payments').datagrid({url:basePath+'/outpPaymentsMoney/findMaoneyPayment?'+$("#northForm").serialize() });
            $('#itemsTables').datagrid({url:basePath + '/outpBillItems/findItems?' + $("#northForm").serialize() });
            $('#searchform').form('load',data);

        }else{
            $.messager.alert('提示',"收费没有未结账的数据", "error");
        }
    },function(data){
        $.messager.alert('提示',"收费结账加载失败", "error");
    })

}