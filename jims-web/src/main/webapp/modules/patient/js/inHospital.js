
//页面加载
$(function(){
    var patientId = '1';
    $('#leftList').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        title:'与当前登记病人重名记录',
        toolbar:'#searchDiv',
        method:'GET',
        url:basePath+'/patMasterIndex/list?patientId='+patientId,
        columns:[[      //每个列具体内容
            {field:'id',title:'ID',hidden:'true'},
            {field:'name',title:'姓名',width:'10%',align:'center'},
            {field:'idNo',title:'身份证号',width:'20%',align:'center'},
            {field:'sex',title:'性别',width:'10%',align:'center'},
            {field:'clinicMaster.visitNo',title:'就诊号',width:'10%',align:'center'},
            {field:'inpNo',title:'住院号',width:'10%',align:'center'},
            {field:'birthPlace',title:'出生地',width:'20%',align:'center'},
            {field:'mailingAddress',title:'通信地址',width:'20%',align:'center'}
        ]], onClickRow: function (index, row) {
        }, onLoadSuccess: function(){

        }
    });
});

function searchByCondition(){

}

