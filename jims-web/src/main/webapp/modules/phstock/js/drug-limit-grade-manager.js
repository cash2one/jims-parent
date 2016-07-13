/**
 * Created by dt on 2016/5/11.
 */
$(function () {
    $.extend({
        ajaxAsync : function(url,callback){
            return $.ajax({
                type: 'get',
                url: url,
                async : false,
                success: callback,
                'contentType': 'application/json'
            });
        }
    });
    var editIndex;
    var drugNameCode;//药品编码

    var specUnits = [];//规格单位字典
    var specUnitPromise =  $.ajaxAsync( basePath + "/dict/findListByType?type=spec_unit", function (data) {
        specUnits = data;
    });
    var drugFormDict = [];//剂型字典
    var drugFormDictPromise =  $.ajaxAsync( basePath  + "/dict/findListByType?type=DRUG_FORM_DICT", function (data) {
        drugFormDict = data;
    });
    var drugToxi = [];//毒理属性字典
    var drugToxiPromise =  $.ajaxAsync( basePath  + "/dict/findListByType?type=DRUG_TOXI_PROPERTY_DICT", function (data) {
        drugToxi = data;
    });
    var doseUnit = [];//剂型单位字典
    var doseUnitPromise =  $.ajaxAsync(  basePath  + "/dict/findListByType?type=dose_unit", function (data) {
        doseUnit = data;
    });
    var drugPreciousFlagDict = [];//贵重等级字典
    var drugPreciousFlagDictPromise =  $.ajaxAsync(basePath  + "/dict/findListByType?type=DRUG_PRECIOUS_FLAG_DICT", function (data) {
        drugPreciousFlagDict = data;
    });
    var drugTypeDict = [];//药品类型字典
    var drugTypeDictPromise =  $.ajaxAsync(basePath  + "/dict/findListByType?type=drug_type_dict", function (data) {
        drugTypeDict = data;
    });

    var limitClassList = [];//药品限制等级
    var drugTypeDictPromise =  $.ajaxAsync(basePath  + "/dict/findListByType?type=LIMIT_CLASS_DICT", function (data) {
        limitClassList = data;
    });


    //停止编辑
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#drugNameDict").datagrid('endEdit', editIndex);
            $("#drugDict").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };



    //单选按钮切换

    $(".radios").click(function () {
        var radioValue = $(this).val();
        if (radioValue == 0){
            $("#drugClass").combobox('clear');
            $("#drugClassForm").combobox('clear');

            $("#drugClass").combobox('disable');
            $("#drugClassForm").combobox('disable');

            $("#drugName").combogrid('enable');
        }
        if (radioValue == 1){
            $("#drugName").combogrid('disable');
            $("#drugName").combogrid('clear');

            $("#drugClass").combobox('enable');
            $("#drugClassForm").combobox('enable');
        }

    });

    //定义药品名称
    $('#drugName').combogrid({
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
            saveChange();
            var url = basePath + "/drug-catalog/listDrugNameDictByDrugCode?drugCode=" +  rowData.drugCode;
            $('#drugNameDict').datagrid('reload', url);
            var url = basePath + "/drug-catalog/listDrugDictByDrugCode?drugCode=" +  rowData.drugCode;
            $('#drugDict').datagrid('reload', url);

        }
    });

    var classCode=''
    var drugForm='';
    //定义药品大类
    $("#drugClass").combobox({
        valueField: 'classCode',
        textField: 'className',
        width:130,
        method: 'GET',
        url: basePath +  "/drug-class-dict/list-parent?parentId=*",
        onSelect: function(){
            classCode=$("#drugClass").combobox('getValue');
            drugForm=$("#drugClassForm").combobox('getValue');
            if(drugForm!='' ){
                saveChange();
                clear();
                loadDrugNameDict();
            }
        }
    });



    //定义药品剂型
    $("#drugClassForm").combobox({
        valueField: 'value',
        textField: 'label',
        width:130,
        method: 'GET',
        url: basePath  + "/dict/findListByType?type=DRUG_FORM_DICT",
        onSelect: function(){
            classCode=$("#drugClass").combobox('getValue');
            drugForm=$("#drugClassForm").combobox('getValue');
            if (classCode!=''){
                saveChange();
                clear();
                loadDrugNameDict();
            }
        }
    });


    var loadDrugNameDict=function(){
        $.get( basePath + "/drug-catalog/listDrugNameDictByClassCode?classCode="+classCode+"&drugForm="+drugForm, function (data) {
            $("#drugNameDict").datagrid('loadData',data);
        });
    }

    //定义页面下方drugNameDict列表
    $("#drugNameDict").datagrid({
        title: '药品名称',
        footer: '#tbNameDict',
        singleSelect: true,
        fit:true,
        method:'get',
        columns: [[{
            title: 'id',
            field: 'id',
            hidden: true
        },{
            title: '代码',
            field: 'drugCode',
            width: "45%",
            align: 'center'
        }, {
            title: '药品名称',
            field: 'drugName',
            width: "50%",
            align: 'center'
        }
        ]],
        onClickRow: function (index, row) {
            console.log(row.drugCode);
            var url = basePath + "/drug-catalog/listDrugDictByDrugCode?drugCode=" + row.drugCode;
            $('#drugDict').datagrid('reload', url);
        }
    });

    var drugDictList=[];
    //定义页面下方drugDict列表
    $("#drugDict").datagrid({
        title:'药品剂型剂量',
        footer: '#tbDict',
        singleSelect: true,
        fit: true,
        method:'get',
        columns: [[{
            title: 'id',
            field: 'id',
            hidden:true
        },{
            title: '药品名称',
            field: 'drugName',
            width: "10%"
        }, {
            title: '规格',
            field: 'drugSpec',
            width: "10%"
        }, {
            title: '单位',
            field: 'units',
            width: "7%",
            formatter:function(value,row,index){
                var label;
                $.each(specUnits, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '剂型',
            field: 'drugForm',
            width: "10%",
            formatter:function(value,row,index){
                var label;
                $.each(drugFormDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '毒理属性',
            field: 'toxiProperty',
            width: "10%",
            formatter:function(value,row,index){
                var label;
                $.each(drugToxi, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: 'OTC',
            field: 'otc',
            width: "3%",
            styler: function () {
                return "text-align: center"
            },

            formatter:function(value,row,index){
                if(value==1){
                    return '<input type="checkbox" name="drugDictCheckbox" style="height: 22px;width: 22px" checked="checked">';
                }
                else{
                    return '<input type="checkbox" name="drugDictCheckbox" style="height: 22px;width: 22px" >';
                }
            }
        }, {
            title: '单位剂量',
            field: 'dosePerUnit',
            width: "7%"
        }, {
            title: '剂量单位',
            field: 'doseUnits',
            width: "10%",
            formatter:function(value,row,index){
                var label;
                $.each(doseUnit, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '限制等级',
            field: 'limitClass',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    editable:false,
                    panelHeight: 'auto',
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    url: basePath  + "/dict/findListByType?type=LIMIT_CLASS_DICT"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(limitClassList, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }

        }, {
            title: '贵重等级',
            field: 'preciousFlag',
            width: "10%",
            editor: {
                type: 'combobox',
                options: {
                    editable:false,
                    panelHeight: 'auto',
                    valueField: 'value',
                    textField: 'label',
                    method: 'get',
                    url:basePath  + "/dict/findListByType?type=DRUG_PRECIOUS_FLAG_DICT"
                }
            },
            formatter:function(value,row,index){
                var label;
                $.each(drugPreciousFlagDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }, {
            title: '类别',
            field: 'drugIndicator',
            width: "10%",
            formatter:function(value,row,index){
                var label;
                $.each(drugTypeDict, function (index,item) {
                    if (item.value == value){
                        label =   item.label;
                    }
                });
                return label;
            }
        }
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex=index;
        }
    });

    $("#top").datagrid({
        toolbar: '#tb',
        fit: true,
        border: false
    });
    $("#bottom").datagrid({
        footer: '#ft',
        fit: true,
        border: false
    });

    $("#global").layout({
        fit: true
    });

    //清除
    var clear=function(){
        $("#drugNameDict").datagrid('loadData', {total: 0, rows: []});
        $("#drugDict").datagrid('loadData', {total: 0, rows: []});
    }
    //将改变的数据保存起来
    var saveChange=function(){
        stopEdit();
        var update=$("#drugDict").datagrid('getChanges','updated');
        console.log(update);

        for(var i=0;i<update.length;i++){
            var sign=true;
            for(var j=0;j<drugDictList.length;j++){
                if(update[i].id==drugDictList[j].id){
                    drugDictList[j]=update[i];
                    sign=false;
                }
            }
            if(sign==true){
                drugDictList.push(update[i]);
            }
        }
        console.log(drugDictList);
    }

    $("#clear").on('click',function(){
        clear();
        drugDictList=[];
        $("#drugClass").combobox('clear');
        $("#drugClassForm").combobox('clear');
        $("#drugName").combogrid('clear');
    });

    //保存
    $("#save").on('click',function(){
        saveChange();
        if(drugDictList.length>0){
            var drugNameDictChangeVo = {};
            drugNameDictChangeVo.inserted = [];
            drugNameDictChangeVo.updated = [];
            drugNameDictChangeVo.deleted = [];

            var drugDictChangeVo = {};
            drugDictChangeVo.inserted = [];
            drugDictChangeVo.updated = drugDictList;
            drugDictChangeVo.deleted = [];

            var drugCatalogBeanVo = {};
            drugCatalogBeanVo.drugDictDrugCatalogBeanVo = drugDictChangeVo;
            drugCatalogBeanVo.drugNameDictDrugCatalogBeanVo = drugNameDictChangeVo;

            $.postJSON(basePath + "/drug-catalog/save", JSON.stringify(drugCatalogBeanVo), function (data) {
                if("1" == data) {
                    $.messager.alert('提示', "保存成功", "info");
                } else {
                    $.messager.alert('提示', "保存失败", "error");
                }
                drugDictList=[];
                clear();
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }

    });

});
