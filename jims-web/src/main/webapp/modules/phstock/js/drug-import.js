/**
 * 入库处理
 * @author yangruidong
 * @version 2016-05-14
 */
$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drug-import").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }
    $("#drug-import").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        footer: '#fb',
        method: 'GET',
        rownumbers: true,


        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "行数",
            field: "administrationCode",
            width: '6%',
            align: 'center'

        }, {
            title: "代码",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "药名",
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
            title: "批号",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "扣率",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "进价",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "批价",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "进价金额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "当前结存",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }, {
            title: "有效期",
            field: "classCode",
            width: '10%',
            align: 'center',
            editor: {
                type: 'datebox'
            }
        }, {
            title: "发票号",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        } , {
            title: "发票日期",
            field: "administrationCode",
            width: '10%',
            align: 'center',
            editor: {

                type: 'datebox'
            }
        }, {
            title: "零价",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }  , {
            title: "零价总额",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "备注",
            field: "administrationCode",
            width: '6%',
            align: 'center'
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    //设置是否禁用控件
    $("#supplyChild").textbox({'disabled':true});
    $("#importChild").textbox({'disabled':true});
    $("#importDocument").textbox({'disabled':true});

    $("#import").combobox({
        url: basePath + '/drug-import/findAll',
        valueField: 'importClass',
        textField: 'importClass',
        method: 'GET'
    });
});




