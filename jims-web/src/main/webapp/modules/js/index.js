function centerRefresh(id, name, url) {
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
}
var str = decodeURI(window.location.search);   //location.search是从当前URL的
if (str.indexOf(name) != -1) {
    var pos_start = str.indexOf(name) + name.length + 1;
    var pos_end = str.indexOf("&", pos_start);
    if (pos_end == -1) {
        var id = str.substring(4, str.lastIndexOf("?"));
        //人员id
        var pid = str.substring(id.length + 16);
    }


}

window.addTab = function (title, href) {
    //如果路径为空，则直接返回
    if (!href) {
        return;
    }
    var tabs = $("#mainContent").tabs('tabs');
    if (tabs.length > 10) {
        $.messager.alert("系统提示", "打开的Tab页面太多，请观不需要的，重新在打开", 'info');
        return;
    }
    if ($("#mainContent").tabs('exists', title)) {
        $("#mainContent").tabs('select', title);
    } else {
        var content = undefined;
        content = '<iframe scrolling="auto" frameborder="0"  src="' + href + '" style="width:100%;height:100%;"></iframe>'
        $("#mainContent").tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    }
}

var config = {};

config.org_Id = id;
config.persion_Id = pid;
config.operator = 'thinkgem';
config.currentStorage = '1001';
$(function () {
    var orgId = config.org_Id;
    var personId = config.persion_Id;
    var staffId = '';   //员工Id
    //var serviceId = []; //员工对应的多个角色下的所有服务(去掉重复的服务)
    var sysService;    //名称为系统管理的服务

    //根据机构ID和人员ID查询员工ID
    $.get(basePath + '/orgStaff/find-staff-by-orgId-personId?persionId=' + personId + '&orgId=' + orgId, function (data) {
        staffId = data.id;
    });
    //根据机构ID和人员ID查询该员工在该机构的所有服务
    $.get(basePath + '/org-service/find-selfServiceList-by-orgId-personId?personId=' + personId + '&orgId=' + orgId, function (data) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].serviceName != '系统管理') {
                $("#menu").append("<li><a id='" + data[i].id + "'>" + data[i].serviceName + "</a></li>");
                $("#" + data[i].id).on('click', function () {
                    alert("yes")
                    $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + serviceId + "&staffId=" + staffId, function (data) {
                        console.log(data)
                    })
                });
            }
            if (data[i].serviceName == '系统管理') {
                sysService = {}
                sysService.id = data[i].id;
                sysService.serviceName = data[i].serviceName;
            }
        }
        if (sysService) {
            $("#menu").append("<li><a id='" + sysService.id + "'>" + sysService.serviceName + "</a></li>");
            $("#" + sysService.id).on('click', function () {
                $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + sysService.id + "&staffId=" + staffId, function (data) {
                    makeTree(data)
                })
            });
        }

        var makeTree=function(menus){
            //测试
            var menuDatas = [];
            for (var i = 0; i < menus.length; i++) {
                menus[i].children = [];
            }

            for (var i = 0; i < menus.length; i++) {
                for (var j = 0; j < menus.length; j++) {
                    if (menus[j].pid == menus[i].id) {
                        menus[i].children.push(menus[j])
                    }
                }
                if (menus[i].pid == "" || menus[i].pid == undefined || menus[i].pid == null) {
                    menuDatas.push(menus[i]);
                }
            }
            var data = {
                title: '嵌入子模板',
                list: menuDatas
            };

            var html = template("his-index", data);
            $("#west").append(html);
            $("#content").layout({
                fit: true
            });

            $("#aa").accordion({
                fit: true
            });
            $(".easyui-tree").tree();
        }
    });





});