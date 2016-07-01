
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
    $("#toDept").combobox({
        url:basePath + '/dept-dict/list?orgId='+orgId,
        valueField:'deptCode',
        textField:'deptName' ,
        method:'GET'
    });
    //获取借物人
    $("#lender").combobox({
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
            {field: 'toDept', title: '借物科室', width: '8%', align: 'center'},
            {field: 'documentNo', title: '业务单据', width: '8%', align: 'center'},
            {field: 'itemNo', title: '序号', width: '4%', align: 'center'},
            {field: 'itemName', title: '名称', width: '10%', align: 'center'},
            {field: 'itemSpec', title: '规格', width: '4%', align: 'center'},
            {field: 'lendAmount', title: '借物数量', width: '5%', align: 'center'},
            {field: 'units', title: '单位', width: '4%', align: 'center'},
            {field: 'returnAmount', title: '归还数量', width: '5%', align: 'center'},
            {field: 'lendDate',title:'借物时间',width:'8%',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }},
            {field: 'returnDate',title:'还物时间',width:'8%',align:'center',formatter: function(value){
                return parent.formatDatebox(value)
            }},
            {field: 'returnFlag',title:'物品状态',width:'10%',align:'center',formatter: function(value,row ){
                if(row.returnFlag=='0'){row.returnFlag = "申请";}
                else if(row.returnFlag=='1'){row.returnFlag = "未还";}
                else if(row.returnFlag=='2'){row.returnFlag = "部分还";}
                else if(row.returnFlag=='3'){row.returnFlag = "全还";}
                else if(row.returnFlag=='4'){row.returnFlag = "对换-未换";}
                else if(row.returnFlag=='5'){row.returnFlag = "对换-已换";}
                else{row.returnFlag = row.returnFlag;}
                return row.returnFlag; }
            },
            {field: 'operator',title:'发物人',width:'7%',align:'center'},
            {field: 'lender',title:'借物人',width:'7%',align:'center'},
            {field: 'returnMan',title:'还物人',width:'7%',align:'center'},
            {field: 'memos', title: '备注', width: '10%', align: 'center'}
        ]]
    });

    loadAnotherData = function(){
        var a = $("#toDept").combobox("getData");
        var b = $("#lender").combobox("getData");
        var listAll = $('#list_data').datagrid('getRows');
        $.each(a,function(index,row){
            $.each(listAll, function(i, r){
                if(row.deptCode == r.toDept){
                    r.toDept = row.deptName;
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
                if(row.empNo == r.operator){
                    r.operator = row.name;
                    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                }
                if(row.empNo == r.lender){
                    r.lender = row.name;
                    $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
                }
            });
        });
    }
    var loadListData = function (flag) {
        if(flag=='1'){
            var exchangeStart = parent.parseToDate($('#exchangeStart').datebox('getValue'));
            var exchangeEnd = parent.parseToDate($('#exchangeEnd').datebox('getValue'));
            var toDept = $("#toDept").combobox('getValue');
            var lender = $("#lender").combobox('getValue');
            var returnFlag = $("#returnFlag").combobox('getValue');
            $("#list_data").datagrid({url:basePath+'/asepsisLendRec/findList',queryParams:{"orgId":orgId,"lendDateStart":exchangeStart,"lendDateEnd":exchangeEnd,"toDept":toDept,"lender":lender,"returnFlag":returnFlag}});
        }else{
            $("#list_data").datagrid({url:basePath+'/asepsisLendRec/findList',queryParams:{"orgId":orgId}});
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
