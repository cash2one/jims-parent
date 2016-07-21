$(function () {
    var loginVo = {};


    $("#validateCode").focus(function () {
        $("#login").text("");
    });
    $("#password").focus(function () {
        $("#login").text("");
    });
    $("#loginName").focus(function () {
        $("#login").text("");
    });





    $("#validateCode").blur(function () {
        var validate = $('#validateCode').val();
        jQuery.ajax({
            'type': 'GET',
            'url': "/servlet/validateCodeServlet?validateCode=" + validate,
            'dataType': 'json',
            'success': function (data) {
                if (data == false) {
                    $("#login").text("验证码有误");
                    $("#login").css("color", "red");
                    return false;
                }
                $("#btnSubmit").off('click');
                $("#btnSubmit").on('click',function () {
                    var loginName = $("#loginName").val();
                    var password = $("#password").val();
                    $.get('/service/login/list?loginName='+loginName+'&password='+password,function(data){
                        console.log(data);
                        if (data.data == "nameNull") {
                            $("#login").text("用户名错误");
                            return false;
                        }
                        if (data.data == "passNull") {
                            $("#login").text("密码错误");
                            return false;
                        }
                        if (data.code =="success") {
                            if(data.data ==1){
                                window.location.href = ('/modules/admin-index.html');
                                return false;
                            }
                            window.location.href = ('/modules/sys/default.html');
                            return false;
                        }
                    });

                });
                return true;
            },
            'error': function (data) {
                console.log("失败");
            }
        });

    });
});

