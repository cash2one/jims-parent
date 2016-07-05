/**
 * 批量入库
 * @author yangruidong
 * @version 2016-05-14
 */
$(function () {
    var currentOrgId = '1';
    var currentStorage = '当前库存';
    var accountFlag ;
    var currentUsername = '测试员' ;
    var currentSubStorageDeptId  ;
    var currentSelectIndex;

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
    var endEditing = function () {
        if (currentSelectIndex == undefined) {
            return true
        }
        if ($('#drug-import-batch').datagrid('validateRow', currentSelectIndex)) {
            $('#drug-import-batch').datagrid('endEdit', currentSelectIndex);
            return true;
        } else {
            return false;
        }
    }

    var onClickCell = function (index, field) {
        if (endEditing()) {
            if (index == $('#drug-import-batch').datagrid('getRows').length - 1) return
            $('#drug-import-batch').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            currentSelectIndex = index;
        }
    }

    // 入库类别
    $("#importClass").combobox({
        width:'130',
        url: parent.basePath + '/drug-import/findAll',
        valueField: 'importClass',
        textField: 'importClass',
        editable: false,
        method: 'GET',
        onSelect: function(r){
            $("#drug-import-batch").datagrid('loadData',[])
            accountFlag = r['accountFlag']
            $("#importStock").combobox('clear');
            $("#importSubStock").combobox('loadData',[]);
            $("#importSubStock").combobox('clear');
            $("#supply").combobox('clear');
            $("#supplyChild").combobox('loadData',[]);
            $("#supplyChild").combobox('clear');
            $("#supply").combobox('reload',parent.basePath + '/drug-storage-dept/list?orgId='+currentOrgId+'&storageType='+ r.storageType)
        }
    });
    //入库库房
    $("#importStock").combobox({
        width:'150',
        url: parent.basePath + '/drug-storage-dept/list?orgId='+currentOrgId,
        valueField: 'storageCode',
        textField: 'storageName',
        editable: false,
        method: 'GET',
        onSelect : function(r){
            $("#drug-import-batch").datagrid('loadData',[])
            $("#importSubStock").combobox('clear');
            $('#importDocument').textbox('setValue','')
            $("#importSubStock").combobox('reload',parent.basePath + '/drug-storage-dept/findSubList?orgId='+currentOrgId+'&storageCode='+ r.storageCode)
            $("#supply").combobox('clear');
            $("#supplyChild").combobox('loadData',[]);
            $("#supplyChild").combobox('clear');
        }
    });
    //入库子单位
    $("#importSubStock").combobox({
        width:'150',
        valueField: 'subStorageCode',
        textField: 'subStorage',
        editable: false,
        method: 'GET',
        onSelect : function(r){
            $("#drug-import-batch").datagrid('loadData',[])
            $("#supply").combobox('clear');
            $("#supplyChild").combobox('loadData',[]);
            $("#supplyChild").combobox('clear');
            currentSubStorageDeptId = r['id']
            var _prefix = r['importNoPrefix']
            var _suffix = r['importNoAva']
            if(_prefix == undefined) _prefix = ''
            if(_suffix == undefined) _suffix = ''
            var _len = (_prefix + _suffix).length
            var _v = _len > 9 ? (_prefix + _suffix).substr(0,10) : _prefix + '0000000000'.substr(_len - 10) + _suffix
            $('#importDocument').textbox('setValue',_v)
        }
    });
    //供货单位
    $("#supply").combobox({
        width:'150',
        valueField: 'storageCode',
        textField: 'storageName',
        editable: false,
        method: 'GET',
        onSelect : function(r){
            $("#drug-import-batch").datagrid('loadData',[])
            $("#supplyChild").combobox('clear');
            $.get(parent.basePath + '/drug-storage-dept/findSubList',{orgId:currentOrgId,storageCode:r.storageCode}, function (res) {
                $("#supplyChild").combobox('loadData',res);
                if(res.length > 0){
                    $("#supplyChild").combobox('setValue',res[0].subStorageCode);
                }
                query()
            })
        }
    });
    //供货子单位
    $("#supplyChild").combobox({
        width:'150',
        valueField: 'subStorageCode',
        textField: 'subStorage',
        editable: false,
        method: 'GET',
        onSelect : function(r){
            $("#drug-import-batch").datagrid('loadData',[])
            query()
        }
    });

    $("#drug-import-batch").datagrid({
        fit: true,
        fitColumns: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "代码",
            field: "drugCode",
            width: '100',
            align: 'center'

        }, {
            title: "药名",
            field: "drugName",
            width: '150',
            align: 'center'
        }, {
            title: "规格",
            field: "drugSpec",
            width: '60',
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: '60',
            align: 'center'
        }, {
            title: "批号",
            field: "batchNo",
            width: '70',
            align: 'center'
        }, {
            title: "数量",
            field: "quantity",
            width: '60',
            align: 'center'
        }, {
            title: "扣率",
            field: "discount",
            width: '60',
            align: 'center',
            formatter: function(value,row){
                if(row.drugCode == '合计') return value
                row.discount = 100;
                return 100;
            }
        }, {
            title: "进价",
            field: "retailPrice",
            width: '60',
            align: 'center'
        }, {
            title: "批价",
            field: "tradePrice",
            width: '60',
            align: 'center'
        }, {
            title: "进价金额",
            field: "purchasePriceCount",
            width: '70',
            align: 'center',
            formatter: function (value,row) {
                if(row.drugCode == '合计') return value
                return ((isNaN(row.discount) ? 0 : +row.discount)
                * (isNaN(row.quantity) ? 0 : +row.quantity)
                * (isNaN(row.retailPrice) ? 0 : +row.retailPrice) / 100 ).toFixed(2)
            }
        }, {
            title: "当前结存",
            field: "stock",
            width: '70',
            align: 'center'
        }, {
            title: "厂家",
            field: "firmName",
            width: '200',
            halign: 'center',
            align: 'left'
        }, {
            title: "有效期",
            field: "expireDate",
            width: '100',
            align: 'center',
            formatter: function(value){
                return parent.formatDatebox(value)
            }
        }, {
            title: "发票号",
            field: "invoiceNo",
            width: '80px',
            align: 'center',
            editor : 'textbox'
        } , {
            title: "发票日期",
            field: "invoiceDate",
            width: '100px',
            align: 'center',
            editor : {
                type: 'datebox',
                options: {
                    editable: false
                }
            }
        }, {
            title: "零价",
            field: "retailPrice",
            width: '60',
            align: 'center'
        }  , {
            title: "零价总额",
            field: "retailCount",
            width: '70',
            align: 'center',
            formatter: function(value,row){
                if(row.drugCode == '合计') return value
                return ((isNaN(row.discount) ? 0 : +row.discount)
                * (isNaN(row.quantity) ? 0 : +row.quantity)
                * (isNaN(row.retailPrice) ? 0 : +row.retailPrice) / 100 ).toFixed(2)
            }
        }, {
            title: "备注",
            field: "memo",
            width: '100px',
            halign: 'center',
            align: 'left',
            editor : 'textbox'
        }
        ]],
        onLoadSuccess: function(){
            var rows = $("#drug-import-batch").datagrid('getRows')
            if(rows.length > 0) {
                var c = 0
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i]
                    c += +((isNaN(row.discount) ? 0 : +row.discount)
                    * (isNaN(row.quantity) ? 0 : +row.quantity)
                    * (isNaN(row.retailPrice) ? 0 : +row.retailPrice) / 100 )
                }
                $(this).datagrid('appendRow', {
                    drugCode: '合计',
                    purchasePriceCount: c.toFixed(2),
                    retailCount: c.toFixed(2)
                })
            }
        },
        onClickCell: onClickCell
    });

    $('#importWindow').window({
        title: '选择入库药品所在单据',
        width: '550',
        height: '450',
        collapsible :false,
        minimizable : false,
        maximizable : false,
        modal : true,
        resizable: false,
        closed: true,
        onClose : function(){

        }
    })

    $("#importTable").datagrid({
        fit: true,
        border: 0,
        fitColumns: true, //列自适应宽度
        singleSelect: true,
        remoteSort: false,
        idField: 'id',
        columns: [[
            {field: 'storage', title: '供货库房', width: 120, halign: "center",align: "left",formatter:function(value){
                return $("#supply").combobox('getText');
            }},
            {field: 'subStorage', title: '供货子库房', width: 120, halign: "center",align: "left",formatter:function(value){
                return $("#supplyChild").combobox('getText');
            }},
            {field: 'documentNo', title: '单据号', width: 100, align: "center"},
            {field: 'exportClass', title: '出库方式', width: 100, align: "center"}
        ]],
        onDblClickRow: function (index, row) {
            $.get(parent.basePath + '/drug-out/findDetailListWithStock',
                {orgId:currentOrgId,documentNo:row.documentNo,storage:row.storage,subStorage:row.subStorage}, function (res) {
                $("#drug-import-batch").datagrid('loadData',res)
            })
            $('#importWindow').window('close')
        }
    })

    $("#importDocument").textbox({
        width: '130',
        disabled:true
    });

    $('#date').datebox({
        width: 130,
        editable: false,
        value: parent.formatDatebox(new Date())
    })

    var query = function(){
        var receiver = $('#importStock').combobox('getValue');
        var subReceiver = $('#importSubStock').combobox('getValue');
        var subStorage = $('#supplyChild').combobox('getValue');
        if(!receiver || !subReceiver || !subStorage){
            $("#importTable").datagrid('loadData',[])
            $('#importWindow').window('open')
        } else {
            $.get(parent.basePath + '/drug-out/findMasterList',
                {orgId:currentOrgId,receiver:receiver,subReceiver:subReceiver,subStorage:subStorage},function(res){
                $("#importTable").datagrid('loadData',res)
                $("#importTable").datagrid('unselectAll')
                $('#importWindow').window('open')
            })
        }
    }
    
    $('#saveBtn').click(function () {
        var rows = $('#drug-import-batch').datagrid('getRows')
        endEditing();
        if(rows.length == 0){
            $.messager.alert('保存','没有要保存的数据！','info');
            return false;
        }
        for(var i=0;i<rows.length;i++){
            rows[i].documentNo = $('#importDocument').textbox('getValue')
            delete rows[i].id
            delete rows[i].importDocumentNo
            delete rows[i].drugStockId
            delete rows[i].firmName
            delete rows[i].drugName
            delete rows[i].stock
        }
        var record = {
            documentNo : $('#importDocument').textbox('getValue')
            ,storage : currentStorage
            ,importDate : $('#date').datebox('getValue')
            ,supplier : $('#supply').combobox('getValue')
            ,accountReceivable : 0
            ,accountPayed : 0
            ,additionalFee : 0
            ,importClass : $('#importClass').combobox('getValue')
            ,subStorage : $('#importSubStock').combobox('getValue')
            ,accountIndicator : accountFlag ? accountFlag : 0
            ,memos : $('#remarks').textbox('getValue')
            ,operator : currentUsername
            ,subSupplier : $('#supplyChild').combobox('getValue')
            ,orgId : currentOrgId
            ,detailList : rows.slice(0,rows.length - 1)
            ,subStorageDeptId : currentSubStorageDeptId
        }
        parent.$.postJSON('/service/drug-in/save',JSON.stringify(record),function(res){
            if(res == '1'){
                $.messager.alert('保存',(accountFlag == '1' ? '保存并记账成功！' : '保存成功！'),'info',function(){
                    window.location.reload()
                })
            } else {
                $.messager.alert('保存','保存失败！','error')
            }
        })
    })
});




