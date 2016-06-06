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
                else {
                    $("#btnSubmit").click(function () {
                        loginVo.loginName = $("#loginName").val();
                        loginVo.password = $("#password").val();
                        var loginName= $("#loginName").val();


                        if ($("#loginName").val() == "") {
                            $("#login").text("用户名不能为空");
                            return false;
                        }
                        if ($("#password").val() == "") {
                            $("#login").text("密码不能为空");
                            return false;
                        }
                        if ($("#validateCode").val() == "") {
                            $("#login").text("验证码不能为空");
                            return false;
                        }
                        jQuery.ajax({
                            'type': 'POST',
                            'url': "/service/login/list",
                            'contentType': 'application/json',

                            'data': JSON.stringify(loginVo),
                            'dataType': 'json',
                            'success': function (data) {
                                console.log(data);
                                if (data.data !=null) {
                                    if(data.data ==1){
                                        window.location.href = ('/modules/admin-index.html');
                                        return false;
                                    }
                                    window.location.href = ('/modules/sys/default.html?persion_id='+data.data);
                                    return false;
                                }
                                if(data.data=="isValue")
                                {
                                    jQuery.ajax({
                                        'type': 'POST',
                                        'url': "/service/login/findNameByOwner",
                                        'contentType': 'application/json',

                                        'data': loginName,
                                        'dataType': 'json',
                                        'success': function (data) {
                                            if(data.data!=null)
                                            {
                                                window.location.href = ('/modules/sys/default.html?name=' +data.data);
                                            }
                                        }

                                    });
                                    return false;
                                }
                                if (data.data == "nameNull") {
                                    $("#login").text("用户名错误");
                                    return false;
                                }
                                if (data.data == "passNull") {
                                    $("#login").text("密码错误");
                                    return false;
                                }
                            },
                            'error': function (data) {
                                console.log("失败");
                            }
                        });

                    });
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });
});

