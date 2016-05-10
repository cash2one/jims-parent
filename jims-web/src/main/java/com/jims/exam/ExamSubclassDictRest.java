package com.jims.exam;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.common.data.StringData;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.exam.api.ExamSubclassDictApi;
import com.jims.exam.entity.ExamSubclassDict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 检查类别字典
 *
 * @author tangxb
 * @version 2016-04-29
 */
@Component
@Produces("application/json")
@Path("examSubclassDict")
public class ExamSubclassDictRest {

    @Reference(version = "1.0.0")
    private ExamSubclassDictApi examSubclassDictApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public List<ExamSubclassDict> list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamSubclassDict> list = new ArrayList<ExamSubclassDict>();
        list = examSubclassDictApi.findAll();
        return list;
    }

    /**
     * 通过orgID获取检查类别列表
     * @param request
     * @param response
     * @return
     */
    @Path("listByOrgId")
    @GET
    public List<ExamSubclassDict> listByOrgId(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamSubclassDict> list = new ArrayList<ExamSubclassDict>();
        list = examSubclassDictApi.findListByOrgId(request.getParameter("orgId"));
        return list;
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public ExamSubclassDict get(String id) {
        ExamSubclassDict examSubclassDict = examSubclassDictApi.get(id);
        return examSubclassDict;
    }

    /**
     * 保存修改方法
     *
     * @param examSubclassDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ExamSubclassDict examSubclassDict) {
        examSubclassDict.setInputCode(PinYin2Abbreviation.cn2py(examSubclassDict.getExamSubclassName()));
        String num = examSubclassDictApi.save(examSubclassDict);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 批量id删除
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = examSubclassDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 获取当前类别子类项目
     * @param request
     * @param response
     * @return
     */
    @Path("list-by-class")
    @GET
    public List<ExamSubclassDict> listByClass(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamSubclassDict> list = new ArrayList<ExamSubclassDict>();
        String orgId = request.getParameter("orgId");
        String className = null;
        try {
            className = URLDecoder.decode(request.getParameter("className"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        list = examSubclassDictApi.listByClass(orgId,className);
        return list;
    }
}
