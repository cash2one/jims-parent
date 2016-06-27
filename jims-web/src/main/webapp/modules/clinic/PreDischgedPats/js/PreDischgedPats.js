var rowNum = -1;
var item = [{"value": "1", "text": "挂号费"}, {"value": "2", "text": "诊疗费"}, {"value": "3", "text": "其他费"}];
var priceItme = [{"value": "11020000100", "text": "普通门诊诊查费"}, {
    "value": "11020000300",
    "text": "急诊诊查费"
}, {"value": "10005", "text": "鉴定费"},
    {"value": "11020000400", "text": "门急诊留观诊查费"}, {"value": "11020000201", "text": "副主任医师诊查费"}];
function onloadMethod() {
    var wardCode = $("#wardCode").val();
    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'post',
        collapsible: false,//是否可折叠的
        //fit: true,//自动大小
        url: basePath + '/preDischgedPats/findPreDischList?wardCode=' + wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        columns: [[      //每个列具体内容

            {field: 'hospitalId', align: 'center', hidden: 'true', editor: 'hidden'},
            {field: 'bedNo', title: '床标号', width: '20%', align: 'center', editor: 'text'},
            {
                field: 'dischargeDateExpcted',
                title: '预计出院时间',
                width: '20%',
                align: 'center',
                editor: 'text',
                formatter: formatDateBoxFull
            },
            {field: 'name', title: '姓名', width: '20%', align: 'center', editor: 'text'},
            {field: 'sex', title: '性别', width: '20%', align: 'center', editor: 'text'},
            {field: 'dischargeDispositionName', title: '出院方式', width: '20%', align: 'center', editor: 'text'},
            {field: 'patientId', title: '病人ID', width: '20%', align: 'center', hidden: 'true', editor: 'text'},
            {field: 'diagnosis', title: '诊断', width: '20%', align: 'center', editor: 'text'},
            {
                field: 'admissionDateTime',
                title: '入院日期',
                width: '20%',
                align: 'center',
                editor: 'text',
                formatter: formatDateBoxFull
            },
            {field: 'deptName', title: '所在科室', width: '20%', align: 'center', editor: 'text'},
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                if (rowNum >= 0) {
                    rowNum++;
                }
                $("#list_data").datagrid('insertRow', {
                    index: 0,
                    row: {}

                });
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                deleteItem();
            }
        }, '-', {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                $("#list_data").datagrid('endEdit', rowNum);
                save();
            }
        }
        ], onClickRow: function (rowIndex, rowData) {
            var dataGrid = $('#list_data');
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
    loadPreList(wardCode);
}
//加载右边患着信息
function loadPreList(strValue) {
    $('#list_doctor').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/preDischgedPats/list?wardCode=' + strValue,
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床标号', width: '30%', align: 'center'},
            {
                field: 'patMasterIndex',
                title: '姓名',
                width: '30%',
                align: 'center',
                formatter: function (value, row, index) {
                    return value.name;
                }
            },
            {
                field: 'patsInHospital',
                title: '操作',
                width: '40%',
                align: 'center',
                formatter: function (value, row, index) {
                    var state = "1";
                    var html =
                        '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="saveHos(\'' + value.patientId + '\',\'' + value.id + '\')"><img src="/static/images/index/icon3.png" width="16"/>出院</button>';

                    return html;
                }
            },
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]]
    });
    //设置分页控件
    var p = $('#list_doctor').datagrid('getPager');
}

//保存医嘱和通知数据
function save() {
    var rows = $('#list_data').datagrid('getRows');
    var tableJson = JSON.stringify(rows);
    $.postJSON(basePath + '/preDischgedPats/save', tableJson, function (data) {
        if (data.code == '1') {
            $.messager.alert("提示消息", data.code + "条记录，保存成功");
            $.messager.alert("提示信息", "保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $('#list_doctor').datagrid('load');
            $('#list_doctor').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })
}

//点击出院按钮保存通知数据
function saveHos(paitentId, hospitalId) {
    var rows = $('#list_data').datagrid('getRows');
    var tableJson = JSON.stringify(rows);
    $.postForm(basePath + '/preDischgedPats/savePatsVo?paitentId=' + paitentId + '&hospitalId=' + hospitalId, "preDischgedPatsForm", function (data) {
        if (data.code == '1') {
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $('#list_doctor').datagrid('load');
            $('#list_doctor').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "通知失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "通知失败", "error");
    })
}

//删除数据
function deleteItem() {
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].hospitalId + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //删除
            $.ajax({
                'type': 'POST',
                'url': basePath + '/preDischgedPats/delete?hospitalId=' + strIds,
                'contentType': 'application/json',
                //'data': id=strIds,
                'dataType': 'json',
                'success': function (data) {
                    if (data.code == '1') {
                        $.messager.alert("提示消息", data.code + "条记录删除成功！");
                        $('#list_data').datagrid('load');
                        $('#list_data').datagrid('clearChecked');
                        $('#list_doctor').datagrid('load');
                        $('#list_doctor').datagrid('clearChecked');
                    } else {
                        $.messager.alert('提示', "删除失败", "error");
                    }
                },
                'error': function (data) {
                    $.messager.alert('提示', "删除失败", "error");
                }
            });
        }
    })
}



