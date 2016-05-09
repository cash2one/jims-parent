/**
 * Created by Administrator on 2016/5/7.
 */
$(function () {
    init_clinic_data()
    load_data()
    $('#aa').combobox({
        url: basePath + '/dict/findType/' + 'BILL_ITEM_CLASS_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    $('#bb').combobox({
        url: basePath + '/dict/findType/' + 'TALLY_SUBJECT_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });

    $('#cc').combobox({
        url: basePath + '/dict/findType/' + 'OUTP_RCPT_FEE_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    $('#dd').combobox({
        url: basePath + '/dict/findType/' + 'INP_RCPT_FEE_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });

    $('#ee').combobox({
        url: basePath + '/dict/findType/' + 'MR_FEE_CLASS_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });

    $('#ff').combobox({
        url: basePath + '/dict/findType/' + 'RECK_ITEM_CLASS_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });

    $('#dt').datetimebox({
        showSeconds: false
    });
    $("#performedBy").combogrid({
        panelWidth: 300,
        idField: 'deptCode',
        textField: 'deptName',
        mode: 'local',
        method: 'GET',
        url: basePath + '/dept-dict/list',
        columns: [[
            {field: 'deptCode', title: '科室编码', width: 100},
            {field: 'deptName', title: '科室名称', width: 100},
            {field: 'inputCode', title: '拼音码', width: 100}
        ]], filter: function (q, row) {

            return row.inputCode.indexOf(q) == 0;
        }


    });

    $("#feeTypeMask").on("click", function () {
        console.log($("#feeTypeMask").prop("checked"));
        if ($("#feeTypeMask").prop("checked") == true) {
            $("#feeTypeMask").val(1);
        } else {
            $("#feeTypeMask").val(0);
        }
    });

    $("#clinicDict").on("click", function () {
        console.log($("#clinicDict").prop("checked"));
        if ($("#clinicDict").prop("checked") == true) {
            $("#clinicDict").val(1);
            alert(1)
        } else {
            $("#clinicDict").val(0);
            alert(0);
        }
    });

    $("#generate").on("click", function () {
        var code = $("#aa").combobox("getValue");
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/code/' + code,
            'success': function (data) {
                $("#itemCode").textbox('setValue', data.data);
                $.messager.alert('提示', "成功", "success");

            },
            'error': function (data) {
                $.messager.alert('提示', "获取失败", "error");
            }
        });
    });
    // 刷新当前标签页
    $("#refresh").on("click", function () {
        window.location.reload();
    });
    // 关闭当前标签页
    $("#cancel").on("click", function () {
        location.window.close();
    });
    function init_clinic_data() {
        $("#clinic_item").datagrid({
            title: "价表管理列表",
            //iconCls: 'icon-save', //图标
            url: basePath +'/price/find',
            method:'get',
            idField: 'id',
            fit: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            pageSize: 10, //页大小
            pageList: [10, 15, 20, 25], //页大小下拉选项此项各value是pageSize的倍数
            fitColumns: true, //列自适应宽度
            columns: [[//显示的列
                {field: 'itemCode', title: '项目代码', width: 120},
                {field: 'itemName', title: '项目名称', width: 120},
                {field: 'itemSpec', title: '项目规格', width: 120},
                {field: 'materialCode', title: '物价码', width: 120},
                //{field: 'materialCode', title: '类别', width: 60},
                {field: 'itemClass', title: '项目类别', width: 120},
                {field: 'inputCode', title: '拼音码', width: 120},
                {field: 'inputCodeWb', title: '五笔码', width: 120}
            ]],
           /* toolbar: [{
                text: '修改',
                iconCls: 'icon-edit',
                handler: function () {
                    var selectRows = $('#clinic_item').datagrid("getSelections");
                    if (selectRows.length < 1) {
                        $.messager.alert("提示消息", "请选中要修改的数据!");
                        return;
                    }
                    get(selectRows[0].id);
                }
            }, '-', {
                text: '批量删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete();
                }
            }],*/
            toolbar: '#tb',
           /* frozenColumns: [[
                {field: 'ck', checkbox: true}
            ]],*/
            queryParams: {
                inputCode:'inputCode'
            }

        });
    }
});

/**
 * 修改字典
 * @param id
 */
function get(id) {
    $.ajax({
        'type': 'post',
        'url': basePath + '/price/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#pro_form').form('load', data);
            $('#add_pro_win').window({
                title: '修改价表项目',
                width: '350',
                height: '200',
                resizable: false
            })
        }
    });
}

//批量删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#clinic_item').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }

    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            //strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
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
        'url': basePath + '/price/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#clinic_item').datagrid('load');
                $('#clinic_item').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}

function saveDict() {
    console.log($("#clinicDict").val());
    $.postForm(basePath + "/price/save", "prescForm", function (data) {
        if (data.data == 'success') {
            $.messager.alert("提示消息", "保存成功", "success");
        } else {
            $.messager.alert('提示消息', data.code, "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })
}

function ShowInfo() {
    var oDiv = $("#itemName").val();
    if (oDiv.value != "") {
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/abb/' + oDiv,
            'success': function (data) {
                $("#inputCode").textbox('setValue', data.code);

            }
        });
    } else {
        $("#inputCode").textbox('setValue', "");
    }
}
/**
 *  加载数据
 */
function load_data() {
    var inputCode = $('#code_gps').val();
    $.ajax({
        'type': 'GET',
        'url': basePath + '/price/find/' + inputCode,
        'success': function (data) {
            $("#clinic_item").datagrid('loadData',data);

        }
    });
}

