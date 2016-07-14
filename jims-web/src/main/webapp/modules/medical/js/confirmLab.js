var rowNum=-1;

function onloadMethod(inOrOut){
    $('#doctorRole').combobox({
        data: clinicDeptCode,
        valueField: 'id'
    })
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
       url:basePath+'/labConfirm/getLabMaster?inOrOut='+inOrOut,
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'status',title:'确认状态',width:'5%',align:'center',formatter:function(value, rowData, rowIndex){
                if (value == '0' || value=='') {
                    return "未确认";
                }
                if(value=='1'){
                    return "已确认";
                }
            }},
            {field:'testNo',title:'申请序号',width:'15%',align:'center'},
            {field:'name',title:'姓名',width:'10%',align:'center'},
            {field:'sex',title:'性别',width:'13%',align:'center'},
            {field:'executeDate',title:'执行日期',width:'10%',align:'center'},
            {field:'costs',title:'费用',width:'10%',align:'center'},
            {field:'charges',title:'应收费用',width:'20%',align:'center'},
            {field:'testCause',title:'检验目的',width:'15%',align:'center'},
            {field:'relevantClinicDiag',title:'临床诊断',width:'15%',align:'center'},
            {field:'specimen',title:'标本',width:'15%',align:'center'},
            {field:'notesForSpcm',title:'标本说明',width:'10%',align:'center'},
            {field:'spcmReceivedDateTime',title:'标本采样日期',width:'15%',align:'center'},
            {field:'spcmSampleDateTime',title:'标本收到日期',width:'15%',align:'center'},
            {field:'requestedDateTime',title:'申请日期',width:'10%',align:'center'},
            {field:'orderingDept',title:'申请科室',width:'10%',align:'center',formatter:clinicDeptCodeFormatter},
            {field:'orderingProvider',title:'申请医生',width:'10%',align:'center'},
            {field:'performedBy',title:'执行科室',width:'10%',align:'center'},
            {field:'resultStatus',title:'结果状态',width:'5%',align:'center'},

        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '确认',
            iconCls: 'icon-save',
            handler: function() {
                confirmLab();
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
/**
 * 条件查询
 */
function searchLab(){
   var startTime=$("#startTime").datebox('getValue');
   var endTime=$("#endTime").datebox('getValue');
   var reqDept=$("#doctorRole").combobox('getValue');
   var labNo=$("#labNo").val();
   var patName=$("#patName").val();
    $('#list_data').datagrid({queryParams:{"startTime":startTime,"endTime":endTime,"reqDept":reqDept,"labNo":labNo,"patName":patName}});
}
/**
 * 确认
 * @returns {boolean}
 */
function confirmLab(){
   var selectRow=  $('#list_data').datagrid('getSelected');
    var tableJson=JSON.stringify(selectRow);
   if(selectRow==null){
       $.messager.alert("提示消息","请选中要确认的检查");
       return false;
   }
    $.postJSON(basePath+'/labConfirm/confirmLab',tableJson,function(data){
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




