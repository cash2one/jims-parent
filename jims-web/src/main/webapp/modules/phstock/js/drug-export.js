/**
 * 出库处理
 * @author luohk
 * @version 2016-05-14
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
        fit: true,
        fitColumns: true,
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
            hidden: true,
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "代码",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }

        }, {
            title: "厂家",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "药名",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "规格",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "单位",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "数量",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "单价",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "批发价",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "当前结存",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "零售价",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "出库金额",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "批号",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "有效期",
            field: "administrationCode",
            width: '8%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });
    $('#statisticClass').combobox({
        url: parent.basePath + '/drug-export/findAll',
        valueField: 'statisticClass',
        textField: 'statisticClass',
        method: 'GET'
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
            parent.$.postJSON("", beanChangeVo, function (data, status) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#dg').datagrid('load');
                $('#dg').datagrid('clearChecked');
            }, function (data) {
                $.messager.alert('提示', data.responseJSON.errorMessage, "error");
            })
        }
    });

});