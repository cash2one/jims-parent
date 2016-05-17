var rowNum=-1;
var editRow = undefined;
var clinicDeptCode=[{"value":"1","text":"内科"},{"value":"2","text":"内一科"},{"value":"3","text":"外科"},{"value":"4","text":"妇科"}];
var doctorName=[{"value":"1","text":"石佳慧"},{"value":"2","text":"张家辉"},{"value":"3","text":"李长青"},{"value":"4","text":"李惠利"},
    {"value":"5","text":"赵丽娟"}];
var doctorJob=[{"value":"1","text":"主治医师"},{"value":"2","text":"主任医师"},{"value":"3","text":"副主任医师"}];
function onloadMethod(){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/clinicIndex/findList',
        remoteSort:false,
        idField:'id',
        singleSelect: true,
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicLabel',title:'门诊名称',width:'15%',align:'center',editor: 'text'},
            {field:'inputCode',title:'输入码',width:'10%',align:'center',editor: 'text'},
            {field:'clinicDept',title:'门诊科室',width:'15%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: clinicDeptCode,
                    valueField: 'value',
                    textField: 'text'
                }
            }},
            {field:'doctor',title:'医师',width:'13%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: doctorName,
                    valueField: 'value',
                    textField: 'text'
                }
            }},
            {field:'doctorTitle',title:'医师职称',width:'10%',align:'center',editor: {
                type: 'combobox',
                options: {
                    data: doctorJob,
                    valueField: 'value',
                    textField: 'text'
                }
            }},
            {field:'clinicType',title:'号类',width:'15%',align:'center',editor: {
                type: 'combobox',
                options: {
                    required: true,
                    url:basePath+'/clinicType/findList' ,
                    valueField: 'id',
                    textField: 'clinicTypeName',
                    method:'get',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].id);
                    }

                }
            }},
            {field:'clinicPosition',title:'门诊位置',width:'15%',align:'center',editor: 'text'},
            {field:'serialNo',title:'号别序号',width:'5%',align:'center',editor: 'text'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
                $("#list_data").datagrid('insertRow', {
                    index: 0,
                    row: {}
                });
            }
        },
            '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                deleteClinicIndex();
            }
        },{
                text: '保存',
                iconCls:'icon-save',
                handler:function(){
                    $("#list_data").datagrid('endEdit', editRow);
                    if (editRow != undefined) {
                        $("#list_data").datagrid("endEdit", editRow);
                    }
                    save();
                }
            }
        ],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        }, onClickRow: function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_data").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }
            if(rowNum!=rowIndex){
                if(rowNum>=0){
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum=rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);
            }
        }
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}
function save(){
    var  rows=$('#list_data').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    alert(tableJson);
    $.postJSON(basePath+'/clinicIndex/saveClinicIndex',tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}

//删除号别
function deleteClinicIndex(){
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //删除
            $.ajax({
                'type': 'POST',
                'url': basePath+'/clinicIndex/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#list_data').datagrid('load');
                        $('#list_data').datagrid('clearChecked');
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



