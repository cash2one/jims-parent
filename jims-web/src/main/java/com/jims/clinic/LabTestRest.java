package com.jims.clinic;

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

    /**
     * 异步加载信息表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        /*Page<ElectronGroupConsultation> page = electronGroupConsultationApi.findPage(new Page<ElectronGroupConsultation>(request,response), new ElectronGroupConsultation());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;*/
        Page<LabTestMaster> page = new Page<LabTestMaster>();
        PageData pageData=new PageData();
        List<LabTestMaster> list = new ArrayList<LabTestMaster>();
        LabTestMaster one = new LabTestMaster();
        one.setRequestedDateTime(new Date());
        one.setOrderingDept("1111");
        one.setMemo("1111-1,1111-2,1111-3,1111-4");
        list.add(one);
        pageData.setRows(list);
        pageData.setTotal(Long.valueOf(1));
        return pageData;
    }

    /**
     * 异步item信息列表
     * @param-request
     * @param-response
     * @return
     */
    @Path("items")
    @POST
    public List<LabTestItems> items(String consulaionId){
        List<LabTestItems> list = new ArrayList<LabTestItems>();
        LabTestItems one = new LabTestItems();
        one.setItemCode("0001");
        one.setItemName("11111111111111111111111111111111111111111111111111111111111111111111111");
        LabTestItems one2 = new LabTestItems();
        one2.setItemCode("0002");
        one2.setItemName("222222222222222222222222222222222222222222222222222222222222222222222222");
        list.add(one);
        list.add(one2);
        LabTestItems one3 = new LabTestItems();
        one3.setItemCode("0003");
        one3.setItemName("33333333333333333333333333333333333333333333333333333333333333333");
        LabTestItems one4 = new LabTestItems();
        one4.setItemCode("0004");
        one4.setItemName("44444");
        LabTestItems one5= new LabTestItems();
        one5.setItemCode("0005");
        one5.setItemName("55555");
        list.add(one3);
        list.add(one4);
        list.add(one5);
        return list;
    }
}
