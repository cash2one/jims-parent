
//页面加载
$(function(){
    $('#regiForm').form('load', basePath+'/clinicMaster/registerFeeForm');

    $('#centerList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/clinicMaster/feeItemList',
        columns:[[      //每个列具体内容
            {field:'item',title:'会计科目',width:'15%',align:'center'},
            {field:'idNo',title:'实收金额',width:'15%',align:'center'},
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function(){

        }
    });
    $('#dataList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/clinicMaster/payWayList',
        columns:[[      //每个列具体内容
            {field:'payWay',title:'支付方式',width:'15%',align:'center'},
            {field:'totalFee',title:'收款金额',width:'15%',align:'center'},
            {field:'returnFee',title:'退号金额',width:'15%',align:'center'},
            {field:'clinicCharge',title:'实收小计',width:'15%',align:'center'},
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function(){

        }
    });

});



function clearForm(){
    $("#masterForm").form('clear');
}



