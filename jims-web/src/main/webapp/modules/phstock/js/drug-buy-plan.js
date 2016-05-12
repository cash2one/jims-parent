$(function(){
    var username  = '仓管员',orgId = '1',buyId = ''
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

    var base_url = '/service/drugBuyPlan/'
    var temporaryNo = []
    var initBuyPlanTable = function (){
        $("#buyPlanTable").datagrid({
            title : "药品购买计划",
            fit : true,
            border:0,
            fitColumns: true, //列自适应宽度
            singleSelect : true,
            remoteSort: false,
            idField :'id',
            columns: [[
                {field: 'id', title: '编号',hidden:true},
                { field: 'buyNo', title: '采购序号', width: 40,align : "center" },
                { field: 'drugName', title: '药名', width: 80,align : "center" },
                { field: 'drugSpec', title: '包装规格', width: 40,align : "center" },
                { field: 'units', title: '包装单位', width: 40,align : "center" },
                { field: 'stockSupplier', title: '厂家', width: 80,align : "center" },
                { field: 'wantNumber', title: '计划数量', width: 40,align : "center" },
                { field: 'purchasePrice', title: '进货价', width: 50,align : "center" },
                { field: 'count', title: '金额', width: 40,align : "center" },
                { field: 'drugForm', title: '剂型', width: 80,align : "center" },
                { field: 'toxiProperty', title: '毒理', width: 80,align : "center" },
                { field: 'storer', title: '仓管员', width: 50,align : "center" },
                { field: 'buyNo1', title: '库存参考数', width: 90,align : "center" },
                { field: 'buyNo2', title: '月消耗量', width: 80,align : "center" },
                { field: 'buyNo3', title: '零售价', width: 80,align : "center" }
            ]],
            toolbar: '#tbn'
        });
    }
    var addRow = function(){
        var record = {
            buyId : buyId,  // 后台生成
            buyNo : $('#buyPlanTable').datagrid('getRows').length+1,
            storer : username,
            orgId : orgId
        }
        $('#buyPlanTable').datagrid('appendRow',record)
    }
    var delRow = function(){
        var _row = $('#buyPlanTable').datagrid('getSelected')
        if(_row){
            var _index = $('#buyPlanTable').datagrid('getRowIndex',_row)
            $('#buyPlanTable').datagrid('deleteRow',_index)
            var _rows = $('#buyPlanTable').datagrid('getRows')
            for(var len = _rows.length;_index<len;_index++){
                _rows[_index].buyNo = _index + 1
                $('#buyPlanTable').datagrid('refreshRow',_index)
            }
        } else {
            $.messager.alert('警告','请选择要删除的药品！','warning')
        }
    }
    var saveData = function(){
        var addData = $('#buyPlanTable').datagrid('getChanges','inserted')
        alert(JSON.stringify(addData))
    }
    var initBtn = function(){
        $('#temporaryNo').combobox({
            data : temporaryNo,
            valueField:'value',
            textField:'label',
            editable : false
        });
        $('#startDate').datetimebox({
            showSeconds: true
        });
        $('#endData').datetimebox({
            showSeconds: true
        });
        $('#addButton').linkbutton({
            iconCls: 'icon-add',
            text : '增加',
            onClick:addRow
        })
        $('#delButton').linkbutton({
            iconCls: 'icon-remove',
            text : '删除',
            onClick:delRow
        })
        $('#exportButton').linkbutton({
            iconCls: 'icon-ok',
            text : '导出',
            onClick:function(){
                alert()
            }
        })
        $('#tempButton').linkbutton({
            iconCls: 'icon-add',
            text : '暂存',
            onClick:function(){
                alert()
            }
        })
        $('#saveButton').linkbutton({
            iconCls: 'icon-save',
            text : '保存',
            onClick:saveData
        })
        $('#flushButton').linkbutton({
            iconCls: 'icon-reload',
            text : '刷新',
            onClick:function(){
                alert()
            }
        })
        $('#printButton').linkbutton({
            iconCls: 'icon-print',
            text : '打印',
            onClick:function(){
                alert()
            }
        })
        $('#closeButton').linkbutton({
            iconCls: 'icon-cancel',
            text : '关闭',
            onClick:function(){
                alert()
            }
        })
    }

    var init = function(){
        initBuyPlanTable()
        initBtn()
    }

    init()
    //$("#buyPlanTable").datagrid('loadData',[])
})