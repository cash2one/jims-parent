$(function(){

  $('#drugDialog').dialog('open').dialog('center').dialog('setTitle', '选择科室');

      $("#dept").datagrid({
          singleSelect: true,
          fit: true,
          method: 'GET',
          url:'/modules/clinic/outDispensing/js/clinic_data.json',
          idField: 'id',
          columns: [[      //每个列具体内容
              {field: 'itemNo', title: '序号', width: '10%', align: 'center'},
              {field: 'itemCode', title: '科室编码', width: '20%', align: 'center'},
              {field: 'itemName', title: '科室名称', width: '60%', align: 'center'},
              {field: 'input', title: '拼音输入码', width: '10%', align: 'center'}
          ]],onClickRow: function (index, row) {//单击行事件
              $('#drugDialog').dialog('destroy');
              window.parent.document.getElementById("centerIframe").src = "/modules/clinic/outDispensing/centerRegion.html?dispensary="+row.itemCode;

         }});

});

//选择查询条件的时候给input的name赋值
function getName(){
   var search= $("#search").val();
    $("#searchSome").attr('name', search);
}

