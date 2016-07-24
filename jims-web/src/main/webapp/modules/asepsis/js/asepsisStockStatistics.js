
$(function() {
    var orgId = config.org_Id;
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
        textField:'asepsisName' ,
        method:'GET'
    });

    $('#list_data').datagrid({
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
            {field:'fromDept',title:'所属科室',width:'11%',align:'center'}
            ,{field:'documentNo',title:'物品单号',width:'10%',align:'center'}
            //,{field:'itemNo',title:'序号',width:'50',align:'center'}
            ,{field:'itemCode',title:'包代码',width:'10%',align:'center'}
            ,{field:'itemName',title:'包名称',width:'15%',align:'center'}
            ,{field:'itemSpec',title:'规格',width:'10%',align:'center'}
            ,{field:'units',title:'单位',width:'10%',align:'center'}
            ,{field:'amount',title:'数量',width:'10%',align:'center'}
            ,{field:'antiDate',title:'消毒日期',width:'10%',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
            ,{field: 'memos', title: '备注', width: '10%', align: 'center'}
        ]]
    });

    loadAnotherData = function(){
        var a = $("#fromDept").combobox("getData");
        var listAll = $('#list_data').datagrid('getRows');
        $.each(a,function(index,row){
            $.each(listAll, function(i, r){
                if(row.deptCode == r.fromDept){
                    r.fromDept = row.deptName;
                    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                }
            });
        });

        $.get(basePath + '/orgSysDict/findUnits?orgId=' + orgId + '&type=PACKAGE_UNITS', function (data) {
            $.each(data,function(index,row){
                $.each(listAll, function(i, r){
                    if(row.value == r.units){
                        r.units = row.label;
                        $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                    }
                });
            });
        });
    }
    var loadListData = function (flag) {
        if(flag=='1'){
            var exchangeStart = parent.parseToDate($('#exchangeStart').datebox('getValue'));
            var exchangeEnd = parent.parseToDate($('#exchangeEnd').datebox('getValue'));
            var fromDept = $("#fromDept").combobox('getValue');
            var itemCode = $("#itemName").combobox('getValue');
            $("#list_data").datagrid({url:basePath+'/asepsisStock/findList',queryParams:{"orgId":orgId,"antiDateStart":exchangeStart,"antiDateEnd":exchangeEnd,"fromDept":fromDept,"itemCode":itemCode}});
        }else{
            $("#list_data").datagrid({url:basePath+'/asepsisStock/findList',queryParams:{"orgId":orgId}});
        }
        setTimeout("loadAnotherData()",1000);
    }
    loadListData();
    $("#searchBtn").on("click",function(){
        loadListData('1');
    });

    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5, 10, 15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });




















})
