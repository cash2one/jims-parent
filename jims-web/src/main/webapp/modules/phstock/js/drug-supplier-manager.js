/**
 * 药品供应维护
 * @author luohk
 * @version 2016-05-14
 */
$(function() {

var orgId = config.org_Id;
var editIndex = undefined;
var currentStorage = config.currentStorage  // 当前登录人所属管理单位
    //currentStorage = '150103'  // 当前登录人所属管理单位
    //orgId = 1;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#list_data').datagrid('validateRow', editIndex)){
        $('#list_data').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
    function onClickCell(index, field){
        if (endEditing()){
            $('#list_data').datagrid('selectRow', index) .datagrid('editCell', {index:index,field:field});
            editIndex = index;
        }
    }
    var drugDict = [];
    //var drugStorageDept = [];
    var drugSupplierCatalog = [];
    var drugSubStorageDept = [];
    $.get(basePath + "/drug-dict/list", function (data) {
        $.each(data,function(index,row){
            drugDict.push(row);
        });
    });
    $.get(basePath + '/drug-storage-dept/findSubList?orgId='+orgId+'&storageCode='+ currentStorage, function (data) {
        $.each(data,function(index,row){
            drugSubStorageDept.push(row);
        });
    });
    $.get(basePath + '/drug-supplier-catalog/list?orgId='+orgId, function (data) {
        $.each(data,function(index,row){
            drugSupplierCatalog.push(row);
        });
    });

    loadAnother = function(){
        //厂商
        if(drugSupplierCatalog.length==0||drugDict.length==0||drugSubStorageDept.length==0){//若这三个对象任何一个为空，就隔一秒递归调用一次本方法，直至三个对象都不为空
            setTimeout("loadAnother()",1000);
        }
        $('#firmId').combogrid({
            panelWidth:300,
            idField:'supplierCode',
            textField:'supplierId',
            valueField:'supplierCode',
            fitColumns: true,
            data:drugSupplierCatalog,
            method:'get',
            mode:'remote',
            columns:[[
                {field:'supplierCode',title:'厂商代码',width:60,align : "left"},
                {field:'supplierId',title:'简称',width:80,align : "left" },
                {field:'supplier',title:'全称',width:140,align : "left"}
            ]]
        })
        //药品名称
        $('#drugName').combobox({
            valueField:'drugCode',
            textField:'drugName',
            data:drugDict,
            width:160
        })
        //库房子单位
        $('#stockSubDept').combobox({
            valueField:'subStorageCode',
            textField:'subStorage',
            data:drugSubStorageDept,
            width:160
        })
    }
    setTimeout("loadAnother()",1000);
    loadAnotherData = function(){
        var listAll = $('#list_data').datagrid('getRows');
        $.each(listAll, function(i, r){
            //$.each(drugSubStorageDept,function(index,row){
            //    if(row.subStorageCode == r.subStorage){
            //        r.subStorage = row.subStorage;
            //    }
            //});
            $.each(drugSupplierCatalog,function(index,row){
                if(row.supplierCode == r.firmId){
                    r.firmId = row.supplierId;
                }
            });
            $('#list_data').datagrid("refreshRow",$('#list_data').datagrid("getRowIndex",r))
        });
    }
    $("#list_data").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        url: basePath+'/drug-stock/findListStock?orgId='+orgId+'&storage='+currentStorage,
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        onClickCell: onClickCell,
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "药品名称",
            field: "drugName",
            width: '11%',
            align: 'center'
        }, {
            title: "规格",
            field: "drugSpec",
            width: '4%',
            align: 'center'
        }, {
            title: "单位",
            field: "units",
            width: '4%',
            align: 'center'
        }, {
            title: "批号",
            field: "batchNo",
            width: '11%',
            align: 'center'
        }, {
            title: "入库单号",
            field: "documentNo",
            width: '8%',
            align: 'center'
        }, {
            title: "数量",
            field: "quantity",
            width: '7%',
            align: 'center'
        }, {
            title: "包装单位",
            field: "packageUnits",
            width: '5%',
            align: 'center'
        }, {
            title: "包装规格",
            field: "packageSpec",
            width: '5%',
            align: 'center'
        }, {
            title: "来源厂家",
            field: "firmId",
            width: '11%',
            align: 'center',formatter: function(value){
                $.each(drugSupplierCatalog,function(index,row){
                    if(row.firmId==value){
                        return row.supplierId;
                    }
                });
                return value;
            }
        }, {
            title: "一级库房",
            field: "storageName",
            width: '11%',
            align: 'center',formatter: function(value,row ){
                if(value == ''){
                    return row.storage;
                }
                return value;
            }
        }, {
            title: "二级库房",
            field: "subStorageName",
            width: '11%',
            align: 'center',formatter: function(value,row ){
                if(value == ''){
                    return row.subStorage;
                }
                return value;
            }
        }, {
            title: "供应标志",
            field: "supplyIndicator",
            width: '8%',
            align: 'center',
            editor:{
                type:'combobox',
                options:{
                    data :[{'value': '1', 'text': '可供应'},{'value': '0', 'text': '不可供应'}],
                    valueField:'value',
                    textField:'text',
                    required:true
            }},formatter: function(value,row ){
                if(value == '1'){
                    return "可供应";
                }
                return "<span style='color:red'>不可供应</span>";
            }
        }]]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

    var loadListData = function () {
        var supplyIndicator=$("#supplyIndicator").combobox('getValue');
        var stockSubDept = $('#stockSubDept').combobox('getValue');
        var drugName = $('#drugName').combobox('getValue');
        var documentNo = $('#documentNo').textbox('getValue');
        var firmId = $('#firmId').combobox('getValue');
        $('#list_data').datagrid('options').url = basePath+'/drug-stock/findListStock?orgId='+orgId+'&storage='+currentStorage+
            '&supplyIndicator='+supplyIndicator+'&subStorage='+stockSubDept+'&drugCode='+drugName+'&documentNo='+documentNo+'&firmId='+firmId;
        $('#list_data').datagrid('reload');
    }
    $("#searchBtn").on("click",function(){
        loadListData();
    });

    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#list_data").datagrid("endEdit", editIndex);
        }
        var insertData = $("#list_data").datagrid("getChanges", "inserted");
        var updateData = $("#list_data").datagrid("getChanges", "updated");
        var deleteData = $("#list_data").datagrid("getChanges", "deleted");

        if(insertData.length<=0&&updateData.length<=0&&deleteData.length<=0){
            alert('没有修改数据，无需保存');
            return ;
        }
        var drugStockVo = {};
        drugStockVo.inserted = insertData;
        drugStockVo.deleted = deleteData;
        drugStockVo.updated = updateData;
        drugStockVo.orgId = orgId;

        if (drugStockVo) {
            $.postJSON(basePath + "/drug-stock/saveAll", JSON.stringify(drugStockVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    //$("#list_data").datagrid('reload');
                    //$("#list_data").datagrid("clearSelections");
                    loadListData();
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });




});