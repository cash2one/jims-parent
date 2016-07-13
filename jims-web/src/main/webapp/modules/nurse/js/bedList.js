var column={};
var patId;
var editRow = undefined;
var rowNum=-1;
var wardCode='160101';
$(function(){
    $('#bedRec').datagrid({
     /*   view: myview,
        emptyMsg: '没有查到相关信息',*/
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/findBedInfo?wardCode='+wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true, //分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center',editor:'text', required: true
                ,keyHandler: {
                up: function() {},
                down: function() {},
                enter: function() {},
                query: function(q) {
                    $.ajax({
                        method:"POST",
                        contentType: "application/json", //必须有
                        dataType: 'json',
                        data: JSON.stringify({"wardCode":wardCode,"bedNo":q}),
                        url: basePath + '/bedRec/judgeBedNo',
                        success: function (data) {
                            if(data){
                                $.messager.alert('提示',"该床位号已经存在，不能重复", "error");
                                return "";
                            }else{
                                return row.bedNo;
                            }
                        }
                    })

                }
            }
                /*     ,formatter:function(value, row, index){
           var editors = $('#bedRec').datagrid('getEditors', index);
                console.info(editors[1]);
                var sfgzEditor = editors[1];
                sfgzEditor.target.bind('change',function () {
                    console.info("111");
                    console.info(sfgzEditor.target.val());
                    $.ajax({
                        method:"POST",
                        contentType: "application/json", //必须有
                        dataType: 'json',
                        data: JSON.stringify({"wardCode":wardCode,"bedNo":row.bedNo}),
                        url: basePath + '/bedRec/judgeBedNo',
                        success: function (data) {
                            if(data){
                                $.messager.alert('提示',"该床位号已经存在，不能重复", "error");
                                return "";
                            }else{
                                return row.bedNo;
                            }
                        }
                    })
                }); }*/

           },
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center',editor:{
                type:'combobox',
                options:{
                    url: basePath+'/dict/label-value-list?type='+"SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET'

            }
        },formatter:function (value, rowData, RowIndex) {
            if(value=='1'){
                return "女";
            }else if(value=='2'){
                return "男";
            }
        }},
            {field: 'bedClass', title: '等级', width: '20%',formatter:bedClassFormatter, align: 'center',editor:{
                type:'combobox',
                options: {
                    data:bedClass,
                    valueField: 'item_code',
                    textField: 'item_name'

                }
                }},
            {field: 'airconditionClass', title: '空调等级',formatter:airconditionFormatter, width: '15%', align: 'center',editor:{
                type:'combobox',
                options: {
                   data:airconditionClass,
                    valueField: 'item_code',
                    textField: 'item_name'
                }
            }},
            {field: 'bedApprovedType', title: '类型', width: '15%',formatter:bedFormatter,align: 'center',editor:{
                type:'combobox',
                options: {
                   data:bedApprovedType,
                    valueField: 'value',
                    textField: 'label'

                }
            }},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center',formatter:function(value, row, index){
                 if(row.bedStatus=='0'||row.bedStatus=='' || row.bedStatus ==null){
                     return "是";
                 }else{
                     return "否";
                 }

            }},
            {field:'wardCode',editor:{type:'textbox',options:{editable:true,disable:false}},hidden:'true'}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                if (rowNum >= 0) {
                    rowNum++;
                }
                $("#bedRec").datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {
                        wardCode:wardCode
                    }
                });

            }
        },{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#bedRec").datagrid('endEdit', rowNum);
                if (rowNum != -1) {
                    $("#bedRec").datagrid("endEdit", rowNum);
                }
                save();
            }
        },{
            text: '换床',
            iconCls:'icon-reload',
            handler:function(){
                changeBed();
            }
        },
            {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete();
                }
            }],onAfterEdit: function (rowIndex, rowData, changes) {
            $("#bedRec").datagrid('endEdit', rowIndex);


        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#bedRec").datagrid('endEdit', rowNum);
            }
            if (editRow == undefined) {
                $("#bedRec").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData) {
            var dataGrid = $('#bedRec');
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
        },/*onClickCell: function (rowIndex, field, value) {
            beginEditing(rowIndex, field, value)
        },*/onLoadSuccess: function (data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="8" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }
});


    //设置分页控件
    var p = $('#bedRec').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });



/*    var beginEditing = function (rowIndex, field, value) {
        if (field != "bedNo")
            return;

        if (rowIndex != rowNum) {

                $('#bedRec').datagrid('beginEdit', rowIndex);
                rowNum = rowIndex;

                var ed = $('#bedRec').datagrid('getEditor', { index: rowIndex, field: 'bedNo' });
          var rowContent =  $('#bedRec').datagrid('getSelected');
                 var number = $(ed.target).text('getValue');
            alert(rowContent.bedNo+"test");
                $(ed.target).focus().bind('blur', function () {
                    $.ajax({
                        method:"POST",
                        contentType: "application/json", //必须有
                        dataType: 'json',
                        data: JSON.stringify({"wardCode":wardCode,"bedNo":rowContent.bedNo}),
                        url: basePath + '/bedRec/judgeBedNo',
                        success: function (data) {
                            if(data){
                                $.messager.alert('提示',"该床位号已经存在，不能重复", "error");
                                return "";
                            }else{
                                return row.bedNo;
                            }
                        }
                    })

                });

        }
    }*/


    //已经分配了床位的在院病人列表
    $('#inPat').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/getInPat?wardCode='+wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedno', title: '床号', width: '15%', align: 'center'},
            {field: 'name', title: '姓名', width: '15%', align: 'center'},
            {field: 'sex', title: '性别', width: '15%', align: 'center',formatter: function (value, rowData, RowIndex) {
                if(value=='1'){
                    return "女";
                }else if(value=='2'){
                    return "男";
                }
            }},
            {field: 'patientid', title: '病人ID', width: '20%', align: 'center'},
            {field: 'visitid', title: '住院号', width: '20%', align: 'center'},
            {field: 'dedlabel', title: '床标号', width: '15%', align: 'center'}
        ]],onClickRow: function (index, row) {//单击行事件

             patId = row.patientid;
            $('#hasBed').datagrid({url: basePath + '/bedRec/findList?wardCode=' + wardCode + '&bedStatus=1' + '&patientId='+patId });
            $('#emptyBed').datagrid({url:basePath + '/bedRec/findList?wardCode=' + wardCode + '&bedStatus=' + '0'+'&patientId='+""});
            $('#emptyBed').datagrid("reload");
        },onLoadSuccess: function (data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="6" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }
    });

    //未分配的床位信息
    $('#emptyBed').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center'},
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center'},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center'},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center',formatter: function (value, rowData, RowIndex) {
                if(value=='1'){
                    return "女";
                }else if(value=='2'){
                    return "男";
                }
            }},
            {field: 'bedClass', title: '等级', width: '15%',formatter:bedClassFormatter, align: 'center'},
            {field: 'airconditionClass', title: '空调等级',formatter:airconditionFormatter, width: '15%', align: 'center'},
            {field: 'bedApprovedType', title: '类型',formatter:bedFormatter, width: '15%', align: 'center'},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center',formatter:function(value, rowData, RowIndex){
                if(value=='0'||value=='' || value ==null){
                    return "是";
                }else{
                    return "否";
                }
            }},
            {field: 'patientId', hidden:true,editor:{type:'textbox',options:{editable:false,disable:false}},formatter:function(value, rowData, RowIndex){
                    return patId;
            }}
        ]
        ],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],onLoadSuccess: function (data) {

            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="10" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }
    });

//病人的包床信息 hasBed
    $('#hasBed').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        remoteSort: false,
        idField: 'id',
        method: 'GET',
        singleSelect: false,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center'},
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center'},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center'},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center',formatter: function (value, rowData, RowIndex) {
                if(value=='1'){
                    return "女";
                }else if(value=='2'){
                    return "男";
                }
            }},
            {field: 'bedClass', title: '等级', width: '15%',formatter:bedClassFormatter, align: 'center'},
            {field: 'airconditionClass', title: '空调等级',formatter:airconditionFormatter, width: '15%', align: 'center'},
            {field: 'bedApprovedType', title: '类型',formatter:bedFormatter, width: '15%', align: 'center'},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center',formatter:function(value, rowData, RowIndex){
                if(value=='0'||value=='' || value ==null){
                    return "是";
                }else{
                    return "否";
                }
            }}

        ]],onLoadSuccess: function (data) {
        if (data.total == 0) {
            var body = $(this).data().datagrid.dc.body2;
            body.find('table tbody').append('<tr><td colspan="8" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
        }
    }
    });



    $('#oldBedNo').textbox('textbox').keydown(function(e){
        if (e.keyCode == 13) {
            getBedInfo();
            return false;
        }
    });
    function getBedInfo(){
        var oldBedNo = $.trim($('#oldBedNo').val());
        //  alert(oldBedNo);
        if (oldBedNo != '') {
            $.ajax({
                method:"POST",
                contentType: "application/json", //必须有
                dataType: 'json',
                data: JSON.stringify({"wardCode":wardCode,"bedNo":oldBedNo}),
                url: basePath + '/bedRec/getOneBed',
                success: function (data) {
                    if(data !=null){
                        $('#oldBed').form('load', data);
                    }else{
                        $.messager.alert('提示',"该床位是空床不能换床", "error");
                    }

                }
            })
        }

    }


});



function save(){
   var bedRows =  $("#bedRec").datagrid("getChanges");
    var tableJson=JSON.stringify(bedRows);
    $.postJSON(basePath+'/bedRec/save',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            window.parent.document.getElementById("centerIframe").window.location.reload();
            $('#bedRec').datagrid('load');
            $('#bedRec').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })

}


function doDelete(){
    //把你选中的 数据查询出来。
    var selectRows = $('#bedRec').datagrid("getSelections");
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
                'url': basePath+'/bedRec/delete',
                'contentType': 'application/json',
                'data': ids=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.data=='success'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#bedRec').datagrid('reload');
                        window.parent.document.getElementById("centerIframe").window.location.reload();
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}


//包床
function packBed(){
  var bedInfo =  $("#emptyBed").datagrid("getSelections");
    var tableJson=JSON.stringify(bedInfo);

    $.postJSON(basePath+'/bedRec/packBed?patId='+patId,tableJson,function(data){
        if(data.data=='success'){
            var row = $('#inPat').datagrid('getSelected');
          //  alert(JSON.stringify(row));
            $.messager.alert("提示消息","包床成功");
            var patientId = row.patientid;
            $('#emptyBed').datagrid('load');
            $('#hasBed').datagrid('load');
        }else{
            $.messager.alert('提示',"包床失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"包床失败", "error");
    })

}

//换床
function   changeBed(){
    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '换床处理');


}

function changeBedOk(){
    var oldBedNo = parseInt($('#oldBedNo').val());
    var patient = parseInt($('#patientid').val());
    var newBedNo = parseInt($('#newBedNo').val());
    var visitId = $('#visitId').val();
    $.ajax({
        'type': 'POST',
        'url': basePath+'/bedRec/changeBed',
        'contentType': 'application/json',
        'data': JSON.stringify({"visitId":visitId,"newBedNo":newBedNo,"oldBedNo":oldBedNo,"wardCode":wardCode,"patientId":patient}),
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息","换床成功！");
                $('#dlg').dialog('close');
                $('#bedRec').datagrid('load');

            }else{
                $.messager.alert('提示',"换床失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"换床失败", "error");
        }
    });
}


function refash(){
    $('#emptyBed').datagrid('reload');
    $('#hasBed').datagrid('reload');
}
//解除包床
function accountsConfirm(){
    var hasBed =  $("#hasBed").datagrid("getSelections");
    var tableJson=JSON.stringify(hasBed);
    $.postJSON(basePath+'/bedRec/accountsConfirm',tableJson,function(data){
        if(data.data=='success'){
            var row = $('#inPat').datagrid('getSelected');
            $.messager.alert("提示消息","解除成功");
            $('#emptyBed').datagrid('load');
            $('#hasBed').datagrid('load');
        }else{
            $.messager.alert('提示',"解除失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"解除失败", "error");
    })

}