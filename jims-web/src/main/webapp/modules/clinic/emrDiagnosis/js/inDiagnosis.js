var administration = [{ "value": "1", "text": "初步诊断" }, { "value": "2", "text": "鉴别诊断" }, { "value": "4", "text": "入院诊断" }];
$(function() {
    $('#tg').treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        fitColumns: true,
        url: '/modules/clinic/emrDiagnosis/js/treegrid_data2.json',
        method: 'get',
        idField: 'id',
        treeField: 'type',
        showFooter: true,
        columns:[[      //每个列具体内容
            // {field:'id',title:'序号',width:'5%',align:'center',editor:'text'},
            /*{field:'itemNo',title:'序号',width:'5%',align:'center',editor:'text'},*/
            {field:'type',title:'诊断类型',width:'10%',align:'center',editor:'text',editor:{
                type:'combobox',
                options:{
                    data :administration,
                    valueField:'value',
                    textField:'text',
                    required:true,
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].text);
                    }
                }

            }
            },
            {field:'description',title:'诊断描述',width:'20%',align:'center',editor:'text'},
            {field:'treatResult',title:'治疗结果',width:'20%',align:'center',editor:'text'},
            {field:'operTreatIndicator',title:'手术',width:'5%',align:'center',editor:'text'},
            {field:'diagnosisDate',title:'诊断日期',width:'15%',align:'center',editor:'datebox'},
            {field:'pathologyNo',title:'病理号',width:'10%',align:'center',editor:'text'},
            {field:'diagnosisId',title:'诊断名称',width:'20%',align:'center',editor:{
                type:'combobox',
                options:{
                    required:true,
                    url: basePath+'/dataicd/autoComplete',
                    valueField: 'code',
                    textField: 'keywordShuoming',
                    method: 'GET',
                    onLoadSuccess: function () {
                        var data = $(this).combobox('getData');
                        $(this).combobox('select', data[0].keywordShuoming);
                    }
                }
            }}


        ]]

    });
});


















function formatProgress(value){
    if (value){
        var s = '<div style="width:100%;border:1px solid #ccc">' +
            '<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
        '</div>';
        return s;
    } else {
        return '';
    }
}
var editingId;
function edit(){
    if (editingId != undefined){
        $('#tg').treegrid('select', editingId);
        return;
    }
    var row = $('#tg').treegrid('getSelected');
    if (row){
        editingId = row.id
        $('#tg').treegrid('beginEdit', editingId);
    }
}
function save(){
    if (editingId != undefined){
        var t = $('#tg');
        t.treegrid('endEdit', editingId);
        editingId = undefined;
        var persons = 0;
        var rows = t.treegrid('getChildren');
        for(var i=0; i<rows.length; i++){
            var p = parseInt(rows[i].persons);
            if (!isNaN(p)){
                persons += p;
            }
        }
        var frow = t.treegrid('getFooterRows')[0];
        frow.persons = persons;
        t.treegrid('reloadFooter');
    }
}
function cancel(){
    if (editingId != undefined){
        $('#tg').treegrid('cancelEdit', editingId);
        editingId = undefined;
    }
}