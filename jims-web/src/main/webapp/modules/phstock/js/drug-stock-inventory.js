//药品库存盘点
$(function(){
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#inventoryList").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    var loadDate=function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    };
    var drugSupplierDict  = [];//药品生产商列表
    $.get( basePath + "/drug-supplier-catalog/list-supplier-type?orgId=" + parent.config.org_Id + "&supplierClass=生产商", function (data) {
        drugSupplierDict = data;
    });

    var specUnits = [];//规格单位字典
    $.get("/service/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });

    //var drugToxi = [];//毒理属性字典
    //$.get( "/service/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT", function (data) {
    //    drugToxi = data;
    //});

    var drugFormDict = [];//剂型字典
    $.get("/service/dict/findListByType?type=DRUG_FORM_DICT", function (data) {
        drugFormDict = data;
    })

    $("#date").datebox("setValue",loadDate());
    //$("#date").datetimebox("setValue","2015-11-1 3:25:10");

    $("#inventoryList").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,
        //  url: basePath + "/AdministrationDict/listAll",

        loadMsg: '数据正在加载中，请稍后.....',
        //pagination: true,//分页控件
        //pageSize: 15,
        //pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品编码",
            field: "drugCode",
            width: '7%',
            align: 'center'
        }, {
            title: "药品名称",
            field: "drugName",
            width: '11%',
            align: 'center'
        }, {
            title: "包装规格",
            field: "packageSpec",
            width: '7%',
            align: 'center'
        }, {
            title: "单位",
            field: "packageUnits",
            width: '6%',
            align: 'center',
            formatter:function(value,row,index){
                var unitsName = value;
                $.each(specUnits, function (index,item) {
                    if(item.value == value){
                        unitsName =  item.label;
                    }
                });
                return unitsName;
            }
        }, {
            title: "生产商",
            field: "supplier",
            hidden:true

        }, {
            title: "厂家",
            field: "firmId",
            width: '9%',
            align: 'center',
            //hidden:true
            // align: 'center',
            formatter: function (value,row,index) {
                 var supplierName = value;
                 $.each(drugSupplierDict, function (index,item) {
                     if(item.id == value){
                         supplierName  =  item.supplier;
                     }
                 });
                 return  supplierName;
            }

        }, {
            title: "剂型",
            field: "drugForm",
            width: '4%',
            align: 'center',
            formatter:function(value,row,index){
                var label=value;
                $.each(drugFormDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: "批号",
            field: "batchNo",
            width: '7%',
            align: 'center'
        }, {
            title: "单价",
            field: "retailPrice",
            width: '7%',
            align: 'center'
        }, {
            title: "账面数",
            field: "quantity",
            width: '7%',
            align: 'center'
        }, {
            title: "账面数（包装）",
            field: "quantityNum",
            width: '7%',
            align: 'center',
            formatter: function (value,row,index) {
                if(value == "合计") return value;
                //var units='';
                //$.each(specUnits, function (index,item){
                //    if(item.value=row.packageUnits){
                //        units=item.label;
                //    }
                //}
                var quantityNum = row.quantity + row.packageUnits + "(" + row.packageSpec + ")";
                if(row.quantity){
                    return quantityNum;
                }else{
                    return "";
                }
            }
        }, {
            title: "实盘数",
            field: "actualQuantity",
            width: '7%',
            align: 'center',
            editor:{
                type:'textbox',options: {
                    onChange: function (n,o) {
                        if (window.event.keyCode == 13) {
                            $("#inventoryList").datagrid("endEdit",editIndex);

                            var length = $("#inventoryList").datagrid("getRows").length - 1;
                            var newEditIndex = editIndex + 1;
                            if(length > newEditIndex){
                                $("#inventoryList").datagrid("beginEdit",newEditIndex);
                                $("#inventoryList").datagrid("selectRow",newEditIndex);
                                var editor = $('#inventoryList').datagrid('getEditor', {index:newEditIndex,field:"actualQuantity"});
                                $(editor.target).textbox().next('span').find('input').focus();
                                editIndex = newEditIndex;
                            }
                        }
                    }
                }
            }
        }, {
            title: "盈亏数",
            field: "profitAndLoss",
            width: '7%',
            align: 'center',
            formatter: function (value,row,index) {
                var profitAndLoss = row.actualQuantity - row.quantity ;
                if(row.quantity){
                    row.profitAndLoss = profitAndLoss;
                    return profitAndLoss;
                }else{
                    return value;
                }
            }
        }, {
            title: "账面额",
            field: "quantityAmount",
            width: '7%',
            align: 'center',
            formatter: function (value,row,index) {
                var quantityAmount = row.quantity * row.retailPrice ;

                if(quantityAmount != undefined  && !isNaN(quantityAmount)){
                    row.quantityAmount = parseFloat(quantityAmount).toFixed(4);
                    return parseFloat(quantityAmount).toFixed(4);
                }else{
                    if(value == undefined){
                        value = 0.00;
                        row.quantityAmount = parseFloat(value).toFixed(4);
                    }
                    return parseFloat(value).toFixed(4);
                }
            }
        }, {
            title: "实盘额",
            field: "actualAmount",
            width: '7%',
            align: 'center',
            formatter: function (value,row,index) {
                if(row.quantityNum == '合计') return parseFloat(value).toFixed(4);

                var actualAmount = (isNaN(row.actualAmount) ? 0 : +row.actualAmount);
                var _value = ((isNaN(row.actualQuantity) ? 0 : +row.actualQuantity)
                * (isNaN(row.retailPrice) ? 0 : +row.retailPrice)).toFixed(4);

                row.actualAmount = _value;

                var _allRow = $('#inventoryList').datagrid('getRows');
                var _lastRow = _allRow[_allRow.length - 1];
                if(_lastRow.quantityNum == '合计') {
                    _lastRow.actualAmount = (+_lastRow.actualAmount + (+_value) - actualAmount).toFixed(4)
                    $('#inventoryList').datagrid('refreshRow', _allRow.length - 1)
                }
                return _value
            }
        }, {
            title: "盈亏额",
            field: "profitAndLossAmount",
            width: '7%',
            align: 'center',
            formatter: function (value,row,index) {
                if(row.quantityNum == '合计') return (+value).toFixed(4);

                var profitAndLossAmount = (isNaN(row.profitAndLossAmount) ? 0 : +row.profitAndLossAmount);
                var _value = (((isNaN(row.actualQuantity) ? 0 : +row.actualQuantity) - (isNaN(row.quantity) ? 0 : +row.quantity)
                )* (isNaN(row.retailPrice) ? 0 : +row.retailPrice)).toFixed(4);

                row.profitAndLossAmount = _value;

                var _allRow = $('#inventoryList').datagrid('getRows');
                var _lastRow = _allRow[_allRow.length - 1];
                if(_lastRow.quantityNum == '合计') {
                    _lastRow.profitAndLossAmount = (+_lastRow.profitAndLossAmount + (+_value) - profitAndLossAmount).toFixed(4);
                    $('#inventoryList').datagrid('refreshRow', _allRow.length - 1)
                }
                return _value

            }
        }, {
            title: "分库房",
            field: "subStorage",
            width: '7%',
            align: 'center',
            formatter: function (value,row,index) {
                var storageName = value;
                $.each(drugSubStorageDict, function (index,item) {
                    if(item.subStorageCode == value){
                        storageName  =  item.subStorage;
                    }
                });
                return  storageName;
            }
        },{
            field: "location",
            hidden:true
        },{
            field: "checkYearMonth",
            hidden:true,
            formatter: function (value,row,iindex) {
                //row.checkYearMonth = new Date(value);
                //return new Date(value)
            }
        },{
            field: "storage",
            hidden:true
        },{
            field: "orgId",
            hidden:true
        },{
            field: "recStatus",
            hidden:true
        }
        ]],
        onSelect: function (rowIndex, rowDate) {
            stopEdit();
            if (rowDate.recStatus == 0||rowDate.recStatus == 2){
                $("#inventoryList").datagrid("beginEdit",rowIndex);
                editIndex = rowIndex;
                var editor = $('#inventoryList').datagrid('getEditor', {index:rowIndex,field:"actualQuantity"});
                $(editor.target).textbox().next('span').find('input').focus();
            }
        },
        onLoadSuccess: function (data) {
            var rows = $("#inventoryList").datagrid("getRows");
            console.log(rows);
            if(rows[rows.length-1].quantityNum=="合计"){
            }else{
                var sumQuantityAmount = 0.0000;
                var sumActualAmount = 0.0000;
                var sumProfitAndLossAmount = 0.0000;

                $.each(rows, function (index,item) {
                    sumQuantityAmount = parseFloat(sumQuantityAmount) + parseFloat(item.quantityAmount);
                    sumActualAmount = parseFloat(sumActualAmount) + parseFloat(item.actualAmount);
                    sumProfitAndLossAmount = parseFloat(sumProfitAndLossAmount) + parseFloat(item.profitAndLossAmount);
                });
                $("#inventoryList").datagrid("appendRow",{quantityNum:"合计",quantityAmount:sumQuantityAmount,actualAmount:sumActualAmount,profitAndLossAmount:sumProfitAndLossAmount});
            }
        }
    });

    //定义药品名称
    $('#location').combogrid({
        panelWidth: 500,
        idField: 'drugCode',
        textField: 'drugName',
        loadMsg: '数据正在加载',
        url: basePath + '/drug-catalog/drugNameDictList',
        mode: 'remote',
        method: 'GET',
        fitColumns:true,
        columns: [[
            {field: 'drugCode', title: '编码', width: 150, align: 'center'},
            {field: 'drugName', title: '名称', width: 200, align: 'center'},
            {field: 'inputCode', title: '拼音', width: 50, align: 'center'}
        ]],
        onSelect: function(rowIndex,rowData){
            var rows = $("#inventoryList").datagrid("getRows");
            $.each(rows, function (index,row) {
                if(row.drugCode == rowData.drugCode  && row.drugName == rowData.drugName){
                    $("#inventoryList").datagrid("scrollTo",index);
                    $("#inventoryList").datagrid("selectRow",index);
                }
            });
        }
    });
    //库存
    $('#storage').combobox({
        valueField: 'subStorageCode',
        panelHeight:"90px",
        textField: 'subStorage',
        columns: [[
            {field: 'subStorageCode', title: '库房代码', width: 100},
            {field: 'subStorage', title: '库房名称', width: 100},
        ]]
    });

    var loadSubStorage=function(){
        $.get(basePath + '/drug-sub-storage-dept/list-by-storageCode?orgId=' + parent.config.org_Id + '&storageCode=' + parent.config.currentStorage, function (data) {
            $("#storage").combobox('loadData', data);
            console.log(data);
        });
    }
    loadSubStorage();

    var drugSubStorageDict  = [];//药品子库房列表                                              //parent.config.currentStorage &storageCode=150520
    $.get( basePath + "/drug-storage-dept/findSubList?orgId=" + parent.config.org_Id + "&storageCode=" + parent.config.currentStorage, function (data) {
        drugSubStorageDict = data;
    });

    var drugInventoryList=[];
    var reset=function(){
        $.ajax({
            type: 'get',
            url: basePath + "/drug-inventory-check/extractInventory?storage=" + parent.config.currentStorage + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datebox("getValue").substr(0,10) + "&subStorage="+ $("#storage").combobox("getValue"),
            async : false,   // true 异步,false 同步
            contentType: 'application/json',
            success: function(res){
                if(res.length>0){
                    drugInventoryList=res;
                }else{
                    drugInventoryList=[];
                }
            }
        })
    }


    //提取
    $("#extractBtn").on("click", function () {
        stopEdit();
        var subStor=$("#storage").combobox("getValue");
        if(subStor=='' ||subStor==null){
            $.messager.alert("提示","请选择子库房",'info');
        }else{
            var url = basePath + "/drug-inventory-check/extractInventory?storage=" + parent.config.currentStorage
                + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datebox("getValue").substr(0,10) + "&subStorage="
                + $("#storage").combobox("getValue");
            $.get(url, function (data) {
                if(data.length != 0){
                    $("#inventoryList").datagrid("reload",url);
                }else{
                    $.messager.alert("提示","没有该时间盘点记录",'info');
                    $("#inventoryList").datagrid("loadData",[]);
                }
            });
        }
        //var url = basePath + "/drug-inventory-check/extractInventory?storage=" + 150520
        //    + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datetimebox("getValue").substr(0,10) + "&subStorage="
        //    + $("#storage").combobox("getValue");
    });
    //生成
    $("#generateBtn").on("click", function () {
        stopEdit();

        var subStor=$("#storage").combobox("getValue");
        if(subStor=='' ||subStor==null) {
            $.messager.alert("提示", "请选择子库房", 'info');
        }else{
            var drugInventoryCheckVos = $("#inventoryList").datagrid("getRows");

            //判断是否有改时间盘点记录
            //var data;
            //var url = basePath + "/drug-inventory-check/extractInventory?storage=" + 150520
            //    + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datetimebox("getValue").substr(0,10) + "&subStorage="
            //    + $("#storage").combobox("getValue");
            //var url = basePath + "/drug-inventory-check/generateInventory?storage=" + parent.config.currentStorage
            //    + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datebox("getValue").substr(0,10) + "&subStorage="
            //    + $("#storage").combobox("getValue");
            //$.get(url, function (data) {
            //    data =data;
            //});
            reset();
            if(drugInventoryCheckVos.length > 1){
                $.messager.alert("提示","盘点数据已经存在，不能生成","error");
            }else{
                if(drugInventoryList.length>0){
                    $.messager.alert("提示","该时间点有盘点信息，请提取","error");
                }else{
                    var url = basePath + "/drug-inventory-check/generateInventory?storage=" + parent.config.currentStorage
                        + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datebox("getValue") + "&subStorage="
                        + $("#storage").combobox("getValue");
                    var drugList=[];
                    $.ajax({
                        type: 'get',
                        url:basePath + "/drug-inventory-check/generateInventory?storage=" + parent.config.currentStorage + "&orgId=" + parent.config.org_Id + "&checkYearMonth=" + $("#date").datebox("getValue") + "&subStorage=" + $("#storage").combobox("getValue"),
                        async : false,   // true 异步,false 同步
                        contentType: 'application/json',
                        success: function(res){
                                drugList=res;
                        }
                    })
                    for(var i=0;i<drugList.length;i++){
                        drugList[i].recStatus='2';
                    }
                    $("#inventoryList").datagrid("loadData", drugList);
                }


            }
        }


    });
    //暂存
    $("#temporaryStorageBtn").on("click", function () {
        stopEdit();
        var rows = $("#inventoryList").datagrid("getRows");
        var drugInventoryCheckVos=[];
        for(var i=0;i<rows.length-1;i++){
            if(rows[i].orgId==parent.config.org_Id){
                j=drugInventoryCheckVos.length;
                drugInventoryCheckVos[j]=rows[i];
            }
        }
        for(var i=0;i<drugInventoryCheckVos.length;i++){
            delete drugInventoryCheckVos[i].quantityNum;
        }
        if(drugInventoryCheckVos.length > 0 && drugInventoryCheckVos[0].recStatus == 1){
            $.messager.alert("提示","盘点数据已经终存不能暂存","error");
        }else if(drugInventoryCheckVos.length == 0 ){
            $.messager.alert("提示","盘点无数据不能暂存","error");
        }else{
            $.postJSON(basePath + "/drug-inventory-check/temporaryStorage",JSON.stringify(drugInventoryCheckVos), function (data) {
                $.messager.alert("提示","暂存成功","info");
                $("#inventoryList").datagrid("loadData", {total: 0, rows: [] });
            }, function (data) {
                $.messager.alert("提示","暂存失败","info");
                $("#inventoryList").datagrid("loadData", {total: 0, rows: [] });
            })
        }



    });
    //终存
    $("#saveBtn").on("click", function () {
        stopEdit();
        var rows = $("#inventoryList").datagrid("getRows");
        var drugInventoryCheckVos=[];
        for(var i=0;i<rows.length-1;i++){
            if(rows[i].orgId==parent.config.org_Id){
                j=drugInventoryCheckVos.length;
                drugInventoryCheckVos[j]=rows[i];
            }
        }
        for(var i=0;i<drugInventoryCheckVos.length;i++){
            delete drugInventoryCheckVos[i].quantityNum;
        }
        if(drugInventoryCheckVos.length > 0 && drugInventoryCheckVos[0].recStatus == 1){
            $.messager.alert("提示","盘点数据已经终存不能再次保存","error");
        }else if(drugInventoryCheckVos.length == 0 ){
            $.messager.alert("提示","盘点无数据不能保存","error");
        }else{
            console.log(drugInventoryCheckVos);
            $.messager.confirm("提示","盘点数据一但终存就不能修改了，是否最终保存？", function (data) {
                if(data){
                    $.postJSON(basePath + "/drug-inventory-check/saveInventory",JSON.stringify(drugInventoryCheckVos), function (data) {
                        $.messager.alert("提示","保存成功","info");
                        $("#inventoryList").datagrid("loadData", {total: 0, rows: [] });
                    }, function (data) {
                        $.messager.alert("提示","保存失败","info");
                        $("#inventoryList").datagrid("loadData", {total: 0, rows: [] });
                    })
                }else{

                }

            });
        }

    });
    //实盘填充
    $("#actualFill").on("click",function(){
        stopEdit();
        var rows = $("#inventoryList").datagrid("getRows");
        if(rows.length > 0 && rows[0].recStatus == 1){
            $.messager.alert("提示","盘点数据已经终存不能实盘填充","error");
        }else {
            $.each(rows, function (index, row) {
                row.actualQuantity=row.quantity;
                //$("#inventoryList").datagrid("beginEdit", index);
                //var event = $("#inventoryList").datagrid("getEditor", {index: index, field: "actualQuantity"});
                //$(event.target).textbox("setValue", row.quantity);
                //$("#inventoryList").datagrid("endEdit", index);
            });
            $("#inventoryList").datagrid("loadData",rows);
        }
    })

});