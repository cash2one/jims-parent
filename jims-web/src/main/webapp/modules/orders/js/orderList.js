$(function(){
    $('#orderList').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible:false,//是否可折叠的
        method:'get',
        url:basePath+'/inOrders/getOrders?'+$('#searchform').serialize(),
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
     /*   pagination:true,//分页控件*/
        rownumbers:true,//行号
        columns:[[      //每个列具体内容
            {field:'repeatIndicator',title:'长',width:'5%',align:'center'},
            {field:'orderClass',title:'类别',width:'5%',align:'center'},
            {field:'startDateTime',title:'下达时间',width:'10%',align:'center'},
            {field:'orderText',title:'医嘱内容',width:'10%',align:'center'},
            {field:'billingAttr',title:'自',width:'5%',align:'center'},
            {field:'dosage',title:'剂量',width:'5%',align:'center'},
            {field:'dosageUnits',title:'单位',width:'5%',align:'center'},
            {field:'administration',title:'途径',width:'5%',align:'center'},
            {field:'frequency',title:'频次',width:'5%',align:'center'},
            {field:'freqDetail',title:'执行时间',width:'10%',align:'center'},
          //  {field:'',title:'阴阳',width:'5%',align:'center'},
            {field:'stopDateTime',title:'结束时间',width:'10%',align:'center'},
          //  {field:'',title:'医生说明',width:'10%',align:'center'},
            {field:'doctor',title:'医生',width:'10%',align:'center'},
            {field:'freqCounter',title:'次数',width:'5%',align:'center'},
            {field:'stopDoctor',title:'停止医生',width:'5%',align:'center'},
            {field:'stopNurse',title:'停止校対护士',width:'5%',align:'center'}
        ]]
    });
    //设置分页控件
  /*  var p = $('#orderList').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });*/

    $("#submit_search").linkbutton({ iconCls: 'icon-search', plain: true }).click(function () {
            $('#orderList').datagrid("load");   //点击搜索
        });
});



