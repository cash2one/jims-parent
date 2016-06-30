function onloadMethod(){

    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
        method:'GET',
        url:basePath+'/patList/getPatMasterByIn',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
        pagination:false,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            /*{field:'id',title:'病人ID号',width:'10%',align:'center'},*/
            {field:'inpNo',title:'病人住院号',width:'10%',align:'center'},
            {field:'name',title:'病人姓名',width:'10%',align:'center'},
            {field:'sex',title:'性别',width:'10%',align:'center'},
            {field:'bedNo',title:'床号',width:'10%',align:'center'},
        ]],
        onClickRow:function(rowIndex,rowData){
            var selectRows = $('#list_data').datagrid("getSelected");
            var clinicIndexId=  selectRows['id'];//病人主索引ID
        }
    });
   /* //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });*/
}
/**
 * 确认生成病历
 */
function confirmMr(){
    var selectRows = $('#list_data').datagrid("getSelected");
    if(selectRows==null ){
        alert("未选中病人");
        return false;
    }
    var patId=  selectRows['id'];//病人主索引ID
    $.ajax({
        'type': 'POST',
        'url': basePath+'/patList/confirmMoveIn',
        'contentType': 'application/json',
        'data': patId=patId,
        'dataType': 'json',
        'success': function(data){
            if(data.code=='1'){
                $.messager.alert("提示消息","移入成功");
                window.location.href="/modules/clinic/patientList.html";
            }else{
                $.messager.alert('提示',"移入失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"移入失败", "error");
        }
    });
}

