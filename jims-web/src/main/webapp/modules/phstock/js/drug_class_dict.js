/**
 * Created by fyg on 2016/6/27.
 */
$(function(){
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drugdict").datagrid('endEdit', editIndex);
            //$("#drugdict").datagrid('unselectAll');
            editIndex = undefined;
        }
    }

    var storage = [{storageType: '全部'}, {storageType: '药库'}, {storageType: '药局'}];

    $("#importDict").on("click", function () {
        $("#drugdict").datagrid({
            title: "药品入库类型字典",
            url: basePath + '/drug-import/findAll',
            method: 'get',
            fit: true,
            rownumbers: true,
            singleSelect: true,//是否单选
            fitColumns: true, //列自适应宽度
            columns: [[{
                title: 'id',
                field: 'id',
                hidden: true
            },{
                title: '入库分类',
                field: 'importClass',
                align: 'center',
                width: '20%',
                editor: 'textbox'
            }, {
                title: '所属类别',
                field: 'statisticClass',
                align: 'center',
                width: '20%',
                editor: 'textbox'
            }, {
                title: '库存类型',
                field: 'storageType',
                align: 'center',
                width: '20%',
                editor: {
                    type: 'combobox',
                    options: {
                        editable: false,
                        align: 'center',
                        valueField: 'storageType',
                        textField: 'storageType',
                        data: storage
                    }
                }
            },{
                title: '是否记账',
                field: 'accountFlag',
                align: 'center',
                width: '20%',
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
            }]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    add();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    del();
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    saveDrugImportClass();
                }
            }],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
    });

    $("#exportDict").on("click", function () {
        $("#drugdict").datagrid({
            title: "药品出库类型字典",
            url: basePath + '/drug-export/findAll',
            method: 'get',
            fit: true,
            rownumbers: true,
            singleSelect: true,//是否单选
            fitColumns: true, //列自适应宽度
            sortName: 'exportClass',
            sortOrder: 'asc',
            columns: [[{
                title: 'id',
                field: 'id',
                hidden: true
            }, {
                title: '出库分类',
                field: 'exportClass',
                align: 'center',
                width: '20%',
                editor: 'textbox'
            }, {
                title: '所属类别',
                field: 'statisticClass',
                align: 'center',
                width: '20%',
                editor: 'textbox'
            }, {
                title: '库存类型',
                field: 'storageType',
                align: 'center',
                width: '20%',
                editor: {
                    type: 'combobox',
                    options: {
                        editable: false,
                        align: 'center',
                        valueField: 'storageType',
                        textField: 'storageType',
                        data: storage
                    }
                }
            }, {
                title: '是否记账',
                field: 'accountFlag',
                align: 'center',
                width: '20%',
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
            }]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    add();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    del();
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    saveDrugExportClass();
                }
            }],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
    });

    $("#codingrule").on("click", function () {
        $("#drugdict").datagrid({
            title: "药品编码描述字典",
            url: basePath + '/drug-code/findAll',
            method: 'get',
            idField: 'id',
            fit: true,
            rownumbers: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            fitColumns: true, //列自适应宽度
            sortName: 'codeLevel',
            sortOrder: 'asc',
            columns: [[//显示的列
                {
                    field: 'codeLevel', title: '编码级别',align: 'center', width: '20%', editor: {
                    type: 'text', options: {required: true}
                }
                },
                {
                    field: 'levelWidth', title: '编码级长度',align: 'center', width: '20%', editor: {
                    type: 'text', options: {required: true}
                }
                },
                {
                    field: 'className', title: '编码级名称',align: 'center', width: '20%', editor: {
                    type: 'text', options: {required: true}
                }
                }]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    add();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    del();
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    saveDrugCodingRule();
                }
            }],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
    });
    $("#dispproperty").on("click", function () {
        $("#drugdict").datagrid({
            title: "摆药类别字典",
            url: basePath + '/drug-disp/findAll',
            method: 'get',
            idField: 'id',
            fit: true,
            rownumbers: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            fitColumns: true, //列自适应宽度
            sortName: 'dispensingProperty',
            sortOrder: 'asc',
            columns: [[//显示的列
                {
                    field: 'dispensingProperty', title: '摆药类别',align: 'center', width: '20%', editor: {
                    type: 'text', options: {required: true}
                }
                },
                {
                    field: 'drugAdministrations', title: '服药途径',align: 'center', width: '30%', editor: {
                    type: 'text', options: {required: true}
                }
                }]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    add();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    del();
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    saveDrugDispPropertyDict();
                }
            }],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
    });

    function saveDrugImportClass() {     //保存入库类型字典增删改
        if (editIndex || editIndex == 0) {
            $("#drugdict").datagrid("endEdit", editIndex);
        }

        var insertData = $("#drugdict").datagrid("getChanges", "inserted");
        var updateData = $("#drugdict").datagrid("getChanges", "updated");
        var deleteData = $("#drugdict").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;

        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-import/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDrugImportClass();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    }
    function saveDrugExportClass() {     //保存出库类型字典增删改
        if (editIndex || editIndex == 0) {
            $("#drugdict").datagrid("endEdit", editIndex);
        }

        var insertData = $("#drugdict").datagrid("getChanges", "inserted");
        var updateData = $("#drugdict").datagrid("getChanges", "updated");
        var deleteData = $("#drugdict").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;

        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-export/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDrugExportClass();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    }
    function saveDrugCodingRule(){      //保存编码描述字典增删改
        if (editIndex || editIndex == 0) {
            $("#drugdict").datagrid("endEdit", editIndex);
        }

        var insertData = $("#drugdict").datagrid("getChanges", "inserted");
        var updateData = $("#drugdict").datagrid("getChanges", "updated");
        var deleteData = $("#drugdict").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;

        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-code/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDrugCodingRule();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    }
    function saveDrugDispPropertyDict(){        //保存摆药类别字典增删改
        if (editIndex || editIndex == 0) {
            $("#drugdict").datagrid("endEdit", editIndex);
        }

        var insertData = $("#drugdict").datagrid("getChanges", "inserted");
        var updateData = $("#drugdict").datagrid("getChanges", "updated");
        var deleteData = $("#drugdict").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;

        if (beanChangeVo) {
            $.postJSON(basePath + '/drug-disp/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDrugDispPropertyDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    }

    function add(){      //增加
        stopEdit();
        $("#drugdict").datagrid('appendRow', {});
        var rows = $("#drugdict").datagrid('getRows');
        var addRowIndex = $("#drugdict").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#drugdict").datagrid('selectRow', editIndex);
        $("#drugdict").datagrid('beginEdit', editIndex);
    }
    function del(){      //删除
        var row = $("#drugdict").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#drugdict").datagrid('getRowIndex', row);
            $("#drugdict").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    }

    var loadDrugImportClass = function () {     //刷新入库类型字典页面
        $.get(basePath + '/drug-import/findAll', function (data) {
            $("#drugdict").datagrid('loadData', data);
        });
    }
    var loadDrugExportClass = function () {     //刷新出库类型字典页面
        $.get(basePath + '/drug-export/findAll', function (data) {
            $("#drugdict").datagrid('loadData', data);
        });
    }
    var loadDrugCodingRule = function(){        //刷新编码描述字典页面
        $.get(basePath + '/drug-code/findAll', function (data) {
            $("#drugdict").datagrid('loadData', data);
        });
    }
    var loadDrugDispPropertyDict = function(){  //刷新摆药类别字典页面
        $.get(basePath + '/drug-disp/findAll', function (data) {
            $("#drugdict").datagrid('loadData', data);
        });
    }

});
