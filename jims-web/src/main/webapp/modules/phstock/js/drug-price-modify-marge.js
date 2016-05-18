$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#modifyList").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#modifyList").datagrid({
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
            field: "drugCode",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }

        }, {
            title: "规格",
            field: "minSpec",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "包装规格",
            field: "drugSpec",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "单位",
            field: "units",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "厂家",
            field: "firmId",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "原批发价",
            field: "originalTradePrice",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "新批发价",
            field: "currentTradePrice",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "原零售价",
            field: "originalRetailPrice",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "新零售价",
            field: "currentRetailPrice",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "通知生效日期",
            field: "noticeEfficientDate",
            width: '9%',
            align: 'center',
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: "调价依据",
            field: "modifyCredential",
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

    $("#priceList").datagrid({
        fit: true,
        striped: true,
        title: '价格记录',
        toolbar: '#modifyListTb',
        singleSelect: true,
        method: 'GET',
        //  url: basePath + "/AdministrationDict/listAll",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "drugCode",
            width: '9%',
            align: 'center'

        }, {
            title: "药品规格",
            field: "drugSpec",
            width: '9%',
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: '6%',
            align: 'center'
        }, {
            title: "厂家",
            field: "firmId",
            width: '9%',
            align: 'center'
        }, {
            title: "批发价",
            field: "tradePrice",
            width: '9%',
            align: 'center'
        }, {
            title: "最高限价",
            field: "hlimitPrice",
            width: '9%',
            align: 'center'
        }, {
            title: "包装量",
            field: "amountPerPackage",
            width: '9%',
            align: 'center'
        }, {
            title: "最小规格",
            field: "minSpec",
            width: '6%',
            align: 'center'
        }, {
            title: "最小单位",
            field: "minUnits",
            width: '6%',
            align: 'center'
        }, {
            title: "起用日期",
            field: "startDate",
            width: '9%',
            align: 'center'
        }, {
            title: "停止日期",
            field: "stopDate",
            width: '9%',
            align: 'center'
        }, {
            title: "备注",
            field: "memos",
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
    //定义药品名称
    $('#drugName').combogrid({
        panelWidth: 500,
        idField: 'drugCode',
        textField: 'drugName',
        loadMsg: '数据正在加载',
        url: basePath + '/drug-catalog/drugNameDictList',
        mode: 'remote',
        method: 'GET',
        fitColumns:true,
        columns: [[
            {field: 'drugCode', title: '编码', width: 150, align: 'center'},
            {field: 'drugName', title: '名称', width: 200, align: 'center'},
            {field: 'inputCode', title: '拼音', width: 50, align: 'center'}
        ]],
        onSelect: function(rowIndex,rowData){
            var url = basePath + "/drug-catalog/listDrugNameDictByDrugCode?drugCode=" +  rowData.drugCode;
            $('#drugNameDict').datagrid('reload', url);
            var url = basePath + "/drug-catalog/listDrugDictByDrugCode?drugCode=" +  rowData.drugCode;
            $('#drugDict').datagrid('reload', url);

        }
    });
    //生成零售价
    $("#generateNewRetailBtn").on("click", function () {

    });
    //提取
    $("#extractBtn").on("click", function () {

    });
    //新零售价
    $("#newRetailBtn").on("click", function () {

    });
    //新增
    $("#addBtn").on('click', function () {
        stopEdit();
        $("#modifyList").datagrid('appendRow', {});
        var rows = $("#modifyList").datagrid('getRows');
        var addRowIndex = $("#modifyList").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#modifyList").datagrid('selectRow', editIndex);
        $("#modifyList").datagrid('beginEdit', editIndex);
    });
    //删除
    $("#delBtn").on('click', function () {
        var row = $("#modifyList").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#modifyList").datagrid('getRowIndex', row);
            $("#modifyList").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });
    //保存
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