$(function () {

    var str = decodeURI(window.location.search);   //location.search是从当前URL的?号开始的字符串
    if (str.indexOf(name) != -1) {
        var pos_start = str.indexOf(name) + name.length + 1;
        var pos_end = str.indexOf("&", pos_start);
        if (pos_end == -1) {
           // alert(str.substring(6))   ;
            $("#hospital").append("<span id='name'>"+ str.substring(6)+"</span>");
        } else {
            alert("没有此值~~");
        }
    }


    $("#name").on('click', function () {


        window.location.href="/modules/sys/companyIndex.html";

    });

});
