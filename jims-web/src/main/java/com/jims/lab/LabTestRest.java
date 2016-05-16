package com.jims.lab;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.EmrDiagnosisServiceApi;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.data.StringData;
import com.jims.lab.api.LabTestMasterServiceApi;
import com.jims.lab.entity.LabTestItems;
import com.jims.lab.entity.LabTestMaster;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 异步加载信息表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<LabTestMaster> page = labTestMasterServiceApi.findPage(new Page<LabTestMaster>(request,response), new LabTestMaster());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
       /* Page<LabTestMaster> page = new Page<LabTestMaster>();
        PageData pageData=new PageData();
        List<LabTestMaster> list = new ArrayList<LabTestMaster>();
        LabTestMaster one = new LabTestMaster();
        one.setRequestedDateTime(new Date());
        one.setOrderingDept("1111");
        one.setMemo("1111-1,1111-2,1111-3,1111-4");
        list.add(one);
        pageData.setRows(list);
        pageData.setTotal(Long.valueOf(1));
        return pageData;*/
    }

    /**
     * 异步item信息列表
     * @param-request
     * @param-response
     * @return
     */
    @Path("items")
    @POST
    public List<ClinicItemNameDict> items(String consulaionId){
        List<ClinicItemNameDict> list = new ArrayList<ClinicItemNameDict>();
        ClinicItemNameDict one = new ClinicItemNameDict();
        one.setItemCode("0001");
        one.setItemName("11111111111111111111111111111111111111111111111111111111111111111111111");
        one.setPrice("11.5");
        ClinicItemNameDict one2 = new ClinicItemNameDict();
        one2.setItemCode("0002");
        one2.setItemName("222222222222222222222222222222222222222222222222222222222222222222222222");
        one2.setPrice("12.5");
        list.add(one);
        list.add(one2);
        ClinicItemNameDict one3 = new ClinicItemNameDict();
        one3.setItemCode("0003");
        one3.setPrice("13.5");
        one3.setItemName("33333333333333333333333333333333333333333333333333333333333333333");
        ClinicItemNameDict one4 = new ClinicItemNameDict();
        one4.setItemCode("0004");
        one4.setItemName("44444");
        one4.setPrice("14.5");
        ClinicItemNameDict one5= new ClinicItemNameDict();
        one5.setItemCode("0005");
        one5.setItemName("55555");
        one5.setPrice("15.5");
        list.add(one3);
        list.add(one4);
        list.add(one5);
        return list;
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
        one.setItemName("0001");
        one.setParentId("0");
        LabTestItems one2 = new LabTestItems();
        one2.setId("2");
        one2.setItemCode("00011");
        one2.setItemName("00011");
        one2.setParentId("1");
        list.add(one);
        list.add(one2);
        LabTestItems one3 = new LabTestItems();
        one3.setId("3");
        one3.setItemCode("00012");
        one3.setItemName("00012");
        one3.setParentId("1");
        LabTestItems one4 = new LabTestItems();
        one4.setId("4");
        one4.setItemCode("0004");
        one4.setItemName("0004");
        one4.setParentId("0");
        LabTestItems one5= new LabTestItems();
        one5.setId("5");
        one5.setItemCode("00041");
        one5.setItemName("00041");
        one5.setParentId("4");
        LabTestItems one6= new LabTestItems();
        one6.setId("6");
        one6.setItemCode("000411");
        one6.setItemName("000411");
        one6.setParentId("5");
        list.add(one3);
        list.add(one4);
        list.add(one5);
        list.add(one6);
        return list;
    }

    /**
     ** 保存或编辑
     * @param -LabTestMaster
     * @return
     */

    @Path("save")
    @POST
    public StringData save(LabTestMaster labTestMaster){
        StringData data=new StringData();
        if(labTestMaster!=null){
            if(labTestMaster.getVisitId()!=null)
            {
                labTestMasterServiceApi.saveAllIn(labTestMaster);
            }else{
                labTestMasterServiceApi.saveAll(labTestMaster);
            }
        }
        data.setData("success");
        return data;
    }

    /**
     **
     * @param
     * @return
     */

    @Path("zhenduan")
    @POST
    public List<EmrDiagnosis> zhenduan(EmrDiagnosis emrDiagnosis){
        List<EmrDiagnosis> diagnosises = emrDiagnosisServiceApi.findAllDiagnosisForOne(emrDiagnosis);
        return diagnosises;
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        LabTestMaster labTestMaster = new LabTestMaster();
        labTestMaster.setId(ids);
        labTestMasterServiceApi.delAll(labTestMaster);
        StringData stringData=new StringData();
        stringData.setData("success");
        return stringData;
    }
}
