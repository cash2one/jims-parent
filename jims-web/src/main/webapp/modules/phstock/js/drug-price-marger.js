/**
 * Created by luohk on 2016/5/13.
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
        title: '药品价格维护',
        fit: true,//让#dg数据创铺满父类容器
        //  url: basePath + "/AdministrationDict/listAll",
        idField: 'id',
        toolbar: '#tb',
        singleSelect: true,
        columns: [[{
            title: '药品',
            field: 'b',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '包装数量',
            field: 'c',
            width: '6%',
            editor: {
                type: 'text',options: {
                    required: true
                }
            }
        }, {
            title: '规格',
            field: 'a',
            width: '6%',
            type: 'checkbox'
        }, {
            title: '单位',
            field: 'supplierClass',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '厂家',
            field: 'inputCode',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '停价',
            field: 'foreignx',
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
            }
        }, {
            title: '批发价',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '零售价格',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '最高限价',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '价格类别',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '批发文号',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: 'GMP标志',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'checkbox', options: {on: '1', off: '0'}
            }
        }, {
            title: '最小规格',
            width: '6%',
            field: 'd',
            type: 'checkbox'
        }, {
            title: '最小单位',
            field: 'e',
            width: '6%',
            type: 'checkbox'
        }, {
            title: '住院收据分类',
            field: 'supplierCode',
            width: '6%',
            color:'bule',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '门诊收据分类',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '核算分类',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '会计科目',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '病案首页分类',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'combobox', options: {
                    idField: '',
                    textField: '',
                    method: 'GET',
                    url: "",
                    columns: [[
                        {field: '', width: "160px"}
                    ]]
                }
            }
        }, {
            title: '备注',
            field: 'supplierCode',
            width: '6%',
            editor: {
                type: 'text'
            }
        },

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
})