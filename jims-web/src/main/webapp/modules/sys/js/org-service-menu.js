/**
 * Created by Administrator on 2016/6/6.
 */
$(function () {
    var storage = [{label: '可预览', value: '0'}, {label: '可编辑', value: '1'}, {label: '不可预览', value: '2'}];
    var editIndex = undefined;
    var editRow = undefined;//保存行的索引
    var med = undefined;
    var serviceId = [];

    $("#roleId").datagrid({
        url: basePath + '/org-role/findAllListByOrgId?orgId=1',
        method: 'get',
        idField: 'id',
        fit: true,
        singleSelect: true,//是否单选
        columns: [[//显示的列
            {
                field: 'roleName', title: '角色名称', width: 220
            }
        ]],
        onClickRow: function (index, data) {
            $("#tt").treegrid("loadData", []);
            $("#serviceId").datagrid({
                url: basePath + '/roleVs/findrole?roleId=' + data.id,
                method: 'get',
                idField: 'id',
                textField:'serviceName',
                fit: true,
                rownumbers: true,
                fitColumns: true, //列自适应宽度
                columns: [[//显示的列
                   /* {
                        field: 'serviceId', title: '服务ID', width: 120, hidden: true
                    },*/ {
                        field: 'serviceName', title: '服务名称', width: 120, editor: {
                            type: 'combobox',
                            options: {
                                panelHeight: '150',
                                valueField: 'id',
                                textField: 'serviceName',
                                method: 'get',
                                url: basePath + "/org-service/find-self-service?orgId=1"
                            }
                        }
                    }
                ]],
                singleSelect: true,
                toolbar: [{
                    text: '新增',
                    iconCls: 'icon-add',
                    handler: function () {
                        doAdd();
                    }
                }, {
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: function () {
                        doSave('/roleVs/save');
                    }
                }],
                onSelect: function (index, data) {
                    menuDict()
                }
            })
        }
    });
    $("#tt").treegrid({
        idField: 'menuId',
        treeField: 'menuName',
        fit: true,
        toolbar: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                saveMenu();
            }
        }],
        singleSelect: false,
        columns: [[
            {
                title: '菜单名称',
                field: 'menuName',
                width: "50%"
            }, {
                title: '权限功能',
                field: 'menuOperate',
                width: "50%",
                editor: {
                    type: 'combobox', options: {
                        valueField: 'value',
                        textField: 'label',
                        data: storage
                    }
                },
                formatter: function (value, row, index) {
                    if (value == 0) {
                        return '可预览';
                    } else if (value == 1) {
                        return '可编辑';
                    } else if (value == 2) {
                        return '不可预览';
                    }
                }
            }]],
        onClickRow: function (row) {
            med = row.menuId;
            $('#tt').treegrid('beginEdit', row.menuId);
        }
    });

    function menuDict() {
        var menus = [];//菜单列表
        var menuTreeData = [];//菜单树的列表
        var node = $('#serviceId').datagrid('getSelected');
        var menuPromise = $.get(basePath + "/service-menu/find-menu?selfServiceId=" + node.id, function (data) {
            $.each(data, function (index, item) {
                var menu = {};
                menu.menuId = item.menuId;
                menu.menuName = item.menuName;
                menu.pid = item.pid;
                menu.menuOperate = item.menuOperate;
                menu.children = [];
                menus.push(menu);
            });
            for (var i = 0; i < menus.length; i++) {
                //判断儿子节点
                for (var j = 0; j < menus.length; j++) {
                    if (menus[i].menuId == menus[j].pid) {
                        menus[i].children.push(menus[j]);
                    }
                }
                //判断是不是根节点  start
                if (menus[i].children.length > 0 && !menus[i].pid) {
                    menuTreeData.push(menus[i]);
                }

                if (!menus[i].pid && menus[i].children.length <= 0) {
                    menuTreeData.push(menus[i]);
                }
                //判断是不是根节点  end
            }
        });
        menuPromise.done(function () {
            $("#tt").treegrid('loadData', menuTreeData);
            $("#tt").treegrid("selectRow", 1);
        })
    }

    //datagrid的单元格编辑
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
    var editIndex = undefined;

    function endEditing1() {
        if (editIndex == undefined) {
            return true
        }
        if ($('#serviceId').datagrid('validateRow', editIndex)) {
            $('#serviceId').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing1()) {
            $('#serviceId').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }

    function doAdd() {
        $("#serviceId").datagrid('appendRow', {});
        var rows = $("#serviceId").datagrid('getRows');
        onClickCell(rows.length - 1, 'serviceName');
    }

    function doSave(path) {
        var services = [];// 服务
        var _index = $('#serviceId').datagrid('getRowIndex', $('#serviceId').datagrid('getSelected'));
        $('#serviceId').datagrid('endEdit', _index)
        var node = $('#roleId').datagrid('getSelected');
        var param = []
        param = $("#serviceId").datagrid('getChanges', 'inserted');
        var serviceId = ''
        for (var i = 0; i < param.length; i++) {
            var id = param[i].serviceName
            serviceId += id + ',';
        }
        services.push({'serviceId': serviceId, 'roleId': node.id})
        $.postJSON(basePath + path, JSON.stringify(services), function (res) {
                if (res == 0) {
                    $.messager.alert("提示消息", "保存成功", "success");
                    $('#serviceId').datagrid('load');
                } else {
                    $.messager.alert('提示消息', "此服务已存在", "error");
                    $('#serviceId').datagrid('load');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            }
        )
    }

    function saveMenu() {
        $('#tt').treegrid("endEdit", med);
        var node = $('#serviceId').datagrid('getSelected');
        var changs = $('#tt').treegrid("getChanges", "updated");
        var names = [];
        $.each(changs, function (index, item) {
            names.push({'roleServiceId': node.id, 'menuId': item.menuId, 'menuOperate': item.menuOperate});
        });
        alert(JSON.stringify(names))
        $.postJSON(basePath + '/service-menu/save', JSON.stringify(names), function (res) {
                if (res == 0) {
                    $.messager.alert("提示消息", "保存成功", "success");
                    $('#tt').treegrid('load');
                } else {
                    $.messager.alert('提示消息', "保存失败", "error");
                    $('#tt').treegrid('load');
                }
            }
        )
    }

})