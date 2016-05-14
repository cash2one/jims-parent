/**
 * 药品目录维护
 * @author yangruidong
 * @version 2016-05-13
 */
$(function () {

    $("#drug-name-search").datagrid({
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
            title: "药品编码",
            field: "administrationCode",
            width: '9%',
            align: 'center'

        }, {
            title: "药品名称",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "包装规格",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "厂家",
            field: "administrationCode",
            width: '9%',
            align: 'center'

        }, {
            title: "单位",
            field: "administrationCode",
            width: '9%',
            align: 'center'

        }, {
            title: "剂型",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "批发价",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "零售价",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "数量",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }, {
            title: "录入日期",
            field: "administrationCode",
            width: '9%',
            align: 'center'
        }
        ]]
    });
    var dataClass={};
     $("#drugClass").combobox({

         url: basePath + '/drug-class-dict/list?orgId=1',
         valueField: 'classCode',
         textField: 'className',
         method: 'GET',
         onLoadSuccess: function () {
             var data = $(this).combobox('getData');
             for (var i = data.length-1; i > -1; i--) {
                 if (data[i].parentId =="*") {
                    data.splice(i,1);
                 }
             }
             $("#drugClass").combobox('loadData', data);
         }
     });
});




