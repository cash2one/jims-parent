/**
 * 出库处理
 * @author luohk
 * @version 2016-05-14
 */
$(function () {
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

    var currentSelectIndex   // datagrid 当前选择的行索引
        ,currentOrgId = config.org_Id  // 当前登录人所属单位ID
        ,currentStorage = config.currentStorage  // 当前登录人所属管理单位
        ,currentUsername = '录入者'   // 当前登录人姓名
        ,currentAccountFlag    //记账标志 0，不记账，1记账
        ,currentToLevel;  //去向
    var currentSubStorageDeptId // 当前选择的入库子单位的ID

    /**
     * 重新加载收货子单位、库房子单位的数据
     * @param id 组件Id
     * @param orgId 所属机构
     * @param storageCode  所属单位
     */
    var loadSubDept = function(id,orgId,storageCode){
        $.get('/service/drug-storage-dept/findSubList',{orgId : orgId,storageCode : storageCode},function(res){
            $('#' + id).combobox('loadData',res)
        })
    }


    var specUnits = [];//规格单位字典
    $.get("/service/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });

    var drugToxi = [];//毒理属性字典
    $.get( "/service/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT", function (data) {
        drugToxi = data;
    });
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
                showWindow(res)
            })
    }

    /**
     * 判断是否已存在此药品的规格
     * @param o {drugCode,packSpec,packUnit}
     */
    var chargeDrugExisted = function (o) {
        var rows = $('#dg').datagrid('getRows')
        for (var i = 0, j = rows.length - 1; i < j; i++) {
            if (i != currentSelectIndex && rows[i].drugCode == o['drug_code']
                && rows[i].firmId == o['firm_id'] && rows[i].drugSpec == o['drug_spec']
                && rows[i].units == o['units']
                && rows[i].batchNo == o['batch_no']
                && rows[i].packageSpec == o['package_spec']
                && rows[i].packageUnits == o['package_units']) {
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
     * 校验，不通过则会红色显示在需要编辑处
     * @param row 校验行数据
     * @returns {boolean} true 通过
     */
    var validateRow = function(row){
        var index = $('#dg').datagrid('getRowIndex',row)
        if(row.drugName == ''){
            $.messager.alert('警告','药品名称不能为空！','error')
            onClickCell(index,'drugName')
            return false
        }
        if(isNaN(row.quantity) || +row.quantity < 1 || +row.quantity > +row.currentStock){
            $.messager.alert('警告','药品入库数量必须大于零且小于当前结存！','error')
            onClickCell(index,'quantity')
            return false
        }
        if($('#statisticClass').combobox('getValue') == '退药出库' && (isNaN(row.price) || +row.price < 0)){
            $.messager.alert('警告','药品单价不能小于零！','error')
            onClickCell(index,'price')
            return false
        }
        return true
    }

    /**
     * 展现药品库存数据，当库存参数内只有一条数据时不显示，直接赋值。当一条也没有时清空药品名称。
     * @param drugStocks 药品库存数据
     */
    var showWindow = function (drugStocks) {
        var exportRow = $('#dg').datagrid('getSelected')
        var _oldDrugName = exportRow.drugName
        if (!drugStocks || drugStocks.length == 0) {
            rollBack(_oldDrugName)
            return
        }
        var _tempFlag = false   // 当window关闭时是否赋值
        var initData = function (drugStock) {
            if (chargeDrugExisted(drugStock)) {
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
            exportRow.price = currentToLevel == '4' ?
                drugStock['trade_price'] : drugStock['retail_price'];
            exportRow.packageSpec = drugStock['package_spec']
            exportRow.packageUnits = drugStock['package_units']
            exportRow.subPackage1 = drugStock['sub_package_1']
            exportRow.subPackageUnits1 = drugStock['sub_package_units_1']
            exportRow.subPackageSpec1 = drugStock['sub_package_spec_1']
            exportRow.subPackage2 = drugStock['sub_package_2']
            exportRow.subPackageUnits2 = drugStock['sub_package_units_2']
            exportRow.subPackageSpec2 = drugStock['sub_package_spec_2']
            exportRow.purchasePrice = drugStock['purchase_price']
            exportRow.currentStock = drugStock['quantity']
            exportRow.drugStockId = drugStock['id']
            if(isNaN(exportRow.quantity) || +exportRow.quantity < 1 || +exportRow.quantity > +exportRow.currentStock){
                onClickCell(currentSelectIndex,'quantity')
            } else {
                $('#dg').datagrid('endEdit', currentSelectIndex)
            }
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
                initData(row)
                $('#drugStockWindow').window('close')
            }
        })
    }

    /**
     * 校验通过则结束编辑
     * @returns {boolean}
     */
    var endEditing = function () {
        if (currentSelectIndex == undefined) {
            return true
        }
        var editor = $('#dg').datagrid('getEditor',{index:currentSelectIndex,field:'drugName'})
        if(editor){
            var rows = $(editor.target).combogrid('grid').datagrid('getRows');
            if(rows.length > 0 && $(editor.target).combogrid('getValue')){
                if(!$(editor.target).combogrid('grid').datagrid('getSelected')){
                    //$(editor.target).combogrid('grid').datagrid('selectRow',0)
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

    /**
     * 点击datagrid单元格事件
     * @param index
     * @param field
     */
    var onClickCell = function (index, field) {
        if (endEditing()) {
            currentSelectIndex = index;
            if (index == $('#dg').datagrid('getRows').length - 1 ||
                (field == 'price' && $('#statisticClass').combobox('getValue') != '退药出库')) {
                return
            }
            $('#dg').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            if(field == 'drugName'){
                var editor = $('#dg').datagrid('getEditor',{index:index,field:field});
                $(editor.target).combogrid('grid').datagrid({
                    url:'/service/drug-stock/findListHasStock?limit=50&orgId='+currentOrgId+'&supplyIndicator=1&storage='+currentStorage+'&subStorage='+$('#stockSubDept').combobox('getValue')
                })
            }
        }
    }

    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#dg').datagrid('getRows').length - 1
        $('#dg').datagrid('mergeCells', {index: _index, field: 'drugCode', rowspan: null, colspan: 9})
        $('#dg').datagrid('mergeCells', {index: _index, field: 'batchNo', rowspan: null, colspan: 3})
    }

    var save = function (mod) {
        if (!endEditing()) return false;
        var rows = $('#dg').datagrid('getRows')
        if(rows.length > 0){
            for(var i=0;i<rows.length - 1 ;i++){
                if(!validateRow(rows[i])){
                    return
                }
            }
            if(!currentSubStorageDeptId) return
            for(var i=0;i<rows.length - 1 ;i++){
                rows[i].itemNo = i + 1
                rows[i].purchasePrice = rows[i].price
                delete rows[i].price
                delete rows[i].outPrice
                delete rows[i].supplier
                delete rows[i].currentStock
                delete rows[i].drugName
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
                ,detailList : rows.slice(0,rows.length - 1)
                ,subStorageDeptId : currentSubStorageDeptId
            }
            if(currentAccountFlag) {
                record.acctDate = new Date()
                record.acctOperator = config.username
            }
            parent.$.postJSON('/service/drug-out/save',JSON.stringify(record),function(res){
                if(res == '1'){
                    $.messager.alert('保存',(currentAccountFlag == '1' ? '保存并记账成功！' : '保存成功！'),'info',function(){
                        if(mod == 'print'){
                            print()
                        } else {
                            window.location.reload()
                        }
                    })
                } else {
                    $.messager.alert('保存','保存失败！','error')
                }
            })
        }
        else {
            $.messager.alert('保存','没有要保存的数据！','info')
        }
    }
    var print = function(){
        $('#printWindow').window({
            title: '打印',
            width: '600',
            height: '450',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            modal: true,
            onClose : function () {
                window.location.reload()
            }
        })
        $('#reportFrame').attr('src','http://localhost:8075/WebReport/ReportServer?reportlet=drugExport.cpt&documentNo='+$('#documentNo').textbox('getValue')+'&orgId='+currentOrgId+'&storage='+currentStorage)
    }

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
                    fitColumns: true,
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
            align: 'center',
                formatter:function(value,row,index){
                    var unitsName = value;
                    console.log(specUnits);
                    $.each(specUnits, function (index,item) {
                        if(item.value == value){
                            unitsName =  item.label;
                        }
                    });
                    return unitsName;
                }
        }, {
            title: "数量",
            field: "quantity",
            width: 70,
            align: 'center',
            editor: {
                type: 'numberbox', options: {
                    min : 0
                }
            }
        }, {
            title: "单价",
            field: "price",
            width: 70,
            align: 'center',
            editor: {
                type: 'numberbox', options: {
                    min : 0,
                    precision:2
                }
            }
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
            title: "厂家",
            field: "supplier",
            width: 200,
            align: 'left',
            halign: 'center'
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
    // 出库类别
    $('#statisticClass').combobox({
        url: parent.basePath + '/drug-export/findList?storageType='+config.currentStorageObj.storageType,
        valueField: 'exportClass',
        textField: 'exportClass',
        editable: false,
        method: 'GET',
        onSelect: function(record){
            currentAccountFlag = record['accountFlag']
            currentToLevel = record['toLevel']
            $("#dg").datagrid('loadData',[])
            if(record['toLevel'] == '4'){
                $("#subStorageDept").combobox({
                    disabled:true,
                    value: '*'
                });
                $('#storageDept').combobox({
                    valueField : 'id',
                    textField : 'supplier',
                    url: '/service/drug-supplier-catalog/findListWithFilter?orgId='+currentOrgId,
                    method:'get',
                    mode:'remote',
                    onSelect: function(){
                        $("#dg").datagrid('loadData',[])
                    }
                })
                $('#storageDept').combobox('addBlurListener')
            } else {
                $("#subStorageDept").combobox('loadData',[])
                $("#subStorageDept").combobox({
                    disabled: false,
                    value: '',
                    onSelect: function(){
                        $("#dg").datagrid('loadData',[])
                    }
                })
                var url = '/service/drug-storage-dept/findListByLevel?orgId='+currentOrgId;
                switch(record.toLevel){
                    case '1' :
                        url += '&condition=remarks<\''+ config.currentStorageObj.level+'\'';
                        break;
                    case '2' :
                        url += '&condition=remarks=\''+ config.currentStorageObj.level+'\'';
                        break;
                    case '3' :
                        url += '&condition=remarks>\''+ config.currentStorageObj.level+'\'';
                        break;
                }
                $('#storageDept').combobox({
                    valueField : 'storageCode',
                    textField : 'storageName',
                    url: url,
                    method:'get',
                    mode:'remote',
                    loadFilter: function(data){
                        if(record.toLevel == '2' && data){
                            for(var i=0;i<data.length;i++){
                                if(data[i].storageCode == config.currentStorageObj.storageCode){
                                    data.splice(i,1);
                                    break;
                                }
                            }
                        }
                        return data
                    },
                    onSelect : function(r){
                        $("#dg").datagrid('loadData',[])
                        loadSubDept('subStorageDept',currentOrgId,r['storageCode'])
                    }
                })
                $('#storageDept').combobox('addBlurListener')
            }

        }
    })
    //日期
    $('#calendar').datebox({
        width: 140,
        editable: false,
        value: parent.formatDatebox(new Date())
    })

    //库房子单位
    $('#stockSubDept').combobox({
        valueField:'subStorageCode',
        textField:'subStorage',
        width:160,
        editable: false,
        onSelect:function(record){
            $("#dg").datagrid('loadData',[])
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

    $('#newBtn').on('click',function(){
        $('#dg').datagrid('loadData',[]);
        $('#statisticClass').combobox('clear')
        $('#calendar').datebox('setValue',parent.formatDatebox(new Date()))
        $('#storageDept').combobox('clear')
        $('#storageDept').combobox('loadData',[])
        $('#stockSubDept').combobox('clear')
        $('#subStorageDept').combobox('clear')
        $('#subStorageDept').combobox('loadData',[])
        $('#accountReceivable').numberbox('setValue',0)
        $('#accountPayed').numberbox('setValue',0)
        $('#additionalFee').numberbox('setValue',0)
        $('#memos').textbox('clear')
        $('#documentNo').textbox('clear')
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
            quantity : '',
            batchNo: ''
        }

        $("#dg").datagrid('insertRow',{index:currentSelectIndex,row: record});
        $("#dg").datagrid('selectRow', currentSelectIndex);
        onClickCell(currentSelectIndex,'drugName')
    })
    $("#delBtn").on('click', function () {
        if (currentSelectIndex != undefined) {
            if(currentSelectIndex == $('#dg').datagrid('getRows').length - 1) return
            $("#dg").datagrid('deleteRow', currentSelectIndex);
            if($("#dg").datagrid('getRows').length == 1){
                $("#dg").datagrid('deleteRow', 0);
            }
            currentSelectIndex = undefined
            //更新出库金额合计与应收款数据
            var newRows = $("#dg").datagrid('getRows');
            var outPrice = 0;
            for(var i=0;i<newRows.length;i++){
                if(newRows[i].drugCode != '出库金额合计'){
                    outPrice += parseFloat(newRows[i].outPrice);
                }
            }
            newRows[newRows.length-1].outPrice = outPrice;
            $("#accountReceivable").numberbox('setValue', newRows[newRows.length - 1].outPrice);
            $("#dg").datagrid('refreshRow',newRows.length-1);
            mergeLastCells()
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

    //设置是否禁用控件
    $("#subStorageDept").textbox({'disabled':true});
    loadSubDept('stockSubDept',currentOrgId,currentStorage);
});
