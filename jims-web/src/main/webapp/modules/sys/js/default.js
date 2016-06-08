$(function () {
    var dataArr = [];
    var str = decodeURI(window.location.search);   //location.search是从当前URL的?号开始的字符串
    if (str.indexOf(name) != -1) {
        var pos_start = str.indexOf(name) + name.length + 1;
        var pos_end = str.indexOf("&", pos_start);
        if (pos_end == -1) {
            var persion_id = str.substring(12);
            $.get('/service/persion-service-list/findListByFlag?persionId=' + persion_id, function (data) {

                if (data != null) {
                    for (var i = 0; i < data.length; i++) {

                        dataArr.push({id:data[i].id,serviceName: data[i].serviceName});
                    }

                    //向页面添加服务
                    var liArr = $('#addServiceModel ul li')
                    for (var i = 0; i < dataArr.length; i++) {
                        var li = '<li id="service_' + dataArr[i].id + '">';
                        li += '<a href="#"><span class="service-name">' + dataArr[i].serviceName + '</span></a>'
                        li += '<img src="/static/bookstrap/images/service/normal.jpg"/>'
                        li += '</li>'
                        $('#addServiceModel ul').append(li);
                    }

                    //点击服务跳转到首页
                    $('#addServiceModel ul li').each(function () {
                        $(this).click(function () {
                            var id=this.id.substring(8)
                            window.location.href="/modules/index.html?id=" + id+"?persion_id="+persion_id;
                        });
                    });

                    //" +"&persion_id="+persion_id



                    if (dataArr.length != 7) {
                        for (var j = 0; j < (7 - dataArr.length); j++) {
                            var li = '<li>';
                            li += '</li>';
                            $('#addServiceModel ul').append(li);
                        }
                    }
                    if (dataArr.length > 7) {
                        var li = '<li> <span style="font-size: 40px;" id="more">更多</span>';
                        li += '</li>';
                        $('#addServiceModel ul').append(li);
                    }


                }

            });
        }


        $("#moreServices").click(function () {

            window.location.href = "/modules/sys/persion-services.html?persionId=" + persion_id;
        });
    }


});
