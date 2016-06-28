/**
 * 包名称维护
 * @author yangruidong
 * @version 2016-06-27
 */


$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    //var orgId=config.org_Id;
    var orgId = 1;
    var currentSelectDeptData;
    var unitsForm=[];
    var formatData = function(arr,value){
        if(!arr || !value) return '';
        for(var i= 0,j=arr.length;i<j;i++){
            if(arr[i].id == value){
                return arr[i].text;
            }
        }
    }

    //查询包单位
    $.get(basePath + "/orgSysDict/findUnits",{type:'PACKAGE_UNITS',orgId:orgId}, function (data) {

        for(var i=0;i<data.length;i++)
        {
            unitsForm.push({id:data[i].value,text:data[i].label});
        }

    });


    $("#asepsis-dict").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        url: basePath + '/asepsisDict/findList?orgId=' + orgId,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "类别",
            field: "itemClass",
            width: '5%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'类别不能为空'
                }
            }
        }, {
            title: "代码",
            field: "asepsisCode",
            width: '5%',
            align: 'center'
        }, {
            title: "包名称",
            field: "asepsisName",
            width: '10%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'包名称不能为空'
                }
            }
        }, {
            title: "包规格",
            field: "asepsisSpec",
            width: '5%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'包规格不能为空'
                }
            }
        }, {
            title: "单位",
            field: "units",
            width: '5%',
            align: 'left',
            editor: {
                type: 'combobox',
                options: {
                    required:true,
                    missingMessage:'包单位不能为空' ,
                    valueField: 'id',
                    textField: 'text',
                    data:unitsForm
                }
            }  ,formatter:function(value,row){
                if(!value && unitsForm && unitsForm.length>0){
                    row.units = unitsForm[0].id;
                    return unitsForm[0].text;
                }
                return formatData(unitsForm,value);
            }
        }, {
            title: "有效期(天)",
            field: "validDays",
            width: '7%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'有效期不能为空'
                }
            }
        }, {
            title: "消毒总费",
            field: "antiPrice",
            width: '7%',
            align: 'center',
            formatter: function (value, row, index) {
                var _value = ((isNaN(row.cleanPrice) ? 0 : +row.cleanPrice)
                + (isNaN(row.packPrice) ? 0 : +row.packPrice) +
                (isNaN(row.asepPrice) ? 0 : +row.asepPrice) + (isNaN(row.nobackPrice) ? 0 : +row.nobackPrice)).toFixed(1)
                return _value
            }
        }, {
            title: "清洗费用",
            field: "cleanPrice",
            width: '7%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'清洗费用不能为空'
                }
            },
            formatter: function (value, row, index) {
                var _value1 = (isNaN(row.cleanPrice) ? 0 : +row.cleanPrice).toFixed(1);
                return _value1
            }
        }, {
            title: "打包费用",
            field: "packPrice",
            width: '7%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'打包费用不能为空'
                }
            },
            formatter: function (value, row, index) {
                var _value1 = (isNaN(row.packPrice) ? 0 : +row.packPrice).toFixed(1);
                return _value1
            }
        }, {
            title: "灭菌费用",
            field: "asepPrice",
            width: '7%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'灭菌费用不能为空'
                }
            },
            formatter: function (value, row, index) {
                var _value1 = (isNaN(row.asepPrice) ? 0 : +row.asepPrice).toFixed(1);
                return _value1
            }
        }, {
            title: "辅料费",
            field: "nobackPrice",
            width: '7%',
            align: 'center',
            editor: {
                type:'textbox',
                options:{
                    required:true,
                    missingMessage:'辅料费用不能为空'
                }
            },
            formatter: function (value, row, index) {
                var _value1 = (isNaN(row.nobackPrice) ? 0 : +row.nobackPrice).toFixed(1);
                return _value1
            }
        }, {
            title: "所属科室",
            field: "belongDept",
            width: '7%',
            align: 'center'
            , formatter: function (value, row) {
                return currentSelectDeptData ? currentSelectDeptData.deptName : '';
            }

        }, {
            title: "拼音码",
            field: "inputCode",
            width: '7%',
            align: 'center',
            formatter: function (value, row, index) {
                var name=row.asepsisName ;
                if(row.asepsisName!=undefined){
                    var inputCode = makePy(row.asepsisName)[0];
                    return inputCode;
                }
            }
        }, {
            title: "统计类型",
            field: "memos",
            width: '7%',
            align: 'center',
            editor: 'textbox'
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
        if ($('#asepsis-dict').datagrid('validateRow', editIndex)) {
            $('#asepsis-dict').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing1()) {
            if (field == 'itemClass') {
                var row = $('#asepsis-dict').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'asepsisName') {
                var row = $('#asepsis-dict').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'asepsisSpec') {
                var row = $('#asepsis-dict').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'units') {
                var row = $('#asepsis-dict').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'validDays') {
                var row = $('#asepsis-dict').datagrid('getRows')[index];
                if (row.id)return;
            }
            if (field == 'memos') {
                var row = $('#asepsis-dict').datagrid('getRows')[index];
                if (row.id)return;
            }
            $('#asepsis-dict').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;

           /* var editor= $("#asepsis-dict").datagrid('getEditor',{index:index,field:'units'})  ;
            $(editor.target).combobox('loadData',unitsForm);*/
        }
    }
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
            $.get(basePath + "/asepsisDict/findPage",{deptCode:currentSelectDeptData?currentSelectDeptData.deptCode:'',orgId:orgId,pageName:null}, function (data) {
                $("#pageName").combobox('loadData', data);
            });
        }
    });



     //获取当前机构下的所有包
    $("#pageName").combobox({
        url:basePath + '/asepsisDict/findList?orgId=' + orgId,
        valueField:'asepsisName',
        textField:'asepsisName' ,
        method:'GET'
    });

    //开始编辑行
    $("#addBtn").on('click', function () {
        if(!currentSelectDeptData) {
            $.messager.alert("系统提示", "请先选择科室，在维护数据", "info");
             return ;
        }
        var asepsisCode = currentSelectDeptData.deptCode
        var rows = $("#asepsis-dict").datagrid('getRows')
        if(rows.length > 0) {
            var c = rows[rows.length - 1].asepsisCode.substr(asepsisCode.length)
            asepsisCode += ('000000000' + (+c + 1)).substr(asepsisCode.length-10)
        }else {
            asepsisCode += '00000000001'.substr(asepsisCode.length-10)
        }



        $("#asepsis-dict").datagrid('appendRow', {asepsisCode:asepsisCode,'belongDept': currentSelectDeptData.deptCode});
        var rows = $("#asepsis-dict").datagrid('getRows');
        onClickCell(rows.length - 1, 'asepsis-dict');
    });


    //查询
    $("#seaBtn").on('click', function () {
        var deptCode = $("#dept").combogrid("getValue");
        //选取科室时重新加载数据
        var pageName=$("#pageName").combobox("getText");
        $.get(basePath + "/asepsisDict/findPage",{deptCode:currentSelectDeptData.deptCode,orgId:orgId,pageName:pageName?pageName:''}, function (data) {
            $("#asepsis-dict").datagrid('loadData', data);
        });
    });


    //包名称保存
    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#asepsis-dict").datagrid("endEdit", editIndex);
        }

        var insertData = $("#asepsis-dict").datagrid("getChanges", "inserted");
        var updateData = $("#asepsis-dict").datagrid("getChanges", "updated");
        var deleteData = $("#asepsis-dict").datagrid("getChanges", "deleted");

        var asepsisDictVo = {};
        asepsisDictVo.inserted = insertData;
        asepsisDictVo.deleted = deleteData;
        asepsisDictVo.updated = updateData;

        asepsisDictVo.orgId = orgId;

                                alert(updateData.length)
        if (asepsisDictVo) {
            $.postJSON(basePath + "/asepsisDict/saveAll", JSON.stringify(asepsisDictVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#asepsis-dict").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败，字段是唯一键或者其他字段不能为空", "error");
            })
        }
    });


    //删除
    $("#delBtn").on("click", function () {
        var row = $("#asepsis-dict").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#asepsis-dict").datagrid('getRowIndex', row);
            $("#asepsis-dict").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });


});




