var editRow = undefined;
var rowNum=-1;
$(function() {
    $('#orderList').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '86%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method: 'get',
        url: basePath + '/ordersNurse/getNurseOrders?' + $('#searchform').serialize(),
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field: 'repeatIndicator', title: '长', width: '5%', align: 'center'},
            {field: 'orderClass', title: '类别', width: '5%', align: 'center'},
            {field: 'startDateTime', title: '开始时间', width: '10%', align: 'center'},
            {field: 'orderText', title: '医嘱内容', width: '10%', align: 'center'},

            {
                field: 'dosage',
                title: '剂量',
                width: '5%',
                align: 'center'
            },
            {field: 'administration', title: '途径', width: '5%', align: 'center'},
            {field: 'freqCounter', title: '次数', width: '5%', align: 'center'},
            {field: 'conversionDateTime', title: '执行时间', width: '10%', align: 'center'},
            {field: 'stopDateTime', title: '结束时间', width: '10%', align: 'center'},
            {field: 'verifyDataTime', title: '校对时间', width: '10%', align: 'center'},
            {field: 'time', title: '摆药截至时间', width: '10%', align: 'center'},
            {field: 'billingAttr', title: '自', width: '5%', align: 'center'},
          //  {field:'',title:'阴阳',width:'5%',align:'center'},
            {field: 'freqDetail', title: '医生说明', width: '10%', align: 'center'},
            {
                field: 'dosageUnits',
                title: '单位',
                width: '5%',
                align: 'center'

            },
            {field: 'frequency', title: '临嘱执行次数', width: '5%', align: 'center'},
            {field:'orderClass',title:'项目类别',width:'5%',align:'center'},
            {field:'doctor',title:'开医嘱医生',width:'5%',align:'center'},
            {field:'nurse',title:'校对护士',width:'5%',align:'center'},
            {field:'stopDoctor',title:'停医生',width:'5%',align:'center'},
            {field:'stopNurse',title:'停止护士',width:'5%',align:'center'},
            {field:'performSchedule',title:'执行时间',width:'5%',align:'center'}
         //   {field:'performSchedule',title:'执行护士',width:'5%',align:'center'}
        ]],
       onClickRow: function (rowIndex, rowData) {

            var row = $('#orderList').datagrid('getSelected');
            var dataGrid = $('#orderList');
            var row = $('#orderList').datagrid('getSelected');
            var status = row.orderStatus;
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false//新开
            } else {
                if (rowNum != rowIndex) {
                    if (rowNum >= 0) {
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum = rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }


        }, onDblClickRow: function (rowIndex, rowData) {
            $("#ordersDialog").dialog('open');
        }, rowStyler: function (index, row) {
            if (row.orderStatus == '1') {
                return 'color:black;';
            } else if (row.orderStatus == "2") {
                return 'color:blue;';
            } else if (row.orderStatus == "3") {
                return 'color:yellow;';
            } else if (row.orderStatus == "4") {
                return 'color:red;';
            }
        }
    });
    $("#submit_search").linkbutton({iconCls: 'icon-search', plain: true}).click(function () {
        $('#orderList').datagrid("load");   //点击搜索
    });
});