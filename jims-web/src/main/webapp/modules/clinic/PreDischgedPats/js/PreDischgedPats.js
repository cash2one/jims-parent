var rowNum = -1;

var  strValue   = "160101" ;

$(function(){
    $('#list_doctor').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/preDischgedPats/list?wardCode=' + strValue,
        remoteSort: false,
        idField: 'patientId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床标号', width: '50%', align: 'center'},
            {
                field: 'name',
                title: '姓名',
                width: '50%',
                align: 'center'

            },
            {field:'sex',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'patientId',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'admissionDateTime',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'diagnosis',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'bedNo',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'deptCode',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'deptCode',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'visitId',hidden:'false',editor:{type:'textbox',options:{editable:false,disable:false}}}
        ]],onClickRow:function(index,row){
            $('#list_data').datagrid('insertRow', {
                index:1,	// index start with 0
                row: {
                    bedNo: row.bedNo,
                    name:row.name,
                    sex:row.sex,
                    patientId:row.patientId,
                    diagnosis:row.diagnosis,
                    admissionDateTime:row.admissionDateTime,
                    deptName:row.deptCode,
                    visitId:row.visitId
                }
            });
        },
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]]
    });

});

function onloadMethod() {
    var wardCode = $("#wardCode").val();
    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'post',
        collapsible: false,//是否可折叠的
        url: basePath + '/preDischgedPats/findPreDischList?wardCode=' + wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '5%', align: 'center', editor: 'text'},
            {
                field: 'dischargeDateExpcted',
                title: '预计出院时间',
                width: '20%',
                align: 'center',
                editor:{type: 'datebox',options:{editable:true,disable:false}},
                formatter: formatDateBoxFull
            },
            {field: 'name', title: '姓名', width: '10%', align: 'center'},
            {field: 'sex', title: '性别', width: '5%', align: 'center'},
            {field: 'dischargeDispositionName', title: '出院方式', width: '10%',fomatter:perFormatter, align: 'center', editor:
            {type:'combobox',
                options: {
                    data: predischarge,
                    valueField: 'discharge_code',
                    textField: 'discharge_name'
                ,onSelect: function () {
                var chargeName = $("#list_data").datagrid('getEditor',{index:rowNum,field:'dischargeDispositionName'});
                var chargeNameValue = $(chargeName.target).combobox('getValue');
                alert(chargeNameValue);
                        if(chargeNameValue!=null){
                        var orderText = $("#list_data").datagrid('getEditor',{index:rowNum,field:'orderText'});
                        var orderCode = $("#list_data").datagrid('getEditor',{index:rowNum,field:'orderCode'});
                        getDis(chargeNameValue);
                    if(dis!=null){
                        for (var i = 0; i < dis.length; i++) {
                                        $(orderText.target).textbox('setValue',dis[i].orders_text);
                                        $(orderCode.target).textbox('setValue',dis[i].orders_code);
                                    }
                    }else{
                                    $(orderText.target).textbox('setValue','');
                                    $(orderCode.target).textbox('setValue','');
                           }
                        }
            }}}},
            {field: 'patientId', title: '病人ID', width: '10%', align: 'center',  editor: 'text'},
            {field: 'diagnosis', title: '诊断', width: '20%', align: 'center'},
            {
                field: 'admissionDateTime',
                title: '入院日期',
                width: '20%',
                align: 'center',
                formatter: formatDateBoxFull
            },
            {field: 'deptName', title: '所在科室', width: '20%', align: 'center', editor: 'text'},
            {field: 'visitId',hidden:'true'},
            {field:'orderText',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}},
            {field:'orderCode',hidden:'true',editor:{type:'textbox',options:{editable:false,disable:false}}}
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                if (rowNum >= 0) {
                    rowNum++;
                }
                $("#list_data").datagrid('insertRow', {
                    index: 0,
                    row: {}

                });
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                deleteItem();
            }
        }, '-', {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                $("#list_data").datagrid('endEdit', rowNum);
                save();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                $("#list_data").datagrid('endEdit', rowNum);
                reload();
            }
        }
        ], onClickRow: function (rowIndex, rowData) {
            var dataGrid = $('#list_data');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false
            }
            if (rowNum != rowIndex) {
                if (rowNum >= 0) {
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum = rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);
            }

        }
    });












    //设置分页控件
    var p = $('#list_doctor').datagrid('getPager');

}

//加载右边患着信息
$("#wardCode").combobox({
    onChange: function (n, o) {
        strValue   = $("#wardCode").combobox("getValue");

    }
});




//保存医嘱和通知数据
function save() {
    var rows = $('#list_data').datagrid('getChanges');
    var tableJson = JSON.stringify(rows);
    $.postJSON(basePath + '/preDischgedPats/save', tableJson, function (data) {
        if (data.data == 'success') {
            $.messager.alert("提示消息", data.code + "条记录，保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $('#list_doctor').datagrid('load');
            $('#list_doctor').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })
}


//删除数据
function deleteItem() {
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        var index1 = $('#list_data').datagrid('getRowIndex', $("#list_data").datagrid('getSelected'))
        $('#list_data').datagrid('deleteRow', index1);
    })
}


//刷新
function reload(){
    $("#list_data").datagrid("reload");
}