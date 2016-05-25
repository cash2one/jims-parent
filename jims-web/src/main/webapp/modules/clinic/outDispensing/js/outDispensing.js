$(function(){
    $("#prescDate").datebox("setValue", formatDatebox(new Date()));
/*   $('#drug').dialog('open').dialog('center').dialog('setTitle', '选择科室');
    var judge=$("#drug").parent().is(":hidden");//打开的时候返回false 隐藏的时候返回true
    if(!judge){//打开的时候
        //选择科室
      $("#dept").datagrid({
          singleSelect: true,
          fit: true,
          method: 'GET',
          url:'/modules/clinic/outDispensing/js/clinic_data.json',
          idField: 'patientId',
          columns: [[      //每个列具体内容
              {field: 'itemNo', title: '序号', width: '10%', align: 'center'},
              {field: 'itemCode', title: '科室编码', width: '20%', align: 'center'},
              {field: 'itemName', title: '科室名称', width: '60%', align: 'center'},
              {field: 'input', title: '拼音输入码', width: '10%', align: 'center'}
          ]],onClickRow: function (index, row) {//单击行事件
              $('#drug').dialog('destroy');
              $('#center').load("/modules/clinic/outDispensing/outDispensing.html");
              $("#dispensary").val(row.itemCode);*/
              //加载发药门诊主记录
              $("#prescTable").datagrid({
                  singleSelect: true,
                  fit: true,
                  method: 'GET',
                  url: basePath+'/prescTemp/getPrescMasterTemp?'+$("#presc").serialize(),
                  idField: 'id',
                  columns: [[      //每个列具体内容
                      {field: 'prescNo', title: '处方号', width: '30%', align: 'center'},
                      {field: 'name', title: '姓名', width: '20%', align: 'center'},
                      {field: 'prescDate', title: '处方日期', width: '50%', align: 'center'}
                  ]],onClickRow: function (index, row) {//单击行事件
                      alert(JSON.stringify(row));
                      $('#master').form('load',JSON.stringify(row));
                      $("#drug").datagrid({
                          singleSelect: true,
                          fit: true,
                          method: 'GET',
                          url: basePath + '/prescTemp/getDetail?masterId=' +row.id,
                          idField: 'id',
                          columns: [[      //每个列具体内容
                              {field: 'itemNo', title: '组别', width: '10%', align: 'center'},
                              {field: 'drugName', title: '药品名称', width: '10%', align: 'center'},
                              {field: 'firmId', title: '厂商', width: '10%', align: 'center'},
                              {field: 'dosageEach', title: '剂量', width: '10%', align: 'center'},
                              {field: 'quantity', title: '数量', width: '10%', align: 'center'},
                              {field: 'packageUnits', title: '单位', width: '10%', align: 'center'},
                              //{field: 'costs', title: '单价', width: '50%', align: 'center'},
                              {field: 'costs', title: '应收', width: '10%', align: 'center'},
                              {field: 'payments', title: '实收', width: '10%', align: 'center'},
                              {field: 'administration', title: '用法', width: '20%', align: 'center'}

                          ]],onLoadSuccess:function(){
                              $('#drug').datagrid('appendRow', {
                                  itemNo: '<span>合计</span>',
                                  drugName: '<span></span>',
                                  firmId: '<span></span>',
                                  dosageEach:'<span></span>',
                                  quantity:'<span></span>',
                                  packageUnits:'<span></span>',
                                  costs: '<span class="subtotal">' + compute("costs") + '</span>',
                                  payments: '<span class="subtotal">' + compute("payments") + '</span>',
                                  administration: '<span class="subtotal"></span>'
                          });
                          }

                      });
                  }
              });
     /*     }});
    }*/
});

//选择查询条件的时候给input的name赋值
function getName(){
   var search= $("#search").val();
    $("#searchSome").attr('name', search);
}

//求和
function compute(colName) {
    var rows = $('#drug').datagrid('getRows');
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        total += parseFloat(rows[i][colName]);
    }
    return total;
}

//确认发药
function confirmDrug(){
    var  rowMaster=$("#prescTable").datagrid('getSelected');
    if(rowMaster!=null){
        $.ajax({
            'type': 'post',
            'url': basePath + '/prescTemp/confirmDrug',
            'contentType': 'application/json',
            'data': id = rowMaster.id,
            'dataType': 'json',
            'success': function (data) {
                if(data.data=="success"){
                    $('#prescTable').datagrid('load');
                    $('#drug').datagrid('load');
                    $.messager.alert("提示信息", "发药成功");
                }else{
                    $.messager.alert("提示信息", "发药失败", "error");
                }

            }
        });
    }else{
        $.messager.alert("提示信息", "请选择药品", "error");
    }


}