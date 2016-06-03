/**
 * Created by fyg on 2016/6/2.
 */
$(function(){
    var applyStatus = '';
    $("#checkFlag").combobox({
        valueField: 'label',
        textField: 'value',
        data:[
            {
                label: '全部',
                value: '全部'
            },{
                label: '待审',
                value: '待审'
            },{
                label: '通过',
                value: '通过'
            },{
                label: '失败',
                value: '失败'
            }
        ],
        onSelect: function(){
            var label = $(this).combobox('getValue');
            if(label == '全部'){
                applyStatus = '';
            }else if(label == '待审'){
                applyStatus = '1';
            }else if(label == '通过'){
                applyStatus = '2';
            }else if(label == '失败'){
                applyStatus = '-1';
            }
            loadDict();
        }
    });
    $("#dg").datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        border: true,
        fit: true,
        fitColumns: true,
        singleSelect: true,
        rownumbers: true,
        toolbar: '#topBar',
        method: 'get',
        url: basePath + '/sys-sompany/find-list-by-status?applyStatus=' + applyStatus,
        columns: [[
            {
                title: '机构代码',
                field: 'orgCode',
                align: 'center',
                width: '10%'
            },{
                title: '机构名称',
                field: 'orgName',
                align: 'center',
                width: '10%'
            },{
                title: '联系人',
                field: 'linkMan',
                align: 'center',
                width: '10%'
            }, {
                title: '联系电话',
                field: 'linkPhoneNum',
                align: 'center',
                width: '10%'
            }, {
                title: '申请状态',
                field: 'applyStatus',
                align: 'center',
                width: '10%',
                formatter: function(value,row,index){
                    if(value == '1'){return '待审';}
                    if(value == '2'){return '通过';}
                    if(value == '-1'){return '失败';}
                }
            }, {
                title: '机构地址',
                field: 'address',
                align: 'center',
                width: '10%'
            }, {
                title: '邮箱',
                field: 'email',
                align: 'center',
                width: '10%'
            },{
                field: 'id', title: '操作', width: '30%', align: 'center', formatter: function (value, row, index) {
                    var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="pass(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>通过</button>' +
                        '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="refuse(\'' + value + '\')"><img src="/static/images/index/icon2.png"  width="12" />驳回</button>' /*+
                        '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="checkService(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>查看服务</button>'*/;
                    return html;
                }
            }
        ]]
    });

    var loadDict = function(){
        $.get(basePath +  '/sys-sompany/find-list-by-status?applyStatus=' + applyStatus,function(data){
            $("#dg").datagrid('loadData',data);
        });
    }
});

function pass(id) {     //通过审核
    $.get(basePath + '/sys-sompany/get-sysCompany-by-id?id=' + id, function (data) {
        if (data.applyStatus == '2') {
            alert("该机构已经通过审核!");
            return ;
        }else{
            $.ajax({
                'type': 'post',
                'url': basePath + '/sys-sompany/update-pass-status?id=' + id,
                'contentType': 'application/json',
                'dataType': 'json',
                'success': function (data) {
                    $.messager.alert("提示消息", "保存成功!");
                    $("#dg").datagrid('reload');
                }
            });
        }
    });
}
function refuse(id) {     //驳回审核
    $.get(basePath + '/sys-sompany/get-sysCompany-by-id?id=' + id, function (data) {
        if (data.applyStatus == '2') {
            alert("该机构已经通过审核!");
            return;
        }else if(data.applyStatus == '-1'){
            alert("您已经驳回了该机构的审核!");
            return;
        }else{
            $.ajax({
                'type': 'post',
                'url': basePath + '/sys-sompany/update-fail-status?id=' + id,
                'contentType': 'application/json',
                'dataType': 'json',
                'success': function (data) {
                    $.messager.alert("提示消息", "保存成功!");
                    $("#dg").datagrid('reload');
                }
            });
        }
    });
}