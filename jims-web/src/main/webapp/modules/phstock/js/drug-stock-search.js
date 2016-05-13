/**
 * 库存量查询
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
            title: "库房",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "名称",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "药品编码",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "规格",
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
            title: "批号",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "有效期",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "进价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "批价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "零售价",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "数量(包装)",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "进价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "批价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        } , {
            title: "零价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "折扣",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "供货方",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "供货子单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]]
    });

    //设置是否禁用日期控件
    //var ck=  $("#ck").val();
   /* $("#feeTypeMask").on("click", function () {
        console.log($("#feeTypeMask").prop("checked"));
        if ($("#feeTypeMask").prop("checked") == true) {
            $("#feeTypeMask").val(1);
        } else {
            $("#feeTypeMask").val(0);
        }
    });*/



});




