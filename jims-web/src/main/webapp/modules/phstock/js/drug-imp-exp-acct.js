/**
 * Created by wei on 2016/5/13.
 */
$(function () {
    $("#dg").datagrid({
        title: '入库单据列表',
        fit: false,//让#dg数据创铺满父类容器
        singleSelect: true,
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
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });
    $("#dlg").datagrid({
        title: '出库单据列表',
        fit: true,
        singleSelect: true,
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
    var reset = function () {
        $("#text").textbox('disable');
        $("#date1").datebox('disable');
        $("#date2").datebox('disable');
    };
    reset();
    $('#chk1').click(function () {
        var a=document.getElementById("chk1");
        if(a.checked){
            $("#date1").datebox('enable');
            $("#date2").datebox('enable');
        } else{
            $("#date1").datebox('disable');
            $("#date2").datebox('disable');
        }
    });
    $('#chk2').click(function () {
        var b=document.getElementById("chk2");
        if(b.checked){
            $("#text").textbox('enable');
        } else{
            $("#text").textbox('disable');
        }

    });

});