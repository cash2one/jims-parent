var rowNum=-1;
$(function (){
    var editRow = undefined; //定义全局变量：当前编辑的行

    $('#confirm_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
       // pagination:true,//分页控件
       // pageSize:15,
        //pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'visitDateAppted',title:'就诊日期',align:'center'},
            {field:'visitTimeAppted',title:'就诊时间',align:'center'},
            {field:'clinicLabel',title:'门诊号别',align:'center'},
            {field:'doctor',title:'医生',align:'center'},
            {field:'name',title:'姓名',align:'center',editor: 'text'},
            {field:'cardNo',title:'预约卡号',align:'center',editor: 'text'},
            {field:'cardName',title:'预约卡类',align:'center',editor: 'text'},
            {field:'clinicType',title:'门诊类别',align:'center'},
            {field:'apptMadeDate',title:'预约时间',align:'center',editor: 'text'},
            {field:'modeCode',title:'预约方式',align:'center',editor: 'text'},
            {field:'masterId',hidden:true},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:false}
        ]],
        toolbar: [
            {
                text: '确认',
                iconCls: 'icon-add',
                handler: function() {
                    confirmAppoint('chargeId','预约结果');
                }
            },
            {
                text: '保存',
                iconCls: 'icon-save',
                handler: function() {
                    saveAppointInfo();
                }
            },
            '-',{
                text: '退约',
                iconCls: 'icon-remove',
                handler: function(){
                    deleteAppoint();
                }
            }
        ], onClickRow: function (rowIndex, rowData) {
            var selectRows = $('#confirm_data').datagrid("getSelected");
            var appointId=  selectRows['id'];//号别ID
            indexInfo(appointId);
            var dataGrid=$('#confirm_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }else{
                if(rowNum!=rowIndex){
                    if(rowNum>=0){
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum=rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }
        }
    });

    //设置分页控件
   /* var p = $('#confirm_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });*/
});
//查询预约的list数据
function searchConfirm(){
    var clinicNo=$("#clinicNo").val();
    var name=$("#name").val();
    var cardNo=$("#cardNo").val();
    var visitDate=$("#visitDate").datebox("getValue");
   $("#confirm_data").datagrid({url:basePath+'/clinicAppoints/searchAppoints',queryParams:{"clinicNo":clinicNo,"name":name,"cardNo":cardNo,"visitDate":visitDate}});
}
//查询右边基本信息
function indexInfo(id){
    $.get(basePath+'/clinicAppoints/get?id='+id,function(data){
      $("#patIndex").form('load',data);
    });
}
//弹出 收费 确认信息
function confirmAppoint(id,name){
    $("#"+id).dialog({title: name}).dialog("open");
    var selectRows = $('#confirm_data').datagrid("getChecked");
    //得要判断 就诊日期和当前日期是否是一天
    var labelHtml="";
        for(var i=0;i<selectRows.length;i++){
            labelHtml+='<tbody>' +
            '<tr>' +
            ' <td name="clinicLabel" class="easyui-validatebox">'+selectRows[i].clinicLabel+'</td>' +
            '</tr>' +
            '</tbody>';
        }

    $("#clinicLabe").html(labelHtml);
}
//保存
function saveAppointReg(){
    var rows = $('#confirm_data').datagrid("getSelections");
    var tableJson=JSON.stringify(rows);
    var formJson=fromJson('patIndex');
    formJson = formJson.substring(0, formJson.length - 1);
    var submitJson=formJson+",\"clinicAppointses\":"+tableJson+"}";
    //alert(submitJson);
    $.postJSON(basePath+"/clinicAppoints/saveAppointReg",submitJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","确认成功");
            $("#"+'chargeId').dialog({title: '预约结果'}).dialog("close");
            $('#confirm_data').datagrid('load');
            $('#confirm_data').datagrid('clearChecked');
            $("#patIndex").form('clear');
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","errorzn");
    }
}


//退约
    function deleteAppoint() {
        var selectRows = $('#confirm_data').datagrid("getSelections");
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
                alert(strIds);
                //删除
                $.ajax({
                    'type': 'POST',
                    'url': basePath + '/clinicAppoints/deleteAppoints',
                    'contentType': 'application/json',
                    'data': id = strIds,
                    'dataType': 'json',
                    'success': function (data) {
                        if (data.code == '1') {
                            $.messager.alert("提示消息", data.code + "条记录删除成功！");
                            $('#confirm_data').datagrid('load');
                            $('#confirm_data').datagrid('clearChecked');
                        } else {
                            $.messager.alert('提示', "删除失败", "error");
                        }
                    },
                    'error': function (data) {
                        $.messager.alert('提示', "删除失败", "error");
                    }
                });
            }
        })
    }
//保存 修改预约信息
function saveAppointInfo(){
    $('#confirm_data').datagrid('endEdit', rowNum);
    var clinicMasterId=$("#clinicMasterId").val();
    var rows = $('#confirm_data').datagrid("getSelected");
    rows.masterId=clinicMasterId;
    var tableJson=JSON.stringify(rows);
    alert(tableJson);
    $.postJSON(basePath+"/clinicAppoints/editAppoints",tableJson,function(data){
        if(data.code=="1"){
            $.messager.alert("提示信息","保存成功");
            $('#confirm_data').datagrid('load');
            $('#confirm_data').datagrid('clearChecked');
            $("#patIndex").form('clear');
        }else{
            $.messager.alert("提示信息","保存失败","error");
        }

    }),function(data){
        $.messager.alert("提示信息","保存失败","errorzn");
    }
}