package com.jims.clinic.exam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ExamRptPatternApi;
import com.jims.clinic.entity.ExamRptPattern;
import com.jims.clinic.vo.ExamRptPatternVo;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 检查项目
 *
 * @author tangxb
 * @version 2016-05-03
 */
@Component
@Produces("application/json")
@Path("examRptPattern")
public class ExamRptPatternRest {

    @Reference(version = "1.0.0")
    private ExamRptPatternApi examRptPatternApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public List<ExamRptPattern> list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamRptPattern> list = new ArrayList<ExamRptPattern>();
        list = examRptPatternApi.findAll();
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
    public ExamRptPattern get(String id) {
        ExamRptPattern examSubclassDict = examRptPatternApi.get(id);
        return examSubclassDict;
    }

    /**
     * 保存修改方法
     *
     * @param examRptPattern
     * @return
     */
    @Path("save")
    @POST
    public StringData save(ExamRptPattern examRptPattern) {
        String num = examRptPatternApi.save(examRptPattern);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 保存修改方法
     *
     * @param examRptPatternVo 保存修改列表
     * @return
     */
    @Path("saveList")
    @POST
    public StringData save(ExamRptPatternVo<ExamRptPattern> examRptPatternVo) {
        int num = 0;
        int count = 0;
        for (int i = 0;i<examRptPatternVo.getInserted().size();i++){
            ExamRptPattern examRptPattern = new ExamRptPattern();
            examRptPattern = (ExamRptPattern)examRptPatternVo.getInserted().get(i);
            examRptPattern.setDescription(examRptPattern.getDescription());
            examRptPattern.setDescItem("检查项目");
            examRptPattern.setDescName("2");
            examRptPattern.setDescriptionCode(examRptPattern.getDescriptionCode());
            examRptPattern.setInputCode(PinYin2Abbreviation.cn2py(examRptPattern.getDescription()));
            examRptPattern.setExamClass(examRptPatternVo.getExamClass());
            examRptPattern.setExamSubClass(examRptPatternVo.getExamSubClass());
            examRptPattern.setOrgId(examRptPatternVo.getOrgId());
            count = count + Integer.parseInt(examRptPatternApi.save(examRptPattern));
        }
        for (int i = 0;i<examRptPatternVo.getUpdated().size();i++){
            ExamRptPattern examRptPattern = new ExamRptPattern();
            examRptPattern = (ExamRptPattern)examRptPatternVo.getUpdated().get(i);
            examRptPattern.setDescription(examRptPattern.getDescription());
            examRptPattern.setDescriptionCode(examRptPattern.getDescriptionCode());
            examRptPattern.setInputCode(PinYin2Abbreviation.cn2py(examRptPattern.getDescription()));
            examRptPattern.setExamClass(examRptPatternVo.getExamClass());
            examRptPattern.setExamSubClass(examRptPatternVo.getExamSubClass());
            examRptPattern.setOrgId(examRptPatternVo.getOrgId());
            count = count + Integer.parseInt(examRptPatternApi.save(examRptPattern));
        }
        StringData stringData = new StringData();
        if (count == (examRptPatternVo.getInserted().size() + examRptPatternVo.getUpdated().size())){
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
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
        String num = examRptPatternApi.delete(ids);
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
    public List<ExamRptPattern> listByClass(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<ExamRptPattern> list = new ArrayList<ExamRptPattern>();
        String orgId = request.getParameter("orgId");
        String className = null;
        String subClassName = null;
        try {
            className = URLDecoder.decode(request.getParameter("className"), "UTF-8");
            subClassName = URLDecoder.decode(request.getParameter("subClassName"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        list = examRptPatternApi.listByClass(orgId,className,subClassName);
        return list;
    }
}
