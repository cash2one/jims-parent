var editRow = undefined;
var rowNum = -1;
var visitIds = parent.patVisit.visitId;
var patientIds = parent.patVisit.patientId;
function onloadMethod() {
    var deptCode ;
    $("#visitId").val(visitIds);
    $("#patientId").val(patientIds);
    /**
     * 病人科室
     */
    $("#deptCode").combobox({
        data:clinicDeptCode,
        valueField:'id',
        textField:'dept_name',
        onSelect:function(n,o){
            $("#deptCodeId").val(n.id);
            deptCode=n.id;
        }
    });
    //病人列表
    $('#patient').datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath + '/operatioinOrder/findPat?deptCode=' + deptCode,
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
                method: "POST",
                url: basePath + "/operatioinOrder/getScheduleIn",
                contentType: "application/json", //必须有
                dataType: 'json',
                data: JSON.stringify({"patientId": row.patientId, "visitId": row.visitId}),
                success: function (data) {
                    $('#operation').form('load', data);
                }
            });

            $.ajax({
                method: "POST",
                url: basePath + "/operatioinOrder/getScheduleOut",
                contentType: "application/json",
                data: clinicId = cId,
                dataType: 'json',
                success: function (data) {
                    $('#operation').form('load', data);
                }
            });
            $('#operationName').datagrid({
                rownumbers: true,
                singleSelect: true,
                fit: true,
                method: 'POST',
                url: basePath + '/operatioinOrder/getOperationName?patientId=' + row.patientId + '&visitId=' + row.visitId,
                idField: 'id',
                columns: [[      //每个列具体内容
                    {
                        field: 'operation', title: '拟实施手术名称', width: '48%', align: 'center', formatter: operationNameFormatter
                        , editor: {
                        type: 'combogrid',
                        options: {
                            panelWidth: 500,
                            data: operation,
                            idField: 'operation_code',
                            textField: 'operation_name',
                            //url: '/modules/operation/js/clinic_data.json',
                            columns: [[
                                {field: 'operation_code', title: '项目代码', width: '20%', align: 'center'},
                                {field: 'operation_name', title: '项目名称', width: '20%', align: 'center'},
                                {field: 'input_code', title: '拼音输入码', width: '20%', align: 'center', editor: 'text'},
                                //{field: 'input_code', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'}
                            ]],
                            fitColumns: true
                        }
                    }
                    },
                    {field: 'schedule', title: '等级', width: '50%', align: 'center',formatter:function(value,rowData,rowIndex){
                        if(rowData.schedule == undefined){
                            return '';
                        }else{
                            if(operationScale == undefined){
                                return '';
                            }else{
                                return operationScaleFormatter(rowData.schedule.operationScale,'','');
                            }
                        }

                    }}
                ]],
                toolbar: [{
                    text: '添加',
                    iconCls: 'icon-add',
                    handler: function () {
                        if (rowNum >= 0) {
                            rowNum++;
                        }
                        $("#operationName").datagrid("insertRow", {
                            index: 0, // index start with 0
                            row: {
                            }
                        });
                    }
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        doDelete();
                    }
                }, {
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: function () {
                        $("#operationName").datagrid('endEdit', rowNum);
                        if (rowNum != undefined) {
                            $("#operationName").datagrid("endEdit", rowNum);
                        }
                        savePperationApply();
                    }
                }
                ],
                //onAfterEdit: function (rowIndex, rowData, changes) {
                //    editRow = undefined;
                //}, onDblClickRow: function (rowIndex, rowData) {
                //    if (editRow != undefined) {
                //        $("#operationName").datagrid('endEdit', editRow);
                //    }
                //    if (editRow == undefined) {
                //        $("#operationName").datagrid('beginEdit', rowIndex);
                //        editRow = rowIndex;
                //    }
                //},
                onClickRow: function (rowIndex, rowData) {
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
        }
    });

    //手术室下拉框
    $('#operatingRoom').combobox({
        data: operatingRoom,
        valueField: 'deptCode',
        textField: 'deptName',
        onSelect: function (n, o) {
            $("#operatingRoomCode").val(n.deptCode);
            comboboxLoad(n.deptCode, 'operatingRoomNo', 'operatingRoomNoCode');
        }
    });

    /**
     * 麻醉方式
     */
    $("#anesthesiaMethod").combobox({
        data: anaesthesiaName,
        valueField: 'id',
        textField: 'label',
        onSelect: function (n, o) {
            $("#anesthesiaMethodId").val(n.value);
        }
    });
    /**
     * 手术科室
     */
    $('#operatingDept').combobox({
        data:clinicDeptCode,
        valueField:'id',
        textField:'dept_name',
        onSelect:function(data){
            $("#operatingDeptId").val(data.id);
        }
    })

    $("#provideWay").combobox({
        data:provideWay,
        valueField:'id',
        textField:'label',
        onSelect:function(data){
            $("#provideWayId").val(data.value);
        }
    })
    /**
     * 手术病情
     */
    $("#patientCondition").combobox({
        data:patientCondition,
        valueField:'id',
        textField:'label',
        onSelect:function(n,o){
            $("#patientConditionId").val(n.value);
        }
    })

    /**
     * 隔离
     */
    $("#isolationIndicator").combobox({
        data:isolationIndicator,
        valueField:'id',
        textField:'label',
        onSelect:function(n,o){
            $("#isolationIndicatorId").val(n.value);
        }
    })

    /**
     * 手术等级
     */
    $("#operationScale").combobox({
        data: operationScaleName,
        valueField: 'id',
        textField: 'label',
        onSelect: function (n, o) {
            $("#operationScaleId").val(n.value);
        }
    });
};

//增加手术名称
function save() {
    $("#operationName").datagrid('endEdit', rowNum);
    var rows = $('#operationName').datagrid('getRows');
    var formJson = fromJson('operation');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson = JSON.stringify(rows);
    var submitJson = formJson + ",\"scheduledOperationNameList\":" + tableJson + "}";
    $.postJSON(basePath + '/operatioinOrder/saveIn', submitJson, function (data) {
        if (data == "1") {
            $.messager.alert("提示消息", data + "条记录，保存成功");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })


}

//删除
function doDelete() {
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
            if (strIds == 'undefined' || strIds == '') {
                var index1 = $('#operationName').datagrid('getRowIndex', $("#operationName").datagrid('getSelected'))
                $('#operationName').datagrid('deleteRow', index1);
            } else {
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