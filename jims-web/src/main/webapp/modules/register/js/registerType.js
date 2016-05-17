var item =  [{ "value": "1", "text": "挂号费" }, { "value": "2", "text": "诊疗费" }, { "value": "3", "text": "其他费" }];
var priceItme= [{ "value": "11020000100", "text": "普通门诊诊查费" }, { "value": "11020000300", "text": "急诊诊查费" }, { "value": "10005", "text": "鉴定费" },
      {"value":"11020000400","text":"门急诊留观诊查费"},{"value":"11020000201","text":"副主任医师诊查费"}];
function onloadMethod(id){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: '100%',
        height: '94%',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
         url:basePath+'/clinicType/itemList?typeId='+id,
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'chargeItem',title:'收费项目',width:'30%',align:'center',editor:{
                type:'combobox',
                options:{
                    data :item,
                    valueField:'value',
                    textField:'text'
                }}
            },
            {field:'priceItem',title:'项目价表',width:'30%',align:'center',id:'item',
                editor:{
                    type:'combobox',
                    options:{
                        data:priceItme,
                        valueField:'text',
                        textField:'value',
                        onSelect: function(data){

                        }
                    }
                }
            },
            {field:'price',title:'项目名称',width:'30%',align:'center',id:'itemName',editor:{type:'textbox',options:{editable:false,disable:false}}},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '增加',
            iconCls: 'icon-add',
            handler: function() {
                $("#list_data").datagrid('insertRow', {
                    index:0,
                    row:{

                    }

                });
                $("#list_data").datagrid("beginEdit", 0);
            }
        }, {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {

            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){

            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');

}
//号类列表
function clinicTypeList(){
    var typeHtml='';
    $.get(basePath + '/clinicType/findList',function(data){
       for(var i=0;i<data.length;i++){
         typeHtml+='<li><a href="#" onclick="onloadMethod('+data[i].id+')">'+data[i].clinicTypeName+'</a><a href="#" class="rp-close">X</a></li>';
       }
        $("#clinicType").html(typeHtml);
    })
}
clinicTypeList();


