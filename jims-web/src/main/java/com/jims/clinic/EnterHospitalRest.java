package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.clinic.api.ElectronEnterHospitalServiceApi;
import com.jims.clinic.entity.ElectronEnterHospital;
import com.jims.common.data.StringData;
import com.jims.sys.entity.Dict;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * 出院记录
 */
@Component
@Produces("application/json")
@Path("enter")
public class EnterHospitalRest {

  @Reference(version = "1.0.0")
  private ElectronEnterHospitalServiceApi electronEnterHospitalApi;

  /**
   * 保存入院记录
   */
  @Path("save")
  @POST
  public StringData save(ElectronEnterHospital electronEnterHospital) {

    StringData data = new StringData();
    String num = data.getCode();
    if (electronEnterHospital != null) {
      num = electronEnterHospitalApi.saveEnter(electronEnterHospital);
    }
    data.setCode(num);
    data.setData("success");
    return data;

  }

  /**
   * 查询
   * @param patId
   * @return
   */
  @Path("get")
  @POST
  public ElectronEnterHospital getElectronEnteHos(String patId){
    return electronEnterHospitalApi.getElectronEnteHos(patId);
  }

}
