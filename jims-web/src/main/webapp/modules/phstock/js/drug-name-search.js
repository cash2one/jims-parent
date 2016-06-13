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
    var datas=[]
    $("#drugClass").combobox({
        valueField: 'classCode',
        textField: 'className'
    });

    var load=function(){
        $.get(basePath + '/drug-class-dict/list',function(drugClassList){
            for (var i =0; i <  drugClassList.length; i++) {
                if (drugClassList[i].parentId !="*") {
                    //data.splice(i,1);
                    datas.push(drugClassList[i])
                }
            }
            $("#drugClass").combobox('loadData', datas);
        });
    }

    load();
});




