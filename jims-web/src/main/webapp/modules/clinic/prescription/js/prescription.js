var editRow = undefined;
var visitDate='2015-06-09';
var visitNo='410';
var prescNo ='';
var serialNo='';
var itemClass='西、成药';
var chargeIndicator='新开';

$(function(){

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
        ]]
    });
    $('#list_data').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'GET',
        url:basePath+'/outppresc/list',
        columns:[[      //每个列具体内容
            {field:'orderNo',title:'处方号',width:'5%',align:'center',formatter: remarkFormater},
            {field:'drugName',title:'药名',width:'10%',align:'center',editor:{
                type:'combobox',
                options:{required:true,
                    url: basePath+'/outppresc/dictlist',
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].label);
                    }
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
                options:{required:true,
                    url: basePath+'/outppresc/dictlist',
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].label);
                    }
                }
            }},
            {field:'frequency',title:'频次',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{required:true,
                    url: basePath+'/outppresc/dictlist',
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].label);
                    }
                }
            }},
            {field:'abidance',title:'用药天数',width:'5%',align:'center',editor:'numberbox'},
            {field:'charges',title:'实收',width:'5%',align:'center'},
            {field:'itemClass',title:'药局',width:'5%',align:'center'},
            {field:'freqDetail',title:'医生说明',width:'5%',align:'center',editor:'text'},
            {field:'providedIndicator',title:'取药属性',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{required:true,
                    url: basePath+'/outppresc/dictlist',
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].label);
                    }
                }
            }},
            /*   {field:'skinFlag',title:'代煎',width:'5%',align:'center',editor:'text'},*/
            {field:'skinFlag',title:'皮试结果',width:'5%',align:'center',editor:{
                type:'combobox',
                options:{required:true,
                    url: basePath+'/outppresc/dictlist',
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].label);
                    }
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
               var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
                  if(selRow!=null&&selRow!=''&&selRow!='undefined'){
                    $("#list_data").datagrid('insertRow', {
                        index:0,
                        row:{}
                    });
                }else{
                      $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
                      return;
                  }
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
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
        },onClickRow:function(rowIndex,rowData){
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_data").datagrid('endEdit', editRow);
            }
        }
    });
});

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

//保存处方及药品信息
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

function giveUpPre(){//弃方即刷新页面
    $('#leftList').datagrid('load');
    $('#leftList').datagrid('clearChecked');
    $('#list_data').datagrid('load');
    $('#list_data').datagrid('clearChecked');
}

//批量删除药品信息
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //真删除数据
            $.ajax({
                'type': 'POST',
                'url': basePath+'/outppresc/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.data=='success'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#list_data').datagrid('load');
                        $('#list_data').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"保存失败", "error");
                }
            });
        }
    })
}

var remarkFormater=function(value, row, index) {
    //alert("value="+value+"  index="+index);
    var content = '';
    var abValue = value +'';
    if(value != undefined){
        if(value.length>=22) {
            abValue = value.substring(0,19) + "...";
            content = '<a href="javascript:;" ' +' title="' + value + '" ' + 'class="easyui-tooltip">' + abValue + '</a>';
        }else{
            content = '<a href="javascript:;"  title="' + abValue + '" class="easyui-tooltip">' + abValue + '</a>';
        }
    }
    return content;
}
