package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.SysCompany;
//import com.jims.sys.vo.DeptDictVo;
import com.jims.sys.vo.DeptDictVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("dept-dict")
public class DeptDictRest {
    @Reference(version = "1.0.0")
    private DeptDictApi deptDictApi;

    @Reference(version = "1.0.0")
    private DeptPropertyDictApi deptPropertyDictApi;

    /**
     * 查询所有的科室信息
     *
     * @return
     */
    @Path("list")
    @GET
    public List<DeptDict> list(@QueryParam("orgId") String orgId) {

        //查询出所有的科室信息
        List<DeptDict> list = deptDictApi.findAllList(orgId);

        //查询出所有的科室属性的类型
        List<OrgDeptPropertyDict> listProperty = deptPropertyDictApi.findProperty(orgId);
        if (listProperty.size() > 0) {
            //遍历所有的科室信息
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder();
                //得到每一个对象的科室属性，以；进行切割
                String[] str = list.get(i).getDeptPropertity().split(";");
                //遍历获得的数组

                for (int y = 0; y < str.length; y++) {
                    //得到每一个切割后的科室属性值
                    if (StringUtils.isNotBlank(str[y])) {
                        //拿科室属性值和科室的类型去数据库中查询科室属性名称
                        OrgDeptPropertyDict listName = deptPropertyDictApi.findNameByTypeAndValue(listProperty.get(y).getPropertyType(), str[y]);
                        if (listName == null) {
                            sb.append("");
                        } else {

                            sb.append(listName.getPropertyName() + " ");
                        }
                    }
                }

                list.get(i).setDeptPropertity(sb.toString());

            }
        }
        return list;
    }

    /**
     * 查询所有的上级科室
     *
     * @return
     */
    @Path("selectParent")
    @POST
    public List<DeptDict> findParent() {

        List<DeptDict> list = deptDictApi.findParent();
        return list;
    }

    /**
     * 查询某个机构上级科室
     *
     * @return
     */
    @Path("selectParentByOrgId")
    @POST
    public List<DeptDict> findParent(@QueryParam("orgId") String orgId) {

        List<DeptDict> list = deptDictApi.findListParent(orgId);
        return list;
    }

    /**
     * 保存修改方法
     *
     * @param
     * @return
     */
    @Path("add")
    @POST
    public StringData save(DeptDictVo deptDictVo) {


        DeptDict deptDict = new DeptDict();
        deptDict.setParentId(deptDictVo.getParentId());

        deptDict.setId(deptDictVo.getId());

        deptDict.setDeptCode(deptDictVo.getDeptCode());
        deptDict.setDeptName(deptDictVo.getDeptName());
        deptDict.setOrgId(deptDictVo.getOrgId());
        deptDict.setInputCode(deptDictVo.getInputCode());
        StringBuilder sb = new StringBuilder();
        String deptPropertity[] = deptDictVo.getArray();
        if (deptPropertity.length == 1) {
            sb.append(deptPropertity[0]);
        } else {
            for (int i = 0; i < deptPropertity.length; i++) {

                sb.append(deptPropertity[i] + ";");
            }
        }

        if(deptPropertity.length>1)
        {
            sb.deleteCharAt(sb.length() - 1);
        }

        deptDict.setDeptPropertity(sb.toString());


        String num = deptDictApi.save(deptDict);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }


    /**
     * 删除科室信息
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = deptDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询所有的下级科室
     *
     * @return
     */
    @Path("findListByCode")
    @POST
    public List<DeptDict> findListByCode(String code) {
        int index = code.indexOf("=");
        code = code.substring(index + 1);
        List<DeptDict> list = deptDictApi.findListByCode(code);
        return list;
    }
}
