package com.jims.clinic.exam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ExamClassDictApi;
import com.jims.clinic.entity.ExamClassDict;
import com.jims.common.data.StringData;
import com.jims.common.utils.PinYin2Abbreviation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
@Path("examClassDict")
public class ExamClassDictRest {

    @Reference(version = "1.0.0")
    private ExamClassDictApi examClassDictApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public List<ExamClassDict> list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamClassDict> list = new ArrayList<ExamClassDict>();
        list = examClassDictApi.findAll();
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
    public List<ExamClassDict> listByOrgId(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamClassDict> list = new ArrayList<ExamClassDict>();
        list = examClassDictApi.findListByOrgId(request.getParameter("orgId"));
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
    public ExamClassDict get(String id) {
        ExamClassDict examClassDict = examClassDictApi.get(id);
        return examClassDict;
    }

    /**
     * 保存修改方法
     *
     * @param examClassDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ExamClassDict examClassDict) {
        examClassDict.setInputCode(PinYin2Abbreviation.cn2py(examClassDict.getExamClassName()));
        String num = examClassDictApi.save(examClassDict);
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
        String num = examClassDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
