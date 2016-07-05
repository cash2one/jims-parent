var patVisit=parent.patVisitIndex;
$(function(){
    $("#nameId").html(patVisit.name);
    $("#sexId").html(itemFormatter(patVisit.sex, '', ''));
    $("#ageId").html(patVisit.age);
    $("#inpNo").html(patVisit.inpNo);
    $("#bedNo").html(patVisit.bedNo);
    $("#deptCode").html(clinicDeptCodeFormatter(patVisit.deptCode, '', ''));
})