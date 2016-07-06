var clinicId = parent.clinicMaster.id;
var patientId = parent.patVisit.patientId;
function onloadMethod(){
    $("#treeGrid").dialog("close");
    $("#saveBut").hide();
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
        url: basePath + '/labtest/list',
        queryParams:{'clinicId' : clinicId},
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'requestedDateTime', title: '申请日期', width: '27%', align: 'center', formatter:formatDateBoxFull},
            {field: 'performedBy', title: '检验科室', width: '25%', align: 'center',formatter:performedBFormatter},
            {field: 'resultStatus', title: '状态', width: '15%', align: 'center',formatter:function(data){
                if(data == '1'){
                    return '未检验';
                }else{
                    return '以检验';
                }
            }},
            {
                field: 'id',
                title: '操作',
                width: '25%',
                align: 'center',
                formatter: function (value, row, index) {
                    var html = '';
                    html =  html +  '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                    html = html +'<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看结果</button>';
                    return html;
                }
            }
        ]],
    //    onExpandRow(index,row){
    //    alert("1");
    //}
        view: detailview,
        detailFormatter: function(rowIndex, rowData){

            var item=[];
            $.ajax({
                type:"POST",
                url: basePath+"/labtest/getItem",
                contentType: 'application/json',
                data: testNo=rowData.testNo,
                async:false,
                dataType: 'json',
                success:function(data){
                    item=data;
                }
            })

            return  '<table><tr>' +
                '<td style="border:0">' +
                '<p>检验项目: </p>' +
                '</td>' +
                '</tr><tr>' +
                '<td style="border:0">' +
                '<p> ' +item[0].itemName + '</p>' +
                '</td>' +
                '</tr></table>';
        },
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '新增检验',
            iconCls: 'icon-add',
            handler: function () {
                add();
            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    $('#items').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'post',
        url: basePath + '/labtest/list',
        queryParams:{'clinicId' : clinicId},
        columns:[[
            {field:'itemName',title:'项目名称',width:'40%',align:'center'},
            {field:'itemCode',title:'项目代码',width:'35%',align:'center'},
            {field:'price',title:'金额',width:'20%',align:'center'}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]]
    });

    $('#treeGrid').treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        fitColumns: true,
        idField: 'id',
        treeField: 'id',
        columns: [[      //每个列具体内容
            {field: 'id', title: '申请日期', width: '30%', align: 'center'},
            {field: 'itemCode', title: '检查科室', width: '25%', align: 'center',formatter:performedBFormatter},
            {field: 'itemName', title: '状态', width: '15%', align: 'center'}
        ]]
    });

};


//新增检验
function add(){
    clearForm();
    $("#saveBut").show();
    $("#clinicId").val(clinicId);
    $("#patientId").val(patientId);
    var newDate=new Date();
    $('#requestedDateTime').datetimebox('setValue',newDate);
    $.ajax({
        //添加
        url: basePath+"/labtest/zhenduan",
        type: "POST",
        dataType: "json",
        contentType: "application/json", //必须有
        data: JSON.stringify({"clinicId":clinicId,inOrOutFlag:"0","visitId":null}),
        success: function (data) {
            if (data!= ""&& data!=null) {
                var d;
                $.each(data, function (index, item) {
                    d =item.description+"\r";
                });
                $("#relevantClinicDiag").val(d);
            }
        }
    })
        //类别下拉框
        $('#labItemClass').combobox({
            data:labItemClass,
            valueField: 'id',
            textField: 'class_name',
            onSelect: function (n, o) {
               // alert(n.dept_code);
                SendProduct();
                $("#performedBy").val(n.dept_name);
               $("#performedByCode").val(n.dept_code);
            }
        })

}

function loadTreeGrid() {
    var menus = [];//菜单列表
    var menuTreeData = [];//菜单树的列表
    var menuPromise = $.get(basePath + '/labtest/treeresult', function (data) {
        $.each(data, function (index, item) {
            var d = {};
            d.id = item.id;
            d.itemCode = item.itemCode;
            d.itemName = item.itemName;
            d.diagnosisDate = formatDatebox(item.diagnosisDate);
            d.parentId = item.parentId;
            d.children = [];
            d.iconCls="icon-file";
            menus.push(d);
        });
        for (var i = 0; i < menus.length; i++) {
            //判断儿子节点
            for(var j = 0 ;j<menus.length;j++){
                if(menus[i].id ==menus[j].parentId){
                    menus[i].children.push(menus[j]) ;
                }
            }
        }
        for (var i = 0; i < menus.length; i++) {
            if(menus[i].parentId=="0"){
                menuTreeData.push(menus[i]);
            }
        }
        $("#treeGrid").treegrid('loadData',menuTreeData) ;
    });
}
//保存
function save(){
    //formSubmitInput();
    //加载值
    //$("#items").datagrid('endEdit', editRow);
    var  rows=$('#items').datagrid('getRows');
    var formJson=fromJson('form');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"list\":"+tableJson+"}";
    $.postJSON(basePath+'/labtest/save',submitJson,function(data){
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
};

function look() {
    loadTreeGrid();
    $("#treeGrid").dialog("open");
}
//弹出选择项目窗口
function SendProduct() {
    var item={};
    //item.orgId="";
    item.dictType="lab_item_view";
    var expand3 = $("#performedBy").val();
    var expand2 = $("#labItemClass").val();
    var expand1 = $("#specimen").val();
    $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(item),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
        'success': function(data){
            var divstr ="<table>";
                    for(var i=0; i<data.length; i++)
                    {   if(i==0){
                            divstr =divstr+"<tr><td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].item_code+"'>"+data[i].item_name+"<input type='hidden' name='price' value='"+data[i].price+"'/></div></td>";
                        }
                        else if(i%3==0){
                            divstr =divstr+"<tr><td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].item_code+"'><span>"+data[i].item_name+"</span><input type='hidden' name='price' value='"+data[i].price+"'/></div></td>";
                        }
                        else if(i%3==2){
                            divstr =divstr+"<td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].item_code+"'><span>"+data[i].item_name+"</span><input type='hidden' name='price' value='"+data[i].price+"'/></div></td></tr>";
                        }
                        else{
                             divstr =divstr+"<td ><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='"+data[i].item_code+"'><span>"+data[i].item_name+"</span><input type='hidden' name='price' value='"+data[i].price+"'/></div></td>";
                        }
                        //alert(data[i].expand1);
                    $("#specimen").val(data[i].expand1);
                    }
                    divstr = divstr +"</table>";
                    divstr = divstr +"<div align='center'><a href='javascript:void(0)'  class='easy-nbtn easy-nbtn-padd' onclick='doSelect();' style='width: 90px'>提交</a></div>";
                    $("#SendProduct").html(divstr);
                    $("#SendProduct").dialog("open");

                }
    });


}

$("#SendProduct").dialog({
    title: '发货操作',
    width:'400',
    height:'300',
    iconCls: 'icon-edit',
    modal: true,
    closed: true
});
function doSelect() {
    //把你选中的 数据查询出来。
    var selectRows = $('div.easyui-window :checkbox:checked');
    if (selectRows.size() < 1) {
        $.messager.alert("提示消息", "请选中数据!");
        return;
    }else{
        var rows=[];
        var all=0;
        selectRows.each(
            function(){
                var row={};
                row.itemName=$(this).next().html();
                row.itemCode=$(this).val();//增
                var temp = $(this).next().next(":hidden").val();
                all += parseFloat(temp);
                //row.price=temp;//增
                rows.push(row);
            }
        )
        var row={};
        //row.price=all;//增
        //rows.push(row);
        var selectJson={'total':selectRows.size(),'rows':rows};
        $('#items').datagrid('loadData',selectJson);
    }
    $("#SendProduct").dialog("close");
}
function clearForm(){
    $("#form").form('clear');
    $("#saveBut").hide();
    $('#items').datagrid('loadData', { total: 0, rows: [] });
}
function formSubmitInput(){
    var clinicId=$("#clinicId").val();
    var orgId=$("#orgId").val();
    var  visitId=$("#zhuyuanId").val();
    var newHtml='<input type="hidden" id="visitId" name="visitId" value="'+visitId+'" />'
        +'<input type="hidden" id="orgId"  name="orgId" value="'+orgId+'" />'
        +'<input type="hidden" id="clinicId"  name="clinicId" value="'+clinicId+'" />';
    $("#form").append(newHtml);
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
/**
 * 删除方法
 * @param id
 */
function del(id){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/labtest/del',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息","已经删除");
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

