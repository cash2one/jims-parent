
function onloadMethod(){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//ͼ��
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//�Ƿ���۵���
        fit: true,//�Զ���С
        url:basePath+'/courseRecord/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//�Ƿ�ѡ
        pagination:true,//��ҳ�ؼ�
        pageSize:15,
        pageList: [10,15,30,50],//��������ÿҳ��¼�������б�
        rownumbers:true,//�к�
        columns:[[      //ÿ���о�������
            {field:'luruShijian',title:'����ʱ��',width:'5%',align:'center'},
            {field:'type',title:'��������',width:'18%',align:'center'},
            {field:'remark',title:'״̬',width:'20%',align:'center'},
            {field:'id',title:'����',width:'30%',align:'center',formatter:function(value, row, index){
                var html='<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\''+value+'\')"><img src="/static/images/index/icon1.png" width="12"/>�鿴</button>'+
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\''+value+'\')"><img src="/static/images/index/icon2.png"  width="12" />�޸�</button>'+
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\''+value+'\')"><img src="/static/images/index/icon3.png" width="16"/>ɾ��</button>';
                return html;
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '���',
            iconCls: 'icon-add',
            handler: function() {
                $("#saveBut").show();

                $("#dlg").dialog({title: '��Ӳ��̼�¼��Ϣ'}).dialog("open")
            }
        }, '-', {
            text: '�޸�',
            iconCls: 'icon-edit',
            handler: function() {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("��ʾ��Ϣ", "��ѡ��Ҫ�޸ĵ�����!");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-',{
            text: 'ɾ��',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }]
    });
    //���÷�ҳ�ؼ�
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '��',//ҳ���ı���ǰ��ʾ�ĺ���
        afterPageText: 'ҳ    �� {pages} ҳ',
        displayMsg: '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
    });

}
//����ɾ��
function doDelete() {
    //����ѡ�е� ���ݲ�ѯ������
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("��ʾ��Ϣ", "��ѡ��Ҫɾ������!");
        return;
    }

    //��ɾ������
    //�����û��Ƿ������ɾ������
    $.messager.confirm("ȷ����Ϣ", "��ȷ��Ҫɾ����Ϣ��", function (r) {
        if (r) {
            //��ɾ����  1,3,4
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
        }
    })
}
//��ɾ��
function deleteRow(id) {
    //��ɾ������
    //�����û��Ƿ������ɾ������
    $.messager.confirm("ȷ����Ϣ", "��ȷ��Ҫɾ����Ϣ��", function (r) {
        if (r) {
            del(id);
        }
    })
}
/**
 * ɾ������
 * @param id
 */
function del(id){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/courseRecord/del',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("��ʾ��Ϣ",data.code+"����¼���Ѿ�ɾ��");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            }else{
                $.messager.alert('��ʾ',"ɾ��ʧ��", "error");
            }
        },
        'error': function(data){
            $.messager.alert('��ʾ',"����ʧ��", "error");
        }
    });
}
/**
 * ���淽��
 */
function saveDice(){
    $.postForm(basePath+'/courseRecord/save','dictForm',function(data){
        if(data.data=='success'){
            $.messager.alert("��ʾ��Ϣ",data.code+"����¼������ɹ�");
            $("#dlg").dialog('close');
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        }else{
            $.messager.alert('��ʾ',"����ʧ��", "error");
        }
    },function(data){
        $.messager.alert('��ʾ',"����ʧ��", "error");
    })
}
/**
 * �޸��ֵ�
 * @param id
 */
function get(id){
    $("#saveBut").show();
    $("#dlg").dialog({title: '�޸Ĳ��̼�¼'}).dialog("open");
    $.ajax({
        'type': 'post',
        'url': basePath+'/courseRecord/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#dictForm').form('load',data);
        }
    });
}
/**
 * �鿴�ֵ�
 * @param id
 */
function look(id){
    $("#dlg").dialog({title: '�鿴���̼�¼'}).dialog("open");
    $("#saveBut").hide();
    $.ajax({
        'type': 'post',
        'url': basePath+'/courseRecord/get',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#dictForm').form('load',data);
        }
    });
}

