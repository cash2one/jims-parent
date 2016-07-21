$(function () {

     $("#dept").datagrid({
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath + "/dept-dict/list?orgId=" + config.org_Id,
        idField: 'id',
        columns: [
            [      //每个列具体内容
                {field: 'id', title: '序号', align: 'center',hidden:true},
                {field: 'deptName', title: '科室名称', width: '40%', align: 'center'},
                {field: 'deptCode', title: '科室编码', width: '30%', align: 'center'},
                {field: 'inputCode', title: '拼音输入码', width: '30%', align: 'center'}
            ]
        ], onClickRow: function (index, row) {//单击行事件
            $('#drugDialog').dialog('destroy');
             config.deptCode=row.deptCode;
             config.deptName=row.deptName;
             location.href = "/modules/clinic/outDispensing/centerRegion.html";
        }});
     $('#drugDialog').dialog('open').dialog('center').dialog('setTitle', '选择科室');
     $("#clearBtn").on("click",function(){
        $("#condition").textbox("setValue","");
     })
    $("#searchBtn").on("click",function(){
        var conditionValue=$("#condition").textbox("getValue");
        $.ajax({
            url:basePath + "/dept-dict/findListWithFilter?orgId="+config.org_Id+"&q="+conditionValue,
            type:"GET",
            dataType:"JSON",
            success:function(result){
                $("#dept").datagrid("loadData",result);
            }
        })
    })
 });



