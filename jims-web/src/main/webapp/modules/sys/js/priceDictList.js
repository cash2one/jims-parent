$(function () {
    var bill = $("#bill").val();
    $('#aa').combobox({
        url: basePath + '/dict/findType/' + bill,
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    var tally = $("#TALLY").val();
    $('#bb').combobox({
        url: basePath + '/dict/findType/' + tally,
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    var outp = $("#OUTP").val();
    $('#cc').combobox({
        url: basePath + '/dict/findType/' + outp,
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    var inp = $("#INP").val();
    $('#dd').combobox({
        url: basePath + '/dict/findType/' + inp,
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    var mr = $("#MR").val();
    $('#ee').combobox({
        url: basePath + '/dict/findType/' + mr,
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    var reck = $("#RECK").val();
    $('#ff').combobox({
        url: basePath + '/dict/findType/' + reck,
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
        } else {
            $("#clinicDict").val(0);
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
});
function saveDict() {
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

function ItemClass() {
    var code = $("#aa").combobox("getValue");
    alert(code);
    if (code != "") {
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
    }
}
