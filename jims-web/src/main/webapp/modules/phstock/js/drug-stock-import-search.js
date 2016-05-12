/**
 * 入库记录查询
 * @author yangruidong
 * @version 2016-05-12
 */
$(function () {
    $("#stock").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
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
            title: "入库日期",
            field: "administrationCode",
            width: '6%',
            align: 'center'

        }, {
            title: "供货单位",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "供货子单位",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "金额",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "批号",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "有效期",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "价格",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "零售价",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "扣率",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "发票号",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "发票日期",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "入库单号",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "备注",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }
        ]]
    });

    //设置是否禁用日期控件
    $("#calendar").datebox({'disabled':true});
    $("#calendar1").datebox({'disabled':true});

    $("#ck").on("click", function () {
        if ($("#ck").prop("checked") == true) {
            $("#calendar").datebox({'disabled':false});
            $("#calendar1"). datebox({'disabled':false});
        } else {
            $("#calendar").datebox({'disabled':true});
            $("#calendar1").datebox({'disabled':true});
        }
    });
});




