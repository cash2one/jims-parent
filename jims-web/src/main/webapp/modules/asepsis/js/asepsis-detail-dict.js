/**
 *包内物品管理
 * @author yangruidong
 * @version 2016-06-29
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    //var orgId=parent.config.org_id;
    var orgId = 1;
    var asepsisCode;
    var staffFrom = [];
    var currentSelectDeptData;
    var editIndex1 = undefined;
    //包名称维护
    $("#asepsis-dict").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        url: basePath + '/asepsisDict/findList?orgId=' + orgId,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'asepsisCode',
            field: "asepsisCode",
            hidden: true
        }, {
            title: '包名称',
            field: 'asepsisName',
            width: '20%',
            align: 'center'
        }, {
            title: '包规格',
            field: 'asepsisSpec',
            width: '20%',
            align: 'center'
        }, {
            title: '单位',
            field: 'label',
            width: '20%',
            align: 'center'
        }, {
            title: '辅料费',
            field: 'nobackPrice',
            width: '20%',
            align: 'center'
        }, {
            title: '所属科室',
            field: 'deptName',
            width: '20%',
            align: 'center'
        }]],
        onClickRow: function (index, row) {
            staffFrom = [];
            var node = $("#asepsis-dict").datagrid("getSelected");
            asepsisCode = node.asepsisCode;
            var dictType = 'v_exp_dict_price_list';
            //加载字段名称
            jQuery.ajax({
                'type': 'GET',
                'url': basePath + '/input-setting/list?orgId=' + orgId + '&dictType=' + dictType,
                'contentType': 'application/json',
                'dataType': 'json',
                'success': function (data) {
                    for (var i = 0; i < data.length; i++) {
                        staffFrom.push({
                            itemCode: data[i].exp_code, itemName: data[i].exp_name, supplier: data[i].supplier
                            , expSpec: data[i].exp_spec
                            , units: data[i].units
                            , label: data[i].label
                            , inputcode: data[i].input_code
                            , tradePrice: data[i].trade_price
                            , retailPricce: data[i].retail_price
                        });
                    }
                },
                'error': function (data) {
                    $.messager.alert("系统提示", "加载数据出错");
                }
            });
            var url = basePath + "/asepsisDetailDict/findList?asepsisCode=" + asepsisCode + "&orgId=" + orgId;
            $("#asepsis-detail-dict").datagrid("reload", url);

        }
    });


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
    //获取当前机构下的所有的科室
    $("#dept").combogrid({
        panelWidth: 300,
        mode: 'remote',
        idField: 'deptCode',
        textField: 'deptName',
        method: 'GET',
        url: basePath + '/dept-dict/findListWithFilter?orgId=' + orgId,
        columns: [[
            {field: 'deptCode', title: '科室代码', width: 100},
            {field: 'deptName', title: '科室名称', width: 100},
            {field: 'inputCode', title: '拼音码', width: 100}
        ]],
        onSelect: function (index, row) {
            currentSelectDeptData = row;
            $.get(basePath + "/asepsisDict/findList", {
                belongDept: currentSelectDeptData ? currentSelectDeptData.deptCode : '',
                orgId: orgId
            }, function (data) {
                $("#asepsisName").combobox('loadData', data);
            });
        }
    });


    //获取当前机构下的所有包
    $("#asepsisName").combobox({

        url: basePath + '/asepsisDict/findList?orgId=' + orgId,
        valueField: 'asepsisName',
        textField: 'asepsisName',
        method: 'GET'
    });


    //查询
    $("#seaBtn").on('click', function () {

        var deptCode = $("#dept").combogrid("getValue");
        //选取科室时重新加载数据
        var asepsisName = $("#asepsisName").combobox("getText");
        $.get(basePath + "/asepsisDict/findList", {
            belongDept: currentSelectDeptData.deptCode,
            orgId: orgId,
            asepsisName: asepsisName ? asepsisName : ''
        }, function (data) {
            $("#asepsis-dict").datagrid('loadData', data);
        });
    });


    //包内物品管理
    $("#asepsis-detail-dict").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        method: 'GET',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "项目代码",
            field: "itemCode",
            width: '10%',
            align: 'center'
        }, {
            title: "项目名称",
            field: 'itemName',
            width: '10%',
            align: 'center',
            editor: {

                type: 'combogrid',
                options: {
                    panelWidth: 400,
                    idField: 'itemName',
                    textField: 'itemName',
                    columns: [[
                        {field: 'itemCode', title: '代码', width: 100, align: 'center'},
                        {field: 'itemName', title: '名称', width: 100, align: 'center'},
                        {field: 'supplier', title: '厂家', width: 100, align: 'center'},
                        {field: 'expSpec', title: '规格', width: 100, align: 'center'},
                        {field: 'label', title: '单位', width: 100, align: 'center'},
                        {field: 'inputcode', title: '拼音码', width: 100, align: 'center'},
                        {field: 'tradePrice', title: '批发价', width: 100, align: 'center'},
                        {field: 'retailPricce', title: '零售价', width: 100, align: 'center'}
                    ]],
                    onSelect: function (index, data) {
                        var row = $('#asepsis-detail-dict').datagrid('getSelected');
                        row.itemCode = data.itemCode;
                        row.itemName = data.itemName;
                        row.itemSpec = data.expSpec;
                        row.units = data.units;
                        row.label = data.label;
                        row.itemPrice = data.tradePrice;
                        $('#asepsis-detail-dict').datagrid('endEdit', editIndex1);
                    }
                }
            }
        }, {
            title: "项目规格",
            field: "itemSpec",
            width: '10%',
            align: 'center'
        }, {
            title: "数量",
            field: "amount",
            width: '10%',
            align: 'center',
            editor: {
                type: 'textbox',
                options: {
                    required: true,
                    missingMessage: '数量不能为空'
                }
            }
        }, {
            title: "单位",
            field: "label",
            width: '10%',
            align: 'center'
        }, {
            title: "项目单价",
            field: "itemPrice",
            width: '10%',
            align: 'center'
        }, {
            title: "备注",
            field: "memos",
            width: '10%',
            align: 'center',
            editor: {
                type: 'textbox',
                options: {}
            }
        }
        ]],
        onClickCell: onClickCell1
    });

    //datagrid的单元格编辑
    function endEditing2() {
        if (editIndex1 == undefined) {
            return true
        }
        if ($('#asepsis-detail-dict').datagrid('validateRow', editIndex1)) {
            $('#asepsis-detail-dict').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell1(index, field) {
        if (endEditing2()) {

            if (field == 'itemName') {
                var row = $('#asepsis-detail-dict').datagrid('getRows')[index];
                if (row.id)return;
            }


            $('#asepsis-detail-dict').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex1 = index;
            if (field == 'itemName') {
                var editor = $("#asepsis-detail-dict").datagrid('getEditor', {index: index, field: 'itemName'});
                $(editor.target).combogrid('grid').datagrid('loadData', staffFrom);
            }
        }
    }

    //开始编辑行
    $("#addBtn").on('click', function () {

        if (!$("#asepsis-dict").datagrid('getSelected')) {
            $.messager.alert("系统提示", "请先选择要管理物品的包", "info");
            return false;
        }

        $("#asepsis-detail-dict").datagrid('appendRow', {
            amount:0,
            orgId: orgId,
            asepsisCode: $("#asepsis-dict").datagrid('getSelected').asepsisCode
        });
        var rows = $("#asepsis-detail-dict").datagrid('getRows');
        onClickCell1(rows.length - 1, 'itemName');
    });

    //包内物品保存
    $("#saveBtn").on("click", function () {

        //不能添加重复的项目
        var data = $("#asepsis-detail-dict").datagrid('getData');
        for (var i = 0; i < data.total; i++) {
            for (var j = i + 1; j < data.total; j++) {
                if (data.rows[i].itemCode == data.rows[j].itemCode) {
                    $.messager.alert("系统提示", "不能添加重复的项目,请修改后再保存", "info");
                    return;
                }
            }
        }
        //实现保存
        if (editIndex1 || editIndex1 == 0) {
            $("#asepsis-detail-dict").datagrid("endEdit", editIndex1);
        }

        var insertData = $("#asepsis-detail-dict").datagrid("getChanges", "inserted");
        var updateData = $("#asepsis-detail-dict").datagrid("getChanges", "updated");
        var deleteData = $("#asepsis-detail-dict").datagrid("getChanges", "deleted");


        //删除多余的字段
        for (var i = 0; i < insertData.length; i++) {
            delete insertData[i]["label"];

            //数量必须大于0
            if (insertData[i].amount == "0") {
                $.messager.alert("系统提示", "数量不能为空,必须大于0", "info");
                return;
            }
        }
        for (var j = 0; j < updateData.length; j++) {
            delete updateData[j]["label"];
        }
        for (var x = 0; x < deleteData.length; x++) {
            delete deleteData[x]["label"];
        }

        var asepsisDetailDictVo = {};
        asepsisDetailDictVo.inserted = insertData;
        asepsisDetailDictVo.deleted = deleteData;
        asepsisDetailDictVo.updated = updateData;

        if (asepsisDetailDictVo) {
            $.postJSON(basePath + "/asepsisDetailDict/saveAll", JSON.stringify(asepsisDetailDictVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#asepsis-detail-dict").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });
    //删除
    $("#delBtn").on("click", function () {
        var row = $("#asepsis-detail-dict").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#asepsis-detail-dict").datagrid('getRowIndex', row);
            $("#asepsis-detail-dict").datagrid('deleteRow', rowIndex);
            if (editIndex1 == rowIndex) {
                editIndex1 = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

});




