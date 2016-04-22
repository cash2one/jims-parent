var editRow = undefined;
var visitDate='2015-06-09';
var visitNo='410';
var prescNo ='';
var serialNo='';
var itemClass='西、成药';
var chargeIndicator='新开';
var administration = [{ "value": "口服", "text": "口服" }, { "value": "静脉注射", "text": "静脉注射" }, { "value": "小儿头皮静脉", "text": "小儿头皮静脉" }, { "value": "静脉输液", "text": "静脉输液" }, { "value": "续静滴", "text": "续静滴" }];
var frequency = [{ "value": "一日一次", "text": "一日一次" }, { "value": "一日二次", "text": "一日二次" }, { "value": "一日三次", "text": "一日三次" }];
$(function(){

    $('#list_data').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list',
        columns:[[      //每个列具体内容
            {field:'orderNo',title:'处方号',width:'5%',align:'center'},
            {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                type:'text',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'drugSpec',title:'规格',width:'5%',align:'center'},
            {field:'firmId',title:'厂家',width:'5%',align:'center'},
            {field:'amount',title:'药品数量',width:'5%',align:'center',editor:'numberbox'},
            {field:'units',title:'单位',width:'5%',align:'center'},
            {field:'performNurse',title:'剂量',width:'5%',align:'center',editor:'numberbox'},
            {field:'dosage',title:'单次用量',width:'5%',align:'center',editor:'text'},
            {field:'dosageUnits',title:'用量单位',width:'5%',align:'center'},
            {field:'administration',title:'途径',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :frequency,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center'},
            {field:'itemClass',title:'药局',width:'5%',align:'center'},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                /* method: 'get',
                 url: basePath+'dict/',
                 valueField: 'id',
                 textField: 'name',
                 filter: function (q, row) {
                 var ret = false;
                 //拼音
                 var spell = row['spell'];
                 if (spell && spell.indexOf(q) >= 0) {
                 ret = true;
                 }
                 //textField
                 if (row[$(this).combobox('options').textField].indexOf(q) >= 0) {
                 ret = true;
                 }
                 return ret;
                 },*/
                options:{
                    required:true
                }
            }},
            /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
            {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{
                    valueField:'productid',
                    textField:'name',
                    data:1,
                    required:true
                }
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                $("#list_data").datagrid('insertRow', {
                    index:0,
                    row:{}
                });
                //if (editRow != undefined) {
                //    $("#list_data").datagrid('endEdit', editRow);
                //}
                //if (editRow == undefined) {
                //    $("#list_data").datagrid('insertRow', {
                //        index: 0,
                //        row:{}
                //    });
                //    $("#list_data").datagrid('beginEdit', 0);
                //    editRow = 0;
                //}
            }
        }, '-', {
            text: '保存', iconCls: 'icon-save', handler: function () {
                $.postRows(basePath+'/outppresc/save','list_data',function(data){
                    if(data.data=='success'){
                        $.messager.alert("提示消息",data.code+"条记录，已经删除");
                        $('#list_data').datagrid('load');
                        $('#list_data').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"保存失败", "error");
                    }
                },function(data){
                    $.messager.alert('提示',"保存失败", "error");
                })
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }

            if (editRow == undefined) {
                $("#list_data").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },
        onClickRow:function(rowIndex,rowData){
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);

            }

        }
    });


    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list',
        columns:[[      //每个列具体内容
            {field:'visitDate',title:'就诊时间',width:'20%',align:'center'},
            {field:'visitNo',title:'就诊序号',width:'15%',align:'center'},
            {field:'serialNo',title:'开单序号',width:'15%',align:'center'},
            {field:'prescNo',title:'处方号',width:'15%',align:'center'},
            {field:'itemClass',title:'处方分类',width:'15%',align:'center'},
            {field:'chargeIndicator',title:'收费状态',width:'15%',align:'center'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                if (editRow != undefined) {
                    $("#list_data").datagrid('endEdit', editRow);
                }
                if (editRow == undefined) {
                    $("#list_data").datagrid('insertRow', {
                        index: 0,
                        row:{}
                    });
                    $("#list_data").datagrid('beginEdit', 0);
                    editRow = 0;
                }
            }
        }],onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            $("#list_data").datagrid('endEdit', editRow);
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }

            if (editRow == undefined) {
                $("#list_data").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        }, onClickRow:function(rowIndex,rowData){
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);

            }

        }
    });
});
//删除用户数据
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }

    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            //真删除了  1,3,4
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            $.messager.alert("提示消息","删除成功~~");
        }
    })
}

function addPre(){//点击新方
    $('#leftList').datagrid('insertRow', {
        url:{},//
        index:0,	// index start with 0
        row: {
            visitDate: visitDate,
            visitNo: visitNo,
            serialNo: serialNo,
            prescNo: prescNo,
            itemClass:itemClass,
            chargeIndicator:chargeIndicator
        }
    });
}

function savePre(){
    $("#list_data").datagrid('endEdit', editRow);
    var  rows=$('#list_data').datagrid('getRows');
    var formJson=fromJson('prescForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"list\":"+tableJson+"}";
    $.postJSON(basePath+'/outppresc/save',submitJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}