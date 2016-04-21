//保存
function formSubmitInput(fromId){
    $("#"+fromId+" div").each(function(){
        var inputId=$(this).attr("submit_id");
        if(typeof(inputId) != "undefined"){
            var html=$(this).html();
            $("#"+inputId).val(html);
        }
    })
}
//查看
function getDiv(fromId){
    $("#"+fromId+" div").each(function(){
        var inputId=$(this).attr("submit_id");
        if(typeof(inputId) != "undefined"){
            $(this).html( $("#"+inputId).val());

        }
    })
}
