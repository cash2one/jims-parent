function centerRefresh(id, name, url) {
    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
}
var str = decodeURI(window.location.search);   //location.search是从当前URL的
if (str.indexOf(name) != -1) {
    var pos_start = str.indexOf(name) + name.length + 1;
    var pos_end = str.indexOf("&", pos_start);
    if (pos_end == -1) {
        var id = str.substring(4,str.lastIndexOf("?") );
        //人员id
        var pid=str.substring(id.length+16);
    }


}
var config = {} ;

/*$.get("/service/sys-sompany/get-sysCompany-by-id?id=" + id, function (data) {
    if (data != null) {
        config.org_Id = id;
    }
    else {
        config.service_Id = id;
    }
});*/
alert(id);
alert(pid);
config.org_Id = id;
config.persion_Id=pid;
config.operator = 'thinkgem';
config.currentStorage = '1001';
$(function () {
    //var orgId = parent.config.org_Id;
    var orgId = config.org_Id;
    var personId = config.persion_Id;
    var staffId = '';   //员工Id
    var serviceId = []; //员工对应的多个角色下的所有服务(去掉重复的服务)

    //查询员工信息
    $.get(basePath + '/orgStaff/find-staff-by-orgId-personId?persionId=' + personId + '&orgId=' + orgId, function (data) {
        staffId = data.id;

        //查询员工的服务
        $.get(basePath + '/orgStaff/find-serviceId-by-staffId?staffId=' + staffId, function (data) {
            for (var i = 0; i < data.length; i++) {
                serviceId.push(data[i].serviceId);
            }
            for (var i = 0; i < serviceId.length; i++) {
                serviceId = serviceId.unique2();    //去掉相同的serviceId
                $.get(basePath + '/sys-service/get?id=' + serviceId[i], function (data) {
                    $("#menu").append("<li><a id='" + data.id + "'>" + data.serviceName + "</a></li>");
                    $("#" + data.id).on('click', function () {
                        $.get(basePath + '/orgStaff/find-list-by-serviceId?serviceId=' + data.id + '&staffId=' + staffId, function (data) {
                        //$.get(basePath + '/orgStaff/find-list-by-serviceId?serviceId=' + '1' + '&staffId=' + '1', function (data) {
                            console.log(data);
                        });
                    });
                });
            }

            /* $.get(basePath + '/menuDict/list', function (data) {
             for (var i = 0; i < data.length; i++) {
             //console.log("data[i].menuName:" + data[i].menuName + "-----" + data[i].id);
             if (data[i].menuName == '系统管理') {
             $("#menu").append("<li><a id='" + data[i].id + "'>" + data[i].menuName + "</a></li>");
             }
             }
             });*/
        });
    });

    //数组元素去重
    Array.prototype.unique2 = function () {
        this.sort(); //先排序
        var res = [this[0]];
        for (var i = 1; i < this.length; i++) {
            if (this[i] !== res[res.length - 1]) {
                res.push(this[i]);
            }
        }
        return res;
    }



    //标题菜单维护
    $.get(basePath + "/menuDict/list", function (data) {
        var menus = [];//菜单列表
        var menuTreeData = [];//菜单树的列表
        $.each(data, function (index, item) {
            var menu = {};
            menu.id = item.id;
            menu.pid = item.pid;
            menu.menuName = item.menuName;
            menu.href = item.href;
            menu.icon = item.icon;
            menu.sort = item.sort;
            menu.target = item.target;
            menu.menuLevel = item.menuLevel;
            menu.children = [];
            menu.childrenDivId = "menu" + index;
            menus.push(menu);
        });
        for (var i = 0; i < menus.length; i++) {
            //判断儿子节点
            for (var j = 0; j < menus.length; j++) {
                if (menus[i].id == menus[j].pid) {
                    menus[i].children.push(menus[j]);
                }
            }
            //判断是不是根节点  start
            if (menus[i].children.length > 0 && !menus[i].pid) {
                menuTreeData.push(menus[i]);
            }

            if (!menus[i].pid && menus[i].children.length <= 0) {
                menuTreeData.push(menus[i]);
            }
            //判断是不是根节点  end
        }
        //$.each(menuTreeData, function (index, item) {
        //    if (item.target == 1) {//直接打开
        //        $("#menu").append("<li><a id='"+item.id+"'>" + item.menuName + "</a></li>");
        //        $("#"+item.id).on("click", function () {
        //            $("#centerIframe").attr("src", item.href);
        //        });
        //    }
        //
        //    if (item.target == 2) {//子菜单打开
        //        var id2 = item.childrenDivId + item.target;
        //        var str = "<div class='menu-content' id='"+item.childrenDivId+"'style='background:#3b4c5c;padding:10px;text-align:left'>";
        //        var script="<script type='text/javascript'> $(function() { ";
        //        $.each(item.children, function (i,t) {
        //            str = str + "<a id='"+t.id+"'>" + t.menuName +"</a>";
        //            script = script + "$('#"+t.id+"').on('click', function () {$('#centerIframe').attr('src','"+ t.href+"');});";
        //        });
        //        $("body").append(str + "</div>");
        //        $("body").append(script + "})</script>");//
        //
        //
        //        $("#menu").append("<li id='"+id2+"'> " +
        //        "<a class='easyui-menubutton' href='" + item.href + "'  data-options=\"menu:'#" + item.childrenDivId + "'\">" +
        //        "<span class='neweu-mparent' >" + item.menuName +"</span></a></li>" );
        //
        //        $("#"+id2 + "> a").menubutton();
        //    }
        //})
    });
});