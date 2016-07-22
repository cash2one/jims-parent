$(function () {
    var base_url = '/service/drug-buy-plan/'
    var orgId = config.org_Id
        ,username = '采购员'
        , drugDicts = []  // 检索的药品字典数据
        , suppliers = []  // 供应商数据

    var planSelectIndex = 0;   // 购买计划表当前选择行索引


    var orgList=[]; //组织机构及其下级机构列表
    $.get("/service/sys-sompany/find-list-by-parent-id?orgId="+config.org_Id, function (data) {
        orgList= data;
        loadBuyList(orgList);
    });

    var buyListByOrg=[];
    var loadBuyList = function(orgLists){
        buyListByOrg=[];
        if(orgLists.length>0){
            for(var i=0;i<orgLists.length;i++){
                $.ajax({
                    type: 'get',
                    url: base_url+'getBuyListByOrg?flag=4&orgId='+orgLists[i].id,
                    async : false,   // true 异步,false 同步
                    //data: {orgId: orgLists[i].id, flag: 3},
                    contentType: 'application/json',
                    success: function(res){
                        if(res.length>0){
                            for (var j=0;j<res.length;j++){
                                buyListByOrg.push(res[j]);
                            }
                        }
                    }
                })
            }
            $('#temporaryNo').combogrid('grid').datagrid("loadData", buyListByOrg);
        }
    }

    var specUnits = [];//规格单位字典
    $.get("/service/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });

    var drugToxi = [];//毒理属性字典
    $.get( "/service/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT", function (data) {
        drugToxi = data;
    });

    var drugFormDict = [];//剂型字典
    $.get("/service/dict/findListByType?type=DRUG_FORM_DICT", function (data) {
        drugFormDict = data;
    });


    /**
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#buyPlanTable').datagrid('getRows').length - 1
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'buyNo', rowspan: null, colspan: 12})
        $('#buyPlanTable').datagrid('mergeCells', {index: _index, field: 'checkMoney', rowspan: null, colspan: 8})
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
                {field: 'drugCode', title: '药名', width: 220, halign: "center", align: "left", formatter: function (value) {
                    if (value == undefined || value == '') return ''
                    for (var i = 0, j = (drugDicts ? drugDicts.length : 0 ); i < j; i++) {
                        if (drugDicts[i].drugCode == value) {
                            value = drugDicts[i].drugName
                            break
                        }
                    }
                    return value;
                }},
                {field: 'checkSupplier', title: '审核供应商', width: 200, halign: "center", align: "left", formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return suppliers[i].supplier
                        }
                    }
                    return ''
                }},
                {field: 'supplier', title: '生成厂家', width: 200, halign: "center", align: "left"},
                {field: 'checkNumber', title: '审核数量', width: 60, align: "center"},
                {field: 'checker', title: '审核人', width: 70, align: "center"},
                {field: 'stockSupplier', title: '供应商', width: 200, halign: "center", align: "left",formatter:function(value){
                    for(var i= 0,j=suppliers.length;i<j;i++){
                        if(suppliers[i].id == value) {
                            return suppliers[i].supplier
                        }
                    }
                    return ''
                }},
                {field: 'storer', title: '仓管员', width: 70, align: "center"},
                {field: 'purchasePrice', title: '进货价', width: 60, align: "center"},
                {field: 'monthUsed', title: '月消耗量', width: 60, align: "center"},
                {field: 'stockNum', title: '库存参考数', width: 90, align: "center"},
                {field: 'packSpec', title: '包装规格', width: 60, align: "center"},
                {field: 'checkMoney', title: '审核金额', width: 60, align: "center",formatter:function(value,row,index){
                    if(row.buyNo == '审核金额合计') return '<div style="text-align:left">　　'+value+'</div>'
                    return ((isNaN(row.checkNumber) ? 0 : +row.checkNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'buyer', title: '采购员', width: 70, align: "center"},
                {field: 'stockMoney', title: '采购金额', width: 60, align: "center",formatter:function(value,row,index){
                    return ((isNaN(row.stockNumber) ? 0 : +row.stockNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'count', title: '计划金额', width: 60, align: "center", formatter: function (value, row, index) {
                    return ((isNaN(row.wantNumber) ? 0 : +row.wantNumber) * (isNaN(row.purchasePrice) ? 0 : +row.purchasePrice)).toFixed(1)
                }},
                {field: 'stockNumber', title: '采购数量', width: 60, align: "center"},
                {field: 'packUnit', title: '包装单位', width: 60, align: "center",
                    formatter:function(value,row,index){
                        var unitsName = value;
                        $.each(specUnits, function (index,item) {
                            if(item.value == value){
                                unitsName =  item.label;
                            }
                        });
                        return unitsName;
                    }},
                {field: 'wantNumber', title: '计划数量', width: 60, align: "center"},
                {field: 'drugForm', title: '剂型', width: 80, align: "center",
                    formatter:function(value,row,index){
                        var label=value;
                        $.each(drugFormDict, function (index,item) {
                            if (item.value == value){
                                label =   item.label;
                            }
                        });
                        return label;
                    }}
            ]],
            onLoadSuccess: function (data) {
                var rows = $(this).datagrid('getRows')
                if (rows.length == 0) return
                var checkMoney = 0
                for(var i= 0,j = rows.length;i<j;i++){
                    checkMoney += +((isNaN(rows[i].checkNumber) ? 0 : +rows[i].checkNumber) * (isNaN(rows[i].purchasePrice) ? 0 : +rows[i].purchasePrice)).toFixed(1)
                }
                var countRecord = {
                    buyNo: '审核金额合计',
                    checkMoney: checkMoney
                }
                $(this).datagrid('appendRow', countRecord)
                mergeLastCells()
            }
        });

    }

    /**
     * 初始化按钮等
     */
    var initBtn = function () {
        $('#temporaryNo').combogrid({
            panelWidth: 203,
            mode:'remote',
            idField: 'buyId',
            textField: 'buyId',
            //editable: false,
            //url:loadBuyList(orgList),
            //data:[],
            columns: [[
                {field: 'buyId', title: '采购单据号', width: 100},
                {field: 'orgId', title: '所属机构', width: 100
                    ,formatter:function(value,index){
                    var orgName=value;
                    for(var i=0;i<orgList.length;i++){
                        if(orgList[i].id==value){
                            orgName=orgList[i].orgName
                        }
                    }
                    return orgName;
                }
                }
            ]],
            onSelect: function (q, row) {
                console.log(row.buyId+row.orgId);
                console.log(q);
                //var rows=$('#temporaryNo').combogrid('getSelected');
                //console.log(rows);
                //planSelectIndex = 0
                //loadDrugBuyPlan(record[0], '3')
                planSelectIndex = 0
                loadDrugBuyPlan(row.buyId, '4',row.orgId);
            }
            //onLoadSuccess:function(){
            //}
        })

        $('#saveButton').linkbutton({
            iconCls: 'icon-cog',
            text: '执行',
            onClick: saveData
        })
        $('#saveAndInStockButton').linkbutton({
            iconCls: 'icon-build',
            text: '执行并入库',
            onClick: function(){
                var rows = $('#buyPlanTable').datagrid('getRows');
                if(rows.length>0 &&rows[0].orgId==config.org_Id){
                    saveData('in')
                }else{
                    $.messager.alert('系统警告：','子机构的采购物品不可入库','error')
                }

            }
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
     * 加载指定购买单据号的数据
     * @param buyId
     * @param flag
     */
    var loadDrugBuyPlan = function (buyId, flag,org) {
        $.get(base_url + 'findList', {buyId: buyId, orgId: org, flag: flag}, function (res) {
            console.log(res);
            for(var i=0;i<res.length;i++){
                res[i].flag = '5'
            }
            $('#buyPlanTable').datagrid('loadData', res)
            $('#buyPlanTable').datagrid('selectRow', planSelectIndex)
        })
    }

    /**
     * 加载药品字典函数
     * @param orgId 机构ID
     */
    var loadDrugDict = function (orgId) {
        $.ajaxAsync('/service/drug-price/findDrugDict', {orgId: orgId}, function (res) {
            drugDicts = res
        }, 'GET', false)
    }

    /**
     * 加载厂商
     * @param orgId
     * @param supplierClass
     */
    var loadSupplier = function(orgId,supplierClass){
        $.ajaxAsync('/service/drug-supplier-catalog/list-supplier-by-sub-org', {
            orgId: config.org_Id
            //supplierClass: supplierClass
        }, function (res) {
            suppliers = res
        }, 'GET', false)
    }

    /**
     * 保存执行数据
     */
    var saveData = function () {
        var _allData = $('#buyPlanTable').datagrid('getRows')
        if (_allData.length == 0){
            $.messager.alert('警告','没有需要执行的数据！','warning')
            return
        }

        var handleData = [[],[]] // handleData[0] 保存的数据,handleData[1] 删除的数据
        _allData.splice(_allData.length-1 ,1)
        for(var i=0;i<_allData.length;i++){
            _allData[i].executedNumber = _allData[i].checkNumber
            _allData[i].executedDate = new Date()
        }
        handleData[0] = _allData
        parent.$.postJSON(base_url + 'saveBatch', JSON.stringify(handleData), function (res) {
            if (res.code = '0')
                $.messager.alert('成功', '执行成功', 'info', function () {
                     window.location.reload()
                })
        })
    }

    var init = function () {
        loadDrugDict(orgId)
        loadSupplier(orgId,'供应商')

        initBuyPlanTable()
        initBtn()
    }

    init()
})
