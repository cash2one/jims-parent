
//页面加载
$(function(){
    var patientId='1';
    $('#centerList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        title:'与当前登记病人重名记录',
        toolbar:'#searchDiv',
        method:'GET',
        url:basePath+'/prepaymentRcpt/list?patientId='+patientId,
        rownumbers:true,
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:'true'},
            {field:'rcptNo',title:'编号',width:'5%',align:'center'},
            {field:'transactDate',title:'日期及时间',width:'15%',align:'center'},
            {field:'amount',title:'金额',width:'10%',align:'right'},
            {field:'payWay',title:'支付方式',width:'10%',align:'center'},
            {field:'transactType',title:'类型',width:'5%',align:'center'},
            {field:'bank',title:'开户银行',width:'15%',align:'center'},
            {field:'checkNo',title:'支票号',width:'10%',align:'center'},
            {field:'bankCode',title:'银行账户',width:'15%',align:'center'},
            {field:'operatorNo',title:'收款员',width:'5%',align:'center'},
            {field:'refundedRcptNo',title:'退款号',width:'10%',align:'center'}
        ]], onClickRow: function (index, row) {
            //$("#payForm").form('load',row);
        }, onLoadSuccess: function(){

        }

    });
    //设置分页控件
    var p = $('#centerList').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
});


//列表条件查询
function searchByCondition(){

}

//保存
function saveMaster() {
    $.postForm(basePath + '/prepaymentRcpt/save', 'payForm', function (data) {
        if (data.data == 'success') {
            $.messager.alert("提示消息", data.code + "条记录，保存成功");
            $('#centerList').datagrid('load');
            $('#centerList').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    });
}
//退款
function removeMaster(){
    var selRow = $('#centerList').datagrid('getChecked');
    if(selRow!=null && selRow!='' && selRow!=undefined){
        $.messager.alert('提示',"本人收取的未结账预交金，不能进行退款操作！", "warning");
    }else{
        $.messager.alert('提示',"请选择要退款的记录！", "warning");
    }
}
//作废
function updatePay(){
    var selRow = $('#centerList').datagrid('getChecked');
    if(selRow!=null && selRow!='' && selRow!=undefined){
       var id = selRow[0].id;
        $.messager.confirm("操作提示", "是否要作废"+selRow[0].amount+"--"+selRow[0].payWay+"该记录？", function (data) {
            if (data) {
                $.ajax({
                    'type': 'POST',
                    'url': basePath+'/prepaymentRcpt/update',
                    'contentType': 'application/json',
                    'data': id=id,
                    'dataType': 'json',
                    'success': function(data){
                        if(data.data=='success'){
                            $.messager.alert("提示消息",data.code+"条记录作废成功！");
                            $('#centerList').datagrid('load');
                            $('#centerList').datagrid('clearChecked');
                        }else{
                            $.messager.alert('提示',"作废失败", "error");
                        }
                    },
                    'error': function(data){
                        $.messager.alert('提示',"作废失败", "error");
                    }
                });

            }
        });
    }else{
        $.messager.alert('提示',"请选择要作废的记录！", "warning");
    }
}
//清屏
function clearForm(){
    $("#payForm").form('clear');
}





