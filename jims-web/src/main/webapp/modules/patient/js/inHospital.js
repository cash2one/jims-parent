
//页面加载
$(function(){
    var patientId='1';
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        title:'与当前登记病人重名记录',
        toolbar:'#searchDiv',
        method:'GET',
        url:basePath+'/patMasterIndex/list?patientId='+patientId,
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:'true'},
            {field:'name',title:'姓名',width:'15%',align:'center'},
            {field:'idNo',title:'身份证号',width:'25%',align:'center'},
            {field:'sex',title:'性别',width:'10%',align:'center'},
            {field:'inpNo',title:'住院号',width:'10%',align:'center'},
            {field:'birthPlace',title:'出生地',width:'20%',align:'center'},
            {field:'mailingAddress',title:'通信地址',width:'20%',align:'center'}
        ]], onClickRow: function (index, row) {
            $("#masterForm").form('load',row);
        }, onLoadSuccess: function(){

        }
    });
    //设置分页控件
    var p = $('#leftList').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
});


//列表条件查询
function searchByCondition(){

}

//保存
function saveMaster() {
    $.postForm(basePath + '/patMasterIndex/save', 'masterForm', function (data) {
        if (data.data == 'success') {

            //$.messager.alert("提示消息", data.code + "条记录，保存成功");

            $.messager.confirm("操作提示", "是否交预交金？", function (data) {
                $('#centerList').datagrid('load');
                $('#centerList').datagrid('clearChecked');
                if (data) {
                    window.parent.document.getElementById("centerIframe").src = "/modules/finance/prepaymentList.html";
                }
            });
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    });
}
//取消登记
function removeMaster(){
    var selRow = $('#leftList').datagrid('getChecked');
    if(selRow!=null && selRow!='' && selRow!=undefined){
        var id = selRow[0].id;
        $.ajax({
            'type': 'POST',
            'url': basePath+'/patMasterIndex/delete',
            'contentType': 'application/json',
            'data': ids=id,
            'dataType': 'json',
            'success': function(data){
                if(data.data=='success'){
                    $.messager.alert("提示消息",data.code+"条记录取消登记成功！");
                    $('#leftList').datagrid('load');
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                }else if(data.data=='warning') {
                    $.messager.alert('提示',"已缴纳预交金，不能取消登记！", "error");
                }else{
                    $.messager.alert('提示',"取消登记失败", "error");
                }
            },
            'error': function(data){
                $.messager.alert('提示',"取消登记失败", "error");
            }
        });
    }else{
        $.messager.alert('提示',"请选择要取消的登记！", "warning");
    }
}

function clearForm(){
    $("#masterForm").form('clear');
}



