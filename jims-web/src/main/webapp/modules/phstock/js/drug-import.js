/**
 * 入库处理
 * @author yangruidong
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
    });
    $.extend($.fn.validatebox.defaults.rules, {
        hasSelected: {
            validator: function(value){
                var editor = $('#drug-import').datagrid('getEditor',{index:currentSelectIndex,field:'drugName'})
                if(editor && !$(editor.target).combogrid('grid').datagrid('getSelected')){
                    return false
                }
                return true
            },
            message: '没有选中项'
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

    var currentOrgId = config.org_Id;
    var currentStorage = parent.config.currentStorage
        ,currentUsername = '录入者'
    var accountFlag // 0，不记账，1记账
        ,currentSelectIndex = undefined
    var drugDicts = [] // 检索的药品字典数据
    var currentSubStorageDeptId // 当前选择的入库子单位的ID

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
     * 重新加载供货子单位、入库子单位的数据
     * @param id 组件Id
     * @param orgId 所属机构
     * @param storageCode  所属单位
     */
    var loadSubDept = function(id,orgId,storageCode){
        $.get('/service/drug-storage-dept/findSubList',{orgId : orgId,storageCode : storageCode},function(res){
            alert(res);
            $('#' + id).combobox('loadData',res)
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
        var editor = $('#drug-import').datagrid('getEditor',{index:currentSelectIndex,field:'drugName'})
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
        if ($('#drug-import').datagrid('validateRow', currentSelectIndex)) {
            $('#drug-import').datagrid('endEdit', currentSelectIndex);
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
            if (index == $('#drug-import').datagrid('getRows').length - 1) return
            $('#drug-import').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            currentSelectIndex = index;
        }
    }

    /**
     * 判断是否已存在此药品的规格
     * @param o {drugCode,packSpec,packUnit}
     */
    var chargeDrugExisted = function (o) {
        var _allData = $('#drug-import').datagrid('getRows')
        for (var i = 0, j = _allData.length - 1; i < j; i++) {
            if (i != currentSelectIndex && _allData[i].drugCode == o['drugCode']
                && _allData[i].drugSpec == o['packSpec'] && _allData[i].firmId == o['firmId']
                && _allData[i].units == o['packUnit']) {
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
        var editor = $('#drug-import').datagrid('getEditor', {index: currentSelectIndex, field: 'drugName'})
        $(editor.target).combogrid('setValue', oldV)
        $('#drug-import').datagrid('endEdit', currentSelectIndex)
    }

    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#drug-import').datagrid('getRows').length - 1
        $('#drug-import').datagrid('mergeCells', {index: _index, field: 'itemNo', rowspan: null, colspan: 10})
        $('#drug-import').datagrid('mergeCells', {index: _index, field: 'currentStock', rowspan: null, colspan: 6})
    }

    /**
     * 校验，不通过则会红色显示在需要编辑处
     * @param row 校验行数据
     * @returns {boolean} true 通过
     */
    var validateRow = function(row){
        var _index = $('#drug-import').datagrid('getRowIndex',row)
        if(!row.drugName){
            onClickCell(_index,'drugName')
            return false
        }
        if(!row.batchNo){
            onClickCell(_index,'batchNo')
            return false
        }
        if(isNaN(row.quantity) || row.quantity < 1){
            onClickCell(_index,'quantity')
            return false
        }
        return true
    }

    var addRow = function(){
        if(!$('#import').combobox('getValue')){
            $.messager.alert('警告','请先选择入库类别！','warning')
            return
        }
        if(!$('#supply').textbox('getValue')){
            $.messager.alert('警告','请先选择供货单位！','warning')
            return
        }
        var _documentNo = $('#importDocument').textbox('getValue')
        if(!$('#importDocument').textbox('getValue')) {
            $.messager.alert('警告','请先选择入库子单位！','warning')
            return
        }
        if(!endEditing()) return
        var len = $('#drug-import').datagrid('getRows').length
        if (len == 0) {
            var _countRecord = {
                itemNo: '合计',
                retailPriceCount:'0',
                purchasePriceCount:'0'
            }
            $('#drug-import').datagrid('appendRow', _countRecord)
            mergeLastCells()
        } else {
            len--
        }
        var _record = {
            documentNo : _documentNo,
            itemNo : len + 1,
            batchNo : 'X',
            expireDate : parent.formatDatebox(new Date()),
            discount : 100,
            quantity : '',
            orgId : currentOrgId
        }
        $('#drug-import').datagrid('insertRow',{index:len,row:_record})
        $("#drug-import").datagrid('selectRow', len)
        currentSelectIndex = len
    }
    var delRow = function(){
        var _row = $('#drug-import').datagrid('getSelected')
        if(_row){
            var _index = $('#drug-import').datagrid('getRowIndex',_row)
            var _rows = $('#drug-import').datagrid('getRows')
            if(_rows.length == _index + 1) return
            $('#drug-import').datagrid('deleteRow',_index)
            if(_rows.length == 1){
                $('#drug-import').datagrid('deleteRow',0)
                return
            }
            for(var len = _rows.length-1;_index<len;_index++){
                _rows[_index].itemNo = _index + 1
                $('#drug-import').datagrid('refreshRow', _index)
            }
        } else {
            $.messager.alert('警告','请选择要删除的药品！','warning')
            return
        }
    }
    var save = function(mod){
        if(currentSelectIndex != undefined)
            $('#drug-import').datagrid('endEdit',currentSelectIndex)
        var _rows = $('#drug-import').datagrid('getRows')
        if(_rows.length > 0){
            for(var i=0;i<_rows.length - 1 ;i++){
                if(!validateRow(_rows[i])){
                    return
                }
            }
            if(!currentSubStorageDeptId) return
            for(var i=0;i<_rows.length - 1 ;i++){
                delete _rows[i].purchasePriceCount
                delete _rows[i].retailPriceCount
                delete _rows[i].drugName
                delete _rows[i].supplier
            }
            var _record = {
                documentNo : $('#importDocument').textbox('getValue')
                ,storage : currentStorage
                ,importDate : $('#date').datebox('getValue')
                ,supplier : $('#supply').combobox('getValue')
                ,accountReceivable : $('#account').textbox('getValue')
                ,accountPayed : $('#paid').textbox('getValue')
                ,additionalFee : $('#surcharge').textbox('getValue')
                ,importClass : $('#import').combobox('getValue')
                ,subStorage : $('#importChild').combobox('getValue')
                ,accountIndicator : accountFlag ? accountFlag : 0
                ,memos : $('#remarks').textbox('getValue')
                ,operator : currentUsername
                ,subSupplier : $('#supplyChild').combobox('getValue')
                ,orgId : currentOrgId
                ,detailList : _rows.slice(0,_rows.length - 1)
                ,subStorageDeptId : currentSubStorageDeptId
            }
            parent.$.postJSON('/service/drug-in/save',JSON.stringify(_record),function(res){
                if(res == '1'){
                    $.messager.alert('保存',(accountFlag == '1' ? '保存并记账成功！' : '保存成功！'),'info',function(){
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
        $('#reportFrame').attr('src','http://localhost:8075/WebReport/ReportServer?reportlet=drugImport.cpt&documentNo='+$('#importDocument').textbox('getValue'))
    }

    /**
     * 初始化组件
     */
    var initComponent = function(){
        //入库类型以及选择事件
        $("#import").combobox({
            url: parent.basePath + '/drug-import/findAll',
            valueField: 'importClass',
            textField: 'importClass',
            method: 'GET',
            editable: false,
            width : 140,
            onSelect: function(o){
                accountFlag = o['accountFlag']
                $('#account').numberbox('setValue','0')
                $('#surcharge').numberbox('setValue','0')
                $('#paid').numberbox('setValue','0')
                $('#remarks').textbox('setValue','')
                $('#importDocument').textbox('setValue','')
                $('#drug-import').datagrid('loadData',[])
                $('#importChild').combobox('setValue','')
                if(o['importClass'] == '采购入库'){
                    $("#supplyChild").combobox({'disabled':true});
                    $.get('/service/drug-supplier-catalog/list',{orgId:currentOrgId},function(res){
                        $('#supply').combobox({
                            valueField : 'supplierCode',
                            textField : 'supplier',
                            data : res
                        })
                        $('#supply').combobox('addBlurListener')
                    })
                } else {
                    $("#supplyChild").combobox('loadData',[])
                    $("#supplyChild").combobox({
                        disabled:false,
                        value:''
                    })
                    $.get('/service/drug-storage-dept/list',{orgId:currentOrgId,storageType:(o['storageType'] == '全部'?'':o['storageType'])},function(res){
                        $('#supply').combobox({
                            valueField : 'storageCode',
                            textField : 'storageName',
                            data : res,
                            onSelect : function(r){
                                loadSubDept('supplyChild',currentOrgId,r['storageCode'])
                            }
                        })
                        $('#supply').combobox('addBlurListener')
                    })
                }
            }
        })
        $('#date').datebox({
            width: 140,
            editable: false,
            value: parent.formatDatebox(new Date())
        })
        $('#account').numberbox({
            width: 140,
            editable: true,
            value : '0',
            precision : 2
        })
        //入库子单位组件
        $('#importChild').combobox({
             valueField:'subStorageCode'
            ,textField:'subStorage'
            ,width:140
            ,editable: false
            ,onSelect:function(record){
                currentSubStorageDeptId = record['id']
                var _prefix = record['importNoPrefix']
                var _suffix = record['importNoAva']
                if(_prefix == undefined) _prefix = ''
                if(_suffix == undefined) _suffix = ''
                var _len = (_prefix + _suffix).length
                var _v = _len > 9 ? (_prefix + _suffix).substr(0,10) : _prefix + '0000000000'.substr(_len - 10) + _suffix
                $('#importDocument').textbox('setValue',_v)
            }
        })
        $('#operator').textbox({
            value : currentUsername,
            editable : false,
            width : 140
        })
        $('#newBtn').linkbutton({
            iconCls : 'icon-add',
            text : '新 单',
            onClick : function(){
                $('#drug-import').datagrid('loadData',[])
            }
        })
        $('#addBtn').linkbutton({
            iconCls : 'icon-edit',
            text : '插 入',
            onClick : addRow
        })
        $('#delBtn').linkbutton({
            iconCls : 'icon-remove',
            text : '删 除',
            onClick : delRow
        })
        $('#saveBtn').linkbutton({
            iconCls : 'icon-save',
            text : '保 存',
            onClick : save
        })
        $('#printBtn').linkbutton({
            iconCls : 'icon-print',
            text : '保存并打印',
            onClick : function(){
                //print()
                save('print')
            }
        })
        $('#cancelBtn').linkbutton({
            iconCls : 'icon-cancel',
            text : '关 闭',
            onClick : function(){
                parent.location.href = parent.getRootPath() + '/modules/index.html'
            }
        })
        //设置是否禁用控件
        $("#supplyChild").textbox({'disabled':true});
        $("#drug-import").datagrid({
            fit: true,
            fitColumns: true,
            striped: true,
            singleSelect: true,
            toolbar: '#tb',
            footer: '#fb',
            columns: [[{
                title: '行数',
                field: 'itemNo',
                width: 60,
                align: 'center',
                formatter : function(value){
                    if(isNaN(value)) return '<div style="text-align: left">　' + value + '</div>'
                    return value
                }
            }, {
                title: '代码',
                field: 'drugCode',
                width: 100,
                align: 'center'
            }, {
                title: "药名",
                field: "drugName",
                width: 220,
                halign: 'center',
                align: 'left',
                editor: {
                    type: 'combogrid',
                    options: {
                        panelWidth: 463,
                        idField: 'drugName',
                        textField: 'drugName',
                        required: true,
                        missingMessage: '药名不能为空',
                        fitColumns: true,
                        //validType:['hasSelected'],
                        url: '/service/drug-price/findDrugDictWithFilter?limit=50&orgId='+currentOrgId,
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
                }
            }, {
                title: "规格",
                field: "drugSpec",
                width: '60px',
                align: 'center'
            }, {
                title: "单位",
                field: "units",
                width: '60px',
                align: 'center'
            }, {
                title: "批号",
                field: "batchNo",
                width: '70px',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true,
                        missingMessage: '批号不能为空'
                    }
                }
            }, {
                title: "数量",
                field: "quantity",
                width: '60px',
                align: 'center',editor:{
                    type : 'numberbox',
                    options:{
                        required:true,
                        missingMessage:'数量不能为空',
                        min : 1,
                        precision : 0
                    }
                }
            }, {
                title: "扣率",
                field: "discount",
                width: '60px',
                align: 'center',editor:{
                    type : 'numberbox',
                    options:{
                        required:true,
                        missingMessage:'扣率不能为空',
                        min : 1,
                        max : 100,
                        precision : 0
                    }
                }
            }, {
                title: "进价",
                field: "purchasePrice",
                width: '70px',
                align: 'center'
            }, {
                title: "批价",
                field: "tradePrice",
                width: '70px',
                align: 'center'
            }, {
                title: "进价金额",
                field: "purchasePriceCount",
                width: '70px',
                align: 'center',
                formatter : function(value,row){
                    if(row.itemNo == '合计') return value
                    var _purchasePriceCount = (isNaN(row.purchasePriceCount) ? 0 : +row.purchasePriceCount)
                    var _value = ((isNaN(row.discount) ? 0 : +row.discount)
                    * (isNaN(row.quantity) ? 0 : +row.quantity)
                    * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice) / 100 ).toFixed(2)

                    row.purchasePriceCount = _value

                    var _allRow = $('#drug-import').datagrid('getRows')
                    var _lastRow = _allRow[_allRow.length - 1]
                    if(_lastRow.itemNo == '合计') {
                        _lastRow.purchasePriceCount = (+_lastRow.purchasePriceCount + +_value - _purchasePriceCount).toFixed(2)
                        $('#drug-import').datagrid('refreshRow', _allRow.length - 1)
                        $('#account').numberbox('setValue',_lastRow.purchasePriceCount)
                        mergeLastCells()
                    }
                    return _value
                }
            }, {
                title: "当前结存",
                field: "currentStock",
                width: '70px',
                align: 'center'
            }, {
                title: "厂家",
                field: "supplier",
                width: '200px',
                halign: 'center',
                align: 'left'
            }, {
                title: "有效期",
                field: "expireDate",
                width: '80px',
                align: 'center',
                editor : {
                    type: 'datebox',
                    options: {
                        editable: false
                    }
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
                width: '80px',
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
                width: '70px',
                align: 'center'
            }  , {
                title: "零价总额",
                field: "retailPriceCount",
                width: '70px',
                align: 'center',
                formatter : function(value,row){
                    if(row.itemNo == '合计') return value
                    var _retailPriceCount = (isNaN(row.retailPriceCount) ? 0 : +row.retailPriceCount)
                    var _value = ((isNaN(row.quantity) ? 0 : +row.quantity)
                    * (isNaN(row.retailPrice) ? 0 : +row.retailPrice)).toFixed(2)

                    row.retailPriceCount = _value

                    var _allRow = $('#drug-import').datagrid('getRows')
                    var _lastRow = _allRow[_allRow.length - 1]
                    if(_lastRow.itemNo == '合计') {
                        _lastRow.retailPriceCount = (+_lastRow.retailPriceCount + +_value - _retailPriceCount).toFixed(2)
                        $('#drug-import').datagrid('refreshRow', _allRow.length - 1)
                        mergeLastCells()
                    }
                    return _value
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
            onClickCell: onClickCell,
            onBeforeSelect: function(index){
                return $('#drug-import').datagrid('validateRow', currentSelectIndex)
            }
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
            showWindow(res, drugDict)
        }, 'GET', true)
    }

    /**
     * 展现药品价格表，当参数内只有一条数据时不显示，直接赋值
     * @param drugPrices 药品价格数据
     * @param drugDict 药品字典数据
     */
    var showWindow = function (drugPrices, drugDict) {
        var importTableRow = $('#drug-import').datagrid('getSelected')
        var _oldDrugName = importTableRow.drugName
        if (!drugPrices || drugPrices.length == 0) {
            rollBack(_oldDrugName)
            return
        }
        var _tempFlag = false   // 当window关闭时是否赋值

        var initData = function (drugPrice, drugDict) {
            var drugParam = {
                drugCode: drugPrice.drugCode
                , packSpec: drugPrice.drugSpec
                , packUnit: drugPrice.units
                , firmId: drugPrice.firmId
            }
            if (chargeDrugExisted(drugParam)) {
                $.messager.alert('警告', '该规格的药品已存在，请重新选择！', 'error')
                rollBack(_oldDrugName)
                return
            }
            importTableRow.drugCode = drugPrice.drugCode;
            importTableRow.drugSpec = drugPrice.drugSpec;
            importTableRow.units = drugPrice.units;
            importTableRow.packageSpec = drugPrice.drugSpec;
            importTableRow.packageUnits = drugPrice.units;
            importTableRow.firmId = drugPrice.firmId;
            importTableRow.supplier = drugPrice.supplier;

            importTableRow.retailPrice = drugPrice.retailPrice;
            importTableRow.tradePrice = drugPrice.tradePrice;
            importTableRow.purchasePrice = drugPrice.retailPrice;

            $('#drug-import').datagrid('endEdit', $('#drug-import').datagrid('getRowIndex', importTableRow))
            _tempFlag = true
        }

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
                    //rollBack(_oldDrugName)
                }
            }
        })
        $("#drugPriceTable").datagrid({
            fit: true,
            border: 0,
            fitColumns: true, //列自适应宽度
            singleSelect: true,
            remoteSort: false,
            data : drugPrices,
            idField: 'id',
            columns: [[
                {field: 'id', title: '编号', hidden: true},
                {field: 'drugSpec', title: '规格', width: 60, align: "center"},
                {field: 'units', title: '单位', width: 60, align: "center"},
                {field: 'supplier', title: '厂家', width: 200, halign: "center",align: "left"},
                {field: 'tradePrice', title: '批发价', width: 60, align: "center"},
                {field: 'retailPrice', title: '零售价', width: 60, align: "center"}
            ]],
            onDblClickRow: function (index, row) {
                initData(row, drugDict)
                $('#drugPriceWindow').window('close')
            }
        })
    }

    initComponent()
    loadSubDept('importChild',currentOrgId,currentStorage)
});




