/**
 * 药品限制等级维护
 * @author yangruidong
 * @version 2016-05-13
 */
$(function () {

    //准备的数据
    var data = [{
        value: '限制级',
        text: '限制级'
    }, {
        value: '非限制级',
        text: '非限制级'
    }, {
        value: '特殊级',
        text: '特殊级'
    }]

    function unitformatter(value, rowData, rowIndex) {
        if (value == 0) {
            return;
        }

        for (var i = 0; i < data.length; i++) {
            if (data[i].value == value) {
                return data[i].text;
            }
        }
    }

    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#adminDict").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }


    $("#drug-limit-grade-manager").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        /*  url: basePath + '/drug-class-dict/list?orgId=1',*/

        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "代码",
            field: "administrationCode",
            width: '7%',
            align: 'center'

        }, {
            title: "药名",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "限制等级",
            field: "administrationCode",
            width: '7%',
            align: 'center',
            formatter: unitformatter,
            editor: {type: 'combobox', options: {data: data, valueField: "value", textField: "text"}}

        }, {
            title: "OTC类型",
            field: "administrationCode",
            width: '7%',
            align: 'center',
            editor: {
                type: 'combobox',
                options: {
                    valueField: "value",
                    textField: "text",
                    data: [{value: '0', text: '是'}, {value: '1', text: '否'}]
                }
            }
        }, {
            title: "单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "剂型",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "毒理",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "单位剂量",
            field: "administrationCode",
            width: '7%',
            align: 'right'
        }, {
            title: "剂量单位",
            field: "administrationCode",
            width: '7%',
            align: 'left'
        }, {
            title: "类别",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "输入码",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }, {
            title: "贵重等级",
            field: "administrationCode",
            width: '7%',
            align: 'center',
            editor: {
                type: 'combogrid',
                options: {
                    panelWidth:200,
                    idField:'code',
                    textField:'name',
                    /*url:'datagrid_data.json',*/
                    columns:[[
                        {field:'code',title:'等级代码',width:80},
                        {field:'name',title:'等级名称',width:120},
                    ]]
                }
            }
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
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




