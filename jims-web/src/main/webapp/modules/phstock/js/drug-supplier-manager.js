/**
 * 药品供应维护
 * @author luohk
 * @version 2016-05-14
 */

$(function () {
    $("#supplier").datagrid({
        title: '药品供应维护',
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
            title: "药品",
            field: "administrationCode",
            width: '11%',
            align: 'center'

        }, {
            title: "规格",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "零售价",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "批号",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "数量(包装)",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }, {
            title: "供应标志",
            field: "administrationCode",
            width: '11%',
            align: 'center'
        }]]
    });
});