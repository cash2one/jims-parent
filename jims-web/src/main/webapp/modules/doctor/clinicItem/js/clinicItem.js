var rowNum = -1;
var itemCode = "";
var clinicId = parent.clinicMaster.id;
$(function () {
    //$('#clinicItem').datagrid('getEditor', { index: index, field: 'amount' }).target.val('1'); 
    $('#clinicItem').datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath + '/treatment/findList',
        queryParams: {"clinicId": clinicId},
        idField: 'id',
        columns: [[      //每个列具体内容
            {
                field: 'itemName', title: '项目名称', width: '25%', align: 'center', editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 500,
                    data: clinicDictName,
                    idField: 'item_code',
                    textField: 'item_name',
                    required: true,
                    columns: [[
                        {field: 'item_name', title: '项目名称', width: '20%', align: 'center'},
                        {field: 'item_code', title: '项目代码', width: '20%', align: 'center'},
                        {field: 'item_class', title: '项目类型', width: '10%', align: 'center', editor: 'text'},
                        {field: 'input_code', title: '拼音输入码', width: '10%', align: 'center', editor: 'text'},
                        {field: 'org_id', title: '部门', width: '10%', align: 'center', editor: 'text'},
                        {field: 'guige', title: '规格', width: '10%', align: 'center', editor: 'text'},
                        {field: 'company', title: '单位', width: '10%', align: 'center', editor: 'text'}
                    ]],
                    keyHandler: {
                        up: function () {
                        },
                        down: function () {
                        },
                        enter: function () {
                        },
                        query: function (q) {
                            dataGridCompleting(q, 'clinicItem', 'itemName');
                        }
                    },
                    onClickRow: function (index, row) {
                        var rows = $('#clinicItem').datagrid("getRows"); // 这段代码是// 对某个单元格赋值
                        var columns = $('#clinicItem').datagrid("options").columns;
                        rows[rowNum][columns[0][1].field] = row.item_code;
                        //rows[rowNum][columns[0][2].field]=clinicId;
                        itemCode = row.item_code;
                        //显示所有处置计价显示
                        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '处置计价');
                        $("#clinic").datagrid({
                            url: basePath + '/price-list/list-by-clinic-code',
                            queryParams: {"orgId": 1, "clinicItemCode": itemCode},
                            method: "get"
                        })

                        $('#clinicItem').datagrid('endEdit', rowNum);
                        $('#clinicItem').datagrid('beginEdit', rowNum);
                        //var units = $("#clinicItem").datagrid('getEditor',{index:editRow,field:'units'});
                        //$(units.target).textbox('setValue',row.company);
                    },
                    fitColumns: true
                }
            }
            },
            {field: 'itemCode', title: '项目代码', width: '15%', align: 'center'},
            {field: 'clinicId', title: '就诊id', width: '20%', align: 'center', hidden: 'true'},
            {field: 'amount', title: '数量', width: '6%', align: 'center', editor: 'text'},
            //{ field: 'units', title: '单位', width: '6%', align: 'center', editor: {
            //    type: 'textbox', options: {editable: false, disable: false}}
            //},
            //{field: 'frequency', title: '频次', width: '10%', align: 'center', editor: 'text'},
            {
                field: 'performedBy',
                title: '执行科室',
                width: '10%',
                align: 'center',
                formatter: clinicDeptCodeFormatter,
                editor: {
                    type: "combobox",
                    options: {
                        data: clinicDeptCode,
                        valueField: 'id',
                        textField: 'dept_name',
                        required: true
                    }
                }
            },
            {
                field: 'wardCode',
                title: '护理单元',
                width: '10%',
                align: 'center',
                formatter: clinicDeptCodeFormatter,
                editor: {
                    type: "combobox",
                    options: {
                        data: clinicDeptCode,
                        valueField: 'id',
                        textField: 'dept_name',
                        required: true
                    }
                }
            },
            {
                field: 'charges', title: '实收', width: '10%', align: 'center', editor: {
                options: {
                    onLoadSuccess: function (index, row) {
                        $('#itemsTables').datagrid('appendRow', {
                            subjcode: '合计',
                            costs: compute("clinicItem", "costs"),
                            charges: compute("clinicItem", "charges")
                        });
                    }
                }
            }
            },
            {
                field: 'chargeIndicator', title: '收费标识', width: '10%', align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    if (value == '0') {
                        return '未收费';
                    }
                    if (value == '1') {
                        return '已收费';
                    }
                }
            }
        ]],


        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                if (rowNum >= 0) {
                    rowNum++;
                }
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                $("#clinicItem").datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {"clinicId": clinicId}
                });
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }, {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                $("#clinicItem").datagrid('endEdit', rowNum);
                if (rowNum != undefined) {
                    $("#clinicItem").datagrid("endEdit", rowNum);
                }
                save();
            }
        }
        ],
        onClickRow: function (rowIndex, rowData) {//单击行事件
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '处置计价');
            $("#clinic").datagrid({
                url: basePath + '/price-list/list-by-clinic-code',
                queryParams: {"orgId": 1, "clinicItemCode": rowData.itemCode},
                method: "get"
            })
            if (rowData.id != null) {
                $('#clinicItem').datagrid("disableEditing")
            }
            var dataGrid = $('#clinicItem');
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


    //求和
    function compute(tableName, colName) {
        var rows = $('#' + tableName).datagrid('getRows');
        var total = 0;
        for (var i = 0; i < rows.length; i++) {

            if (isNaN(parseFloat(rows[i][colName]))) {
                total += 0;
            } else {
                total += parseFloat(rows[i][colName]);
            }
        }
        return total;
    }

//处置计价
    $('#clinic').datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath + '/price-list/list-by-clinic-code',
        queryParams: {"orgId": 1, "clinicItemCode": itemCode},
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'itemClass', title: '类别', width: '15%', align: 'center', editor: 'text'},
            {field: 'itemName', title: '计价项目', width: '40%', align: 'center'},
            {field: 'itemSpec', title: '规格', width: '15%', align: 'center', editor: 'text'},
            //{field:'number',title:'数量',width:'10%',align:'center',editor:'text'},
            {field: 'units', title: '单位', width: '15%', align: 'center', editor: 'text'},
            {field: 'price', title: '金额', width: '19%', align: 'center', editor: 'text'}
        ]]

    });

    //诊疗项目
    $('#class').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: '/modules/clinic/clinicItem/js/clinic_data.json',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'itemName', title: '项目名称', width: '20%', align: 'center', editor: 'text'},
            {field: 'itemCode', title: '项目代码', width: '20%', align: 'center'},
            {field: 'itemClass', title: '项目类型', width: '10%', align: 'center', editor: 'text'},
            {field: 'inputCode', title: '拼音输入码', width: '10%', align: 'center', editor: 'text'},
            {field: 'inputCodeWb', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'},
            {field: 'orgId', title: '部门', width: '10%', align: 'center', editor: 'text'},
            {field: 'guige', title: '规格', width: '10%', align: 'center', editor: 'text'},
            {field: 'company', title: '单位', width: '10%', align: 'center', editor: 'text'}
        ]]


    });


});


//删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#clinicItem').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            if (strIds == 'undefined' || strIds == '') {
                var index1 = $('#clinicItem').datagrid('getRowIndex', $("#clinicItem").datagrid('getSelected'))
                $('#clinicItem').datagrid('deleteRow', index1);
            } else {
                //真删除数据
                $.ajax({
                    'type': 'POST',
                    'url': basePath + '/treatment/delete',
                    'contentType': 'application/json',
                    'data': id = strIds,
                    'dataType': 'json',
                    'success': function (data) {
                        if (data == '1') {
                            $.messager.alert("提示消息", data + "条记录删除成功！");
                            $("#clinicItem").datagrid("reload");
                            //$('#zhenduan').datagrid('load');
                            //$('#zhenduan').datagrid('clearChecked');
                        } else {
                            $.messager.alert('提示', "删除失败", "error");
                        }
                    },
                    'error': function (data) {
                        $.messager.alert('提示', "删除失败", "error");
                    }
                });
            }
        }
    })
}


function save() {
    $("#clinicItem").datagrid("endEdit", rowNum);
    var rows = $('#clinicItem').datagrid('getRows');
    if (rows[0].itemCode == undefined) {
        $.messager.alert('提示', "请选择项目，再添加", "error");
    } else {
        var tableJson = JSON.stringify(rows);
        $.postJSON(basePath + '/treatment/save', tableJson, function (data) {
            if (data.code == 'success') {
                $.messager.alert('提示消息', '保存成功', 'success');
                $("#clinicItem").datagrid("reload");
            } else {
                $.messager.alert('提示', "保存失败", "error");
            }
        }, function (data) {
            $.messager.alert('提示', "保存失败", "error");
        })
    }
}