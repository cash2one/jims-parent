/**
 * Created by fyg on 2016/6/23.
 */
$(function () {
    var orgId = config.org_Id;
    var chargeTypeDict;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }

    function listAll() {
        $.get(basePath + '/ChargeTypeDict/list-all?orgId=' + orgId, function (data) {
            chargeTypeDict = data;
        });
    }

    listAll();

    $("#dg").datagrid({
        fit: true,
        toolbar: '#tb',
        singleSelect: true,
        rownumbers: true,
        method: 'get',
        url: basePath + '/pay-way-dict/list?orgId=' + orgId,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "所属组织机构",
            field: "orgId",
            align: "center",
            hidden: true
        }, {
            title: "支付方式代码",
            field: "payWayCode",
            align: 'center',
            width: "12%",
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "支付方式名称",
            field: "payWayName",
            align: 'center',
            width: '12%',
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "记账标志",                  //0，不记账，1记账
            field: "acctingIndicator",
            align: 'center',
            width: '11%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '记账',
                        selected: true
                    }, {
                        value: '0',
                        text: '不记账'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "记账";
                }
                if (value == "0") {
                    return "不记账";
                }
            }
        }, {
            title: "门诊病人适用",          //0，否，1是
            field: "outpIndicator",
            align: 'center',
            width: '12%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '是',
                        selected: true
                    }, {
                        value: '0',
                        text: '否'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "是";
                }
                if (value == "0") {
                    return "否";
                }
            }
        }, {
            title: "住院病人适用",              //0，否，1是
            field: "inpIndicator",
            align: 'center',
            width: '12%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '是',
                        selected: true
                    }, {
                        value: '0',
                        text: '否'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "是";
                }
                if (value == "0") {
                    return "否";
                }
            }
        }, {
            title: "输入码",
            field: "inputCode",
            align: 'center',
            width: '12%'
        },{
            title: "已注册",                  //0，否，1是
            field: "registIndicator",
            align: 'center',
            width: "12%",
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '是',
                        selected: true
                    }, {
                        value: '0',
                        text: '否'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "是";
                }
                if (value == "0") {
                    return "否";
                }
            }
        },{
            title: "医保类别",
            field: "chargeTypeName",
            align: 'center',
            width: "13%",
            editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 400,
                    idField: 'chargeTypeCode',
                    textField: 'chargeTypeName',
                    url: basePath + '/ChargeTypeDict/list-by-orgId?orgId=' + orgId,
                    mode: 'remote',
                    method: 'get',
                    remoteSort: false,
                    pagination: true,//分页控件
                    pageSize: 15,
                    pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
                    columns: [[
                        {field: 'id', title: 'id', hidden: true},
                        {field: 'chargeTypeCode', title: '类别代码', width: '33%'},
                        {field: 'chargeTypeName', title: '类别名称', width: '33%'},
                        {field: 'inputCodeWb', title: '五笔码', width: '33%'}
                    ]]
                }
            },
            formatter: function (value, row, index) {
                for(var i=0; i< chargeTypeDict.length; i++){
                    if(value == chargeTypeDict[i].chargeTypeCode){
                        return chargeTypeDict[i].chargeTypeName;
                    }
                    if(value == chargeTypeDict[i].chargeTypeName){
                        return chargeTypeDict[i].chargeTypeName;
                    }
                }
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    //设置分页控件
    var p = $('#dg').datagrid('getPager');

    $("#addBtn").on("click", function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#delBtn").on("click", function () {
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

    $("#saveBtn").on("click", function () {
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

        if (beanChangeVo.inserted.length > 0) {
            for (var i = 0; i < beanChangeVo.inserted.length; i++) {
                beanChangeVo.inserted[i].orgId = orgId;
            }
        }
        if (beanChangeVo.updated.length > 0) {
            for (var i = 0; i < beanChangeVo.updated.length; i++) {
                beanChangeVo.updated[i].orgId = orgId;
            }
        }
        if (beanChangeVo) {
            $.postJSON(basePath + '/pay-way-dict/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    });

    var loadDict = function () {
        $.get(basePath + '/pay-way-dict/list?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();
});
