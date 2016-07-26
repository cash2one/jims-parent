$(function(){

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
        url:basePath+'/prepaymentRcpt/recordList',
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'operator_no',title:'收款员',width:'10%',align:'center'},
            {field:'rcpt_no',title:'编号',width:'15%',align:'center'},
            {field:'name',title:'姓名',width:'15%',align:'center'},
            {field:'amount',title:'金额',width:'15%',align:'center'},
            {field:'pay_way',title:'支付方式',width:'15%',align:'center',formatter:payWayFormatter},
            {field:'transact_type',title:'类型',width:'15%',align:'center'},
            {field:'transact_date',title:'时间',width:'15%',align:'center'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

});

//条件查询
function searchClinicIndex(){
    var transactType=$("#transactType").val();
    var endDate=$("#endDate").val();
    var startDate=$("#startDate").val();
    $("#list_data").datagrid('reload',{"transactType":transactType,"endDate":endDate,"startDate":startDate});
}


