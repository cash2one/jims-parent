var accNo;
$(function(){
    $("#visitDate").datebox("setValue", formatDateBoxFull(new Date()));

    $.ajax({
        method:"GET",
        url:basePath+"/outpAcctMaster/getMaxAcctNo",
      /*  contentType: "application/json", //必须有
        dataType: 'json',
        data: ,*/
        success: function(data){
            accNo=data;
            $("#acctNoU").val(accNo);
            $('#acctNo').val(accNo);
        }
    });
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
        ]],onLoadSuccess:function(index,row){

                $('#payments').datagrid('appendRow', {
                    moneyType: '<span>合计</span>',
                    paymentAmount: '<span class="subtotal">' + compute("payments","paymentAmount") + '</span>',
                    refundedAmount: '<span class="subtotal">' + compute("payments","refundedAmount") + '</span>',
                    xiaoji: '<span class="xiaoji">' + compute("payments","xiaoji") + '</span>'
                });
            }


    });

    $("#itemsTables").datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
      method: 'GET',
        /*   url: basePath + '/outpBillItems/findItems?' + $("#search").serialize(),*/
        columns: [[      //每个列具体内容
            {field: 'subjcode', title: '项目', width: '15%', align: 'center'},
            {field: 'costs', title: '金额', width: '15%', align: 'center'},
            {field: 'charges', title: '应收金额', width: '25%', align: 'center'}
        ]],onLoadSuccess:function(index,row){
                $('#itemsTables').datagrid('appendRow', {
                    subjcode: '<span>合计</span>',
                    costs: '<span class="subtotal">' + compute("itemsTables","costs") + '</span>',
                    charges: '<span class="subtotal">' + compute("itemsTables","charges") + '</span>'
                });


        }
    });


    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
        searchAcct();
    });


});


//结账确认
function save(){
    $('#acctNo').val(accNo);
    var  paymentsRows=$('#payments').datagrid('getRows');
    var paymentsJson=JSON.stringify(paymentsRows);
    var itemsRows  = $('#itemsTables').datagrid('getRows');
    var itemsJson = JSON.stringify(itemsRows);
    var masterFrom=fromJson('searchform');
    masterFrom = masterFrom.substring(0, masterFrom.length - 1);
    var submitJson=masterFrom+",\"outpAcctMoneyList\":"+paymentsJson+",\"outpAcctDetailList\":"+itemsJson+"}";
    $.postJSON(basePath+'/outpAcctMaster/save',submitJson,function(data){
        if(data.data=="success"){
            $.messager.alert("提示消息","收费结账"+data.code+"结账成功");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
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