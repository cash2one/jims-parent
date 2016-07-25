/**
 * 包名称维护
 * @author yangruidong
 * @version 2016-06-27
 */


$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    var orgId=config.org_Id;
    //var orgId = 1;

    $('#asepsis-borrow').datagrid({
        fit: true,
        border: 1,
        fitColumns: true,
        singleSelect: true,
        remoteSort: false,
        idField: 'id',
        method: 'GET',
        url: basePath + '/asepsisLendRec/findList?orgId=' + orgId+'&returnFlag=1',
        columns: [[      //每个列具体内容
            {field: 'itemNo', title: '序号', width: '7%', align: 'center'},
            {field: 'toDeptName', title: '借物科室', width: '10%', align: 'center'}
            , {field: 'documentNo', title: '借物单据号', width: '10%', align: 'center'}
            , {field: 'itemCode', title: '代码', width: '7%', align: 'center'}
            , {field: 'itemName', title: '名称', width: '7%', align: 'center'}
            , {field: 'itemSpec', title: '规格', width: '7%', align: 'center'}
            , {
                field: 'lendAmount', title: '借物数量', width: '7%', align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true,
                        missingMessage: '数量不能为空'
                    }
                }
            }
            , {field: 'units', title: '单位', width: '7%', align: 'center'}
            , {field: 'antiFee', title: '消毒费', width: '7%', align: 'center'}
            , {field: 'antiFeeSum', title: '消毒费合计', width: '7%', align: 'center'}
            , {field: 'antiDate', title: '消毒日期', width: '7%', align: 'center',
                formatter: function (value, row, index) {
                    return formatDatebox(value)
                }}
            , {field: 'reqOperator', title: '借物人', width: '7%', align: 'center'}
            , {field: 'memos', title: '说明', width: '7%', align: 'center'}
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
        if ($('#asepsis-borrow').datagrid('validateRow', editIndex)) {
            $('#asepsis-borrow').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }


    function onClickCell(index, field) {
        if (endEditing1()) {
            $('#asepsis-borrow').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }


    //送物修改
    $("#send").click(function () {
        var url = basePath + "/asepsisSendRec/findList?orgId=" + orgId;
        $('#asepsis-borrow').datagrid('reload', url);
        $('#asepsis-borrow').datagrid({
            columns: [[      //每个列具体内容
                {field: 'itemNo', title: '序号', width: '7%', align: 'center'}
                , {field: 'fromDeptName', title: '送物科室', width: '10%', align: 'center'}
                , {field: 'itemCode', title: '项目代码', width: '7%', align: 'center'}
                , {field: 'itemName', title: '项目名称', width: '7%', align: 'center'}
                , {field: 'itemSpec', title: '项目规格', width: '7%', align: 'center'}
                , {
                    field: 'sendAmount', title: '送物数量', width: '7%', align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '数量不能为空'
                        }
                    }
                }
                , {field: 'units', title: '单位', width: '7%', align: 'center'}
                , {field: 'antiFee', title: '消毒费', width: '7%', align: 'center'}
                , {field: 'antiFeeSum', title: '消毒费合计', width: '7%', align: 'center'}
                , {field: 'sendDate', title: '送物日期', width: '7%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDatebox(value) ;
                    }}
                , {field: 'sender', title: '送物人', width: '7%', align: 'center'}
                , {field: 'reqOperator', title: '操作员', width: '7%', align: 'center'}
            ]],
            onClickCell: onClickCell1
        });
    });


    var editIndex1 = undefined;

    function endEditing2() {
        if (editIndex1 == undefined) {
            return true
        }
        if ($('#asepsis-borrow').datagrid('validateRow', editIndex1)) {
            $('#asepsis-borrow').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell1(index, field) {
        if (endEditing2()) {
            $('#asepsis-borrow').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex1 = index;
        }
    }


    //借物修改
    $("#borrow").click(function () {
        var url = basePath + "/asepsisLendRec/findList?orgId=" + orgId+'&returnFlag=1';
        $('#asepsis-borrow').datagrid('reload', url);



        $('#asepsis-borrow').datagrid({
            columns: [[      //每个列具体内容
                {field: 'itemNo', title: '序号', width: '7%', align: 'center'},
                {field: 'toDeptName', title: '借物科室', width: '10%', align: 'center'}
                , {field: 'documentNo', title: '借物单据号', width: '7%', align: 'center'}
                , {field: 'itemCode', title: '代码', width: '7%', align: 'center'}
                , {field: 'itemName', title: '名称', width: '7%', align: 'center'}
                , {field: 'itemSpec', title: '规格', width: '7%', align: 'center'}
                , {
                    field: 'lendAmount', title: '借物数量', width: '7%', align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '数量不能为空'
                        }
                    }
                }
                , {field: 'units', title: '单位', width: '7%', align: 'center'}
                , {field: 'antiFee', title: '消毒费', width: '7%', align: 'center'}
                , {field: 'antiFeeSum', title: '消毒费合计', width: '7%', align: 'center'}
                , {field: 'antiDate', title: '消毒日期', width: '7%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDatebox(value) ;
                    }}
                , {field: 'reqOperator', title: '借物人', width: '7%', align: 'center'}
                , {field: 'memos', title: '说明', width: '7%', align: 'center'}
            ]]
        });

    });

    $("#itemName").combogrid({
        panelWidth: 300,
        mode: 'remote',
        idField: 'asepsisName',
        textField: 'asepsisName',
        method: 'GET',
        url: basePath + '/asepsisDict/findList?orgId=' + orgId,
        columns: [[
            {field: 'asepsisCode', title: '包代码', width: 100},
            {field: 'asepsisName', title: '包名称', width: 100},
            {field: 'inputCode', title: '拼音码', width: 100} ,
            {field: 'deptName', title: '所属科室', width: 100}
        ]]
    });

    //包对换
    $("#package").click(function () {
        var url = basePath + '/asepsisLendRec/findList?orgId=' + orgId+'&returnFlag=4';
        $('#asepsis-borrow').datagrid('reload', url);
        $('#asepsis-borrow').datagrid({
            columns: [[      //每个列具体内容
                {field: 'id', title: 'id', width: '7%', align: 'center', hidden: 'true'},
                {field: 'itemNo', title: '序号', width: '7%', align: 'center'}
                , {field: 'toDeptName', title: '借物科室', width: '10%', align: 'center'}
                , {field: 'documentNo', title: '借物单据号', width: '10%', align: 'center'}
                , {field: 'itemCode', title: '代码', width: '7%', align: 'center'}
                , {field: 'itemName', title: '名称', width: '7%', align: 'center'}
                , {field: 'itemSpec', title: '规格', width: '7%', align: 'center'}
                , {field: 'lendAmount', title: '借物数量', width: '7%', align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '数量不能为空'
                        }
                    }}
                , {field: 'units', title: '单位', width: '7%', align: 'center'}
                , {field: 'antiFee', title: '消毒费', width: '7%', align: 'center'}
                , {field: 'antiFeeSum', title: '消毒费合计', width: '7%', align: 'center'}
                , {field: 'antiDate', title: '消毒日期', width: '7%', align: 'center',
                    formatter: function (value, row, index) {
                        return formatDatebox(value) ;
                    }}
                , {field: 'lender', title: '借物人', width: '7%', align: 'center'}
                , {field: 'memos', title: '说明', width: '7%', align: 'center'}
            ]] ,
            onClickCell: onClickCell3
        });

    });

    var editIndex3 = undefined;

    function endEditing4() {
        if (editIndex3 == undefined) {
            return true
        }
        if ($('#asepsis-borrow').datagrid('validateRow', editIndex3)) {
            $('#asepsis-borrow').datagrid('endEdit', editIndex3);
            editIndex3= undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell3(index, field) {
        if (endEditing4()) {
            $('#asepsis-borrow').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex3 = index;
        }
    }



    //日期
    $('#startDate').datebox({
        value: formatDatebox(new Date())
    })
    $('#endDate').datebox({
        value: formatDatebox(new Date())
    })


//查询
    $("#seaBtn").on('click', function () {

        //借物修改的查询
        if ($("#borrow").is(":checked")) {

            var params = {
                orgId: orgId,
                lendDateStart: parseToDate($('#startDate').datebox('getValue')+' 00:00:00'),
                lendDateEnd: parseToDate($('#endDate').datebox('getValue')+' 23:59:59 '      ) ,
                returnFlag:1  ,
                itemName: $("#itemName").combogrid('getValue'),
                documentNo :$("#documentNo").val()
            }

            $.get(basePath + '/asepsisLendRec/findList',params,function(res){
                $('#asepsis-borrow').datagrid('loadData',res)
            })

        }

        //送物修改的查询
        if ($("#send").is(":checked")) {

            var params = {
                orgId: orgId,
                sendDateStart: parseToDate($('#startDate').datebox('getValue')+' 00:00:00'),
                sendDateEnd: parseToDate($('#endDate').datebox('getValue')+' 23:59:59 '      )  ,
                itemName: $("#itemName").combobox('getValue')
            }

            $.get(basePath + '/asepsisSendRec/findList',params,function(res){
                $('#asepsis-borrow').datagrid('loadData',res)
            })

        }

        //包对换修改的查询
        if ($("#package").is(":checked")) {

            var params = {
                orgId: orgId,
                lendDateStart: parseToDate($('#startDate').datebox('getValue')+' 00:00:00'),
                lendDateEnd: parseToDate($('#endDate').datebox('getValue')+' 23:59:59 '      ) ,
                returnFlag:4  ,
                itemName: $("#itemName").combogrid('getValue'),
                documentNo :$("#documentNo").val()
            }
            $.get(basePath + '/asepsisLendRec/findList',params,function(res){
                $('#asepsis-borrow').datagrid('loadData',res)
            })

        }
    });


    //包名称保存
    $("#saveBtn").on("click", function () {

        if ($("#send").is(":checked")) {
            if (editIndex1 || editIndex1 == 0) {
                $("#asepsis-borrow").datagrid("endEdit", editIndex1);
            }
            var updateData = $("#asepsis-borrow").datagrid("getChanges", "updated");
            var asepsisVo = {};
            asepsisVo.updated = updateData;
            if (asepsisVo) {
                $.postJSON(basePath + "/asepsisSendRec/saveAll", JSON.stringify(asepsisVo), function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info");
                        var url = basePath + "/asepsisSendRec/findList?orgId=" + orgId;
                        $("#asepsis-borrow").datagrid('reload', url);
                    }
                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                })
            }

        }

        if ($("#borrow").is(":checked")) {
            if (editIndex || editIndex == 0) {
                $("#asepsis-borrow").datagrid("endEdit", editIndex);
            }
            var updateData = $("#asepsis-borrow").datagrid("getChanges", "updated");
            var asepsisVo = {};
            asepsisVo.updated = updateData;
            if (asepsisVo) {
                $.postJSON(basePath + "/asepsisLendRec/saveAll", JSON.stringify(asepsisVo), function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info");
                        var url = basePath + "/asepsisLendRec/findList?orgId=" + orgId+'&returnFlag=1';
                        $("#asepsis-borrow").datagrid('reload', url);
                    }
                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                })
            }

        }


        if ($("#package").is(":checked")) {
            if (editIndex3 || editIndex3 == 0) {
                $("#asepsis-borrow").datagrid("endEdit", editIndex3);
            }
            var updateData = $("#asepsis-borrow").datagrid("getChanges", "updated");
            var asepsisVo = {};
            asepsisVo.updated = updateData;
            if (asepsisVo) {
                $.postJSON(basePath + "/asepsisLendRec/saveAll", JSON.stringify(asepsisVo), function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info");
                        var url = basePath + '/asepsisLendRec/findList?orgId=' + orgId+'&returnFlag=4';
                        $("#asepsis-borrow").datagrid('reload', url);
                    }
                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                })
            }

        }


    });

});




