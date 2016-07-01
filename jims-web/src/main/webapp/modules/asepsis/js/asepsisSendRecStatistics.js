
$(function() {
    var orgId = 1;
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
    //获取送物人
    $("#sender").combobox({
        url:basePath + '/staffDict/listStaffDict',
        valueField:'empNo',
        textField:'name' ,
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

            {field:'fromDept',title:'送物科室',width:'100',align:'center'}
            ,{field:'documentNo',title:'单据号',width:'80',align:'center'}
            ,{field:'itemNo',title:'序号',width:'50',align:'center'}
            ,{field:'itemCode',title:'包代码',width:'80',align:'center'}
            ,{field:'itemName',title:'包名称',width:'150',align:'center'}
            ,{field:'itemSpec',title:'规格',width:'50',align:'center'}
            ,{field:'units',title:'单位',width:'50',align:'center'}
            ,{field:'sendAmount',title:'送物数量',width:'60',align:'center'}
            ,{field:'getAmount',title:'已领数量',width:'60',align:'center'}
            ,{field:'sendDate',title:'送物日期',width:'80',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
            ,{field:'getDate',title:'领物日期',width:'80',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }}
            ,{field:'stock',title:'可领数量',width:'60',align:'center'}
            ,{field: 'getFlag',title:'物品状态',width:'7%',align:'center',formatter: function(value,row ){
                if(row.getFlag=='0'){row.getFlag = "申请";}
                else if(row.getFlag=='1'){row.getFlag = "未领";}
                else if(row.getFlag=='2'){row.getFlag = "已消毒";}
                else if(row.getFlag=='3'){row.getFlag = "部分领取";}
                else if(row.getFlag=='4'){row.getFlag = "全部领取";}
                else{row.getFlag = row.getFlag;}
                return row.getFlag; }
            }
            ,{field: 'sender',title:'送物人',width:'6%',align:'center'}
            ,{field: 'antiFee',title:'消毒单价',width:'6%',align:'center'}
            ,{field: 'antiFeeSum',title:'消毒费合计',width:'7%',align:'center'}
            ,{field: 'nobackFee',title:'辅料费',width:'7%',align:'center'}
            ,{field: 'memos', title: '备注', width: '10%', align: 'center'}
        ]]
    });

    loadAnotherData = function(){
        var a = $("#fromDept").combobox("getData");
        var b = $("#sender").combobox("getData");
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
        $.each(b,function(index,row){     //empNo,name
            $.each(listAll, function(i, r){
                if(row.empNo == r.sender){
                    r.sender = row.name;
                    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                }
            });
        });
    }
    var loadListData = function (flag) {
        if(flag=='1'){
            var exchangeStart = parent.parseToDate($('#exchangeStart').datebox('getValue'));
            var exchangeEnd = parent.parseToDate($('#exchangeEnd').datebox('getValue'));
            var fromDept = $("#fromDept").combobox('getValue');
            var sender = $("#sender").combobox('getValue');
            var getFlag = $("#getFlag").combobox('getValue');
            $("#list_data").datagrid({url:basePath+'/asepsisSendRec/findList',queryParams:{"orgId":orgId,"sendDateStart":exchangeStart,"sendDateEnd":exchangeEnd,"fromDept":fromDept,"sender":sender,"getFlag":getFlag}});
        }else{
            $("#list_data").datagrid({url:basePath+'/asepsisSendRec/findList',queryParams:{"orgId":orgId}});
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
