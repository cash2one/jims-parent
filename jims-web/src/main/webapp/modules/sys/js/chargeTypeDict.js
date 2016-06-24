/**
 * Created by fyg on 2016/6/24.
 */
$(function () {
    var orgId = config.org_Id;  //组织机构ID
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            $("#dg").datagrid('unselectAll');
            editIndex = undefined;
        }
    }

    $("#dg").datagrid({
        fit: true,//让#dg数据创铺满父类容器
        toolbar: '#tb',
        width: 'auto',
        height: 'auto',
        nowrap: true,   //设置为true，当数据长度超出列宽时将会自动截取
        striped: true,  //设置为true将交替显示行背景
        collapsible: true,//显示可折叠按钮
        fitColumns: true,//允许表格自动缩放，以适应父容器
        singleSelect: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        url: basePath + '/ChargeTypeDict/list-by-orgId?orgId=' + orgId,
        idField: 'fldId',
        remoteSort: false,
        pagination: true,//分页控件
        //pageSize: 15,
        //pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        rownumbers: true,   //行数
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
            title: "费别代码",
            field: "chargeTypeCode",
            align: 'center',
            width: "12%",
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "费别名称",
            field: "chargeTypeName",
            align: 'center',
            width: '12%',
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "享受优惠价格",                  //1—享受 0—不享受
            field: "chargePriceIndicator",
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
                        text: '享受',
                        selected: true
                    }, {
                        value: '0',
                        text: '不享受'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "享受";
                }
                if (value == "0") {
                    return "不享受";
                }
            }
        }, {
            title: "五笔码",
            field: "inputCodeWb",
            align: 'center',
            width: '12%',
            editor: {
                type: 'textbox'
            }
        }, {
            title: "医保类别",              //0－非医保 1－医保
            field: "isInsur",
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
                        text: '医保',
                        selected: true
                    }, {
                        value: '0',
                        text: '非医保'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "医保";
                }
                if (value == "0") {
                    return "非医保";
                }
            }
        }, {
            title: "费别分组号",             //01自费  02医保 03合作医疗
            field: "groupNo",
            align: 'center',
            width: '12%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '01',
                        text: '自费'
                    }, {
                        value: '02',
                        text: '医保',
                        selected: true
                    }, {
                        value: '03',
                        text: '合作医疗'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "01") {
                    return "自费";
                }
                if (value == "02") {
                    return "医保";
                }
                if (value == "03") {
                    return "合作医疗";
                }
            }
        }, {
            title: "费别分组名称",
            field: "groupName",
            align: 'center',
            width: "12%",
            editor: {
                type: 'textbox'
            }
        }, {
            title: "院长查询用的医保类别",                //0:自费 1:医保  2:合作医疗
            field: "insuranceTypeInq",
            align: 'center',
            width: "14%",
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '0',
                        text: '自费'
                    }, {
                        value: '1',
                        text: '医保',
                        selected: true
                    }, {
                        value: '2',
                        text: '合作医疗'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "0") {
                    return "自费";
                }
                if (value == "1") {
                    return "医保";
                }
                if (value == "2") {
                    return "合作医疗";
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
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5, 10, 15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

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
            $.postJSON(basePath + '/ChargeTypeDict/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
                stopEdit();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
                stopEdit();
            })
        }
    });

    $("#searchBtn").on("click", function () {
        var name = $("#name").textbox("getValue");
        $.get(basePath + '/ChargeTypeDict/search?chargeTypeName=' + name + '&orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    });

    var loadDict = function () {
        $.get(basePath + '/ChargeTypeDict/list-by-orgId?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();
});


/*
function onloadMethod() {
    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/ChargeTypeDict/list',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        rownumbers:true,//行号
        columns: [[      //每个列具体内容
            {field: 'orgId', title: '医院', width: '28%', align: 'center'},
            {field: 'chargeTypeCode', title: '费别代码', width: '10%', align: 'center'},
            {field: 'chargeTypeName', title: '费别姓名', width: '10%', align: 'center'},
            {field: 'chargePriceIndicator', title: '享受优惠价格标志', width: '18%', align: 'center'},
            {field: 'inputCodeWb', title: '五笔码', width: '18%', align: 'center'},
            {field: 'isInsur', title: '是否是医保级别', width: '18%', align: 'center'},
            {field: 'groupNo', title: '费别分组号', width: '18%', align: 'center'},
            {field: 'groupName', title: '费别分组名称', width: '18%', align: 'center'},
            {field: 'insuranceTypeInq', title: '院长查询用的医保类别', width: '18%', align: 'center'},
            {field: 'createBy', title: '创建人', width: '18%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '18%', align: 'center'},
            {field: 'updateBy', title: '更新人', width: '18%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width: '18%', align: 'center'},
            {field: 'remarks', title: '备注信息', width: '18%', align: 'center'},
            {
                field: 'id', title: '操作', width: '30%', align: 'center', formatter: function (value, row, index) {
                var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>' +
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\'' + value + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>' +
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                return html;
            }
            }
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $("#chargeTypeDictForm").form('clear');
                $("#dlg").dialog({title: '添加'}).dialog("open")
            }
        },'-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("提示消息", "请选中要删的数据!");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }]
    });
//设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

///批量删除
    function doDelete() {
        //把你选中的 数据查询出来。
        var selectRows = $('#list_data').datagrid("getSelections");
        if (selectRows.length < 1) {
            $.messager.alert("提示消息", "请选中要删的数据!");
            return;
        }

        //真删除数据
        //提醒用户是否是真的删除数据
        $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
            if (r) {
                //真删除了  1,3,4
                var strIds = "";
                for (var i = 0; i < selectRows.length; i++) {
                    strIds += selectRows[i].id + ",";
                }
                strIds = strIds.substr(0, strIds.length - 1);
                del(strIds);
            }
        })
    }


}

//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}

*/
/**
 * 删除方法
 * @param id
 *//*

function del(id) {
    $.ajax({
        'type': 'POST',
        'url': basePath + '/ChargeTypeDict/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "保存失败", "error");
        }
    });
}

*/
/**
 * 保存方法
 *//*

function saveDict() {
    $.postForm(basePath + '/ChargeTypeDict/save', 'chargeTypeDictForm', function (data) {
        if (data.data == 'success') {
            $.messager.alert("提示消息", data.code + "条记录，保存成功");
            $("#dlg").dialog('close');
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })
}

*/
/**
 * 修改字典
 * @param id
 *//*

function get(id) {
    $("#saveBut").show();
    $("#dlg").dialog({title: '修改字典信息'}).dialog("open");
    $.ajax({
        'type': 'post',
        'url': basePath + '/ChargeTypeDict/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#chargeTypeDictForm').form('load', data);
        }
    });
}

*/
/**
 * 查看字典
 * @param id
 *//*

function look(id) {
    $("#dlg").dialog({title: '查看字典信息'}).dialog("open");
    $("#saveBut").hide();
    $.ajax({
        'type': 'post',
        'url': basePath + '/ChargeTypeDict/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#chargeTypeDictForm').form('load', data);
        }
    });
}*/
