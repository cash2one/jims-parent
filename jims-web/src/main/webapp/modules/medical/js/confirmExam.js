var rowNum=-1;

function onloadMethod(outOrIn){
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
        url:basePath+'/examConfirm/getExamAppoints?outOrIn='+outOrIn,
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'regPrnFlag',title:'确认状态',width:'5%',align:'center',formatter:function(value, rowData, rowIndex){
                if (value == null || value=='') {
                    return "未确认";
                }
                if(value=='1'){
                    return "已确认";
                }
            }},
            {field:'examSubClass',title:'检查子类',width:'15%',align:'center'},
            {field:'reqDept',title:'申请科室',width:'10%',align:'center'},
            /* {field:'clinicDept',title:'病人ID',width:'15%',align:'center'},*/
            {field:'name',title:'姓名',width:'13%',align:'center'},
            {field:'sex',title:'性别',width:'10%',align:'center'},
            {field:'clinicType',title:'检查项目',width:'15%',align:'center'},
            {field:'reqDateTime',title:'申请时间',width:'15%',align:'center'},
            {field:'scheduledDate',title:'预约时间',width:'15%',align:'center'},
            /*  {field:'serialNo',title:'放射科号',width:'5%',align:'center',editor: 'text'},*//*字段未确定*/
            {field:'examGroup',title:'检查组',width:'5%',align:'center'},
            {field:'patientSource',title:'来源',width:'5%',align:'center'},
            {field:'costs',title:'检查费用',width:'5%',align:'center'},
            {field:'charges',title:'应收费用',width:'5%',align:'center'},
            {field:'reqMemo',title:'备注',width:'10%',align:'center',editor: 'text'},
            {field:'examNo',title:'申请序号',width:'10%',align:'center'},
            {field:'examMode',title:'检查方式',width:'5%',align:'center'},
            {field:'reqPhysician',title:'申请医生',width:'10%',align:'center'},
            {field:'clinDiag',title:'临床诊断（印象）',width:'20%',align:'center'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '确认',
            iconCls: 'icon-save',
            handler: function() {
                confirmExam();
            }
        }
        ], onClickRow: function (rowIndex, rowData) {
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
/**
 * 查询
 */
function searchAppoints(){
    var startTime= $("#startTime").datebox('getValue');
    var startTime= $("#endTime").datebox('getValue');

}
/**
 * 确认
 * @returns {boolean}
 */
function confirmExam(){
   var selectRow=  $('#list_data').datagrid('getSelected');
    var tableJson=JSON.stringify(selectRow);
   if(selectRow==null){
       $.messager.alert("提示消息","请选中要确认的检查");
       return false;
   }
    $.postJSON(basePath+'/examConfirm/confirmExam',tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息","确认成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        }else if(data.code=='2'){
            $.messager.alert("提示消息","该检查已经确认");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        }
        else{
            $.messager.alert('提示',"确认失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");

    });
}




