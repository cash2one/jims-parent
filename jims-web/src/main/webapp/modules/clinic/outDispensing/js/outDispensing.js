$(function(){
    $('#drug').dialog('open').dialog('center').dialog('setTitle', '选择科室');
    var judge=$("#drug").parent().is(":hidden");//打开的时候返回false 隐藏的时候返回true
    if(!judge){//打开的时候
        //选择科室
      $("#dept").datagrid({
          singleSelect: true,
          fit: true,
          method: 'GET',
          url:'/modules/clinic/outDispensing/clinic_data.json',
          idField: 'patientId',
          columns: [[      //每个列具体内容
              {field: 'itemNo', title: '床号', width: '10%', align: 'center'},
              {field: 'itemCode', title: '科室编码', width: '20%', align: 'center'},
              {field: 'patientId', title: '科室名称', width: '60%', align: 'center'},
              {field: 'visitId', title: '拼音输入码', width: '10%', align: 'center'}
          ]],onClickRow: function (index, row) {//单击行事件
              $("#dispensary").val(row.itemCode);
              //加载发药门诊主记录
              $("#prescTable").datagrid({
                  singleSelect: true,
                  fit: true,
                  method: 'GET',
                  url: basePath+'/operatioinOrder/findPat?'+$("#presc").serialize(),
                  idField: 'id',
                  columns: [[      //每个列具体内容
                      {field: 'prescNo', title: '处方号', width: '50%', align: 'center'},
                      {field: 'name', title: '姓名', width: '10%'},
                      {field: 'prescDate', title: '处方日期', width: '40%'}
                  ]]
              });
          }});


    }
});

//选择查询条件的时候给input的name赋值
function getName(){
   var search= $("#search").val();
    $("#searchSome").attr('name', search);
}