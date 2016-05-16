/**
 * Created by wei on 2016/5/13.
 */
$(function () {

    $("#dg").datagrid({
        title: '入库单据列表',
        fit: true,
        toolbar:'#tb1',
        columns: [[{
            title: '编号',
            field: 'id',
            hidden: 'true'
        }, {
            title: '日期',
            field: 'supplierId',
            width: "20%",
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '单据号',
            field: 'supplier',
            width: "20%",
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }, {
            title: '类型',
            field: 'supplierClass',
            width: "20%",
            editor: {
                type: 'text', options: {
                    required: true
                }
            }
        }]]
    });
    $("#dlg").datagrid({
        title: '入库单据列表',
        fit: true,
        toolbar:'#tt'


    });

    $('#tt').tabs({
        border:false,
        fit:true,
        tools:'#tb2',
        onSelect:function(title){
        }
    });


});