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
    $.extend({
        ajaxAsync : function(url,data,callback,type,async){
            return $.ajax({
                type: type,
                url: url,
                async : async,   // true 异步,false 同步
                data: data,
                success: callback,
                'contentType': 'application/json'
            });
        }
    })
    $.extend($.fn.combobox.methods, {
        addBlurListener: function(jq,param){
            jq.next().children(':text').blur(function(){
                var v = jq.combobox('getValue')
                if(v != undefined && v != ''){
                    var rows = jq.combobox('getData')
                    if(rows.length > 0){
                        var valueField = jq.combobox('options').valueField
                        var textField = jq.combobox('options').textField
                        for(var i= 0,j=rows.length;i<j;i++){
                            if(rows[i][textField] && (rows[i][textField].toUpperCase().indexOf(v.toUpperCase()) > -1
                                || rows[i][valueField] == v)){
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

    var base_url = '/service/drug-buy-plan/'
    var username = '审核员'
        , orgId = '1'
        , currentBuyId = '' // 当前采购单据号
        , currentStorage = parent.config.currentStorage
        , suppliers = []  // 供应商数据

    var planSelectIndex = 0;   // 购买计划表当前选择行索引

    // 药品类别
    var drugIndicators = [
        {value: '1', label: '西药'},
        {value: '2', label: '中草药'},
        {value: '3', label: '中成药'},
        {value: '5', label: '辅料'},
        {value: '6', label: '试剂'},
        {value: '8', label: '材料'},
        {value: '9', label: '其他'}]

    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#buyPlanTable').datagrid('getRows').length - 1
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'buyNo', rowspan: null, colspan: 12})
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'checkMoney', rowspan: null, colspan: 8})
    }

    /**
     * 格式化数据
     * @param arr 数组格式类似 [{value:'1',label:'测试'}...]
     * @param value
     * @returns {*}
     */
    var format = function (arr, value) {
        if (arr) {
            for (var i = 0, len = arr.length; i < len; i++) {
                if (arr[i].value == value)
                    return arr[i].label
            }
        }
        return value
    }

    /**
     * 校验通过则结束编辑
     * @returns {boolean}
     */
    var endEditing = function () {
        if (planSelectIndex == undefined) {
            return true
        }
        var editor = $('#buyPlanTable').datagrid('getEditor',{index:planSelectIndex,field:'drugName'})
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
        if ($('#buyPlanTable').datagrid('validateRow', planSelectIndex)) {
            $('#buyPlanTable').datagrid('endEdit', planSelectIndex);
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
            if (index == $('#buyPlanTable').datagrid('getRows').length - 1) return
            if ($('#buyPlanTable').datagrid('getRows')[index].id){
                if(field == 'drugCode' || field == 'purchasePrice') return
            }
            $('#buyPlanTable').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            if(field == 'checkSupplier'){
                var editor = $('#buyPlanTable').datagrid('getEditor',{index: index, field: field});
                $(editor.target).combobox('addBlurListener')
            }
            planSelectIndex = index;
        }
    }

    /**
     * 初始化药品购买计划表
     */
    var initBuyPlanTable = function () {
        $("#buyPlanTable").datagrid({
            fit: true,
            border: 0,
            striped: true,
            singleSelect: true,
            remoteSort: false,
            idField: 'id',
            toolbar: '#tbn',
            columns: [[
                {field: 'id', title: '编号', hidden: true},
                {field: 'buyNo', title: '采购序号', width: 60, align: "center", formatter: function (value) {
                    if (value == '审核金额合计') return '<div style="text-align:right">' + value + '：　　　</div>'
                    return value
                }},
                {field: 'drugName', title: '药名', width: 220, halign: "center",align: "left", editor: {
                    type: 'combogrid',
                    options: {
                        panelWidth: 463,
                        idField: 'drugName',
                        textField: 'drugName',
                        required: true,
                        missingMessage: '药名不能为空',
                        fitColumns: true,
                        url : '/service/drug-price/findDrugDictWithFilter?limit=50&orgId='+orgId,
                        method:'get',
                        mode:'remote',
                        columns: [[
                            {field: 'drugCode', title: '药品代码', width: 100, align: "center"},
                            {field: 'drugName',
                                title: '药品名称',
                                width: 160,
                                halign: "center",
                                align: "left"
                            },
                            {field: 'inputCode', title: '输入码', width: 70, align: "center"},
                            {field: 'toxiProperty', title: '毒理分类', width: 70, align: "center"},
                            {field: 'drugIndicator', title: '药品类别', width: 60, align: "center",
                                formatter: function (value) {
                                    return format(drugIndicators, value)
                                }
                            }
                        ]],
                        onSelect: function (index, row) {
                            loadDrugPriceData(row)
                        }
                    }
                }},
                {field: 'checkSupplier', title: '审核供应商', width: 200, halign: "center", align: "left", editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'id',
                        textField: 'supplier',
                        required: true,
                        missingMessage: '审核供应商不能为空',
                        data: suppliers
                    }
                },formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return '<div style="text-align: left">'+suppliers[i].supplier+'</div>'
                        }
                    }
                    return ''
                }},
                {field: 'supplier', title: '生成厂家', width: 200, halign: "center", align: "left"},
                {field: 'checkNumber', title: '审核数量', width: 60, align: "center",editor:{
                    type : 'numberbox',
                    options:{
                        required:true,
                        missingMessage:'审核数量不能为空',
                        min : 1,
                        precision : 0
                    }
                }},
                {field: 'checker', title: '审核人', width: 70, align: "center"},
                {field: 'stockSupplier', title: '供应商', width: 200, halign: "center", align: "left",formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) return suppliers[i].supplier
                    }
                    return ''
                }},
                {field: 'storer', title: '仓管员', width: 70, align: "center"},
                {field: 'purchasePrice', title: '进货价', width: 60, align: "center",editor:{
                    type : 'numberbox',
                    options:{
                        required:true,
                        missingMessage:'进货价不能为空',
                        min : 1.0,
                        precision : 1
                    }}},
                {field: 'monthUsed', title: '月消耗量', width: 60, align: "center"},
                {field: 'stockNum', title: '库存参考数', width: 90, align: "center"},
                {field: 'packSpec', title: '包装规格', width: 60, align: "center"},
                {field: 'checkMoney', title: '审核金额', width: 60, align: "center",formatter:function(value,row,index){
                    if(row.buyNo == '审核金额合计') return '<div style="text-align:left">　　'+value+'</div>'
                    var _checkMoney = (isNaN(row.checkMoney) ? 0 : +row.checkMoney)
                    var _value = ((isNaN(row.checkNumber) ? 0 : +row.checkNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                    row.checkMoney = _value

                    var _allRow = $('#buyPlanTable').datagrid('getRows')
                    var _lastRow = _allRow[_allRow.length - 1]
                    if(_lastRow.buyNo == '审核金额合计') {
                        _lastRow.checkMoney = (+_lastRow.checkMoney + +_value - _checkMoney).toFixed(1)
                        $('#buyPlanTable').datagrid('refreshRow', _allRow.length - 1)
                        mergeLastCells()
                    }
                    return _value
                }},
                {field: 'buyer', title: '采购员', width: 70, align: "center"},
                {field: 'stockMoney', title: '采购金额', width: 60, align: "center",formatter:function(value,row,index){
                    return ((isNaN(row.stockNumber) ? 0 : +row.stockNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'count', title: '计划金额', width: 60, align: "center", formatter: function (value, row, index) {
                    var _value = ((isNaN(row.wantNumber) ? 0 : +row.wantNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                    return _value
                }},
                {field: 'stockNumber', title: '采购数量', width: 60, align: "center"},
                {field: 'packUnit', title: '包装单位', width: 60, align: "center"},
                {field: 'wantNumber', title: '计划数量', width: 60, align: "center"},
                {field: 'drugForm', title: '剂型', width: 80, align: "center"}
            ]],
            onClickCell: onClickCell,
            onLoadSuccess: function (data) {
                var rows = $(this).datagrid('getRows')
                if (rows.length == 0) return
                var countRecord = {
                    buyNo: '审核金额合计',
                    checkMoney: 0
                }
                $(this).datagrid('appendRow', countRecord)
                mergeLastCells()
            },
            onBeforeSelect: function(index){
                return $('#buyPlanTable').datagrid('validateRow', planSelectIndex)
            }
        });

    }

    /**
     * 初始化按钮等
     */
    var initBtn = function () {
        $('#temporaryNo').combobox({
            valueField: '0',
            textField: '0',
            editable: false,
            url:base_url+'getBuyId?flag=3&orgId='+orgId,
            method:'get',
            mode:'remote',
            onSelect: function (record) {
                planSelectIndex = 0
                loadDrugBuyPlan(record[0], '3')
            }
        })

        $('#addButton').linkbutton({
            iconCls: 'icon-add',
            text: '增加',
            onClick: addRow
        })
        $('#delButton').linkbutton({
            iconCls: 'icon-remove',
            text: '删除',
            onClick: delRow
        })
        $('#saveButton').linkbutton({
            iconCls: 'icon-save',
            text: '保存',
            onClick: saveData
        })
        $('#flushButton').linkbutton({
            iconCls: 'icon-reload',
            text: '刷新',
            onClick: function () {
                window.location.reload();
            }
        })
        $('#closeButton').linkbutton({
            iconCls: 'icon-cancel',
            text: '关闭',
            onClick: function () {
                parent.location.href = parent.getRootPath() + '/modules/clinic/index.html'
            }
        })
    }

    /**
     * 判断是否已存在此药品的规格
     * @param o
     */
    var chargeDrugExisted = function (o) {
        var _allData = $('#buyPlanTable').datagrid('getRows')
        for (var i = 0, j = _allData.length - 1; i < j; i++) {
            if (i != planSelectIndex && _allData[i].drugCode == o.drugCode
                && _allData[i].packSpec == o.packSpec
                && _allData[i].firmId == o.firmId && _allData[i].packUnit == o.packUnit) {
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
        var editor = $('#buyPlanTable').datagrid('getEditor', {index: planSelectIndex, field: 'drugName'})
        $(editor.target).combogrid('setValue', oldV)
        $('#buyPlanTable').datagrid('endEdit', planSelectIndex)
    }

    /**
     * 展现药品价格表，当参数内只有一条数据时不显示，直接赋值
     * @param drugPrices 药品价格数据
     * @param drugDict 药品字典数据
     */
    var showDrugPriceWindow = function (drugPrices, drugDict) {
        if (!drugPrices) return
        var _buyPlanTableRow = $('#buyPlanTable').datagrid('getSelected')
        var _oldDrugCode = _buyPlanTableRow.drugCode

        var initData = function (drugPrice, drugDict) {
            var _o = {
                drugCode: drugPrice.drugCode
                , packSpec: drugPrice.drugSpec
                , firmId: drugPrice.firmId
                , packUnit: drugPrice.units
            }
            if (chargeDrugExisted(_o)) {
                $.messager.alert('警告', '该规格的药品已存在，请重新选择！', 'error')
                rollBack(_oldDrugCode)
                return
            }
            _buyPlanTableRow.drugName = drugDict.drugName
            _buyPlanTableRow.drugCode = drugDict.drugCode
            _buyPlanTableRow.drugSpec = drugPrice.drugSpec
            _buyPlanTableRow.units = drugPrice.units
            _buyPlanTableRow.packSpec = drugPrice.drugSpec
            _buyPlanTableRow.packUnit = drugPrice.units
            _buyPlanTableRow.firmId = drugPrice.firmId
            _buyPlanTableRow.supplier = drugPrice.supplier

            _buyPlanTableRow.dosePerUnit = drugDict.dosePerUnit
            _buyPlanTableRow.doseUnits = drugDict.doseUnits
            _buyPlanTableRow.drugForm = drugDict.drugForm
            _buyPlanTableRow.toxiProperty = drugDict.toxiProperty
            _buyPlanTableRow.drugIndicator = drugDict.drugIndicator
            _buyPlanTableRow.inputCode = drugDict.inputCode

            $('#buyPlanTable').datagrid('endEdit', planSelectIndex)
            _tempFlag = true
        }
        var _tempFlag = false
        if (drugPrices.length == 1) {
            initData(drugPrices[0], drugDict)
            return
        }
        $('#drugPriceWindow').window({
            title: '选择药品规格和单位',
            width: '550',
            height: '450',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            modal: true,
            resizable: false,
            onClose: function () {
                if (!_tempFlag) {
                    rollBack(_oldDrugCode)
                }
            }
        })
        $("#drugPriceTable").datagrid({
            fit: true,
            border: 0,
            fitColumns: true, //列自适应宽度
            singleSelect: true,
            remoteSort: false,
            idField: 'id',
            data: drugPrices,
            columns: [[
                {field: 'id', title: '编号', hidden: true},
                {field: 'drugSpec', title: '规格', width: 60, align: "center"},
                {field: 'units', title: '单位', width: 60, align: "center"},
                {field: 'firmId', title: '厂家主键', hidden: true},
                {field: 'supplier', title: '厂家', width: 200, align: "center"},
                {field: 'tradePrice', title: '批发价', width: 60, align: "center"},
                {field: 'retailPrice', title: '零售价', width: 60, align: "center"},
                {field: 'minSpec', hidden: true},
                {field: 'minUnits', hidden: true},
            ]],
            onDblClickRow: function (index, row) {
                initData(row, drugDict)
                $('#drugPriceWindow').window('close')
            }
        })
    }

    /**
     * 加载指定购买单据号的数据
     * @param buyId
     * @param flag
     */
    var loadDrugBuyPlan = function (buyId, flag) {
        $.get(base_url + 'findList', {buyId: buyId, orgId: orgId, flag: flag}, function (res) {
            currentBuyId = buyId
            for(var i=0;i<res.length;i++){
                res[i].checker = username
                res[i].flag = '4'
            }
            $('#buyPlanTable').datagrid('loadData', res)
            $('#buyPlanTable').datagrid('selectRow', planSelectIndex)
        })
    }

    /**
     * 加载同一药品的不同规格、厂商等
     * @param drugDict
     */
    var loadDrugPriceData = function (drugDict) {
        $.ajaxAsync('/service/drug-price/findList', {
            orgId: drugDict.orgId,
            drugCode: drugDict.drugCode
        }, function (res) {
            showDrugPriceWindow(res, drugDict)
        }, 'GET', false)
    }

    /**
     * 加载厂商
     * @param orgId
     * @param supplierClass
     */
    var loadSupplier = function(orgId,supplierClass){
        $.ajaxAsync('/service/drug-supplier-catalog/list-supplier-type', {
            orgId: orgId,
            supplierClass: supplierClass
        }, function (res) {
            suppliers = res
        }, 'GET', false)
    }

    /**
     * 校验，不通过则会红色显示在需要编辑处
     * @param row 校验行数据
     * @returns {boolean} true 通过
     */
    var validateRow = function (row) {
        var _index = $('#buyPlanTable').datagrid('getRowIndex', row)
        if (!row.drugName) {
            onClickCell(_index, 'drugName')
            return false
        }
        if (!row.checkSupplier) {
            onClickCell(_index, 'checkSupplier')
            return false
        }
        if (!row.checkNumber) {
            onClickCell(_index, 'checkNumber')
            return false
        }
        if (isNaN(row.purchasePrice)) {
            onClickCell(_index, 'purchasePrice')
            return false
        }
        return true
    }

    /**
     * 添加行
     */
    var addRow = function () {
        var len = $('#buyPlanTable').datagrid('getRows').length
        if (!currentBuyId){
            $.messager.alert('警告','请选择需审核的采购单据号！','warning')
            return
        }
        if (len == 0) {
            var countRecord = {
                buyNo: '审核金额合计',
                checkMoney: '0'
            }
            $('#buyPlanTable').datagrid('appendRow', countRecord)
            mergeLastCells()
        } else {
            len--
        }
        var record = {
            buyId: currentBuyId,  // 后台生成
            buyNo: len + 1,
            checker: username,
            orgId: orgId,
            flag: '4',
            wantNumber:'0',
            stockNumber:'0',
            storage: currentStorage,
            supplier: ''
        }

        $('#buyPlanTable').datagrid('insertRow', {index: len, row: record})
        if (endEditing()) {
            $('#buyPlanTable').datagrid('selectRow', len)
            planSelectIndex = len
        }
    }
    /**
     * 删除行
     */
    var delRow = function () {
        var _row = $('#buyPlanTable').datagrid('getSelected')
        if (_row) {
            var _index = $('#buyPlanTable').datagrid('getRowIndex', _row)
            $('#buyPlanTable').datagrid('deleteRow', _index)
            planSelectIndex = undefined
            var _rows = $('#buyPlanTable').datagrid('getRows')
            if (_rows.length == 1) {
                $('#buyPlanTable').datagrid('deleteRow', 0)
            }
            //更改购买序号
            for (var len = _rows.length - 1; _index < len; _index++) {
                _rows[_index].buyNo = _index + 1
                $('#buyPlanTable').datagrid('refreshRow', _index)
            }
        } else {
            $.messager.alert('警告', '请选择要删除的药品！', 'warning')
        }
    }
    /**
     * 保存数据
     */
    var saveData = function () {
        if (!currentBuyId){
            $.messager.alert('警告','没有需要保存的数据！','warning')
            return
        }

        if (planSelectIndex != undefined)
            $('#buyPlanTable').datagrid('endEdit', planSelectIndex)

        var handleData = [[]] // handleData[0] 保存的数据,handleData[1] 删除的数据
        var _allData = $('#buyPlanTable').datagrid('getRows')
        for (var i = 0; i < _allData.length - 1; i++) {
            if (!validateRow(_allData[i])) return
            delete _allData[i].checkMoney
            delete _allData[i].stockMoney
            delete _allData[i].count
            handleData[0].push(_allData[i])
        }
        // 删除的数据，只传递需删除的数据的主键（ID）
        var _deleteData = $('#buyPlanTable').datagrid('getChanges', 'deleted')
        var _ids = ''
        for (var i = 0; i < _deleteData.length; i++) {
            _ids += ',' + _deleteData[i].id
        }
        var _dels = []
        if (_ids) {
            _dels.push({delFlag: '1', id: _ids.substr(1)})
        }
        handleData.push(_dels)
        parent.$.postJSON(base_url + 'saveBatch', JSON.stringify(handleData), function (res) {
            if (res.code = '0')
                $.messager.alert('成功', '保存成功', 'info', function () {
                    window.location.reload()
                })
        })
    }

    var init = function () {
        loadSupplier(orgId,'供应商')

        initBuyPlanTable()
        initBtn()
    }

    init()
})
