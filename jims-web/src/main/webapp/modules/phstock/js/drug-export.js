/**
 * 出库处理
 * @author luohk
 * @version 2016-05-14
 */
$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
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


    var currentSelectIndex   // datagrid 当前选择的行索引
        ,currentOrgId = parent.config.org_Id  // 当前登录人所属单位ID
        ,currentStorage = parent.config.currentStorage  // 当前登录人所属管理单位
        ,currentUsername = '录入者'   // 当前登录人姓名
        ,currentAccountFlag    //记账标志 0，不记账，1记账
        ,drugDicts = []  // 检索的药品字典数据
        ,initDrugPriceWindowFlag = true
    var currentSubStorageDept   // 当前选择的库房子库房数据

    /**
     * 加载库存中有余量的药品名称数据
     */
    var loadDrugNameData = function(){
        var chargeType = {colName:'storage',colValue:currentStorage,operateMethod:"="}
        var param = {dictType:'v_drug_stock_name_dict',orgId:currentOrgId,inputParamVos:[chargeType]}
        parent.$.postJSON('/service/input-setting/listParam',
            JSON.stringify(param) ,function(res){
            drugDicts = res
        })
    }

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

    /**
     * 校验通过则结束编辑
     * @returns {boolean}
     */
    var endEditing = function () {
        if (currentSelectIndex == undefined) {
            return true
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
            if (index == $('#dg').datagrid('getRows').length - 1) return
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
     * 合并合计单元格
     */
    var mergeLastCells = function () {
        var _index = $('#dg').datagrid('getRows').length - 1
        $('#dg').datagrid('mergeCells', {index: _index, field: 'drugCode', rowspan: null, colspan: 10})
        $('#dg').datagrid('mergeCells', {index: _index, field: 'batchNo', rowspan: null, colspan: 2})
    }

    $("#dg").datagrid({
        fit: true,
        fitColumns: false,
        striped: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
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
            field: "firmId",
            width: 200,
            align: 'center'
        }, {
            title: "药名",
            field: "drugName",
            width: 220,
            align: 'center',
            editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 333,
                    idField: 'drug_name',
                    textField: 'drug_name',
                    required: true,
                    missingMessage: '药名不能为空',
                    fitColumns: true,
                    data: drugDicts,
                    columns: [[
                        {field: 'drug_code', title: '药品代码', width: 100, align: "center"},
                        {field: 'drug_name',
                            title: '药品名称',
                            width: 160,
                            align: "center",
                            formatter: function (value) {
                                return '<div style="text-align:left">' + value + '</div>'
                            }
                        },
                        {field: 'input_code', title: '输入码', width: 70, align: "center"}
                    ]],
                    onChange: function (newV, oldV) {
                        if (newV != oldV)return
                    },
                    filter: function (field, row) {
                        if (row['drug_code'].toUpperCase().indexOf(field.toUpperCase()) > -1
                            || row['input_code'].toUpperCase().indexOf(field.toUpperCase()) > -1
                            || row['drug_name'].toUpperCase().indexOf(field.toUpperCase()) > -1) {
                            return true
                        }
                    },
                    onClickRow: function (index, row) {
                        //loadDrugPriceData(row)
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
                    required: true
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
        }, {
            title: "零售价",
            field: "retailPrice",
            width: 70,
            align: 'center'
        }, {
            title: "出库金额",
            field: "outPrice",
            width: 70,
            align: 'center'
        }, {
            title: "批号",
            field: "batchNo",
            width: 70,
            align: 'center'
        }, {
            title: "有效期",
            field: "expireDate",
            width: 100,
            align: 'center'
        }
        ]],
        onClickCell: onClickCell
    })

    $('#statisticClass').combobox({
        url: parent.basePath + '/drug-export/findAll',
        valueField: 'exportClass',
        textField: 'exportClass',
        method: 'GET',
        onSelect: function(record){
            currentAccountFlag = record['accountFlag']
            $("#dg").datagrid('loadData',[])
            if(record['exportClass'] == '退药出库'){
                $("#subStorageDept").combobox({
                    disabled:true,
                    value: '*'
                });
                $.get('/service/drug-supplier-catalog/list',{orgId:currentOrgId},function(res){
                    $('#storageDept').combobox({
                        valueField : 'supplierCode',
                        textField : 'supplier',
                        width: 140,
                        data : res
                    })
                })
            } else {
                $("#subStorageDept").combobox('loadData',[])
                $("#subStorageDept").combobox({
                    disabled: false,
                    value: ''
                })
                $.get('/service/drug-storage-dept/list',{orgId:currentOrgId,storageType:(record['storageType'] == '全部'?'':record['storageType'])},function(res){
                    $('#storageDept').combobox({
                        valueField : 'storageCode',
                        textField : 'storageName',
                        data : res,
                        onSelect : function(r){
                            loadSubDept('subStorageDept',currentOrgId,r['storageCode'])
                        }
                    })
                })
            }
        }
    })
    $('#calendar').datebox({
        width: 140,
        editable: true,
        value: parent.formatDatebox(new Date())
    })
    $('#stockSubDept').combobox({
        valueField:'subStorageCode',
        textField:'subStorage',
        width:160,
        onSelect:function(record){
            loadDrugNameData()
            $("#dg").datagrid('loadData',[])
            currentSubStorageDept = record
            var prefix = record['exportNoPrefix']
            var suffix = record['exportNoAva']
            if(prefix == undefined) prefix = ''
            if(suffix == undefined) suffix = ''
            var len = (prefix + suffix).length
            var v = len > 9 ? (prefix + suffix).substr(0,10) : prefix + '0000000000'.substr(len - 10) + suffix
            $('#documentNo').textbox('setValue',v)
        }
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
            batchNo: 'X'
        }

        $("#dg").datagrid('insertRow',{index:currentSelectIndex,row: record});
        $("#dg").datagrid('selectRow', currentSelectIndex);
    });

    $("#delBtn").on('click', function () {
        if (currentSelectIndex != undefined) {
            if(currentSelectIndex == $('#dg').datagrid('getRows').length - 1) return
            $("#dg").datagrid('deleteRow', currentSelectIndex);
            currentSelectIndex = undefined
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

    /**
     * 保存修改的内容
     * 基础字典的改变，势必会影响其他的统计查询
     * 基础字典的维护只能在基础数据维护的时候使用。
     */
    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateDate = $("#dg").datagrid("getChanges", "updated");
        var deleteDate = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteDate;
        beanChangeVo.updated = updateDate;


        if (beanChangeVo) {
            parent.$.postJSON("", beanChangeVo, function (data, status) {
                $.messager.alert("系统提示", "保存成功", "info");
                $('#dg').datagrid('load');
                $('#dg').datagrid('clearChecked');
            }, function (data) {
                $.messager.alert('提示', data.responseJSON.errorMessage, "error");
            })
        }
    });

    //设置是否禁用控件
    $("#subStorageDept").textbox({'disabled':true});
    loadSubDept('stockSubDept',currentOrgId,currentStorage)
});