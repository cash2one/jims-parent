
//页面加载
$(function(){
    var date = $("#acctDate").val();
    $.get(basePath+'/clinicMaster/northFrom?date=' + date, function (data) {
        $('#northForm').form('load', data);
    });
    $('#centerList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        columns:[[      //每个列具体内容
            {field:'costs',title:'会计科目',hidden:true},
            {field:'tallyFeeClass',title:'会计科目',width:'15%',align:'center'},
            {field:'income',title:'实收金额',width:'15%',align:'center'},
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function(){

        }
    });
    $('#dataList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        columns:[[      //每个列具体内容
            {field:'moneyType',title:'支付方式',width:'15%',align:'center'},
            {field:'totalFee',title:'收款金额',width:'15%',align:'center'},
            {field:'refundedAmount',title:'退号金额',width:'15%',align:'center'},
            {field:'incomeAmount',title:'实收小计',width:'15%',align:'center'},
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function(){

        }
    });
});

function accFun(){
    var date = $("#acctDate").val();

    $.ajax({
        'type': 'POST',
        'url': basePath+'/clinicMaster/registerFeeFrom',
        'contentType': 'application/json',
        'data': date = date,
        'dataType': 'json',
        'success': function (data) {
            $('#regiForm').form('load', data);
        }
    });

    $.get(basePath+'/clinicMaster/feeItemList?date=' + date, function (data) {
        $("#centerList").datagrid("loadData", data);
    });

    $.get(basePath+'/clinicMaster/payWayList?date=' + date, function (data) {
        $("#dataList").datagrid("loadData", data);
    });
}

function confirmFun(){
    var  crows=$('#centerList').datagrid('getRows');
    var ctableJson=JSON.stringify(crows);
    var  drows=$('#dataList').datagrid('getRows');
    var dtableJson=JSON.stringify(drows);

    var nformJson=fromJson('northForm');
    nformJson =nformJson.substring(0, nformJson.length - 1);

    var formJson=fromJson('regiForm');
    formJson = formJson.substring(1, formJson.length - 1);

    var submitJson=nformJson+","+formJson+",\"acctDetails\":"+ctableJson+",\"acctMoneys\":"+dtableJson+"}";
    $.postJSON(basePath+'/registAcctMaster/save',submitJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}

function clearForm(){
    $("#regiForm").form('clear');
}



