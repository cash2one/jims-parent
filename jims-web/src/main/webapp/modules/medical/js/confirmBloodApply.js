/**
 * Created by Administrator on 2016/7/7 0007.
 */
/**
 * 门诊手术确认
 * Created by pq on 2016/7/6 0006.
 */
$(function(){
    $('#confirmBlood').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        method:'GET',
        url:basePath+'/bloodConfirm/findBlood',
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'applyDate',title:'申请时间',width:'10%',formatter:formatDateBoxFull,align:'center'},
            {field:'patName',title:'姓名',width:'5%',align:'center'},
            {field:'patSex',title:'性别',width:'10%',formatter:sexFormatter,align:'center'},
            {field:'feeType',title:'费别',width:'10%',formatter:itemFormatter,align:'center'},
            {field:'bloodInuse',title:'用血方式',width:'10%',align:'center'},
            {field:'bloodDiagnose',title:'诊断及输血适应症',width:'10%',align:'center'},
            {field:'patBloodGroup',title:'受血者血型',width:'10%',formatter:bloodTypeFormatter,align:'center'},
            {field:'bloodSum',title:'输血总量',width:'10%',align:'center'},
            {field:'hematin',title:'血色素',width:'10%',align:'center'},
            {field:'platelet',title:'血小板',width:'10%',align:'center'},
            {field:'leucocyte',title:'白血球',width:'10%',align:'center'},
            {field:'gatherDate',title:'血库收到时间',width:'10%',formatter:formatDateBoxFull,align:'center'},
            {field:'applyStatus',hidden:"true"}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{

                text: '确认',
                iconCls: 'icon-add',
                handler: function(){
                    confirmBlood();
                }
            }
        ],
        rowStyler:function(index,row) {
            if (row.applyStatus!=null ) {
                if (row.applyStatus == '0'|| row.applyStatus == 0) {//未确认
                    return 'color:red;';
                } else if (row.applyStatus == '1' || row.applyStatus == 1) {//已确认
                    return 'color:blue;';
                }
            }
        }
    });
});
/*    //设置分页控件
    var p = $('#confirmBlood').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });*/

//手术安排确认
function confirmBlood(){
    var rows = $('#confirmBlood').datagrid("getSelections");
    var tableJson=JSON.stringify(rows);
    $.postJSON(basePath+'/bloodConfirm/confirmBlood',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","确认成功");
            $('#confirmBlood').datagrid('load');
        }else{
            $.messager.alert('提示',"确认失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"确认失败", "error");
    })
}


function search(){
   // $('#confirmBlood').datagrid({url:basePath+'/operationConfirm/findOperation?scheduledDateTime='+$("#scheduledDateTime").datebox('getValue') });
}

