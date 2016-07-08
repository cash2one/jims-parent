var rowNum=-1;
var patientId =parent.patVisit.patientId;
var zhuyuanId = parent.patVisit.visitId;
var visitDate='2015-06-09';
var visitNo='410';
var prescNo ='';
var serialNo='';
var doctorid ="5";
function onloadMethod() {
    $("#patientId").val("06b45a7a-b286-11e5-b259-0894ef010b20");
    /**
     * 会诊类型
     */
    $("#grouptype").combobox({
        data: groupType,
        valueField: 'value',
        textField: 'label',
        onSelect: function (data) {
            $("#groupTypeId").val(data.value);
        }
    })

    changeYiJian("4");
    $('#list_doctor').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method: 'post',
        url: basePath + '/group/doctorlist',
        columns: [[
            {field: 'id', title: 'id', hidden: true, align: 'center'},
            //每个列具体内容
            {
                field: 'officeId',
                title: '科室',
                width: '20%',
                align: 'center',
                formatter: clinicDeptCodeFormatter,
                editor: {
                    type: 'combobox',
                    options: {
                        data: clinicDeptCode,
                        valueField: 'id',
                        textField: 'dept_name',
                        required: true
                    }
                }
            },

            {
                field: 'doctorId',
                title: '参与医生',
                width: '20%',
                align: 'center',
                formatter: doctorNameFormatter,
                editor: {
                    type: 'combobox',
                    options: {
                        data: doctorName,
                        valueField: 'id',
                        textField: 'name',
                        required: true
                        //columns:[[
                        //    {field:'name',title:'医生姓名',width:70},
                        //    {field:'dept_name',title:'科室',width:120},
                        //    {field:'title',title:'职称',width:70}
                        //]]
                        //onClickRow: function (index, data) {
                        //    var rows = $('#list_data').datagrid("getRows"); // 这段代码是// 对某个单元格赋值
                        //    var columns = $('#list_data').datagrid("options").columns;
                        //    rows[rowNum][columns[0][4].field]=data.title;
                        //    $('#list_data').datagrid('endEdit', rowNum);
                        //    $('#list_data').datagrid('beginEdit', rowNum);
                        //}
                    }
                    //}},
                    //    }
                }
            },
            {field: 'inHuizhenyijian', title: '意见', width: '60%', align: 'center',editor :'text'}
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                if (rowNum >= 0) {
                    rowNum++;
                }
                changeYiJian(0);
                $("#list_doctor").datagrid('insertRow', {
                    index: 0,
                    row: {}
                });
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                inDoDelete();
            }
        }],
        onClickRow: function (rowIndex, rowData) {
            var dataGrid = $('#list_doctor');
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
        //onAfterEdit: function (rowIndex, rowData, changes) {
        //    editRow = undefined;
        //},onDblClickRow:function (rowIndex, rowData) {
        //    if (editRow != undefined) {
        //        $("#list_doctor").datagrid('endEdit', editRow);
        //    }
        //    if (editRow == undefined) {
        //        $("#list_doctor").datagrid('beginEdit', rowIndex);
        //        editRow = rowIndex;
        //    }
        //},onClickRow:function(rowIndex,rowData){
        //    //tooltips选中行，药品价目列表信息
        //    if (editRow != undefined) {
        //        $("#list_doctor").datagrid('endEdit', editRow);
        //    }
        //}
    });
    //$('#list_doctor').datagrid('hideColumn','officeId');

    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/group/list',
        QueryParams: {patientId: patientId},
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        rownumbers: true,//行号
        columns: [[      //每个列具体内容
            {field: 'shenqingshijian', title: '会诊时间', width: '35%', align: 'center', formatter: formatDateBoxFull},
            {
                field: 'id', title: '操作', width: '60%', align: 'center', formatter: function (value, row, index) {
                var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="get(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>';
                if (row.ideaFlag != "1") {
                    if (doctorid == row.doctorId) {
                        html = html + '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\'' + value + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>';
                        html = html + '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                    }
                }
                if (row.fabuflag != "1") {
                    html = html + '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="fabuRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>发布</button>';
                } else {
                    if (doctorid == row.doctorId) {
                        html = html + '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="ideaRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>意见1</button>';
                    } else {
                        html = html + '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="ideaOtherRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>意见2</button>';
                    }
                }
                return html;
            }
            }
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                clearForm();
                changeYiJian("0");
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                changeYiJian("0");
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("提示消息", "请选中要修改的数据!");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }]
    });
};
//设置分页控件
var p = $('#list_data').datagrid('getPager');
$(p).pagination({
    beforePageText: '第',//页数文本框前显示的汉字
    afterPageText: '页    共 {pages} 页',
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
});

function save(){
    formSubmitInput("form");
    //加载值
    $("#doctorId").val("5");
    $("#zhuyuanId").val("03e681f7-ba5e-11e5-bb7f-0894ef010b21");
    $("#patientId").val("06b45a7a-b286-11e5-b259-0894ef010b20");
    $("#list_doctor").datagrid("endEdit", rowNum);
    var  rows=$('#list_doctor').datagrid('getRows');
    var formJson=fromJson('form');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"electronGroupConsultationInList\":"+tableJson+"}";
    alert(submitJson);
    $.postJSON(basePath+'/group/save',submitJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            clearForm();

        }else{
            $.messager.alert('提示',"保存失败", "error");
            clearForm();
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
        clearForm();
    })
}
//保存会诊者发表意见
function saveMainOnlyBut(){
    formSubmitInput("form");
    var id = $("#id").val();
    var huizhenyijian = $("#huizhenyijian").val();
    var jsonstr = '{"id":"'+id+'","huizhenyijian":"'+huizhenyijian+'"}';
    $.postJSON(basePath+'/group/savemainonly',jsonstr,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","保存成功");
            clearForm();
        }else{
            $.messager.alert('提示',"保存失败", "error");
            clearForm();
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
        clearForm();
    })

}
//保存会诊者发表意见
function saveOtherBut(){
    formSubmitInput("form");
    var id = $("#id").val();
    var inHuizhenyijian = $("#inHuizhenyijian").val();
    var jsonstr = '{"id":"'+id+'","inHuizhenyijian":"'+inHuizhenyijian+'"}';
    $.postJSON(basePath+'/group/saveotheridea',jsonstr,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","保存成功");
            clearForm();
        }else{
            $.messager.alert('提示',"保存失败", "error");
            clearForm();
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
        clearForm();
    })
}
//根据主表iD和医生ID查询参与表信息
function addidea(id){
    var doctorId ="3";
    var jsonstr = '{"huizhenId":"'+id+'","doctorId":"'+doctorId+'"}';
    $.ajax({
        'type': 'post',
        'url': basePath+'/group/getgroupin',
        'contentType': 'application/json',
        'data':jsonstr,
        'dataType': 'json',
        'success': function(data){
            $('#form').form('load',data);
            getDiv("form");
        }
    });
}
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
            del(strIds);
        }
    })
}
//行删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}
//行编辑
function get(id) {
    $.ajax({
        'type': 'post',
        'url': basePath+'/group/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#form').form('load',data);
            getDiv("form");
            changeYiJian("0");
            var id= $("#id").val();
            $('#list_doctor').datagrid({ url:basePath+"/group/doctorlist",queryParams:{'a.huizhen_id':id},method:"post"});
        }
    });
}
function look(id){
    alert(id);
    $.ajax({
        'type': 'post',
        'url': basePath+'/group/get',
        'data': {id :id},
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            $('#form').form('load',data);
            getDiv("form");
            var id= $("#id").val();
            $('#list_doctor').datagrid({ url:basePath+"/group/alldoctorlist",queryParams:{'a.huizhen_id':id},method:"post"});
            changeYiJian("4");
        }
    });
    /**
     * 发布医生查看
     */
}function look(id){
    $.ajax({
        'type': 'post',
        'url': basePath+'/group/get',
        'data': {id :id},
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            $('#form').form('load',data);
            getDiv("form");
            var id= $("#id").val();
            $('#list_doctor').datagrid({ url:basePath+"/group/alldoctorlist",queryParams:{'a.huizhen_id':id},method:"post"});
            changeYiJian("4");
        }
    });
}
//发布会诊者发表意见
function ideaRow(id) {
    $.ajax({
        'type': 'post',
        'url': basePath+'/group/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#form').form('load',data);
            getDiv("form");
            var id= $("#id").val();
            $('#list_doctor').datagrid({ url:basePath+"/group/alldoctorlist",queryParams:{'a.huizhen_id':id},method:"post"});
            changeYiJian("1");
        }
    });
}
//参与会诊者发表意见
function ideaOtherRow(id) {
    $.ajax({
        'type': 'post',
        'data': id=id,
        'url': basePath+'/group/get',
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            $('#form').form('load',data);
            getDiv("form");
            var id= $("#id").val();
            $('#list_doctor').datagrid({ url:basePath+"/group/alldoctorlist",queryParams:{'a.huizhen_id':id},method:"post"});
            changeYiJian("2");
            addidea(id);
        }
    });
}

/**
 * 删除方法
 * @param id
 */
function del(id){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/group/del',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                if(data.code>0){
                    $.messager.alert("提示消息",data.code+"条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                }else{
                    $.messager.alert('提示',"删除失败", "error");
                }
            }else{
                $.messager.alert('提示',"删除失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"保存失败", "error");
        }
    });
}
/**
 * 发布方法
 * @param id
 */
function fabuRow(id){
    $.messager.confirm("确认消息", "您确定要发布信息吗？", function (r) {
        if (r) {
            $.ajax({
                'type': 'POST',
                'url': basePath+'/group/fabu',
                'contentType': 'application/json',
                'data': id=id,
                'dataType': 'json',
                'success': function(data){
                    if(data.data=='success'){
                        $.messager.alert("提示消息","发布成功");
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
    });
}
function inDoDelete() {
    var rows = $('#list_doctor').datagrid("getSelections");
    if (rows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    var copyRows = [];
    for ( var j= 0; j < rows.length; j++) {
        copyRows.push(rows[j]);
    }
    for(var i =0;i<copyRows.length;i++){
        if (typeof(copyRows[i].id) != "undefined") {
        }
        var index = $('#list_doctor').datagrid('getRowIndex',copyRows[i]);
        $('#list_doctor').datagrid('deleteRow',index);
    }
}
/**
 * 删除方法
 * @param id
 */
function inDel(id){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/group/indel',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                if(data.code>0){
                    $.messager.alert("提示消息",data.code+"条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                }else{
                    $.messager.alert('提示',"删除失败", "error");
                }
            }else{
                $.messager.alert('提示',"删除失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"保存失败", "error");
        }
    });
}

//控制意见框的显示：1发布人意见、2参与人意见
function changeYiJian(state){
    $("#huizhenyijianDiv *").attr("disabled",true);
    $("#huizhenyijianDiv").hide();
    $("#inHuizhenyijianDiv *").attr("disabled",true);
    $("#inHuizhenyijianDiv").attr("disabled",true);
    $("#saveOtherBut").hide();
    $("#saveBut").hide();
    $("#saveMainOnlyBut").hide();
    $("#inHuizhenyijianDiv").hide();
    if(state==1){
        //隐藏功能栏
        $('form div.datagrid-toolbar').hide();
        $("#huizhenyijianDiv *").attr("disabled",false);
        $("#huizhenyijianDiv").show();
        $("#saveMainOnlyBut").show();
    }
    if(state==2){
        //隐藏功能栏
        $('form div.datagrid-toolbar').hide();
        $("#inHuizhenyijianDiv *").attr("disabled",false);
        $("#inHuizhenyijianDiv").show();
        $("#saveOtherBut").show();
    }
    if(state==0){
        $('form div.datagrid-toolbar').show();
        $("#saveBut").show();
    }
    if(state==4){
        $('form div.datagrid-toolbar').hide();
    }
}
//编辑页面清空
function clearForm(){
    $("#form").form('clear');
    $("[submit_id='bingqingzhaiyao']").html("");
    $("[submit_id='huizhenliyou']").html("");
    $("[submit_id='huizhenyijian']").html("");
    $("#saveOtherBut").hide();
    $("#saveBut").hide();
    $("#saveMainOnlyBut").hide();
    $('#list_doctor').datagrid('loadData', { total: 0, rows: [] });
}