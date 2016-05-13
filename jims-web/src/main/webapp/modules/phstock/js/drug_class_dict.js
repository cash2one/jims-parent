/**
 * Created by Administrator on 2016/5/10.
 */
$(function (){
    var editRow = undefined;//保存行的索引
    var editIndex = undefined;
    var statistic = [{statisticClass: '生产入库'}, {statisticClass: '采购入库'}, {statisticClass: '赠送入库'},
                     {statisticClass: '盘点入库'}, {statisticClass: '部门入库'}, {statisticClass: '调整入库'},
                     {statisticClass: '发放入库'}, {statisticClass: '退药入库'}];
    var storage = [{storageType: '全部'}, {storageType: '药库'}, {storageType: '药局'}];
    $("#importDict").on("click", function () {
        $("#drugdict").datagrid({
            title: "药品入库类型字典",
            url: basePath + '/drug-import/findAll',
            method: 'get',
            idField: 'id',
            fit: true,
            rownumbers: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            fitColumns: true, //列自适应宽度
            ctrlSelect:true,
            sortName:'importClass',
            sortOrder:'asc',
            columns: [[//显示的列
                {field: 'importClass', title: '入库类别名称', width: 120, editor: {
                    type: 'text', options: {required: true}}},
                {field: 'statisticClass', title: '统计用户别名', width: 120,editor:{
                    type: 'combobox', options: {
                        valueField: 'statisticClass',
                        textField: 'statisticClass',
                        data: statistic
                    }}},
                {field: 'storageType', title: '适用单位', width: 120,editor: {
                    type: 'combobox', options: {
                        valueField: 'storageType',
                        textField: 'storageType',
                        data: storage
                    }
                }}
            ]],
            toolbar: [{
                 text: '新增',
                 iconCls: 'icon-add',
                 handler: function () {
                   doAdd();
              }
             },'-',{
                 text: '删除',
                 iconCls: 'icon-remove',
                 handler: function () {
                   doDelete('/drug-import/delete');
                 }
             },{
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                  doSave('/drug-import/save');
                }
            }],
            onClickCell: onClickCell
    });
    });

    $("#exportDict").on("click", function () {
        $("#drugdict").datagrid({
            title: "药品出库类型字典",
            url: basePath + '/drug-export/findAll',
            method: 'get',
            idField: 'id',
            fit: true,
            rownumbers: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            fitColumns: true, //列自适应宽度
            sortName: 'exportClass',
            sortOrder: 'asc',
            columns: [[//显示的列
                {field: 'exportClass', title: '出库类别名称', width: 120, editor: {
                    type: 'text', options: {required: true}
                }},
                {field: 'statisticClass', title: '统计用户别名', width: 120, editor: {
                    type: 'combobox', options: {
                        valueField: 'statisticClass',
                        textField: 'statisticClass',
                        data: statistic
                    }
                }},
                {field: 'storageType', title: '适用单位', width: 120, editor: {
                    type: 'combobox', options: {
                        valueField: 'storageType',
                        textField: 'storageType',
                        data: storage
                    }
                }}
            ]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    doAdd();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete('/drug-export/delete');
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    doSave('/drug-export/save');
                }
            }],
            onClickCell: onClickCell
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
                {field: 'codeLevel', title: '编码级别', width: 120, editor: {
                    type: 'text', options: {required: true}
                }},
                {field: 'levelWidth', title: '编码长度', width: 120, editor: {
                    type: 'text', options: {required: true}
                }},
                {field: 'className', title: '编码级名称', width: 120, editor: {
                    type: 'text', options: {required: true}
                }}]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    doAdd();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete('/drug-code/delete');
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    doSave('/drug-code/save');
                }
            }],
            onClickCell: onClickCell
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
                {field: 'dispensingProperty', title: '摆药类别', width: 120, editor: {
                    type: 'text', options: {required: true}
                }},
                {field: 'drugAdministrations', title: '服药途径', width: 240, editor: {
                    type: 'text', options: {required: true}
                }}]],
            toolbar: [{
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
                    doAdd();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete('/drug-disp/delete');
                }
            }, {
                text: '保存',
                iconCls: 'icon-save',
                handler: function () {
                    doSave('/drug-disp/save');
                }
            }],
            onClickCell: onClickCell
        });
    });

function endEditing() {
    if (editIndex == undefined) {
        return true
    }
    if ($('#drugdict').datagrid('validateRow', editIndex)) {
        $('#drugdict').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field) {
    if (endEditing()) {
        $('#drugdict').datagrid('selectRow', index)
            .datagrid('editCell', {index: index, field: field});
        editIndex = index;
    }
}
function doAdd(){
    if (editRow != undefined) {
        $("#drugdict").datagrid('endEdit', editRow);
    }
    if (editRow == undefined) {
        $("#drugdict").datagrid('appendRow', {});
        $("#drugdict").datagrid('beginEdit', 0);
        editRow = undefined;
    }
}
function doSave(path){
    var _index = $('#drugdict').datagrid('getRowIndex',$('#drugdict').datagrid('getSelected'))
    $('#drugdict').datagrid('endEdit',_index)
    var param =[]
    param = param.concat($("#drugdict").datagrid('getChanges', 'inserted'))
    param = param.concat($("#drugdict").datagrid('getChanges', 'updated'))
    $.postJSON(basePath + path, JSON.stringify(param), function (data) {
          if (data.data == 'success') {
            $.messager.alert("提示消息", "保存成功"+ data.code+"条数据", "success");
            $('#drugdict').datagrid('load');
            $('#drugdict').datagrid('clearChecked');
          } else {
            $.messager.alert('提示消息', data.code, "error");
          }
        }, function (data) {
            $.messager.alert('提示', "保存失败", "error");
        }
    )
}

//批量删除
function doDelete(path) {
    //把你选中的 数据查询出来。
    var selectRows = $('#drugdict').datagrid("getSelections");
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
            del(strIds,path);
        }
    })
}
/**
 * 删除方法
 * @param id
 */
function del(id,path) {
    $.ajax({
        'type': 'POST',
        'url': basePath + path,
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#drugdict').datagrid('load');
                $('#drugdict').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "删除失败", "error");
        }
    });
}
$.extend($.fn.datagrid.methods, {
    editCell: function (jq, param) {
        return jq.each(function () {
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
            for (var i = 0; i < fields.length; i++) {
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] != param.field) {
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            for (var i = 0; i < fields.length; i++) {
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    }
});
})