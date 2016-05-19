
function onloadMethod(){
    var editRow =undefined;
    var rowNum=-1;
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: '100%',
        height: '90%',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
         url:basePath+'/clinicSchedule/findList',
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicLabel',title:'门诊号名称',width:'20%',align:'center'},
            {field:'timeDesc',title:'时间',width:'25%',align:'center'},
            {field:'dayOfWeek',title:'星期',width:'20%',align:'center'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');


    $('#list_data_num').datagrid({
        iconCls:'icon-edit',//图标
        width: '100%',
        height: '90%',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
         url:basePath+'/clinicRegister/findList',
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicDate',title:'出诊日期',width:'20%',align:'center'},
            {field:'clinicLabel',title:'门诊号名称',width:'25%',align:'center'},
            {field:'timeDesc',title:'出诊时间',width:'20%',align:'center'},
            {field:'registrationLimits',title:'限号数',width:'20%',align:'center'},
            {field:'currentNo',title:'当前号',width:'20%',align:'center'},
            {field:'appointmentLimits',title:'限预约号数',width:'20%',align:'center'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar:'#time',onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        }, onClickRow: function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_data_num").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_data_num").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
            var dataGrid=$('#list_data_num');
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
    var p = $('#list_data_num').datagrid('getPager');
}
//添加行
function addRowCol(){
    var selectRows = $('#list_data').datagrid("getSelections");
    var jsonData=JSON.stringify(selectRows);
    alert(jsonData);
    var startTime=$('#startTime').datebox('getValue');
    var endTime=$("#endTime").datebox('getValue');
    if(startTime==null ||startTime=='' ||  endTime==null || endTime==''){
        $.messager.alert("提示消息", "请选择门诊号表生成日期!");
        return;
    }
    $.postJSON(basePath+'/clinicRegister/saveRegister?startTime='+startTime+'&endTime='+endTime,jsonData,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#list_data_num').datagrid('load');//加载号表列表
            $('#list_data').datagrid('clearChecked');//取消选中的号别
            $("#startTime").datebox('setValue','');//清空门诊号表生成日期
            $("#endTime").datebox('setValue','');//清空门诊号表生成日期
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
//删除已经生成的号表信息
function deleteForReg(){
    var selectRows = $('#list_data_num').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
                alert(selectRows[i].id );
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //删除
            $.ajax({
                'type': 'POST',
                'url': basePath+'/clinicRegister/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#list_data_num').datagrid('load');
                        $('#list_data_num').datagrid('clearChecked');
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
