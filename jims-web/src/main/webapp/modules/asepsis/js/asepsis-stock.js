/**
 * 包名称维护
 * @author yangruidong
 * @version 2016-06-27
 */


$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    //var orgId=config.org_Id;
    var orgId = 1;
    //当前登录人
    var user = 1;

    $("#asepsis-stock").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        url: basePath + '/asepsisStock/findList?orgId=' + orgId ,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "序号",
            field: "itemNo",
            width: '5%',
            align: 'center'
        }, {
            title: "单据号",
            field: "documentNo",
            width: '5%',
            align: 'center'
        }, {
            title: "所属科室",
            field: "fromDeptName",
            width: '7%',
            align: 'center'
        }, {
            title: "代码",
            field: "itemCode",
            width: '10%',
            align: 'center',
            editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 400,
                    mode: 'remote',
                    idField: 'asepsisCode',
                    textField: 'asepsisCode',
                    method: 'GET',
                    url: basePath + '/asepsisDict/findList?orgId=' + orgId,
                    columns: [[
                        {field: 'asepsisCode', title: '包代码', width: 100},
                        {field: 'asepsisName', title: '包名称', width: 100},
                        {field: 'inputCode', title: '拼音码', width: 100},
                        {field: 'deptName', title: '所属科室', width: 100},
                        {field: 'asepsisSpec', title: '规格', width: 100, hidden: 'true'},
                        {field: 'label', title: '单位', width: 100, hidden: 'true'}

                    ]],
                    onSelect: function (index, data) {
                        var row = $('#asepsis-stock').datagrid('getSelected');
                        row.itemName = data.asepsisName;
                        row.itemSpec = data.asepsisSpec;

                        row.units = data.units;
                        row.label=data.label;

                        row.fromDept = data.belongDept;
                        row.fromDeptName = data.deptName;
                        $('#asepsis-stock').datagrid('endEdit', editIndex);
                    }
                }
            }
        }, {
            title: "名称",
            field: "itemName",
            width: '5%',
            align: 'center'
        }, {
            title: "规格",
            field: "itemSpec",
            width: '5%',
            align: 'left'

        }, {
            title: "数量",
            field: "amount",
            width: '7%',
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
            width: '7%',
            align: 'center'
        }, {
            title: "消毒灭菌日期",
            field: "antiDate",
            width: '10%',
            align: 'center',
            formatter: function (value, row, index) {
                var _value1 = getNowFormatDate1();
                return _value1
            }
        }, {
            title: "操作员",
            field: "operator",
            width: '7%',
            align: 'center'
        }, {
            title: "修改时间",
            field: "alterDate",
            width: '7%',
            align: 'center'
        }, {
            title: "备注",
            field: "memos",
            width: '7%',
            align: 'center'

        }
        ]],
        onClickCell: onClickCell
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

    var editIndex = undefined;

    function endEditing1() {
        if (editIndex == undefined) {
            return true
        }
        if ($('#asepsis-stock').datagrid('validateRow', editIndex)) {
            $('#asepsis-stock').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing1()) {
            if (field == 'itemNo') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'documentNo') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'fromDept') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'itemCode') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'itemName') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'itemSpec') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'units') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'alterDate') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'memos') {
                var row = $('#asepsis-stock').datagrid('getRows')[index];
                if (row.id)return;
            }
            $('#asepsis-stock').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }
    var deptCode = $("#dept").combogrid("getValue");
    //选取科室时重新加载数据
    var itemName = $("#itemName").combobox("getText");
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
        ]]  ,
        onSelect:function(){
            var deptCode = $("#dept").combogrid("getValue");
            //选取科室时重新加载数据
            var itemName = $("#itemName").combobox("getText");
            $.get(basePath + "/asepsisStock/findList", {fromDept: deptCode, orgId: orgId, itemName: itemName ? itemName : ''}, function (data) {
                $("#asepsis-stock").datagrid('loadData', data);
            });
        }
    });


//获取当前机构下的所有包
    $("#itemName").combobox({

        url: basePath + '/asepsisStock/findList?orgId=' + orgId,
        valueField: 'itemName',
        textField: 'itemName',
        method: 'GET'  ,
        onSelect:function(){
            var deptCode = $("#dept").combogrid("getValue");
            //选取科室时重新加载数据
            var itemName = $("#itemName").combobox("getText");
            $.get(basePath + "/asepsisStock/findList", {fromDept: deptCode, orgId: orgId, itemName: itemName ? itemName : ''}, function (data) {
                $("#asepsis-stock").datagrid('loadData', data);
            });
        }
    });

    //格式化时间 年月之间带“-”
    function getNowFormatDate1() {
        var day = new Date();
        var Year = 0;
        var Month = 0;
        var Day = 0;
        var CurrentDate = "";
        //初始化时间
        Year = day.getFullYear();
        Month = day.getMonth() + 1;
        Day = day.getDate();
        CurrentDate += Year + "-";
        if (Month >= 10) {
            CurrentDate += Month + "-";
        }
        else {
            CurrentDate += "0" + Month + "-"
        }
        if (Day >= 10) {
            CurrentDate += Day;
        }
        else {
            CurrentDate += "0" + Day;
        }
        return CurrentDate;
    }

    //格式化时间 年月之间不带“-”
    function getNowFormatDate() {
        var day = new Date();
        var Year = 0;
        var Month = 0;
        var Day = 0;
        var CurrentDate = "";
        //初始化时间
        Year = day.getFullYear();
        Month = day.getMonth() + 1;
        Day = day.getDate();
        CurrentDate += Year;
        if (Month >= 10) {
            CurrentDate += Month;
        }
        else {
            CurrentDate += "0" + Month
        }
        if (Day >= 10) {
            CurrentDate += Day;
        }
        else {
            CurrentDate += "0" + Day;
        }
        return CurrentDate;
    }

    //开始编辑行
    $("#addBtn").on('click', function () {
        var nowdate = getNowFormatDate();
        var documentNo = nowdate.substr(2);
        var rows = $("#asepsis-stock").datagrid('getRows')
        if (rows.length > 0) {
            var c = rows[rows.length - 1].documentNo.substr(documentNo.length)
            documentNo += ('000000000' + (+c + 1)).substr(documentNo.length - 10)
        } else {
            documentNo += '00000000001'.substr(documentNo.length - 10)
        }
        //序号
        var itemNo = documentNo.toString().charAt(documentNo.length - 1);
        $("#asepsis-stock").datagrid('appendRow', {
            itemNo: itemNo,
            documentNo: documentNo,
            'operator': user
        });
        var rows = $("#asepsis-stock").datagrid('getRows');
        onClickCell(rows.length - 1, 'asepsis-stock');
    });


//查询
    $("#seaBtn").on('click', function () {
        var deptCode = $("#dept").combogrid("getValue");
        //选取科室时重新加载数据
        var itemName = $("#itemName").combobox("getText");
        $.get(basePath + "/asepsisStock/findList", {fromDept: deptCode, orgId: orgId, itemName: itemName ? itemName : ''}, function (data) {
            $("#asepsis-stock").datagrid('loadData', data);
        });
    });

//包名称保存
    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#asepsis-stock").datagrid("endEdit", editIndex);
        }

        var insertData = $("#asepsis-stock").datagrid("getChanges", "inserted");
        var updateData = $("#asepsis-stock").datagrid("getChanges", "updated");
        var deleteData = $("#asepsis-stock").datagrid("getChanges", "deleted");

        //删除多余的字段
        for(var i=0;i<insertData.length;i++){
            delete insertData[i]["fromDeptName"];
            delete insertData[i]["label"];
        }
        for(var j=0;j<updateData.length;j++){
            delete updateData[j]["fromDeptName"];
            delete updateData[j]["label"];
        }
        for(var x=0;x<deleteData.length;x++){
            delete deleteData[x]["fromDeptName"];
            delete deleteData[x]["label"];
        }


        var asepsisStockVo = {};
        asepsisStockVo.inserted = insertData;
        asepsisStockVo.deleted = deleteData;
        asepsisStockVo.updated = updateData;

        asepsisStockVo.orgId = orgId;
        if (asepsisStockVo) {
            $.postJSON(basePath + "/asepsisStock/saveAll", JSON.stringify(asepsisStockVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#asepsis-stock").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });
    //删除
    $("#delBtn").on("click", function () {
        var row = $("#asepsis-stock").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#asepsis-stock").datagrid('getRowIndex', row);
            $("#asepsis-stock").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });


})
;




