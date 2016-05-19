/**
 * 发开dialog
 * @param id
 * @param name
 */
function openDialog(id,name){
    $("#"+id).dialog({title: name}).dialog("open")
}

function centerTypeActive(li){
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}

