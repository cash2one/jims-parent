var editRow = undefined;
var rowNum=-1;
$(function() {
    $('#orderCopied').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method: 'get',
        url: basePath + '/ordersNurse/findOrdersCopied?' + $('#searchform').serialize(),
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field: 'orderStatus', title: '执/停', width: '5%', align: 'center',formatter:function(value, row, index){}
            },
            {field: 'bedNo', title: '床号', width: '5%', align: 'center'},
            {field: 'name', title: '姓名', width: '5%', align: 'center'},
            {field: 'orderClass', title: '类别', width: '5%', align: 'center'},
            {field: 'startDateTime', title: '下达医嘱时间', width: '10%', align: 'center',formatter:formatDateBoxFull},
            {field: 'orderText', title: '医嘱内容', width: '10%', align: 'center'},
            {field: 'billingAttr', title: '自', width: '5%', align: 'center'},
            {field: 'dosage', title: '剂量', width: '5%', align: 'center'},
            {field: 'dosageUnits', title: '单位', width: '5%', align: 'center'},
            {field: 'administration', title: '途径', width: '5%', align: 'center'},
            {field: 'freqCounter', title: '频次', width: '5%', align: 'center'},
            {field: 'freqDetail', title: '医生说明', width: '10%', align: 'center'},
            {field:'doctor',title:'开医嘱医生',width:'10%',align:'center'},
            {field: 'stopDateTime', title: '停止时间', width: '10%', align: 'center',formatter:formatDateBoxFull}

        ]],
        rowStyler: function (index, row) {
            if (row.orderStatus == '1') {
                return 'color:black;';
            } else if (row.orderStatus == "2") {
                return 'color:blue;';
            } else if (row.orderStatus == "3") {
                return 'color:yellow;';
            } else if (row.orderStatus == "4") {
                return 'color:red;';
            }
        }
    });
    $("#submit_search").linkbutton({iconCls: 'icon-search', plain: true}).click(function () {
        $('#orderCopied').datagrid("load");   //点击搜索
    });
});


function operationCopied(){
   var ordersRow = $('#orderCopied').datagrid("getSelections");
    var tableJson=JSON.stringify(ordersRow);
    $.postJSON(basePath+'/ordersNurse/operationCopied',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","处理成功");
            $('#orderCopied').datagrid("load");
        }else{
            $.messager.alert('提示',"处理失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"处理失败", "error");
    })

}