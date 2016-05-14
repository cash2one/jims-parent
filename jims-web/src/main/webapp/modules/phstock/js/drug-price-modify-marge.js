$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#dg").datagrid({
        fit: true,
        fitColumns: true,
        title: '调价记录维护',
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }

        }, {
            title: "规格",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "包装规格",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "单位",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "原批发价",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "新批发价",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "原零售价",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "新零售价",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "通知生效日期",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "调价依据",
            field: "administrationCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    $("#modify").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '9%',
            align: 'center'

        }, {
            title: "药品规格",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "批发价",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "最高限价",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "包装量",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "最小规格",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "最小单位",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "起用日期",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "停止日期",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "备注",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
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
        if (row) {
            var rowIndex = $("#dg").datagrid('getRowIndex', row);
            $("#dg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
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

    /**
     * 保存修改的内容
     * 基础字典的改变，势必会影响其他的统计查询
     * 基础字典的维护只能在基础数据维护的时候使用。
     */
    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateDate = $("#dg").datagrid("getChanges", "updated");
        var deleteDate = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteDate;
        beanChangeVo.updated = updateDate;


        if (beanChangeVo) {
            $.postJSON("/api/exp-coding-rule/merge", beanChangeVo, function (data, status) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#dg').datagrid('load');
                $('#dg').datagrid('clearChecked');
            }, function (data) {
                $.messager.alert('提示', data.responseJSON.errorMessage, "error");
            })
        }
    });

});