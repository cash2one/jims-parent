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
            {field:'remarks'
                ,formatter:function(value, rowData, rowIndex){
                if(rowData.orderNo!=rowData.orderSubNo){
                    return "<div style='color:blue;font-weight:bold; '>子</div>";
                }else{
                    return "";
                }
            }},
            {field: 'orderStatus', title: '执/停', width: '5%', align: 'center',formatter:function(value, row, index){}
            },
            {field: 'bedNo', title: '床号', width: '5%', align: 'center'},
            {field: 'name', title: '姓名', width: '5%', align: 'center'},
            {field: 'orderClass', title: '类别', width: '5%', formatter:orderClassFormatter,align: 'center'},
            {field: 'startDateTime', title: '下达医嘱时间', width: '10%', align: 'center',formatter:formatDateBoxFull},
            {field: 'orderText', title: '医嘱内容', width: '10%', align: 'center'},
            {field: 'billingAttr', title: '自', width: '5%', align: 'center',formatter:billingAttrFormatter},
            {field: 'dosage', title: '剂量', width: '5%', align: 'center'},
            {field: 'dosageUnits', title: '单位', width: '5%', align: 'center'},
            {field: 'administration', title: '途径', width: '5%',formatter:administrationFormatter, align: 'center'},
            {field: 'freqCounter', title: '频次', width: '5%',formatter:performFreqFormatter, align: 'center'},
            {field: 'freqDetail', title: '医生说明', width: '10%', align: 'center'},
            {field:'doctor',title:'开医嘱医生',width:'10%',align:'center'},
            {field: 'stopDateTime', title: '停止时间', width: '10%', align: 'center',formatter:formatDateBoxFull}

        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],toolbar: [{
            text: '转抄',
            iconCls:'icon-save',
            handler:function(){
                $("#orderCopied").datagrid('endEdit', editRow);
                if (editRow != undefined) {

                    $("#orderCopied").datagrid("endEdit", editRow);
                }
                operationCopied();
            }
        }]
    });
    $("#submit_search").click(function () {
        $('#orderCopied').datagrid({url:basePath + '/ordersNurse/findOrdersCopied?' + $('#searchform').serialize() });   //点击搜索
    });
});
//设置分页控件
    var p = $('#orderCopied').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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