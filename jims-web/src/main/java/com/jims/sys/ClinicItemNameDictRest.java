package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.ClinicItemNameDictServiceApi;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.sys.api.ClinicItemClassDictServiceApi;
import com.jims.sys.entity.ClinicItemClassDict;
import com.jims.sys.entity.LabItemClassDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by xueyx on 2016/5/5 0024.
 */
@Component
@Produces("application/json")
@Path("clinicitemname")
public class ClinicItemNameDictRest {

    @Reference(version = "1.0.0")
    private ClinicItemNameDictServiceApi clinicNameClassDictServiceApi;

    /**
     * 查询检验项目
     * @param标本expand1
     * @param检验类别expand2
     * @param科室expand3
     * @author xueyx
     * @version 2016/5/06
     */
    @Path("selectlabitem")
    @POST
    public List<ClinicItemNameDict> selectLabItem(ClinicItemNameDict clinicItemNameDict) {
        List<ClinicItemNameDict> list = clinicNameClassDictServiceApi.selectLabItem(clinicItemNameDict);
        return list;
    }

}
