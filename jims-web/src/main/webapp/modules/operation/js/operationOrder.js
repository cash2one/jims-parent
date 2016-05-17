var editRow = undefined;

$(function(){
    var deptCode=$("#deptCode").val();
    //病人列表
    $('#patient').datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath+'/operatioinOrder/findPat?deptCode='+deptCode,
        idField: 'patientId',
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '50%', align: 'center'},
            {field: 'name', title: '姓名', width: '50%'},
            {field: 'patientId', title: '病人Id', hidden: 'true'},
            {field: 'visitId', title: '住院Id', hidden: 'true'},
            {field: 'sex', title: '性别', hidden: 'true'}
        ]],
        onClickRow: function (index, row) {//单击行事件
            $("#bedNo").val(row.bedNo);
            $("#name").val(row.name);
            $("#diagnosis").val(row.diagnosis);
            $("#patientId").val(row.patientId);
            $("#visitId").val(row.visitId);
            $("#sex").val(row.sex);
            $.ajax({
                method:"POST",
                url:basePath+"/operatioinOrder/getScheduleIn",
                dataType: 'json',
                contentType: "application/json", //必须有
                data: JSON.stringify({"patientId":row.patientId,"visitId":row.visitId}),
                success: function(data){
                    $('#operation').form('load',data);
                }
            });

            $('#operationName').datagrid({
                rownumbers: true,
                singleSelect: true,
                fit: true,
                method:'POST',
                url: basePath+'/operatioinOrder/getOperationName?patientId='+row.patientId+'&visitId='+row.visitId,
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
                    if (editRow != undefined) {
                        $("#operationName").datagrid('endEdit', editRow);
                    }

                }
            });


        }

    });
});

//增加手术名称
function save(){
    $("#operationName").datagrid('endEdit', editRow);
    var  rows=$('#operationName').datagrid('getRows');
    var formJson=fromJson('operation');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"scheduledOperationNameList\":"+tableJson+"}";
    $.postJSON(basePath+'/operatioinOrder/saveIn',submitJson,function(data){
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
            }else {
                //真删除数据
                $.ajax({
                    'type': 'POST',
                    'url': basePath + '/operatioinOrder/delete',
                    'contentType': 'application/json',
                    'data': id = strIds,
                    'dataType': 'json',
                    'success': function (data) {
                        if (data == 1) {
                            $.messager.alert("提示消息", data + "条记录删除成功！");
                            $('#operationName').datagrid('load');
                            $('#operationName').datagrid('clearChecked');
                        } else {
                            $.messager.alert('提示', "删除失败", "error");
                        }
                    },
                    'error': function (data) {
                        $.messager.alert('提示', "删除失败", "error");
                    }
                });
            }
        }
    })
}