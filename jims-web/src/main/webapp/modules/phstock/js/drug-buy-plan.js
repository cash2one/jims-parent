$(function(){
    var base_url = '/service/drug-buy-plan/'
    var username  = '仓管员'
        ,orgId = parent.config.org_Id
        ,currentBuyId = ''
        ,currentStorage = parent.config.currentStorage
        ,drugDicts = []  // 检索的暂存采购计划单
        ,initDrugPriceWindowFlag = true

    /**
     * 初始化药品购买计划表
     */
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
                { field: 'buyNo', title: '采购序号', width: 60,align : "center"},
                { field: 'drugName', title: '药名', width: 220,align : "center",editor:{
                    type:'combogrid',
                    options:{
                        panelWidth:443,
                        idField:'drugCode',
                        textField:'drugName',
                        required:true,
                        missingMessage:'药名不能为空',
                        fitColumns: true,
                        data : drugDicts,
                        columns:[[
                            {field:'drugCode',title:'药品代码',width:100,align : "center"},
                            {field:'drugName',title:'药品名称',width:160,align : "center",formatter:function(value){
                                return '<div style="text-align:left">'+value+'</div>'
                            }},
                            {field:'inputCode',title:'输入码',width:70,align : "center"},
                            {field:'drugForm',title:'剂型',width:50,align : "center"},
                            {field:'drugIndicator',title:'药品类别',width:60,align : "center"}
                        ]],
                        onChange:function(newV,oldV){
                            if(newV != oldV)return
                        },
                        filter: function(field, row){
                            if(row.drugCode.toUpperCase().indexOf(field.toUpperCase()) > -1
                                || row.drugName.toUpperCase().indexOf(field.toUpperCase()) > -1){
                                return true
                            }
                        },
                        onClickRow:function(index,row){
                            loadDrugPriceData(row)
                        }
                    }
                },formatter:function(value){
                    if(value == undefined || value == '') return ''
                    for(var i= 0,j= (drugDicts ? drugDicts.length : 0 );i<j;i++){
                        if(drugDicts[i].drugCode == value){
                            value = drugDicts[i].drugName
                            break
                        }
                    }
                    return '<div style="text-align:left">'+value+'</div>';
                }},
                { field: 'drugSpec', title: '包装规格', width: 60,align : "center" },
                { field: 'units', title: '包装单位', width: 60,align : "center" },
                { field: 'firmId', title: '厂家', width: 200,align : "center" },
                { field: 'wantNumber', title: '计划数量', width: 60,align : "center",editor:{
                    type : 'numberbox',
                    options:{
                        required:true,
                        missingMessage:'计划数量不能为空',
                        min : 1,
                        precision : 0,
                        onChange : function(newV,oldV){
                            if(newV != oldV){
                                var _row = $('#buyPlanTable').datagrid('getSelected')
                                _row.count = +newV * (isNaN(_row.purchasePrice) ? 0 : +_row.purchasePrice)
                            }
                        }
                    }
                }},
                { field: 'purchasePrice', title: '进货价', width: 60,align : "center" ,editor:{
                    type : 'numberbox',
                    options:{
                        required:true,
                        missingMessage:'进货价不能为空',
                        min : 1.0,
                        precision : 1,
                        onChange : function(newV,oldV){
                            if(newV != oldV){
                                var _row = $('#buyPlanTable').datagrid('getSelected')
                                _row.count = +newV * (isNaN(_row.wantNumber) ? 0 : +_row.wantNumber)
                            }
                        }
                    }
                }},
                { field: 'count', title: '金额', width: 60,align : "center" ,formatter:function(value,row,index){
                    return (isNaN(row.wantNumber) ? 0 : +row.wantNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)
                }},
                { field: 'drugForm', title: '剂型', width: 80,align : "center" },
                { field: 'toxiProperty', title: '毒理', width: 150,align : "center" },
                { field: 'storer', title: '仓管员', width: 70,align : "center" },
                { field: 'stockNum', title: '库存参考数', width: 90,align : "center" },
                { field: 'monthUsed', title: '月消耗量', width: 60,align : "center" },
                { field: 'rmb', title: '零售价', width: 60,align : "center" }
            ]],
            toolbar: '#tbn',
            onClickCell:onClickCell
        });
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#buyPlanTable').datagrid('validateRow', editIndex)){
                $('#buyPlanTable').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            if (endEditing()){
                $('#buyPlanTable').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
                editIndex = index;
            }
        }
    }

    /**
     * 初始化按钮等
     */
    var initBtn = function(){
        $('#temporaryNo').combobox({
            valueField:'value',
            textField:'label',
            editable : false,
            onSelect:function(record){
                loadDrugBuyPlan(record.value,'1')
            }
        })
        $.get(base_url + 'getBuyId',{flag : '1',orgId:orgId},function(res){
            var _temporaryNo = []
            for(var i=0;i<res.length;i++){
                _temporaryNo.push({value:res[i],label:res[i]})
            }
            $('#temporaryNo').combobox('loadData',_temporaryNo)
        })

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

            }
        })
        $('#tempButton').linkbutton({
            iconCls: 'icon-add',
            text : '暂存',
            onClick:function(){
                saveData('1')
            }
        })
        $('#saveButton').linkbutton({
            iconCls: 'icon-save',
            text : '保存',
            onClick:function(){
                saveData('2')
            }
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

    /**
     * 获取当前日期的下一个单据号
     */
    var getNextBuyId = function(){
        $.ajaxAsync(base_url + 'getNextBuyId',{orgId:orgId},function(res){
            currentBuyId = res
        },'GET',false)
    }

    /**
     * 展现药品价格表，当参数内只有一条数据时不显示，直接赋值
     * @param drugPrices 药品价格数据
     * @param drugDict 药品字典数据
     */
    var showDrugPriceWindow = function(drugPrices,drugDict){
        if(!drugPrices) return
        var initData = function(drugPrice,drugDict){
            var _buyPlanTableRow = $('#buyPlanTable').datagrid('getSelected')
            var _buyPlanTableIndex = $('#buyPlanTable').datagrid('getRowIndex',_buyPlanTableRow)
            _buyPlanTableRow.drugCode = drugPrice.drugCode
            _buyPlanTableRow.drugSpec = drugPrice.drugSpec
            _buyPlanTableRow.units = drugPrice.units
            _buyPlanTableRow.firmId = drugPrice.firmId
            _buyPlanTableRow.drugForm = drugDict.drugForm
            _buyPlanTableRow.toxiProperty = drugDict.toxiProperty
            _buyPlanTableRow.dosePerUnit = drugDict.dosePerUnit
            _buyPlanTableRow.doseUnits = drugDict.doseUnits
            _buyPlanTableRow.drugIndicator = drugDict.drugIndicator
            _buyPlanTableRow.inputCode = drugDict.inputCode
            _buyPlanTableRow.packSpec = drugDict.drugSpec
            _buyPlanTableRow.packUnit = drugDict.units
            $('#buyPlanTable').datagrid('endEdit',_buyPlanTableIndex)
        }

        if(drugPrices.length == 1){
            initData(drugPrices[0],drugDict)
            return
        }
        if(initDrugPriceWindowFlag) {
            $('#drugPriceWindow').window({
                title: '选择药品规格和单位',
                width: '550',
                height: '450',
                resizable: false
            })
            $("#drugPriceTable").datagrid({
                title: "选择药品规格和单位",
                fit: true,
                border: 0,
                fitColumns: true, //列自适应宽度
                singleSelect: true,
                remoteSort: false,
                idField: 'id',
                columns: [[
                    {field: 'id', title: '编号', hidden: true},
                    {field: 'drugSpec', title: '规格', width: 60, align: "center"},
                    {field: 'units', title: '单位', width: 60, align: "center"},
                    {field: 'firmId', title: '厂家', width: 200, align: "center"},
                    {field: 'tradePrice', title: '批发价', width: 60, align: "center"},
                    {field: 'retailPrice', title: '零售价', width: 60, align: "center"}
                ]],
                onDblClickRow : function(index,row){
                    initData(row,drugDict)
                    $('#drugPriceWindow').window('close')
                }
            });
            initDrugPriceWindowFlag = false
        } else {
            $('#drugPriceWindow').window('open')
        }
        $("#drugPriceTable").datagrid('loadData',drugPrices)
    }

    /**
     * 加载指定购买单据号的数据
     * @param buyId
     * @param flag
     */
    var loadDrugBuyPlan = function(buyId,flag){
        $.get(base_url + 'findList',{buyId:buyId,orgId:orgId,flag:flag},function(res){
            if(res && res.length > 0){
                currentBuyId = buyId
                $('#buyPlanTable').datagrid('loadData',res)
            }
        })
    }

    /**
     * 加载药品字典函数
     * @param orgId 机构ID
     */
    var loadDrugDict = function(orgId){
        $.ajaxAsync('/service/drug-price/findDrugDict',{orgId:orgId},function(res){
            drugDicts = res
        },'GET',false)
    }

    /**
     * 加载同一药品的不同规格、厂商等
     * @param drugDict
     */
    var loadDrugPriceData = function(drugDict){
        $.ajaxAsync('/service/drug-price/findList',{orgId:drugDict.orgId,drugCode:drugDict.drugCode},function(res){
            showDrugPriceWindow(res,drugDict)
        },'GET',false)
    }

    var addRow = function(){
        if(!currentBuyId)
            getNextBuyId()
        var record = {
            buyId : currentBuyId,  // 后台生成
            buyNo : $('#buyPlanTable').datagrid('getRows').length+1,
            storer : username,
            orgId : orgId,
            storage : currentStorage
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
                $('#buyPlanTable').datagrid('updateRow',{index: _index,row: _rows})
            }
        } else {
            $.messager.alert('警告','请选择要删除的药品！','warning')
        }
    }
    var saveData = function(flag){
        var addData = []
        var _insertData = $('#buyPlanTable').datagrid('getChanges','inserted')
        var _updateData = $('#buyPlanTable').datagrid('getChanges','updated')
        var _deleteData = $('#buyPlanTable').datagrid('getChanges','deleted')
        // delRow 函数中datagrid的updateRow函数会保留删除的记录，所以通过遍历除去
        if(_deleteData.length > 0) {
            for (var i = _updateData.length - 1; i > -1; i--) {
                for (var j = 0; j < _deleteData.length; j++) {
                    if (_updateData[i].id == _deleteData[j].id) {
                        _updateData.splice(i, 1)
                        break
                    }
                }
            }
        }
        addData.push(_insertData,_updateData,_deleteData)
        for(var i=0;i<addData.length;i++){
            addData[i].flag = flag
        }
        parent.$.postJSON(base_url + 'saveBatch',JSON.stringify(addData),function(res){
            alert(res.data)
        })
    }
    var init = function(){
        initBuyPlanTable()
        initBtn()
    }

    loadDrugDict(orgId)
    init()
})
