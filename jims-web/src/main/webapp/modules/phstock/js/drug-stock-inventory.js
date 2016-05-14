//药品库存盘点
$(function(){
    var loadDate=function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    };
    $("#date").datetimebox("setValue",loadDate());

    $("#dg").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        //toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "序号",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "药品编码",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "包装规格",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "剂型",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "批号",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "单价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "账面数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "账面数（包装）",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "实盘数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "盈亏数",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "账面额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "实盘额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "盈亏额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "分库房",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }

        ]]
    })


    $('#storage').combobox({
        idField: 'id',
        valueField: 'id',
        panelHeight:"90px",
        textField: 'text',
        data: [
            {'id': '0', 'text': '西药字库'},
            {'id': '1', 'text': '成药字库'},
            {'id': '2', 'text': '草药字库'}
        ]
    });
})