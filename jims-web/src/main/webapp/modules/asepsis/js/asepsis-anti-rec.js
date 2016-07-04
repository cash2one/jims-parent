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

    $("#asepsis-anti-rec").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + '/asepsisStock/findList?orgId=' + orgId ,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "所属科室",
            field: "belongDept",
            width: '5%',
            align: 'center'
        }, {
            title: "消毒批号",
            field: "documentNo",
            width: '5%',
            align: 'center'
        }, {
            title: "代码",
            field: "asepsisCode",
            width: '7%',
            align: 'center'
        }, {
            title: "名称",
            field: "asepsisName",
            width: '7%',
            align: 'center'
        }, {
            title: "规格",
            field: "asepsisSpec",
            width: '5%',
            align: 'left'

        }, {
            title: "数量",
            field: "amount",
            width: '5%',
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: '5%',
            align: 'center'
        }, {
            title: "消毒人",
            field: "antiOperator",
            width: '7%',
            align: 'center'
        }, {
            title: "锅号",
            field: "boilerNo",
            width: '7%',
            align: 'center'
        }, {
            title: "锅次",
            field: "boilerTimes",
            width: '7%',
            align: 'center'
        }, {
            title: "消毒日期",
            field: "antiDate",
            width: '10%',
            align: 'center'

        } , {
            title: "消毒方法",
            field: "antiWays",
            width: '10%',
            align: 'center'

        }, {
            title: "备注",
            field: "memos",
            width: '10%',
            align: 'center'

        }, {
            title: "查对者",
            field: "checker",
            width: '7%',
            align: 'center'

        }
        ]]
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
           /* if (field == 'itemNo') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'documentNo') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'fromDept') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'itemCode') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'itemName') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'itemSpec') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'units') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'alterDate') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'memos') {
                var row = $('#asepsis-anti-rec').datagrid('getRows')[index];
                if (row.id)return;
            }*/
            $('#asepsis-anti-rec').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }


    var deptCode = $("#dept").combogrid("getValue");
    //选取科室时重新加载数据
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
        onSelect: function () {
            var deptCode = $("#dept").combogrid("getValue");
            //选取科室时重新加载数据

            $.get(basePath + "/asepsisStock/findList", {fromDept: deptCode, orgId: orgId}, function (data) {
                $("#asepsis-anti-rec").datagrid('loadData', data);
            });
        }
    });


//查询
    $("#seaBtn").on('click', function () {
        var deptCode = $("#dept").combogrid("getValue");
        //选取科室时重新加载数据

    });

})
;




