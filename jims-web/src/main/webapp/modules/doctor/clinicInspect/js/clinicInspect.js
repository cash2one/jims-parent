var clinicId = parent.clinicMaster.id;
var patientId = parent.clinicMaster.patientId;
var description = []; 
var diagnosisTypeClinic = [{"value": "1", "text": "中医"}, {"value": "2", "text": "西医"}];

function diagnosisTypeClinicformatter(value) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < diagnosisTypeClinic.length; i++) {
        if (diagnosisTypeClinic[i].value == value) {
            return diagnosisTypeClinic[i].text
        }
    }
}
function onloadMethod() {
    //$("#patientId").val(patientId);
    //$("#clinicId").val(clinicId);
    $("#saveBut").hide();
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
        url: basePath + '/clinicInspect/list',
        queryParams: {'clinicId': clinicId},
        remoteSort: false,
        //idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'examSubClass', title: '检查项目', width: '25%', align: 'center', formatter: examClassFormatter},
            //{field: 'reqDept', title: '开单科室', width: '25%', align: 'center',formatter:performedBFormatter},
            {field: 'performedBy', title: '执行科室', width: '25%', align: 'center', formatter: clinicDeptCodeFormatter},
            {
                field: 'regPrnFlag',
                title: '状态',
                width: '23%',
                align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    if (rowData.regPrnFlag == 0) {
                        return "未确认";
                    }
                    if (rowData.regPrnFlag == 1) {
                        return "已确认";
                    }
                }
            },
            {
                field: 'id',
                title: '操作',
                width: '38%',
                align: 'center',
                formatter: function (value, row, index) {
                    //= '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>' +
                    //var html= '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\'' + row.id + '\',\'' + row.type + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>' +
                    var html = '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                    return html;
                }
            }
        ]],
        view: detailview,
        detailFormatter: function (rowIndex, rowData) {
            var detailHtml = "<table style='width:100%;color:blue' border='0'><tr><td><strong>检查项目：</strong></td></tr>";
            $.ajax({
                type: "POST",
                url: basePath + "/clinicInspect/getItemName",
                contentType: 'application/json',
                data: appointsId = rowData.id,
                async: false,
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (i, list) {
                        detailHtml += "<tr><td>" + list.examItem + "</td></tr>";
                    });
                }
            })
            detailHtml += "</table>";
            return detailHtml;
        },
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '新增检查',
            iconCls: 'icon-add',
            handler: function () {
                add();
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
}

//新增检验
function add() {
    $("#saveBut").hide();
    $('#examClassNameId').removeAttr("disabled");
    $('#examSubclassNameId').removeAttr("disabled");
    //下拉框选择控件，下拉框的内容是动态查询数据库信息
    $('#examClassNameId').combobox({
        url: basePath + '/examClassDict/getEx',
        method: "GET",
        dataType: "json",
        valueField: 'id',
        textField: 'examClassName',
        onSelect: function (data) {
            var examClassName = data.examClassName;
            $("#performedBy").val(data.performBy);
            $("#reqDept").val(clinicDeptCodeFormatter(data.performBy, '', ''));

            //清空二级联动
            $("#examSubclassNameId").combobox("clear");

            //清空子项目div
            $("#target").empty();
            $("#descriptionId").empty();
            $.ajax({
                url: basePath + '/examClassDict/getExamSubclass',
                method: "GET",
                data: {"examClassName": examClassName},
                dataType: "json",
                success: function (data) {
                    $("#examSubclassNameId").combobox('loadData', data);
                }
            });
        }
    });
//联动下拉框 子项目
    $('#examSubclassNameId').combobox({
        valueField: 'id',
        textField: 'examSubclassName',
        onSelect: function (data) {
            $.ajax({
                url: basePath + '/examClassDict/getExamRptPattern',
                method: "GET",
                data: {"examSubClass": data.examSubclassName},
                dataType: "json",
                success: function (data) {
                    var checkbox = "";
                    var hidden = "";
                    var divHtmls = $('#target .submitName');
                    var isin = true;
                    for (var i = 0; i < data.length; i++) {
                        for (var j = 0; j < divHtmls.length; j++) {
                            if ($(divHtmls[j]).val() == data[i].description) {
                                isin = false;
                                break;
                            }
                        }
                        if (isin) {
                            var jsonHtml = "{\"examItem\":\"" + data[i].description + "\",\"examItemCode\":\"" + data[i].descriptionCode + "\"},";

                            checkbox += '<div><input class="submitName"  id="' + data[i].inputCode + i + '" type="checkbox" value="' + data[i].description + '"  >' + data[i].description + '</input><div class="submitName" style="display: none">' + jsonHtml + '</div></div>'
                        } else {
                            isin = true;
                        }

                    }
                    $("#descriptionId").html(checkbox);
                }
            });
        }
    });
    $.ajax({
        //添加
        url: basePath + "/diagnosis/findListOfOut",
        type: "GET",
        dataType: "json",
        data: {"clinicId": clinicId},
        success: function (data) {
            if (data != "" && data != null) {
                var d = "";
                $.each(data, function (index, item) {
                    formatter:var type = diagnosisTypeClinicformatter(item.type);
                    d = d + type + ":" + item.icdName + "\r";
                });
                $("#clinDiagDiv").val(d);
            }
        }
    })
    clearForm();
    $("#saveBut").show();
    $("#clinicId").val(clinicId);
    $("#patientId").val(patientId);
    $("#name").val(parent.clinicMaster.name);
    $("#sex").val(parent.clinicMaster.sex);
    $("#chargeType").val(parent.clinicMaster.chargeType);
    $("#identity").val(parent.clinicMaster.identity);

};
function clearForm() {
    $("#clinicInspectForm").form('clear');
    $("#saveBut").hide();
    //$('#items').datagrid('loadData', { total: 0, rows: [] });
}
//检查选中
function selecteds() {
    $('#descriptionId input[type=checkbox]:checked').each(function () {
        var selected = $(this).parent();
        var html = selected.prop("outerHTML");
        selected.remove();
        $("#target").append(html);
    })
};
//检查取消
function cancels() {
    $('#target input[type=checkbox]:checked').each(function () {
        var selected = $(this).parent();
        var html = selected.prop("outerHTML");
        selected.remove();
        $("#descriptionId").append(html);
    });
};
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
        'url': basePath + '/clinicInspect/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "保存失败", "error");
        }
    });
}


/**
 * 查看
 * @param id
 */
function look(id) {
    $("#fm").dialog({title: '查看'}).dialog("open");
    $("#saveBut").hide();
    $.ajax({
        'type': 'post',
        'url': basePath + '/clinicInspect/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $("#reqDept").val(function (value, rowData, rowIndex) {
                return performedBFormatter(data.performedBy, '', '');
            });
            $('#clinicInspectForm').form('load', data);
        }
    });
}

/**
 * 修改
 * @param id
 */
function get(id) {
    $.ajax({
        'type': 'post',
        'url': basePath + '/clinicInspect/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $("#modify").val("2");
            $('#clinicInspectForm').form('load', data);
        }
    });
}
//保存
function saveClinicInspect() {
    if (!$("#clinicInspectForm").form("validate")) {
        return false;
    }
    $.ajax({
        //添加
        url: basePath + "/diagnosis/findListOfOut",
        type: "GET",
        dataType: "json",
        data: {"clinicId": clinicId},
        success: function (data) {
            if (data != "" && data != null) {
                var formJson = fromJson('clinicInspectForm');
                formJson = formJson.substring(0, formJson.length - 1);
                var divJson = "";
                $('#target .submitName').each(function (index, element) {
                    divJson += $(this).html();
                })
                divJson = divJson.substring(0, divJson.length - 1);
                if (divJson != "" && divJson != null) {
                    var submitJson = formJson + ",\"examItemsList\":[" + divJson + "]}";
                    var url = "";
                    url = basePath + "/clinicInspect/saveExamAppoints";
                    $.postJSON(url, submitJson, function (data) {
                        if (data.code == "1") {
                            $.messager.alert("提示信息", "保存成功");
                            $('#list_data').datagrid('load');
                            $("#clinicInspectForm").form('clear');
                            $("#target").empty();
                            $("#descriptionId").empty();
                        } else {
                            $.messager.alert("提示信息", "保存失败", "error");
                        }

                    }), function (data) {
                        $.messager.alert("提示信息", "保存失败", "error");
                    }
                }else{
                    $.messager.alert("提示信息", "请选择需要的检查项目");
                }
            } else {
                $.messager.alert("提示信息", "病人没有诊断信息，不能开出检查申请");
            }
        }
    })


}

