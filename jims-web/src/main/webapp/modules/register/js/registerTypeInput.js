var rowNum=-1;

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
            {field:'clinicLabel',title:'号别名称',width:'15%',align:'center',editor: 'text'},
            {field:'inputCode',title:'输入码',width:'10%',align:'center',editor: 'text'},
            {field:'clinicDept',title:'门诊科室',width:'15%',align:'center',formatter:clinicDeptCodeFormatter,editor: {
                type: 'combobox',
                options: {
                    data: clinicDeptCode,
                    valueField: 'id',
                    textField: 'dept_name'
                }
            }},
            {field:'doctor',title:'医师',width:'13%',align:'center',formatter:doctorNameFormatter,editor: {
                type: 'combogrid',
                options: {
                    data: doctorName,
                    idField:'id',
                    textField:'name',
                    columns:[[
                        {field:'name',title:'医生姓名',width:70},
                        {field:'dept_name',title:'科室',width:120},
                        {field:'title',title:'职称',width:70}
                    ]],keyHandler: {
                        up: function() {},
                        down: function() {},
                        enter: function() {},
                        query: function(q) {
                            dataGridCompleting(q,'list_data','doctor');
                        }
                    },
                    onClickRow: function (index, data) {
                        var rows = $('#list_data').datagrid("getRows"); // 这段代码是// 对某个单元格赋值
                        var columns = $('#list_data').datagrid("options").columns;
                        rows[rowNum][columns[0][4].field]=data.title;
                        $('#list_data').datagrid('endEdit', rowNum);
                        $('#list_data').datagrid('beginEdit', rowNum);
                    }
                }
            }},
            {field:'doctorTitle',title:'医师职称',width:'10%',align:'center'
               },
            {field:'clinicType',title:'号类',width:'15%',align:'center',formatter:registerTypFormatter,editor: {
                type: 'combobox',
                options: {
                    data:registerTyp,
                    valueField: 'id',
                    textField: 'clinicTypeName'
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
                    $("#list_data").datagrid('endEdit', rowNum);
                    save();
                }
            }
        ], onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }else{
                if(rowNum!=rowIndex){
                    if(rowNum>=0){
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum=rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                    var ed = $('#list_data').datagrid('getEditor', {index:rowIndex,field:'doctor'});
                    $(ed.target).combogrid("grid").datagrid("loadData", doctorName);
                }
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
    //alert(tableJson);
    $.postJSON(basePath+'/clinicIndex/saveClinicIndex',tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息","保存成功");
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



