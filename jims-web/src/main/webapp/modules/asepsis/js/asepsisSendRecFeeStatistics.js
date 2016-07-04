
$(function() {
    var orgId = 1;
    var fp = 0;
    $('#exchangeStart').datebox({
        height:'25',
        value: parent.formatDatebox(new Date())
    });
    $('#exchangeEnd').datebox({
        height:'25',
        value: parent.formatDatebox(new Date())
    });
    //获取所属科室
    $("#fromDept").combobox({
        url:basePath + '/dept-dict/list?orgId='+orgId,
        valueField:'deptCode',
        textField:'deptName' ,
        method:'GET'
    });
    //获取包名称
    $("#itemName").combobox({
        url:basePath + '/asepsisDict/findList?orgId='+orgId,
        valueField:'asepsisCode',
        textField:'itemName' ,
        method:'GET'
    });
    //获取单据号

    $('#manager0').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '76%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method:'GET',
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field:'fromDept',title:'所属科室',width:'120',align:'center'}
            ,{field:'documentNo',title:'单据号',width:'90',align:'center'}
            ,{field:'itemCode',title:'包代码',width:'90',align:'center'}
            ,{field:'itemName',title:'包名称',width:'150',align:'center'}
            ,{field:'itemSpec',title:'规格',width:'70',align:'center'}
            ,{field:'units',title:'单位',width:'70',align:'center'}
            ,{field:'sendAmount',title:'数量',width:'70',align:'center'}
            ,{field: 'antiFee',title:'消毒单价',width:'80',align:'center'}
            ,{field: 'antiFeeSum',title:'消毒费合计',width:'100',align:'center'}
            ,{field: 'nobackFee',title:'辅料费',width:'100',align:'center'}
            ,{field: 'sendDate', title: '送/借时间', width: '10%', align: 'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
        ]]
    });
    $('#manager1').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '76%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method:'GET',
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field:'fromDept',title:'所属科室',width:'200',align:'center'}
            ,{field: 'antiFeeSum',title:'消毒费合计',width:'200',align:'center'}
            ,{field: 'nobackFee',title:'辅料费合计',width:'200',align:'center'}
            ,{field: 'flag', title: '日期', width: '200', align: 'center'}
        ]]
    });
    $('#manager2').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '76%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method:'GET',
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field:'fromDept',title:'所属科室',width:'200',align:'center'}
            ,{field: 'flag', title: '日期', width: '200', align: 'center'}
            ,{field: 'antiFeeSum',title:'消毒费合计',width:'200',align:'center'}
            ,{field: 'nobackFee',title:'辅料费合计',width:'200',align:'center'}
        ]]
    });
    $('#manager3').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '76%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method:'GET',
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true,//分页控件
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field:'fromDept',title:'所属科室',width:'200',align:'center'}
            ,{field: 'flag', title: '月份', width: '200', align: 'center'}
            ,{field: 'antiFeeSum',title:'消毒费合计',width:'200',align:'center'}
            ,{field: 'nobackFee',title:'辅料费合计',width:'200',align:'center'}
        ]]
    });
    $('#tabs').tabs({
        fit:true,
        justified:true,
        onSelect: function(title,index){
            fp = index;
            $("#manager"+index).datagrid({url:basePath+'/asepsisSendRec/findListFee',queryParams:{"orgId":orgId,"flag":index}});
            setTimeout("loadAnotherData()",500);
        }
    })
    var loadData = function (flag) {
        if(flag=='1'){
            var exchangeStart = parent.parseToDate($('#exchangeStart').datebox('getValue'));
            var exchangeEnd = parent.parseToDate($('#exchangeEnd').datebox('getValue'));
            var fromDept = $("#fromDept").combobox('getValue');
            var itemName = $("#itemName").combobox('getValue');
            //var documentNo = $("#documentNo").combobox('getValue');
            var documentNo = '';
            $("#manager"+fp).datagrid({url:basePath+'/asepsisSendRec/findListFee',queryParams:{"orgId":orgId,"flag":fp,"sendDateStart":exchangeStart,"sendDateEnd":exchangeEnd,"fromDept":fromDept,"itemName":itemName,"documentNo":documentNo}});
        }else{
            $("#manager"+fp).datagrid({url:basePath+'/asepsisSendRec/findListFee',queryParams:{"orgId":orgId,"flag":fp}});
        }
        setTimeout("loadAnotherData()",1200);
    }
    //loadData();
    loadAnotherData = function(){
        var a = $("#fromDept").combobox("getData");
        var listAll = $('#manager'+fp).datagrid('getRows');
        $.each(a,function(index,row){
            $.each(listAll, function(i, r){
                if(row.deptCode == r.fromDept){
                    r.fromDept = row.deptName;
                    $('#manager'+fp).datagrid("refreshRow",$('#manager'+fp).datagrid("getRowIndex",r))
                }
            });
        });
        var index = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        if(index==0){
            $.get(basePath + '/orgSysDict/findUnits?orgId=' + orgId + '&type=PACKAGE_UNITS', function (data) {
                $.each(data,function(index,row){
                    $.each(listAll, function(i, r){
                        if(row.value == r.units){
                            r.units = row.label;
                            $('#manager'+fp).datagrid("refreshRow",$('#manager'+fp).datagrid("getRowIndex",r))
                        }
                    });
                });
            });
        }
    }
    $("#searchBtn").on("click",function(){
        //var index = $('#tabs').tabs('getTabIndex',$('#tabs').tabs('getSelected'));
        loadData('1');
    });

    //设置分页控件
    var p = $('#manager'+fp).datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5, 10, 15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });




















})
