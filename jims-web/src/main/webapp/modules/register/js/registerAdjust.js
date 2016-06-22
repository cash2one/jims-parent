var rowNum=-1;
var timeInterval=[];


/**
 * 时间
 * @type {{}}
 */
var timeIntervalData={};
timeIntervalData.orgId="";
timeIntervalData.dictType="TIME_INTERVAL_DICT"
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(timeIntervalData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        timeInterval=data;
    }
});

/**
 * 时间翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function timeDescFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < timeInterval.length; i++) {
        if (timeInterval[i].time_interval_code == value) {
            return timeInterval[i].time_interval_name;
        }
    }
}
function onloadMethod(){
    $('#timeDescId').combobox({
        data: timeInterval,
        valueField: 'time_interval_code',
        textField: 'time_interval_name'
    })
    $('#list_data_num').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
         url:basePath+'/clinicRegister/findList',
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicDate',title:'出诊日期',width:'18%',align:'center',formatter:formatDatebox},
            {field:'clinicLabelName',title:'号别名称',width:'20%',align:'center'},
            {field:'timeDesc',title:'出诊时间',width:'15%',align:'center',formatter:timeDescFormatter
            },
            {field:'registrationLimits',title:'限号数',width:'15%',align:'center',editor:{type:'numberbox'},formatter:function(value, rowData, rowIndex){
                if(value=='' || value==null){
                    return "0"
                }else{
                    return value;
                }
            }},
            {field:'currentNo',title:'当前号',width:'15%',align:'center'},
            {field:'appointmentLimits',title:'限预约号数',width:'15%',align:'center',editor:{type:'numberbox'},formatter:function(value, rowData, rowIndex){
                if(value=='' || value==null){
                    return "0"
                }else{
                    return value;
                }
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar:'#time',
        onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#list_data_num');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }else{
                if(rowNum!=rowIndex){
                    if(rowNum>=0){
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum=rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }
        }
    });

    //设置分页控件
    var p = $('#list_data_num').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
}
/**
 * 查询
 */
function searchClinicAdjust(){
    var timeInterval=$("#timeDescId").combobox('getValue');
    var clinicDate=$("#clinicDateId").datebox('getValue');
    var clinicIndexName=$("#clinicNameId").val();
    $("#list_data_num").datagrid('reload',{"clinicIndexName":clinicIndexName,"timeDesc":timeInterval,"clinicDateStr":clinicDate});
}
/**
 * 修改
 */
function update(){
    $("#list_data_num").datagrid('endEdit', rowNum);
    var  rows=$('#list_data_num').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    //alert(tableJson);
    $.postJSON(basePath+'/clinicRegister/update',tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息","保存成功");
            $('#list_data_num').datagrid('load');
            $('#list_data_num').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}

