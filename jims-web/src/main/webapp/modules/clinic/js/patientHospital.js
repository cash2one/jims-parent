$(function(){
    var patientId=$("#patientId",parent.document).val();
    $("#patientId").val(patientId);
    var name=$("#name",parent.document).val();
    $("#nameId").html(name);
    var sex=$("#sex",parent.document).val();
    $("#sexId").html(sex);
    var age=$("#age",parent.document).val();
    $("#ageId").html(age);
    var inpNo=$("#inpNo",parent.document).val();
    $("#inpNo").html(inpNo);
    var bedNo=$("#bedNo",parent.document).val();
    $("#bedNo").html(bedNo);
    var deptCode=$("#deptCode",parent.document).val();
    $("#deptCode").html(deptCode);

})