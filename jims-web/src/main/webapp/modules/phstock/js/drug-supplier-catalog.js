/**
 * Created by wei on 2016/5/10.
 */
$(function () {

    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#dg").datagrid({
        title: '药品厂商维护',
        fit: true,//让#dg数据创铺满父类容器
        toolbar: '#tb',
        singleSelect: true,
        columns: [[{
            title: '编号',
            field: 'id',
            hidden: 'true'
        }, {
            title: '供应厂商别名',
            field: 'supplierId',
            width: "10%",
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '厂商',
            field: 'supplier',
            width: "15%",
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '厂商类别',
            field: 'supplierClass',
            width: "10%",
            editor: {
                type: 'combogrid', options: {
                    editable: false,
                    width: '150px',
                    idField: 'supplierClass',
                    textField: 'supplierClass',
                    method: 'GET',
                    url: basePath + "/drug-supplier-catalog/list-type?orgId=" + parent.config.org_Id,
                    mode: 'remote',
                    columns: [[
                        {field: 'supplierClass', title: '厂商类别', width: "150px"}
                    ]]
                }
            }
        }, {
            title: '输入码',
            field: 'inputCode',
            width: "10%"

        }, {
            title: '备注',
            field: 'memo',
            width: "10%",
            editor: {
                type: 'textbox', options: {
                    disabled: false
                }
            }

        }, {
            title: '注册商标',
            field: 'trademark',
            width: "10%",
            editor: {
                type: 'textbox', options: {
                    disabled: false
                }
            }

        }, {
            title: '是否国外',
            field: 'foreignx',
            width: "10%",
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    width: '100px',
                    idField: 'value',
                    valueField: 'value',
                    textField: 'text',
                    data: [
                        {'value': '0', 'text': '是', width: "100px"},
                        {'value': '1', 'text': '否', width: "100px"}
                    ]
                }
            }

        }, {
            title: '厂商代码',
            field: 'supplierCode',
            width: "20%",
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '是否使用',
            field: 'usedFlag',
            width: "5%",
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    width: '100px',
                    idField: 'value',
                    valueField: 'value',
                    textField: 'text',
                    data: [
                        {'value': '0', 'text': '使用', width: "100px"},
                        {'value': '1', 'text': '停用', width: "100px"}
                    ]
                }
            }

        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });


    $('#supplierType').combogrid({

        delay: 150,
        width: '150px',
        mode: 'remote',
        method: 'GET',
        url: basePath + '/drug-supplier-catalog/list-type?orgId=' + parent.config.org_Id,
        idField: 'supplierClass',
        textField: 'supplierClass',
        columns: [[
            {field: 'supplierClass', title: '类别', width: "150px", sortable: true}
        ]]
    });

    var reset = function () {
        $('#chk1').click();
        $("#inputCode").textbox('setValue', '');
        $("#inputCode").textbox('disable');
        $("#supplierType").combogrid('enable');
    }
    reset();

    $('#chk2').click(function () {
        $("#supplierType").combogrid('setValue', '');

        $("#inputCode").textbox('enable');

        $("#supplierType").combogrid('disable');
    });
    $('#chk1').click(function () {
        $("#inputCode").textbox('setValue', '');
        $("#inputCode").textbox('disable');
        $("#supplierType").combogrid('enable');

    });


    $("#searchBtn").on("click", function () {
        var inputCode = $("#inputCode").textbox("getValue");
        var supplierType = $("#supplierType").textbox("getValue");
        if (supplierType == "") {
            $.get(basePath + "/drug-supplier-catalog/list-supplier-input-code?orgId=" + parent.config.org_Id + "&inputCode=" + inputCode, function (data) {

                $("#dg").datagrid('loadData', data);
            });
        } else {
            $.get(basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=" + supplierType, function (data) {

                $("#dg").datagrid('loadData', data);
            });
        }
    });

    $("#addBtn").on('click', function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#delBtn").on('click', function () {
        var row = $("#dg").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }
        if (!row.id) {
            //判断是否是新加项目
            var index = $("#dg").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#dg").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/drug-supplier-catalog/del", row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        loadDict();
                    })
                }
            });
        }

    });

    $("#editBtn").on('click', function () {
        var row = $("#dg").datagrid("getSelected");
        var index = $("#dg").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#dg").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#dg").datagrid("endEdit", editIndex);
            $("#dg").datagrid("beginEdit", index);
            editIndex = index;
        }
    });

    var loadDict = function () {

        $.get(basePath + "/drug-supplier-catalog/list?orgId=" + parent.config.org_Id, function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i].foreignx == '0') {
                    data[i].foreignx = '是'
                } else {
                    data[i].foreignx = '否'
                }
            }
            for (var j = 0; j < data.length; j++) {
                if (data[j].usedFlag == '0') {
                    data[j].usedFlag = '使用'
                } else {
                    data[j].usedFlag = '停用'
                }
            }
            $("#dg").datagrid('loadData', data);

        });
    };

    loadDict();

    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateData = $("#dg").datagrid("getChanges", "updated");

        var examRptPatternVo = {};
        examRptPatternVo.inserted = insertData;
        examRptPatternVo.updated = updateData;
        examRptPatternVo.orgId = parent.config.org_Id;
        for (var i = 0; i < examRptPatternVo.updated.length; i++) {
            if (examRptPatternVo.updated[i].usedFlag == '使用') {
                examRptPatternVo.updated[i].usedFlag = '0';
            }
            if (examRptPatternVo.updated[i].usedFlag == '停用') {
                examRptPatternVo.updated[i].usedFlag = '1';
            }
            if (examRptPatternVo.updated[i].foreignx == '是') {
                examRptPatternVo.updated[i].foreignx = '0';
            }
            if (examRptPatternVo.updated[i].foreignx == '否') {
                examRptPatternVo.updated[i].foreignx = '1';
            }

        }
        $.postJSON(basePath + "/drug-supplier-catalog/merge", JSON.stringify(examRptPatternVo), function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
            loadDict();
        })


    });
});