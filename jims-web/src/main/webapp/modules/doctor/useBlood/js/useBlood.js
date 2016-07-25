var rowNum = -1;
var visitId = parent.patVisit.visitId;
var patientId = parent.patVisit.patientId;
/**
 * 设置动态行
 * @param id
 */

//用血申请记录列表
function onloadMethod() {
    $("#visitId").val(visitId);
    $("#patientId").val(patientId);
    $("#patName").val(parent.patVisit.name);
    $("#patSex").val(parent.patVisit.sex);
    $("#feeType").val(chargeTypeFormatter(parent.patVisit.chargeType, '', ''));
    $("#feeTypeId").val(parent.patVisit.chargeType);
    $("#applyDate").datetimebox("setValue", formatDateBoxFull(new Date));

    $('#list_doctor').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method: 'post',
        columns: [[
            {field: 'id', title: 'id', hidden: true, align: 'center'},
            {
                field: 'fastSlow', title: '用血方式', width: '20%', align: 'center', editor: {
                type: 'combobox',
                options: {
                    data: fastSlo,
                    valueField: 'value',
                    textField: 'text',
                    required: true
                }
            }, formatter: fastSloFormatter
            },
            //每个列具体内容
            {
                field: 'transDate', title: '预订输血时间', width: '20%', align: 'center',
                editor: {
                    type: "datetimebox",
                    options: {
                        required: true
                    }
                }
            },
            {field: 'transCapacity', title: '血量', width: '20%', align: 'center', editor: 'text'},
            {
                field: 'unit', title: '单位', width: '20%', align: 'center', editor: {
                type: 'combobox',
                options: {
                    data: units,
                    valueField: 'value',
                    textField: 'text',
                    required: true
                }
            }, formatter: unitsFormatter
            },
            {
                field: 'bloodType', title: '血液要求', width: '20%', align: 'center', editor: {
                type: 'combobox',
                options: {
                    data: bloodTypeName,
                    valueField: 'blood_type',
                    textField: 'blood_type_name',
                    required: true
                }
            }, formatter: bloodTypeNameFormatter
            },
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                if (!$("#list_doctor").datagrid("validateRow", rowNum)) {
                    $.messager.alert('提示', "请填写完本行数据后，再添加", "Warning")
                    return false;
                }
                if (rowNum >= 0) {
                    rowNum++;
                }
                $("#list_doctor").datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {}
                });
                rowNum = 0;
                $("#list_doctor").datagrid("beginEdit", rowNum);
            }
        }, {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                inDoDelete();
            }
        },
        ],

        onClickRow: function (rowIndex, rowData) {
            var dataGrid = $('#list_doctor');
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

    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bloodApply/listHos',
        QueryParams: {'visitId': visitId, 'patientId': patientId},
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            //{field: 'deptCode', title: '科室', width: '18%', align: 'center', formatter: clinicDeptCodeFormatter},
            {field: 'bloodInuse', title: '血源', width: '18%', align: 'center', formatter: bloodInusesFormatter},
            //{field: 'bloodDiagnose', title: '诊断', width: '18%', align: 'center'},
            {field: 'preBloodType', title: '血型', width: '18%', align: 'center'},
            //{field: 'bloodInuse', title: '方式', width: '18%', align: 'center'},
            {field: 'applyDate', title: '申请时间', width: '30%', align: 'center', formatter: formatDateBoxFull},
            {
                field: 'id', title: '操作', width: '40%', align: 'center', formatter: function (value, row, index) {
                var state = "1";
                var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="getBloodApply(\'' + row.id + '\',\'' + state + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>' +
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="getBloodApply(\'' + row.id + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>' +
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                return html;
            }
            }
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [
            //{
            //text: '修改',
            //iconCls: 'icon-edit',
            //handler: function () {
            //    var selectRows = $('#list_data').datagrid("getSelections");
            //    if (selectRows.length < 1) {
            //        $.messager.alert("提示消息", "请选中需要修改的数据");
            //        return;
            //    }
            //    get(selectRows[0].id);
            //}
            //},
            '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete();
                }
            }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

    $("#patBloodGroup").combobox({
        data: bloodType,
        valueField: 'value',
        textField: 'label',
        required: true,
        onSelect: function (n) {
            $("#patBloodGroupId").val(n.value);
        }
    })
    $("#preBloodType").combobox({
        data: bloodType,
        valueField: 'value',
        textField: 'label',
        onSelect: function (n) {
            $("#preBloodTypeId").val(n.value);
        }
    })
    /**
     * 属地
     */
    $("#patSource").combobox({
        data: patSource,
        valueField: 'value',
        textField: 'text',
        onSelect: function (data) {
            $("#patSourceId").val(data.value);
        }
    })
    /**
     * bloodInuse
     * 血源
     */
    $("#bloodInuse").combobox({
        data: bloodInuses,
        valueField: 'value',
        textField: 'text',
        onSelect: function (data) {
            $("#bloodInuseId").val(data.value);
        }
    })
}
/**
 * 保存
 * @param id
 */
function saveUseBloodApply() {
    $.ajax({
        //添加
        url: basePath + "/diagnosis/findListOfIn",
        type: "GET",
        dataType: "json",
        data: {"patientId": patientId, "visitId": visitId},//住院visitId不为null
        success: function (data) {
            if (data != "" && data != null) {
                $("#list_doctor").datagrid("endEdit", rowNum);
                var rows = $('#list_doctor').datagrid('getRows');
                var formJson = fromJson('useBloodForm');
                formJson = formJson.substring(0, formJson.length - 1);
                var tableJson = JSON.stringify(rows);
                var submitJson = formJson + ",\"bloodCapacityList\":" + tableJson + "}";
                //$("#inpNo").attr("value", "123");
                if (rows.length > 0) {
                    $.postJSON(basePath + "/bloodApply/saveHos", submitJson, function (data) {
                        if (data == "1") {
                            $.messager.alert("提示信息", data + "条记录，保存成功");
                            $('#list_data').datagrid('load');
                            $('#list_data').datagrid('clearChecked');
                            $("#useBloodForm").form("clear");
                        } else {
                            $.messager.alert("提示信息", "保存失败", "error");
                        }

                    }), function (data) {
                        $.messager.alert("提示信息", "保存失败", "error");
                    }
                } else {
                    $.messager.alert("提示信息","请添加用血方式");
                }
            } else {
                $.messager.alert("提示信息", "病人没有诊断信息，不能开出用血申请");
            }
        }
    })

}
//批量删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            //真删除了  1,3,4
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
        }
    })
}
//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}

/**
 * 删除方法
 * @param id
 */
function del(id) {
    $.ajax({
        'type': 'POST',
        'url': basePath + '/bloodApply/delHos',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                if (data.code > 0) {
                    $.messager.alert("提示消息", data.code + "条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_doctor').datagrid('loadData', {total: 0, rows: []});
                    $("#useBloodForm").form("clear");
                } else {
                    $.messager.alert('提示', "删除失败", "error");
                }
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}
/**
 * 显示修改
 * @param data
 */
function getBloodApply(id, state) {
    if (state == "1") {
        $("#saveUseBlood").hide();
    }
    else {
        $("#saveUseBlood").show();
    }
    $.ajax({
        'type': 'post',
        'url': basePath + '/bloodApply/getBloodApply',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#useBloodForm').form('load', data);
            var applyNum = data.applyNum;
            $('#list_doctor').datagrid({
                url: basePath + "/bloodApply/getBloodCapacityList",
                queryParams: {'applyNum': applyNum},
                method: "post"
            });
        }
    })
}
/**
 * 删除动态行
 * @param data
 */
function inDoDelete() {
    var rows = $('#list_doctor').datagrid("getSelections");
    if (rows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    var copyRows = [];
    for (var j = 0; j < rows.length; j++) {
        copyRows.push(rows[j]);
    }
    for (var i = 0; i < copyRows.length; i++) {
        if (typeof(copyRows[i].id) != "undefined") {
        }
        var index = $('#list_doctor').datagrid('getRowIndex', copyRows[i]);
        $('#list_doctor').datagrid('deleteRow', index);
    }
}

