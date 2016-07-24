package com.jims.doctor.lab;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.diagnosis.api.EmrDiagnosisServiceApi;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.common.data.StringData;
import com.jims.lab.api.LabTestItemsServiceApi;
import com.jims.lab.api.LabTestMasterServiceApi;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("labtest")
public class LabTestRest {

    @Reference(version = "1.0.0")
    public LabTestMasterServiceApi labTestMasterServiceApi;

    @Reference(version = "1.0.0")
    public EmrDiagnosisServiceApi emrDiagnosisServiceApi;
    @Reference(version = "1.0.0")
    private LabTestItemsServiceApi labTestItemsServiceApi;

    /**
     * 门诊检验列表
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response,@QueryParam("clinicId")String clinicId){
        LabTestMaster labTestMaster = new LabTestMaster();
        labTestMaster.setClinicId(clinicId);
        Page<LabTestMaster> page = labTestMasterServiceApi.findPage(new Page<LabTestMaster>(request,response), labTestMaster);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 住院检验列表
     * @param request
     * @param response
     * @return
     */
    @Path("listHos")
    @GET
    public PageData listHos(@Context HttpServletRequest request,@Context HttpServletResponse response,@QueryParam("visitId")String visitId,
                            @QueryParam("patientId")String patientId){
        LabTestMaster labTestMaster = new LabTestMaster();
        labTestMaster.setVisitId(visitId);
        labTestMaster.setPatientId(patientId);
        Page<LabTestMaster> page = labTestMasterServiceApi.findPage(new Page<LabTestMaster>(request,response), labTestMaster);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 异步item信息列表
     * @param-request
     * @param-response
     * @return
     */
    @Path("treeresult")
    @GET
    public List<LabTestItems> treeresult(String consulaionId){
        List<LabTestItems> list = new ArrayList<LabTestItems>();
        LabTestItems one = new LabTestItems();
        one.setId("1");
        one.setItemCode("0001");
        one.setItemName("谷丙转氨酶");
        one.setParentId("0");
        LabTestItems one2 = new LabTestItems();
        one2.setId("2");
        one2.setItemCode("00011");
        one2.setItemName("乙肝表面抗体");
        one2.setParentId("1");
        list.add(one);
        list.add(one2);
        LabTestItems one3 = new LabTestItems();
        one3.setId("3");
        one3.setItemCode("00012");
        one3.setItemName("乙肝表面抗体");
        one3.setParentId("1");
        LabTestItems one4 = new LabTestItems();
        one4.setId("4");
        one4.setItemCode("0004");
        one4.setItemName("尿白细胞");
        one4.setParentId("0");
        LabTestItems one5= new LabTestItems();
        one5.setId("5");
        one5.setItemCode("00041");
        one5.setItemName("尿蛋白质");
        one5.setParentId("4");
        LabTestItems one6= new LabTestItems();
        one6.setId("6");
        one6.setItemCode("000411");
        one6.setItemName("酸碱度");
        one6.setParentId("5");
        list.add(one3);
        list.add(one4);
        list.add(one5);
        list.add(one6);
        return list;
    }

    /**
     ** 门诊保存或编辑
     * @param -LabTestMaster
     * @return
     */

    @Path("save")
    @POST
    public StringData save(LabTestMaster labTestMaster,@Context HttpServletRequest request, @Context HttpServletResponse response){
        String mun="";
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        labTestMaster.setOrderingDept(loginInfo.getDeptId());
        labTestMaster.setOrderingProvider(loginInfo.getPersionId());
        labTestMaster.setOrderingProvider(loginInfo.getPersionId());//送检医生
        StringData data=new StringData();
        mun = labTestMasterServiceApi.saveAll(labTestMaster);
        data.setData("success");
        data.setCode(mun);
        return data;
    }
    /**
     ** 住院保存或编辑
     * @param -LabTestMaster
     * @return
     */
    @Path("saveHos")
    @POST
    public StringData saveHos(LabTestMaster labTestMaster){
        StringData data=new StringData();
        String mun="";
        mun = labTestMasterServiceApi.saveAllIn(labTestMaster);
        data.setData("success");
        data.setCode(mun);
        return data;
    }

    /**
     **
     * @param
     * @return
     */

    @Path("zhenduan")
    @GET
    public List<EmrDiagnosis> zhenduan(@QueryParam("clinicId")String clinicId){
        EmrDiagnosis emrDiagnosis = new EmrDiagnosis();
        emrDiagnosis.setClinicId(clinicId);
        List<EmrDiagnosis> diagnosises = emrDiagnosisServiceApi.findAllDiagnosisForOne(emrDiagnosis);
        return diagnosises;
    }
    /**
     *门诊记录删除
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        labTestMasterServiceApi.deleteLabTestMaster(ids);
        StringData stringData=new StringData();
        stringData.setData("success");
        return stringData;
    }

    /**
     * 住院记录删除
     * @param ids
     * @return
     */
    @Path("delHos")
    @POST
    public StringData deleteLabTestMaster(String ids){
        StringData stringData=new StringData();
        labTestMasterServiceApi.deleteLabTestMasterHos(ids);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 通过labMaster获取对应检验项目列表
     * @param labMaster
     * @return
     */
    @Path("getItem")
    @POST
    public List<LabTestItems> getItem(String labMaster){
        List<LabTestItems> labTestItemsList=labTestItemsServiceApi.getItemName(labMaster);
        return labTestItemsList;
    }
}
