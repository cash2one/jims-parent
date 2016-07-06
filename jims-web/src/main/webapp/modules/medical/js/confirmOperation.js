/**
 * 门诊手术确认
 * Created by pq on 2016/7/6 0006.
 */

$(function(){
    $('#confirmOperation').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        method:'GET',
        url:basePath+'/operationConfirm/findOperation',
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'scheduled_date_time',title:'时间',width:'10%',formatter:formatDateBoxFull,align:'center'},
            {field:'operating_room_no',title:'手术间',width:'5%',align:'center'},
            {field:'sequence',title:'台次',width:'10%',align:'center'},
            {field:'name',title:'急症',width:'10%',align:'center'},
            {field:'anesthesia_method',title:'麻醉方法',width:'10%',align:'center'},
            {field:'dept_stayed',title:'病室',width:'10%',align:'center'},
            {field:'anesthesia_doctor',title:'麻醉医师',width:'10%',align:'center'},
            {field:'firstOperationNurse',title:'洗手护士',width:'10%',align:'center'},
            {field:'secondOperationNurse',title:'巡回护士',width:'10%',align:'center'},
            {field:'name',title:'姓名',width:'10%',align:'center'},
            {field:'sex',title:'性别',width:'10%',align:'center'},
            {field:'age',title:'年龄',width:'10%',align:'center'},
            {field:'inp_no',title:'住院号',width:'10%',align:'center'},
            {field:'bed_no',title:'床号',width:'10%',align:'center'},
            {field:'diag_before_operation',title:'诊断名称',width:'10%',align:'center'},
            {field:'operation',title:'手术名称',width:'10%',align:'center'},
            {field:'doctor_user',title:'手术医师',width:'10%',align:'center'},
            {field:'operating_dept',title:'科室',width:'10%',align:'center'},
            {field:'first_assistant',title:'助手',width:'10%',align:'center'},
            {field:'blood_tran_doctor',title:'输血医师',width:'10%',align:'center'},
            {field:'remarks',title:'备注',width:'10%',align:'center'},
            {field:'ackIndicator',hidden:"true"}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
         rowStyler:function(index,row) {
             if (row.ackIndicator!=null ) {
                 if (row.ackIndicator == '0'|| row.ackIndicator == 0) {//未确认
                     return 'color:red;';
                 } else if (row.ackIndicator == '1' || row.ackIndicator == 1) {//已确认
                     return 'color:blue;';
                 }
             }
         }
});
});

//手术安排确认
function confirmOperation(){
   var rows = $('#confirmOperation').datagrid("getSelections");
    var tableJson=JSON.stringify(rows);
    $.postJSON(basePath+'/operationConfirm/confirm',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","确认成功");
            $('#confirmOperation').datagrid('load');
        }else{
            $.messager.alert('提示',"确认失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"确认失败", "error");
    })
}