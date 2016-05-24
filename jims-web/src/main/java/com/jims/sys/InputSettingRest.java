package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.vo.DeptDictVo;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("input-setting")
public class InputSettingRest {
    @Reference(version = "1.0.0")
    private InputSettingServiceApi inputSettingServiceApi;

    /**
     * 根据组织机构id查询输入法的主记录
     *
     * @param orgId
     * @return
     */
    @Path("findAllListByOrgId")
    @GET
    public List<InputSettingMaster> findAllListByOrgId(@QueryParam("orgId") String orgId) {
        return inputSettingServiceApi.findAllListByOrgId(orgId);
    }

    /**
     * 保存  增删改
     *
     * @param inputSettingMasterVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(InputSettingVo<InputSettingMaster> inputSettingMasterVo) {
        List<InputSettingMaster> newUpdateDict = new ArrayList<InputSettingMaster>();
        newUpdateDict = inputSettingServiceApi.saveAll(inputSettingMasterVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     *
     * @param id
     * @return
     * @author yangruidong
     */
    @Path("findListDetail")
    @GET
    public List<InputSettingDetail> findListDetail(@QueryParam("id") String id) {
        return inputSettingServiceApi.findListDetail(id);
    }


    /**
     * 根据表名称，查询表中有什么样的字段
     *
     * @param tableName
     * @return
     */
    @Path("listTableColByTableName")
    @GET
    public List<String> listTableColByTableName(@QueryParam("tableName") String tableName) {
        return inputSettingServiceApi.listTableColByTableName(tableName);
    }


    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     *
     * @param dictType 字典类型
     * @param orgId    组织机构
     * @return
     * @Author ztq
     */
    @Path("list")
    @GET
    public List<BaseDto> list(@QueryParam("dictType") String dictType, @QueryParam("orgId") String orgId) {
        List<BaseDto> list = inputSettingServiceApi.listInputDataBy(dictType, orgId);
        return list;
    }


    /**
     * 保存  增删改      输入法字典明细表
     *
     * @param inputSettingDetailVo
     * @return
     * @author yangruidong
     */
    @Path("saveDetail")
    @POST
    public StringData saveDetail(InputSettingVo<InputSettingDetail> inputSettingDetailVo) {
        List<InputSettingDetail> newUpdateDict = new ArrayList<InputSettingDetail>();
        newUpdateDict = inputSettingServiceApi.saveDetail(inputSettingDetailVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 根据传入的信息查询并过滤相关内容
     *
     * @param inputInfoVo
     * @return
     */
    @Path("listParam")
    @POST
    public List<BaseDto> listParam(InputInfoVo inputInfoVo) {
       /* InputParamVo vo = new InputParamVo();
        vo.setColName("FLAG_SHOW");
        vo.setColValue("1");
        vo.setOperateMethod("=");

        InputParamVo vo1 = new InputParamVo();
        vo1.setColName("FLAG_ISNAME");
        vo1.setColValue("Y");
        vo1.setOperateMethod("=");
        List<InputParamVo> in = new ArrayList<InputParamVo>();
        in.add(vo);
        in.add(vo1);
        inputInfoVo.setInputParamVos(in);*/

        List<BaseDto> list = inputSettingServiceApi.listInputDataByParam(inputInfoVo);
        return list;
    }
}
