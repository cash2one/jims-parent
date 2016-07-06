$(function(){
config.orgId
    var currentSelectIndex   // datagrid 当前选择的行索引
        ,currentOrgId = '1'  // 当前登录人所属单位ID
        ,currentStorage = config.currentStorage  // 当前登录人所属管理单位
        ,currentUsername = '录入者'   // 当前登录人姓名
        ,currentAccountFlag    //记账标志 0，不记账，1记账
        ,drugDicts = []  // 检索的药品字典数据
    var currentSubStorageDeptId // 当前选择的入库子单位的ID

    $.extend($.fn.validatebox.defaults.rules, {
        maxStock: {
            validator: function(value){
                var row = $('#dg').datagrid('getSelected')
                if(+value > +row.currentStock) {
                    return false
                }
                return true
            },
            message: '数量不能大于库存量！'
        },
        hasSelected: {
            validator: function(value){
                var editor = $('#dg').datagrid('getEditor',{index:currentSelectIndex,field:'drugName'})
                if(editor && !$(editor.target).combogrid('grid').datagrid('getSelected')){
                    return false
                }
                return true
            },
            message: '没有选中项'
        }
    });
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
    $.extend($.fn.combobox.methods, {
        addBlurListener: function(jq,param){
            jq.next().children(':text').blur(function(){
                var v = jq.combobox('getValue')
                if(v != undefined && v != ''){
                    var rows = jq.combobox('getData')
                    if(rows.length > 0){
                        var s = jq.combobox('options').textField
                        for(var i= 0,j=rows.length;i<j;i++){
                            if(rows[i][s] && rows[i][s].toUpperCase().indexOf(v.toUpperCase()) > -1){
                                jq.combobox('select',rows[i][jq.combobox('options').valueField])
                                return
                            }
                        }
                    }
                    jq.combobox('setValue','')
                }
            })
        }
    });

    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#dg').datagrid('getRows').length - 1
        $('#dg').datagrid('mergeCells', {index: _index, field: 'drugCode', rowspan: null, colspan: 10})
        $('#dg').datagrid('mergeCells', {index: _index, field: 'batchNo', rowspan: null, colspan: 2})
    }
    var endEditing = function () {
        if (currentSelectIndex == undefined) {
            return true
        }
        var editor = $('#dg').datagrid('getEditor',{index:currentSelectIndex,field:'drugName'})
        if(editor){
            var rows = $(editor.target).combogrid('grid').datagrid('getRows');
            if(rows.length > 0){
                if(!$(editor.target).combogrid('grid').datagrid('getSelected')){
                    $(editor.target).combogrid('grid').datagrid('selectRow',0)
                }
            } else {
                $(editor.target).combogrid('setValue','')
            }
        }
        if ($('#dg').datagrid('validateRow', currentSelectIndex)) {
            $('#dg').datagrid('endEdit', currentSelectIndex);
            return true;
        } else {
            return false;
        }
    }
    var onClickCell = function (index, field) {
        if (endEditing()) {
            if (index == $('#dg').datagrid('getRows').length - 1) {
                return
            }
            $('#dg').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            currentSelectIndex = index;
            if(field == 'drugName'){
                var editor = $('#dg').datagrid('getEditor',{index:index,field:field})
                $(editor.target).combogrid('grid').datagrid('loadData',drugDicts)
            }
        }
    }

    // 出库类别
    $('#statisticClass').combobox({
        url: parent.basePath + '/drug-export/findAll',
        valueField: 'exportClass',
        textField: 'exportClass',
        editable: false,
        method: 'GET',
        value: '发放出库',
        onSelect: function(record){
            currentAccountFlag = record['accountFlag']
            $("#dg").datagrid('loadData',[])
            $('#subStorageDept').combobox('clear')
            $('#stockSubDept').combobox('clear')
            $('#documentNo').textbox('clear')
            $('#storageDept').combobox('clear')
            $('#storageDept').combobox('reload',parent.basePath+'/drug-storage-dept/list?orgId='+currentOrgId+'&storageType='+(record['storageType'] == '全部'?'':record['storageType']))
        }
    })
    //日期
    $('#calendar').datebox({
        width: 140,
        editable: false,
        value: parent.formatDatebox(new Date())
    })
    //收货单位
    $('#storageDept').combobox({
        valueField : 'storageCode',
        textField : 'storageName',
        width: 160,
        url: parent.basePath+'/drug-storage-dept/list?orgId='+currentOrgId+'&storageType=药库',
        method : 'get',
        editable: false,
        onSelect : function(r){
            $("#dg").datagrid('loadData',[])
            $('#subStorageDept').combobox('clear')
            $('#stockSubDept').combobox('clear')
            $('#documentNo').textbox('clear')
            $('#subStorageDept').combobox('reload',parent.basePath+'/drug-storage-dept/findSubList?orgId='+currentOrgId+'&storageCode='+ r.storageCode)
        }
    })
    //收货子单位
    $('#subStorageDept').combobox({
        valueField:'subStorageCode',
        textField:'subStorage',
        width:140,
        method : 'get',
        editable: false,
        onSelect : function(r){
            $("#dg").datagrid('loadData',[])
            $('#stockSubDept').combobox('clear')
            $('#documentNo').textbox('clear')
        }
    })
    //库房子单位
    $('#stockSubDept').combobox({
        valueField:'subStorageCode',
        textField:'subStorage',
        url: parent.basePath+'/drug-storage-dept/findSubList?orgId='+currentOrgId+'&storageCode='+ currentStorage,
        method:'get',
        width:160,
        editable: false,
        onSelect:function(record){
            $("#dg").datagrid('loadData',[])
            query();
            currentSubStorageDeptId = record['id']
            var prefix = record['exportNoPrefix']
            var suffix = record['exportNoAva']
            if(prefix == undefined) prefix = ''
            if(suffix == undefined) suffix = ''
            var len = (prefix + suffix).length
            var v = len > 9 ? (prefix + suffix).substr(0,10) : prefix + '0000000000'.substr(len - 10) + suffix
            $('#documentNo').textbox('setValue',v)
        }
    })

    // 药品出库的datagrid
    $("#dg").datagrid({
        fit: true,
        fitColumns: false,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        width: 400,
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "代码",
            field: "drugCode",
            width: 100,
            align: 'center',
            formatter: function(value){
                if(value == '出库金额合计') return '<div style="text-align: right">' + value + '   </div>'
                return value
            }
        }, {
            title: "厂家",
            field: "supplier",
            width: 200,
            align: 'left',
            halign: 'center'
        }, {
            title: "药名",
            field: "drugName",
            width: 220,
            align: 'left',
            halign: 'center',
            editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 333,
                    idField: 'drugName',
                    textField: 'drugName',
                    required: true,
                    missingMessage: '药名不能为空',
                    fitColumns: true,
                    url:'/service/drug-stock/findListHasStock?limit=50&orgId='+currentOrgId+'&supplyIndicator=1',
                    method:'get',
                    mode:'remote',
                    columns: [[
                        {field: 'drugCode', title: '药品代码', width: 100, align: "center"},
                        {field: 'drugName', title: '药品名称', width: 160, halign: "center", align: "left"},
                        {field: 'inputCode', title: '输入码', width: 70, align: "center"}
                    ]],
                    onSelect: function (index, row) {
                        //loadDrugStockData(row)
                    }
                }
            }
        }, {
            title: "规格",
            field: "drugSpec",
            width: 70,
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: 70,
            align: 'center'
        }, {
            title: "数量",
            field: "quantity",
            width: 70,
            align: 'center',
            editor: {
                type: 'numberbox', options: {
                    required: true,
                    min : 1,
                    validType : ['maxStock']
                }
            }
        }, {
            title: "单价",
            field: "price",
            width: 70,
            align: 'center'
        }, {
            title: "批发价",
            field: "tradePrice",
            width: 70,
            align: 'center'
        }, {
            title: "当前结存",
            field: "currentStock",
            width: 70,
            align: 'center'
            //formatter: function(value,row){
            //    return (isNaN(value) ? 0 : value) - (isNaN(row.quantity) ? 0 : + row.quantity)
            //}
        }, {
            title: "零售价",
            field: "retailPrice",
            width: 70,
            align: 'center'
        }, {
            title: "出库金额",
            field: "outPrice",
            width: 70,
            align: 'center',
            formatter : function(value,row){
                if(row.drugCode == '出库金额合计') return value
                var outPrice = (isNaN(row.outPrice) ? 0 : +row.outPrice)
                var _value = ((isNaN(row.quantity) ? 0 : +row.quantity)
                * (isNaN(row.price) ? 0 : +row.price)).toFixed(2)

                row.outPrice = _value

                var _allRow = $('#dg').datagrid('getRows')
                var _lastRow = _allRow[_allRow.length - 1]
                if(_lastRow.drugCode == '出库金额合计') {
                    _lastRow.outPrice = (+_lastRow.outPrice + (+_value) - outPrice).toFixed(2)
                    $('#dg').datagrid('refreshRow', _allRow.length - 1)
                    $('#accountReceivable').numberbox('setValue',_lastRow.outPrice)
                    mergeLastCells()
                }
                return _value
            }
        }, {
            title: "批号",
            field: "batchNo",
            width: 70,
            align: 'center'
        }, {
            title: "有效期",
            field: "expireDate",
            width: 100,
            align: 'center',
            formatter: function(value){
                if(value && value.length > 9) return value.substr(0,10)
                return value
            }
        }
        ]],
        onClickCell: onClickCell,
        onBeforeSelect: function(index){
            return $('#dg').datagrid('validateRow', currentSelectIndex)
        }
    })

    $('#requestWindow').window({
        title: '选择申请出库的药品',
        width: '750',
        height: '450',
        collapsible :false,
        minimizable : false,
        maximizable : false,
        modal : true,
        resizable: false,
        closed: true
    })
    $("#requestTable").datagrid({
        fit: true,
        border: 0,
        //fitColumns: true, //列自适应宽度
        singleSelect: true,
        remoteSort: false,
        idField: 'id',
        footer:'#fb',
        columns: [[
            {field: 'subStorage', title: '发放子库房', width: 150, halign: "center",align: "left"},
            //{field: 'itemNo', title: '序号', width: 120, halign: "center",align: "left"},
            {field: 'drugName', title: '药品', width: 150, halign: "center",align: "left"},
            {field: 'drugSpec', title: '规格', width: 100, align: "center"},
            {field: 'packageUnits', title: '单位', width: 70, align: "center"},
            {field: 'quantity', title: '数量', width: 70, align: "center"},
            {field: 'enterDateTime', title: '申请日期', width: 100, align: "center"},
            {field: 'documentNo', title: '申请单号', width: 100, align: "center"},
            {field: 'batchNo', title: '批号', width: 100, align: "center"},
            {field: 'applicantStorage', title: '请领库房', width: 150, align: "center"},
            {field: 'applicantStorageSub', title: '请领子库房', width: 150, align: "center"}
        ]],
        onDblClickRow: function (index, row) {
            $.get(parent.basePath + '/drug-out/findDetailListWithStock',
                {orgId:currentOrgId,documentNo:row.documentNo,storage:row.storage,subStorage:row.subStorage}, function (res) {
                    $("#drug-import-batch").datagrid('loadData',res)
                })
            $('#importWindow').window('close')
        }
    })

    $('#newBtn').on('click',function(){
        $('#dg').datagrid('loadData',[])
    })
    $("#addBtn").on('click', function () {
        if(!$('#statisticClass').combobox('getValue')){
            $.messager.alert('警告','请先选择出库类别！','warning')
            return
        }
        if(!$('#storageDept').combobox('getValue')){
            $.messager.alert('警告','请先选择收货单位！','warning')
            return
        }
        if(!$('#documentNo').textbox('getValue')) {
            $.messager.alert('警告','请先选择库房子单位！','warning')
            return
        }
        if(!endEditing()) return
        var len = $('#dg').datagrid('getRows').length
        if (len == 0) {
            var countRecord = {
                drugCode: '出库金额合计',
                outPrice:'0'
            }
            $('#dg').datagrid('appendRow', countRecord)
            mergeLastCells()
        } else {
            len--
        }
        currentSelectIndex = len ;
        var record = {
            documentNo: $('#documentNo').textbox('getValue'),
            orgId: currentOrgId,
            batchNo: ''
        }

        $("#dg").datagrid('insertRow',{index:currentSelectIndex,row: record});
        $("#dg").datagrid('selectRow', currentSelectIndex);
    })
    $("#delBtn").on('click', function () {
        if (currentSelectIndex != undefined) {
            if(currentSelectIndex == $('#dg').datagrid('getRows').length - 1) return
            $("#dg").datagrid('deleteRow', currentSelectIndex);
            currentSelectIndex = undefined
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    })
    $("#saveBtn").on('click',function(){
        save()
    })
    $('#printBtn').on('click',function(){
        save('print')
    })
    $('#closeBtn').on('click',function(){
        parent.location.href = parent.getRootPath() + '/modules/index.html'
    })

    var query = function(){
        var applicantStorage = $('#storageDept').combobox('getValue');
        var applicantStorageSub = $('#subStorageDept').combobox('getValue');
        if(!applicantStorage || !applicantStorageSub){
            $("#requestTable").datagrid('loadData',[])
            $('#requestWindow').window('open')
        } else {
            $.get(parent.basePath + '/drugProvideApplication/findList',
                {orgId:currentOrgId,applicantStorage:applicantStorage,
                    applicantStorageSub:applicantStorageSub,flag:'0,1'},function(res){
                    $("#requestTable").datagrid('loadData',res)
                    $("#requestTable").datagrid('unselectAll')
                    $('#requestWindow').window('open')
                })
        }
    }
})