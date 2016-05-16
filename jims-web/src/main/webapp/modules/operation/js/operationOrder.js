var editRow = undefined;
$(function(){

    //病人列表
    $('#patient').datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath+'/operatioinOrder/findPat',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '50%', align: 'center'},
            {field: 'name', title: '姓名', width: '50%'},
        ]]
    });
});

//增加手术名称
$(function(){
    $('#operationName').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        method:'GET',
        url: basePath+'/treatment/findList',
        idField: 'id',
        columns: [[      //每个列具体内容
            {field: 'operation', title: '拟实施手术名称', width: '70%', align: 'center', editor:{
                type:'combogrid',
                options: {
                    panelWidth: 500,
                    idField: 'itemCode',
                    textField: 'itemName',
                    url: '/modules/operation/js/clinic_data.json',
                    columns: [[
                        {field: 'itemCode', title: '项目代码', width: '20%', align: 'center'},
                        {field: 'itemName', title: '项目名称', width: '20%', align: 'center'},
                        {field: 'inputCode', title: '拼音输入码', width: '10%', align: 'center', editor: 'text'},
                        {field: 'inputCodeWb', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'}
                    ]],
                    fitColumns: true
                }
                  }
            },
            {field: 'operationScale', title: '等级', width: '20%', align: 'center'}
        ]],/*,onLoadSuccess:function(){//隐藏手术主表的Id
            $('#operationName').datagrid('hideColumn','scheduleId');
        },*/
    toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                $("#operationName").datagrid('endEdit', editRow);
                if (editRow != undefined) {
                    $("#operationName").datagrid("endEdit", editRow);
                }
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (editRow == undefined) {
                    $("#operationName").datagrid("insertRow", {
                        index: 0, // index start with 0
                        row: {}
                    });
                    //将新插入的那一行开户编辑状态
                    $("#operationName").datagrid("beginEdit", 0);
                    //给当前编辑的行赋值
                    editRow = 0;
                }
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }
        ]
    });
});



function save(){
    $("#operationName").datagrid('endEdit', editRow);
    var  rows=$('#operationName').datagrid('getRows');
    var formJson=fromJson('operation');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"scheduledOperationNameList\":"+tableJson+"}";
    $.postJSON(basePath+'/operatioinOrder/save',submitJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
            $('#operationName').datagrid('load');
            $('#operationName').datagrid('clearChecked');
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })


}