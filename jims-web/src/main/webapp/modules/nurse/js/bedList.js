


var editRow = undefined;
var rowNum=-1;
$(function(){
    var wardCode='160101';
    $('#bedRec').datagrid({
        view: myview,
        emptyMsg: '没有查到相关列表',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/findBedInfo?wardCode='+wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true, //分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    url: basePath+'/dict/label-value-list?type='+"SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                        //$(this).combobox('setValues', row.bedSexType);
                    }
            }
        }},
            {field: 'bedClass', title: '等级', width: '20%', align: 'center',editor:{
                type:'combobox',
                options: {
                    required: true,
                    url: basePath + '/dict/label-value-list?type=' + "SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                       // $(this).combobox('setValues', row.bedClass);
                    }
                }
                }},
            {field: 'airconditionClass', title: '空调等级', width: '15%', align: 'center',editor:{
                type:'combobox',
                options: {
                    required: true,
                    url: basePath + '/dict/label-value-list?type=' + "SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                      //  $(this).combobox('setValues', row.airconditionClass);
                    }
                }
            }},
            {field: 'bedApprovedType', title: '类型', width: '15%', align: 'center',editor:{
                type:'combobox',
                options: {
                    required: true,
                    url: basePath + '/dict/label-value-list?type=' + "SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                     //   $(this).combobox('setValues', row.bedApprovedType);
                    }
                }
            }},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center',editor:{
                type:'combobox',
                options: {
                    required: true,
                    url: basePath + '/dict/label-value-list?type=' + "BED_STATUS_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                    //    $(this).combobox('setValues', row.bedStatus);
                    }
                }
            }}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                if (rowNum >= 0) {
                    rowNum++;
                }
                $("#bedRec").datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {

                    }
                });

            }
        },{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#bedRec").datagrid('endEdit', rowNum);
                if (rowNum != -1) {
                    $("#bedRec").datagrid("endEdit", rowNum);
                }
                save();
            }
        },{
            text: '换床',
            iconCls:'icon-reload',
            handler:function(){
                $("#bedRec").datagrid('endEdit', rowNum);
                if (rowNum != -1) {
                    $("#bedRec").datagrid("endEdit", rowNum);
                }
                save();
            }
        },
            {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete();
                }
            }],onAfterEdit: function (rowIndex, rowData, changes) {

        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#bedRec").datagrid('endEdit', rowNum);
            }
            if (editRow == undefined) {
                $("#bedRec").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData) {
            var dataGrid = $('#bedRec');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false
            }
            if (rowNum != rowIndex) {
                if (rowNum >= 0) {
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum = rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);

            }
        },onLoadSuccess:function(data){

        }



    });


    //设置分页控件
    var p = $('#bedRec').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

});