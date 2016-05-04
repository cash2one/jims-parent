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
    public List<DeptDict> list() {

        //查询出所有的科室信息
        List<DeptDict> list = deptDictApi.findAllList();

        //查询出所有的科室属性的类型
        List<OrgDeptPropertyDict> listProperty = deptPropertyDictApi.findProperty();

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
           // sb.deleteCharAt(sb.length() - 1);
            list.get(i).setDeptPropertity(sb.toString());

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
     * 保存修改方法
     *
     * @param
     * @return
     */
//    @Path("add")
//    @POST
//    public StringData save(DeptDictVo deptDictVo) {
//
//        System.out.print(deptDictVo);
//        DeptDict deptDict = new DeptDict();
//        deptDict.setParentId(deptDictVo.getParentId());
//
//        deptDict.setId(deptDictVo.getId());
//
//        deptDict.setDeptCode(deptDictVo.getDeptCode());
//        deptDict.setDeptName(deptDictVo.getDeptName());
//
//        StringBuilder sb = new StringBuilder();
//        String deptPropertity[] = deptDictVo.getArray();
//        for (int i = 0; i < deptPropertity.length; i++) {
//
//                sb.append(deptPropertity[i] + ";");
//        }
//
//        deptDict.setDeptPropertity(sb.toString());
//
//
//        String num = deptDictApi.save(deptDict);
//        StringData stringData = new StringData();
//        stringData.setData("success");
//        return stringData;
//    }


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
}
