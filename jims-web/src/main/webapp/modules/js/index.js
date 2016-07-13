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
        content = '<iframe scrolling="auto" frameborder="0"  src="' + href + '" style="width:85%;height:93%;"></iframe>'
        $("#mainContent").tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    }
}


/**
 * 生成菜单
 * @param menus
 */
var makeTree = function (menus) {
    //测试
    $("#west").empty();
    var menuDatas = [];
    for (var i = 0; i < menus.length; i++) {
        menus[i].children = [];
    }

    for (var i = 0; i < menus.length; i++) {
        for (var j = 0; j < menus.length; j++) {
            if (menus[j].pid == menus[i].menuId) {
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

    console.log(menuDatas)

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

var addMenu = function (serviceId, staffId) {
    config.serviceId = serviceId;
    config.staffId = staffId;

    var promise = $.get(basePath + "/service-param/find-by-self-service-id?selfServiceId=" + config.serviceId + "&orgId=" + config.org_Id, function (data) {
        console.log(data);
        var objs = [];

        for (var i = 0; i < data.length; i++) {
            var obj = {};
            obj.paramDesp = data[i].paramDesp;
            obj.paramName = data[i].paramName;
            var temp1 = data[i].valueRange.split(";");
            obj.options = [];
            for (j = 0; j < temp1.length; j++) {
                var temp2 = temp1[j].split("|");
                var obj1 = {};
                obj1.label = temp2[0];
                obj1.value = temp2[1];
                obj.options.push(obj1);
            }
            objs.push(obj);
        }
        var data = {};
        data.list = objs;
        var html = template('param', data);
        $("#paramDialog").empty();
        $("#paramDialog").append(html);
        $(".easyui-combobox").combobox();

    })
    promise.done(function (obj) {
        var op = {width: 400, height: 70 * obj.length}
        if (obj.length <= 0) {
            $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + config.serviceId + "&staffId=" + config.staffId, function (data) {
                makeTree(data)
            })
        } else {
            $("#paramDialog").dialog("resize", op);
            $("#paramDialog").dialog('open');
            $("#paramDialog").dialog('center');
        }

    })


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
    var sysService;    //名称为系统管理的服务

    //根据机构ID和人员ID查询员工ID
    $.get(basePath + '/orgStaff/find-staff-by-orgId-personId?persionId=' + personId + '&orgId=' + orgId+"&date=new Date()", function (data) {
        staffId = data.id;
    });
    //根据机构ID和人员ID查询该员工在该机构的所有服务
    $.get(basePath + '/org-service/find-selfServiceList-by-orgId-personId?personId=' + personId + '&orgId=' + orgId, function (data) {
        for (var i = 0; i < data.length; i++) {
            $("#menu").append("<li><a href=\"#\" onclick=\'addMenu(\"" + data[i].id + "\",\"" + staffId + "\")\'>" + data[i].serviceName + "</a></li>");
        }
    });

    //退出
    $("#exit").on("click", function () {
        location.href = "/modules/sys/login.html";
    });

    $("#paramDialog").dialog({
        width: 400,
        height: 500,
        modal: 'true',
        title: '参数选择',
        closed: true,
        closable: false,
        buttons: [{
            text: '确定',
            handler: function () {
                $("#paramDialog .easyui-combobox").each(function (index, item) {
                    var value = $("#" + item.id).combobox('getValue');
                    config[item.id] = value;
                    console.log("config");
                    console.log(config);
                })
                $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + config.serviceId + "&staffId=" + config.staffId, function (data) {
                    makeTree(data)
                })
                $("#paramDialog").dialog("close");
            }
        }, {
            text: '取消',
            handler: function () {
                $("#paramDialog").dialog("close")
            }
        }]
    })
});