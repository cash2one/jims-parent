var editRow = undefined;
$(function() {
    $('#clinicItem').datagrid({
        singleSelect: true,
        fit: true,
        method:'GET',
        url: basePath+'/treatment/findList',
       // url:'/modules/clinic/clinicItem/js/datagrid_data.json',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'itemName', title: '项目名称', width: '20%', align: 'center', editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    idField: 'itemCode',
                    textField: 'itemName',
                    queryParams:{zjm:''},
                    url: '/modules/clinic/clinicItem/js/clinic_data.json',
                    columns: [[
                        {field: 'itemName', title: '项目名称', width: '20%', align: 'center'},
                        {field: 'itemCode', title: '项目代码', width: '20%', align: 'center'},
                        {field: 'itemClass', title: '项目类型', width: '10%', align: 'center', editor: 'text'},
                        {field: 'inputCode', title: '拼音输入码', width: '10%', align: 'center', editor: 'text'},
                        {field: 'inputCodeWb', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'},
                        {field: 'orgId', title: '部门', width: '10%', align: 'center', editor: 'text'},
                        {field: 'guige', title: '规格', width: '10%', align: 'center', editor: 'text'},
                        {field: 'company', title: '单位', width: '10%', align: 'center', editor: 'text'}
                    ]],
                    fitColumns: true
                }
               /* onClickRow: function (index, row) {
                    ed = $("#clinicItem").datagrid('getEditor', {index: editRow, field: 'expCode'});
                    //$(ed.target).textbox('setValue', row.expCode);
                    currentExpCode = row.expCode;
                    $("#expDetailDialog").dialog('open');
                },
                keyHandler: $.extend({}, $.fn.combogrid.defaults.keyHandler, {
                    enter: function (e) {
                        var row = $(this).combogrid('grid').datagrid('getSelected');
                        if (row) {
                        ed = $("#right").datagrid('getEditor', {index: editIndex, field: 'expCode'});
                            //$(ed.target).textbox('setValue', row.expCode);
                            currentExpCode = row.expCode;
                            $("#expDetailDialog").dialog('open');
                        }
                        $(this).combogrid('hidePanel');
                    }
                })*/
            }

                 },
            {field: 'amount', title: '数量', width: '5%', align: 'center', editor: 'text'},
            {field: 'units', title: '单位', width: '5%', align: 'center'},
             {field:'frequency',title:'频次',width:'10%',align:'center',editor:'text'},
            {field: 'performedBy', title: '执行科室', width: '10%', align: 'center', editor: 'text'},
             {field:'wardCode',title:'护理单元',width:'10%',align:'center',editor:'text'},
            {field: 'charges', title: '实收', width: '5%', align: 'center'},
            {field: 'serialNo', title: '开单序号', width: '10%', align: 'center'},
            {field: 'chargeIndicator', title: '收费标识', width: '5%', align: 'center'}

        ]],


        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                $("#clinicItem").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#clinicItem").datagrid("endEdit", editRow);
                }
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (editRow == undefined) {
                    $("#clinicItem").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {}
                    });
                    //将新插入的那一行开户编辑状态
                    $("#clinicItem").datagrid("beginEdit", 0);
                    //给当前编辑的行赋值
                    editRow = 0;
                }
                /*  $("#zhenduan").datagrid('insertRow', {
                 index:0,

                 row:{}
                 });*/
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
                $("#clinicItem").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#clinicItem").datagrid("endEdit", editRow);
                }
                save();
            }
        }
        ], onAfterEdit: function (rowIndex, rowData, changes) {

            editRow = undefined;
        }, onDblClickRow: function (rowIndex, rowData) {//双击行
            if (editRow != undefined) {
                $("#clinicItem").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#clinicItem").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        }, onClickRow: function (rowIndex, rowData) {//单击行事件
              //显示所有处置计价显示
            $('#dlg').dialog('open').dialog('center').dialog('setTitle', '处置计价');
        }




});

    //回显
   /* setTimeout(function () {
        var old = '';
        var search = true;
        var query = [];
        //var $grid = $('#itemName');
       *//* var $grid = $('#itemName').combogrid('grid');	// 获取表格控件对象
        var r = g.datagrid('getSelected');	//获取表格当前选中行
        alert(r.itemName);//随便 点出行内各个字段属性值
        alert(11111111111);*//*
       *//* $grid.onChange = function (_new, _old) {
            alert(22222222222222);
            if (_new != old) {
                old = _new;
                query = [old];
                setTimeout(function () {
                    if (query.length > 0 && search) {
                        var param = query.pop();
                        query = [];
                        if (param != '') {
                            $grid.combogrid('grid').datagrid('load', {zjm: param});
                        }
                        loading = false;
                    }
                }, 500);
            }
        };*//*

      *//*  $grid.combogrid('grid').datagrid('options').onSelect = function(){
            return false;
        };*//*

        $('#itemName').combogrid('grid').datagrid('options').onClickRow = function(index, row) {
            var grid=$("#itemName").combogrid("grid");//获取表格对象
            var row = grid.datagrid('getSelected');//获取行数据
         *//*   $grid.combo('hidePanel');
            $grid.combo('setValue', row.aka061);
            $grid.combo('setText', row.aka061);*//*
            var selectRows = $('#clinicItem').datagrid("getSelected");
            selectRows.units=row.company;//单位
            selectRows.charges=row.guige;//实收
                $('#clinicItem').datagrid('updateRow', selectRows);
            setTimeout(function () {
                search = true;
            }, 1000);
        }
    }, 1000);*/








//处置计价
    $('#clinic').datagrid({
        singleSelect: true,
        fit: true,
        method:'GET',
        url: '/modules/clinic/clinicItem/js/datagrid_data2.json',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'itemClass', title: '类别', width: '20%', align: 'center', editor: 'text'},
            {field: 'xiangmuName', title: '计价项目', width: '20%', align: 'center'},
            {field: 'guige', title: '规格', width: '10%', align: 'center', editor: 'text'},
            {field:'number',title:'数量',width:'10%',align:'center',editor:'text'},
            {field: 'company', title: '单位', width: '10%', align: 'center', editor: 'text'},
            {field: 'netReceipts', title: '金额', width: '10%', align: 'center', editor: 'text'}
        ]]

    });

    //诊疗项目
    $('#class').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        method:'GET',
        url: '/modules/clinic/clinicItem/js/clinic_data.json',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'itemName', title: '项目名称', width: '20%', align: 'center', editor: 'text'},
            {field: 'itemCode', title: '项目代码', width: '20%', align: 'center'},
            {field: 'itemClass', title: '项目类型', width: '10%', align: 'center', editor: 'text'},
            {field:'inputCode',title:'拼音输入码',width:'10%',align:'center',editor:'text'},
            {field: 'inputCodeWb', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'},
            {field: 'orgId', title: '部门', width: '10%', align: 'center', editor: 'text'},
            {field: 'guige', title: '规格', width: '10%', align: 'center', editor: 'text'},
            {field: 'company', title: '单位', width: '10%', align: 'center', editor: 'text'}
        ]]



    });


});


//删除
function doDelete(){
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
            //真删除数据
            $.ajax({
                'type': 'POST',
                'url': basePath+'/treatment/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data==1){
                        $.messager.alert("提示消息",data+"条记录删除成功！");
                        $('#zhenduan').datagrid('load');
                        $('#zhenduan').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}


function save(){
    var  rows=$('#clinicItem').datagrid('getRows');
    var tableJson=JSON.stringify(rows);


    $.postJSON(basePath+'/treatment/save',tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#zhenduan').datagrid('load');
            $('#zhenduan').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}