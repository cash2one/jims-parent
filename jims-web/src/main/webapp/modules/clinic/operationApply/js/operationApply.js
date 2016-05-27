var editRow=undefined;
var rowNum=-1;
$(function(){
    var cId=$("#clinicMasterId",parent.document).val();
    $("#clinicId").val(cId);
    $.ajax({
        method:"POST",
        url:basePath+"/operatioinOrder/getScheduleOut",
        contentType:"application/json",
        data: clinicId= cId ,
        dataType: 'json',
        success: function(data){
            $('#operation').form('load',data);
        }
    });
    $('#operationName').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        method:'POST',
        url: basePath+'/operatioinOrder/getOperationName?clinicId='+cId,
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'operation', title: '拟实施手术名称', width: '70%', align: 'center', editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    idField: 'itemCode',
                    textField: 'itemName',
                    url: '/modules/operation/js/clinic_data.json',
                    columns: [[
                        {field: 'itemCode', title: '项目代码', width: '20%', align: 'center'},
                        {field: 'itemName', title: '项目名称', width: '20%', align: 'center'},
                        {field: 'inputCode', title: '拼音输入码', width: '10%', align: 'center', editor: 'text'},
                        {field: 'inputCodeWb', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'}
                    ]],
                    fitColumns: true
                }
            }
            },
            {field: 'operationScale', title: '等级', width: '20%', align: 'center'}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                if(rowNum>=0){
                    rowNum++;
                }
                    $("#operationName").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {}
                    });
                }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        },{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#operationName").datagrid('endEdit', rowNum);
                if (rowNum != undefined) {
                    $("#operationName").datagrid("endEdit", rowNum);
                }
                savePperationApply();
            }
        }
        ],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#operationName").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#operationName").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            var dataGrid = $('#operationName');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false
            }
            if (rowNum != rowIndex) {
                if (rowNum >= 0) {
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum = rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);

            }
        }


    });
});



/**
 * 保存申请记录
 * @param id
 */
function savePperationApply() {
    var  rows=$('#operationName').datagrid('getRows');
    var formJson=fromJson('operation');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"scheduledOperationNameList\":"+tableJson+"}";

    $.postJSON(basePath+'/operatioinOrder/saveOut',submitJson,function(data){
        if(data=="1"){
            $.messager.alert("提示消息",data+"条记录，保存成功");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}

//删除
function doDelete(){
    var selectRows = $('#operationName').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            if(strIds=='undefined'|| strIds==''){
               var index1= $('#operationName').datagrid('getRowIndex', $("#operationName").datagrid('getSelected'))
                $('#operationName').datagrid('deleteRow',index1);
            }else{

            //真删除数据
            $.ajax({
                'type': 'POST',
                'url': basePath+'/operatioinOrder/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data==1){
                        $.messager.alert("提示消息",data+"条记录删除成功！");
                        $('#operationName').datagrid('load');
                        $('#operationName').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
            }
        }
    })
}
