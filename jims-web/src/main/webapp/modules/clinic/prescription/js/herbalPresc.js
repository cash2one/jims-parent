var rowNum=-1;
var orderNo=0;
var prescNo;
var itemClass;
var clinicId;
var chargeIndicator='新开';
//页面加载
$(function() {
    itemClass = $("#itemClass").val();
    clinicId = $("#clinicMasterId", parent.document).val();
    $("#clinicId").val(clinicId);



    //途径
    $('#administration').combobox({
        data: administrationmzDict,
        valueField: 'id',
        textField: 'administrationName'
    });
    $("#administration").combobox('select',administrationmzDict[0].value);
    //频次
    $('#frequency').combobox({
        data: performFreqDict,
        valueField: 'id',
        textField: 'freqDesc'
    });
    $("#frequency").combobox('select',performFreqDict[0].value);

    /**药品**/
    $('#drugName').combogrid({
        width: '300',
        height: 'auto',
        data: herbalDrugData,
        idField:'item_name',
        textField:'item_name',
        mode: 'remote',
        columns: [[
            {field: 'drug_code', title: '代码', width: '8%', align: 'center'},
            {field: 'item_name', title: '名称', width: '15%', align: 'center'},
            {field: 'drug_spec', title: '规格', width: '15%', align: 'center'},
            {field: 'quanity', title: '库存', width: '15%', align: 'center'},
            {field: 'units', title: '包装单位', width: '15%', align: 'center'},
            {field: 'item_class', title: '库房', width: '15%', align: 'center'},
            {field: 'supplier', title: '厂家', width: '15%', align: 'center'},
            {field: 'dose_per_unit', title: '单次用量', width: '15%', align: 'center'},
            {field: 'dose_units', title: '用量单位', width: '15%', align: 'center'},
            {field: 'subj_code', title: '',hidden:true},
            {field: 'performed_by', title: '',hidden:true},
            {field: 'price', title: '',hidden:true}
        ]], keyHandler: {
            query: function (q) {
                comboGridCompleting(q, 'drugName');
                $('#drugName').combogrid("grid").datagrid("loadData", comboGridComplete);
            }
        }
    })

   /* function herbalList(){
        var itemClass = $("#itemClass").val();
        var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
        if(selRow!=null&&selRow!=''&&selRow!='undefined') {
            prescNo = selRow[0].prescNo;
            var liHtml = "";
            *//**
             * 中药列表
             *//*
            $.postJSON(basePath+'/outppresc/sublist', "{\"prescNo\":\"" +  row.prescNo + "\",\"clinicId\":\"" + clinicId + "\"}", function (data) {

                for (var i = 0; i < data.length; i++) {
                    liHtml += '<li onclick="centerActive(this)" input_id="liHerbalLabel' + i + '">' +
                    '<span style="padding-right:10px;"><input  type="text" style="width: 150px" value="'+data[i].drugName+'"/></span>' +
                    ' <a href="#" class="color-red"><input type="text" style="width: 50px" value="'+data[i].drugName+'"/></a> ' +
                    '<span class="color-blue" style="padding-left:10px;">'+data[i].dosageUnits+'</span></li>';
                }
                $("#herbal_ul").html(liHtml);

            }, function () {
                $.messager.alert("提示信息", "网络连接失败");
            });
        }
    }*/

    $("#addBtn").on("click",function(){
        rowNum++;
        var selRow = $('#leftList').datagrid('getChecked');//获取处方选中行数据，有新开处方，才能添加处方医嘱明细
        if(selRow!=null&&selRow!=''&&selRow!='undefined') {
            var html = '<li onclick="centerActive(this)">' +
                '<span style="padding-right:10px;"><input  type="text" style="width: 100px;" value=""/></span>' +
                '<a href="#" class="color-red"><input id="drugName" name="drugName" type="text" style="width: 100px" value=""/></a>' +
                ' <span class="color-blue" style="padding-left:10px;">g</span> </li>';
            //html.appendTo($("#herbal_ul"));
            $("#herbal_ul").append(html);
        }else{
            $.messager.alert("提示消息", "请选择处方后再进行添加操作!");
            return false;
        }
    });

    $("#delBtn").on("click",function(){

    })
});

function centerActive(li){
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}