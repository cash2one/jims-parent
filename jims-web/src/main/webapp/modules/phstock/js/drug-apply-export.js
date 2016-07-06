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
                if(+value > +row.stock) {
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

    //合并合计单元格
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
            if(rows.length > 0 && $(editor.target).combogrid('getValue')){
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
            var rows = $('#dg').datagrid('getRows')
            if (index == rows.length - 1) {
                return
            }
            if(rows[index].id && field == 'drugName') return
            $('#dg').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            currentSelectIndex = index;
            if(field == 'drugName'){
                var editor = $('#dg').datagrid('getEditor',{index:index,field:field})
                $(editor.target).combogrid('grid').datagrid('loadData',drugDicts)
            }
        }
    }

    /**
     * 判断是否已存在此药品的规格
     * @param o {drugCode,packSpec,packUnit}
     */
    var chargeDrugExisted = function (o) {
        var rows = $('#dg').datagrid('getRows')
        for (var i = 0, j = rows.length - 1; i < j; i++) {
            if (i != currentSelectIndex && rows[i].drugCode == o['drugCode']
                && rows[i].firmId == o['firmId'] && rows[i].drugSpec == o['drugSpec']
                && rows[i].units == o['units']) {
                return true
            }
        }
        return false
    }
    /**
     * 药品返回到选择前的值
     * @param oldV 选择前的值
     */
    var rollBack = function (oldV) {
        var editor = $('#dg').datagrid('getEditor', {index: currentSelectIndex, field: 'drugName'})
        $(editor.target).combogrid('setValue', oldV)
        $('#dg').datagrid('endEdit', currentSelectIndex)
    }
    /**
     * 加载同一药品的不同规格、厂商等
     * @param drugDict
     */
    var loadDrugStockData = function (drugDict) {
        var chargeType = [{colName:'storage',colValue:drugDict['storage'],operateMethod:"="},
            {colName:'drug_code',colValue:drugDict['drugCode'],operateMethod:"="}]
        var param = {dictType:'v_drug_stock',orgId:drugDict['orgId'],inputParamVos:chargeType}

        parent.$.postJSON('/service/input-setting/listParam',
            JSON.stringify(param) ,function(res){
                showWindow(res, drugDict)
            })
    }
    /**
     * 展现药品库存数据，当库存参数内只有一条数据时不显示，直接赋值。当一条也没有时清空药品名称。
     * @param drugStocks 药品库存数据
     * @param drugDict 药品名称
     */
    var showWindow = function (drugStocks, drugDict) {
        var exportRow = $('#dg').datagrid('getSelected')
        var _oldDrugName = exportRow.drugName
        if (!drugStocks || drugStocks.length == 0) {
            rollBack(_oldDrugName)
            return
        }
        var _tempFlag = false   // 当window关闭时是否赋值

        var initData = function (drugStock) {
            var drugParam = {
                drugCode: drugStock['drug_code'],
                firmId: drugStock['firm_id'],
                drugSpec: drugStock['drug_spec'],
                units: drugStock['units']
            }
            if (chargeDrugExisted(drugParam)) {
                $.messager.alert('警告', '该规格的药品已存在，请重新选择！', 'error')
                rollBack(_oldDrugName)
                return
            }
            exportRow.drugCode = drugStock['drug_code']
            exportRow.drugSpec = drugStock['drug_spec']
            exportRow.units = drugStock['units']
            exportRow.batchNo = drugStock['batch_no']
            exportRow.expireDate = drugStock['expire_date']
            exportRow.firmId = drugStock['firm_id']
            exportRow.supplier = drugStock['supplier']
            exportRow.retailPrice = drugStock['retail_price']
            exportRow.tradePrice = drugStock['trade_price']
            exportRow.price = drugStock['retail_price']
            exportRow.packageSpec = drugStock['package_spec']
            exportRow.packageUnits = drugStock['package_units']
            exportRow.subPackage1 = drugStock['sub_package_1']
            exportRow.subPackageUnits1 = drugStock['sub_package_units_1']
            exportRow.subPackageSpec1 = drugStock['sub_package_spec_1']
            exportRow.subPackage2 = drugStock['sub_package_2']
            exportRow.subPackageUnits2 = drugStock['sub_package_units_2']
            exportRow.subPackageSpec2 = drugStock['sub_package_spec_2']
            exportRow.purchasePrice = drugStock['purchase_price']
            exportRow.stock = drugStock['quantity']
            exportRow.drugStockId = drugStock['id']
            exportRow.quantity=0
            $('#dg').datagrid('endEdit', currentSelectIndex)
            _tempFlag = true
        }
        if (drugStocks.length == 1) {
            initData(drugStocks[0])
            return
        }

        $('#drugStockWindow').window({
            title: '选择药品规格和单位',
            width: '653',
            height: '450',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            modal: true,
            resizable: false,
            onClose: function () {
                if (!_tempFlag) {
                    rollBack(_oldDrugName)
                }
            }
        })
        $("#drugStockTable").datagrid({
            fit: true,
            border: 0,
            fitColumns: true, //列自适应宽度
            singleSelect: true,
            remoteSort: false,
            idField: 'id',
            data:drugStocks,
            columns: [[
                {field: 'id', title: '编号', hidden: true},
                {field: 'drug_spec', title: '规格', width: 60, align: "center"},
                {field: 'supplier', title: '厂家', width: 200, halign: "center",align:'left'},
                {field: 'quantity', title: '库存量', width: 60, align: "center"},
                {field: 'purchase_price', title: '进货价', width: 60, align: "center"},
                {field: 'trade_price', title: '批发价', width: 60, align: "center"},
                {field: 'retail_price', title: '零售价', width: 60, align: "center"},
                {field: 'batch_no', title: '批号', width: 60, align: "center"},
                {field: 'expire_date', title: '有效期', width: 90, align: "center"}

            ]],
            onDblClickRow: function (index, row) {
                initData(row, drugDict)
                $('#drugStockWindow').window('close')
            }
        })
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
                        loadDrugStockData(row)
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
                    min : 0
                }
            }
        }, {
            title: "单价",
            field: "price",
            width: 70,
            align: 'center',
            formatter:function(value,row){
                return row.retailPrice
            }
        }, {
            title: "批发价",
            field: "tradePrice",
            width: 70,
            align: 'center'
        }, {
            title: "当前结存",
            field: "stock",
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
                * (isNaN(row.retailPrice) ? 0 : +row.retailPrice)).toFixed(2)

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
            editor : {
                type: 'datebox',
                options: {
                    editable: false
                }
            },
            formatter: function(value){
                return parent.formatDatebox(value);
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
        singleSelect: false,
        remoteSort: false,
        idField: 'id',
        footer:'#fb',
        columns: [[
            {field: 'subStorage', title: '发放子库房', width: 150, halign: "center",align: "left",formatter:function(){
                return $('#stockSubDept').combobox('getText')
            }},
            //{field: 'itemNo', title: '序号', width: 120, halign: "center",align: "left"},
            {field: 'drugName', title: '药品', width: 150, halign: "center",align: "left"},
            {field: 'drugSpec', title: '规格', width: 100, align: "center"},
            {field: 'label', title: '单位', width: 70, align: "center"},
            {field: 'noProvideQuantity', title: '数量', width: 70, align: "center",formatter: function (value,row) {
                return row.flag == 0 ? row.quantity : value;
            }},
            {field: 'enterDateTime', title: '申请日期', width: 100, align: "center",formatter: function (value) {
                return parent.formatDatebox(value);
            }},
            {field: 'documentNo', title: '申请单号', width: 100, align: "center"},
            {field: 'batchNo', title: '批号', width: 100, align: "center"},
            {field: 'applicantStorage', title: '请领库房', width: 150, align: "center",formatter: function(){
                return $('#storageDept').combobox('getText');
            }},
            {field: 'applicantStorageSub', title: '请领子库房', width: 150, align: "center",formatter: function(){
                return $('#subStorageDept').combobox('getText');
            }}
        ]],
        onDblClickRow: function (index, row) {
            $.get(parent.basePath + '/drug-out/findDetailListWithStock',
                {orgId:currentOrgId,documentNo:row.documentNo,storage:row.storage,subStorage:row.subStorage}, function (res) {
                    $("#drug-import-batch").datagrid('loadData',res)
                })
            $('#importWindow').window('close')
        }
    })
    $('#allBtn').click(function(){
        $("#requestTable").datagrid('selectAll');
    })
    $('#noBtn').click(function(){
        $("#requestTable").datagrid('unselectAll');
    })
    $('#okBtn').click(function(){
        var selectRows = $("#requestTable").datagrid('getSelections');
        for(var i=0;i<selectRows.length;i++){
            var select = selectRows[i];
            var row = {
                id: select.id,
                documentNo: $('#documentNo').textbox('getValue'),
                drugCode: select.drugCode,
                drugSpec: select.drugSpec,
                units: select.label,
                batchNo: select.batchNo,
                //expireDate: new Date(),
                firmId: select.firmId,
                importDocumentNo: '',
                purchasePrice: select.retailPrice,
                retailPrice: select.retailPrice,
                packageSpec: select.packageSpec,
                quantity: select.flag == 0 ? select.quantity : select.noProvideQuantity,
                packageUnits: select.label,
                tradePrice: select.tradePrice,
                inventory: '',
                orgId: currentOrgId,
                supplier: select.supplierId,
                drugName: select.drugName,
                stock: select.stock,
                drugStockId: select.drugStockId
            }
            addRow( row)
        }
        $("#requestWindow").window('close');
    })
    $('#cancleBtn').click(function(){
        $("#requestWindow").window('close');
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

        var record = {
            documentNo: $('#documentNo').textbox('getValue'),
            orgId: currentOrgId,
            batchNo: ''
        }
        addRow(record)
    })
    $("#delBtn").on('click', function () {
        if (currentSelectIndex != undefined) {
            if(currentSelectIndex == $('#dg').datagrid('getRows').length - 1) return
            $("#dg").datagrid('deleteRow', currentSelectIndex);
            if($("#dg").datagrid('getRows').length == 1){
                $("#dg").datagrid('deleteRow', 0);
            }
            currentSelectIndex = undefined
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    })
    $("#saveBtn").on('click',function(){
        if (!endEditing()) return false;
        var rows = $('#dg').datagrid('getRows')
        var details = []
        if(rows.length > 0){
            if(!currentSubStorageDeptId) return
            for(var i=0;i<rows.length - 1 ;i++){
                if(!rows[i].quantity){
                    continue;
                }
                if(+rows[i].quantity > +rows[i].stock){
                    $.messager.alert('警告','出库数量大于当前结余，请修改！','warning')
                    $('#dg').datagrid('selectRow',i)
                    return false;
                }
                rows[i].itemNo = i + 1
                delete rows[i].price
                delete rows[i].outPrice
                delete rows[i].supplier
                delete rows[i].drugName
                details.push(rows[i]);
            }
            var record = {
                documentNo : $('#documentNo').textbox('getValue')
                ,storage : currentStorage
                ,exportDate : $('#calendar').datebox('getValue')
                ,receiver : $('#storageDept').combobox('getValue')
                ,accountReceivable : $('#accountReceivable').numberbox('getValue')
                ,accountPayed : $('#accountPayed').numberbox('getValue')
                ,additionalFee : $('#additionalFee').numberbox('getValue')
                ,exportClass : $('#statisticClass').combobox('getValue')
                ,subStorage : $('#stockSubDept').combobox('getValue')
                ,accountIndicator : currentAccountFlag ? currentAccountFlag : 0
                ,memos : $('#memos').textbox('getValue')
                ,operator : currentUsername
                ,subReceiver : $('#subStorageDept').combobox('getValue')
                ,orgId : currentOrgId
                ,detailList : details
                ,subStorageDeptId : currentSubStorageDeptId
            }
            parent.$.postJSON('/service/drug-out/saveAndUpdateRequest',JSON.stringify(record),function(res){
                if(res == '1'){
                    $.messager.alert('保存',(currentAccountFlag == '1' ? '保存并记账成功！' : '保存成功！'),'info',function(){
                        window.location.reload()
                    })
                } else {
                    $.messager.alert('保存','保存失败！','error')
                }
            })
        }
        else {
            $.messager.alert('保存','没有要保存的数据！','info')
        }
    })
    $('#printBtn').on('click',function(){

    })

    var query = function(){
        var applicantStorage = $('#storageDept').combobox('getValue');
        var applicantStorageSub = $('#subStorageDept').combobox('getValue');
        if(!applicantStorage || !applicantStorageSub){
            $("#requestTable").datagrid('loadData',[])
            $('#requestWindow').window('open')
        } else {
            $.get(parent.basePath + '/drugProvideApplication/findListWithPrice',
                {orgId:currentOrgId,applicantStorage:applicantStorage,
                    applicantStorageSub:applicantStorageSub,flag:'0,1',storage:currentStorage,
                    subStorage:$('#stockSubDept').combobox('getValue')},function(res){
                    $("#requestTable").datagrid('loadData',res)
                    $("#requestTable").datagrid('unselectAll')
                    $('#requestWindow').window('open')
                })
        }
    }
    // 添加一行
    var addRow = function(row){
        if(row){
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
            $("#dg").datagrid('insertRow',{index:currentSelectIndex,row: row});
            $("#dg").datagrid('selectRow', currentSelectIndex);
        }
    }
})