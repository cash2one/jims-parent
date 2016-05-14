/**
 * 根据消耗量定库存量高低限
 * @author yangruidong
 * @version 2016-05-14
 */
$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drug-stock-level-set").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }
    $("#drug-stock-level-set").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',

        method: 'GET',
        rownumbers: true,
        /*url: basePath + '/drug-class-dict/list?orgId=1',*/

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
            width: '8%',
            align: 'center'
        }, {
            title: "名称",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "规格",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "单位",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "每包装含量",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "包装单位",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "高位水平",
            field: "classCode",
            width: '10%',
            align: 'center',
            editor: {
                type: 'textbox'
            }
        }, {
            title: "低位水平",
            field: "className",
            width: '8%',
            align: 'center',
            editor: {
                type: 'textbox'
            }
        }, {
            title: "统计区间消耗",
            field: "administrationCode",
            width: '8%',
            align: 'center'
        }, {
            title: "库存子单位",
            field: "administrationCode",
            width: '7%',
            align: 'center'
        }
        ]] ,
        onClickCell: onClickCell
    });
    //datagrid的单元格编辑
    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#drug-stock-level-set').datagrid('validateRow', editIndex)){
            $('#drug-stock-level-set').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickCell(index, field){
        if (endEditing()){
            $('#drug-stock-level-set').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            editIndex = index;
        }
    }

    //设置是否禁用控件
    $("#importChild").textbox({'disabled':true});
    $("#importDocument").textbox({'disabled':true});

    $("#importClass").combobox({
        url: basePath + '/drug-import/findAll',
        valueField: 'importClass',
        textField: 'importClass',
        method: 'GET'
    });
});




