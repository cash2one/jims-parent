package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.EmrDataIcd10ServiceApi;
import com.jims.clinic.entity.EmrDataIcd10;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * EmrDataIcd10Rest
 *
 * @author PangQian
 * @date2016/4/26 0026
 */
@Component
@Produces("application/json")
@Path("dataicd")
public class EmrDataIcd10Rest {
    @Reference(version = "1.0.0")
    private EmrDataIcd10ServiceApi emrDataIcd10ServiceApi;

    @Path("autoComplete")
    @GET
    public List<EmrDataIcd10> findAutoCompleteData(){
        EmrDataIcd10 emrDataIcd10=new EmrDataIcd10();
        List<EmrDataIcd10> list= emrDataIcd10ServiceApi.findList(emrDataIcd10);
        return list;
    }
}
